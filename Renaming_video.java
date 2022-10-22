package my_tool;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Renaming_video implements ActionListener {

	public JScrollPane jScrollPane;
	public JPanel corner;
	public JToolBar jToolBar, blank_jJToolBar;
	public JFileChooser jFileChooser;
	public FileNameExtensionFilter filter;
	public JButton openButton;
	public JFrame basic_frame;
	public Getfileproperties getfileproperties;

	static final  String wanttoremoveString_reg = "(Lilith-Raws)|(Baha)|(WEB-DL)|(Ohys-Raws)|(BIG5)|(AT\\-X)|(Nekomoe kissaten)|(1080p)|(1080P)|\\[|\\]";
	public Renaming_video() {
		basic_frame = new JFrame("rename");
		basic_frame.setBounds(100, 100, 450, 300);
		basic_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jToolBar = new JToolBar();
		basic_frame.add(jToolBar, BorderLayout.NORTH);

		openButton = new JButton("open figure");
		jToolBar.add(openButton);
		openButton.addActionListener(this);

		basic_frame.setVisible(true);

	}

	public static void main(String[] args) {
		
		
		Renaming_video renaming_video = new Renaming_video();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jFileChooser = new JFileChooser("C:\XX\\XX");
		jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int return_value = jFileChooser.showOpenDialog(basic_frame);

		if (return_value == JFileChooser.APPROVE_OPTION) {
			// System.out.println("return_value "+ return_value);

			File selectedfile = jFileChooser.getSelectedFile();
			String filepath = selectedfile.getAbsolutePath();
//			System.out.println(filepath);
			String[] files = selectedfile.list();
			for(int i=0;i<files.length;i++) {
				String full_nameString= selectedfile.getAbsolutePath().concat("\\").concat(files[i]);
				String clean_nameString= full_nameString.replaceAll(wanttoremoveString_reg, "");
				
				File rename_file = new File(full_nameString);
				
				rename_file.renameTo(new File(clean_nameString));
//				System.out.println("full_nameString="+full_nameString);
				System.out.println("clean_nameString="+clean_nameString);
			}

			jScrollPane = new JScrollPane();
			// jScrollPane.setColumnHeaderView(jToolBar);
			jScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);
			jScrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner);
			jScrollPane.setCorner(JScrollPane.LOWER_LEFT_CORNER, corner);
			jScrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, corner);

		}

	}
}
