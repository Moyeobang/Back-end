package com.ssafy.member.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.Member;
import com.ssafy.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
 
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
 
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
//        return memberMapper.findByMemberId(memberId)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    	
    // JPA는 아마도 한번에 불러오는게 가능한듯하다. Mybatis는 List를 따로 불러야하는듯해서 분리하여 roles을 찾는다.
      Member member = memberMapper.findByMemberId(memberId);
      List<String> roles = memberMapper.getRoles(memberId);
      member.setRoles(roles);
      System.out.println(member.getRoles());
      return Optional.of(member)
      .map(this::createUserDetails)
      .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }
 
    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }
}