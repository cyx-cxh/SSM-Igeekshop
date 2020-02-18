package com.igeekshop.ssm.service;


import com.igeekshop.ssm.domain.Users;

public interface UserService {
    public Users login(Users user) ;

    public boolean regist(Users user);

    public void active(String activeCode);

    public boolean checkUsername(String username);
}
