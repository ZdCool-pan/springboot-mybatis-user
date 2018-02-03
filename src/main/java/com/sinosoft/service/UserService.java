package com.sinosoft.service;

import com.sinosoft.mapper.UserMapper;
import com.sinosoft.database.DynamicDataSourceContextHolder;
import com.sinosoft.database.TargetDataSource;
import com.sinosoft.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @TargetDataSource(name = "ds1")
    public User selectByPrimaryKey(String userid) {
        LOGGER.info(DynamicDataSourceContextHolder.getDataSourceType());
        return userMapper.selectByPrimaryKey(userid);
    }
}
