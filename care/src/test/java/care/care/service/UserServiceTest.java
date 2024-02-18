package care.care.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import care.care.domain.User;
import care.care.repository.MemoryUserRepository;


public class UserServiceTest {

    UserService userService;
    MemoryUserRepository repository;

    @BeforeEach
    public void BeforeEach() {
        repository = new MemoryUserRepository();
        userService = new UserService(repository);
    }


    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void join() {
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
    public void 중복_예외_확인() {
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
        
        // try{
        //     userService.join(user2);
        // }catch(IllegalStateException e){
        //     assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
        // }
        

        //then
    }

    @Test
    void findUsers() {
        
    }

    @Test
    void findOne() {

    }



}