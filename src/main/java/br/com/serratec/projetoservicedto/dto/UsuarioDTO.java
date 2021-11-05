package br.com.serratec.projetoservicedto.dto;

import br.com.serratec.projetoservicedto.model.Usuario;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    public UsuarioDTO(){

    }

    public UsuarioDTO(String nome, String email, Long id) {
        this.nome = nome;
        this.email = email;
        this.id = id;
    }


    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();

	}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
