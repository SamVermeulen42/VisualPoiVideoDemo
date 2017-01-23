package visualpoivideo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visualpoivideo.controller.FileParser;

public class SaveProjectListener implements ActionListener {

    private FileParser parser;

    public SaveProjectListener(FileParser parser) {
        this.parser = parser;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            parser.saveModel();
        } catch (IOException ex) {
            Logger.getLogger(SaveProjectListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
