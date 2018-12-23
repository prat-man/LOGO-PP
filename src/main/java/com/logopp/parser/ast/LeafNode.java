package com.logopp.parser.ast;

import com.logopp.lexer.Token;

public class LeafNode extends Node
{
	public LeafNode(Token token) {
		super(token);
	}
}
