package com.learning.excerise.atm.simulator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Util {

	public static final String COMMA_TOKENIZER = ",";
	public static final String SPACE_TOKENIZER = " ";

	/**
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public Boolean validateInputEndsWithSpaceAndDot(String str) throws Exception {

		if (str != null && str.endsWith(" .")) {
			return true;
		} else {
			throw new Exception(
					"Input is not in the correct format, it should be ended with Space and .  by e.g. 10 20 50 .");
		}

	}

	/**
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String removeSpaceAndDot(String str) throws Exception {

		String target = null;

		if (validateInputEndsWithSpaceAndDot(str)) {
			target = str.replace(" .", "");
		}
		return target;
	}

	public String[] tokenizeStringByDelimiter(String sourceValue, String delimiter) {
		String[] tokenizedString = null;
		if (sourceValue != null && delimiter != null) {
			tokenizedString = sourceValue.split(delimiter);
		}
		return tokenizedString;
	}

	
	public List<String> convertStringArraytoListString(String[] sourceValues) {
		List<String> targetValues = null;
		if (sourceValues != null) {
			targetValues = new LinkedList<String>(Arrays.asList(sourceValues));
		}

		return targetValues;
	}

	public Set<Integer> transformHashSet_String_to_Integer(Set<String> hashSetString) throws NumberFormatException {

		Set<Integer> denominationsetInteger = null;
		if (hashSetString != null) {
			denominationsetInteger = hashSetString.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toSet());

		}
		return denominationsetInteger;
	}

	public List<Integer> transformList_String_to_Integer(List<String> sourceValues) throws NumberFormatException {

		List<Integer> targetValues = null;
		if (sourceValues != null) {
			targetValues = sourceValues.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());

		}
		return targetValues;
	}

}