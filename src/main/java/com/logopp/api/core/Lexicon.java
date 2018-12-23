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
import java.util.Arrays;
import java.util.List;

public class Lexicon
{
	/*********************************************
	 *                 Keywords                  *
	 *********************************************/
	
	private static ArrayList<String> keywords;
	
	static {
		if (keywords == null) {
			keywords = new ArrayList<String>();
			
			// direction control
			keywords.add("forward");
			keywords.add("backward");
			keywords.add("rightturn");
			keywords.add("leftturn");
			
			// reset environment state
			//keywords.add("reset");
			
			// reset turtle to starting position
			//keywords.add("home");
			
			// clear image
			keywords.add("clear");
			
			// turtle visibility control
			keywords.add("turtle.hide");
			keywords.add("turtle.show");
			
			// pen marking control
			keywords.add("pen.up");
			keywords.add("pen.down");
			
			// branching control
			keywords.add("if");
			keywords.add("else");
			keywords.add("repeat");
			keywords.add("while");
			
			// other reserved keywords
			keywords.add("class");
			keywords.add("func");
			keywords.add("construct");
			keywords.add("render");
			keywords.add("def");
			
			// predefined classes
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
			//operators.add(".");
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
	
	public static boolean isBinaryOperator(String lexeme) {
        List<String> binOps = Arrays.asList(new String[]{"/", "*", "+", "-"});
        return binOps.contains(lexeme);
    }
}
