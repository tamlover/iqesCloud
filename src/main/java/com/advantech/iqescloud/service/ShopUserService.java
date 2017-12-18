package com.advantech.iqescloud.service;

import com.advantech.iqescloud.entity.DTO.ShopUserDTO;
import com.advantech.iqescloud.entity.ShopUser;
import com.advantech.iqescloud.repository.ShopUserDao;
import com.advantech.iqescloud.utils.MyMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class ShopUserService {


    @Autowired
    private ShopUserDao shopUserDao;

    /**
     * 注册用户
     *
     * @param userName
     * @param password
     */
    public void registerUser(String userName, String password) {
        String encryptedPwd = null;
        ShopUser shopUser = new ShopUser();
        try {
            encryptedPwd = MyMD5Util.getEncryptedPwd(password);

            shopUser.setAccount(userName);
            shopUser.setPassword(encryptedPwd);

            shopUserDao.save(shopUser);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 验证登陆
     *
     * @param
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public void loginValid(ShopUserDTO shopUserDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        ShopUser shopUser = shopUserDao.findByAccount(shopUserDTO.getAccount());

        if (null == shopUser) {
            throw new ServiceException("The account error");
        } else {
            if (!MyMD5Util.validPassword(shopUserDTO.getPwd(), shopUser.getPassword())) {
                throw new ServiceException("The password error");
            }
        }
    }
}
