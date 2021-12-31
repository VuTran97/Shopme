package com.shopme.admin.user;

import com.shopme.common.entity.RoleEntity;
import com.shopme.common.entity.UserEntity;
import java.util.List;

public interface UserService {
  
  List<UserEntity> findAll();

  List<RoleEntity> findAllRoles();

   void save(UserEntity user);

   boolean isEmailUnique(String email, Integer id);

   UserEntity getUser(Integer id) throws UserNotFoundException;

   void delete(Integer id) throws UserNotFoundException;
}
