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

public abstract class Node
{
	// token contained in the node
	protected Token token;
	
	// parent node in sequential execution
	protected Node parent;
	
	// next node in sequential execution
	protected Node next;
	
	/**
	 * Parameterized constructor
	 * @param token: The token contained in this node
	 */
	public Node(Token token) {
		super();
		this.token = token;
	}
	
	/* Getters and Setters */
	
	/**
	 * Get token contained by this node
	 * @return token contained by this node
	 */
	public Token getToken() {
		return token;
	}
	
	/**
	 * Get parent node in sequential execution
	 * @return parent node
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * Set parent node in sequential execution
	 * @param parent: parent node
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	/**
	 * Get next node in sequential execution
	 * @return next node
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * Set next node in sequential execution
	 * @param next: next node
	 */
	public void setNext(Node next) {
		this.next = next;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [token=" + token + ", parent=" + parent + ", next=" + next + "]";
	}
}
