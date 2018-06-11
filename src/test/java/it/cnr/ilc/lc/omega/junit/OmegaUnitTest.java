/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.junit;

import it.cnr.ilc.lc.omega.test.DummyClassForJUnitTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author angelo
 */
public class OmegaUnitTest {
    static DummyClassForJUnitTest test;
    
    public OmegaUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        test = new DummyClassForJUnitTest();
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void provaJUnit(){
        assertEquals("il test è fallito!", "provaTesto-ok", DummyClassForJUnitTest.play("provaTesto"));
    }
    
    @Test
    public void provaJUnitBeforeClass(){
        assertEquals("il test è fallito!", "provaTesto-go", test.action("provaTesto"));
    }
    
    
}
