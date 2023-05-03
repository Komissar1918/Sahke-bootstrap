package ru.alexeykedr.springbootbootstrap.util;

import ru.alexeykedr.springbootbootstrap.model.Role;
import ru.alexeykedr.springbootbootstrap.service.UserService;
import ru.alexeykedr.springbootbootstrap.model.User;
import ru.alexeykedr.springbootbootstrap.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);

        User newUser = new User("Alex", "Savelyev", 33, "user@gmail.com", "user",
                "user", userSet);
        User admin = new User("Aigul", "Savelyeva", 30, "admin@gmail.com", "admin",
                "admin", adminSet);
        User newUser2 = new User("Yosyf","Akhmetov",31,"user2@gmail.com","user2",
                "user2",userSet);

        userService.saveUser(newUser);
        userService.saveUser(admin);
        userService.saveUser(newUser2);
    }
}
