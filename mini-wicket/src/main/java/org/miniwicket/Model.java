package org.miniwicket;

/**
 * Simple model implementation that holds a value.
 * Demonstrates basic model pattern from Wicket.
 */
public class Model<T> implements IModel<T> {
    
    private T object;
    
    public Model(T object) {
        this.object = object;
    }
    
    /**
     * Create a model with the given object
     */
    public static <T> Model<T> of(T object) {
        return new Model<>(object);
    }
    
    @Override
    public T getObject() {
        return object;
    }
    
    @Override
    public void setObject(T object) {
        this.object = object;
    }
    
    @Override
    public String toString() {
        return "Model[" + object + "]";
    }
}