package com.example.demo.service;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.impl.TarefasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Tarefas;
import com.example.demo.repository.TarefasRepository;

@Service
public class TarefasService {

	@Autowired
	private TarefasRepository tarefasRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TarefasDao tarefasDao;
	
	public List<Tarefas> pegarTodasTarefas(){
		return tarefasRepository.findAll();
	}
	
	public Tarefas pegarTarefaPorTitulo(String titulo) throws Exception {
		Tarefas tarefa = tarefasRepository.findByTituloTarefa(titulo);
		return tarefa;
	}

	public List<Tarefas> pegarTarefasPeloIdUsuario(Long idUsuario){
		return tarefasRepository.pegarTarefasPeloIdUsuario(idUsuario);
	}

	public void adicionarTarefa(Tarefas tarefa, Long id) throws Exception {
		if(tarefa.getTituloTarefa() != null && tarefa.getNomeTarefa() != null) {
			tarefasDao.insert(tarefa, id);
		}else {
			throw new Exception("Titulo ou Nome da Tarefa estão");
		}
	}
	
	public ResponseEntity atualizar(Tarefas tarefa, Long idUsuario, Long idTarefa) throws Exception{
		return tarefasRepository.findById(idTarefa).map(
				tf -> {
					tf.setCompletada(tarefa.getCompletada() != null ? tarefa.getCompletada() : tf.getCompletada());
					tf.setDescricaoTarefa(tarefa.getDescricaoTarefa() != null ? tarefa.getDescricaoTarefa() : tf.getDescricaoTarefa());
					tf.setNomeTarefa(tarefa.getNomeTarefa() != null ? tarefa.getNomeTarefa() : tf.getNomeTarefa());
					tf.setTituloTarefa(tarefa.getTituloTarefa() != null ? tarefa.getTituloTarefa() : tf.getTituloTarefa());

					tarefasRepository.update(
							tf.getCompletada(),
							tf.getDescricaoTarefa(),
							tf.getNomeTarefa(),
							tf.getTituloTarefa(),
							idUsuario,
							idTarefa
					);
					return ResponseEntity.ok().body("Tarefa Atualizada com Sucesso!");
				}
		).orElse(ResponseEntity.notFound().build());
	}
	
	public void deletar(Long id) {
		tarefasRepository.deleteById(id);
	}
}
