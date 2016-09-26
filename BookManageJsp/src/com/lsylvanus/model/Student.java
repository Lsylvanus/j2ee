package com.lsylvanus.model;


/**
 * 学生Model 继承BaseModel
 */
public class Student extends BaseModel {

	private String name;
	private String department;
	private int cardNumber;
	
	/**
	 * 获取卡号
	 * @return 卡号
	 */
	public int getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * 获取院系
	 * @return 院系信息
	 */
	public String getDepartment() {
		return department;
	}
	
	/**
	 * 获取姓名
	 * @return 姓名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置卡号
	 * @param cardNumber 卡号
	 */
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/**
	 * 设置院系信息
	 * @param department 院系信息
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
