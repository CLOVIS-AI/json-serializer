/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clovis.serializer;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.WriterConfig;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ivan
 */
public class SerializerTest {
    
    public SerializerTest() {
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
     * Test of addObject method, of class Serializer.
     */
    @Test
    public void testAddObject() {
        System.out.println("addObject");
        JsonObject j = new JsonObject();
        Tester1 t1 = new Tester1();
        Field fa = null;
        
        try {
            fa = t1.getClass().getDeclaredField("a");
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("Exception was raised: " + ex.getLocalizedMessage());
        }
        
        JsonSerializer aa = fa.getAnnotation(JsonSerializer.class);
        
        Serializer.addObject(j, fa, aa, t1);
        
        System.out.println(j.toString(WriterConfig.MINIMAL));
        
        assertTrue(j.contains("a"));
        assertEquals(2, j.getInt("a", 0));
    }
    
    class Tester1 {
        @JsonSerializer(fieldName="a")
        int a = 2;
        
        @JsonSerializer(fieldName="b")
        boolean b = true;
    }
    
}
