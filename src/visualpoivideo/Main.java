package visualpoivideo;

import javax.swing.JFrame;
import visualpoivideo.model.Project;
import visualpoivideo.model.Video;
import visualpoivideo.view.FullView;

public class Main {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Visual Poi Movie Importer");
        
        Project project = new Project();
        Video video = new Video();
        FullView view = new FullView(project, video);
        project.addListener(view.getPview());
        
        frame.add(view);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
}
