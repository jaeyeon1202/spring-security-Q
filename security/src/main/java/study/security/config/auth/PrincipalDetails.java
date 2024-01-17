package study.security.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인 진행이 완료되면 시큐리티 session을 만들어준다.(Security ContextHolder)
// 오브젝트타입 => Authentication 객체
// Authentication 안에 User정보가 있어야 됨
// User오브젝트타입 => UserDetails 타입 객체

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.security.model.User;

import java.util.ArrayList;
import java.util.Collection;

// Security Session => Authentication => UserDetails
@Data
public class PrincipalDetails implements UserDetails {

    private User user; //콤포지션

    public PrincipalDetails(User user){
        super();
        this.user = user;
        System.out.println("user33232 = " + user);
    }

    @Override
    public String getPassword() {
        System.out.println("user.getPassword() = " + user.getPassword());
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //이 계정 만료됐니?
        return true; //아니요
    }

    @Override
    public boolean isAccountNonLocked() { //니 계정 잠겼니
        return true; //아니요
    }

    @Override
    public boolean isCredentialsNonExpired() { //니 계정의 비밀번호가 기간이 지났니(1년이 지났니)
        return true; //아니요
    }

    @Override
    public boolean isEnabled() { //니 계정이 활성화 되있니
        // 우리 사이트!! 1년동안 회원이 로그인 안하면!! 휴면계정으로 하기로 함
        // 현재시간 - 로그인시간 => 1년을 초과하면 return false;
        return true; //아니요
    }


    /*
    //해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
        collet.add(()->{ return user.getRole();});
        return collet;
    }
}
