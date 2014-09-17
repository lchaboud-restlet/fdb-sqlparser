package parser;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import util.Util;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;

public class SqlImport {

	public List<StatementNode> read(final InputStream is) {
		if(is == null) {
			return null;
		}

		final List<String> querys = getSqlQuerys(is);
		final List<StatementNode> stmts = read(querys);

		return stmts;
	}

	public List<StatementNode> read(final List<String> querys) {

		final List<StatementNode> stmts = new ArrayList<StatementNode>();

		final SQLParser parser = new SQLParser();
		for(final String query : querys) {
			StatementNode stmt;
			try {
				stmt = parser.parseStatement(query);
				stmts.add(stmt);
			} catch (final StandardException e) {
				System.out.println("Error on query : "+query);
				System.out.println(e);
			}
		}

		return stmts;
	}

	public List<String> getSqlQuerys(final InputStream is) {
		if(is == null) {
			return null;
		}

		final Util util = new Util();
		final String content = util.read(is);

		final List<String> querys = getSqlQuerys(content);

		return querys;
	}

	public List<String> getSqlQuerys(final String content) {
		final List<String> querys = new ArrayList<String>();

		int posStart = getPosStartQuery(content, 0);
		int posEnd = getPosEndQuery(content, posStart);
		while((posStart != -1) && (posStart < content.length()) && (posEnd < content.length())) {

			final String query = content.substring(posStart, posEnd);
			System.out.println("query : " + query);
			final String queryUpperCase = query.toUpperCase();
			if((queryUpperCase.indexOf("CREATE TABLE") != -1) || ((queryUpperCase.indexOf("ALTER TABLE") != -1) && ((queryUpperCase.indexOf("ADD CONSTRAINT") != -1) || (queryUpperCase.indexOf("MODIFY") != -1)) ) ) {
				querys.add(query);
			}

			if((posEnd + 1) >= content.length()) {
				posStart = -1;
			} else {
				posStart = getPosStartQuery(content,posEnd+1);
				posEnd = getPosEndQuery(content, posStart);
			}
		}

		return querys;
	}

	private int getPosStartQuery(final String content, int pos) {

		boolean inLineComment = false;
		boolean inMultiLineComment = false;
		boolean inStringValue = false;

		while(pos < content.length()) {
			final char character = content.charAt(pos);
			if(character == '/') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '*')) {
						inMultiLineComment = true;
						pos = pos+2;
						continue;
					}
				}
			}
			if(character == '*') {
				if(!inStringValue && !inLineComment && inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '/')) {
						inMultiLineComment = false;
						pos = pos+2;
						continue;
					}
				}
			}
			if(character == '"') {
				if(!inLineComment && !inMultiLineComment) {
					inStringValue = !inStringValue;
				}
			}
			if(character == '-') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '-')) {
						inLineComment = true;
						pos = pos + 2;
						continue;
					}
				}
			}
			if((character == '\n') || (character == '\r') ) {
				if(inLineComment) {
					inLineComment = false;
				}
			}
			if(((character >= 'a') && (character <= 'z')) ||((character >= 'A') && (character <= 'Z'))) {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					break;
				}
			}
			pos++;
		}

		return pos;
	}

	private int getPosEndQuery(final String content, int pos) {

		boolean inLineComment = false;
		boolean inMultiLineComment = false;
		boolean inStringValue = false;

		while(pos < content.length()) {
			final char character = content.charAt(pos);
			if(character == '/') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '*')) {
						inMultiLineComment = true;
						pos = pos+2;
						continue;
					}
				}
			}
			if(character == '*') {
				if(!inStringValue && !inLineComment && inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '/')) {
						inMultiLineComment = false;
						pos = pos+2;
						continue;
					}
				}
			}
			if(character == '"') {
				if(!inLineComment && !inMultiLineComment) {
					inStringValue = !inStringValue;
				}
			}
			if(character == '-') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					if(((pos+1) < content.length()) && (content.charAt(pos+1) == '-')) {
						inLineComment = true;
						pos = pos + 2;
						continue;
					}
				}
			}
			if((character == '\n') || (character == '\r') ) {
				if(inLineComment) {
					inLineComment = false;
				}
			}
			if(character == ';') {
				if(!inStringValue && !inLineComment && !inMultiLineComment) {
					break;
				}
			}
			pos++;
		}

		return pos;
	}


}
