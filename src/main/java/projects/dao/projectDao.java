package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import projects.entity.projects;
import projects.exception.DbException;
import provided.util.DaoBase;

public class projectDao extends DaoBase {
	
	private static final String CATEGORY_TABLE =" category";
	private static final String MATERIAL_TABLE =" category";
	private static final String PROJECT_TABLE =" project";
	private static final String PROJECT_CATEGORY_TABLE =" project_category";
	private static final String STEP_TABLE =" step";
	
	public static projects insertProject(projects projects) {
		//@formatter:off
		String sql= " "
				+"INSERT INTO" + PROJECT_TABLE+ " "
				+"(project_name,estimated_hours,actual_hours,difficulty,notes)"
				+"VALUES"
				+"(? , ?, ?, ?, ?)";
		//@formatter:on		
		
		try(Connection conn =DbConnection.getConnection()){
			startTransaction(conn);
		
			try(PreparedStatement stmt =conn.prepareStatement(sql)){
				setParameter(stmt, 1, projects.getProjectName(), String.class);
			    setParameter(stmt, 2, projects.getEstimatedHours(), BigDecimal.class);
			    setParameter(stmt, 3, projects.getActualHours(), BigDecimal.class);
			    setParameter(stmt, 4, projects.getDifficulty(), Integer.class);
			    setParameter(stmt, 5, projects.getNotes(), String.class);
			    
			    stmt.executeUpdate();
				
				Integer projectID= getLastInsertId(conn, PROJECT_TABLE);
				commitTransaction(conn);
				projects.setProjectID(projectID);
				return projects;
			}
			catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
		throw new DbException(e);
		}
	}
	public void executeBatch(List<String> sqlBatch) {
	try(Connection conn =DbConnection.getConnection()){
		startTransaction(conn);
	try(Statement stmt=conn.createStatement()){
			for(String sql: sqlBatch) {
					stmt.addBatch(sql);
				}
			stmt.executeBatch();
			commitTransaction(conn);
	}catch(Exception e) {
		rollbackTransaction(conn);
		throw new DbException(e);
		
	}
	} catch (SQLException e1) {
		throw new DbException(e1);
		
	}
	}
}
	





















