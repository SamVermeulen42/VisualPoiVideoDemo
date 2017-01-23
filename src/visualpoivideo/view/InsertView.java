package visualpoivideo.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import visualpoivideo.controller.AddAtEndListener;
import visualpoivideo.model.Project;
import visualpoivideo.model.Video;

public class InsertView extends JPanel {
    
    public InsertView(Project project, Video video) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JPanel settings = new JPanel();
        settings.setLayout(new GridLayout(0, 2));
        
        settings.add(new JLabel("Rotations/min"));
        JTextField fieldRotations = new JTextField();
        settings.add(fieldRotations);
        settings.add(new JLabel("Repetitions"));
        JTextField fieldRepetitions = new JTextField();
        settings.add(fieldRepetitions);
        settings.add(new JLabel("Stretch"));
        JTextField fieldStretch = new JTextField();
        settings.add(fieldStretch);
        
        add(settings);
        JButton addAtEndButton = new JButton("Append after last image");
        addAtEndButton.addActionListener(new AddAtEndListener(project, video, fieldRotations, fieldRepetitions, fieldStretch));
        add(addAtEndButton);
        
        
    }
    
}
