package com.lsylvanus.model;


/**
 * �鼮Model �̳�BaseModel
 */
public class Book extends BaseModel {

	private String classify;
	private int cardNumber;
	private String name;
	private int stock;
	private int AStrongerStock;
	
	public int getAStrongerStock() {
		return AStrongerStock;
	}

	/**
	 * ��ȡͼ����
	 * @return ͼ����
	 */
	public int getCardNumber() {
		return cardNumber;
	}

	/**
	 * ��ȡͼ�����
	 * @return ͼ�����
	 */
	public String getClassify() {
		return classify;
	}

	/**
	 * ��ȡͼ������
	 * @return ͼ������
	 */
	public String getName() {
		return name;
	}

	public int getStock() {
		return stock;
	}
	
	public void setAStrongerStock(int aStrongerStock) {
		AStrongerStock = aStrongerStock;
	}
	
	/**
	 * ����ͼ����
	 * @param cardNumber ͼ����
	 */
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/**
	 * ����ͼ�����
	 * @param classify ͼ�����
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	/**
	 * ����ͼ������
	 * @param name ͼ������
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
}
