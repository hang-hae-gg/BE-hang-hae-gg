package com.example.hanghaegg.domain.chat.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hanghaegg.domain.matching.entity.Board;
import com.example.hanghaegg.domain.matching.repository.BoardRepository;
import com.example.hanghaegg.domain.member.entity.Member;
import com.example.hanghaegg.domain.member.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
	private final MemberRepository memberRepository;
	private final BoardRepository boardRepository;
	// 아직 로그인 된 사용자 정보를 어떻게 가져와야 할지 모르는 상황 -> 건님
	// board의 작성자가 누구인지 확인이 안되는 상황 -> 은서님

	public String master = null; // 고민해봐야할 문제
	public String guest = null; // 고민해봐야할 문제

	@GetMapping("/{boardId}/{myId}")
	public void getChat(HttpServletRequest request, @PathVariable Long myId, @PathVariable Long boardId) {
		Member guestMember = memberRepository.findById(myId).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 멤버입니다.")
		);
		Long masterId = 2L;

		Board board = boardRepository.findById(boardId).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
		);
		Member masterMember = memberRepository.findById(masterId).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 멤버입니다.")
		);

		HttpSession session = request.getSession();
		System.out.println("chat param id 값 출력 : "  + myId);

		if (myId.equals(guestMember.getId())) {
			String name = guestMember.getNickname() + session.toString().substring(session.toString().indexOf("@"));
			guest = name;
			session.setAttribute("sessionId", name);
		} else if(myId.equals(board.getMember().getId())) {
			//board의 작성자를 알게되면 board에서 작성자의 아이디를 넣으면 됨
			String name = board.getMember().getNickname();
			master = name;
			session.setAttribute("sessionId", name);
		}

		log.info("@ChatController, getChat()");
	}

	@GetMapping("/master")
	public String enterChatAsMaster(HttpServletRequest request) {

		log.info("@ChatController, getChat()");
		return "/chat";
	}
}
