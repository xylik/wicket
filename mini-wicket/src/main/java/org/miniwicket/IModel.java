package org.miniwicket;

import java.io.Serializable;

/**
 * Model interface for data binding in Mini Wicket.
 * Extracted from Apache Wicket's IModel pattern.
 * 
 * Key concepts:
 * - Encapsulates data access and modification
 * - Supports lazy loading and detachment for scalability
 * - Type-safe generic interface
 */
public interface IModel<T> extends Serializable {
    
    /**
     * Gets the model object
     * @return the model object
     */
    T getObject();
    
    /**
     * Sets the model object
     * @param object the object to set
     */
    void setObject(T object);
    
    /**
     * Detaches the model after use to free resources.
     * This is called at the end of request processing.
     */
    default void detach() {
        // Default implementation does nothing
    }
}