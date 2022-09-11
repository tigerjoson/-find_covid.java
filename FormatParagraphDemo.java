/************************************************************************
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * 
 * Copyright 2011 IBM. All rights reserved.
 * 
 * Use is subject to license terms.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0. You can also
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
import java.io.InputStreamReader;
import java.net.URI;

import org.odftoolkit.odfdom.type.Color;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;
import org.odftoolkit.simple.style.Font;
import org.odftoolkit.simple.style.StyleTypeDefinitions.FontStyle;
import org.odftoolkit.simple.style.StyleTypeDefinitions.HorizontalAlignmentType;
import org.odftoolkit.simple.text.Paragraph;

/**
 * This demo shows the new text document format features, which creates a text
 * document using plain text. While reading content, paragraphs whose content
 * lengths less than 20 characters are changed to headings and new font style.
 * "Version" and "date" information is special, which is set right alignment and
 * gray text. Each text which matches URL format will be applied as hyper link.
 * Each heading and its following paragraphs are considered as a chapter. There
 * will be a page break after page line count bigger than the predefined value.
 * Then check words spell and add comments as tip to those which may have a
 * spell mistake. The last one is security check. After these process, plain
 * text is changed to format document.
 * 
 * @author Administrator
 * 
 */
public class FormatParagraphDemo {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				FormatParagraphDemo.class.getResourceAsStream("text.txt")));
		String in = reader.readLine();
		TextDocument doc = TextDocument.newTextDocument();
		int lineCount = 0;
		int pageLineCount = 0;
		Paragraph refParagraph = null;
		while (in != null) {
			in = in.trim();
			Paragraph paragraph = doc.addParagraph(in);
			lineCount++;
			pageLineCount++;
			switch (lineCount) {
			case 1:
				paragraph.setHorizontalAlignment(HorizontalAlignmentType.CENTER);
				paragraph.setFont(new Font("Arial", FontStyle.BOLD, 16));
				paragraph.applyHeading();
				break;
			case 2:
			case 3:
				paragraph.setHorizontalAlignment(HorizontalAlignmentType.RIGHT);
				paragraph.setFont(new Font("Tahoma", FontStyle.ITALIC, 10,
						Color.GRAY));
				break;
			default:
				if (in.trim().startsWith("http://")) {
					paragraph.applyHyperlink(new URI(in));
				}
				if (in.length() < 20) {
					paragraph.applyHeading();
					paragraph.setFont(new Font("Arial", FontStyle.BOLD, 12));
					if (pageLineCount > 16) {
						doc.addPageBreak(refParagraph);
						pageLineCount = 0;
					}
				}
			}
			refParagraph = paragraph;
			in = reader.readLine();

		}
		// spell check
		TextNavigation navigation1 = new TextNavigation("lower-level", doc);
		while (navigation1.hasNext()) {
			TextSelection selection = (TextSelection) navigation1
					.nextSelection();
			selection.addComment(
					"Please change 'lower-level' with 'lower level'.",
					"SpellChecker");
		}
		// security check
		TextNavigation navigation2 = new TextNavigation("confidential", doc);
		if (navigation2.hasNext()) {
			TextSelection selection = (TextSelection) navigation2
					.nextSelection();
			selection
					.addComment(
							"This is a confidential document, please don't redistribute.",
							"SecurityChecker");
		}
		doc.save("format_text.odt");
	}
}
