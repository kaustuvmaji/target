package app.inventory.file.management;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2249365296280214918L;

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private Double itemno, productno, quantity;

	private String productName, empno, employeename;

	private Date edate;

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

	public Date getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		if (null != edate) {
			try {
				this.edate = dateFormat.parse(edate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "Item [itemno=" + itemno + ", productno=" + productno + ", quantity=" + quantity + ", productName="
				+ productName + ", empno=" + empno + ", employeename=" + employeename + ", edate=" + edate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edate == null) ? 0 : edate.hashCode());
		result = prime * result + ((empno == null) ? 0 : empno.hashCode());
		result = prime * result + ((itemno == null) ? 0 : itemno.hashCode());
		result = prime * result + ((productno == null) ? 0 : productno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (edate == null) {
			if (other.edate != null)
				return false;
		} else if (!edate.equals(other.edate))
			return false;
		if (empno == null) {
			if (other.empno != null)
				return false;
		} else if (!empno.equals(other.empno))
			return false;
		if (itemno == null) {
			if (other.itemno != null)
				return false;
		} else if (!itemno.equals(other.itemno))
			return false;
		if (productno == null) {
			if (other.productno != null)
				return false;
		} else if (!productno.equals(other.productno))
			return false;
		return true;
	}

}
