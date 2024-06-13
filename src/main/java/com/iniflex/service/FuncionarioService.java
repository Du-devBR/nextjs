package com.iniflex.service;

import com.iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class FuncionarioService {

    /**
     * Cada funcionario é adicionado a lista
     *
     * @return Array de ListaFuncionarios
     */
    public List<Funcionario> funcionarios(){

        List<Funcionario> listaFuncionarios = new ArrayList<>();

        listaFuncionarios.add(new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal(2009.44), "Operador"));
        listaFuncionarios.add(new Funcionario("João", LocalDate.of(1990, Month.MAY, 12), new BigDecimal(2284.38), "Operador"));
        listaFuncionarios.add(new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 02), new BigDecimal(9836.14), "Coordenador"));
        listaFuncionarios.add(new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal(1919.88), "Diretor"));
        listaFuncionarios.add(new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 05), new BigDecimal(2234.68), "Recepicionista"));
        listaFuncionarios.add(new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal(1582.72), "Operador"));
        listaFuncionarios.add(new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31), new BigDecimal(4071.84), "Contador"));
        listaFuncionarios.add(new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8), new BigDecimal(3017.45), "Gerente"));
        listaFuncionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, Month.MAY, 24), new BigDecimal(1606.85), "Eletricista"));
        listaFuncionarios.add(new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2), new BigDecimal(2799.93), "Gerente"));

        return listaFuncionarios;
    }

    /**
     * Mostra todos os funcionarios da lista
     * @param funcionarios
     */

    public void mostrarTodosFuncionarios(List<Funcionario> funcionarios){
        for(Funcionario funcionario : funcionarios){
            System.out.println(funcionario);
        }
    }

    /**
     * Remove um nome da lista, nome é passado pelo parametro
     * @param funcionarios
     * @param nome
     */

    public void removerFuncionarioLista(List<Funcionario> funcionarios, String nome){
        for(int i = 0; i < funcionarios.size(); i++){
            if(funcionarios.get(i).getNome().equals(nome)){
                funcionarios.remove(i);
                i--;
            }
        }
    }

    /**
     * Gera um aumento percentual a todos da lista, valor percentual é passado pelo parametro
     * @param funcionarios
     * @param aumento
     */

    public void gerarAumentoSalario(List<Funcionario> funcionarios, BigDecimal aumento){
        for (Funcionario funcionario : funcionarios){
            BigDecimal salarioComAumento = funcionario.getSalario().multiply(BigDecimal.ONE.add(aumento));
            funcionario.setSalario(salarioComAumento);
        }
    }

    /**
     * Função MAP para agrupar todos os funcionarios com a mesma funcao.
     * @param funcionarios
     * @return
     */

    public Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> funcionarios){
        Map<String, List<Funcionario>> map = new HashMap<>();
        for(Funcionario funcionario: funcionarios){
            String funcaoFuncionario = funcionario.getFuncao();
            if(!map.containsKey(funcaoFuncionario)){
                map.put(funcaoFuncionario, new ArrayList<>());
            }
            map.get(funcaoFuncionario).add(funcionario);
        }
        return map;
    }

    /**
     * Mostra em tela qual a funcvao e quais funcionarios estao atreladas a essa funcao.
     * @param funcionarios
     */

    public void mostrarFuncionarioPorFuncao(List<Funcionario> funcionarios){
        Map<String, List<Funcionario>> map = agruparFuncionariosPorFuncao(funcionarios);
        for(Map.Entry<String, List<Funcionario>> entry : map.entrySet()){
            System.out.println("Função " + entry.getKey());
            mostrarTodosFuncionarios(entry.getValue());
        }
    }

    /**
     * Função para buscar os funcionarios que fazem aniversario no mes passado pelo parametro.
     * @param funcionarios
     * @param meses
     */

    public void mostrarFuncionariosAniversario(List<Funcionario> funcionarios, int... meses){
        List<Funcionario> funcionariosFiltrados = new ArrayList<>();
        for (Funcionario funcionario : funcionarios){
           int mesNascimento = funcionario.getDataNacimento().getMonthValue();
           for(int mes : meses){
              if(mesNascimento == mes){
                  funcionariosFiltrados.add(funcionario);
              }
           }
        }
        mostrarTodosFuncionarios(funcionariosFiltrados);
    }

    /**
     * Função para buscar qual o funcionario com maior idade entre todos os funcionarios
     * @param funcionarios
     */

    public void mostrarFuncionarioComIdadeMaior(List<Funcionario> funcionarios){

        Funcionario funcionarioComMaiorIdade = null;
        for(Funcionario funcionario : funcionarios){

            if(funcionarioComMaiorIdade == null || funcionario.getDataNacimento().compareTo(funcionarioComMaiorIdade.getDataNacimento()) < 0){
                funcionarioComMaiorIdade = funcionario;
            }
        }
        if(funcionarioComMaiorIdade != null){
            int idade = funcionarioComMaiorIdade.getDataNacimento().until(LocalDate.now()).getYears();
            System.out.println("Nome: " + funcionarioComMaiorIdade.getNome() + ", Idade: " + idade + " anos");
        }
    }

    /**
     * Função para listar todos os funcionarios em ordem alfabetica, é criado um novo array ordenado. Utilizado metodo sort para orgaznizar o array.
     * @param funcionarios
     */

    public void mostrarFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios){
        List<Funcionario> listaFuncionariosOrdenada = new ArrayList<>(funcionarios);
        listaFuncionariosOrdenada.sort(Comparator.comparing(Funcionario::getNome));
        for(Funcionario funcionario : listaFuncionariosOrdenada){
            System.out.println(funcionario);
        }
    }

    /**
     * Função para calcular o valor total dos salarios da empresa
     * @param funcionarios
     */

    public void calcularSalarioTotal(List<Funcionario> funcionarios){
        DecimalFormat df = new DecimalFormat("#,##0.00");
        BigDecimal total = BigDecimal.ZERO;
        for(Funcionario funcionario : funcionarios){
            total = total.add(funcionario.getSalario());
        }
        System.out.println("Total: " +  df.format(total));
    }

    /**
     * Função para calcular qual o total de salarios minimos por funcionario. Valor do salario minimo é passado por parametro.
     * @param funcionarios
     * @param salarioMinimo
     */

    public void calcularSalarioMinimo(List<Funcionario> funcionarios, BigDecimal salarioMinimo){

        for(Funcionario funcionario : funcionarios){
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println("nome: " + funcionario.getNome() + ": " + salariosMinimos + " salarios minimos.");
        }
    }
}
