package visualpoivideo.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JTextField;
import visualpoivideo.model.Picture;
import visualpoivideo.model.Project;
import visualpoivideo.model.Video;

public class AddAtEndListener implements ActionListener{

    private Project project;
    private Video video;
    private JTextField fieldRotations;
    private JTextField fieldRepetitions;
    private JTextField fieldStretch;

    public AddAtEndListener(Project project, Video video, JTextField fieldRotations, JTextField fieldRepetitions, JTextField fieldStretch) {
        this.project = project;
        this.video = video;
        this.fieldRotations = fieldRotations;
        this.fieldRepetitions = fieldRepetitions;
        this.fieldStretch = fieldStretch;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double fps = Double.valueOf(fieldRotations.getText()) / 60.0;
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File("ffmpeg.exe");
            
            String[] cmd = new String[7];
            cmd[0] = file.getPath();
            cmd[1] = "-i";
            cmd[2] = video.getFile().getPath();
            cmd[3] = "-vf";
            cmd[4] = "\"fps=" + String.valueOf(fps) + ",";
            cmd[5] = "scale=-2:80\"";
            cmd[6] = project.getLocation().getParent().concat("\\images\\" + video.getFile().getName().split("\\.")[0] + "%03d.bmp");
            File log = new File("log");
            ProcessBuilder p = new ProcessBuilder(cmd);
            p.redirectErrorStream(true);
            p.redirectOutput(log);
            Process proc = p.start();
            try {
                proc.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(AddAtEndListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Done extracting frames");
            
            List<File> images = new ArrayList<>();
            int fileID = project.getFiles().size();
            List<Picture> prevPictures = project.getPictures();
            List<File> prevFiles = project.getFiles();
            double startTime = prevPictures.get(prevPictures.size() - 1).getEndTime();
            double frameDur = (double) 1000 / (Double.valueOf(fieldRotations.getText()) / 60.0);
            int occurances = Integer.valueOf(fieldRepetitions.getText());
            File folder = new File(project.getLocation().getParent() + "\\images\\");
            
            Color myBlack = new Color(0, 0, 0);
            int rgb = myBlack.getRGB();
            
            for (File image : folder.listFiles()) {
                if (image.getName().split("\\.")[1].equals("bmp") && !prevFiles.contains(image)) {
                    // TODO: edit images with black line
                    BufferedImage buffered = ImageIO.read(image);
                    for (int row = 0; row < buffered.getHeight(); row++) {
                        buffered.setRGB(buffered.getWidth() - 1, row, rgb);
                    }
                    buffered.flush();
                    ImageIO.write(buffered, "bmp", image);
                    
                    project.addFile(image);
                    double endTime = startTime + frameDur;
                    Picture picture = new Picture(fileID, startTime, endTime, 600 / occurances - buffered.getWidth(), 1);
                    project.addPicture(picture);
                    fileID++;
                    startTime = endTime;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AddAtEndListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
