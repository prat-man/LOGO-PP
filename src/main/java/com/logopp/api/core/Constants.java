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
