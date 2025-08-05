package org.miniwicket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ComponentTest {
    
    private Component component;
    
    @BeforeEach
    void setUp() {
        component = new TestComponent("test");
    }
    
    @Test
    void testComponentId() {
        assertEquals("test", component.getId());
    }
    
    @Test
    void testComponentModel() {
        IModel<String> model = Model.of("Hello");
        component.setModel(model);
        
        assertEquals("Hello", component.getModelObject());
        
        component.setModelObject("World");
        assertEquals("World", component.getModelObject());
    }
    
    @Test
    void testComponentVisibility() {
        assertTrue(component.isVisible());
        
        component.setVisible(false);
        assertFalse(component.isVisible());
    }
    
    @Test
    void testComponentPath() {
        MarkupContainer parent = new TestContainer("parent");
        parent.add(component);
        
        assertEquals("parent:test", component.getPath());
    }
    
    // Test implementation of Component
    private static class TestComponent extends Component {
        public TestComponent(String id) {
            super(id);
        }
        
        @Override
        protected void onRender() {
            // Test implementation
        }
    }
    
    // Test implementation of MarkupContainer
    private static class TestContainer extends MarkupContainer {
        public TestContainer(String id) {
            super(id);
        }
        
        @Override
        protected void onRender() {
            renderChildren();
        }
    }
}