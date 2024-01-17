package study.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.security.model.User;
import study.security.repository.UserRepository;

@Controller
public class IndexController {

    @Autowired UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    public IndexController(BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

    @GetMapping({"", "/"})
    public String index(){
        //머스테치 기본폴더 src/main/resources/
        //뷰지볼버 설정 : templates(prefix), .mustache(suffix)
        return "index";
    }
    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    //스프링 시큐리티가 해당주소를 낚아채버림 -> SecurityConfig 파일 생성 후 작동안함.
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(User user){
        System.out.println(user);
        user.setRole("ROLE_USER");

        String pw = user.getPassword();
        System.out.println("pw = " + pw);
        String encPw = bCryptPasswordEncoder.encode(pw);  //비밀번호 암호화 ********
        System.out.println("encPw = " + encPw);

        user.setPassword(encPw);

        userRepository.save(user); //회원가입 잘됨.비밀번호 1234 => 시큐리티로 로그인을 할 수 없음. 이유는 패스워드 암호화가 안되었기 때문
        return "redirect:/loginForm";
    }
}
