package com.lsylvanus.model;


/**
 * ѧ��Model �̳�BaseModel
 */
public class Student extends BaseModel {

	private String name;
	private String department;
	private int cardNumber;
	
	/**
	 * ��ȡ����
	 * @return ����
	 */
	public int getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * ��ȡԺϵ
	 * @return Ժϵ��Ϣ
	 */
	public String getDepartment() {
		return department;
	}
	
	/**
	 * ��ȡ����
	 * @return ����
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * ���ÿ���
	 * @param cardNumber ����
	 */
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/**
	 * ����Ժϵ��Ϣ
	 * @param department Ժϵ��Ϣ
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * ��������
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
