package com.shopme.admin.user;

import com.shopme.common.entity.UserEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<UserEntity> findAll() {
    List<UserEntity> users = (List<UserEntity>)userRepository.findAll();
    return users;
  }
}
