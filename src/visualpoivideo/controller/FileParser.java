package visualpoivideo.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import visualpoivideo.model.Project;
import visualpoivideo.model.Picture;

public class FileParser {

    private Project model;
    private JSONObject root; // saved to store everything we don't adjust
    
    public FileParser(Project model) {
        this.model = model;
    }
    
    public Project readFile(File file) throws IOException {
        model.clear();
        
        model.setLocation(file);
        
        root = new JSONObject(fileToString(file));
        JSONArray images = root.getJSONArray("buff");
        JSONObject compo = root.getJSONObject("compo");
        JSONArray sources = compo.getJSONArray("sourceId");
        JSONArray startTimes = compo.getJSONArray("startTime");
        JSONArray endTimes = compo.getJSONArray("endTime");
        JSONArray scales = compo.getJSONArray("scale");
        JSONArray spacings = compo.getJSONArray("spacing");
        
        String folder = file.getParent().concat("\\images\\");
        
        for (int i = 0; i < images.length(); i++) {
            model.addFile(new File(folder.concat(images.getString(i))));
        }
        
        for (int i = 0; i < sources.length(); i++) {
            Picture pic = new Picture(sources.getInt(i),
                    startTimes.getDouble(i),
                    endTimes.getDouble(i),
                    spacings.getDouble(i),
                    scales.getDouble(i));
            model.addPicture(pic);
        }
        
        return model;
    }
    
    public void saveModel() throws IOException {
        FileWriter out = new FileWriter(model.getLocation());
        
        JSONArray fileNames = new JSONArray();
        for (File file : model.getFiles()) {
            fileNames.put(file.getName());
        }
        
        JSONArray sources = new JSONArray();
        JSONArray startTimes = new JSONArray();
        JSONArray endTimes = new JSONArray();
        JSONArray scales = new JSONArray();
        JSONArray spacings = new JSONArray();
        for (Picture pic : model.getPictures()) {
            sources.put(pic.getFileID());
            startTimes.put(pic.getStartTime());
            endTimes.put(pic.getEndTime());
            scales.put(pic.getScale());
            spacings.put(pic.getSpacing());
        }
        
        root.put("buff", fileNames);
        
        JSONObject compo = new JSONObject();
        compo.put("sourceId", sources);
        compo.put("startTime", startTimes);
        compo.put("endTime", endTimes);
        compo.put("scale", scales);
        compo.put("spacing", spacings);
        
        root.put("compo", compo);
        
        // TODO: timeActive / timeFull / (timeActiveStart)
        List<Picture> list = model.getPictures();
        double endTime = list.get(list.size() - 1).getEndTime();
        
        if (endTime > 120000.0) {
            root.put("timeFull", endTime);
        }
        root.put("timeActive", endTime);
        
        out.write(root.toString());
        out.flush();
    }
    
    private String fileToString(File file) throws IOException {
        FileReader in = new FileReader(file);
        String raw = "";
        
        int c;
        while ((c = in.read()) != -1) {
            raw += (char) c;
        }
        
        return raw;
    }
}
