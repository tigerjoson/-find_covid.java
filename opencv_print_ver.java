package my_turn;

import org.opencv.core.Core;

public class print_info {

	public static void main(String[] args) {
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 System.out.print(Core.VERSION);
	}

}
