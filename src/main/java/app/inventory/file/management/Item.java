package app.inventory.file.management;

import java.io.Serializable;

public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2249365296280214918L;

	private Double itemno, productno,  quantity;

	private String productName, empno,employeename;

	public Double getItemno() {
		return itemno;
	}

	public void setItemno(Double itemno) {
		this.itemno = itemno;
	}

	public Double getProductno() {
		return productno;
	}

	public void setProductno(Double productno) {
		this.productno = productno;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	@Override
	public String toString() {
		return "Item [itemno=" + itemno + ", productno=" + productno + ", empno=" + empno + ", quantity=" + quantity
				+ ", productName=" + productName + ", employeename=" + employeename + "]";
	}


}
