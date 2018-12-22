package com.logopp.parser.ast;

import java.util.ArrayList;

public class Node
{
	// token contained in the node
	private String token;
	
	// children of the node
	private ArrayList<Node> children;
	
	// next node in sequential execution
	private Node next;
	
	// constructor
	public Node(String token) {
		super();
		this.token = token;
	}
	
	public Node[] getChildren() {
		return children.toArray(new Node[0]);
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	public Node getChild(int index) {
		return this.children.get(index);
	}
	
	public void addChild(Node node) {
		this.children.add(node);
	}
	
	public void addChild(int index, Node node) {
		this.children.add(index, node);
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public String getToken() {
		return token;
	}
}
