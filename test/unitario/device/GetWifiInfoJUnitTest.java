/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitario.device;

import entidades.wifiInfo.WifiInfoHolder;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AuthFactory;
import models.comandos.WiFiInfoAction;
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
    public void getWifiInfo() {

        WiFiInfoAction dao = new WiFiInfoAction();

        try {
            ret = dao.getWiFiInfo(8328571, AuthFactory.getEnd(), "{\"frequency\":\"2.4GHz\"}");
            System.out.println("Fim");
        } catch (Exception ex) {
            Logger.getLogger(GetWifiInfoJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
