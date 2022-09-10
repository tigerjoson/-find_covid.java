package my_tool;

import java.io.File;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Table;

public class Printinfo_ods {
	private File ods_file;

	public File getods_file() {
		return ods_file;
	}

	public void setods_file(File ods_file) {
		this.ods_file = ods_file;
	}

	public Printinfo_ods()  {}
		
	public void print_base_info() throws Exception {
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(getods_file());
		Table activetable = spreadsheetDocument.getSheetByIndex(0);
		System.out.println("spreadsheetDocument.getTableList().size()="+spreadsheetDocument.getTableList().size());
		System.out.println("activetable_name= "+activetable.getTableName());
		System.out.println("activetable.getHeaderColumnCount()=" + activetable.getHeaderColumnCount());
		System.out.println("activetable.getColumnCount()=" + activetable.getColumnCount());
		System.out.println("activetable.getRowCount()=" + activetable.getRowCount());
		
	}

	public void print_all_cell(int Max_value_want_to_see) throws Exception {
		if (getods_file().exists()) {
			SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(getods_file());
			// beging from zero
			Table activetable = spreadsheetDocument.getSheetByIndex(0);
			if (Max_value_want_to_see > activetable.getColumnCount()
					&& Max_value_want_to_see > activetable.getRowCount()) {
				for (int i = 0, j = 0; i < activetable.getColumnCount() || j < activetable.getRowCount(); i++, j++) {
					Cell cell = activetable.getCellByPosition(i, j);
					System.out.println("cell.getDisplayText()=" + cell.getDisplayText());
				}
			} else {
				System.out.println(activetable.getTableName());
				System.out.println("activetable.getHeaderColumnCount()=" + activetable.getHeaderColumnCount());
				System.out.println("activetable.getColumnCount()=" + activetable.getColumnCount());
				System.out.println("activetable.getRowCount()=" + activetable.getRowCount());
			}

		} else {
			System.out.println("ods_file.exists()=" + getods_file().exists());
		}
	}

	public  void print_all_cell() throws Exception {
		if (getods_file().exists()) {
			
			SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(getods_file());
			// beging from zero
			Table activetable = spreadsheetDocument.getSheetByIndex(0);

			for (int i = 0, j = 0; i < activetable.getColumnCount() || j < activetable.getRowCount(); i++, j++) {
				Cell cell = activetable.getCellByPosition(i, j);
				System.out.println("cell.getDisplayText()=" + cell.getDisplayText());
			}
			System.out.println(activetable.getTableName());
			System.out.println("activetable.getHeaderColumnCount()=" + activetable.getHeaderColumnCount());
			System.out.println("activetable.getColumnCount()=" + activetable.getColumnCount());
			System.out.println("activetable.getRowCount()=" + activetable.getRowCount());

		} else {
			System.out.println("ods_file.exists()=" + getods_file().exists());
		}
	}

}
