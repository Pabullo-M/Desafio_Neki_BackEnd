package com.desafio.neki.dto;

import com.desafio.neki.model.Skills;
import com.desafio.neki.model.UsuarioSkill;

public record ResponseSkills(
		Skills skill,
		Long level
		) {
 
    public static ResponseSkills toDto(UsuarioSkill usuarioSkill) {
        return new ResponseSkills(usuarioSkill.getSkill(), usuarioSkill.getLevel());
    }
}
