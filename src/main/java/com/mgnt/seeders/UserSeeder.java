package com.mgnt.seeders;

import com.mgnt.models.User;
import com.mgnt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {
  private final UserRepository userRepository;

  @Autowired
  public UserSeeder(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    User user1 = new User();
    user1.setName("John Doe");
    user1.setEmail("john@example.com");
    user1.setPassword("password123");
    userRepository.save(user1);

    User user2 = new User();
    user2.setName("Jane Smith");
    user2.setEmail("jane@example.com");
    user2.setPassword("password456");
    userRepository.save(user2);
  }
}
