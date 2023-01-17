package com.banquito.settings.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Provincia {
	private String nombreProvincia;
	private List<Canton> cantones;
}
