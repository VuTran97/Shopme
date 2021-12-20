package com.shopme.admin.user;

import com.shopme.common.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

  @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
  UserEntity getUserByEmail(@Param("email") String email);
}
