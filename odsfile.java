package reference;

import java.io.File;
import java.io.IOException;


import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Table;

public class odsfile {

	public static void main(String[] args) throws IOException {

		try {
			// load the file.
			File ods_file = new File("C:\\Users\\tiger\\Desktop\\odf\\br171_result.ods");
			if (ods_file.exists()) {
				SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(ods_file);
				//beging from zero
				Table activetable = spreadsheetDocument.getSheetByIndex(0);
				//A6 之cell value,format : uppercase+noumber
				Cell cell = activetable.getCellByPosition("C35");
				//Cell cell = activetable.getCellByPosition(1,5);
								
				System.out.println("cell.getDisplayText()="+cell.getDisplayText());
				System.out.println("cell.getCellStyleName()="+cell.getCellStyleName());
				System.out.println(activetable.getTableName());
				System.out.println("activetable.getHeaderColumnCount()="+activetable.getHeaderColumnCount());
				System.out.println("activetable.getColumnCount()="+activetable.getColumnCount());
				System.out.println("activetable.getRowCount()="+activetable.getRowCount());
						
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
