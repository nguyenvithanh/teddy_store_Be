package com.backend.services;

import java.util.List;

import com.backend.dto.ColorDTO;
import com.backend.model.Color;

public interface ColorService {

	public List<Color> getAllColor();
	public List<ColorDTO> getColorById(String id);
	
	Object getAllColor(int page, int size);

	Object updateColor(String id, String color);

	Object searchColor(String color);
}
