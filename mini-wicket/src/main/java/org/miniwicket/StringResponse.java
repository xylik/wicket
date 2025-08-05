package org.miniwicket;

/**
 * String-based response implementation for capturing output.
 */
public class StringResponse extends Response {
    
    private final StringBuilder buffer = new StringBuilder();
    
    @Override
    public void write(String text) {
        buffer.append(text);
    }
    
    /**
     * Get the accumulated output
     */
    public String getBuffer() {
        return buffer.toString();
    }
    
    /**
     * Clear the buffer
     */
    public void reset() {
        buffer.setLength(0);
    }
}