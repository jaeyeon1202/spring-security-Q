package study.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.security.model.User;


//CRUD 함수를 JpaRepository가 들고 있음
//@Repository라는 어노테이션이 없어도 Ioc가능, 이유느느 JpaRepository를 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Integer> {

    //FindBy규칙 -> Username 문법
    // select * from user where username = 1? => 1: 파라미터값
    public User findByUsername(String username); //Jpa 쿼리메서드

    //select * from user where email = ?
    //public User findByEmail();


}
