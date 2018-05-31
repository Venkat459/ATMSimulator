package com.learning.excerise.atm.simulator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

public class DepositUtil {
	static Set<Integer> atmAllowedDenominations = new HashSet<>(Arrays.asList(10, 20, 50));
	static Map<Integer, Integer> atmDepoistedCurrencies = new TreeMap<>();

	Util util;
	String depoistedLine;
	Set<Integer> depositedDenominations;

	public String getReadLineFromConsole() {
		return depoistedLine;
	}

	public void setReadLineFromConsole(String readLineFromConsole) {
		this.depoistedLine = readLineFromConsole;
	}

	public static Set<Integer> getAtmAllowedDenominations() {
		return atmAllowedDenominations;
	}

	public static void setAtmAllowedDenominations(Set<Integer> atmAllowedDenominations) {
		DepositUtil.atmAllowedDenominations = atmAllowedDenominations;
	}

	public static Map<Integer, Integer> getAtmDepoistedCurrencies() {
		return atmDepoistedCurrencies;
	}

	public static void setAtmDepoistedCurrencies(Map<Integer, Integer> atmDepoistedCurrencies) {
		DepositUtil.atmDepoistedCurrencies.putAll(atmDepoistedCurrencies);
	}

	DepositUtil(String depoistedLine) {
		this.depoistedLine = depoistedLine;
		this.util = new Util();
	}

	public List<Integer> getDepositedDenominations() throws Exception {

		List<Integer> depoistedValuesListInteger = null;
		String readDepositLine = this.depoistedLine;
		readDepositLine = util.removeSpaceAndDot(readDepositLine);
		String[] depositedValues = util.tokenizeStringByDelimiter(readDepositLine, Util.SPACE_TOKENIZER);
		List<String> depoistedValuesListString = util.convertStringArraytoListString(depositedValues);
		depoistedValuesListInteger = util.transformList_String_to_Integer(depoistedValuesListString);
		// System.out.println("depoistedValuesListInteger:" +
		// depoistedValuesListInteger);
		return depoistedValuesListInteger;
	}

	public void processDepositedDenominations() throws Exception {

		List<Integer> depoistedValues = getDepositedDenominations();

		List<Integer> validDenominations = new LinkedList<>();
		List<Integer> inValidDenominations = new LinkedList<>();

		if (depoistedValues != null && (!depoistedValues.isEmpty())) {

			for (Integer depositedDenomination : depoistedValues) {

				if (atmAllowedDenominations.contains(depositedDenomination)) {
					validDenominations.add(depositedDenomination);
				} else {
					inValidDenominations.add(depositedDenomination);
				}

			}

		}
		validDenominations.forEach(valid -> System.out.println("Accepted:" + valid));
		inValidDenominations.forEach(inValid -> System.out.println("Invalid denomination:" + inValid + "$"));
		updateAtmDepoistedCurrenciesMap(validDenominations);
		createCreditTransaction(validDenominations);

		/*
		 * System.out.println("depoistedValues" + depoistedValues);
		 * System.out.println("validDepositedDenominations:" + validDenominations);
		 * System.out.println("inValidDepositedDenominations:" + inValidDenominations);
		 */
	}

	public void createCreditTransaction(List<Integer> validDenominations) {
		Optional<Integer> c = validDenominations.stream().reduce((a, b) -> a + b);
		Double totalDeposit = (double) c.get();
		String currentDateFormat = DateUtilHelper.getDefaultCurrentDateFormat();
		TransactionType trType = TransactionType.CREDIT;

		TransactionDetails tr = new TransactionDetails(currentDateFormat, trType, totalDeposit);
		TransactionUtilHelper.calculateClosingBalance(tr);
		// System.out.println("TranactionDetails:" + tr);
		TransactionHistoryDetails.addTranaction(tr);
		AvaliableBalance.setAvalibaleBalance(tr.closingBalance);
	}

	public static void updateAtmDepoistedCurrenciesMap(List<Integer> validDenominations) {
		for (Integer validDenomination : validDenominations) {
			Boolean isKeyPresent = atmDepoistedCurrencies.containsKey(validDenomination);
			// System.out.println("IsKeyPresent for denomination:"+isKeyPresent + "for
			// denomination:"+validDenomination);
			if (isKeyPresent) {
				Integer numberOfCurriences = atmDepoistedCurrencies.get(validDenomination);
				numberOfCurriences++;
				atmDepoistedCurrencies.put(validDenomination, numberOfCurriences);
			} else {
				atmDepoistedCurrencies.put(validDenomination, 1);
			}
		}
		// System.out.println("atmDepoistedCurrencies:" + atmDepoistedCurrencies);
	}
}