package study.security.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록됨
public class SecurityConfig  {

    //해당 메서드의 리턴되는 오브젝트를 Ioc로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)                              //csrf 꺼두기
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/**").authenticated()          // /user/** 로 오는 요청은 권한 설정    //authenticated:인증만 되면 들어갈 수 있는 주소
                        .requestMatchers("/manager/**").hasRole("MANAGER")    // /manager/** 로 오는 요청은 권한 설정  //권한:MANAGER
                        .requestMatchers("/admin/**").hasRole("ADMIN")        // /admin/** 로 오는 요청은 권한 설정        //권한 : ADMIN
                        .anyRequest().permitAll()                               //그 외 주소들은 접근가능
                )
                .formLogin((formLogin)->{                                       //권한없이 user, manager, admin으로 들어오면 login페이지로 이동할게
                    formLogin.loginPage("/loginForm")                           //login페이지 주소는 이거야
                            //.usernameParameter("username2") -> name으로 파라미터 받을거 아니면 변수명 수정
                            .loginProcessingUrl("/login")                      //login주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행해줌
                            .successForwardUrl("/");                            //로그인 성공시 "/"로 이동
                });

                return http.build();
    }

}
