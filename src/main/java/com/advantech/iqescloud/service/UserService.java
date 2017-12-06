package com.advantech.iqescloud.service;

import com.advantech.iqescloud.entity.DTO.UserDTO;
import com.advantech.iqescloud.entity.IqesUser;
import com.advantech.iqescloud.entity.RabbitCarrier;
import com.advantech.iqescloud.entity.Restaurant;
import com.advantech.iqescloud.entity.UserFeedback;
import com.advantech.iqescloud.repository.RestaurantDao;
import com.advantech.iqescloud.repository.UserDao;
import com.advantech.iqescloud.repository.UserFeedbackDao;
import com.advantech.iqescloud.utils.RabbitMqSendMessageUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


/**
 * @author huqili.tp
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserFeedbackDao userFeedbackDao;

    @Autowired
    private RestaurantDao restaurantDao;


    public String saveUser(UserDTO userDTO) throws Exception {

        IqesUser user = new IqesUser();
        if (userDTO.getTel()==null||userDTO.getPassword()==null){
            throw new Exception("The account or password cannot be empty");
        }
        if (userDTO.getUserName() == null) {
            user.setUserName("unnamed");
        }

        user.setCreditValue(100);
        user.setMemberIntegral(0);
        user.setTel(userDTO.getTel());
        user.setPassword(userDTO.getPassword());
        user.setUniqueName("unnamed" + "_" + System.currentTimeMillis());

        userDao.save(user);
        return "注册成功";
    }

    public UserDTO login(String tel, String password) throws Exception {
        UserDTO userDTO;
        IqesUser u = userDao.findByTel(tel);
        if (u == null) {
            throw new Exception("This account is not registered");
        } else {
            if (!password.equals(u.getPassword())) {
                throw new Exception("The account or password is wrong");
            } else {
                userDTO = new UserDTO(u);
            }
        }
        return userDTO;
    }

    public void feedback(long userId, String context) throws Exception {
        System.out.println("feedback,without picture");
        UserFeedback userFeedback = new UserFeedback();
        /**
         * 绑定用户
         */
        IqesUser user = userDao.findOne(userId);
        if (user == null) {
            throw new Exception("this user is not exist!");
        } else {
            userFeedback.setIqesUser(user);
        }
        /**
         * 绑定内容
         */
        userFeedback.setContext(context);

//        /**
//         * 绑定图片
//         */
//        if(photo!=null) {
//            String localPath = request.getSession().getServletContext().getRealPath("/photo/feedback");
//            String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
//
//            File dir = new File(localPath);
//            if (!dir.exists()) {
//                dir.mkdir();
//            }
//            try {
//                photo.transferTo(new File(localPath + "\\" + fileName));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            userFeedback.setPhotoUrl(request.getServletContext().getContextPath()+"/photo/feedback/"+fileName);
//        }
        userFeedbackDao.save(userFeedback);
    }

    public JSONObject checkOrder(String customerName, long restaurantId) {

        RabbitCarrier rabbitCarrier = new RabbitCarrier();
        rabbitCarrier.setServiceName("QueueService");
        rabbitCarrier.setMethodName("checkOrder");

        JSONObject parameterJsonObject = new JSONObject();
        parameterJsonObject.put("customerName", customerName);

        rabbitCarrier.setParameter(JSONObject.toJSONString(parameterJsonObject));

        JSONObject jsonObject = RabbitMqSendMessageUtils.sendMessage(rabbitCarrier, String.valueOf(restaurantId));
        return jsonObject;
    }

    public JSONObject getUserOrder(long userId, long restaurantId) {


        RabbitCarrier rabbitCarrier = new RabbitCarrier();
        rabbitCarrier.setServiceName("QueueService");
        rabbitCarrier.setMethodName("checkOrder");

        JSONObject parameterJsonObject = new JSONObject();
        String customerName = userDao.findOne(userId).getUniqueName();
        parameterJsonObject.put("customerName", customerName);

        rabbitCarrier.setParameter(JSONObject.toJSONString(parameterJsonObject));

        JSONObject jsonObject = RabbitMqSendMessageUtils.sendMessage(rabbitCarrier, String.valueOf(restaurantId));
        /**
         * load restaurant info
         */
        jsonObject.put("restaurantInfo",restaurantDao.getOne(restaurantId));

        return jsonObject;
    }

}





