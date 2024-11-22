/**
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author João Victor Crivoi Cesar Souza
 *         Atilio Almeida Costa
 */

import java.awt.*;

public class Calculadora extends Frame {


    public static void main(String[] args) {
        Frame f = new Frame("Calculadora");
        f.setSize(500, 400);
        f.setLayout(new BorderLayout());
        f.addWindowListener(new FechaJanela());
        
        TextField display = new TextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        f.add(display, BorderLayout.NORTH);
       
        String[] conteudoBotao = {"7", "8", "9", "/", "4", "5", "6", "*",
                "1", "2", "3", "-", "0", ".", "=", "+", "C"};
        
        Panel botoesPanel = new Panel();
        botoesPanel.setLayout(new GridLayout(4, 4));

        for (String texto : conteudoBotao) {
            Button botao = new Button(texto);
            botao.addActionListener(new BotaoClicado(display));
            botoesPanel.add(botao);
        }

        f.add(botoesPanel, BorderLayout.CENTER);
        
        f.setVisible(true);
    }

}
