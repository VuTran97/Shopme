package com.shopme.admin.user;

import com.shopme.common.entity.RoleEntity;
import com.shopme.common.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testCreateUserWithOneRole(){
    RoleEntity roleAdmin = entityManager.find(RoleEntity.class, 1);
    UserEntity vuth = new UserEntity("vuth2397@gmail.com", "vu1234", "Vu", "Tran");
    vuth.addRoles(roleAdmin);
    UserEntity savedUser = userRepository.save(vuth);
    assertThat(savedUser.getId()).isGreaterThan(0);
  }

  @Test
  public void testCreateUserWithTwoRole(){
    UserEntity ravi = new UserEntity("ravi@gmail.com", "ravi1234", "Ravi", "Tran");
    RoleEntity roleAssistant = new RoleEntity(3);
    RoleEntity roleEditor = new RoleEntity(5);
    ravi.addRoles(roleAssistant);
    ravi.addRoles(roleEditor);
    UserEntity savedUser = userRepository.save(ravi);
    assertThat(savedUser.getId()).isGreaterThan(0);
  }

  @Test
  public void testFindAllUsers(){
    Iterable<UserEntity> users = userRepository.findAll();
    for (UserEntity user : users) {
      System.out.println(user);
    }
  }

  @Test
  public void testGetUserById(){
    UserEntity user = userRepository.findById(1).get();
    System.out.println(user);
    assertThat(user).isNotNull();
  }

  @Test
  public void testUpdateUser(){
    UserEntity user = userRepository.findById(1).get();
    user.setEnabled(true);
    user.setEmail("vuth2397@gmail.com");
    userRepository.save(user);
  }

  @Test
  public void testUpdateUserRole(){
    UserEntity user = userRepository.findById(2).get();
    RoleEntity roleAssistant = new RoleEntity(3);
    RoleEntity roleSalesPerson = new RoleEntity(2);
    user.getRoles().remove(roleAssistant);
    user.addRoles(roleSalesPerson);
    userRepository.save(user);
  }

  @Test
  public void testDeleteUser(){
    Integer id = 2;
    userRepository.deleteById(2);
  }

  @Test
  public void testGetUserByEmail(){
    String email = "admin@abc.xyz";
    UserEntity user = userRepository.getUserByEmail(email);

    assertThat(user).isNotNull();
  }

}
