package com.banquito.settings.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Canton {
	private String cantonName;
	private List<Parish> parishes;
}
