package my_turn;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;

public class mypaintver1 implements ActionListener {

	private JFrame frame;
	public JScrollPane jScrollPane;
	public JComponent jComponent;
	Image_component imageComponent;
	public JPanel corner;
	public JToolBar jToolBar;
	public JFileChooser jFileChooser;
	public FileNameExtensionFilter filter;
	public JButton openButton, saveButton, zoominButton, zoomoutButton, clearimagebutton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mypaintver1 window = new mypaintver1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mypaintver1() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jToolBar = new JToolBar();
		frame.getContentPane().add(jToolBar, BorderLayout.NORTH);
		openButton = new JButton("open figure");
		jToolBar.add(openButton);
		openButton.addActionListener(this);

		saveButton = new JButton("save figure");
		jToolBar.add(saveButton);
		saveButton.addActionListener(this);

		clearimagebutton = new JButton("clear image");
		jToolBar.add(clearimagebutton);
		clearimagebutton.addActionListener(this);

		zoominButton = new JButton("zoom in");
		jToolBar.add(zoominButton);
		zoominButton.addActionListener(this);

		zoomoutButton = new JButton("zoom out");
		jToolBar.add(zoomoutButton);
		zoomoutButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			System.out.println("trigger chooserfile");
			System.out.println("Command : " + e.getActionCommand());
			
			filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
			jFileChooser.addChoosableFileFilter(filter);

			int return_value = jFileChooser.showOpenDialog(frame);

			if (return_value == JFileChooser.APPROVE_OPTION) {
				// System.out.println("return_value "+ return_value);
				File file = jFileChooser.getSelectedFile();

				imageComponent = new Image_component();
				try {
					imageComponent.bufferedImage = ImageIO.read(file);
					// imageComponent.printinfo();
					// System.out.println(file.getPath());
					jScrollPane = new JScrollPane();
					jScrollPane.setColumnHeaderView(jToolBar);
					jScrollPane.setViewportView(imageComponent);
					jScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, corner);
					jScrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner);
					jScrollPane.setCorner(JScrollPane.LOWER_LEFT_CORNER, corner);
					jScrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, corner);
					jScrollPane.setBorder(BorderFactory.createLineBorder(Color.red));

					frame.getContentPane().add(jScrollPane);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == zoominButton) {
			System.out.println("zoom in");

		} else if (e.getSource() == zoomoutButton) {
			System.out.println("zoom out");

		} else if (e.getSource() == clearimagebutton) {
			System.out.println("clear");
			frame.getContentPane().removeAll();
						
		}
	}
}
