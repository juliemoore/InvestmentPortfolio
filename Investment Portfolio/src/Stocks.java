
public class Stocks {
	public String symbol;
	public int shares;
	public double purchasePrice;
	public double currentPrice;
	
	// Default constructor
	public Stocks() {
		symbol = null;
		shares= 0;
		purchasePrice = 0;
		currentPrice = 0;
	}
	
	// Parameterized constructor
	public Stocks(String symbol, int shares, double purchasePrice, double currentPrice) {
       this.symbol = symbol;
       this.shares = shares;
       this.purchasePrice = purchasePrice;
       this.currentPrice = currentPrice;	
	 }
	
	// Getter/setter methods for a stock	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String stockSymbol) {
		this.symbol = stockSymbol;
	}
	
	public double getShares() {
		return shares;
	}
	
	public void setShares(int numOfShares) {
		this.shares = numOfShares;
	}
	
	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	public void setPurchasePrice(double purchPrice) {
		purchasePrice = purchPrice;
	}
	
	public double getCurrentPrice() {
       	return currentPrice;
	}
	
	public void setCurrentPrice(double currPrice) {
		currentPrice = currPrice;
	}	
	
	// method to set data
	public void setStock(String symbol, int shares, double purchasePrice, double currentPrice) {
		// set data to asset object 
	   this.setSymbol(symbol);
	   this.setShares(shares);
	   this.setPurchasePrice(purchasePrice);
	   this.setCurrentPrice(currentPrice);
	}
	
	// method to get the total investment value of a stock
	public Double investmentValue() {
		double value = 0;
		value = this.getShares() * this.getPurchasePrice();
		return value;
	}

	// method to get the total current value of a stock
	public Double currentValue() {
		double value = 0;		
		value = this.getShares() * this.getCurrentPrice();
		return value;
	}
	

	// method to get the profit or loss of a stock
	public Double profitLoss() {
		double value = 0;		
		value = this.currentValue() - this.investmentValue();
		return value;	
	}	
			
} // end class
