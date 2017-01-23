package visualpoivideo.model;

import java.io.File;
import java.io.IOException;

public class Video extends AbstractModel{
    
    private File file;
    
    public void setFile(File file) throws IOException {
        this.file = file;
        changed();
    }

    public File getFile() {
        return file;
    }
}
