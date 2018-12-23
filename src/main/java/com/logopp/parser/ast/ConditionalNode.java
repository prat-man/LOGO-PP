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

package com.logopp.parser.ast;

import com.logopp.lexer.Token;

public class ConditionalNode extends Node
{
	private Node condition;
	
	private Node trueChild;
	
	private Node falseChild;
	
	public ConditionalNode(Token token) {
		super(token);
	}

	public Node getCondition() {
		return condition;
	}

	public void setCondition(Node condition) {
		this.condition = condition;
	}

	public Node getTrueChild() {
		return trueChild;
	}

	public void setTrueChild(Node trueChild) {
		this.trueChild = trueChild;
	}

	public Node getFalseChild() {
		return falseChild;
	}

	public void setFalseChild(Node falseChild) {
		this.falseChild = falseChild;
	}
}
