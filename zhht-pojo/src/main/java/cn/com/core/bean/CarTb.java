package cn.com.core.bean;

import java.io.Serializable;

import javax.xml.crypto.Data;

public class CarTb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String name;
	
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public CarTb(String name,String password){
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "TestTb [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
