package com.example.hanghaegg.search.controller;

import com.example.hanghaegg.search.dto.FinalResponsDto;
import com.example.hanghaegg.search.service.SummonerService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SummonerController {

	private final SummonerService summonerService;

	@GetMapping(value = "/summonerByName")
	public FinalResponsDto callSummonerByName(
		@RequestParam("page") int page,
		@RequestParam("size") int size,
		@RequestParam("summonerName") String summonerName){

		return summonerService.callRiotAPISummonerByName(page,size,summonerName);
	}
}
