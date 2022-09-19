package my_tool;

import java.io.File;
import java.util.ArrayList;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.CellRange;
import org.odftoolkit.simple.table.Table;

public class Access_ods {
	private File ods_file;

	public File getods_file() {
		return ods_file;
	}

	public void setods_file(File ods_file) {
		this.ods_file = ods_file;
	}

	public Access_ods() {
	}

	public void print_base_info() throws Exception {
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(getods_file());
		Table activetable = spreadsheetDocument.getSheetByIndex(0);
		System.out.println("spreadsheetDocument.getTableList().size()=" + spreadsheetDocument.getTableList().size());
		System.out.println("activetable_name= " + activetable.getTableName());
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

	public void print_all_cell() throws Exception {
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

	public ArrayList<Cell> all_data(int index_of_shhet) throws Exception {
		File file = getods_file();
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(file);
		Table activetable = spreadsheetDocument.getSheetByIndex(index_of_shhet);
		ArrayList<Cell> arrayList = new ArrayList<Cell>();
		for (int i = 0, j = 0; i < activetable.getColumnCount() || j < activetable.getRowCount(); i++, j++) {
			Cell cell = activetable.getCellByPosition(i, j);
			// System.out.println("cell.getDisplayText()=" + cell.getDisplayText());
			arrayList.add(cell);
		}
		return arrayList;
	}

	public ArrayList<Cell> all_data() throws Exception {
		File file = getods_file();
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(file);
		Table activetable = spreadsheetDocument.getSheetByIndex(0);
		ArrayList<Cell> arrayList = new ArrayList<Cell>();
		for (int i = 0, j = 0; i < activetable.getColumnCount() || j < activetable.getRowCount(); i++, j++) {
			Cell cell = activetable.getCellByPosition(i, j);
			// System.out.println("cell.getDisplayText()=" + cell.getDisplayText());
			arrayList.add(cell);
		}
		return arrayList;
	}

	public CellRange cellrange_data(int startCol, int startRow, int endCol, int endRow) throws Exception {
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(getods_file());
		Table activetable = spreadsheetDocument.getSheetByIndex(0);

		return activetable.getCellRangeByPosition(startCol, startRow, endCol, endRow);
	}

	public CellRange cellrange_data(int startCol, int startRow, int endCol, int endRow, int index_of_sheet)
			throws Exception {
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(getods_file());
		Table activetable = spreadsheetDocument.getSheetByIndex(index_of_sheet);

		return activetable.getCellRangeByPosition(startCol, startRow, endCol, endRow);
	}

	public SpreadsheetDocument spreadsheetDocument_object() throws Exception {
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(getods_file());
		return spreadsheetDocument;
	}

	public void addtable(boolean isremove_first_sheet, File output_file, Table referenceTable,
			String name_of_new_sheet) throws Exception {

		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(getods_file());
		spreadsheetDocument.appendSheet(referenceTable, name_of_new_sheet);
		if (isremove_first_sheet) {
			spreadsheetDocument.removeSheet(0);
		}
		spreadsheetDocument.save(output_file);
	}

	public void addtable(boolean isremove_first_sheet, String output_file_string, Table referenceTable,
			String name_of_new_sheet) throws Exception {
		SpreadsheetDocument spreadsheetDocument = SpreadsheetDocument.loadDocument(getods_file());
		File output_file = new File(output_file_string);
		spreadsheetDocument.appendSheet(referenceTable, name_of_new_sheet);
		if (isremove_first_sheet) {
			spreadsheetDocument.removeSheet(0);
		}
		spreadsheetDocument.save(output_file);
	}

}
