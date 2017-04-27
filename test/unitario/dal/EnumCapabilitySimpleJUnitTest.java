/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitario.dal;

import dal.arris.RequestCapabilityDiagnosticSimple;
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
public class EnumCapabilitySimpleJUnitTest {

    public EnumCapabilitySimpleJUnitTest() {
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
    public void requestCapabilityDiagnosticSimple() {
        EnumCapabilitySimple[] e = EnumCapabilitySimple.values();

        System.out.println("EnumCapabilitySimple");

        for (EnumCapabilitySimple enumCapability : e) {
            RequestCapabilityDiagnosticSimple s = new RequestCapabilityDiagnosticSimple(enumCapability, 194859257);
            System.out.println(s.getRequestUrl());
        }
        System.out.println("--------------------");
    }
}
