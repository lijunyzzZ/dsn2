package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.domain.AfterDataInstance;
import com.domain.DataInstance;
import com.common.JdbcUtil;

public class DataBase_Dao {
	public void saveDaos(List<DataInstance> datalist) {
		for (DataInstance dataInstance : datalist) {
			saveDao(dataInstance, datalist.size());
		}
	}

	public void saveDao(DataInstance d, int l) {
		List<Object> paramList = new ArrayList<Object>();
		String sql = "INSERT INTO data(LOCUS,DEFINITION,ACCESSION,VERSION,DBLINK,KEYWORDS,SOURCE,ORGANISM,COMMENT,prima,FEATURES,ORIGIN)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		paramList.add(d.getLOCUS());
		paramList.add(d.getDEFINITION());
		paramList.add(d.getACCESSION());
		paramList.add(d.getVERSION());
		paramList.add(d.getDBLINK());
		paramList.add(d.getKEYWORDS());
		paramList.add(d.getSOURCE());
		paramList.add(d.getORGANISM());
		paramList.add(d.getCOMMENT());
		paramList.add(d.getPRIMARY());
		paramList.add(d.getFEATURES());
		paramList.add(d.getORIGIN());
		JdbcUtil jdbcUtil = null;
		boolean bool = false;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
		} catch (SQLException e) {
			System.out.println(this.getClass() + "执行更新操作抛出异常！");
			e.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
		System.out.println("执行更新的结果：" + bool);
	}

	public void saveDaos1(List<DataInstance> datalist) {
		int count = 0;  
		int preCount =1000;
		JdbcUtil jdbcUtil = null;
		PreparedStatement mysqlPs = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection();
			Connection mysqlConn = jdbcUtil.getConnection(); // 获取数据库链接
			String sql = "INSERT INTO data(LOCUS,DEFINITION,ACCESSION,VERSION,DBLINK,KEYWORDS,SOURCE,ORGANISM,COMMENT,prima,FEATURES,ORIGIN)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			mysqlPs = mysqlConn.prepareStatement(sql);
			for(int i = 0;i<datalist.size();i++){
				mysqlPs.setString(1, datalist.get(i).getLOCUS());
				mysqlPs.setString(2, datalist.get(i).getDEFINITION());
				mysqlPs.setString(3, datalist.get(i).getACCESSION());
				mysqlPs.setString(4, datalist.get(i).getVERSION());
				mysqlPs.setString(5, datalist.get(i).getDBLINK());
				mysqlPs.setString(6, datalist.get(i).getKEYWORDS());
				mysqlPs.setString(7, datalist.get(i).getSOURCE());
				mysqlPs.setString(8, datalist.get(i).getORGANISM());
				mysqlPs.setString(9, datalist.get(i).getCOMMENT());
				mysqlPs.setString(10, datalist.get(i).getPRIMARY());
				mysqlPs.setString(11, datalist.get(i).getFEATURES());
				mysqlPs.setString(12, datalist.get(i).getORIGIN());
				mysqlPs.addBatch();
				if ((i % 1000) == 0){  
                    mysqlPs.executeBatch();  
                    System.out.println("当前进行完毕===>" + (++count) * preCount + "条");  
                }  
				if(i == datalist.size()-1){
					 mysqlPs.executeBatch();  
					 System.out.println("当前进行完毕===>" + datalist.size()+ "条");  
				}
			}

		} catch (SQLException e) {
			System.out.println(this.getClass() + "执行更新操作抛出异常！");
			e.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
		// System.out.println("执行更新的结果：" + bool);
	}
	public List<AfterDataInstance> getDao() {
		List<AfterDataInstance> datares = new ArrayList<AfterDataInstance>();
		AfterDataInstance data = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
		JdbcUtil jdbcUtil= null;
		try {
			jdbcUtil = new JdbcUtil();
			conn = jdbcUtil.getConnection();
			sql="SELECT * FROM data";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				data  = new AfterDataInstance();
				data.setId(rs.getString("LOCUS"));
				data.setOrigin((rs.getString("origin")));
				datares.add(data);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datares;
		
	}
	
	@Test
	public void test(){
		List<AfterDataInstance> datares = getDao();
		System.out.println(datares.size());
	}
	
	
}
