package com.logopp.lexer;

public class Token
{
	// the lexeme contained in the token
	private String lexeme;
	
	// the line number which contains the lexeme
	private int line;
	
	// the column number where the column starts
	private int column;
	
	// constructor
	public Token(String lexeme, int line, int column) {
		super();
		this.lexeme = lexeme;
		this.line = line;
		this.column = column;
	}
	
	// getters and setters
	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Token [lexeme=" + lexeme + ", line=" + line + ", column=" + column + "]";
	}
}
