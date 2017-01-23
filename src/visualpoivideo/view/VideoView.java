package visualpoivideo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import visualpoivideo.Main;
import visualpoivideo.controller.OpenVideoListener;
import visualpoivideo.model.Video;

public class VideoView extends JPanel implements ViewInterface {

    private JLabel filename;
    private Video video;
    
    public VideoView(Video video) {
        super(new BorderLayout());
        
        this.video = video;
        video.addListener(this);
        
        JButton openVideoButton = new JButton("Open video");
        ClassLoader classLoader = getClass().getClassLoader();
        openVideoButton.setIcon(new ImageIcon(classLoader.getResource("resources/Open24.gif")));
        openVideoButton.addActionListener(new OpenVideoListener(video, this));
        
        add(openVideoButton, BorderLayout.PAGE_START);
        
        JPanel info = new JPanel();
        BoxLayout boxlay = new BoxLayout(info, BoxLayout.Y_AXIS);
        info.setLayout(boxlay);
        filename = new JLabel("No Video opened");
        info.add(filename);
        
        add(info, BorderLayout.CENTER);
    }

    @Override
    public void onChange() {
        if (video.getFile() == null) {
            filename.setText("No Video opened");
        } else {
            filename.setText("Opened file: " + video.getFile().getName());
        }
    }
    
    
    
}
