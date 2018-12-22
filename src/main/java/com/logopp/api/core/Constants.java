package com.logopp.api.core;

import java.util.ArrayList;

public class Constants
{
	/*********************************************
	 *                 Keywords                  *
	 *********************************************/
	
	private static ArrayList<String> keywords;
	
	static {
		if (keywords == null) {
			keywords = new ArrayList<String>();
			
			keywords.add("forward");
			keywords.add("back");
			keywords.add("right");
			keywords.add("left");
			keywords.add("home");
			keywords.add("clear");
			keywords.add("clearscreen");
			keywords.add("hideturtle");
			keywords.add("showturtle");
			keywords.add("penup");
			keywords.add("pendown");
			keywords.add("cleartext");
			keywords.add("repeat");
			//keywords.add("loop");
			keywords.add("cleartext");
			
			keywords.add("rectangle");
			keywords.add("square");
			keywords.add("circle");
			keywords.add("arc");
		}
	}
	
	public static String[] getKeywords()
	{
		return (String[]) keywords.toArray();
	}
	
	public static boolean isKeyword(String kw)
	{
		return keywords.contains(kw);
	}
	
	
	
	/******************************************
	 *               Operators                *
	 ******************************************/
	
	private static ArrayList<String> operators;
	
	static {
		if (operators == null) {
			operators = new ArrayList<String>();
			
			operators.add("+");
			operators.add("-");
			operators.add("*");
			operators.add("/");
			operators.add("=");
			operators.add("{");
			operators.add("}");
			operators.add("[");
			operators.add("]");
			operators.add("(");
			operators.add(")");
			operators.add("::");
			operators.add(",");
			operators.add(".");
			operators.add(";");
		}
	}
	
	public static String[] getOperators()
	{
		return (String[]) operators.toArray();
	}
	
	public static boolean isOperator(String op)
	{
		return operators.contains(op);
	}
}
