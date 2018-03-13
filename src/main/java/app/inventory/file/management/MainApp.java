package app.inventory.file.management;

public class MainApp {
	
	public static void main(String[] args) {
		try {
			ItemFIleProcessor ifp = new ItemFIleProcessor("C:/Users/kama0717/Desktop/TargetUseCase2.xls");
			OrderItemProcessor oip = new OrderItemProcessor();
			for (OrderItem item : ifp.execute()) {
				oip.process(item);
			}
			oip.im.voltStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
