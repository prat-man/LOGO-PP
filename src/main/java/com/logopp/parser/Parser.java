package com.logopp.parser;

import com.logopp.parser.ast.Node;

public class Parser
{
	private int index;
	
	private Node root;
	
	private String[] tokens;
	
	public Parser(String[] tokens)
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
