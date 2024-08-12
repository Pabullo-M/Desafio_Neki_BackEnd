package com.desafio.neki.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.neki.dto.AddSkillsDto;
import com.desafio.neki.dto.ResponseSkills;
import com.desafio.neki.model.UsuarioSkill;
import com.desafio.neki.service.UsuarioSkillService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/usuarioSkill")
public class UsuarioSkillsController {

	@Autowired
	UsuarioSkillService service;
	
	@GetMapping("/{usuarioId}")
	@SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<List<Map<String, Object>>> getAllSkills(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.getAllSkills(usuarioId));
    }
	
	 @PostMapping("/add")
	 @SecurityRequirement(name = "bearer-jwt")
	    public ResponseEntity<ResponseSkills> addSkillToUser(@RequestBody AddSkillsDto data) {
		 System.out.println(data);
	        if (data == null) {
	            return ResponseEntity.badRequest().body(null);
	        }
	        return ResponseEntity.ok(service.saveUsuarioSkill(data));
	    }
	 @PutMapping("/{id}")
	 @SecurityRequirement(name = "bearer-jwt")
	    public ResponseEntity<UsuarioSkill> updateLevelUsuarioSkill(@PathVariable Long id, @RequestBody Long novoLevel) {
	        UsuarioSkill updatedUsuarioSkill = service.updateUsuarioSkill(id, novoLevel);
	        return ResponseEntity.ok(updatedUsuarioSkill);
	    }
	 
	 @DeleteMapping("/{id}")
	 @SecurityRequirement(name = "bearer-jwt")
	 public ResponseEntity<String> deleteSkill(@PathVariable Long id) {
		 service.deleteSkill(id);
		 return ResponseEntity.ok("Skill deletada com sucesso");
	 }

}
