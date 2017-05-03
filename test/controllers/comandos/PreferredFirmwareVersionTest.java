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
public class PreferredFirmwareVersionTest {

    public PreferredFirmwareVersionTest() {
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
     * Test of buscaFirmware method, of class PreferredFirmwareVersion.
     */
    @Test
    public void testBuscaFirmware() throws Exception {
        System.out.println("buscaFirmware");
        try {
            Integer deviceId = InitValues.deviceId;
            PreferredFirmwareVersion instance = new PreferredFirmwareVersion();
            instance.buscaFirmware(deviceId);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

}
