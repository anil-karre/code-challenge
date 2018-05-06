package com.anilk.n26.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.anilk.n26.service.ITransactionStatisticsService;
import com.anilk.n26.vo.ExpiryWrapper;
import com.anilk.n26.vo.StatisticsVO;
import com.anilk.n26.vo.TransactionVO;

@Service
public class TransactionStatisticsServiceImpl implements ITransactionStatisticsService {

	private List<ExpiryWrapper<TransactionVO>> transactionsWithExpiry = Collections.synchronizedList(new ArrayList<>());
	private AtomicReference<StatisticsVO> atomicStatistics = new AtomicReference<StatisticsVO>(new StatisticsVO());

	@Value("${app.transaction.life-time.in-milli-sec}")
	private long transactionLifetime;

	@Override
	public boolean addTransaction(TransactionVO transactionVO) {
		long timeDiff = transactionVO != null ? System.currentTimeMillis() - transactionVO.getTimestamp() : -1;
		if (timeDiff > 0 && timeDiff < transactionLifetime) {
			transactionsWithExpiry.add(new ExpiryWrapper<TransactionVO>().setValue(transactionVO)
					.setExpiryTimestamp(transactionVO.getTimestamp() + transactionLifetime));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public StatisticsVO getStatistics() {
		return atomicStatistics.get();
	}

	@Scheduled(fixedRate = 250)
	private void cleanupExpiredTransactionsAndComputeStatistics() {
		List<ExpiryWrapper<TransactionVO>> transactions = new ArrayList<>();
		synchronized (transactionsWithExpiry) {
			transactionsWithExpiry = transactionsWithExpiry.parallelStream()
					.filter(transaction -> transaction.getExpiryTimestamp() > System.currentTimeMillis())
					.collect(Collectors.toList());
			transactions = new ArrayList<>(transactionsWithExpiry);
		}

		double val = 0, sum = 0, avg = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
		long count = 0;
		for (ExpiryWrapper<TransactionVO> expiryWrapper : transactions) {
			val = expiryWrapper.getValue().getAmount();
			sum = sum + val;
			max = val > max ? val : max;
			min = val < min ? val : min;
		}
		count = transactions.size();
		avg = count > 0 ? sum / count : 0;
		atomicStatistics.set(count <= 0 ? new StatisticsVO()
				: new StatisticsVO().setAvg(avg).setCount(count).setMax(max).setMin(min).setSum(sum));
	}

}
