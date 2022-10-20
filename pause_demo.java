package video_vlc_reference;

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

public class pause_demo extends JFrame {
   private static final long serialVersionUID = 1L;
   private static final String TITLE = "My First Media Player";
   private static final String VIDEO_PATH = "C:\\Users\\tiger\\Downloads\\1.mkv";
   private final EmbeddedMediaPlayerComponent embeddedMediaPlayerComponent;
   private JButton playButton;
   private JButton pauseButton;

   public pause_demo(String title) {
      super(title);
      embeddedMediaPlayerComponent = new EmbeddedMediaPlayerComponent();		
   }
   
   public void initialize() {
      this.setBounds(100, 100, 600, 400);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            embeddedMediaPlayerComponent.release();
            System.exit(0);
         }
      });    	
      JPanel contentPane = new JPanel();
      contentPane.setLayout(new BorderLayout());   	 
      contentPane.add(embeddedMediaPlayerComponent, BorderLayout.CENTER);
      JPanel controlsPane = new JPanel();
		//play button
	playButton = new JButton("Play");
      controlsPane.add(playButton);    
		//pause button
	 pauseButton = new JButton("Pause");
      controlsPane.add(pauseButton);
      contentPane.add(controlsPane, BorderLayout.SOUTH);
      playButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            embeddedMediaPlayerComponent.mediaPlayer().controls().play();
         }
      });  
      pauseButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            embeddedMediaPlayerComponent.mediaPlayer().controls().pause();
         }
      });
      this.setContentPane(contentPane);
      this.setVisible(true);
   }
   
   public void loadVideo(String path) {
      embeddedMediaPlayerComponent.mediaPlayer().media().startPaused(path);   	
   }
   
   public static void main( String[] args ){
      try {
         UIManager.setLookAndFeel(
         UIManager.getSystemLookAndFeelClassName());
      } 
      catch (Exception e) {
         System.out.println(e);
      }
      pause_demo application = new pause_demo(TITLE);
      application.initialize(); 
      application.setVisible(true);  
      application.loadVideo(VIDEO_PATH);
   }
}
