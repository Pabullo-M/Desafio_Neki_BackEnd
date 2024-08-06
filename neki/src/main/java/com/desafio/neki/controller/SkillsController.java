package com.desafio.neki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.neki.model.Skills;
import com.desafio.neki.service.SkillsService;

@RestController
@RequestMapping("/skills")
public class SkillsController {

	@Autowired
	SkillsService service;
	
	
	@GetMapping
	public ResponseEntity<List<Skills>> getAll(){
		return ResponseEntity.ok(service.getAllSkills());
	}
}
