/************************************************************************
 *
 * Copyright 2009 J David Eisenberg All rights reserved.
 *
 * Uses ODF Toolkit which is Copyright 2008 Sun Microsystems, Inc.
 * All rights reserved.
 *
 * Use is subject to license terms.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0. You can also
 * obtain a copy of the License at http://odftoolkit.org/docs/license.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ************************************************************************/
package reference;

import java.io.BufferedReader;
import java.io.FileReader;
import org.odftoolkit.odfdom.OdfFileDom;

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.number.OdfNumberDateStyle;
import org.odftoolkit.odfdom.doc.number.OdfNumberStyle;
import org.odftoolkit.odfdom.doc.number.OdfNumberTimeStyle;
import org.odftoolkit.odfdom.doc.office.OdfOfficeAutomaticStyles;
import org.odftoolkit.odfdom.doc.office.OdfOfficeSpreadsheet;
import org.odftoolkit.odfdom.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.doc.style.OdfStyleParagraphProperties;
import org.odftoolkit.odfdom.doc.style.OdfStyle;
import org.odftoolkit.odfdom.doc.style.OdfStyleTableColumnProperties;
import org.odftoolkit.odfdom.doc.style.OdfStyleTableRowProperties;
import org.odftoolkit.odfdom.doc.style.OdfStyleTextProperties;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableColumn;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.odftoolkit.odfdom.doc.text.OdfTextParagraph;
import org.odftoolkit.odfdom.dom.attribute.office.OfficeValueTypeAttribute;
import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.w3c.dom.Node;

/**
 *
 * @author J David Eisenberg
 */
public class SimpleOds {

	String inputFileName;
	String outputFileName;
	OdfSpreadsheetDocument outputDocument;
	OdfFileDom contentDom; // the document object model for content.xml
	OdfFileDom stylesDom; // the document object model for styles.xml
	// the office:automatic-styles element in content.xml
	OdfOfficeAutomaticStyles contentAutoStyles;
	// the office:styles element in styles.xml
	OdfOfficeStyles stylesOfficeStyles;
	// Save the automatically generated style names
	String columnStyleName;
	String rowStyleName;
	String headingStyleName;
	String noaaTimeStyleName;
	String noaaDateStyleName;
	String noaaTempStyleName;
	// the office:spreadsheet element in the content.xml file
	OdfOfficeSpreadsheet officeSpreadsheet;

	public void run(String[] args) {
		if (args.length == 2) {
			inputFileName = args[0];
			outputFileName = args[1];

			setupOutputDocument();

			if (outputDocument != null) {
				cleanOutDocument();
				addAutomaticStyles();
				processInputDocument();
				saveOutputDocument();
			}
		} else {
			System.err.println("Usage: SimpleOds infile outfile");
		}
	}

	void setupOutputDocument() {
		try {
			outputDocument = OdfSpreadsheetDocument.newSpreadsheetDocument();
			contentDom = outputDocument.getContentDom();
			stylesDom = outputDocument.getStylesDom();
			contentAutoStyles = contentDom.getOrCreateAutomaticStyles();
			stylesOfficeStyles = outputDocument.getOrCreateDocumentStyles();
			officeSpreadsheet = outputDocument.getContentRoot();
		} catch (Exception e) {
			System.err.println("Unable to create output file.");
			System.err.println(e.getMessage());
			outputDocument = null;
		}
	}

	/*
	 * The default document has some content in it already (in the case of a text
	 * document, a <text:p>. Clean out all the old stuff.
	 */
	void cleanOutDocument() {
		Node childNode;

		childNode = officeSpreadsheet.getFirstChild();
		while (childNode != null) {
			officeSpreadsheet.removeChild(childNode);
			childNode = officeSpreadsheet.getFirstChild();
		}
	}

	void setFontWeight(OdfStyleBase style, String value) {
		style.setProperty(OdfStyleTextProperties.FontWeight, value);
		style.setProperty(OdfStyleTextProperties.FontWeightAsian, value);
		style.setProperty(OdfStyleTextProperties.FontWeightComplex, value);
	}

	void addAutomaticStyles() {

		OdfStyle style;

		// Column style (all columns same width)
		style = contentAutoStyles.newStyle(OdfStyleFamily.TableColumn);
		columnStyleName = style.getStyleNameAttribute();
		style.setProperty(OdfStyleTableColumnProperties.ColumnWidth, "2.5cm");

		// Row style
		style = contentAutoStyles.newStyle(OdfStyleFamily.TableRow);
		rowStyleName = style.getStyleNameAttribute();
		style.setProperty(OdfStyleTableRowProperties.RowHeight, "0.5cm");

		// bold centered cells (for first row)
		style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
		headingStyleName = style.getStyleNameAttribute();
		style.setProperty(OdfStyleParagraphProperties.TextAlign, "center");
		setFontWeight(style, "bold");

		// Create the date, time, and temperature styles and add them.
		// The null in OdfNumberDateStyle means "use default calendar system"
		OdfNumberDateStyle dateStyle = new OdfNumberDateStyle(contentDom, "yyyy-MM-dd", "numberDateStyle", null);
		OdfNumberTimeStyle timeStyle = new OdfNumberTimeStyle(contentDom, "hh:mm:ss", "numberTimeStyle");
		OdfNumberStyle numberStyle = new OdfNumberStyle(contentDom, "#0.00", "numberTemperatureStyle");

		contentAutoStyles.appendChild(dateStyle);
		contentAutoStyles.appendChild(timeStyle);
		contentAutoStyles.appendChild(numberStyle);

		// cell style for Date cells
		style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
		noaaDateStyleName = style.getStyleNameAttribute();
		style.setStyleDataStyleNameAttribute("numberDateStyle");

		// and for time cells
		style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
		noaaTimeStyleName = style.getStyleNameAttribute();
		style.setStyleDataStyleNameAttribute("numberTimeStyle");

		// and for the temperatures
		style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
		noaaTempStyleName = style.getStyleNameAttribute();
		style.setStyleDataStyleNameAttribute("numberTemperatureStyle");
		style.setProperty(OdfStyleParagraphProperties.TextAlign, "right");

	}

	void processInputDocument() {
		BufferedReader inReader; // for reading the file
		String data; // holds one line of the file
		String[] info; // holds the split-up data
		OdfTable table;
		OdfTableRow row;
		OdfTableColumn column;
		OdfTableCell cell;

		table = new OdfTable(contentDom);
		column = table.addStyledTableColumn(columnStyleName);
		column.setDefaultCellStyle(contentAutoStyles.getStyle(noaaDateStyleName, OdfStyleFamily.TableCell));

		column = table.addStyledTableColumn(columnStyleName);
		column.setDefaultCellStyle(contentAutoStyles.getStyle(noaaTimeStyleName, OdfStyleFamily.TableCell));

		column = table.addStyledTableColumn(columnStyleName);
		column.setDefaultCellStyle(contentAutoStyles.getStyle(noaaTempStyleName, OdfStyleFamily.TableCell));

		// fill in the header row
		row = new OdfTableRow(contentDom);
		row.setTableStyleNameAttribute(rowStyleName);

		row.appendCell(createCell(headingStyleName, "Date"));
		row.appendCell(createCell(headingStyleName, "Time"));
		row.appendCell(createCell(headingStyleName, "\u00b0C")); // degrees C

		table.appendRow(row);

		try {
			inReader = new BufferedReader(new FileReader(inputFileName));
			data = inReader.readLine();
			while (data != null) {
				info = data.split(",");
				/* 0 = date, 1 = time, 2 = temperatue in degrees C */
				row = new OdfTableRow(contentDom);
				row.setTableStyleNameAttribute(rowStyleName);

				cell = new OdfTableCell(contentDom);
//                cell.setTableStyleNameAttribute(noaaDateStyleName);
				cell.setOfficeDateValueAttribute(info[0]);
				cell.setOfficeValueTypeAttribute(OfficeValueTypeAttribute.Value.DATE.toString());

				row.appendCell(cell);

				cell = new OdfTableCell(contentDom);
//                cell.setTableStyleNameAttribute(noaaTimeStyleName);
				cell.setOfficeTimeValueAttribute(convertToOdfTime(info[1]));
				cell.setOfficeValueTypeAttribute(OfficeValueTypeAttribute.Value.TIME.toString());

				row.appendCell(cell);

				cell = new OdfTableCell(contentDom);
//                cell.setTableStyleNameAttribute(noaaTempStyleName);
				cell.setOfficeValueAttribute(Double.parseDouble(info[2]));
				cell.setOfficeValueTypeAttribute(OfficeValueTypeAttribute.Value.FLOAT.toString());

				row.appendCell(cell);

				table.appendRow(row);
				data = inReader.readLine();

			}
			inReader.close();

			// insert a blank row
			table.appendRow(new OdfTableRow(contentDom));

			// the row with the formula
			row = new OdfTableRow(contentDom);
			row.appendCell(new OdfTableCell(contentDom));
			row.appendCell(createCell(headingStyleName, "Avg. Temp."));
			cell = new OdfTableCell(contentDom);
			cell.setOfficeValueTypeAttribute(OfficeValueTypeAttribute.Value.FLOAT.toString());
			cell.setTableFormulaAttribute("oooc:=AVERAGE([.C2:.C25]");
			row.appendCell(cell);

			table.appendRow(row);

			officeSpreadsheet.appendChild(table);
		} catch (Exception e) {
			System.err.println("Cannot process " + inputFileName);
		}
	}

	private OdfTableCell createCell(String cellStyle, String content) {
		OdfTableCell cell = new OdfTableCell(contentDom);
		OdfTextParagraph paragraph = new OdfTextParagraph(contentDom, null, content);
		cell.setTableStyleNameAttribute(cellStyle);

		cell.setOfficeStringValueAttribute(content);
		cell.setOfficeValueTypeAttribute(OfficeValueTypeAttribute.Value.STRING.toString());
		cell.appendChild(paragraph);
		return cell;
	}

	private String convertToOdfTime(String hourMinuteSecondTime) {
		String[] timeParts = hourMinuteSecondTime.split(":");
		String result = "PT" + timeParts[0] + "H" + timeParts[1] + "M" + timeParts[2] + "S";
		return result;
	}

	void saveOutputDocument() {
		try {
			outputDocument.save(outputFileName);
		} catch (Exception e) {
			System.err.println("Unable to save document.");
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SimpleOds app = new SimpleOds();
		app.run(args);
	}
}
