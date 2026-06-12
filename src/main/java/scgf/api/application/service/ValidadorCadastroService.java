package scgf.api.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import scgf.api.application.dto.funcionario.DtoCadastroFuncionario;
import scgf.api.domain.enums.especialidade.Especialidade;
import scgf.api.domain.model.funcionario.Funcionario;
import scgf.api.application.validation.ValidadorCadastroFuncionario;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidadorCadastroService {

    private final List<ValidadorCadastroFuncionario> validadores;

    public void validarCadastro(DtoCadastroFuncionario dados) {
        validadores.forEach(validador -> validador.validar(dados));
    }

    public void ajustarSalarioSeDesenvolvedor(Funcionario funcionario) {
        if (funcionario.getEspecialidade() == Especialidade.DESENVOLVEDOR) {
            var salarioAtual = funcionario.getConta().getSalario();
            var aumento = funcionario.getConta().getSalario() * 0.20;
            funcionario.getConta().setSalario(funcionario.getConta().getSalario() + aumento);
            log.info("Salário do desenvolvedor {} aumentado em 20%: salário anterior = {}, novo salário = {}", funcionario.getNome(), salarioAtual, funcionario.getConta().getSalario());
        }
    }
}
