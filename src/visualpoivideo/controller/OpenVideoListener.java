package visualpoivideo.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import visualpoivideo.model.Video;

public class OpenVideoListener implements ActionListener{

    private JFileChooser chooser;
    private Video video;
    private Component owner;

    public OpenVideoListener(Video video, Component owner) {
        this.video = video;
        this.owner = owner;
        
        chooser = new JFileChooser();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = chooser.showOpenDialog(owner);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                video.setFile(chooser.getSelectedFile());
            } catch (IOException ex) {
                //
            }
        }
    }
    
}
