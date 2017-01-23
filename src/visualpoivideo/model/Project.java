package visualpoivideo.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Project extends AbstractModel {
    
    private File location;
    private List<File> pictureFiles;
    private List<Picture> pictures;
    
    public Project() {
        pictureFiles = new ArrayList<>();
        pictures = new ArrayList<>();
    }
    
    public void addFile(File fileName) {
        pictureFiles.add(fileName);
        changed();
    }
    
    public List<File> getFiles() {
        return new ArrayList<>(pictureFiles);
    }
    
    public void addPicture(Picture picture) {
        pictures.add(picture);
        changed();
    }
    
    public List<Picture> getPictures() {
        // TODO: change to deep copy
        return new ArrayList<>(pictures);
    }

    public File getLocation() {
        return location;
    }

    public void setLocation(File location) {
        this.location = location;
    }
    
    
    
    public void clear() {
        pictureFiles.clear();
        pictures.clear();
        location = null;
        changed();
    }
}
