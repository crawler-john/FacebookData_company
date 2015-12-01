package com.db.manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class tesetsql {

	private static final String POOLNAME = "proxool.FaceBook";

	// 查询未获得schoolname_facebook的学校
	public List<String> getEmptySchoolNameList() {
		DBServer dbServer = new DBServer(POOLNAME);
		List<String> list = new ArrayList<String>();
		try {
			String sql = "(select id,schoolName,count(schoolName) from t_company t2  group by schoolName having count(schoolName) > 1)";
			ResultSet rs = dbServer.select(sql);
			while (rs.next()) {
//				System.out.println(rs.getString("schoolName"));
				list.add(rs.getString("schoolName"));
				
			
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();

		}
		return list;
	}
	
	public void deleteSchoolNameList(String name) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "SELECT id from t_company where schoolName='"+name+"'";
			System.out.println(sql);
			ResultSet rs = dbServer.select(sql);
			rs.next();
			while (rs.next()) {
				
				System.out.println(rs.getString("id"));
				delete(rs.getString("id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();

		}
		
	}
	
	
	public void delete(String  id) {
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			dbServer.delete("delete from t_company where id ="+id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}
	public static void main(String[] args) {
		tesetsql tesetsql =  new tesetsql();
		List<String> list = tesetsql.getEmptySchoolNameList();
		System.out.println(111);
		for(String string : list){
			System.out.println(string);
			tesetsql.deleteSchoolNameList(string);
		}
	}
	
}
