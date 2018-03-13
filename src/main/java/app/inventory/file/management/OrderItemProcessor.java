package app.inventory.file.management;

public final class OrderItemProcessor {

	InventoryVoltManager im = InventoryVoltManager.getinstance();

	public void process(OrderItem orderItem) {
		if (im.checkAvilability(orderItem.getItemno(), orderItem.getQuantity())) {
			System.out.println(im.fetch(orderItem.getItemno(), orderItem.getQuantity(), orderItem.getEdate()));
		}
	}
}
