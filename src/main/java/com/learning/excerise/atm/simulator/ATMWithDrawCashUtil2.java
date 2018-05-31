package com.learning.excerise.atm.simulator;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ATMWithDrawCashUtil2 {

	public static final Integer DENOMONATION_50 = 50;
	public static final Integer DENOMONATION_20 = 20;
	public static final Integer DENOMONATION_10 = 10;

	public static Map<Integer, Integer> atmDepoistedCurrencies = new TreeMap<>();
	public Map<Integer, Integer> atmDispenseCurrencies = new TreeMap<>(Collections.reverseOrder());

	public static Map<Integer, Integer> getAtmDepoistedCurrencies() {
		return atmDepoistedCurrencies;
	}

	public static void setAtmDepoistedCurrencies(Map<Integer, Integer> atmDepoistedCurrencies) {
		ATMWithDrawCashUtil.atmDepoistedCurrencies.putAll(atmDepoistedCurrencies);
		// ATMWithDrawCashUtil.atmDepoistedCurrencies = atmDepoistedCurrencies;
	}

	public Map<Integer, Integer> getAtmDispenseCurrencies() {
		return atmDispenseCurrencies;
	}

	public void setAtmDispenseCurrencies(Map<Integer, Integer> atmDispenseCurrencies) {
		this.atmDispenseCurrencies = atmDispenseCurrencies;
	}

	public void validateATMWithdrawAmount(Integer amountToWithDraw) throws Exception {
		if (amountToWithDraw <= 0) {
			throw new Exception(
					"Invalid amount is enterted to withDraw, please enter positive integer value which is multiple of 10s");

		}
		if (amountToWithDraw % 10 != 0) {
			throw new Exception(
					"Invalid amount is enterted to withDraw as it is not multiples of 10s, please enter integer value which is multiple of 10s");

		}
		if (amountToWithDraw > AvaliableBalance.getAvalibaleBalance()) {
			throw new Exception(
					"There is no sufficent money to dispense money, please enter the amount less than or equal to avaliable balance:"
							+ AvaliableBalance.getAvalibaleBalance());
		}
		
	}

	/**
	 * @param amountToWithDraw
	 * @return TODO : Needs correct logic, negative cases is not working and it is
	 *         un acceptable behavior For example: ATM has two 50s and and user
	 *         enters amount 80 and data is inconsistent.
	 */
	public boolean processWithDraw(Integer amountToWithDraw, ATMWithDrawDenominations atmWithDrawDenominations ) {
		Boolean isSuccess = true;

		ATMWithDrawCurrencyDetails atmWithDrawCurrencyDetails = new ATMWithDrawCurrencyDetails();
		
		atmWithDrawCurrencyDetails.setTotalAmountToWithDraw(amountToWithDraw);
		atmWithDrawCurrencyDetails.setRemainingBalance(amountToWithDraw);
		processWithDraw_With_50_Denominations(atmWithDrawCurrencyDetails, atmWithDrawDenominations);
		if (!isWithDrawReady(atmWithDrawCurrencyDetails)) {
			processWithDraw_With_20_Denominations(atmWithDrawCurrencyDetails, atmWithDrawDenominations);
		}

		if (!isWithDrawReady(atmWithDrawCurrencyDetails)) {
			processWithDraw_With_10_Denominations(atmWithDrawCurrencyDetails, atmWithDrawDenominations);
		}

		if (!isWithDrawReady(atmWithDrawCurrencyDetails)) {
			processWithDraw_With_20_Denominations(atmWithDrawCurrencyDetails, atmWithDrawDenominations);
		}

		isSuccess = isWithDrawReady(atmWithDrawCurrencyDetails);
		return isSuccess;

	}

	public Boolean isWithDrawReady(ATMWithDrawCurrencyDetails atmWithDrawCurrencyDetails) {
		Boolean isWithDrawReadyFlag = false;
		if (atmWithDrawCurrencyDetails != null) {
			int proccessedAmount = atmWithDrawCurrencyDetails.getProcessedAmount();
			int remainingBalance = atmWithDrawCurrencyDetails.getRemainingBalance();
			int totalAmountToWithDraw = atmWithDrawCurrencyDetails.getTotalAmountToWithDraw();
			if ((proccessedAmount == totalAmountToWithDraw) && (remainingBalance == 0)) {
				isWithDrawReadyFlag = true;
			}
		}

		return isWithDrawReadyFlag;
	}

	public void processWithDraw_With_50_Denominations(ATMWithDrawCurrencyDetails atmWithDrawCurrencyDetails,
			ATMWithDrawDenominations atmWithdrawDenominations) {

		Boolean DENOMONATION_50_FLAG = DepositUtil.atmDepoistedCurrencies.containsKey(DENOMONATION_50);
		Double atmAvaliableBalance = AvaliableBalance.getAvalibaleBalance();

		Integer total_50_currency_deposited_values = null;
		if (DENOMONATION_50_FLAG) {
			total_50_currency_deposited_values = DepositUtil.atmDepoistedCurrencies.get(DENOMONATION_50);
		}

		int amountToWithDraw = atmWithDrawCurrencyDetails.getTotalAmountToWithDraw();
		int origRemainingBalanceToWithDraw = atmWithDrawCurrencyDetails.getRemainingBalance();
		int origProcessedAmount = atmWithDrawCurrencyDetails.getProcessedAmount();
		int processedAmount = 0;
		int remainingBalanceToWithDraw = 0;
		int finalProcessedAmount = 0;
		if (atmAvaliableBalance >= amountToWithDraw && amountToWithDraw >= DENOMONATION_50
				&& (total_50_currency_deposited_values != null) && origRemainingBalanceToWithDraw > 0) {
			// System.out.println("50 flag");
			int totalRequiredCurrencyNotes = origRemainingBalanceToWithDraw / DENOMONATION_50;
			// remainingBalanceToWithDraw = remainingBalanceToWithDraw % DENOMONATION_50;

			// System.out.println("required 50 currency notes:" +
			// totalRequiredCurrencyNotes);
			// System.out.println("remainingBalanceToWithDraw:" +
			// remainingBalanceToWithDraw);

			// System.out.println("total_50_currency_deposited_values:" +
			// total_50_currency_deposited_values);
			if (total_50_currency_deposited_values == totalRequiredCurrencyNotes) {
				processedAmount = total_50_currency_deposited_values * DENOMONATION_50;
				atmWithdrawDenominations.setFiftyDenominations(total_50_currency_deposited_values);
			} else if (total_50_currency_deposited_values < totalRequiredCurrencyNotes) {
				processedAmount = total_50_currency_deposited_values * DENOMONATION_50;
				atmWithdrawDenominations.setFiftyDenominations(total_50_currency_deposited_values);

			} else if (total_50_currency_deposited_values > totalRequiredCurrencyNotes) {
				processedAmount = totalRequiredCurrencyNotes * DENOMONATION_50;
				atmWithdrawDenominations.setFiftyDenominations(totalRequiredCurrencyNotes);
			}

			remainingBalanceToWithDraw = origRemainingBalanceToWithDraw - processedAmount;
			finalProcessedAmount = origProcessedAmount + processedAmount;
			atmWithDrawCurrencyDetails.setRemainingBalance(remainingBalanceToWithDraw);
			atmWithDrawCurrencyDetails.setProcessedAmount(finalProcessedAmount);
		}
	}

	public void processWithDraw_With_20_Denominations(ATMWithDrawCurrencyDetails atmWithDrawCurrencyDetails,
			ATMWithDrawDenominations atmWithdrawDenominations) {

		Boolean DENOMONATION_20_FLAG = DepositUtil.atmDepoistedCurrencies.containsKey(DENOMONATION_20);
		Double atmAvaliableBalance = AvaliableBalance.getAvalibaleBalance();

		Integer total_20_currency_deposited_values = null;
		if (DENOMONATION_20_FLAG) {
			total_20_currency_deposited_values = DepositUtil.atmDepoistedCurrencies.get(DENOMONATION_20);
		}

		int amountToWithDraw = atmWithDrawCurrencyDetails.getTotalAmountToWithDraw();
		int origRemainingBalanceToWithDraw = atmWithDrawCurrencyDetails.getRemainingBalance();
		int origProcessedAmount = atmWithDrawCurrencyDetails.getProcessedAmount();
		int processedAmount = 0;
		int remainingBalanceToWithDraw = 0;
		int finalProcessedAmount = 0;
		if (atmAvaliableBalance >= amountToWithDraw && amountToWithDraw >= DENOMONATION_20
				&& (total_20_currency_deposited_values != null) && origRemainingBalanceToWithDraw > 0) {
			// System.out.println("50 flag");
			int totalRequiredCurrencyNotes = origRemainingBalanceToWithDraw / DENOMONATION_20;
			// remainingBalanceToWithDraw = remainingBalanceToWithDraw % DENOMONATION_50;

			// System.out.println("required 50 currency notes:" +
			// totalRequiredCurrencyNotes);
			// System.out.println("remainingBalanceToWithDraw:" +
			// remainingBalanceToWithDraw);

			// System.out.println("total_50_currency_deposited_values:" +
			// total_50_currency_deposited_values);
			if (total_20_currency_deposited_values == totalRequiredCurrencyNotes) {
				processedAmount = total_20_currency_deposited_values * DENOMONATION_20;
				atmWithdrawDenominations.setTwentyDenominations(totalRequiredCurrencyNotes);
			} else if (total_20_currency_deposited_values < totalRequiredCurrencyNotes) {
				processedAmount = total_20_currency_deposited_values * DENOMONATION_20;
				atmWithdrawDenominations.setTwentyDenominations(totalRequiredCurrencyNotes);

			} else if (total_20_currency_deposited_values > totalRequiredCurrencyNotes) {
				processedAmount = totalRequiredCurrencyNotes * DENOMONATION_20;
				atmWithdrawDenominations.setTwentyDenominations(totalRequiredCurrencyNotes);
			}

			remainingBalanceToWithDraw = origRemainingBalanceToWithDraw - processedAmount;
			finalProcessedAmount = origProcessedAmount + processedAmount;
			atmWithDrawCurrencyDetails.setRemainingBalance(remainingBalanceToWithDraw);
			atmWithDrawCurrencyDetails.setProcessedAmount(finalProcessedAmount);
		}
	}

	public void processWithDraw_With_10_Denominations(ATMWithDrawCurrencyDetails atmWithDrawCurrencyDetails,
			ATMWithDrawDenominations atmWithdrawDenominations) {

		Boolean DENOMONATION_10_FLAG = DepositUtil.atmDepoistedCurrencies.containsKey(DENOMONATION_10);
		Double atmAvaliableBalance = AvaliableBalance.getAvalibaleBalance();

		Integer total_10_currency_deposited_values = null;
		if (DENOMONATION_10_FLAG) {
			total_10_currency_deposited_values = DepositUtil.atmDepoistedCurrencies.get(DENOMONATION_10);
		}

		int amountToWithDraw = atmWithDrawCurrencyDetails.getTotalAmountToWithDraw();
		int origRemainingBalanceToWithDraw = atmWithDrawCurrencyDetails.getRemainingBalance();
		int origProcessedAmount = atmWithDrawCurrencyDetails.getProcessedAmount();
		int processedAmount = 0;
		int remainingBalanceToWithDraw = 0;
		int finalProcessedAmount = 0;
		if (atmAvaliableBalance >= amountToWithDraw && amountToWithDraw >= DENOMONATION_10
				&& (total_10_currency_deposited_values != null) && origRemainingBalanceToWithDraw > 0) {
			// System.out.println("50 flag");
			int totalRequiredCurrencyNotes = origRemainingBalanceToWithDraw / DENOMONATION_10;
			// remainingBalanceToWithDraw = remainingBalanceToWithDraw % DENOMONATION_50;

			// System.out.println("required 50 currency notes:" +
			// totalRequiredCurrencyNotes);
			// System.out.println("remainingBalanceToWithDraw:" +
			// remainingBalanceToWithDraw);

			// System.out.println("total_50_currency_deposited_values:" +
			// total_50_currency_deposited_values);
			if (total_10_currency_deposited_values == totalRequiredCurrencyNotes) {
				processedAmount = total_10_currency_deposited_values * DENOMONATION_10;
				atmWithdrawDenominations.setTenDenominations(totalRequiredCurrencyNotes);
			} else if (total_10_currency_deposited_values < totalRequiredCurrencyNotes) {
				processedAmount = total_10_currency_deposited_values * DENOMONATION_10;
				atmWithdrawDenominations.setTenDenominations(totalRequiredCurrencyNotes);

			} else if (total_10_currency_deposited_values > totalRequiredCurrencyNotes) {
				processedAmount = totalRequiredCurrencyNotes * DENOMONATION_10;
				atmWithdrawDenominations.setTenDenominations(totalRequiredCurrencyNotes);
			}

			remainingBalanceToWithDraw = origRemainingBalanceToWithDraw - processedAmount;
			finalProcessedAmount = origProcessedAmount + processedAmount;
			atmWithDrawCurrencyDetails.setRemainingBalance(remainingBalanceToWithDraw);
			atmWithDrawCurrencyDetails.setProcessedAmount(finalProcessedAmount);
		}
	}

	public void createDebitTransaction(Double amountToWithDraw) {

		String currentDateFormat = DateUtilHelper.getDefaultCurrentDateFormat();
		TransactionType trType = TransactionType.DEBIT;
		TransactionDetails tr = new TransactionDetails(currentDateFormat, trType, amountToWithDraw);
		TransactionUtilHelper.calculateClosingBalance(tr);
		// System.out.println("TranactionDetails:" + tr);
		TransactionHistoryDetails.addTranaction(tr);
		AvaliableBalance.setAvalibaleBalance(tr.closingBalance);

	}

	public void updateDepositedCurrencies(ATMWithDrawDenominations atmWithDrawDenominations) {
		
		int total_50_denominations_to_dispense = atmWithDrawDenominations.getFiftyDenominations();
		int total_20_denominations_to_dispense = atmWithDrawDenominations.getTwentyDenominations();
		int total_10_denominations_to_dispense = atmWithDrawDenominations.getTenDenominations();
		Map<Integer, Integer> atmDepoistedDenominations = DepositUtil.getAtmDepoistedCurrencies();
		
		Boolean is_50_denomination_key_present = atmDepoistedDenominations.containsKey(DENOMONATION_50);
		Boolean is_20_denomination_key_present = atmDepoistedDenominations.containsKey(DENOMONATION_20);
		Boolean is_10_denomination_key_present = atmDepoistedDenominations.containsKey(DENOMONATION_10);
		
		int total_50_denominations_avalaiable = 0;
		int total_20_denominations_avalaiable = 0;
		int total_10_denominations_avalaiable = 0;
		
		if(is_50_denomination_key_present) {
			total_50_denominations_avalaiable = atmDepoistedDenominations.get(DENOMONATION_50);
			int total_50_denominations_remaining = total_50_denominations_avalaiable - total_50_denominations_to_dispense;
			if (total_50_denominations_remaining == 0) {
				atmDepoistedDenominations.remove(DENOMONATION_50);
			} else {
				atmDepoistedDenominations.put(DENOMONATION_50, total_50_denominations_remaining);
			}
		}
		
		if(is_20_denomination_key_present) {
			total_20_denominations_avalaiable = atmDepoistedDenominations.get(DENOMONATION_20);
			int total_20_denominations_remaining = total_20_denominations_avalaiable - total_20_denominations_to_dispense;
			if (total_20_denominations_remaining == 0) {
				atmDepoistedDenominations.remove(DENOMONATION_20);
			} else {
				atmDepoistedDenominations.put(DENOMONATION_20, total_20_denominations_remaining);
			}
		}
		
		if(is_10_denomination_key_present) {
			total_10_denominations_avalaiable = atmDepoistedDenominations.get(DENOMONATION_10);
			int total_10_denominations_remaining = total_10_denominations_avalaiable - total_10_denominations_to_dispense;
			if (total_10_denominations_remaining == 0) {
				atmDepoistedDenominations.remove(DENOMONATION_10);
			} else {
				atmDepoistedDenominations.put(DENOMONATION_10, total_10_denominations_remaining);
			}
		}
	
		DepositUtil.setAtmDepoistedCurrencies(atmDepoistedDenominations);
	}

	public void dispenseCurrencies() {

		Map<Integer, Integer> atmDispenseCurrencies = getAtmDispenseCurrencies();
		System.out.println("ATM Dispensed amount:" + atmDispenseCurrencies);
		for (Map.Entry<Integer, Integer> entry : atmDispenseCurrencies.entrySet()) {
			display(entry.getKey(), entry.getValue());
			// System.out.println("Item : " + entry.getKey() + " Count : " +
			// entry.getValue());
		}
	}

	public void display(Integer key, Integer value) {
		for (int i = 0; i < value; i++) {
			System.out.println("Dispensing " + key + "$");
		}

	}

	public void dispenseCurrencies(ATMWithDrawDenominations atmWithDrawDenominations) {
		int total_50_denominations_to_dispense = atmWithDrawDenominations.getFiftyDenominations();
		int total_20_denominations_to_dispense = atmWithDrawDenominations.getTwentyDenominations();
		int total_10_denominations_to_dispense = atmWithDrawDenominations.getTenDenominations();
		if (total_50_denominations_to_dispense >=0) {
			display(50,total_50_denominations_to_dispense);	
		}
		
		if (total_20_denominations_to_dispense >=0) {
			display(20,total_20_denominations_to_dispense);	
		}
		
		if (total_10_denominations_to_dispense >=0) {
			display(10,total_10_denominations_to_dispense);	
		}
	}

}