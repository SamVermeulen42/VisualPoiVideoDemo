package visualpoivideo.model;

public class Picture {
    private int fileID;
    private double startTime;
    private double endTime;
    private double spacing;
    private double scale;

    public Picture(int fileID, double startTime, double endTime, double spacings, double scales) {
        this.fileID = fileID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.spacing = spacings;
        this.scale = scales;
    }

    public int getFileID() {
        return fileID;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public double getSpacing() {
        return spacing;
    }

    public double getScale() {
        return scale;
    }
    
}
