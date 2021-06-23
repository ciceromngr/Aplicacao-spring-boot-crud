package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Tarefas;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE tarefas_tb \n" +
			"SET completada =:completada," +
			"descricao_tarefa =:descricao, " +
			"nome_tarefa =:nomeTarefa, " +
			"titulo_tarefa =:tituloTarefa, " +
			"id_usuario =:idUsuario \n" +
			"WHERE id =:id_tarefa", nativeQuery = true)
	void update(@Param("completada") Boolean completada,
					   @Param("descricao") String descricao,
					   @Param("nomeTarefa") String nomeTarefa,
					   @Param("tituloTarefa") String tituloTarefa,
					   @Param("idUsuario") Long id,
					   @Param("id_tarefa") Long idTarefa
						);

	Tarefas findByTituloTarefa(String tituloTarefa);

	@Transactional
	@Query(value = "select * from tarefas_tb where id_usuario =:idUsuario", nativeQuery = true)
	List<Tarefas> pegarTarefasPeloIdUsuario(@Param("idUsuario") Long id);
}
