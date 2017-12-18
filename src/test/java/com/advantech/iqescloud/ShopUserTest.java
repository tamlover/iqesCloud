package com.advantech.iqescloud;

import com.advantech.iqescloud.entity.DTO.ShopUserDTO;
import com.advantech.iqescloud.service.ShopUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopUserTest {

    @Autowired
    private ShopUserService shopUserService;

    @Test
    public void testRegister(){
        String account="admin";
        String password="1234";

        shopUserService.registerUser(account,password);
    }

    @Test
    public void login() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ShopUserDTO shopUserDTO=new ShopUserDTO();
        shopUserDTO.setAccount("admina");
        shopUserDTO.setPwd("123");

        shopUserService.loginValid(shopUserDTO);
    }
}
