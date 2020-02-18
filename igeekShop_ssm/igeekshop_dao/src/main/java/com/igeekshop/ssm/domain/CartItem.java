package com.igeekshop.ssm.domain;

public class CartItem {
private Product product;
private int buyNum;
private double subTotal;
private  String pid;
private  String oid;
public CartItem(Product product, int buyNum, double subTotal,String pid) {
	super();
	this.product = product;
	this.buyNum = buyNum;
	this.subTotal = subTotal;
	this.pid=pid;
}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public int getBuyNum() {
	return buyNum;
}
public void setBuyNum(int buyNum) {
	this.buyNum = buyNum;
}
public double getSubTotal() {
	return subTotal;
}
public void setSubTotal(double subTotal) {
	this.subTotal = subTotal;
}
@Override
public String toString() {
	return "CartItem [product=" + product + ", buyNum=" + buyNum + ", subTotal=" + subTotal + "]";
}

}
