package com.banquito.settings.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.banquito.settings.model.Province;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRQ implements Serializable {

	private List<Province> provinces;

}
