package com.igeekshop.ssm.service.impl;

import com.igeekshop.ssm.dao.UsersMapper;
import com.igeekshop.ssm.domain.Users;
import com.igeekshop.ssm.domain.UsersExample;
import com.igeekshop.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSeviceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public Users login(Users user) {
        List<Users> users=null;
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(user.getEmail());
        criteria.andPasswordEqualTo(user.getPassword());
        users = usersMapper.selectByExample(example);
        if (users.size()>0)
        {
            return users.get(0);
        }
        else {
            return  null;
        }

    }

    @Override
    public boolean regist(Users user) {

        int row=0;
        try {
            row=usersMapper.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return row>0?true:false;

    }

    @Override
    public void active(String activeCode) {
//usersMapper.up
    }

    @Override
    public boolean checkUsername(String username) {
        return false;
    }
}
