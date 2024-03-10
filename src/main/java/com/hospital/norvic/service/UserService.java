package com.hospital.norvic.service;

import com.hospital.norvic.entity.UserInfo;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserInfo saveUser(UserInfo userInfo) throws IOException;

    UserInfo updateUser(UserInfo userInfo);

    UserInfo getUserById(Integer id);

    List<UserInfo> getAllUser();

    String deleteUserById(Integer id);
}
