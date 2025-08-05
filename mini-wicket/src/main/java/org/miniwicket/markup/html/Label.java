package org.miniwicket.markup.html;

import org.miniwicket.Component;
import org.miniwicket.IModel;
import org.miniwicket.Model;

/**
 * A label component that displays text.
 * Demonstrates basic component with model binding.
 */
public class Label extends Component {
    
    public Label(String id) {
        super(id);
    }
    
    public Label(String id, String text) {
        super(id, Model.of(text));
    }
    
    public Label(String id, IModel<String> model) {
        super(id, model);
    }
    
    @Override
    protected void onRender() {
        Object modelObject = getModelObject();
        String text = modelObject != null ? modelObject.toString() : "";
        
        // In a real implementation, this would write to the response
        // For now, we'll store it in a way that can be retrieved
        getRequestCycle().getResponse().write(text);
    }
    
    private org.miniwicket.RequestCycle getRequestCycle() {
        return org.miniwicket.RequestCycle.get();
    }
}