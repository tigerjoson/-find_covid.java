package reference;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
public class AddingImagesTrackbar {
    private static final int ALPHA_SLIDER_MAX = 100;
    private int alphaVal = 0;
    private Mat matImgSrc1;
    private Mat matImgSrc2;
    private Mat matImgDst = new Mat();
    private JFrame frame;
    private JLabel imgLabel;
    public AddingImagesTrackbar(String[] args) {
        String imagePath1 = "C:\\XX\\XXX.jpg";
        String imagePath2 = "C:\\UXX\\XXXX.jpg";
        if (args.length > 1) {
            imagePath1 = args[0];
            imagePath2 = args[1];
            System.out.println(args.length);
        }
        matImgSrc1 = Imgcodecs.imread(imagePath1);
        matImgSrc2 = Imgcodecs.imread(imagePath2);
        if (matImgSrc1.empty()) {
            System.out.println("Empty image: " + imagePath1);
            System.exit(0);
        }
        if (matImgSrc2.empty()) {
            System.out.println("Empty image: " + imagePath2);
            System.exit(0);
        }
        // Create and set up the window.
        frame = new JFrame("Linear Blend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set up the content pane.
        Image img = HighGui.toBufferedImage(matImgSrc2);
        addComponentsToPane(frame.getContentPane(), img);
        // Use the content pane's default BorderLayout. No need for
        // setLayout(new BorderLayout());
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    private void addComponentsToPane(Container pane, Image img) {
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }
        JPanel myjPanel = new JPanel();
        myjPanel.setLayout(new BoxLayout(myjPanel, BoxLayout.PAGE_AXIS));
        myjPanel.add(new JLabel(String.format("Alpha x %d", ALPHA_SLIDER_MAX)));
        JSlider jslider = new JSlider(0, ALPHA_SLIDER_MAX, 0);
        jslider.setMajorTickSpacing(20);
        jslider.setMinorTickSpacing(5);
        jslider.setPaintTicks(true);
        jslider.setPaintLabels(true);
        
        jslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                alphaVal = source.getValue();
                update();
            }
        });
        myjPanel.add(jslider);
        pane.add(myjPanel, BorderLayout.PAGE_START);
        imgLabel = new JLabel(new ImageIcon(img));
        pane.add(imgLabel, BorderLayout.CENTER);
    } 
    private void update() {
        double alpha = alphaVal / (double) ALPHA_SLIDER_MAX;
        double beta = 1.0 - alpha;
        Core.addWeighted(matImgSrc1, alpha, matImgSrc2, beta, 0, matImgDst);
        Image img = HighGui.toBufferedImage(matImgDst);
        imgLabel.setIcon(new ImageIcon(img));
        frame.repaint();
    }
    public static void main(String[] args) {
        // Load the native OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddingImagesTrackbar(args);
            }
        });
    }
}
