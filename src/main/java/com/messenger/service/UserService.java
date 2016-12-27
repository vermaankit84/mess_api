package com.messenger.service;

import com.messenger.bean.UserDetails;
import com.messenger.constants.CacheConstants;
import com.messenger.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository = null;


    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_USER_CACHE_CONSTANTS)
    public List<UserDetails> getUserDetails() {
        return userRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 30)
    @CachePut(value = CacheConstants.STR_USER_CACHE_CONSTANTS, key = "#userDetails.userId")
    public UserDetails createUser(final UserDetails userDetails) {
        return userRepository.saveAndFlush(userDetails);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_USER_CACHE_CONSTANTS, key = "#id")
    public UserDetails getUserDetails(final int id) {
        return userRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_USER_CACHE_CONSTANTS, allEntries = true, key = "#id")
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_USER_CACHE_CONSTANTS, allEntries = true, key = "#userDetails.userId")
    @CachePut(value = CacheConstants.STR_USER_CACHE_CONSTANTS, key = "#userDetails.userId")
    public void updateTemplate(final UserDetails userDetails) {
        final UserDetails u = userRepository.findOne(userDetails.getUserId());
        u.setUserType(userDetails.getUserType() != null ? userDetails.getUserType() : u.getUserType());
        u.setUserName(StringUtils.isNotEmpty(userDetails.getUserName()) ? userDetails.getUserName() : u.getUserName());
        u.setUserPass(StringUtils.isNotEmpty(userDetails.getUserPass()) ? userDetails.getUserPass() : u.getUserPass());
        userRepository.saveAndFlush(u);
    }
}
