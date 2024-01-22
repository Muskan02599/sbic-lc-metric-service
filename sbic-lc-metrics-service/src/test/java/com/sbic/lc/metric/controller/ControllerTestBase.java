package com.sbic.lc.metric.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sbic.lc.metric.config.ControllerTestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ControllerTestConfig
public class ControllerTestBase {

	@Autowired protected MockMvc mockMvc;
	@Autowired protected ObjectMapper objectMapper;

	
	protected Logger LOGGER = LoggerFactory.getLogger(ControllerTestBase.class);

	protected String token = "agent246";
	protected String apiKey = "2eqweds5624pk3r-3o4dummy";

	protected ObjectMapper omapper = new ObjectMapper();

	public ControllerTestBase() {
		omapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	protected String getPrettyJSON(JsonNode node) {
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			String pretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
			return pretty;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	protected MockHttpServletRequestBuilder buildGet(String url) {
		return get(url)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
		.with(csrf());
	}
	
	protected MockHttpServletRequestBuilder buildGetInternal(String url) {
		return get(url)
				.header(HttpHeaders.AUTHORIZATION, "App " + apiKey)
				.with(csrf());
	}
		
	protected MockHttpServletRequestBuilder buildPost(String url) {
		return post(url)
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
		.with(csrf());
	}
	
	protected MockHttpServletRequestBuilder buildPostInternal(String url) {
		return post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "App " + apiKey)
				.with(csrf());
	}

	protected MockHttpServletRequestBuilder buildPostWithNoToken(String url) {
		return post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf());
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected MockHttpServletRequestBuilder buildPut(String url) {
		return MockMvcRequestBuilders.put(url)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.with(csrf());
	}
	
	protected MockHttpServletRequestBuilder buildPutWithNoToken(String url) {
		return MockMvcRequestBuilders.put(url)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.with(csrf());
	}
	
	protected MockHttpServletRequestBuilder buildDelete(String url) {
		return MockMvcRequestBuilders.delete(url)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
				.with(csrf());
	}
	

}