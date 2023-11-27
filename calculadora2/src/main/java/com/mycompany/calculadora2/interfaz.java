/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadora2;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author diego
 */
public class interfaz  {
    
    
    //Texto para los botones
    String[] numeros = {"7","8","9","4","5","6","1","2","3","0"};
    String[] otros = {".","+/-","="};
    char[] operadores = {'/','*','-','+'};
    String[] especiales = {"C","<-","%"};
    
    //atributos para la interfaz
    JTextField texto;
    JFrame frame;
    JButton boton;
    String buttonText;
    

    
  
    public interfaz(){
      
    
    frame = new JFrame("Calculadora");
    texto =new JTextField();  
    Stack<Integer> numerosStack = new Stack<>();
    Stack<String> operadoresStack = new Stack<>();
    
    GridBagLayout grid= new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    frame.setLayout(grid);
    
    int x=0;
    int y=1;
    
    gbc.insets = new Insets(5,5,5,5);
    
    gbc.fill = GridBagConstraints.HORIZONTAL;  
    // setear botones especiales
    for (int i=0;i<especiales.length;i++){
        boton = new JButton (""+especiales[i]);
        gbc.gridx=x;
        gbc.gridy=y;
        //gbc.gridwidth=2;
        boton.setBackground(new Color (128,131,237));
        frame.add(boton,gbc);
        x++;
        
        boton.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed (ActionEvent e){
              JButton o = (JButton)e.getSource();
              buttonText = o.getText(); 
              switch (buttonText){
                  case "C": 
                      texto.setText("");
                  break;
                  case "<-": 
                      if(!(texto.getText().isEmpty())){
                            StringBuffer newString=new StringBuffer(texto.getText());
                            newString.deleteCharAt(newString.length()-1);
                             
                            texto.setText(newString.toString());
                                //se pasa el texto del textfield a un stringbuffer y se borra el ultimo caracter
                      }
                  break;
                  case "%":
                      if(!(texto.getText().isEmpty())){
                          texto.setText(texto.getText()+buttonText); 
                      }     
                  break;
              }
          }
      }); 
    }
       
    // setear numeros
    x=0;
    y=2;
    for(int i=0;i<numeros.length;i++){
        gbc.fill = GridBagConstraints.HORIZONTAL;  
        if(x>2){
            x=0;
            y++;
        }
        gbc.gridx=x;
        gbc.gridy=y;
        boton = new JButton(""+numeros[i]);
            boton.setBackground(new Color (100,189,252));

        frame.add(boton,gbc);
        x++;
        
        boton.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed (ActionEvent e){
              JButton o = (JButton)e.getSource();
              buttonText = o.getText();
              // primero hay que verificar si el primer numero es 0 o no, no existen numeros que empiecen con 0
              if(texto.getText().isEmpty() && buttonText.equals("0")){
                  
                  texto.setText("");
                  
              }else{
                  
                texto.setText(texto.getText()+buttonText);
                
                
              }
                           
          }
      }); 
    }
    
    for(int i=0;i<otros.length;i++){
        gbc.gridx=x;
        gbc.gridy=y;
        boton = new JButton(""+otros [i]);
        boton.setBackground(new Color (90,131,237));
        frame.add(boton,gbc);  
        x++;
        boton.addActionListener(new ActionListener(){
       @Override
       public void actionPerformed(ActionEvent e){
           JButton o = (JButton)e.getSource();
           buttonText = o.getText();
           
           switch(buttonText){
               
               case "=": 
            
                     if(checkString(texto.getText(),operadores)){
                        String operacion = texto.getText();
                        
                        operacion = infixToPostfix(operacion);
                        
                        operacion = postFixCalculator(operacion);
                        
                        texto.setText(operacion);
                      
                     }  
               break;
               
               case ".":
                   if(checkString(texto.getText(),operadores)){
                       texto.setText(texto.getText()+buttonText);
                   }
                   
               break;
           }
           
           
       }
    });
    }
    

    //setear operadores
    x=3;
    y=1;
    
    for(int i=0;i<operadores.length;i++){
       gbc.gridx=x;
       gbc.gridy=y;
       boton= new JButton(""+operadores[i]);
       boton.setBackground(new Color (128,131,237));
       frame.add(boton,gbc);
       y++;
       
       boton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
                
               JButton o = (JButton)e.getSource();
               buttonText = o.getText();
               
               if( checkString(texto.getText(),operadores)){
      
                   texto.setText(texto.getText()+buttonText);
                  
                }               
           }
       });
    }
    
    //textfield implementation
    x=0;
    y=0;
    
    texto = new JTextField();
    gbc.gridx=x;
    gbc.gridy=y;
    gbc.gridwidth=5;
    texto.setEditable(false);
    texto.setForeground(Color.black);
    
    frame.add(texto,gbc);
    
    
    frame.setSize(500, 500);  
    frame.setVisible(true);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE); 
     
    }

    private boolean checkString(String s,char[] items){
        
        
        
        if (s.isEmpty()){
            
            return false;
            
        }else{
            
            char lastChar = s.charAt(s.length()-1);
            
            for(int i=0;i<items.length;i++){
                
            if (lastChar == items[i] || lastChar == '.'){
                
                return false;
                
            }
            
          }
            
        }
        
        return true;
    }
    
    private String infixToPostfix(String infix){
        Stack<Character> operators = new Stack<>();
        char symbol;
        String postfix = "";
        
        for(int i=0; i<infix.length();i++){
            symbol = infix.charAt(i);
            
            if(Character.isDigit(symbol)){
                postfix = postfix + symbol;
                
            }
            
            else if(symbol=='('){
                operators.push(symbol);
            }
            
            else if(symbol==')'){
                
                while(operators.peek()!='('){
                    postfix = postfix + operators.pop();
                }
                operators.pop();
            }
            
            else {
                postfix= postfix + ',';
                while(!operators.empty() && !(operators.peek()=='(') && prec(symbol) <= prec(operators.peek())){
                    postfix = postfix + operators.pop();
                    postfix= postfix + ',';

                }
                    
                    operators.push(symbol);
                
            }
            
        }
        while (!operators.empty()){
            postfix= postfix + ',';
                 postfix = postfix + operators.pop();
                 
            }
        return postfix;
    }
    
    private int prec (char x){
        if(x=='+' || x== '-'){
            return 1;
        }
        if(x=='*'||x=='/'||x=='%'){
            return 2;
        }
        
        return 0;
    }
    
    private String postFixCalculator(String postfix){
        
        Stack <Integer> valores = new Stack<>();
        Stack <Character> operadoresStack= new Stack<>();
        
        String valorActual="";
            char ultimoSigno = ' ';
        for(int i=0;i<postfix.length();i++){
            
            
            char lastChar = postfix.charAt(i);
            
            if (Character.isDigit(lastChar)){    
                valorActual = valorActual + lastChar;
            }
            else if(!Character.isDigit(lastChar) && lastChar != ','){
                ultimoSigno = lastChar;
            }
            
            else if(lastChar==','){
                if(!valorActual.isEmpty()){
                    valores.push(Integer.valueOf(valorActual));
                    valorActual = "";
                }
                
                if(ultimoSigno != ' '){
                    operadoresStack.push(ultimoSigno);
                    ultimoSigno = ' ';
                }
            }
            
            if (i == postfix.length()-1){
                 operadoresStack.push(ultimoSigno);
                 ultimoSigno = ' ';
            }
            
            if(!operadoresStack.empty()){
                int valor2 = valores.pop();
                int valor1 = valores.pop();
                
                int resultado = checkSigno(valor1,valor2,operadoresStack.pop());
                               
                valores.push(resultado);
            }
        }
        
        return valores.peek().toString();
    }

    
    private static int checkSigno(int valor1, int valor2, char signo ){
        int resultado=0;
        
        switch(signo){
            case '+': 
                resultado = valor1+valor2;
                break;
                
            case '-': 
                resultado = valor1-valor2;
                break;
            
            case '*': 
                resultado = valor1*valor2;
                break;
                
            case '/': 
                resultado = valor1/valor2;
                break;
        }
        
        return resultado;
    }
}
