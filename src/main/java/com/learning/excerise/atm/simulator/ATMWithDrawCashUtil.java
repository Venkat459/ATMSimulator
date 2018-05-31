package com.learning.excerise.atm.simulator;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ATMWithDrawCashUtil {

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
		// processWithDraw(amountToWithDraw);

	}

	
	/**
	 * @param amountToWithDraw
	 * @return
	 * TODO : Needs correct logic, negative cases is not working and it is un acceptable behavior 
	 * For example: ATM has two 50s and and user enters amount 80 and data is inconsistent.
	 */
	public boolean processWithDraw(Integer amountToWithDraw) {
		Boolean isSuccess = true;
		Boolean DENOMONATION_50_FLAG = atmDepoistedCurrencies.containsKey(DENOMONATION_50);
		Boolean DENOMONATION_20_FLAG = atmDepoistedCurrencies.containsKey(DENOMONATION_20);
		Boolean DENOMONATION_10_FLAG = atmDepoistedCurrencies.containsKey(DENOMONATION_10);
		int remainingBalanceToWithDraw = 0;
		Double atmAvaliableBalance = AvaliableBalance.getAvalibaleBalance();
		// System.out.println("atmAvaliableBalance:" + atmAvaliableBalance);
		System.out.println("amountToWithDraw :" + amountToWithDraw);

		System.out.println("DENOMONATION_50_FLAG :" + DENOMONATION_50_FLAG);
		System.out.println("DENOMONATION_20_FLAG :" + DENOMONATION_20_FLAG);
		System.out.println("DENOMONATION_10_FLAG :" + DENOMONATION_10_FLAG);

		if (amountToWithDraw > 0) {
			if (atmAvaliableBalance >= amountToWithDraw && amountToWithDraw >= DENOMONATION_50
					&& DENOMONATION_50_FLAG) {
				// System.out.println("50 flag");
				int totalRequiredCurrencyNotes = amountToWithDraw / DENOMONATION_50;
				remainingBalanceToWithDraw = amountToWithDraw % DENOMONATION_50;

				// System.out.println("required 50 currency notes:" +
				// totalRequiredCurrencyNotes);
				// System.out.println("remainingBalanceToWithDraw:" +
				// remainingBalanceToWithDraw);

				Integer total_50_currency_deposited_values = atmDepoistedCurrencies.get(DENOMONATION_50);
				// System.out.println("total_50_currency_deposited_values:" +
				// total_50_currency_deposited_values);
				if (total_50_currency_deposited_values != null) {
					if (total_50_currency_deposited_values == totalRequiredCurrencyNotes) {
						this.atmDispenseCurrencies.put(DENOMONATION_50, total_50_currency_deposited_values);
						atmDepoistedCurrencies.remove(DENOMONATION_50);
						amountToWithDraw = remainingBalanceToWithDraw;
						processWithDraw(amountToWithDraw);
					} else if (total_50_currency_deposited_values < totalRequiredCurrencyNotes) {
						amountToWithDraw = (amountToWithDraw - (total_50_currency_deposited_values * DENOMONATION_50));
						this.atmDispenseCurrencies.put(DENOMONATION_50, total_50_currency_deposited_values);
						atmDepoistedCurrencies.remove(DENOMONATION_50);
						processWithDraw(amountToWithDraw);
					} else if (total_50_currency_deposited_values > totalRequiredCurrencyNotes) {
						amountToWithDraw = (amountToWithDraw - (total_50_currency_deposited_values * DENOMONATION_50));
						this.atmDispenseCurrencies.put(DENOMONATION_50, totalRequiredCurrencyNotes);
						atmDepoistedCurrencies.put(DENOMONATION_50,
								total_50_currency_deposited_values - totalRequiredCurrencyNotes);
						amountToWithDraw = remainingBalanceToWithDraw;
						processWithDraw(amountToWithDraw);
					}

				}

			}
			if (atmAvaliableBalance >= amountToWithDraw && amountToWithDraw >= DENOMONATION_20
					&& DENOMONATION_20_FLAG) {
				// System.out.println("20 flag");
				int totalRequiredCurrencyNotes = amountToWithDraw / DENOMONATION_20;
				remainingBalanceToWithDraw = amountToWithDraw % DENOMONATION_20;

				// System.out.println("required 20 currency notes:" +
				// totalRequiredCurrencyNotes);
				// System.out.println("remainingBalanceToWithDraw:" +
				// remainingBalanceToWithDraw);

				Integer total_20_currency_deposited_values = atmDepoistedCurrencies.get(DENOMONATION_20);
				if (total_20_currency_deposited_values != null) {
					if (total_20_currency_deposited_values == totalRequiredCurrencyNotes) {
						atmDispenseCurrencies.put(DENOMONATION_20, total_20_currency_deposited_values);
						atmDepoistedCurrencies.remove(DENOMONATION_20);
						amountToWithDraw = remainingBalanceToWithDraw;
						processWithDraw(amountToWithDraw);
					} else if (total_20_currency_deposited_values < totalRequiredCurrencyNotes) {
						atmDispenseCurrencies.put(DENOMONATION_20, total_20_currency_deposited_values);
						amountToWithDraw = (amountToWithDraw - (total_20_currency_deposited_values * DENOMONATION_20));
						atmDepoistedCurrencies.remove(DENOMONATION_20);
						processWithDraw(amountToWithDraw);
					} else if (total_20_currency_deposited_values > totalRequiredCurrencyNotes) {
						amountToWithDraw = (amountToWithDraw - (total_20_currency_deposited_values * DENOMONATION_20));
						atmDispenseCurrencies.put(DENOMONATION_20, totalRequiredCurrencyNotes);
						atmDepoistedCurrencies.put(DENOMONATION_20,
								total_20_currency_deposited_values - totalRequiredCurrencyNotes);
						amountToWithDraw = remainingBalanceToWithDraw;
						processWithDraw(amountToWithDraw);
					}

				}

			}

			if (atmAvaliableBalance >= amountToWithDraw && amountToWithDraw >= DENOMONATION_10)
				if (DENOMONATION_10_FLAG) {
					System.out.println("10 flag");
					int totalRequiredCurrencyNotes = amountToWithDraw / DENOMONATION_10;
					remainingBalanceToWithDraw = amountToWithDraw % DENOMONATION_10;

					System.out.println("required 10 currency notes:" + totalRequiredCurrencyNotes);
					System.out.println("remainingBalanceToWithDraw:" + remainingBalanceToWithDraw);

					Integer total_10_currency_deposited_values = atmDepoistedCurrencies.get(DENOMONATION_10);
					if (total_10_currency_deposited_values != null) {
						if (total_10_currency_deposited_values == totalRequiredCurrencyNotes) {
							atmDispenseCurrencies.put(DENOMONATION_10, total_10_currency_deposited_values);
							atmDepoistedCurrencies.remove(DENOMONATION_10);

							amountToWithDraw = remainingBalanceToWithDraw;
							System.out.println("--- amountToWithDraw:-" + amountToWithDraw);
							processWithDraw(amountToWithDraw);
						} else if (total_10_currency_deposited_values < totalRequiredCurrencyNotes) {
							amountToWithDraw = (amountToWithDraw
									- (total_10_currency_deposited_values * DENOMONATION_10));
							atmDispenseCurrencies.put(DENOMONATION_10, total_10_currency_deposited_values);
							atmDepoistedCurrencies.put(DENOMONATION_10, 0);
							atmDepoistedCurrencies.remove(DENOMONATION_10);
							processWithDraw(amountToWithDraw);
						} else if (total_10_currency_deposited_values > totalRequiredCurrencyNotes) {
							amountToWithDraw = (amountToWithDraw
									- (total_10_currency_deposited_values * DENOMONATION_10));
							atmDispenseCurrencies.put(DENOMONATION_10, totalRequiredCurrencyNotes);
							atmDepoistedCurrencies.put(DENOMONATION_10,
									total_10_currency_deposited_values - totalRequiredCurrencyNotes);
							amountToWithDraw = remainingBalanceToWithDraw;
							processWithDraw(amountToWithDraw);
						}
					}
				} /*
					// TODO: Need to handle negative scenarios and needs to corrected.
					else if (amountToWithDraw != 0 || amountToWithDraw == 10) {
					// exit recursive function.
					amountToWithDraw = 0;
					System.out.println(
							"There is money in the ATM but not able to dispense withdrawl amount as there are no smaller denominations");
					// rollback maps.
					atmDispenseCurrencies.clear();
					atmDepoistedCurrencies.putAll(DepositUtil.getAtmDepoistedCurrencies());

					isSuccess = false;
				}
*/
		}
		return isSuccess;
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

	public void updateDepositedCurrencies() {
		DepositUtil.atmDepoistedCurrencies.clear();
		DepositUtil.setAtmDepoistedCurrencies(ATMWithDrawCashUtil.getAtmDepoistedCurrencies());
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

}