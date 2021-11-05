package br.com.serratec.projetoservicedto.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.projetoservicedto.dto.UsuarioDTO;
import br.com.serratec.projetoservicedto.dto.UsuarioInserirDTO;
import br.com.serratec.projetoservicedto.exception.EmailException;
import br.com.serratec.projetoservicedto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody UsuarioInserirDTO usuario) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.inserir(usuario);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(usuario);
        } catch (EmailException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listar();
    }

}
