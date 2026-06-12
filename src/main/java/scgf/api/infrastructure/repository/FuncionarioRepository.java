package scgf.api.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import scgf.api.domain.enums.conta.ContaTipo;
import scgf.api.domain.enums.especialidade.Especialidade;
import scgf.api.domain.model.funcionario.Funcionario;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {

    @Query("""
        select f
        from Funcionario f
        where f.ativo = true
        """)
    Page<Funcionario> findAllByAtivo(Pageable paginacao);

    @Query("""
        select f
        from Funcionario f
        where f.ativo = false
        """)
    Page<Funcionario> findAllByInativo(Pageable paginacao);

    @Query("""
        select f
        from Funcionario f
        where f.email = :email
    """)
    List<Funcionario> findByEmail(String email);

    @Query("""
        select f
        from Funcionario f
        where f.especialidade = :especialidade
    """)
    List<Funcionario> findByEspecialidade(Especialidade especialidade);

    @Query("""
        select f
        from Funcionario f
        where f.conta.tipoConta in :tipoConta
    """)
    List<Funcionario> findByContaTipo(List<ContaTipo> tipoConta);

    boolean existsByContaNumConta(String numConta);

    boolean existsByEmail(String email);
}
