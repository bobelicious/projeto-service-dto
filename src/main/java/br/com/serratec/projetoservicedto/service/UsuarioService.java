package br.com.serratec.projetoservicedto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.projetoservicedto.config.MailConfig;
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
    private MailConfig mailConfig;

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

    public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException {

		if (usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
			throw new EmailException("Email já existe ! Insira outro");
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());

		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioInserirDTO.getSenha()));
		usuarioRepository.save(usuario);
        mailConfig.enviarEmail(usuario.getEmail(), "Cadastro de usuario  confirmado", usuario.toString());
		return new UsuarioDTO(usuario);
    }
    
}
