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
			// TODO: Handle keywords
			
			// TODO: Handle compound statements (brackets {})
			
			// TODO: Handle assignments
			
			// TODO: Handle expressions
			
			// TODO: Handle function calls
		}
	}
}
