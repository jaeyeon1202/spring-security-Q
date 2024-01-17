package study.security.config.auth;
//시큐리티 session = Authentication = UserDetails
//시큐리티 session = Authentication(내부 UserDetails)
//시큐리티 session(내부 Authentication(내부 UserDetails))
// 시큐리티 설정에서 loginProcessUrl("/login");
// login 요청이 오면 자동으로 UserDetailsService 타입으로 Ioc되어있는 loadUserByUserName 함수가 실행

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.security.model.User;
import study.security.repository.UserRepository;


@Service
public class PrinsipalDetailsService implements UserDetailsService {

    @Autowired UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        User user = userRepository.findByUsername(username); //일치하는 username이 있는지 확인
        System.out.println("user = " + user);
        if(username == null){
            return null;

        }
        return new PrincipalDetails(user);
    }



}
