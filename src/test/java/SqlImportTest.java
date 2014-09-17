

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import parser.SqlImport;
import util.Util;

import com.foundationdb.sql.parser.StatementNode;

/**
 * Test : SQL import.
 */
public class SqlImportTest {

	private SqlImport sqlImport = new SqlImport();
	private Util util = new Util();

	@Test
	public void testRead_nofile() {

		final InputStream is = null;

		// When
		final List<StatementNode> stmts = sqlImport.read(is);

		assertNull(stmts);
	}

	@Test
	public void testRead_standard() throws FileNotFoundException {
		// Given
		final File file = util.getFileByClassPath("/standard.sql");
		final InputStream in = new FileInputStream(file);

		// When
		final List<String> querys = sqlImport.getSqlQuerys(in);
		assertEquals(5, querys.size());

		final List<StatementNode> stmts = sqlImport.read(querys);
		assertEquals(5, stmts.size());
	}

	@Test
	public void testRead_mysql() throws FileNotFoundException {
		// Given
		final File file = util.getFileByClassPath("/mysql.sql");
		final InputStream in = new FileInputStream(file);

		// When
		final List<String> querys = sqlImport.getSqlQuerys(in);
		assertEquals(7, querys.size());

		final List<StatementNode> stmts = sqlImport.read(querys);
		assertEquals(7, stmts.size());
	}

	@Test
	public void testRead_postgres() throws FileNotFoundException {
		// Given
		final File file = util.getFileByClassPath("/postgres.sql");
		final InputStream in = new FileInputStream(file);

		// When
		final List<String> querys = sqlImport.getSqlQuerys(in);
		assertEquals(9, querys.size());

		final List<StatementNode> stmts = sqlImport.read(querys);
		assertEquals(9, stmts.size());
	}

	@Test
	public void testRead_oracle1() throws FileNotFoundException {
		// Given
		final File file = util.getFileByClassPath("/oracle1.sql");
		final InputStream in = new FileInputStream(file);

		// When
		final List<String> querys = sqlImport.getSqlQuerys(in);
		assertEquals(15, querys.size());

		final List<StatementNode> stmts = sqlImport.read(querys);
		assertEquals(9, stmts.size());
	}

	@Test
	public void testRead_oracle2() throws FileNotFoundException {
		// Given
		final File file = util.getFileByClassPath("/oracle2.sql");
		final InputStream in = new FileInputStream(file);

		// When
		final List<String> querys = sqlImport.getSqlQuerys(in);
		assertEquals(3, querys.size());

		final List<StatementNode> stmts = sqlImport.read(querys);
		assertEquals(3, stmts.size());
	}

}
