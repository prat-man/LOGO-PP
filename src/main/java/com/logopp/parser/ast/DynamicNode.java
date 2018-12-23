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

import java.util.ArrayList;

import com.logopp.lexer.Token;

public class DynamicNode extends Node
{
	// children of the node
	private ArrayList<Node> children;
	
	/**
	 * Parameterized constructor
	 * @param token: The token contained in this node
	 */
	public DynamicNode(Token token) {
		super(token);
	}
	
	/* Getters and Setters */
	
	/**
	 * Get all child nodes
	 * @return array containing all child nodes
	 */
	public Node[] getChildren() {
		return children.toArray(new Node[0]);
	}
	
	/**
	 * Set child nodes
	 * @param children: ArrayList containing all children
	 */
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	/**
	 * Get child node by index 
	 * @param index: index for which child node is to be fetched
	 * @return child node at specified index
	 */
	public Node getChild(int index) {
		return this.children.get(index);
	}
	
	/**
	 * Add child for current node
	 * @param node: node to be added as child
	 */
	public void addChild(Node node) {
		this.children.add(node);
	}
	
	/**
	 * Add child node at specific index
	 * @param index: index at which child node is to be added
	 * @param node: node to be added as child
	 */
	public void addChild(int index, Node node) {
		this.children.add(index, node);
	}
}
