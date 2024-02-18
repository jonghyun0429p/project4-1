package care.care.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import care.care.domain.User;
import care.care.repository.UserRepository;


@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired UserService userService;
    @Autowired UserRepository repository;

    @Test
    // @Commit
    void join() throws Exception {
        //given
        User user = new User();
        user.setName("hello");

        //when
        Long saveId = userService.join(user);

        //then
        User findUser = userService.findOne(saveId).get();
        Assertions.assertThat(user.getName()).isEqualTo(findUser.getName());
    }

    @Test
    public void 중복_예외_확인() throws Exception {
        //given
        User user1 = new User();
        user1.setName("spring");

        User user2 = new User();
        user2.setName("spring");
        
        //when
        userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> userService.join(user2));//예외가 발생해야 한다.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }



}