/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitario.dal;

import dal.arris.RequestArrisAlter;
import java.io.IOException;
import model.AuthFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author G0042204
 */
public class RequestArrisJUnitTest {

    public RequestArrisJUnitTest() {
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
    public void requestArris() throws IOException {

//        RequestArrisAlter r = new RequestArrisAlter(8328571, AuthFactory.getEnd(), "{\"frequency\":\"2.4GHz\"}");
        RequestArrisAlter c = new RequestArrisAlter(194859257, AuthFactory.getEnd(), "{\"frequency\":\"2.4GHz\"}");

        // 8328571
    }
}
