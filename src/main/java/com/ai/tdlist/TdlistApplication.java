package com.ai.tdlist;

import com.ai.tdlist.entities.UserEntity;
import com.ai.tdlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TdlistApplication implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

	public static void main(String[] args)  {
		SpringApplication.run(TdlistApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        //Add user with encrypted password for testing the APIs
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        UserEntity userEntity=new UserEntity();
        userEntity.setId(1);
        userEntity.setUsername("user");
        userEntity.setPassword(passwordEncoder.encode("user123"));
        userRepository.save(userEntity);
    }
}
