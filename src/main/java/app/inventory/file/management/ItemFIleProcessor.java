package app.inventory.file.management;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ItemFIleProcessor {

	private final String filePath;
	private static Set<OrderItem> itemBag;

	public ItemFIleProcessor(String filepath) {
		this.filePath = filepath;
	}

	private void fileParse() throws Exception {
		FileInputStream myStream = new FileInputStream(new File(filePath));
		@SuppressWarnings("resource")
		HSSFWorkbook myWB = new HSSFWorkbook(myStream);
		HSSFSheet mySheet = myWB.getSheetAt(0);
		int xRows = mySheet.getLastRowNum() + 1;
		int xCols = mySheet.getRow(0).getLastCellNum();
		System.out.println("Number of rows are : " + xRows);
		System.out.println("Number of columns are : " + xCols);

		String[][] xData = new String[xRows][xCols];
		itemBag = new HashSet<>();
		for (int i = 1; i < xRows; i++) {
			HSSFRow row = mySheet.getRow(i);
			OrderItem item = new OrderItem();
			for (int j = 0; j < xCols; j++) {
				HSSFCell cell = row.getCell(j);
				String value = cellToString(cell);
				xData[i][j] = value;
				switch (j) {
				case 0:
					item.setItemno(Double.parseDouble(xData[i][j]));
					break;
				case 1:
					item.setProductName(xData[i][j]);
					break;
				case 2:
					item.setEmployeename(xData[i][j]);
					break;
				case 3:
					item.setEmpno(xData[i][j]);
					break;
				case 4:
					item.setQuantity(Double.parseDouble(xData[i][j]));
					break;
				case 5:
					item.setEdate(xData[i][j]);
					break;
				default:
					break;
				}
			}
			itemBag.add(item);
		}
	}

	@SuppressWarnings("deprecation")
	public String cellToString(HSSFCell cell) {
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			result = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			result = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We dont support this cell type : " + type);
		}
		return result.toString();
	}

	public Set<OrderItem> execute() throws Exception {
		fileParse();
		return itemBag;
	}
}
