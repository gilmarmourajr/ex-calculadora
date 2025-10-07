package br.fiap.calculadora.service;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculadoraService {

   /* public double calcular(double num1, double num2, String operador){

        if(operador.equalsIgnoreCase("+")){
            return num1 + num2;
        } else if (operador.equalsIgnoreCase("-")) {
            return num1 - num2;
        } else if (operador.equalsIgnoreCase("*")) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }

    }*/

    public BigDecimal calcular(String num1, String num2, String operador){

        BigDecimal n1 = toBigDecimal(num1);
        BigDecimal n2 = toBigDecimal(num2);

        return switch (operador){
            case "+" -> n1.add(n2);
            case "-" -> n1.subtract(n2);
            case "*" -> n1.multiply(n2);
            case "/" -> {
                if(n2.compareTo(BigDecimal.ZERO) == 0){
                    throw new IllegalArgumentException("O segundo número deve ser diferente de zero '0'");
                }

                yield n1.divide(n2,6);
            }
            default -> throw  new IllegalArgumentException("Operação inválida");
        };
    }

    public BigDecimal toBigDecimal(String valor){
        String aux = valor.trim().replace("," ,".");
        try {
            return new BigDecimal(aux);
        } catch (IllegalArgumentException e){
            throw e;
        }
    }

}