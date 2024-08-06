package com.desafio.neki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.neki.model.Skills;
import com.desafio.neki.repository.SkillsRepository;

@Service
public class SkillsService {
	
	@Autowired
	SkillsRepository repository;
	
	
	public List<Skills> getAllSkills(){
		return repository.findAll();
	}
}
