/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import entidades.wifiInfo.WifiInfoHolder;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import test.InitValues;
import util.GsonUtil;

/**
 *
 * @author G0041775
 */
public class GetWiFiConfigTest {

    public GetWiFiConfigTest() {
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
     * Test of consultar method, of class GetWiFiConfig.
     */
    @Test
    public void buscaInformacoesWifi() {
        System.out.println("consultar");
        try {
            Integer deviceId = InitValues.deviceId;
            WifiConfig instance = new WifiConfig();
            instance.buscaInformacoesWifi(deviceId);

            for (WifiInfoHolder infoHolder : instance.getInfoHolder()) {
                System.out.println(GsonUtil.serialize(infoHolder));
            }
            assertTrue(instance.getInfoHolder() != null);
        } catch (Exception e) {
            e.printStackTrace();
            fail("The test case is a prototype.");
        }

        // TODO review the generated test code and remove the default call to fail.
    }

}
