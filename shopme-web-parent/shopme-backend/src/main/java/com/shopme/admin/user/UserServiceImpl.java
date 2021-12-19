package com.shopme.admin.user;

import com.shopme.common.entity.RoleEntity;
import com.shopme.common.entity.UserEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public List<UserEntity> findAll() {
    List<UserEntity> users = (List<UserEntity>)userRepository.findAll();
    return users;
  }

  @Override
  public List<RoleEntity> findAllRoles() {
    List<RoleEntity> roles = (List<RoleEntity>)roleRepository.findAll();
    return roles;
  }

  @Override
  public void save(UserEntity user) {
    encodePassword(user);
    userRepository.save(user);
  }


  private void encodePassword(UserEntity user){
    String encodePassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodePassword);
  }
}
