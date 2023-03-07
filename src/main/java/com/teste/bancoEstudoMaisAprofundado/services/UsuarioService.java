package com.teste.bancoEstudoMaisAprofundado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teste.bancoEstudoMaisAprofundado.entities.UsuarioModel;
import com.teste.bancoEstudoMaisAprofundado.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
public class UsuarioService {

	final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Transactional
	public UsuarioModel save(UsuarioModel usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<UsuarioModel> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Optional<UsuarioModel> findById(Integer id) {
		return usuarioRepository.findById(id);
	}
	
	public UsuarioModel update(Integer id, UsuarioModel obj) {
		UsuarioModel entity = usuarioRepository.getReferenceById(id);
		updateData(entity, obj);
		return usuarioRepository.save(entity);
	}

	private void updateData(UsuarioModel entity, UsuarioModel obj) {
		if (obj.getNome() != null) {
			entity.setNome(obj.getNome());
		}
		if (obj.getEmail() != null) {
			entity.setEmail(obj.getEmail());
		}
		if (obj.getTelefone() != null) {
			entity.setTelefone(obj.getTelefone());
		}
		if (obj.getCpf() != null) {
			entity.setCpf(obj.getCpf());
		}
		if (obj.getSaldo() != null) {
			entity.setSaldo(obj.getSaldo());
		}
	}

	@Transactional
	public void delete(UsuarioModel usuario) {
		usuarioRepository.delete(usuario);
	}
}
