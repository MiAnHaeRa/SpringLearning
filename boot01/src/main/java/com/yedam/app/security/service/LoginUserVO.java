package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserVO implements UserDetails{
	
	private UserVO userVO;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
					// ? extend ~~ 상속관계로 이루어진 클래스만 자료형으로 받는다
					// ? super ~~ 상속관계에 존재하는 클래스만 자료형으로 받는다
					// 참고 사이트 : https://nostress.tistory.com/2
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));	
		
		return auths;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {		//계정 만료 여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		//계정 잠금 여부
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {	//계정 패스워드 만료 여부
		return true;
	}

	@Override
	public boolean isEnabled() {				//계정 사용 여부 
		return true;
	}
	
}
