package scgf.api.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import scgf.api.infrastructure.repository.FuncionarioRepository;
import scgf.api.infrastructure.exception.RecursoNaoEncontradoException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RemoverFuncionarioService {

    private final FuncionarioRepository repositorio;

    @Transactional
    public void remover(String cpf) {
        var funcionario = repositorio.findById(cpf)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado!"));

        funcionario.excluir();

        log.info("Removendo funcionário(a) {} com CPF: {}", funcionario.getNome(), cpf);
        log.info("Funcionário removido com sucesso!");
    }
}
