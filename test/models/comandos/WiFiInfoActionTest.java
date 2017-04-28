/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.comandos;

import entidades.wifiInfo.WifiConf;
import entidades.wifiInfo.WifiInfoHolder;
import model.EnumFrequency;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author G0042204
 */
public class WiFiInfoActionTest {

    public WiFiInfoActionTest() {
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
     * Test of getWiFiInfo method, of class WiFiInfoAction.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetWiFiInfo() throws Exception {

        WifiConf in = new WifiConf(EnumFrequency.DEFAULT);
        Integer deviceId = 194859257;
        String frequency = "1";
        WiFiInfoAction instance = new WiFiInfoAction();

        WifiInfoHolder[] result = instance.getWiFiInfo(deviceId, null, frequency);
        System.out.println("");
    }

}
