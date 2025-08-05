package org.miniwicket;

import java.util.*;

/**
 * Container component that can hold child components.
 * Demonstrates Wicket's hierarchical component architecture.
 */
public abstract class MarkupContainer extends Component {
    
    private final Map<String, Component> children = new LinkedHashMap<>();
    
    public MarkupContainer(String id) {
        super(id);
    }
    
    public MarkupContainer(String id, IModel<?> model) {
        super(id, model);
    }
    
    /**
     * Add a child component
     */
    public MarkupContainer add(Component... components) {
        for (Component component : components) {
            Objects.requireNonNull(component, "Component cannot be null");
            
            String id = component.getId();
            if (children.containsKey(id)) {
                throw new IllegalArgumentException("Component with id '" + id + "' already exists");
            }
            
            children.put(id, component);
            component.setParent(this);
        }
        return this;
    }
    
    /**
     * Remove a child component
     */
    public MarkupContainer remove(Component component) {
        if (component != null) {
            children.remove(component.getId());
            component.setParent(null);
            component.onRemove();
        }
        return this;
    }
    
    /**
     * Get a child component by ID
     */
    public Component get(String id) {
        return children.get(id);
    }
    
    /**
     * Get all child components
     */
    public Collection<Component> getChildren() {
        return Collections.unmodifiableCollection(children.values());
    }
    
    /**
     * Check if this container has children
     */
    public boolean hasChildren() {
        return !children.isEmpty();
    }
    
    /**
     * Render all child components
     */
    protected void renderChildren() {
        for (Component child : children.values()) {
            child.render();
        }
    }
    
    @Override
    protected void onRemove() {
        // Remove all children when this container is removed
        for (Component child : new ArrayList<>(children.values())) {
            remove(child);
        }
        super.onRemove();
    }
}