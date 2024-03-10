package com.example.store.service;

import com.example.store.entity.User;
import com.example.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private User user;


    @Transactional
    public User getUser() {
        return userRepository.findById(2L).orElse(null);
    }

    @Transactional
    public void getUserByEmailAndPassword(String email, String password){
        user =  userRepository.getUserByEmailAndPassword(email, password);
    }

//    public User getUser() {
//        return user;
//    }
}


//добавить отзыву область видимостей, исправить мелькие недочеты, исправить покупки?
// но еще не особа поняла каким образом это должно выглядить
// насчет характеристок у меня там есть надоотчеты и средняя оценка он не правильно округляет
//типа покупка 1
//потом перехожу на эту покупку
//