/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import test.InitValues;

/**
 *
 * @author G0042204
 */
public class GetPPPoECredentialsTest {

    public GetPPPoECredentialsTest() {
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
     * Test of getPPPoECredentialAction method, of class GetPPPoECredentials.
     */
    @Test
    public void testGetPPPoECredentialAction() {
        System.out.println("getPPPoECredentialAction");
        GetPPPoECredentials instance = new GetPPPoECredentials();
        instance.setDeviceId(InitValues.deviceId);
        instance.getPPPoECredentialAction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
