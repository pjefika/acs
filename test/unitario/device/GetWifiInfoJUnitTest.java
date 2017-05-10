/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitario.device;

import dal.arris.ComandoArris;
import dal.arris.DeviceDAO;
import dal.arris.RequestCapabilityDiagnosticComplex;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.wifiInfo.WifiInfoHolder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author G0042204
 */
public class GetWifiInfoJUnitTest {

    private WifiInfoHolder[] ret;

    public GetWifiInfoJUnitTest() {
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

    @Test
    public void getWifiInfo() throws Exception {
        DeviceDAO instance = new DeviceDAO();
        ComandoArris result = instance.request(new RequestCapabilityDiagnosticComplex(EnumCapabilityComplex.getLanWiFiInfo.name(), 194859257, "\"frequency\"=\"2.4Ghz\""));
        System.out.println(result.getResult());

    }
}
