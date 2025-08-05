package org.miniwicket;

import java.io.Serializable;
import java.util.*;

/**
 * Base class for all components in the Mini Wicket framework.
 * Demonstrates Wicket's component-based architecture.
 * 
 * Key concepts extracted from Apache Wicket:
 * - Hierarchical component structure
 * - Component lifecycle management  
 * - Markup ID binding
 * - Model support for data binding
 * - Serializable for session storage
 */
public abstract class Component implements Serializable {
    
    private final String id;
    private Component parent;
    private IModel<?> model;
    private boolean visible = true;
    private boolean enabled = true;
    
    /**
     * Constructor with component ID
     */
    public Component(String id) {
        this(id, null);
    }
    
    /**
     * Constructor with component ID and model
     */
    public Component(String id, IModel<?> model) {
        this.id = Objects.requireNonNull(id, "Component ID cannot be null");
        this.model = model;
    }
    
    /**
     * Get the component's unique identifier within its parent
     */
    public String getId() {
        return id;
    }
    
    /**
     * Get the component's model for data binding
     */
    public IModel<?> getModel() {
        return model;
    }
    
    /**
     * Set the component's model
     */
    public Component setModel(IModel<?> model) {
        this.model = model;
        return this;
    }
    
    /**
     * Get the model object (convenience method)
     */
    public Object getModelObject() {
        return model != null ? model.getObject() : null;
    }
    
    /**
     * Set the model object (convenience method)
     */
    public Component setModelObject(Object object) {
        if (model != null) {
            @SuppressWarnings("unchecked")
            IModel<Object> objectModel = (IModel<Object>) model;
            objectModel.setObject(object);
        }
        return this;
    }
    
    /**
     * Get the parent component
     */
    public Component getParent() {
        return parent;
    }
    
    /**
     * Set the parent component (internal use)
     */
    void setParent(Component parent) {
        this.parent = parent;
    }
    
    /**
     * Check if component is visible
     */
    public boolean isVisible() {
        return visible;
    }
    
    /**
     * Set component visibility
     */
    public Component setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }
    
    /**
     * Check if component is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }
    
    /**
     * Set component enabled state
     */
    public Component setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
    
    /**
     * Get the full path of this component (for debugging)
     */
    public String getPath() {
        if (parent == null) {
            return getId();
        }
        return parent.getPath() + ":" + getId();
    }
    
    /**
     * Component lifecycle: called before rendering
     */
    protected void onBeforeRender() {
        // Override in subclasses
    }
    
    /**
     * Component lifecycle: called after rendering
     */
    protected void onAfterRender() {
        // Override in subclasses
    }
    
    /**
     * Component lifecycle: called when component is removed
     */
    protected void onRemove() {
        // Override in subclasses
    }
    
    /**
     * Render the component to the response
     */
    protected abstract void onRender();
    
    /**
     * Final render method that manages lifecycle
     */
    public final void render() {
        if (!isVisible()) {
            return;
        }
        
        onBeforeRender();
        onRender();
        onAfterRender();
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + id + "]";
    }
}