package com.lsylvanus.model;


/**
 * 书籍Model 继承BaseModel
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
	 * 获取图书编号
	 * @return 图书编号
	 */
	public int getCardNumber() {
		return cardNumber;
	}

	/**
	 * 获取图书分类
	 * @return 图书分类
	 */
	public String getClassify() {
		return classify;
	}

	/**
	 * 获取图书名称
	 * @return 图书名称
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
	 * 设置图书编号
	 * @param cardNumber 图书编号
	 */
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/**
	 * 设置图书分类
	 * @param classify 图书分类
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	/**
	 * 设置图书名称
	 * @param name 图书名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
}
