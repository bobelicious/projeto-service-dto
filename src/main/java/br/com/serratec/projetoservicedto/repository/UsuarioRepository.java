package br.com.serratec.projetoservicedto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.projetoservicedto.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
   Usuario findByEmail(String email);
}
