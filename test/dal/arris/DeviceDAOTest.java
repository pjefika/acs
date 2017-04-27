/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris;

import dal.arris.capability.EnumCapabilitySimple;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author G0042204
 */
public class DeviceDAOTest {

    public DeviceDAOTest() {
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
     * Test of request method, of class DeviceDAO.
     */
    @Test
    public void testRequest() throws Exception {

        for (EnumCapabilitySimple value : EnumCapabilitySimple.values()) {
            System.out.println("request");
            EnumCapabilitySimple c = value;
            Integer deviceId = 194859257;
            DeviceDAO instance = new DeviceDAO();
            RequestCapabilityDiagnosticSimple expResult = null;
            RequestCapabilityDiagnosticSimple result = instance.request(c, deviceId);
            System.out.println(result.getResult());
            // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        }

    }

}
