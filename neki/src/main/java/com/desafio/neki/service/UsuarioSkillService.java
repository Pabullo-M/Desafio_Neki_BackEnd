package com.desafio.neki.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.neki.dto.AddSkillsDto;
import com.desafio.neki.dto.ResponseSkills;
import com.desafio.neki.model.Skills;
import com.desafio.neki.model.Usuario;
import com.desafio.neki.model.UsuarioSkill;
import com.desafio.neki.repository.SkillsRepository;
import com.desafio.neki.repository.UsuarioRepository;
import com.desafio.neki.repository.UsuarioSkillRepository;

@Service
public class UsuarioSkillService {
	
	
	@Autowired
	UsuarioSkillRepository repository;
	
	@Autowired
	UsuarioRepository repositoryUsuario;
	
	@Autowired
	SkillsRepository repositorySkills;

	public List<Map<String, Object>> getAllSkills(Long id) {
		return repository.findAllByUsuarioId(id);
	}

    public ResponseSkills saveUsuarioSkill(AddSkillsDto data) {
    	Usuario usuario = repositoryUsuario.findById(data.usuarioId()).orElseThrow(()-> new RuntimeException("Usuario nao encontrada"));
    	Skills skill =  repositorySkills.findById(data.skillId()).orElseThrow(()-> new RuntimeException("Skill nao encontraram"));
    	
    	UsuarioSkill usuarioSkill = new UsuarioSkill(
    			usuario,
    			skill,
    			data.level()
    			
    			);
    	
        return ResponseSkills.toDto(repository.save(usuarioSkill));
    }

    public UsuarioSkill updateUsuarioSkill(Long id, Long novoLevel) {
        UsuarioSkill usuarioSkill = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("nao foi encontrada a skill " + id));
        
        usuarioSkill.setSkill(usuarioSkill.getSkill());
        usuarioSkill.setUsuario(usuarioSkill.getUsuario());
        usuarioSkill.setLevel(novoLevel);

        return repository.save(usuarioSkill);
    }

	public void deleteSkill(Long id) {
		repository.deleteById(id);
	}
}
