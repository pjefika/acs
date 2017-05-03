/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import dal.arris.DeviceDAO;
import dal.arris.RequestCapabilityDiagnosticComplex;
import dal.arris.capability.EnumCapabilityComplex;
import entidades.ping.PingIn;
import entidades.ping.PingOut;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import test.InitValues;
import util.GsonUtil;

/**
 *
 * @author G0042204
 */
public class GetPingTest {

    protected DeviceDAO dao = new DeviceDAO();
    private PingOut pingOut;

    public GetPingTest() {
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
     * Test of PingAction method, of class GetPing.
     */
    @Test
    public void testPingAction() {
        Integer deviceId = InitValues.deviceId;

        String response;
        try {
            response = dao.request(new RequestCapabilityDiagnosticComplex(EnumCapabilityComplex.Ping.name(), deviceId, new PingIn())).getResult();
            this.pingOut = (PingOut) GsonUtil.convert(response, PingOut.class);
            System.out.println("end");
        } catch (IOException ex) {
            Logger.getLogger(GetPingTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        fail("The test case is a prototype.");
    }

}
