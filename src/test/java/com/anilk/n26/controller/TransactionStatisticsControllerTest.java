package com.anilk.n26.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.anilk.n26.N26CodeChallengeApplicationTests;
import com.anilk.n26.service.impl.TransactionStatisticsServiceImpl;
import com.anilk.n26.vo.StatisticsVO;
import com.anilk.n26.vo.TransactionVO;
import com.google.gson.Gson;

public class TransactionStatisticsControllerTest extends N26CodeChallengeApplicationTests {

	private MockMvc mockMvc;

	private Gson gson;

	private Random random;

	@Mock
	private TransactionStatisticsServiceImpl transactionStatisticsService;

	@InjectMocks
	private TransactionStatisticsController transactionStatisticsController;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(transactionStatisticsController).build();
		MockitoAnnotations.initMocks(this);
		gson = new Gson();
		random = new Random();
	}

	@Test
	public void returnCreatedStatusIfTransactionIsAdded() throws Exception {
		TransactionVO transactionVO = new TransactionVO().setAmount(random.nextDouble())
				.setTimestamp(System.currentTimeMillis() - 10000);
		when(transactionStatisticsService.addTransaction(any(TransactionVO.class))).thenReturn(true);
		mockMvc.perform(
				post("/transactions").content(gson.toJson(transactionVO)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated());
	}

	@Test
	public void returnNoContentStatusIfTransactionIsRejected() throws Exception {
		TransactionVO transactionVO = new TransactionVO().setAmount(random.nextDouble())
				.setTimestamp(System.currentTimeMillis() - 70000);
		when(transactionStatisticsService.addTransaction(any(TransactionVO.class))).thenReturn(false);
		mockMvc.perform(
				post("/transactions").contentType(MediaType.APPLICATION_JSON_VALUE).content(gson.toJson(transactionVO)))
				.andExpect(status().isNoContent());
	}

	@Test
	public void getStatisticsReturnsNotNullResult() throws Exception {
		when(transactionStatisticsService.getStatistics()).thenReturn(new StatisticsVO());
		MvcResult mvcResult = mockMvc.perform(get("/statistics")).andExpect(status().isOk()).andReturn();
		StatisticsVO statisticsVO = gson.fromJson(mvcResult.getResponse().getContentAsString(), StatisticsVO.class);
		Assert.assertNotNull(statisticsVO);
	}

}
