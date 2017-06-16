/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lfranco.logger;

import java.sql.SQLException;
import java.util.Map;
import static junit.framework.Assert.assertTrue;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author lfranco
 */
public class JobTest extends TestCase {
    
    JobLogger job;
    
    public void testwriteLogFile() {
        Map<String, String> dbParams = new Action().dbParams;
        job = new JobLogger(true, false, false, dbParams);
        assertTrue(job.writeLogFile(1, "Test write to file"));
    }
    
    public void testAPrintForConsole() {
        Map<String, String> dbParams = new Action().dbParams;
        job = new JobLogger(true, false, false, dbParams);
        assertTrue(job.printConsole(1, "Test print to console"));
    }
    
    public void testSaveToDB() throws SQLException {
        Map<String, String> dbParams = new Action().dbParams;
        job = new JobLogger(true, false, false, dbParams);
        assertTrue(job.saveLogDb(1, "Test print to console"));
    }
    
    public static Test suite() {
        return new TestSuite(JobLogger.class);
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
}
