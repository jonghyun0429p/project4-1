package care.care.service;

import java.util.List;
import java.util.Optional;

import care.care.domain.User;
import care.care.repository.UserRepository;
import jakarta.transaction.Transactional;

@Transactional
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(User user){
        long start = System.currentTimeMillis();
        
        try{
        //중복 불가
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getID();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("Join " + timeMs + "ms");
        }
    }

    public void validateDuplicateUser(User user){
        userRepository.findByName(user.getName())
            .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
        
    }

    /*
     * 전체 회원 조회
     */
    public List<User> findUsers(){
        long start = System.currentTimeMillis();
        
        try{
            return userRepository.findAll();
        } finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findusers " + timeMs + "ms");
        }
    }

    public Optional<User> findOne(Long userId){
        return userRepository.findByid(userId);
    }
}
