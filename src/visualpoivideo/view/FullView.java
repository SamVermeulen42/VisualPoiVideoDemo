package visualpoivideo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import visualpoivideo.model.Project;
import visualpoivideo.model.Video;

public class FullView extends JPanel{
    
    private static int HEIGHT = 540;
    private static int WIDTH = 960;

    private ProjectView pview;
    private VideoView vview;
    private InsertView iview;
    
    public FullView(Project project, Video video) {
        super(new GridLayout(1, 3, 3, 3));
        
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        pview = new ProjectView(project);
        iview = new InsertView(project, video);
        vview = new VideoView(video);
        
        add(pview);
        add(iview);
        add(vview);
    }

    public ProjectView getPview() {
        return pview;
    }

    public VideoView getVview() {
        return vview;
    }
    
}
