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
public class PortMappingTableTest {

    public PortMappingTableTest() {
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
     * Test of getPortMappingTableAction method, of class PortMappingTable.
     */
    @Test
    public void testGetPortMappingTableAction() {
        System.out.println("getPortMappingTableAction");
        try {
            Integer deviceId = InitValues.deviceId;
            PortMappingTable instance = new PortMappingTable();
            instance.getPortMappingTableAction(deviceId);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

}
