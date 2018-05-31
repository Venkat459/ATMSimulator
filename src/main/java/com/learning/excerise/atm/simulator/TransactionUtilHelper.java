package com.learning.excerise.atm.simulator;

public class TransactionUtilHelper {

	public static TransactionDetails previousTransactionDetails = null;

	public static void calculateClosingBalance(TransactionDetails currentTranaction) {

		if (previousTransactionDetails == null) {

			currentTranaction.setClosingBalance(currentTranaction.getAmount());
		} else {
			String currrentTransactionType = currentTranaction.getTrasnsactionType().name();

			switch (currrentTransactionType) {

			case "CREDIT": {
				currentTranaction
						.setClosingBalance(previousTransactionDetails.closingBalance + currentTranaction.amount);

				// System.out.println("previousTransaction");
				break;
			}

			case "DEBIT": {
				currentTranaction
						.setClosingBalance(previousTransactionDetails.closingBalance - currentTranaction.amount);
				// System.out.println("previousTransaction");
				break;
			}

			}

		}

		previousTransactionDetails = currentTranaction;
	}

}
