package br.com.serratec.projetoservicedto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoservicedto.dto.UsuarioDTO;
import br.com.serratec.projetoservicedto.dto.UsuarioInserirDTO;
import br.com.serratec.projetoservicedto.exception.EmailException;
import br.com.serratec.projetoservicedto.model.Usuario;
import br.com.serratec.projetoservicedto.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();

		for (Usuario usuario : usuarios) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			usuariosDTO.add(usuarioDTO);
		}
		return usuariosDTO;
	}

    public UsuarioDTO inserir (UsuarioInserirDTO user) throws EmailException{
       Usuario usuario = usuarioRepository.findByEmail(user.getEmail());
       if(usuario !=null){
           throw new EmailException("Email já existe");
       }
        usuario .setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        usuario.setSenha((bCryptPasswordEncoder.encode(user.getSenha()))); 
       usuario =  usuarioRepository.save(usuario);
       
       return new UsuarioDTO(usuario);
    }

    
}
