package org.miniwicket;

/**
 * Request cycle manages the processing of a single request.
 * Simplified version of Wicket's RequestCycle.
 */
public class RequestCycle {
    
    private static final ThreadLocal<RequestCycle> CURRENT = new ThreadLocal<>();
    
    private Response response;
    
    public RequestCycle(Response response) {
        this.response = response;
    }
    
    /**
     * Get the current request cycle
     */
    public static RequestCycle get() {
        return CURRENT.get();
    }
    
    /**
     * Set the current request cycle for this thread
     */
    public static void set(RequestCycle cycle) {
        CURRENT.set(cycle);
    }
    
    /**
     * Clear the current request cycle
     */
    public static void clear() {
        CURRENT.remove();
    }
    
    /**
     * Get the response for this request cycle
     */
    public Response getResponse() {
        return response;
    }
    
    /**
     * Set the response (for internal processing)
     */
    public void setResponse(Response response) {
        this.response = response;
    }
}