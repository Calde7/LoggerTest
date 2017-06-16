package com.lfranco.logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
public class AppTest extends TestCase {
    
    Action action = new Action();
    
    public void testSetdbParam() {
        assertNull(action.dbParams);
    }    
    
    public void testSetSetLog() throws Exception {
        assertTrue(action.setLog());
    }
    
    public void testAskForType() {
        assertTrue(action.askForType() < 6 || action.askForType() > 0);
    }
    
    public void testAskForLog() {
        assertTrue(action.askForLog() < 4 || action.askForLog() > 4);
    }
    
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
