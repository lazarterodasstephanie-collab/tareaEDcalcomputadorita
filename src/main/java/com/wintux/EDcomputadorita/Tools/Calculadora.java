package com.wintux.EDcomputadorita.Tools;

import java.util.Stack;

public class Calculadora {

    public static String convertirApostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                output.append(c).append(' ');
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(')
                    output.append(pila.pop()).append(' ');
                pila.pop();
            } else {
                while (!pila.isEmpty() && precedencia(pila.peek()) >= precedencia(c))
                    output.append(pila.pop()).append(' ');
                pila.push(c);
            }
        }

        while (!pila.isEmpty())
            output.append(pila.pop()).append(' ');

        return output.toString().trim();
    }

    public static String resolverExpresionPostfix(String postfix) {
        Stack<Integer> pila = new Stack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                pila.push(Integer.parseInt(token));
            } else {
                int b = pila.pop();
                int a = pila.pop();
                switch (token) {
                    case "+": pila.push(a + b); break;
                    case "-": pila.push(a - b); break;
                    case "*": pila.push(a * b); break;
                    case "/": pila.push(a / b); break;
                }
            }
        }
        return String.valueOf(pila.pop());
    }

    private static int precedencia(char op) {
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return 0;
    }
}