/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import test.InitValues;

/**
 *
 * @author G0042204
 */
public class DeviceStatusTest {

    public DeviceStatusTest() {
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
     * Test of getDeviceStatus method, of class DeviceStatus.
     */
    @Test
    public void testGetDeviceStatus() {
        try {
            System.out.println("getDeviceStatus");
            DeviceStatus instance = new DeviceStatus();
            instance.getDeviceStatus(InitValues.deviceId);
            Assert.assertTrue(instance.getDeviceStatusHolder().getStatus().equalsIgnoreCase("OK"));
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }

    }

}
