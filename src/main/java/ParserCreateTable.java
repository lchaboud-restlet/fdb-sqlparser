import com.foundationdb.sql.parser.ColumnDefinitionNode;
import com.foundationdb.sql.parser.ConstraintDefinitionNode;
import com.foundationdb.sql.parser.CreateTableNode;
import com.foundationdb.sql.parser.ResultColumn;
import com.foundationdb.sql.parser.ResultColumnList;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;
import com.foundationdb.sql.parser.TableElementList;
import com.foundationdb.sql.parser.TableElementNode;

public class ParserCreateTable {
	public static void main(final String[] args) throws Exception {
		final SQLParser parser = new SQLParser();

		final String s = "CREATE TABLE films (\n"+
				"    code        char(5) CONSTRAINT firstkey PRIMARY KEY,\n"+
				"    title       varchar(40) NOT NULL,\n"+
				"    did         integer NOT NULL,\n"+
				"    date_prod   date,\n"+
				"    kind        varchar(10),\n"+
				"    len         interval hour to minute\n"+
				")";
		final StatementNode stmt = parser.parseStatement(s);
		System.out.println(stmt.getNodeType());
		System.out.println(stmt.getClass());
		stmt.treePrint();
		System.out.println("===========================================================================");
		final CreateTableNode ctn = (CreateTableNode) stmt;
		System.out.println("full name : "+ctn.getFullName());
		System.out.println("relative name : "+ctn.getRelativeName());
		final TableElementList tel = ctn.getTableElementList();
		for(int i=0; i<tel.size(); i++) {
			final TableElementNode ten = tel.get(i);
			System.out.println("\n - "+i+" : "+ten.getClass());
			System.out.println("name : "+ten.getName());
			if(ten instanceof ColumnDefinitionNode) {
				final ColumnDefinitionNode cdn = (ColumnDefinitionNode) ten;
				System.out.println("column name : "+cdn.getColumnName());
				System.out.println("type : "+cdn.getType());
				System.out.println("Autoinc_create_or_modify_Start_Increment : "+cdn.getAutoinc_create_or_modify_Start_Increment());
				System.out.println("AutoincrementIncrement : "+cdn.getAutoincrementIncrement());
				System.out.println("AutoincrementStart : "+cdn.getAutoincrementStart());
			}
			if(ten instanceof ConstraintDefinitionNode) {
				final ConstraintDefinitionNode constraint = (ConstraintDefinitionNode) ten;
				System.out.println("\n - Constraint :");
				System.out.println("constraint name : "+constraint.getConstraintName());
				System.out.println("constraint type : "+constraint.getConstraintType());
				final ResultColumnList rcl = constraint.getColumnList();
				for(int j=0; j<rcl.size(); j++) {
					final ResultColumn rc = rcl.get(j);
					System.out.println(" - result column : "+i+" - class : "+rc.getClass());
					System.out.println("name : "+rc.getName());
				}
			}
		}
	}
}