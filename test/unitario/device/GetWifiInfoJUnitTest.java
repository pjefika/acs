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
import static org.junit.Assert.assertTrue;
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
            // :112239590:194859257
            ret = dao.getWiFiInfo(194859257, AuthFactory.getEnd(), "{\"frequency\":\"2.4GHz\"}");
            assertTrue(ret != null);
        } catch (Exception ex) {
            assertTrue(false);
            Logger.getLogger(GetWifiInfoJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
    }
}
