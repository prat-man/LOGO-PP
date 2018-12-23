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

import com.logopp.api.core.Lexicon;
import com.logopp.lexer.Token;
import com.logopp.parser.ast.Node;

public class Parser
{
	private int index;
	
	private Node root;
	
	private Token[] tokens;
	
	public Parser(Token[] tokens)
	{
		this.tokens = tokens; 
	}
	
	public void parse()
	{
		while (index < tokens.length) {
			String lexeme = tokens[index].getLexeme();
			String nextLexeme = tokens[index + 1].getLexeme();
			
			// Handle keywords
			if (Lexicon.isKeyword(lexeme)) {
				handleKeyword();
			}
			
			// Handle compound statements (brackets {})
			else if (lexeme.equals("{")) {
				handleCompoundStatement();
			}
			
			// Handle assignments
			else if (nextLexeme.equals("=")) {
				handleAssignment();
			}
			
			// Handle expressions
			else if (Lexicon.isBinaryOperator(nextLexeme)) {
				handleExpression();
			}
			
			// Handle function calls
			// Well, constructor calls to be more precise; we do not allow functions yet
			else if (nextLexeme.equals("(")) {
				handleFunctionCall();
			}
		}
	}

	private void handleFunctionCall() {
		// TODO Auto-generated method stub
		
	}

	private void handleExpression() {
		// TODO Auto-generated method stub
		
	}

	private void handleAssignment() {
		// TODO Auto-generated method stub
		
	}

	private void handleCompoundStatement() {
		// TODO Auto-generated method stub
		
	}

	private void handleKeyword() {
		// TODO Auto-generated method stub
		
	}
}
