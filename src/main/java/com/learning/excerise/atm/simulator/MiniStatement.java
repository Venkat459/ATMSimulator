package com.learning.excerise.atm.simulator;

import java.util.List;

public class MiniStatement {

	public void printMiniStatement() {
		List<TransactionDetails> transactionDetails = TransactionHistoryDetails.getTransactionDetails();
		StringBuilder ministatement = null;
		if (transactionDetails != null && (!transactionDetails.isEmpty())) {
			// System.out.println("size: " + transactionDetails.size());
			ministatement = new StringBuilder();
			MiniStatementFormater miniStateFormatter = new MiniStatementFormater();
			ministatement.append(miniStateFormatter.basicFormatter());
			for (TransactionDetails transactionDetail : transactionDetails) {
				if (transactionDetail != null) {
					ministatement.append(formatTransactionDetails(transactionDetail));
				}
			}

		}
		System.out.println(ministatement);

	}

	public String formatTransactionDetails(TransactionDetails transactionDeail) {

		StringBuilder transactionDeailsFormat = null;
		String columnSepartaor = "\t";
		String lineSeparator = "\n";
		if (transactionDeail != null) {

			transactionDeailsFormat = new StringBuilder();
			transactionDeailsFormat.append(transactionDeail.getTransactionDate());
			transactionDeailsFormat.append(columnSepartaor);
			transactionDeailsFormat.append(columnSepartaor);
			transactionDeailsFormat.append(transactionDeail.getTransactionType());
			transactionDeailsFormat.append(columnSepartaor);
			transactionDeailsFormat.append(columnSepartaor);
			transactionDeailsFormat.append(transactionDeail.getAmount());
			transactionDeailsFormat.append(columnSepartaor);
			transactionDeailsFormat.append(columnSepartaor);
			transactionDeailsFormat.append(transactionDeail.getClosingBalance());
			transactionDeailsFormat.append(lineSeparator);
		}
		// System.out.println("transactionDeailsFormat:" +
		// transactionDeailsFormat.toString());
		return transactionDeailsFormat.toString();
	}
}
