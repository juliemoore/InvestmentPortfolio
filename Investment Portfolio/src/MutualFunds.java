
public class MutualFunds extends Stocks {
	private String symbol = null;
	private double dblShares;
	private double purchasePrice;
	private double currentPrice;
	
	// Default constructor
	public MutualFunds() {
		symbol = null;
		dblShares= 0;
		purchasePrice = 0;
		currentPrice = 0;
	}

	// parameterized constructors
	public MutualFunds(String symbol, double dblShares, double purchasePrice, double currentPrice) {
		this.symbol = symbol;
		this.dblShares = dblShares;
		this.purchasePrice = purchasePrice;
		this.currentPrice = currentPrice;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	// Getter/setter for shares as percentage
	public double getDblShares() {
		return dblShares;
	}
	
	public void setDblShares(double dblShares) {
		this.dblShares = dblShares;
	}
	
	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	public void setPurchasePrice(double purchPrice) {
		this.purchasePrice = purchPrice;
	}
	
	public double getCurrentPrice() {
       	return currentPrice;
	}
	
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}	
		
	// set all mutual fund items
	public void setMutualFund(String symbol, double shares, double purchasePrice, double currentPrice) {
	   this.setSymbol(symbol);
	   this.setDblShares(shares);
	   this.setPurchasePrice(purchasePrice);
	   this.setCurrentPrice(currentPrice);
	}
		
	@Override
	// method to get the total investment value of a stock
	public Double investmentValue() {
		double value = 0;
		value = this.getDblShares() * this.getPurchasePrice();
		return value;
	}

	@Override
	// method to get the total current value of a stock
	public Double currentValue() {
		double value = 0;		
		value = this.getDblShares() * this.getCurrentPrice();
		return value;
	}

	@Override
	// method to get the profit or loss of a stock
	public Double profitLoss() {
		double value = 0;
		value = this.currentValue() - this.investmentValue() ;
		return value;
	}
} // end class
