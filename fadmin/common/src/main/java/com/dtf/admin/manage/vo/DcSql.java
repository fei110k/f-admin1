
package com.dtf.admin.manage.vo;

import java.io.Serializable;

public class DcSql implements Serializable{
	
	private String dc_name;
	private String dc_sql;
	private String dc_desc;


	public String getDc_name(){
		return dc_name;
	}

	public void setDc_name(String dc_name){
		this.dc_name = dc_name;
	}

	public String getDc_sql(){
		return dc_sql;
	}

	public void setDc_sql(String dc_sql){
		this.dc_sql = dc_sql;
	}

	public String getDc_desc(){
		return dc_desc;
	}

	public void setDc_desc(String dc_desc){
		this.dc_desc = dc_desc;
	}


}