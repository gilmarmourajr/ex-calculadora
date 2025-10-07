package br.fiap.calculadora.controller;

import br.fiap.calculadora.service.CalculadoraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


@Controller
public class CalculadoraController {

    private CalculadoraService service;

    public CalculadoraController(CalculadoraService service){
        this.service = service;
    }

   /* @PostMapping("/calculadora")
    public String calculadora(@RequestParam double num1, @RequestParam double num2, @RequestParam String operador, Model model){

        model.addAttribute("resultado", service.calcular(num1,num2,operador));

        return "index";
    }*/

    @PostMapping("/calculadora")
    public String calculadora(@RequestParam String num1, @RequestParam String num2, @RequestParam String operador, Model model){

        String erro = "";
        BigDecimal resultado = null;

        try {
            resultado = service.calcular(num1, num2, operador);
        } catch (Exception e){
            erro = e.getMessage();
        }
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("resultado", resultado);
        model.addAttribute("erro",erro);
        model.addAttribute("operacao", operador);

        return "index";
    }

}