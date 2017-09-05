package com.dish.ofm.service.codeChallenge083017.controller;

import com.dish.ofm.service.codeChallenge083017.service.CipherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CodeChallenge083017ControllerTest {
    private CodeChallenge083017Controller controller;

    @Mock
    CipherService cipherService;

    @Before
    public void setup() {
    }

    @Test
    public void controller_responds() throws Exception {
    }
}
