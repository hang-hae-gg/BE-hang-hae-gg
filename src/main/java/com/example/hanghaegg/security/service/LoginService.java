package com.example.hanghaegg.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hanghaegg.domain.member.entity.Member;
import com.example.hanghaegg.domain.member.repository.MemberRepository;
import com.example.hanghaegg.exception.MemberErrorCode;
import com.example.hanghaegg.exception.RestApiException;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new RestApiException(MemberErrorCode.MEMBER_NOT_FOUND));

		log.info("일반 로그인 서비스 로직입니다.");
		return org.springframework.security.core.userdetails.User.builder()
			.username(member.getEmail())
			.password(member.getPassword())
			.roles(member.getRole().name())
			.build();
	}
}

