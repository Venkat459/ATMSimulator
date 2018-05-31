package com.learning.excerise.atm.simulator;

public class TransactionDetails {

	String transactionDate;
	TransactionType transactionType;
	Double amount;
	Double closingBalance;

	public TransactionDetails() {
		super();
	}

	public TransactionDetails(String transactionDate, TransactionType transactionType, Double amount,
			Double closingBalance) {

		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
		this.closingBalance = closingBalance;
	}

	public TransactionDetails(String transactionDate, TransactionType transactionType, Double amount) {

		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public TransactionType getTrasnsactionType() {
		return transactionType;
	}

	public void setTrasnsactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((closingBalance == null) ? 0 : closingBalance.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionDetails other = (TransactionDetails) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (closingBalance == null) {
			if (other.closingBalance != null)
				return false;
		} else if (!closingBalance.equals(other.closingBalance))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionType != other.transactionType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransactionDetails [transactionDate=" + transactionDate + ", transactionType=" + transactionType
				+ ", amount=" + amount + ", closingBalance=" + closingBalance + "]";
	}

}
