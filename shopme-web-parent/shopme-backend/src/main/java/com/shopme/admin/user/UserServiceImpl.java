package com.shopme.admin.user;

import com.shopme.common.entity.RoleEntity;
import com.shopme.common.entity.UserEntity;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.criteria.CriteriaBuilder.In;
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
    boolean isUpdating = (user.getId() != null);
    if(isUpdating){
      UserEntity existUser = userRepository.findById(user.getId()).get();
      if(user.getPassword().isEmpty()){
        user.setPassword(existUser.getPassword());
      }else{
        encodePassword(user);
      }
    }else{
      encodePassword(user);
    }
    userRepository.save(user);
  }

  @Override
  public boolean isEmailUnique(String email, Integer id) {
    UserEntity userByEmail = userRepository.getUserByEmail(email);
    boolean isCreatingNew = (id == null);
    if (userByEmail == null){
      return true;
    }

    if(isCreatingNew) {
      if (userByEmail != null) {
        return false;
      }
    }else{
      if(!userByEmail.getId().equals(id)){
        return false;
      }
    }

    return true;
  }

  @Override
  public UserEntity getUser(Integer id) throws UserNotFoundException {
    try{
      return userRepository.findById(id).get();
    }catch (NoSuchElementException e){
      throw new UserNotFoundException("Could not find any user with ID "+id);
    }
  }


  private void encodePassword(UserEntity user){
    String encodePassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodePassword);
  }
}
