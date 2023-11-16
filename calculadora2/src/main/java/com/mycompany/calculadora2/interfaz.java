/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadora2;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Arrays;
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
    char[] operadores = {'/','x','-','+'};
    String[] especiales = {"C","<-","%"};
    
    //atributos para la interfaz
    JTextField texto;
    JFrame frame;
    JButton boton;
    String buttonText;
    
    //Atributos para verificar el textfield y operar el string
    String valorActual ="";
    String resultado;
    
  
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
                        
                        
                        for(int i=0; i<operacion.length();i++){
                            
                            if(operacion.charAt(i) == '+' || operacion.charAt(i) == '-'
                                    ||operacion.charAt(i) == 'x' || operacion.charAt(i) == '/'){
                                System.out.println("holaaa");
                            }
                            else{
                                System.out.println("oh no");
                            }
                            
                            /*
                            valorActual +=buttonText;
                            int valor = Integer.parseInt(valorActual);
                            numerosStack.push(valor);
                   
                   
                            operadoresStack.push(buttonText);
                   
                            System.out.println(operadoresStack.peek());
                            System.out.println(numerosStack.peek());
                   
                            valorActual="";
                            */
                        }
                      
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

    public boolean checkString(String s,char[] items){
        
        
        
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
    
   
}
