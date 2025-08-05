package org.miniwicket;

/**
 * Simple response abstraction for writing output.
 * Simplified version of Wicket's Response class.
 */
public abstract class Response {
    
    /**
     * Write text to the response
     */
    public abstract void write(String text);
    
    /**
     * Write a character sequence to the response
     */
    public void write(CharSequence cs) {
        write(cs.toString());
    }
}