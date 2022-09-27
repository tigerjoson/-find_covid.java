
package reference;
//reference :https://www.tutorialspoint.com/vlcj/vlcj_play.htm

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class myvlc extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "My First Media Player";
	private static final String VIDEO_PATH = "D:\\Downloads\\sunset-beach.mp4";
	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private JButton playButton;

	public myvlc(String title) {
		super(title);
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
	}

	public void initialize() {
		this.setBounds(100, 100, 600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mediaPlayerComponent.release();
				System.exit(0);
			}
		});
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

		JPanel controlsPane = new JPanel();
		playButton = new JButton("Play");
		controlsPane.add(playButton);
		contentPane.add(controlsPane, BorderLayout.SOUTH);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediaPlayerComponent.mediaPlayer().controls().play();
			}
		});
		this.setContentPane(contentPane);
		this.setVisible(true);
	}

	public void loadVideo(String path) {
		mediaPlayerComponent.mediaPlayer().media().startPaused(path);
		// vlcj > 4.8
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}
		myvlc application = new myvlc(TITLE);
		application.initialize();
		application.setVisible(true);
		application.loadVideo(VIDEO_PATH);
	}
}
