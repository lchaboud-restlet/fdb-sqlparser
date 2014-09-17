
Tests
=====

Run tests : "SqlImportTest.java"

SQL scripts for databases tests :
- mysql.sql
- oracle1.sql
- oracle2.sql
- postgres.sql
- standard.sql

--------------------


Run "ParserCreateTable.java"

* SQL query :
```
SQL query : 
CREATE TABLE films (
    code        char(5) CONSTRAINT firstkey PRIMARY KEY,
    title       varchar(40) NOT NULL,
    did         integer NOT NULL,
    date_prod   date,
    kind        varchar(10),
    len         interval hour to minute
)
```
=> Results :
```
===========================================================================
com.foundationdb.sql.parser.CreateTableNode@5a8a0d5d
name: films
statementType: CREATE TABLE
withData: false
lockGranularity: R
existenceCheck: NO_CONDITION
tableElementList: 	
	com.foundationdb.sql.parser.TableElementList@1d73831b

	[0]:	
	com.foundationdb.sql.parser.ConstraintDefinitionNode@aa470b8
	constraintName: firstkey
	constraintType: PRIMARY_KEY
	properties: null
	name: firstkey
	elementType: AT_DROP_CONSTRAINT
	columnList: 		
		com.foundationdb.sql.parser.ResultColumnList@18e2b22

		[0]:		
		com.foundationdb.sql.parser.ResultColumn@1cb1c5fa
		exposedName: code
		name: code
		tableName: null
		isDefaultColumn: false
		type: null
	[1]:	
	com.foundationdb.sql.parser.ColumnDefinitionNode@5b3caecd
	type: CHAR(5)
	name: code
	elementType: null
	[2]:	
	com.foundationdb.sql.parser.ColumnDefinitionNode@1f194a4e
	type: VARCHAR(40) NOT NULL
	name: title
	elementType: null
	[3]:	
	com.foundationdb.sql.parser.ColumnDefinitionNode@355d56d5
	type: INTEGER NOT NULL
	name: did
	elementType: null
	[4]:	
	com.foundationdb.sql.parser.ColumnDefinitionNode@2efd552
	type: DATE
	name: date_prod
	elementType: null
	[5]:	
	com.foundationdb.sql.parser.ColumnDefinitionNode@4f9dfbff
	type: VARCHAR(10)
	name: kind
	elementType: null
	[6]:	
	com.foundationdb.sql.parser.ColumnDefinitionNode@d0b4b2f
	type: INTERVAL HOUR TO MINUTE
	name: len
	elementType: null
===========================================================================
full name : films
relative name : films

 - 0 : class com.foundationdb.sql.parser.ConstraintDefinitionNode
name : firstkey

 - Constraint :
constraint name : firstkey
constraint type : PRIMARY_KEY
 - result column : 0 - class : class com.foundationdb.sql.parser.ResultColumn
name : code

 - 1 : class com.foundationdb.sql.parser.ColumnDefinitionNode
name : code
column name : code
type : CHAR(5)
Autoinc_create_or_modify_Start_Increment : 0
AutoincrementIncrement : 0
AutoincrementStart : 0

 - 2 : class com.foundationdb.sql.parser.ColumnDefinitionNode
name : title
column name : title
type : VARCHAR(40) NOT NULL
Autoinc_create_or_modify_Start_Increment : 0
AutoincrementIncrement : 0
AutoincrementStart : 0

 - 3 : class com.foundationdb.sql.parser.ColumnDefinitionNode
name : did
column name : did
type : INTEGER NOT NULL
Autoinc_create_or_modify_Start_Increment : 0
AutoincrementIncrement : 0
AutoincrementStart : 0

 - 4 : class com.foundationdb.sql.parser.ColumnDefinitionNode
name : date_prod
column name : date_prod
type : DATE
Autoinc_create_or_modify_Start_Increment : 0
AutoincrementIncrement : 0
AutoincrementStart : 0

 - 5 : class com.foundationdb.sql.parser.ColumnDefinitionNode
name : kind
column name : kind
type : VARCHAR(10)
Autoinc_create_or_modify_Start_Increment : 0
AutoincrementIncrement : 0
AutoincrementStart : 0

 - 6 : class com.foundationdb.sql.parser.ColumnDefinitionNode
name : len
column name : len
type : INTERVAL HOUR TO MINUTE
Autoinc_create_or_modify_Start_Increment : 0
AutoincrementIncrement : 0
AutoincrementStart : 0
```
