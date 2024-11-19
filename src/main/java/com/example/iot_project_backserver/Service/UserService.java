package com.example.iot_project_backserver.Service;

import com.example.iot_project_backserver.Entity.User.app_user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    List<app_user> getAllUsers();                    // 모든 유저 가져오기

    Optional<app_user> getUserById(String id);       // ID로 유저 가져오기

    app_user createUser(app_user newUser);           // 새로운 유저 생성

    Optional<app_user> updateUser(String id, app_user updatedUser);  // 유저 정보 업데이트

    boolean deleteUser(String id);                // 유저 삭제

    app_user saveUser(app_user newUser);                 // 유저 저장 메소드 추가

    boolean existsByUserid(String userid);

    List<Map<String, String>> getUserInfoByName(String name);

    Optional<Map<String, String>> getUserInfoByUserid(String userid);

    Optional<app_user> findUserByUserid(String userid);
}
