package com.hospital.norvic.service;

import com.hospital.norvic.entity.UserInfo;
import com.hospital.norvic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public UserInfo updateUser(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public UserInfo getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Info is not available."));
    }

    @Override
    public List<UserInfo> getAllUser() {
        return null;
    }

    @Override
    public String deleteUserById(Integer id) {
        return null;
    }
}
