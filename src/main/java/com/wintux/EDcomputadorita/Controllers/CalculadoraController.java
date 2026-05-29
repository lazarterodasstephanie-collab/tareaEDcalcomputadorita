package com.wintux.EDcomputadorita.Controllers;

import com.wintux.EDcomputadorita.Tools.Calculadora;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

    @PostMapping("/expresion")
    public String calcularInfix(@RequestBody String infix){
        String postfix = Calculadora.convertirApostfix(infix);
        String resultado = Calculadora.resolverExpresionPostfix(postfix);
        return resultado;
    }
}