package com.nwpu.carpoolingsystem.service.impl;

import com.nwpu.carpoolingsystem.entity.Carpool;
import com.nwpu.carpoolingsystem.entity.Result;
import com.nwpu.carpoolingsystem.entity.UserAuthVO;
import com.nwpu.carpoolingsystem.mapper.CarpoolMapper;
import com.nwpu.carpoolingsystem.mapper.UserMapper;
import com.nwpu.carpoolingsystem.service.CarpoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@Service
public class CarpoolServiceImpl implements CarpoolService {
    @Autowired
    private CarpoolMapper carpoolMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result createCarpool(Carpool carpool) {
        //获取当前登陆用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthVO currentUser = (UserAuthVO) authentication.getPrincipal();
        Long userId = userMapper.findIdByUserName(currentUser.getUsername());
        carpool.setOwnerId(userId);
        carpool.setAvailableSeats(carpool.getTotalSeats());
        carpool.setState(1);
        int row = carpoolMapper.insertCarpool(carpool);
        if(row == 0){
            return new Result(500, "创建失败");

        }
        return new Result(200, "创建成功");
    }

    @Override
    public Result getCurrentUserCarpool() {
        //获取当前登陆用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthVO currentUser = (UserAuthVO) authentication.getPrincipal();
        Long userId = userMapper.findIdByUserName(currentUser.getUsername());
        Carpool carpool = new Carpool();

        List<Carpool> carpools = carpoolMapper.getCurrentUserCarpool(carpool);
        List<HashMap<String, Object>> data = new ArrayList<>();
        if(carpools != null){
            for (Carpool newCarpool : carpools) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("ownerName", userMapper.findUserNameById(newCarpool.getOwnerId()));
                map.put("startPoint", newCarpool.getStartPoint());
                map.put("endPoint", newCarpool.getEndPoint());
                map.put("availableSeats", newCarpool.getAvailableSeats());
                map.put("totalSeats", newCarpool.getTotalSeats());
                map.put("route", newCarpool.getRoute());
                map.put("plateNumber", newCarpool.getPlateNumber());
                data.add(map);
            }
        }
        return new Result(200, "查询成功", data);
    }

    @Override
    public Result getCurrentUserCarpoolByParam(Carpool carpool) {
        //获取当前登陆用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthVO currentUser = (UserAuthVO) authentication.getPrincipal();
        Long userId = userMapper.findIdByUserName(currentUser.getUsername());
        carpool.setOwnerId(userId);
        List<Carpool> carpools;
        if(carpool.getUseState()){
            if (carpool.getUseTime()) {
                carpools = carpoolMapper.getCurrentUserCarpoolByStateAndTime(carpool);
            }else {
                carpools = carpoolMapper.getCurrentUserCarpoolByState(carpool);
            }
        }else {
            if (carpool.getUseTime()) {
                carpools = carpoolMapper.getCurrentUserCarpoolByTime(carpool);
            }else {
                carpools = carpoolMapper.getCurrentUserCarpool(carpool);
            }
        }

        List<HashMap<String, Object>> data = new ArrayList<>();
        if(carpools != null){
            for (Carpool newCarpool : carpools) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("Id", newCarpool.getId());
                map.put("ownerName", userMapper.findUserNameById(newCarpool.getOwnerId()));
                map.put("startPoint", newCarpool.getStartPoint());
                map.put("endPoint", newCarpool.getEndPoint());
                map.put("availableSeats", newCarpool.getAvailableSeats());
                map.put("totalSeats", newCarpool.getTotalSeats());
                map.put("route", newCarpool.getRoute());
                map.put("plateNumber", newCarpool.getPlateNumber());
                data.add(map);
            }
        }
        return new Result(200, "查询成功", data);
    }

    @Override
    public Result updateCarpoolPoint(Carpool carpool) {
        //获取当前登陆用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthVO currentUser = (UserAuthVO) authentication.getPrincipal();
        Long userId = userMapper.findIdByUserName(currentUser.getUsername());

        if(!userId.equals(carpoolMapper.getOwnerIdById(carpool))){
            return new Result(500, "没有修改此订单的权限");
        }
        if(carpoolMapper.getStateById(carpool) == 0){
            return new Result(500, "订单已过期");
        }
        carpoolMapper.updateCarpoolPoint(carpool);
        return new Result(200, "更新成功");
    }

    @Override
    public Result updateCarpoolSeat(Carpool carpool) {
        //获取当前登陆用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthVO currentUser = (UserAuthVO) authentication.getPrincipal();
        Long userId = userMapper.findIdByUserName(currentUser.getUsername());

        if(!userId.equals(carpoolMapper.getOwnerIdById(carpool))){
            return new Result(500, "没有修改此订单的权限");
        }
        if(carpoolMapper.getStateById(carpool) == 0){
            return new Result(500, "订单已过期");
        }
        carpoolMapper.updateCarpoolSeat(carpool);
        return new Result(200, "更新成功");
    }

    @Override
    public Result comleleteCarpool(Carpool carpool) {
        //获取当前登陆用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthVO currentUser = (UserAuthVO) authentication.getPrincipal();
        Long userId = userMapper.findIdByUserName(currentUser.getUsername());

        if(!userId.equals(carpoolMapper.getOwnerIdById(carpool))){
            return new Result(500, "没有修改此订单的权限");
        }
        if(carpoolMapper.getStateById(carpool) == 0){
            return new Result(500, "订单已过期");
        }

        carpoolMapper.compeleteCarpool(carpool);
        return new Result(200, "操作成功");
    }

    @Override
    public Result getCarpool() {
        List<Carpool> carpools = carpoolMapper.getCarpool();
        List<HashMap<String, Object>> data = new ArrayList<>();
        if(carpools != null){
            for (Carpool newCarpool : carpools) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("Id", newCarpool.getId());
                map.put("ownerName", userMapper.findUserNameById(newCarpool.getOwnerId()));
                map.put("startPoint", newCarpool.getStartPoint());
                map.put("endPoint", newCarpool.getEndPoint());
                map.put("availableSeats", newCarpool.getAvailableSeats());
                map.put("totalSeats", newCarpool.getTotalSeats());
                map.put("route", newCarpool.getRoute());
                map.put("plateNumber", newCarpool.getPlateNumber());
                data.add(map);
            }
        }
        return new Result(200, "查询成功", data);
    }

    @Override
    public Result getCarpoolByParam(Carpool carpool) {
        List<Carpool> carpools;
        if(carpool.getUseOwner()){

            String username = carpool.getUsername();
            StringBuilder newname = new StringBuilder("%");
            for (int i = 0; i < username.length(); i++) {
                newname.append(username.charAt(i)).append("%");
            }
            carpool.setUsername(newname.toString());

            if (carpool.getUseTime()) {
                carpools = carpoolMapper.getCarpoolByOwnerAndTime(carpool);
            }else {
                carpools = carpoolMapper.getCarpoolByOwner(carpool);
            }
        }else {
            if (carpool.getUseTime()) {
                carpools = carpoolMapper.getCarpoolByTime(carpool);
            }else {
                carpools = carpoolMapper.getCarpool();
            }
        }

        List<HashMap<String, Object>> data = new ArrayList<>();
        if(carpools != null){
            for (Carpool newCarpool : carpools) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("Id", newCarpool.getId());
                map.put("ownerName", userMapper.findUserNameById(newCarpool.getOwnerId()));
                map.put("startPoint", newCarpool.getStartPoint());
                map.put("endPoint", newCarpool.getEndPoint());
                map.put("availableSeats", newCarpool.getAvailableSeats());
                map.put("totalSeats", newCarpool.getTotalSeats());
                map.put("route", newCarpool.getRoute());
                map.put("plateNumber", newCarpool.getPlateNumber());
                data.add(map);
            }
        }
        return new Result(200, "查询成功", data);
    }

    @Override
    public Result updateCarpoolTime(Carpool carpool) {
        //获取当前登陆用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthVO currentUser = (UserAuthVO) authentication.getPrincipal();
        Long userId = userMapper.findIdByUserName(currentUser.getUsername());

        if(!userId.equals(carpoolMapper.getOwnerIdById(carpool))){
            return new Result(500, "没有修改此订单的权限");
        }
        if(carpoolMapper.getStateById(carpool) == 0){
            return new Result(500, "订单已过期");
        }
        carpoolMapper.updateCarpoolTime(carpool);
        return new Result(200, "更新成功");
    }
}
