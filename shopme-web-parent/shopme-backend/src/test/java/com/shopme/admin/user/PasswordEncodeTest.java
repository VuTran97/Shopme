package com.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncodeTest {

  @Test
  public void testEncodePassword(){
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String rawPass = "vu2020";
    String encodedPass = bCryptPasswordEncoder.encode(rawPass);
    System.out.println(encodedPass);

    boolean isMtached = bCryptPasswordEncoder.matches(rawPass, encodedPass);
    assertThat(isMtached).isTrue();
  }

}
