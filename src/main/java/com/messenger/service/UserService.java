package com.messenger.service;

import com.messenger.bean.UserDetails;
import com.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository = null;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, timeout = 30)
    public List<UserDetails> getUserDetails() {
        return userRepository.findAll();
    }
}
