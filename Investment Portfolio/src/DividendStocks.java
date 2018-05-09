public class DividendStocks extends Stocks {
	private double divPaid;
	
	// Parameterized constructor
	public DividendStocks(String symbol, int shares, double purchasePrice, double currentPrice, double divPaid) {
		super(symbol, shares, purchasePrice, currentPrice);
		this.divPaid = divPaid;
	}

	// Getter/setter methods for added dividend paid field                                                       
	public double getDivPaid() {
		return divPaid;
	}
	
	public void setDivPaid(double divPaid) {
		this.divPaid = divPaid;
	}
			
	// sets all dividend stock items
	public void setDivStock(String symbol, int shares, double purchasePrice, double currentPrice, double divPaid) {
		// set data to asset object 
	   this.setSymbol(symbol);
	   this.setShares(shares);
	   this.setPurchasePrice(purchasePrice);
	   this.setCurrentPrice(currentPrice);
	   this.setDivPaid(divPaid);
	}
	

	@Override
	// method to get the total investment value of a stock
	public Double investmentValue() {
		double value = 0;
		value = this.getShares() * this.getPurchasePrice() + this.getDivPaid();
		return value;
	}
		
	@Override
	// method to get the total current value of a stock
	public Double currentValue() {
		double value = 0;		
		value = this.getShares() * this.getCurrentPrice() + this.getDivPaid();
		return value;
	}
	
	@Override
	// method to get the profit or loss of a stock
	public Double profitLoss() {
		double value = 0;		
		value = this.currentValue() - this.investmentValue();
		return value;	
	}	
	
	
}
