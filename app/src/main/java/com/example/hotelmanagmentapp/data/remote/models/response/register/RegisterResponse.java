package com.example.hotelmanagmentapp.data.remote.models.response.register;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse{

	@SerializedName("code")
	private String code;

	@SerializedName("data")
	private String data;

	@SerializedName("messageCh")
	private String messageCh;

	@SerializedName("message")
	private String message;

	@SerializedName("messageKh")
	private String messageKh;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setData(String data){
		this.data = data;
	}

	public String getData(){
		return data;
	}

	public void setMessageCh(String messageCh){
		this.messageCh = messageCh;
	}

	public String getMessageCh(){
		return messageCh;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setMessageKh(String messageKh){
		this.messageKh = messageKh;
	}

	public String getMessageKh(){
		return messageKh;
	}

	@Override
 	public String toString(){
		return 
			"RegisterResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",messageCh = '" + messageCh + '\'' + 
			",message = '" + message + '\'' + 
			",messageKh = '" + messageKh + '\'' + 
			"}";
		}
}