package com.shopme.admin.user;

import com.shopme.common.entity.UserEntity;
import java.util.List;

public interface UserService {
  
  List<UserEntity> findAll();
}
