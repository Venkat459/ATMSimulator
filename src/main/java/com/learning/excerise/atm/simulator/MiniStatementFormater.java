package com.learning.excerise.atm.simulator;

public class MiniStatementFormater {

	public String basicFormatter() {

		String lineSeparator = "--------------------------------------------------------------------------";
		String headerContents = "Date Time \t\t" + "Transaction\t\t" + "Amount\t\t" + "Closing Balance";
		String newLineSeperator = "\n";

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(lineSeparator);
		strBuilder.append(newLineSeperator);
		strBuilder.append(headerContents);
		strBuilder.append(newLineSeperator);
		strBuilder.append(lineSeparator);
		strBuilder.append(newLineSeperator);
		return strBuilder.toString();
	}
}
