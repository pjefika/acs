/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitario.dal;

import dal.arris.request.SipDiagnosticsRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author G0042204
 */
public class SipDiagnosticsRequestJUnitTest {

    public SipDiagnosticsRequestJUnitTest() {
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
    public void sipDiagnosticsRequest() {
        SipDiagnosticsRequest r = new SipDiagnosticsRequest(194859257, "1");
        System.out.println(r.getRequestUrl());
    }
}
