package com.payriff.dictionary_ms.initializer;

import com.payriff.dictionary_ms.entity.UserCredential;
import com.payriff.dictionary_ms.repository.UserCredentialRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBUsersInitializer {
    @Bean
    public ApplicationRunner init(UserCredentialRepository userRepo, PasswordEncoder encoder) {
        return (args) -> {
            UserCredential adminUser = new UserCredential("admin",
                    encoder.encode("admin"), "admin_user@test.com");
            adminUser.addRole("ROLE_MANAGER");
            adminUser.addRole("ROLE_ADMIN");
            userRepo.save(adminUser);

            UserCredential managerUser = new UserCredential("manager",
                    encoder.encode("manager"), "manager_user@test.com");
            managerUser.addRole("ROLE_MANAGER");
            userRepo.save(managerUser);

            UserCredential testUser = new UserCredential("testUser",
                    encoder.encode("12345"), "test_user@test.com");
            userRepo.save(testUser);
        };
    }
}
