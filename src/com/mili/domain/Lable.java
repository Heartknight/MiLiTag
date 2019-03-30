package com.mili.domain;

import java.sql.Date;

public class Lable {

	//字段
	private int 	lnuo;
	private int 	l_uid;
	private String 	ltitle;
	private String 	lContent;
	private Date 	ltime;
	private boolean stick;
	
	//构造方法
	public Lable() {}
		
	//属性
	public boolean 	isStick() {
		return stick;
	}
	public void 	setStick(boolean stick) {
		this.stick = stick;
	}
	public int 		getLnuo() {
		return lnuo;
	}
	public void 	setLnuo(int lnuo) {
		this.lnuo = lnuo;
	}
	public int 		getL_uid() {
		return l_uid;
	}
	public void 	setL_uid(int l_uid) {
		this.l_uid = l_uid;
	}
	public String 	getLtitle() {
		return ltitle;
	}
	public void 	setLtitle(String ltitle) {
		this.ltitle = ltitle;
	}
	public String 	getlContent() {
		return lContent;
	}
	public void 	setlContent(String lContent) {
		this.lContent = lContent;
	}
	public Date 	getLtime() {
		return ltime;
	}
	public void 	setLtime(Date ltime) {
		this.ltime = ltime;
	}
}
