package com.anilk.n26.service;

import com.anilk.n26.vo.StatisticsVO;
import com.anilk.n26.vo.TransactionVO;

public interface ITransactionStatisticsService {

	/**
	 * To add a transaction
	 * @param transactionVO
	 * @return
	 */
	boolean addTransaction(TransactionVO transactionVO);
	
	/**
	 * To get statistics
	 * @return
	 */
	StatisticsVO getStatistics();

}
