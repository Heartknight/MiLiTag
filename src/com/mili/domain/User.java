package com.mili.domain;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mili.util.C3P0Utils;

public class User {

	private int u_id;
	private String u_tel;
	private String u_pwd;
	private String u_name;

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getU_tel() {
		return u_tel;
	}

	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}

	public String getU_pwd() {
		return u_pwd;
	}

	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	// 对外的公开大的方法啊
	/**
	 * 判断密码是否正确
	 * 
	 * @param u_pwd
	 * @return
	 */
	public boolean IsPwd(String u_pwd) {
		// System.out.println(u_pwd+this.u_pwd);
		if (u_pwd.trim().equals(this.u_pwd.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取便签的集合
	 * 
	 * @return
	 */
	public List<Lable> getLables() {
		try {
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			String sql = "SELECT * FROM lable WHERE lable.l_uid = ? ORDER BY lable.stick DESC,lable.ltime DESC;";
			// Object[] params = {this.u_id};
			// System.out.println(params);
			List<Lable> lables = qr.query(sql, new BeanListHandler<Lable>(Lable.class), this.u_id);
			
			 for(Lable l : lables) { System.out.println(l.getLtitle());}
			  
			 
			return lables;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
