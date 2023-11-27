
import java.util.Stack;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author diego
 */
public class testing {

    static Stack<Character> operators = new Stack<>();
    public static void main(String[] args) {
      String operacion="1+2*14+1+1*2";
        String newOp = infixToPostfix(operacion);
        System.out.println(newOp);
        System.out.println(postFixCalculator(newOp));
      
    }

    private static String infixToPostfix(String infix){
        
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
    
    static int prec (char x){
        if(x=='+' || x== '-'){
            return 1;
        }
        if(x=='*'||x=='/'||x=='%'){
            return 2;
        }
        
        return 0;
    }
    
    private static String postFixCalculator(String postfix){
        
        Stack <Integer> valores = new Stack<>();
        Stack <Character> operadores= new Stack<>();
        
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
                    operadores.push(ultimoSigno);
                    ultimoSigno = ' ';
                }
            }
            
            if (i == postfix.length()-1){
                 operadores.push(ultimoSigno);
                 ultimoSigno = ' ';
            }
            
            if(!operadores.empty()){
                int valor2 = valores.pop();
                int valor1 = valores.pop();
                
                int resultado = checkSigno(valor1,valor2,operadores.pop());
                               
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

