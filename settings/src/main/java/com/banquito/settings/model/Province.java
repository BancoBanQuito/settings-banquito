package com.banquito.settings.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Province {
	private String provinceName;
	private List<Canton> cantons;
}
