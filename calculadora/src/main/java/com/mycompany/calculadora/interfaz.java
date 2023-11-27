

package com.mycompany.calculadora;
import javax.swing.*;
import java.awt.*;


public class interfaz extends JFrame {
    char[] numeros = {'7','8','9','4','5','6','1','2','3','0'};
    String[] operadores = {"%","^","√","/","x","-","+"};
    String[] especiales = {"C","CE","Borrar"};
    JTextField texto;
    public interfaz(){
    
    texto=new JTextField();    
    
    GridBagLayout grid= new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    this.setLayout(grid);
    
    int x=0;
    int y=0;
    
    gbc.fill = GridBagConstraints.HORIZONTAL;  
    for(int i=0;i<numeros.length;i++){
        if(x>2){
            y++;
            x=0;
        }
        
        gbc.gridx=x;
        gbc.gridy=y;
        JButton button = new JButton(""+numeros[i]);
        this.add(button,gbc);
        x++;
    }
    
    setSize(500, 500);  
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);  
    }
}
