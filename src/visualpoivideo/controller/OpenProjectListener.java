package visualpoivideo.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenProjectListener implements ActionListener{

    private FileParser parser;
    private Component owner;
    
    private JFileChooser chooser;
    
    public OpenProjectListener(FileParser parser, Component owner) {
        this.parser = parser;
        this.owner = owner;
        
        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Visual Poi Project", "vpp");
        chooser.setFileFilter(filter);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = chooser.showOpenDialog(owner);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("Opening file");
            try {
                parser.readFile(chooser.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(OpenProjectListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
