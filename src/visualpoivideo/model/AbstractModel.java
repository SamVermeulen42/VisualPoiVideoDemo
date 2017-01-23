package visualpoivideo.model;

import java.util.ArrayList;
import java.util.List;
import visualpoivideo.view.ViewInterface;

public abstract class AbstractModel {
    
    private List<ViewInterface> listeners = new ArrayList<>();
    
    public void changed() {
        for (ViewInterface view : listeners) {
            view.onChange();
        }
    }
    
    public void addListener(ViewInterface list) {
        listeners.add(list);
    }
    
}
