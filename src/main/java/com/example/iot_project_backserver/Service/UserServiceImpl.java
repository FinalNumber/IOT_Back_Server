package com.example.iot_project_backserver.Service;

import com.example.iot_project_backserver.Entity.User.app_user;
import com.example.iot_project_backserver.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<app_user> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<app_user> getUserById(String id) {
        return userRepository.findById(id);
    }
    @Override
    public Optional<app_user> getRefresh_token(String Token) {
        return userRepository.findByRefreshToken(Token);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public app_user createUser(app_user newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); // 비밀번호 암호화
        return userRepository.save(newUser);
    }

    @Override
    public Optional<app_user> updateUser(String id, app_user updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    existingUser.setName(updatedUser.getName());
                    existingUser.setBirth(updatedUser.getBirth());
                    existingUser.setPhone_num(updatedUser.getPhone_num());
                    existingUser.setDivision(updatedUser.getDivision());
                    return userRepository.save(existingUser);
                });
    }

    @Override
    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public app_user saveUser(app_user newUser) {
        return userRepository.save(newUser);  // DB에 유저 저장
    }

    @Override
    public boolean existsByUserid(String userid) {
        return userRepository.existsByUserid(userid);  // ID 중복 체크
    }

    @Override
    public List<Map<String, String>> getUserInfoByName(String name) {
        return userRepository.findByName(name)
                .stream()
                .filter(user -> "Patient".equals(user.getDivision()))  // division이 "Patient"인 데이터만 필터링
                .map(user -> Map.of("userid", user.getUserid(), "name", user.getName()))
                .toList();
    }

    @Override
    public Optional<Map<String, String>> getUserInfoByUserid(String userid) {
        return userRepository.findById(userid)
                .filter(user -> "Patient".equals(user.getDivision()))  // division이 "Patient"인 경우에만 반환
                .map(user -> Map.of("userid", user.getUserid(), "name", user.getName()));
    }
    @Override
    public Optional<app_user> findUserByUserid(String userid) {
        return Optional.ofNullable(userRepository.findByUserid(userid).orElse(null));
    }
}
