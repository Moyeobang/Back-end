package com.ssafy.member.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

/**
 * SpringSecurity 유저 관련 권한 설정을 위한 클래스.
 * 최소한 username(PK), password, List<String>roles를 가져야한다.
 * 나는 편의상 재활용을 위해 memberName 필드를 끼워주었다.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {
 
    @Id
    private String memberId;
    private String memberName;
    private String password;
 
    @Builder.Default
    private List<String> roles = new ArrayList<>();
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
 
    @Override
    public String getUsername() {
        return memberId;
    }
 
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public void setRoles(List<String> roles) {
    	this.roles = roles;
    }

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
    
	public String getMemberName() {
		return this.memberName;
	}
	
}