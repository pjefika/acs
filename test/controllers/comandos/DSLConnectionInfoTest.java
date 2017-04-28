/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.comandos;

import entidades.dslConnectionInfo.Values;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.GsonUtil;

/**
 *
 * @author G0041775
 */
public class DSLConnectionInfoTest {

    public DSLConnectionInfoTest() {
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
     * Test of DSLConnectionInfoAction method, of class DSLConnectionInfo.
     */
    @Test
    public void testDSLConnectionInfoAction() {
        System.out.println("DSLConnectionInfoAction");
        try {
            Integer deviceId = 194859257;
            DSLConnectionInfo instance = new DSLConnectionInfo();
            instance.DSLConnectionInfoAction(deviceId);

            for (Values values : instance.getValuesDslConnection()) {
                System.out.println(GsonUtil.serialize(values));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("The test case is a prototype.");
        }

        // TODO review the generated test code and remove the default call to fail.
    }

}
