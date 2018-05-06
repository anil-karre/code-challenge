package com.anilk.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anilk.n26.service.ITransactionStatisticsService;
import com.anilk.n26.vo.StatisticsVO;
import com.anilk.n26.vo.TransactionVO;

@RestController
public class TransactionStatisticsController {

	@Autowired
	private ITransactionStatisticsService transactionStatisticsService;

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public ResponseEntity<Object> addTransaction(@RequestBody TransactionVO transactionVO) {
		return transactionStatisticsService.addTransaction(transactionVO) ? new ResponseEntity<>(HttpStatus.CREATED)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ResponseEntity<StatisticsVO> getStatistics() {
		return new ResponseEntity<StatisticsVO>(transactionStatisticsService.getStatistics(), HttpStatus.OK);
	}

}
