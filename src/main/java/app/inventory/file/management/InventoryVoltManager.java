package app.inventory.file.management;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public final class InventoryVoltManager {

	private Set<InventoryItem> volt;

	private static double throttlepoint = 10;

	private void loadVolt() {
		volt = new HashSet<>();
		volt.add(new InventoryItem(1.0, 112.0, 5.0, "IBM laptop", 2l));
		volt.add(new InventoryItem(2.0, 113.0, 100.0, "mouse", 1l));
		volt.add(new InventoryItem(3.0, 114.0, 90.0, "IBM laptop Charger", 1l));
	}

	private InventoryVoltManager() {
		loadVolt();
	}

	public static synchronized InventoryVoltManager getinstance() {
		return new InventoryVoltManager();
	}

	public boolean checkAvilability(Double itemNo, Double quantity) {
		checkMinAvilability();
		for (InventoryItem each : volt) {
			if (itemNo.equals(each.getItemno())) {
				if (quantity < each.getQuantity()) {
					return true;
				}
			}
		}
		return false;
	}

	public Date fetch(Double itemNo, Double quantity, Date orderendDate) {
		Date dt = null;
		for (InventoryItem each : volt) {
			if (itemNo.equals(each.getItemno())) {
				if (each.isPurchaseLock()) {
					int newDate = (int) (each.getPurchaseAvailableDate().getDate() + each.getTimeline());
					orderendDate.setDate(newDate);
					return orderendDate;
				}
				each.setQuantity(each.getQuantity() - quantity);
				int newDate = (int) (orderendDate.getDate() + each.getTimeline());
				orderendDate.setDate(newDate);
				return orderendDate;
			}
		}
		return dt;
	}

	public void voltStatus() {
		for (InventoryItem item : volt) {
			System.out.println(item);
		}
	}

	/**
	 * Schedule this method every 5 min
	 */
	public void checkMinAvilability() {
		for (InventoryItem each : volt) {
			if (each.getQuantity() < throttlepoint) {
				// raise purchase Order;
				// set next availDate
				Date dt = new Date();
				dt.setDate(dt.getDay() + 7);
				each.setPurchaseAvailableDate(dt);
			} else if (each.getQuantity() < 2) {
				each.setPurchaseLock(true);
			}
		}
	}

}
