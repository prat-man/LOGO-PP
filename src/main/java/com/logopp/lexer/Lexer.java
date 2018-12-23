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

package com.logopp.lexer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.mozilla.universalchardet.UniversalDetector;

import com.glaforge.i18n.io.CharsetToolkit;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import com.logopp.api.core.Lexicon;

public class Lexer
{
	/**
	 * Tokenize a source file into tokens
	 * @param file: instance of type java.io.File for source file
	 * @return Array containing the tokens
	 * @throws IOException
	 */
	public static Token[] getTokens(File file) throws IOException
	{
		String filePath = file.getAbsolutePath();
		
		// if file does not exist, throw an exception
		if (!file.exists()) {
			throw new FileNotFoundException("File Not Found: " + filePath);
		}
		
		// if file is not actually a file, but a directory
		if (file.isDirectory() || filePath == null || !filePath.toLowerCase().endsWith(".lpp")) {
			throw new IOException("Invalid File Format: " + filePath);
		}
		
		// getLexemes the file character-wise
		return getTokensFromFile(file);
	}
	
	/**
	 * Tokenize a source file into tokens
	 * @param filePath: path to source file
	 * @return Array containing the tokens
	 * @throws IOException
	 */
	public static Token[] getTokens(String filePath) throws IOException
	{
		return getTokens(new File(filePath));
	}
	
	/**
	 * Utility method that actually gets lexemes from the source file as tokens
	 * @param file: instance of type java.io.File for source file
	 * @return Array containing the tokens
	 * @throws IOException
	 */
	private static Token[] getTokensFromFile(File file) throws IOException
	{
		// list to store the tokens
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		// detect file encoding
		String encoding = guessEncoding(file);
		
		System.out.println("Detected Encoding: " + encoding);
		
		// open a handle to read the file
	    InputStreamReader isr = null;
	    FileInputStream fis = new FileInputStream(file);
	    
	    try {
	    	if (encoding == null) {
	    		isr = new InputStreamReader(fis);
	    	}
	    	else {
	    		isr = new InputStreamReader(fis, encoding);
	    	}
	    	
	    	String lexeme = new String();
			
			int line = 1;
			int column = 1;
			
			// read two characters at a time
			int ch, next;
			
			// read current character
			ch = isr.read();
			
			// handle byte mark
			if (ch == 0xFEFF ||			// UTF-16 BE
				ch == 0xFFFE ||			// UTF-16 LE
				ch == 0x0000FEFF ||		// UTF-32 BE
				ch == 0xFFFE0000)		// UTF-32 LE
			{
				// ignore byte order mark
				ch = isr.read();
			}
			
			// loop through the file two characters at a time
			while (ch != -1) {
				// read next character also to handle composite operators
				next = isr.read();
				
				// if character is whitespace, add lexeme to tokens list
				if (Character.isWhitespace((char) ch)) {
					if (!lexeme.equals(new String())) {
						tokens.add(new Token(lexeme, line, column - lexeme.length()));
						lexeme = new String();
					}
					
					// handle line endings
					// if using old mac line ending, use \r to break lines
					// else use \n to break lines for both windows and linux
					if ((char) ch == '\n' || ((char) ch == '\r' && (char) next != '\n')) {
						// increment line number
						line++;
						
						// reset column number
						column = 0;
					}
				}
				
				// if it is a composite operator, add lexeme and composite operator to tokens list
				else if (next != -1 && Lexicon.isOperator(String.valueOf((char) ch) + String.valueOf((char) next))) {
					if (!lexeme.equals(new String())) {
						tokens.add(new Token(lexeme, line, column - lexeme.length()));
						lexeme = new String();
					}
					
					tokens.add(new Token(String.valueOf((char) ch) + String.valueOf((char) next), line, column - lexeme.length()));
					
					// consume next character
					next = isr.read();
					
					// increment column number
					column++;
				}
				
				// if (character is a simple operator, add lexeme and simple operator to lexeme list
				else if (Lexicon.isOperator(String.valueOf((char) ch))) {
					if (!lexeme.equals(new String())) {
						tokens.add(new Token(lexeme, line, column - lexeme.length()));
						lexeme = new String();
					}
					
					tokens.add(new Token(String.valueOf((char) ch), line, column - lexeme.length()));
				}
				
				// otherwise add to lexeme
				else {
					lexeme += (char) ch;
				}
				
				// advance
				ch = next;
				
				// increment column number
				column++;
			}
			
			// return an array containing the lexemes
			return tokens.toArray(new Token[0]);
	    }
	    finally {
	    	try {
	    		isr.close();
	    	}
	    	catch (Exception e) {
				// bad, but nothing we can do at this point
			}
	    }
	}
	
	/**
	 * Utility method to guess the encoding of a text file
	 * @param file: File for which encoding is to be guessed
	 * @return Guessed file encoding if possible, else null
	 */
	public static String guessEncoding(File file)
	{
		String encoding1, encoding2, encoding3;
		
		BufferedInputStream bis = null;
		
		// Detect encoding using GuessEncoding
		try {
			byte[] buffer = new byte[10240];
		    bis = new BufferedInputStream(new FileInputStream(file));
		    bis.read(buffer);
		    CharsetToolkit ct = new CharsetToolkit(buffer);
		    encoding1 = ct.guessEncoding().displayName();
		}
		catch (IOException e) {
			encoding1 = null;
		}
		finally {
			try {
				bis.close();
			}
			catch (Exception e) {
				// bad, but nothing we can do at this point
			}
		}
	    
		// Detect encoding using ICU4j
		try {
			CharsetDetector charsetDetector = new CharsetDetector();
			bis = new BufferedInputStream(new FileInputStream(file));
		    charsetDetector.setText(bis);
		    charsetDetector.enableInputFilter(true);
		    CharsetMatch cm = charsetDetector.detect();
		    encoding2 =  cm.getName();
		}
		catch (IOException e) {
			encoding2 = null;
		}
		finally {
			try {
				bis.close();
			}
			catch (Exception e) {
				// bad, but nothing we can do at this point
			}
		}
		
		// Detect encoding using juniversalchardset
		try {
			encoding3 = UniversalDetector.detectCharset(file);
		} catch (IOException e) {
			encoding3 = null;
		}
		
		// Return best guess
		if (encoding1 != null && encoding1.equals(encoding2))      return encoding1;
		else if (encoding2 != null && encoding2.equals(encoding3)) return encoding2;
		else if (encoding3 != null && encoding3.equals(encoding1)) return encoding1;
		else if (encoding1 != null) return encoding1;
		else if (encoding2 != null) return encoding2;
		else if (encoding3 != null) return encoding3;
		else return null;
	}
	
	/**
	 * Test function - main
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		Token[] tokens = getTokens("/mnt/D Drive/a.lpp");
		
		for (Token token : tokens) {
			System.out.println(token);
		}
	}
}
