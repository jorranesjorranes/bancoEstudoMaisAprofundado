package com.teste.bancoEstudoMaisAprofundado.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.bancoEstudoMaisAprofundado.entities.UsuarioModel;
import com.teste.bancoEstudoMaisAprofundado.services.UsuarioService;

@RestController
@RequestMapping("/api-usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarTodos() {
    	return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> listarPorId(@PathVariable Integer id) {
    	Optional<UsuarioModel> usuarioOptional = usuarioService.findById(id);
    	if (!usuarioOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Usuário para esse id não foi encontrado.");
    	}
    	
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody UsuarioModel usuario) {  	
    	return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario)); 		
    }    	      
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioModel> update(@PathVariable Integer id, @RequestBody UsuarioModel obj) {
		obj = usuarioService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
    
    @DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {
    	Optional<UsuarioModel> usuarioOptional = usuarioService.findById(id);
    	if (!usuarioOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
    	}
    	
		usuarioService.delete(usuarioOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
	}
}
