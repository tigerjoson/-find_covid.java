package my_tool;

import java.io.File;
import java.io.IOException;

public class Task_manange {
	public static void main(String[] args) throws IOException {
		System.out.println(" Number of processors available to the Java virtual machine:" + Runtime.getRuntime().availableProcessors());
		String cmdString = "cmd.exe /c";
		String path ="C:\\Users\\tiger\\Desktop\\notepad++backup\\task.txt";
		File file = new File(path);
		boolean filecanread = file.canRead();
		Runtime.getRuntime().exec(cmdString+"start "+"C:\\Users\\tiger\\Desktop\\程式\\mybat\\task.bat");
		Runtime.getRuntime().exit(0);
		System.out.println("filecanread:"+filecanread);
//		Runtime.getRuntime().exec(cmdString+"start "+"C:\\Users\\tiger\\Desktop\\程式\\mybat\\刪除暫存檔.bat");
		//runtime.exec(cmdString + "notepad");
		System.out.println("write file path:"+file.getPath());
		// runtime.exec(cmdString+"start cmd.exe");
		

	}
}
        