package com.shopme.admin.user;

import com.shopme.common.entity.RoleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;
    
    @Test
    public void createRoleTest(){
        RoleEntity admin = new RoleEntity("Admin", "manage everything");
        RoleEntity salePerson = new RoleEntity("Salesperson","manage product price, customer, "
                                + "shipping, orders and sales report");
        RoleEntity assistant = new RoleEntity("Assistant","manage questions and reviews");
        RoleEntity editor = new RoleEntity("Editor","manage categories, brands, "
                + "products, articles and menus");
        RoleEntity shipper = new RoleEntity("Shipper","view products, view orders "
                + "and update status");
        roleRepository.saveAll(List.of(admin, salePerson, assistant, shipper, editor));

    }
}
