package com.dish.ofm.service.codeChallenge083017.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CipherServiceTest {

    private CipherService cipherService;

    @Before
    public void setup() {
    }

    @Test
    public void testEncode1() throws Exception {
        cipherService = new CipherService("B");
        String output = cipherService.encode("ABCD");
        assertThat(output, equalTo("BCDE"));
    }

    @Test
    public void testEncode2() throws Exception {
        cipherService = new CipherService("X");
        String output = cipherService.encode("I AM GOING TO WIN");
        assertThat(output, equalTo("F XJ DLFKD QL TFK"));
    }

    @Test
    public void testEncode3() throws Exception {
        cipherService = new CipherService("H");
        String output = cipherService.encode("I AM CLOSE TO WINNING");
        assertThat(output, equalTo("Q IU KTWAM BW EQVVQVO"));
    }

    @Test
    public void testDecode1() throws Exception {
        cipherService = new CipherService("B");
        String output = cipherService.decode("BCDE");
        assertThat(output, equalTo("ABCD"));
    }

    @Test
    public void testDecode2() throws Exception {
        cipherService = new CipherService("X");
        String output = cipherService.decode("F XJ DLFKD QL TFK");
        assertThat(output, equalTo("I AM GOING TO WIN"));
    }

    @Test
    public void testDecode3() throws Exception {
        cipherService = new CipherService("H");
        String output = cipherService.decode("Q IU KTWAM BW EQVVQVO");
        assertThat(output, equalTo("I AM CLOSE TO WINNING"));
    }
}
