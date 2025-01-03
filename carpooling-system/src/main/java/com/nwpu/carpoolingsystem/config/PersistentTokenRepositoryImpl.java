package com.nwpu.carpoolingsystem.config;

import com.nwpu.carpoolingsystem.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.security.Security;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 存入redis而不是数据库，可以减轻mysql访问压力，用于remember-me的持久化操作
 */
@Component
public class PersistentTokenRepositoryImpl implements PersistentTokenRepository {
    private final static String USERNAME_KEY = "rememberMe:USERNAME_KEY:";
    private final static String SERIES_KEY = "rememberMe:SERIES_KEY:";


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 创建token 存入到redis中
     * 14天过期
     * @param persistentRememberMeToken
     */
    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        // token 含有 username series tokenValue date 四个属性
        //redis中存储的时series,series在用户二次登录后也不会更新

        String series = persistentRememberMeToken.getSeries();
        //生成存储token信息的key,series + 前缀
        String key = generateKey(series,SERIES_KEY);
        System.out.println(SecurityUtils.getCurrentUser());
        System.out.println(persistentRememberMeToken.getUsername());

        // 改成邮箱 , 这里无法获取username，刚开始为空值
        String usernameKey = generateKey(persistentRememberMeToken.getUsername(),USERNAME_KEY);
        System.out.println("usernameKey:" + usernameKey);

        //用户只要采用账户密码重新登录，那么为了安全就有必要清除之前的token信息。deleteIfPresent方法通过
        //username查找到用户对应的series，然后删除旧的token信息。
        deleteIfPresent(usernameKey);
        HashMap<String,String > hashMap = new HashMap<>();
        hashMap.put("username",persistentRememberMeToken.getUsername());
        //获取token的值
        hashMap.put("token",persistentRememberMeToken.getTokenValue());
        hashMap.put("date",String.valueOf(persistentRememberMeToken.getDate().getTime()));
        //将数据插入  username为空需要修改
        redisTemplate.opsForHash().putAll(key,hashMap);

        redisTemplate.expire(key,14, TimeUnit.DAYS);//设置token保存期限


        stringRedisTemplate.opsForValue().set(usernameKey,series);

        //设置过期时间
        redisTemplate.expire(usernameKey,14, TimeUnit.DAYS);
    }

    @Override
    public void updateToken(String s, String s1, Date date) {
        String key = generateKey(s,SERIES_KEY);
        if(redisTemplate.hasKey(key)) {
            redisTemplate.opsForHash().put(key,"token",s1);
        }

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        String key = generateKey(s,SERIES_KEY);
        List<String> hashKeys = new ArrayList<>();
        hashKeys.add("username");
        hashKeys.add("token");
        hashKeys.add("date");
        List<String> hashValues = redisTemplate.opsForHash().multiGet(key, hashKeys);
        String username =  hashValues.get(0);
        String tokenValue = hashValues.get(1);
        String date = hashValues.get(2);
        if (null == username || null == tokenValue || null == date) {
            return null;
        }
        Long timestamp = Long.valueOf(date);
        Date time = new Date(timestamp);
        return new PersistentRememberMeToken(username, s, tokenValue, time);
    }

    @Override
    public void removeUserTokens(String s) {
        //rememberMeService实现类中调用这个方法传入的参数是username，因此我们必须通过username查找到
        //对应的series，然后再通过series查找到对应的token信息再删除。
        String key = generateKey(s,USERNAME_KEY);
        deleteIfPresent(key);
    }

    private void deleteIfPresent(String key){
        //删除token时应该同时删除token信息，以及保存了对应的username与series对照数据。
        if(redisTemplate.hasKey(key)){
            String series = generateKey(stringRedisTemplate.opsForValue().get(key),SERIES_KEY);
            if(series != null && redisTemplate.hasKey(series)){
                redisTemplate.delete(series);
                redisTemplate.delete(key);
            }
        }
    }
    private String generateKey(String series,String prefix) {
        return prefix + series;
    }

}
