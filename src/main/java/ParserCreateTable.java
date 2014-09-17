import com.foundationdb.sql.parser.ColumnDefinitionNode;
import com.foundationdb.sql.parser.ConstraintDefinitionNode;
import com.foundationdb.sql.parser.CreateTableNode;
import com.foundationdb.sql.parser.FKConstraintDefinitionNode;
import com.foundationdb.sql.parser.ResultColumn;
import com.foundationdb.sql.parser.ResultColumnList;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;
import com.foundationdb.sql.parser.TableElementList;
import com.foundationdb.sql.parser.TableElementNode;

public class ParserCreateTable {
	public static void main(final String[] args) throws Exception {
		final SQLParser parser = new SQLParser();

		final String s1 = "CREATE TABLE films (\n"+
				"    code        char(5) CONSTRAINT firstkey PRIMARY KEY,\n"+
				"    title       varchar(40) NOT NULL,\n"+
				"    did         integer NOT NULL,\n"+
				"    date_prod   date,\n"+
				"    kind        varchar(10),\n"+
				"    len         interval hour to minute\n"+
				")";

		final String mySQL = "CREATE TABLE Orders\n"
				+"(\n"
				+"O_Id int NOT NULL,\n"
				+"OrderNo int NOT NULL,\n"
				+"P_Id int,\n"
				+"PRIMARY KEY (O_Id),\n"
				+"FOREIGN KEY (P_Id) REFERENCES Persons(P_Id2)\n"
				+")";

		final String oracleSQLServerAccess = "CREATE TABLE Orders\n"
				+"(\n"
				+"O_Id int NOT NULL PRIMARY KEY,\n"
				+"OrderNo int NOT NULL,\n"
				+"P_Id int FOREIGN KEY REFERENCES Persons(P_Id)\n"
				+")";

		final String all = "CREATE TABLE Orders\n"
				+"(\n"
				+"O_Id int NOT NULL,\n"
				+"OrderNo int NOT NULL,\n"
				+"P_Id int,\n"
				+"PRIMARY KEY (O_Id),\n"
				+"CONSTRAINT fk_PerOrders FOREIGN KEY (P_Id, P_Id2)\n"
				+"REFERENCES Persons(P_Id, P_Id2)\n"
				+")";

		final String oracle1 = "CREATE TABLE employeesdemo\n"
				+"(\n"
				+" employee_id    NUMBER(6)\n"
				+", first_name     VARCHAR2(20)\n"
				+", last_name      VARCHAR2(25) \n"
				+"     CONSTRAINT emp_last_name_nn_demo NOT NULL\n"
				+", email          VARCHAR2(25) \n"
				+"     CONSTRAINT emp_email_nn_demo     NOT NULL\n"
				+", phone_number   VARCHAR2(20)\n"
				+", hire_date      DATE  DEFAULT SYSDATE \n"
				+"     CONSTRAINT emp_hire_date_nn_demo  NOT NULL\n"
				+", job_id         VARCHAR2(10)\n"
				+"   CONSTRAINT     emp_job_nn_demo  NOT NULL\n"
				+", salary         NUMBER(8,2)\n"
				+"   CONSTRAINT     emp_salary_nn_demo  NOT NULL\n"
				+", commission_pct NUMBER(2,2)\n"
				+", manager_id     NUMBER(6)\n"
				+", department_id  NUMBER(4)\n"
				+", dn             VARCHAR2(300)\n"
				/*
				+", CONSTRAINT     emp_salary_min_demo\n"
				+"                 CHECK (salary > 0) \n"
				+", CONSTRAINT     emp_email_uk_demo\n"
				+"                 UNIQUE (email)\n"
				 */
				 +") ;";

		final String oracle2 = "CREATE TABLE employeesdemo\n"
				+"(\n"
				+" employee_id    INT\n"
				+", first_name     VARCHAR(20)\n"
				+", last_name      VARCHAR(25) \n"
				+"     CONSTRAINT emp_last_name_nn_demo NOT NULL\n"
				+", email          VARCHAR(25) \n"
				+"     CONSTRAINT emp_email_nn_demo     NOT NULL\n"
				+", phone_INT   VARCHAR(20)\n"
				+", hire_date      DATE \n"
				+"     CONSTRAINT emp_hire_date_nn_demo  NOT NULL\n"
				+", job_id         VARCHAR(10)\n"
				+"   CONSTRAINT     emp_job_nn_demo  NOT NULL\n"
				+", salary         DECIMAL\n"
				+"   CONSTRAINT     emp_salary_nn_demo  NOT NULL\n"
				+", commission_pct DECIMAL\n"
				+", manager_id     INTEGER\n"
				+", department_id  INTEGER\n"
				+", dn             VARCHAR(300)\n"
				/*
				+", CONSTRAINT     emp_salary_min_demo\n"
				+"                 CHECK (salary > 0) \n"
				+", CONSTRAINT     emp_email_uk_demo\n"
				+"                 UNIQUE (email)\n"
				 */
				 +")";

		//String s = mySQL;
		//final String s = oracleSQLServerAccess;
		//final String s = all;
		final String s = oracle2;


		System.out.println("SQL query : \n"+s+"\n\n");
		final StatementNode stmt = parser.parseStatement(s);
		System.out.println("===========================================================================");
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
			if(ten instanceof FKConstraintDefinitionNode) {
				final FKConstraintDefinitionNode constraint = (FKConstraintDefinitionNode) ten;
				System.out.println("\n - Constraint :");
				System.out.println("constraint name : "+constraint.getConstraintName());
				System.out.println("constraint type : "+constraint.getConstraintType());
				ResultColumnList rcl = constraint.getColumnList();
				for(int j=0; j<rcl.size(); j++) {
					final ResultColumn rc = rcl.get(j);
					System.out.println(" - result column : "+i+" - class : "+rc.getClass());
					System.out.println("name : "+rc.getName());
				}
				rcl = constraint.getRefResultColumnList();
				for(int j=0; j<rcl.size(); j++) {
					final ResultColumn rc = rcl.get(j);
					System.out.println(" - result column : "+i+" - class : "+rc.getClass());
					System.out.println("name : "+rc.getName());
				}
			}
			else
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