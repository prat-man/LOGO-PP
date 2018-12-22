/*
 * Author: Pratanu Mandal
 * 
 * This file is part of LOGO++.
 * 
 * LOGO++ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * LOGO++ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with LOGO++.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.logopp.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.logopp.api.core.Constants;
import com.logopp.lexer.Lexer;
import com.logopp.lexer.Token;
import com.logopp.parser.ast.BinaryNode;
import com.logopp.parser.ast.LeafNode;
import com.logopp.parser.ast.Node;

public class ShuntingYard
{
    public static Node exprToAST(Token[] tokens, int index) throws Exception
    {
        Stack<Token> postfix = new Stack<>();
        
        Stack<Token> opStack = new Stack<>();
        
        for (int i = index; i < tokens.length; i++) {
        	Token token = tokens[i];
            String lexeme = token.getLexeme();
            
            if (Constants.isKeyword(lexeme) || lexeme.equals(";")) {
                do {
                    Token op = opStack.pop();
                    
                    if (op.getLexeme().equals("(")) {
                        throw new Exception("Malformed Expression");
                    }
                    
                    postfix.push(op);
                } while (!opStack.empty());
            }
            
            else if (lexeme.equals("(")) {
                opStack.push(token);
            }
            
            else if (lexeme.equals(")")) {
                Token op = opStack.pop();
                
                while (!op.getLexeme().equals("(")) {
                    postfix.push(op);
                    op = opStack.pop();
                }
            }
            
            else if (isBinaryOperator(lexeme)) {
            	while (!opStack.empty() && precedence(lexeme) < precedence(opStack.peek().getLexeme())) {
            		postfix.push(opStack.pop());
            	}
            	
            	opStack.push(token);
                /*if (opStack.empty() || precedence(lexeme) >= precedence(opStack.peek().getLexeme())) {
                    opStack.push(token);
                }
                else {
                	do {
                		postfix.push(opStack.pop());
                	} while (!opStack.empty() && precedence(lexeme) >= precedence(opStack.peek().getLexeme()));
                }*/
            }
            
            else {
                postfix.push(token);
            }
        }
        
        System.out.println();
        
        for (Token token : postfix) {
        	System.out.println(token);
        }
        
        BinaryNode node = new BinaryNode(postfix.pop());
        
        stackToAST(postfix, node);
        
        if (!postfix.isEmpty()) {
        	throw new Exception("Malformed Expression");
        }
        
        return node;
    }

    private static void stackToAST(Stack<Token> postfix, BinaryNode root)
    {
    	if (isBinaryOperator(root.getToken().getLexeme())) {
    		Token token;
    		
    		token = postfix.pop();
    		
    		if (token != null) {
    			if (isBinaryOperator(token.getLexeme())) {
    				BinaryNode node = new BinaryNode(token);
    				node.setParent(root);
    				root.setRight(node);
    				
    				stackToAST(postfix, node);
    			}
    			else {
    				LeafNode node = new LeafNode(token);
    				node.setParent(root);
    				root.setRight(node);
    			}
    		}
    		
    		token = postfix.pop();
    		
    		if (token != null) {
    			if (isBinaryOperator(token.getLexeme())) {
    				BinaryNode node = new BinaryNode(token);
    				node.setParent(root);
    				root.setLeft(node);
    				
    				stackToAST(postfix, node);
    			}
    			else {
    				LeafNode node = new LeafNode(token);
    				node.setParent(root);
    				root.setRight(node);
    			}
    		}
    	}
	}

	private static int precedence(String lexeme) {
        switch (lexeme) {
            case "/":
            case "*": return 2;
            
            case "+":
            case "-": return 1;
            
            default:  return 0;
        }
    }

    private static boolean isBinaryOperator(String lexeme) {
        List<String> binOps = Arrays.asList(new String[]{"/", "*", "+", "-"});
        return binOps.contains(lexeme);
    }
    
    public static void main(String[] args) throws Exception
    {
        Token[] tokens = Lexer.getTokens("/mnt/D Drive/x.lpp");
        
        for (Token token : tokens) {
        	System.out.println(token);
        }
        
        Node node = ShuntingYard.exprToAST(tokens, 5);
        
        System.out.println(node.toString());
    }
}
