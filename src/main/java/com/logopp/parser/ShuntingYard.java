package com.logopp.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShuntingYard
{
    public static String[] infixToPostfix(String[] infix, int index) throws Exception
    {
        ArrayList<String> postfix = new ArrayList<>();
        
        Stack<String> opStack = new Stack<>();
        
        for (int i = index; i < infix.length; i++) {
            String token = infix[i];
            
            if (isKeyword(token) || token.equals(";")) {
                do {
                    String op = opStack.pop();
                    
                    if (op.equals("(")) {
                        throw new Exception("Malformed Expression");
                    }
                    
                    postfix.add(op);
                } while (!opStack.empty());
            }
            
            else if (token.equals("(")) {
                opStack.push(token);
            }
            
            else if (token.equals(")")) {
                String op = opStack.pop();
                
                while (!op.equals("(")) {
                    postfix.add(op);
                    op = opStack.pop();
                }
            }
            
            else if (isBinaryOperator(token)) {
                if (opStack.empty() || precedence(token) >= precedence(opStack.peek())) {
                    opStack.push(token);
                }
            }
            
            else {
                postfix.add(token);
            }
        }
        
        return postfix.toArray(new String[0]);
    }

    private static int precedence(String token) {
        switch (token) {
            case "/":
            case "*": return 2;
            
            case "+":
            case "-": return 1;
            
            default:  return 0;
        }
    }

    private static boolean isBinaryOperator(String token) {
        List<String> binOps = Arrays.asList(new String[]{"/", "*", "+", "-"});
        return binOps.contains(token);
    }

    private static boolean isKeyword(String token) {
        // TODO Auto-generated method stub
        return false;
    }
    
    public static void main(String[] args) throws Exception
    {
        String[] tokens = {"value", "=", "5", "+", "6", "*", "7", "render", "(", "circle1", ")", ";"};
        
        String[] postfix = infixToPostfix(tokens, 2);
        
        for (String token : postfix) {
            System.out.println(token);
        }
        
        System.out.println();
    }
}
