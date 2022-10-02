package hsu;
//reference :https://www.tutorialspoint.com/vlcj/vlcj_play.htm

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class myvlcvsersion1 extends JFrame implements ActionListener, WindowListener {
	public JFrame myvlc_frame;
	public JButton jButton_play, jButton_pause;
	public JToolBar jToolBar;
	public EmbeddedMediaPlayerComponent embeddedMediaPlayerComponent;
	public static String path_string ="C:\\\\Users\\\\tiger\\\\Desktop\\\\20220918143247_000016.MP4"; 

	public static void main(String[] args) throws Exception {
		myvlcvsersion1 myplayer = new myvlcvsersion1();

	}

	public myvlcvsersion1() {
		
		myvlc_frame = new JFrame("mvlctest");
		myvlc_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myvlc_frame.addWindowListener(this);

		myvlc_frame.setVisible(true);
		myvlc_frame.setBounds(0, 100, 500, 400);
		
		embeddedMediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		myvlc_frame.add(embeddedMediaPlayerComponent);
		embeddedMediaPlayerComponent.mediaPlayer().media().startPaused(path_string);
		
		jButton_play = new JButton("play");
		jButton_play.addActionListener(this);
		jButton_pause = new JButton("pause");
		jButton_pause.addActionListener(this);

		jToolBar = new JToolBar();
		myvlc_frame.add(jToolBar, BorderLayout.PAGE_END);
		jToolBar.setLayout(new FlowLayout(1));
		jToolBar.add(jButton_play);
		jToolBar.add(jButton_pause);
	}

	public void windowOpened(WindowEvent e) {
		//
		System.out.println(e.paramString());
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.paramString());
	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.paramString());
		 embeddedMediaPlayerComponent.release();
		System.exit(0);

	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.paramString());

	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.paramString());
	}

	public void windowActivated(WindowEvent e) {
		System.out.println(e.paramString());

	}

	public void windowDeactivated(WindowEvent e) {
		System.out.println(e.paramString());

	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.paramString());
		if (e.getSource()==jButton_play) {
			 embeddedMediaPlayerComponent.mediaPlayer().controls().play();
			
		}else if (e.getSource()==jButton_pause) {
			 embeddedMediaPlayerComponent.mediaPlayer().controls().pause();
		}
	}

}
