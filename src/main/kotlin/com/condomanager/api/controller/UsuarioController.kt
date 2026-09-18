package com.condomanager.api.controller

import com.condomanager.api.entity.Usuario
import com.condomanager.api.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuarios")
class UsuarioController (val service: UsuarioService) {

    @PostMapping
    fun cadastrar(@RequestBody usuario : Usuario) : ResponseEntity<Usuario> {
        return ResponseEntity.ok(service.cadastrar(usuario))
    }

    @GetMapping
    fun listar() : ResponseEntity<List<Usuario>> {
        return ResponseEntity.ok(service.listar())
    }

    @GetMapping("/{id}")
    fun buscar(@PathVariable id : Long) : ResponseEntity<Usuario> {
        return ResponseEntity.ok(service.buscar(id))
    }

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id : Long,
        @RequestBody usuario: Usuario
    ) : ResponseEntity<Usuario> {
        val atualizado = service.atualizar(id, usuario) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(atualizado)
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id : Long) : ResponseEntity<Void> {
        val excluido = service.excluir(id)
        if (!excluido) {
            return ResponseEntity.notFound().build()
        } else {
            return ResponseEntity.ok().build()
        }
    }

}