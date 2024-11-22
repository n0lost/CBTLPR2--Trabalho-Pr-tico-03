/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;


/**
 *
 * @author João Victor Crivoi Cesar Souza
 *         Atilio Almeida Costa
 */
public class BotaoClicado implements ActionListener {
    private TextField display;

    public BotaoClicado(TextField display) {
        this.display = display;
    }

    @Override
   public void actionPerformed(ActionEvent e) {
        Button botao = (Button) e.getSource();
        String texto = botao.getLabel();

        switch (texto) {
            case "C":
                display.setText(""); 
                break;
            case "=":
                try {
                    double resultado = calcular(display.getText());
                    display.setText(String.valueOf(resultado));
                } catch (Exception ex) {
                    display.setText("Erro");
                }
                break;
            default:
                display.setText(display.getText() + texto);
                break;
        }
        
          
}
   
   private double calcular(String expressao) throws Exception {
        Stack<Double> numeros = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        StringBuilder numeroAtual = new StringBuilder();

        for (char c : expressao.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                numeroAtual.append(c);
            } else if ("+-*/".indexOf(c) >= 0) {
                numeros.push(Double.parseDouble(numeroAtual.toString()));
                numeroAtual.setLength(0); 
                while (!operadores.isEmpty() && precedencia(c) <= precedencia(operadores.peek())) {
                    processar(numeros, operadores.pop());
                }
                operadores.push(c);
            } else {
                throw new IllegalArgumentException("Caractere inválido: " + c);
            }
        }

        if (numeroAtual.length() > 0) {
            numeros.push(Double.parseDouble(numeroAtual.toString()));
        }

        while (!operadores.isEmpty()) {
            processar(numeros, operadores.pop());
        }

        if (numeros.size() != 1) {
            throw new IllegalArgumentException("Erro na expressão");
        }

        return numeros.pop();
    }

    private int precedencia(char operador) {
        return (operador == '+' || operador == '-') ? 1 : 2; // '+' e '-' têm menor precedência
    }

    private void processar(Stack<Double> numeros, char operador) {
        double b = numeros.pop();
        double a = numeros.pop();
        switch (operador) {
            case '+': numeros.push(a + b); break;
            case '-': numeros.push(a - b); break;
            case '*': numeros.push(a * b); break;
            case '/': 
                if (b == 0) throw new ArithmeticException("Divisão por zero");
                numeros.push(a / b);
                break;
        }
    }
}

