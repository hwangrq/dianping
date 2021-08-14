package com.hwangrq.dianping.service.impl;

import com.hwangrq.dianping.common.BusinessException;
import com.hwangrq.dianping.common.EnumBusinessError;
import com.hwangrq.dianping.dal.UserModelMapper;
import com.hwangrq.dianping.model.UserModel;
import com.hwangrq.dianping.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author hwangrq
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelMapper userModelMapper;


    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Date now = new Date();
        registerUser.setCreatedAt(now);
        registerUser.setUpdatedAt(now);
        registerUser.setPassword(encodeByMd5(registerUser.getPassword()));
        try {
            userModelMapper.insert(registerUser);
        } catch (DuplicateKeyException ex) {
            log.error(ex.getMessage());
            throw new BusinessException(EnumBusinessError.REGISTER_DUP_FAIL);
        }
        return getUser(registerUser.getId());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserModel login(String telephone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        UserModel userModel = userModelMapper.selectByTelephoneAndPassword(telephone, encodeByMd5(password));
        if (userModel == null) {
            throw new BusinessException(EnumBusinessError.LOGIN_FAIL);
        }
        return userModel;
    }

    @Override
    public Integer countAllUser() {
        return userModelMapper.countAllUser();
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes(StandardCharsets.UTF_8)));
    }
}
