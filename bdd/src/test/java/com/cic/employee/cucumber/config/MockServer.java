package com.cic.employee.cucumber.config;

import com.cic.employee.cucumber.dto.ApiBinding;
import com.cic.employee.cucumber.dto.MockBinder;
import com.cic.employee.cucumber.dto.MockBindings;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class MockServer {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    private final Map<String,MediaType> mediaTypeMap= Map.of("JSON",MediaType.APPLICATION_JSON, "key2", MediaType.APPLICATION_XML);

    @PostConstruct
    public void setUp(){
        mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
    }

    public void reinitialize(){
        if(mockServer !=null){
            mockServer.reset();
        }
    }
    
    public void configure(String api, String responseKey, Map<String, String> placeholder, int times){
        MockBindings mockBindings = MockBinder.INSTANCE.getBindings();
        this.validateMock(api,mockBindings);
        final ApiBinding apiBinding = mockBindings.getBindings().get(api);
        boolean isBadRequest =false;
        boolean isUnAuthorized = false;

        if(responseKey==null){
            log.warn("No response key found..");
            responseKey= "DEFAULT";
        }

        Map<String,String> responses = apiBinding.getResponses();

        if(responses==null){
            log.warn("No response not found for the api {}, marking it a bad request",api);
            isBadRequest = true;
        }

        String response = responses == null ? null: responses.get(responseKey);

        response = substitutePlaceHolders(placeholder,response);

        String url =apiBinding.getUrl();

        if(url==null){
            throw new RuntimeException("Url not found");
        }
        url = substitutePlaceHolders(placeholder,apiBinding.getUrl());

        HttpMethod method = HttpMethod.GET;

        if(apiBinding.getHttpMethod()!=null){
            method=apiBinding.getHttpMethod();
        }

        mockServer.expect(
                ExpectedCount.times((apiBinding.getRepetitions() == null ? times: apiBinding.getRepetitions())),
                MockRestRequestMatchers.requestTo(url))
                .andExpect(MockRestRequestMatchers.method(method))
                .andRespond(getMockResponse(isBadRequest,isUnAuthorized,response,apiBinding));
    }

    private String substitutePlaceHolders(Map<String, String> placeholder, String response) {
        if(placeholder!=null){
            for(Map.Entry<String,String> entry : placeholder.entrySet()){
                String key = "\\$\\{\\{"+ entry.getKey() +"\\}\\}";
                String value =entry.getValue();
                response = response ==null ? null : response.replaceAll(key,value);
            }
        }
        return response;
    }

    private DefaultResponseCreator getMockResponse(boolean isBadRequest, boolean isUnAuthorized, String responseString, ApiBinding apiBinding) {
        DefaultResponseCreator response  = MockRestResponseCreators.withBadRequest();

        if(isUnAuthorized){
            response = MockRestResponseCreators.withUnauthorizedRequest();
        } else if (!isBadRequest) {
            response =MockRestResponseCreators.withSuccess(responseString,
                    apiBinding.getMediaType()== null ? MediaType.APPLICATION_JSON : mediaTypeMap.get(apiBinding.getMediaType()));
        }

        return response;
    }

    private void validateMock(String api, MockBindings mockBindings) {
        if(this.mockServer==null){
            throw new RuntimeException("Spring Mock Rest server was null...");
        } else if (api==null || mockBindings.getBindings().get(api)==null) {
            throw new RuntimeException("Api key is not configured. Add binding in binding.json");
        }
    }
}
