package com.shopme.admin.user;

import com.shopme.common.entity.RoleEntity;
import com.shopme.common.entity.UserEntity;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

  @GetMapping("/users/new")
  public String newUser(Model model){
    List<RoleEntity> roles = userService.findAllRoles();
    UserEntity user = new UserEntity();
    model.addAttribute("user", user);
    model.addAttribute("roles", roles);
    model.addAttribute("pageTitle", "Create New User");
    return "user_form";
  }

  @PostMapping("/users/save")
  public String saveUser(UserEntity user, RedirectAttributes redirectAttributes){
    userService.save(user);
    redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
    return "redirect:/users";
  }

  @GetMapping("/users/edit/{id}")
  public String updateUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
    try{
      UserEntity userEntity = userService.getUser(id);
      List<RoleEntity> roles = userService.findAllRoles();
      model.addAttribute("user", userEntity);
      model.addAttribute("roles", roles);
      model.addAttribute("pageTitle", "Edit User (ID: " +id+ ")");
      return "user_form";
    }catch(UserNotFoundException e){
      redirectAttributes.addFlashAttribute("message", e.getMessage());
      return "redirect:/users";
    }

  }
}
