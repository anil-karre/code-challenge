package com.anilk.n26.service.impl;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import com.anilk.n26.N26CodeChallengeApplicationTests;
import com.anilk.n26.vo.StatisticsVO;
import com.anilk.n26.vo.TransactionVO;

public class TransactionStatisticsServiceImplTest extends N26CodeChallengeApplicationTests {

	@InjectMocks
	private TransactionStatisticsServiceImpl transactionStatisticsService;

	private Random random;

	@Value("${app.transaction.life-time.in-milli-sec}")
	private long lifetime;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		random = new Random();
		ReflectionTestUtils.setField(transactionStatisticsService, "transactionLifetime", lifetime);
	}

	@Test
	public void addTransactionThatHappenedInLast60Sec() {
		TransactionVO transactionVO = new TransactionVO().setAmount(random.nextDouble())
				.setTimestamp(System.currentTimeMillis() - 10000);
		boolean isAdded = transactionStatisticsService.addTransaction(transactionVO);
		Assert.assertTrue(isAdded);
	}

	@Test
	public void rejectTransactionThatHappened60SecAgo() {
		TransactionVO transactionVO = new TransactionVO().setAmount(random.nextDouble())
				.setTimestamp(System.currentTimeMillis() - 70000);
		boolean isAdded = transactionStatisticsService.addTransaction(transactionVO);
		Assert.assertFalse(isAdded);
	}

	@Test
	public void getStatisticsReturnsNotNullResult() {
		StatisticsVO statisticsVO = transactionStatisticsService.getStatistics();
		Assert.assertNotNull(statisticsVO);
	}

}
