package scgf.api.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import scgf.api.application.dto.funcionario.DtoAtualizarFuncionario;
import scgf.api.application.dto.funcionario.DtoDetalhamentoFuncionario;
import scgf.api.infrastructure.repository.FuncionarioRepository;
import scgf.api.infrastructure.exception.RecursoNaoEncontradoException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AtualizarFuncionarioService {

    private final FuncionarioRepository repositorio;

    @Transactional
    public DtoDetalhamentoFuncionario atualizar(DtoAtualizarFuncionario dados) {
        var funcionario = repositorio.findById(dados.cpf())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário não encontrado!"));

        funcionario.atualizarDados(dados);

        log.info("Atualizando funcionário(a) {} com CPF: {}", funcionario.getNome(), dados.cpf());
        log.info("Dados atualizados: {}", dados);
        log.info("Funcionário atualizado com sucesso!");

        return new DtoDetalhamentoFuncionario(funcionario);
    }
}
