package org.miniwicket;

/**
 * Page component - top level container in the component hierarchy.
 * Demonstrates Wicket's page-based architecture.
 */
public abstract class Page extends MarkupContainer {
    
    private static int nextPageId = 1;
    private final int pageId;
    
    public Page() {
        super("page" + nextPageId);
        this.pageId = nextPageId++;
    }
    
    /**
     * Get unique page identifier
     */
    public int getPageId() {
        return pageId;
    }
    
    /**
     * Get the HTML markup for this page
     */
    protected abstract String getMarkup();
    
    @Override
    protected void onRender() {
        String markup = getMarkup();
        // Process markup and render components
        String processedMarkup = processMarkup(markup);
        
        // Output the processed markup
        getResponse().write(processedMarkup);
    }
    
    /**
     * Process the markup template and replace component placeholders
     */
    private String processMarkup(String markup) {
        String processed = markup;
        
        // Simple markup processing - replace wicket:id attributes
        for (Component child : getChildren()) {
            String placeholder = "wicket:id=\"" + child.getId() + "\"";
            if (processed.contains(placeholder)) {
                // Render component content
                StringBuilder componentOutput = new StringBuilder();
                Response originalResponse = getResponse();
                StringResponse stringResponse = new StringResponse();
                setResponse(stringResponse);
                
                child.render();
                componentOutput.append(stringResponse.getBuffer());
                
                setResponse(originalResponse);
                
                // Replace the content between tags
                String pattern = "<[^>]*" + placeholder + "[^>]*>.*?</[^>]*>";
                String replacement = "<span id=\"" + child.getId() + "\">" + componentOutput + "</span>";
                processed = processed.replaceAll(pattern, replacement);
            }
        }
        
        return processed;
    }
    
    /**
     * Get current response (simplified - in real app this would come from request cycle)
     */
    private Response getResponse() {
        return RequestCycle.get().getResponse();
    }
    
    /**
     * Set response (for internal processing)
     */
    private void setResponse(Response response) {
        RequestCycle.get().setResponse(response);
    }
}