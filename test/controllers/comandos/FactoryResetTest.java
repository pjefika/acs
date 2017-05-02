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

/**
 *
 * @author G0041775
 */
public class FactoryResetTest {

    public FactoryResetTest() {
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
     * Test of factoryResetAction method, of class FactoryReset.
     */
    @Test
    public void testFactoryResetAction() {
        System.out.println("factoryResetAction");
        try {
            Integer deviceId = 196274866;
            String parametro = "";
            FactoryReset instance = new FactoryReset();
            instance.factoryResetAction(deviceId, parametro);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

}
