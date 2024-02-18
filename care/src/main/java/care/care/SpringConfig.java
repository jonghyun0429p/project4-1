package care.care;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import care.care.repository.UserRepository;
import care.care.service.UserService;

@Configuration
public class SpringConfig {
    
    private final UserRepository userRepository;

    public SpringConfig(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository);
    }
}
