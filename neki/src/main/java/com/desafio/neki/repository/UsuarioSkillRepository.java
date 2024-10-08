package com.desafio.neki.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafio.neki.model.UsuarioSkill;

public interface UsuarioSkillRepository extends JpaRepository<UsuarioSkill, Long> {

	@Query(value = "SELECT us.id AS usuarioSkillId, us.level AS usuarioSkillLevel, s.id AS skillId, s.nome AS skillNome, s.descricao AS skillDescricao, s.img_url AS imgUrl " +
            "FROM usuario_skill us JOIN skills s ON us.skill_id = s.id WHERE us.usuario_id = :usuarioId", 
    nativeQuery = true)
List<Map<String, Object>> findAllByUsuarioId(@Param("usuarioId") Long usuarioId);


	
}
