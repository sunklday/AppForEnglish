package com.jiai.sun.appforenglist;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void randowtest() throws Exception {

        for(int i =1 ;i<112;i++){
            double buttonId = Math.random()*4;
            System.out.println((int)buttonId);

        }
    }
}