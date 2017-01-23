package visualpoivideo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import visualpoivideo.controller.OpenProjectListener;
import java.awt.GridLayout;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import visualpoivideo.controller.FileParser;
import visualpoivideo.controller.SaveProjectListener;
import visualpoivideo.model.Picture;
import visualpoivideo.model.Project;

public class ProjectView extends JPanel implements ViewInterface{

    private JList fileNames;
    
    private Project project;
    private boolean firstOpen = true; // used to only call setEnabled on save button once
    
    private JButton saveProjectButton;
    
    public ProjectView(Project project) {
        super(new BorderLayout());
        
        this.project = project;
        
        FileParser parser = new FileParser(project);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        
        JButton openProjectButton = new JButton("Open project");
        ClassLoader classLoader = getClass().getClassLoader();
        openProjectButton.setIcon(new ImageIcon(classLoader.getResource("resources/Open24.gif")));
        openProjectButton.addActionListener(new OpenProjectListener(parser, this));
        
        saveProjectButton = new JButton("Save project");
        saveProjectButton.setIcon(new ImageIcon(classLoader.getResource("resources/Save24.gif")));
        saveProjectButton.addActionListener(new SaveProjectListener(parser));
        saveProjectButton.setEnabled(false);
        
        fileNames = new JList();
        JScrollPane listScroller = new JScrollPane(fileNames);
        listScroller.setPreferredSize(new Dimension(300, 500));
        
        buttonPanel.add(openProjectButton);
        buttonPanel.add(saveProjectButton);
        add(buttonPanel, BorderLayout.PAGE_START);
        add(listScroller, BorderLayout.CENTER);
    }

    @Override
    public void onChange() {
        List<Picture> pictures = project.getPictures();
        List<File> files = project.getFiles();
        int picCount = pictures.size();
        
        if (firstOpen && project.getLocation() != null) {
            saveProjectButton.setEnabled(true);
            saveProjectButton.repaint();
            firstOpen = false;
        }
        
        String[] listContents = new String[picCount];
        for (int i = 0; i < picCount; i++) {
            Picture pic = pictures.get(i);
            Date from = new Date((long) pic.getStartTime());
            Date to = new Date((long) pic.getEndTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss:SS");
            
            listContents[i] = dateFormat.format(from) + "-"
                    + dateFormat.format(to) + ": \""
                    + files.get(pic.getFileID()).getName() + "\"";
        }
        
        fileNames.setListData(listContents);
    }
    
    
        
    
    
    
    
    
}
