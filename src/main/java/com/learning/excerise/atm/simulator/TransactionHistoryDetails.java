package com.learning.excerise.atm.simulator;

import java.util.LinkedList;
import java.util.List;

public class TransactionHistoryDetails {

	static List<TransactionDetails> transactionDetails = new LinkedList<>();

	public static List<TransactionDetails> getTransactionDetails() {
		return transactionDetails;
	}

	public static void setTransactionDetails(List<TransactionDetails> transactionDetailsSource) {
		transactionDetails = transactionDetailsSource;
	}

	public static void addTranaction(TransactionDetails transactionDetail) {
		transactionDetails.add(transactionDetail);
	}

}
