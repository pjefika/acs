/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitario.dal;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilityExecuteSimple;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author G0042204
 */
public class RequestCapabilityExecuteRequestJUnitTest {

    public RequestCapabilityExecuteRequestJUnitTest() {
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
    public void requestCapabilityExecute() {
        EnumCapabilityExecuteSimple[] e = EnumCapabilityExecuteSimple.values();
        for (EnumCapabilityExecuteSimple enu : e) {
            RequestCapabilityExecute r = new RequestCapabilityExecute(enu.name(), 194859257);
            System.out.println(r.getRequestUrl());
        }

    }
}
