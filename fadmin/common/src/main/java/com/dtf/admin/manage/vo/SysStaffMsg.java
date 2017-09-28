
package com.dtf.admin.manage.vo;

import java.io.Serializable;

public class SysStaffMsg implements Serializable{
	
	//用户消息ID
	private String msg_id;
	//接收消息用户ID
	private String receive_staff_id;
	//发送消息用户ID
	private String send_staff_id;
	//消息标题
	private String msg_title;
	//消息内容，如果有URL什么的，也在内容里边处理
	private String msg_content;
	//消息类型：SYS_STAFF_MSG_TYPE
	private String msg_type;
	//接收时间
	private String receive_time;
	//消息状态：SYS_STAFF_MSG_STATE
	private String msg_state;
	//消息状态时间
	private String msg_state_time;
	//是否置顶：SYS_STAFF_MSG_IS_TOP
	private String is_top;


	public String getMsg_id(){
		return msg_id;
	}

	public void setMsg_id(String msg_id){
		this.msg_id = msg_id;
	}

	public String getReceive_staff_id(){
		return receive_staff_id;
	}

	public void setReceive_staff_id(String receive_staff_id){
		this.receive_staff_id = receive_staff_id;
	}

	public String getSend_staff_id(){
		return send_staff_id;
	}

	public void setSend_staff_id(String send_staff_id){
		this.send_staff_id = send_staff_id;
	}

	public String getMsg_title(){
		return msg_title;
	}

	public void setMsg_title(String msg_title){
		this.msg_title = msg_title;
	}

	public String getMsg_content(){
		return msg_content;
	}

	public void setMsg_content(String msg_content){
		this.msg_content = msg_content;
	}

	public String getMsg_type(){
		return msg_type;
	}

	public void setMsg_type(String msg_type){
		this.msg_type = msg_type;
	}

	public String getReceive_time(){
		return receive_time;
	}

	public void setReceive_time(String receive_time){
		this.receive_time = receive_time;
	}

	public String getMsg_state(){
		return msg_state;
	}

	public void setMsg_state(String msg_state){
		this.msg_state = msg_state;
	}

	public String getMsg_state_time(){
		return msg_state_time;
	}

	public void setMsg_state_time(String msg_state_time){
		this.msg_state_time = msg_state_time;
	}

	public String getIs_top(){
		return is_top;
	}

	public void setIs_top(String is_top){
		this.is_top = is_top;
	}


}