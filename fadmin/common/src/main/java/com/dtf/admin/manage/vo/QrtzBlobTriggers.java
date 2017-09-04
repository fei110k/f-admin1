
package com.dtf.admin.manage.vo;

import java.io.Serializable;

public class QrtzBlobTriggers implements Serializable{
	
	private String sched_name;
	private String trigger_name;
	private String trigger_group;
	private String blob_data;


	public String getSched_name(){
		return sched_name;
	}

	public void setSched_name(String sched_name){
		this.sched_name = sched_name;
	}

	public String getTrigger_name(){
		return trigger_name;
	}

	public void setTrigger_name(String trigger_name){
		this.trigger_name = trigger_name;
	}

	public String getTrigger_group(){
		return trigger_group;
	}

	public void setTrigger_group(String trigger_group){
		this.trigger_group = trigger_group;
	}

	public String getBlob_data(){
		return blob_data;
	}

	public void setBlob_data(String blob_data){
		this.blob_data = blob_data;
	}


}