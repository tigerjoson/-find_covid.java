package mytool;

import java.io.File;
import java.util.ArrayList;

public class Getfileproperties {

	public Getfileproperties() {
	}

	public String GetFile_string() {
		return file_string;
	}

	public void setFile_string(String file_string) {
		this.file_string = file_string;
	}

	private String file_string;

	File file = new File(GetFile_string());

	public int numer_of_files() {
		int numer_of_files = file.list().length;
		return numer_of_files;
	}

	public void print_fileinfo() {
		for (int i = 0; i < file.list().length; i++) {
			String[] fileStringslist = file.list();
			System.out.println(i + "," + fileStringslist[i]);
		}

	}

	public ArrayList<String> fileArrayListstring(File filepath) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String[] fileStringslist = file.list();
		for (int i = 0; i < file.list().length; i++) {
			arrayList.add(fileStringslist[i]);
		}
		return arrayList;
	}
	

	public ArrayList<File> fileArrayListfile(File filepath) {
		ArrayList<File> arrayList = new ArrayList<File>();
		String[] fileStringslist = file.list();
		for (int i = 0; i < file.list().length; i++) {
			String string = fileStringslist[i];
			arrayList.add(new File(string));
		}
		return arrayList;
	}

}
