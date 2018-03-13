package app.inventory.file.management;

import java.io.Serializable;
import java.util.Date;

public class InventoryItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2916903288641402733L;
	private Double itemno, productno, quantity;
	private String productName;
	private boolean purchaseLock;
	private long timeline;
	private Date purchaseAvailableDate;

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

	public boolean isPurchaseLock() {
		return purchaseLock;
	}

	public void setPurchaseLock(boolean purchaseLock) {
		this.purchaseLock = purchaseLock;
	}

	public Date getPurchaseAvailableDate() {
		return purchaseAvailableDate;
	}

	public void setPurchaseAvailableDate(Date purchaseAvailableDate) {
		this.purchaseAvailableDate = purchaseAvailableDate;
	}

	public long getTimeline() {
		return timeline;
	}

	public void setTimeline(long timeline) {
		this.timeline = timeline;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemno == null) ? 0 : itemno.hashCode());
		result = prime * result + ((productno == null) ? 0 : productno.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		InventoryItem other = (InventoryItem) obj;
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
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InventoryItem [itemno=" + itemno + ", productno=" + productno + ", quantity=" + quantity
				+ ", productName=" + productName + "]";
	}

	public InventoryItem(Double itemno, Double productno, Double quantity, String productName, long timeline) {
		super();
		this.itemno = itemno;
		this.productno = productno;
		this.quantity = quantity;
		this.productName = productName;
		this.timeline = timeline;
	}

}
