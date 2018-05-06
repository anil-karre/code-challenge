package com.anilk.n26.service;

import com.anilk.n26.vo.StatisticsVO;
import com.anilk.n26.vo.TransactionVO;

public interface ITransactionStatisticsService {

	boolean addTransaction(TransactionVO transactionVO);
	
	StatisticsVO getStatistics();

}
