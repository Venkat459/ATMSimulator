package com.learning.excerise.atm.simulator;

import java.util.Scanner;

public class ATMSimulator {

	public static void main(String[] args) throws Exception {
		ATMSimulator atmSimulator = new ATMSimulator();
		atmSimulator.runAtmSimulator();

	}

	public void runAtmSimulator() throws Exception {

		int choice = -1;

		Scanner input = new Scanner(System.in);

		do {

			// System.out.println("Choose from these choices");
			// System.out.println("-------------------------\n");
			System.out.println("1.Deposit");
			System.out.println("2.Withdraw");
			System.out.println("3.DisplayBalance");
			System.out.println("4.MiniStatement");
			System.out.println("5.Quit");

			try {

				choice = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException numberFormatException) {
				System.out.println("Invalid integer, please enter choice 1-5");

				continue;

			}
			if (choice < 1 || choice > 5) {
				System.out.println("Invalid input choice, please select only between 1 to 5");
				continue;

			}
			try {
				processSelection(choice, input);
			} catch (Exception e) {
				
				 System.out.println("below exception while processing choice:" + choice);
				 if (e != null && e.getMessage() != null) {
					 System.out.println("Exception details---:" + e.getMessage());
						
				 }
				continue;
			}

		} while (choice != 5);

	}

	public void processSelection(int choice, Scanner input) throws Exception {

		switch (choice) {

		case 1:

			// System.out.println("User selected option 1 deposit");
			System.out.println("Enter ccy to deposit terminated by. e.g. 10 20 50 .");
			String readLine = input.nextLine();

			if (readLine != null) {
				DepositUtil depositUtil = new DepositUtil(readLine);
				depositUtil.processDepositedDenominations();
				ATMWithDrawCashUtil.setAtmDepoistedCurrencies(DepositUtil.getAtmDepoistedCurrencies());
			}

			break;

		case 2:
			// System.out.println("User selected option 2 withdraw");
			System.out.println("Enter amount to withdraw");
			// System.out.println("ATMWithDrawCashUtil- Before running withdraw transaction
			// :"
			// + ATMWithDrawCashUtil.getAtmDepoistedCurrencies());
			// System.out.println(
			// "DepositUtil - before running withdraw transaction :" +
			// DepositUtil.getAtmDepoistedCurrencies());

			Integer amountToWithDraw = Integer.parseInt(input.nextLine());
			ATMWithDrawCashUtil2 atmWithDrawCashUtil = new ATMWithDrawCashUtil2();

			atmWithDrawCashUtil.validateATMWithdrawAmount(amountToWithDraw);
			ATMWithDrawDenominations atmWithDrawDenominations = new ATMWithDrawDenominations();
			Boolean isAbleToWithDraw = atmWithDrawCashUtil.processWithDraw(amountToWithDraw, atmWithDrawDenominations);
			// System.out.println("isAbleToWithDraw:" + isAbleToWithDraw);
			if (isAbleToWithDraw) {
				atmWithDrawCashUtil.createDebitTransaction(amountToWithDraw.doubleValue());
				atmWithDrawCashUtil.updateDepositedCurrencies(atmWithDrawDenominations);
				atmWithDrawCashUtil.dispenseCurrencies(atmWithDrawDenominations);

			} else {
				System.out.println(
						"Currenlty ATM is not able to withdraw money as there no lower doniminations to dispense the required amount"
								+ amountToWithDraw);
			}

			// System.out.println("ATMWithDrawCashUtil - After running withdraw transaction
			// :"
			// + ATMWithDrawCashUtil.getAtmDepoistedCurrencies());
			// System.out.println(
			// "DepositUtil - After running withdraw transaction :" +
			// DepositUtil.getAtmDepoistedCurrencies());

			break;
		case 3:
			// System.out.println("User selected option 3 avalibleBalance");
			System.out.println("Available balance:" + AvaliableBalance.getAvalibaleBalance());
			break;

		case 4:
			// System.out.println("User selected option 4 print MiniStatement");
			MiniStatement miniStatment = new MiniStatement();
			miniStatment.printMiniStatement();
			break;

		case 5:
			// System.out.println("User selected option 5.Quit and terminating program");
			System.out.println("Have a good day");
			break;

		default:
			throw new Exception("Invalid input choice, please select only between 1 to 5");

		}

	}

}
