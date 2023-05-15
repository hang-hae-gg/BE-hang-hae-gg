package com.example.hanghaegg.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchResponseDto {

	private boolean winresult;
	private String championName;
	private int kills;
	private int	deaths;
	private	int assists;
}