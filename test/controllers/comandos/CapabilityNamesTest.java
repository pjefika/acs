/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import test.InitValues;

/**
 *
 * @author G0041775
 */
public class CapabilityNamesTest {

    public CapabilityNamesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of listCapabilityNamesFor method, of class CapabilityNames.
     */
    @Test
    public void testListCapabilityNamesFor() {
        System.out.println("listCapabilityNamesFor");
        try {
            Integer deviceId = InitValues.deviceId;
            Boolean ativo = true;
            CapabilityNames instance = new CapabilityNames();
            instance.listCapabilityNamesFor(deviceId, ativo);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

}
