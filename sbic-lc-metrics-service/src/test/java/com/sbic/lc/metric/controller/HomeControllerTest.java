package com.sbic.lc.metric.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

class HomeControllerTest extends ControllerTestBase {

	@Test
	void healthcheck() throws Exception {
		String uri = "/healthcheck";
		MockHttpServletRequestBuilder bldr = buildGet(uri);
		MvcResult mvcResult = mockMvc.perform(bldr).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		assertTrue(content.contains("Application Name: [SBIC Live Chat Metric Service]"));
	}
}

