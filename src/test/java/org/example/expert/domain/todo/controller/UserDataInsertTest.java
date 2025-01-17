//package org.example.expert.domain.todo.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//import org.example.expert.domain.user.entity.User;
//import org.example.expert.domain.user.enums.UserRole;
//import org.example.expert.domain.user.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Commit;
//
//@SpringBootTest
//public class UserDataInsertTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Commit
//    @Test
//    public void insertMillionUsers() {
//        int totalUsers = 1_000_000;
//        int batchSize = 10_000;
//
//        List<User> users = new ArrayList<>();
//        for (int i = 1; i <= totalUsers; i++) {
//            String randomEmail = "users" + i + "@example.com";
//            String randomNickname = "nicks" + UUID.randomUUID().toString().substring(0, 8);
//            User user = new User(randomEmail, "password", UserRole.USER, randomNickname);
//            users.add(user);
//
//            if (i % batchSize == 0) {
//                userRepository.saveAll(users);
//                users.clear();
//                System.out.println("Inserted " + i);
//            }
//        }
//
//        // 남은 데이터 저장
//        if (!users.isEmpty()) {
//            userRepository.saveAll(users);
//        }
//
//        System.out.println("100만건 추가 완료");
//    }
//}
//
