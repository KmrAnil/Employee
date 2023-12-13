package com.cic.employee.cucumber.dto;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public enum MockBinder {
    INSTANCE;


    private MockBindings mockBindings;

    MockBinder(){
        try{
            String json =
                    IOUtils.toString(MockBinder.class.getResourceAsStream
                            ("/mock-binding/binding.json"), Charset.defaultCharset());
            mockBindings = new ObjectMapper().readValue(json, MockBindings.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MockBindings getBindings() {
        return this.mockBindings;
    }
}
