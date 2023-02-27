package my_tool;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Node;

public class File_RW {

	private File file;
	private String folderpathString; 

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFile(String fileString) {
		this.file = new File(fileString);
	}

	public void copy(String source_path, String destination_folder) throws IOException {
		try {
			int line_number = 0;
			InputStream inputStream = new FileInputStream(source_path);
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader bReader = new BufferedReader(isr);
			FileWriter fWriter = new FileWriter(destination_folder);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			String line;
			while ((line = bReader.readLine()) != null) {
//				System.out.println(line.length());
				// System.out.println(line_number);
				line_number++;
				// System.out.println(line);
				bWriter.write(line + "\r\n");
			}
			bReader.close();
			inputStream.close();
			isr.close();
			bWriter.close();
			fWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException e");
			e.printStackTrace();
		}
	}

	public int getnumberofline() throws Exception {
		InputStream inputStream = new FileInputStream(this.getFile());
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader bReader = new BufferedReader(isr);
		
		int i = 0;
		while ( bReader.readLine() != null) {
			i++;
		}
		return i;
	}

	public Object[] getcontents() throws Exception {
		InputStream inputStream = new FileInputStream(this.getFile());
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader bReader = new BufferedReader(isr);
		ArrayList<String> arrayList = new ArrayList<>();
		String line;
		while ((line = bReader.readLine()) != null) {
			arrayList.add(line);
		}

		bReader.close();
		inputStream.close();
		isr.close();
		return arrayList.toArray();
	}

	public void write_folder_info(String folderString,String write_info_loation_folder) throws Exception {
		File file = new File(folderString);
		String[] file_liSt = file.list();
		// write file location
		//		filename
		String file_name = file.getName().concat("_before_soting");
		String document_extension = file_name.concat(".txt");
		String full_destination = write_info_loation_folder.concat(document_extension);
		// write file's txt file
		FileWriter fileWriter = new FileWriter(full_destination);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		boolean filecanread = file.canRead();
		boolean isdir = file.isDirectory();
		if (filecanread && isdir) {
			for (int index = 0; index < file.listFiles().length; index++) {
				// for cast
				File f = new File(file_liSt[index]);
				String string = file_name.toString();
//				System.out.println(f.isDirectory());
				System.out.println(index);

				bufferedWriter.write(string);
				bufferedWriter.write("NO");
				bufferedWriter.write(Integer.toString(index + 1));
				bufferedWriter.write(",");
				bufferedWriter.write(file_liSt[index]);

				String file_full_name = file.getPath().concat(file_liSt[index]);
				Path path_one_file = Paths.get(file_full_name);
				Long file_size = Files.size(path_one_file);

				bufferedWriter.write("," + file_size);
				bufferedWriter.newLine();

			}
			System.out.println("write file place:");
			System.out.println(full_destination);
			bufferedWriter.close();
			fileWriter.close();
		} else {
			System.out.println("file canread:" + filecanread);
			System.out.println("is directory:" + isdir);
		}

	}

	
	public ArrayList<String> get_all_files(String folderString) {
		File file = new File(folderString);
		String[] file_listStrings = file.list();
		String absolutePathString= file.getAbsolutePath();
		ArrayList<String> arrayList = new ArrayList<String>();
		for(int i=0; i< file_listStrings.length ;i++) {
			String fullfilenameString = absolutePathString.concat("\\"+file_listStrings[i]);
			System.out.println(fullfilenameString);
			arrayList.add(fullfilenameString);
		}
		return arrayList;
	}
	
	public void print_to_screen(String file_path, int columns) throws IOException {
		try {
			int line_number = 0;
			FileReader fReader = new FileReader(file_path);
			BufferedReader bReader = new BufferedReader(fReader);
			String line;
			// System.out.println("encode="+fReader.getEncoding());
			while ((line = bReader.readLine()) != null) {
				line_number++;
				if (line_number <= columns) {
					System.out.print(line_number + ",");
					System.out.println(line);
				}
			}
			bReader.close();
			fReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void print_to_screen() throws IOException {
		try {
			String file_path = this.getFile().toString();
			int line_number = 0;
			FileReader fReader = new FileReader(file_path);
			BufferedReader bReader = new BufferedReader(fReader);
			String line;
			// System.out.println("encode="+fReader.getEncoding());
			while ((line = bReader.readLine()) != null) {
				line_number++;
				System.out.println(line_number);
				System.out.println(line);
			}
			bReader.close();
			fReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

//look specified file 
	public void Show_file_info() {
		String file_path = this.getFile().toString();
		File file = new File(file_path);
		System.out.println("canExecute=" + file.canExecute());
		System.out.println("canRead=" + file.canRead());
		System.out.println("canwrite=" + file.canWrite());
		System.out.println("exit=" + file.exists());
	}

	public void remove_string(String destination, String oldString, String newString) throws IOException {
		try {
			File f = this.getFile();

			// read
			InputStream inputStream = new FileInputStream(f.toString());
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader bReader = new BufferedReader(isr);
			// write
			OutputStream outputStream = new FileOutputStream(destination);
			OutputStreamWriter osr = new OutputStreamWriter(outputStream);
			BufferedWriter bWriter = new BufferedWriter(osr);
			String line;

			// per line
			while ((line = bReader.readLine()) != null) {
				Pattern p = Pattern.compile(oldString);
				Matcher m = p.matcher(line);

				if (m.find()) {
					bWriter.write(line.replaceAll(oldString, newString));
					bWriter.newLine();
				}
			}
			bReader.close();
			inputStream.close();
			isr.close();
			bWriter.close();
			osr.close();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void print_node_info(Node node) {
		System.out.println("node.getLocalName()="+node.getLocalName());
		System.out.println("node.getNodetype()="+node.getNodeType());
		System.out.println("node.getNodeName()="+node.getNodeName());
		System.out.println("NamedNodeMap=node.getAttributes()="+node.getAttributes());
		System.out.println("node.getTextContent()= "+node.getTextContent());
		for(int i=0;i<node.getAttributes().getLength();i++) {
			System.out.println(node.getAttributes().item(i));
		}
	}

	public String getFolderpathString() {
		return folderpathString;
	}

	public void setFolderpathString(String folderpathString) {
		this.folderpathString = folderpathString;
	}

}
