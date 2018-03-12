package app.inventory.file.management;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ItemFIleProcessor {

	private final String filePath;

	public ItemFIleProcessor(String filepath) {
		this.filePath = filepath;
	}

	private void fileParse() throws Exception {

		FileInputStream myStream = new FileInputStream(new File(filePath)); // Declare a input stream and pass myFile in
		HSSFWorkbook myWB = new HSSFWorkbook(myStream); // Declare work book and pass myStream in it
		HSSFSheet mySheet = myWB.getSheetAt(0); // Go to the sheet at index 0 i.e. First One

		int xRows = mySheet.getLastRowNum() + 1; // Get rows number
		int xCols = mySheet.getRow(0).getLastCellNum(); // Get column number

		System.out.println("Number of rows are : " + xRows);
		System.out.println("Number of columns are : " + xCols);

		String[][] xData = new String[xRows][xCols]; // Declare an array of string type which will hold values

		for (int i = 1; i < xRows; i++) {
			HSSFRow row = mySheet.getRow(i); // Pointing to the row from we need to read data; it will be first row at
												// first iteration
			Item item = new Item();
			for (int j = 0; j < xCols; j++) {
				HSSFCell cell = row.getCell(j); // Pointing to the cell of the row

				String value = cellToString(cell); // Getting value of the cell and put it into a variable "value"; Here
													// cellToString() is a user define function which will convert cell
													// value in string.

				xData[i][j] = value; // Store that value in to array
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
				default:
					break;
				}

				System.out.print("\t");
			}
			System.out.println("@###" + item);
			System.out.println("");
		}
	}

	private XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		ContentHandler handler = new SheetHandler(sst);
		parser.setContentHandler(handler);
		return parser;
	}

	public String cellToString(HSSFCell cell) {// Declare a method "cellToString()" which will convert an object of type
												// cell into string
		int type = cell.getCellType();// This method "getCellType()" will return an integer which is 0-5 and on the
										// basis of this integer value we will use SWITCH

		Object result; // Its an temporary object which will hold value in switch statement

		switch (type) {

		case HSSFCell.CELL_TYPE_NUMERIC: // If a cell contain numeric value then it will return 0
			result = cell.getNumericCellValue();
			break;

		case HSSFCell.CELL_TYPE_STRING: // If a cell string value then it will return 1
			result = cell.getStringCellValue();
			break;

		case HSSFCell.CELL_TYPE_FORMULA: // If a cell contain formula then it will return 2
			throw new RuntimeException("Can not eveulate formila in JAVA");

		case HSSFCell.CELL_TYPE_BLANK: // If a cell contain blank value then it will return 3
			result = "";
			break;

		case HSSFCell.CELL_TYPE_BOOLEAN: // If a cell contain boolean value then it will return 4
			result = cell.getBooleanCellValue();
			break;

		case HSSFCell.CELL_TYPE_ERROR:// If a cell contain error then it will return 5
			throw new RuntimeException("This cell has an error");

		default:
			throw new RuntimeException("We dont support this cell type : " + type);
		}
		return result.toString();

	}

	private void objectLoader() {

	}

	public void execute() throws Exception {
		fileParse();
	}

	public static void main(String[] args) {
		ItemFIleProcessor ifp = new ItemFIleProcessor("C:/Users/kama0717/Downloads/MedicalBills.xls");
		try {
			ifp.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
