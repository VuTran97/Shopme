package com.shopme.admin.user;

import com.shopme.common.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
}
