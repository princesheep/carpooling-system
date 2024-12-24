package com.nwpu.carpoolingsystem.generator;

import com.nwpu.carpoolingsystem.util.RsaUtils;
import org.junit.jupiter.api.Test;


public class RsaTest {
    private String publicFile = "D:\\java_project\\carpooling-system\\carpooling-system\\src\\resources\\rsa_key\\rsa_key.pub";
    private String privateFile = "D:\\java_project\\carpooling-system\\carpooling-system\\src\\resources\\rsa_key\\rsa_key";


    /**生成公钥和私钥
     * @author wzy
     * @date 2024-12-23 10:32:16
     * @throws Exception
     * @version 1.0
     */
    @Test
    public void generateKey() throws Exception{

        RsaUtils.generateKey(publicFile,privateFile,"西工大拼车系统",2048);

    }

}

