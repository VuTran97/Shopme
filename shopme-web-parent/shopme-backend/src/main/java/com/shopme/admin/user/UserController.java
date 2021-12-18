package com.shopme.admin.user;

import com.shopme.common.entity.UserEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @GetMapping("/users")
  public String listAllUser(Model model){
    List<UserEntity> users = userService.findAll();
    model.addAttribute("users", users);
    return "users";
  }
}
