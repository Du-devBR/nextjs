package com.iniflex;
import com.iniflex.model.Funcionario;
import com.iniflex.service.FuncionarioService;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        FuncionarioService funcionarioService = new FuncionarioService();
        List<Funcionario> funcionarios = funcionarioService.funcionarios();

        System.out.println("\nQuadro de funcionarios");
        funcionarioService.mostrarTodosFuncionarios(funcionarios);

        System.out.println("\nRemovendo João da lista");
        funcionarioService.removerFuncionarioLista(funcionarios, "João");

        System.out.println("\nQuadro de funcionarios");
        funcionarioService.mostrarTodosFuncionarios(funcionarios);

        System.out.println("\nQuadro de funcionarios atualizado salario");
        funcionarioService.gerarAumentoSalario(funcionarios, new BigDecimal("0.1"));
        funcionarioService.mostrarTodosFuncionarios(funcionarios);

        System.out.println("\nQuadro de funcionarios por função");
        funcionarioService.mostrarFuncionarioPorFuncao(funcionarios);

        System.out.println("\nQuadro de funcionarios atualizado por aniversario no mes 10 e 12");
        funcionarioService.mostrarFuncionariosAniversario(funcionarios, 10, 12);

        System.out.println("\nFuncionario com a maior idade de nascimento");
        funcionarioService.mostrarFuncionarioComIdadeMaior(funcionarios);

        System.out.println("\nLista de funcionarios organizado por ordem alfabetica");
        funcionarioService.mostrarFuncionariosOrdemAlfabetica(funcionarios);

        System.out.println("\nTotal de salarios da empresa");
        funcionarioService.calcularSalarioTotal(funcionarios);

        System.out.println("\nTotal de salarios de salarios minimos que cada funcionario ganha");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarioService.calcularSalarioMinimo(funcionarios, salarioMinimo);
    }


}