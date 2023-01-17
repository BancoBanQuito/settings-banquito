package com.banquito.settings.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parish {
	private String parishName;
	private String zipCode;
}
