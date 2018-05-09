import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class InvestmentPortfolio {
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//																															//
	//									Initialize Arraylist and Investment Portfolio objects												//
	//                                                                                                                          //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	// Asset objects to add assets to arrays
  	public static Stocks stock;
  	public static DividendStocks divStock;
  	public static MutualFunds mutualFund;
	public static ArrayList<Stocks> stockList;
	public static ArrayList<DividendStocks> divStockList;
	public static ArrayList<MutualFunds> mfList;
	
	// Variables
  	public static String sType = "Stock";
  	public static String dsType = "Dividend Stock";
  	public static String mfType = "MutualFund";
  	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//																															//
	//									JSwing Components for Investment Portfolio Class										//
	//                                                                                                                          //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Text fields/ text areas for displaying results of current stock value and profit/loss buttons
	private static JTextField resultsTextField = null;
	private static JTextField divStockResultsTextField = null;
	private static JTextField mutFundsResultsTextField = null;
	private static JTextField editDivPaidTextField = null;
	private static JTextArea resultsTextArea = null;
	private static JTextArea stockTextArea = null;
	private static JTextArea stockTextArea2 = null;
	private static JTextArea stockTextArea3 = null;

	
	public static void main(String[] args) {
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//																															//
	//							JSwing components and events for main Investment Portfolio method								//
	//                                                                                                                          //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// component for alert frames
		Component frame = null;		
		
		// top frame
		JFrame topFrame;
		
		// initiate asset objects
		stock = new Stocks();
		divStock = new DividendStocks(dsType, 0, 0, 0, 0);
		mutualFund = new MutualFunds();
		stockList = new ArrayList<Stocks>();		
		divStockList = new ArrayList<DividendStocks>();
		mfList = new ArrayList<MutualFunds>();
			
		// JFrame for program
		topFrame = new JFrame();
		topFrame.setTitle("Investment Portfolio");
		topFrame.setBounds(100, 100, 530, 510);
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topFrame.getContentPane().setLayout(null);
		// Display window
		topFrame.setVisible(true);
		
		JLabel lblAddAssetsTo = new JLabel("Investment Portfolio Management");
		lblAddAssetsTo.setBounds(10, 10, 490, 20);
		lblAddAssetsTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddAssetsTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAssetsTo.setLabelFor(topFrame.getContentPane());
		lblAddAssetsTo.setVerticalAlignment(SwingConstants.TOP);
		topFrame.getContentPane().add(lblAddAssetsTo);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 40, 490, 410);
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.LIGHT_GRAY));
		topFrame.getContentPane().add(tabbedPane);
		
		// Panel, components and tab for stocks
		JPanel stocksPanel = new JPanel();
		stocksPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Stocks", null, stocksPanel, null);
		stocksPanel.setLayout(null);
		
		JLabel lblStockSymbol = new JLabel("Enter stock symbol:");
		lblStockSymbol.setHorizontalAlignment(SwingConstants.LEFT);
		lblStockSymbol.setBounds(10, 10, 150, 20);
		stocksPanel.add(lblStockSymbol);
		
		JTextField stockSymbolTextField = new JTextField();
		stockSymbolTextField.setColumns(10);
		stockSymbolTextField.setBounds(200, 10, 100, 20);
		stockSymbolTextField.setMargin(new Insets(0, 3, 0, 3));
		stocksPanel.add(stockSymbolTextField);
		
		JLabel lblNumOfShares = new JLabel("Enter number of shares:");
		lblNumOfShares.setBounds(10, 40, 150, 20);
		stocksPanel.add(lblNumOfShares);
		
		JTextField numOfSharesTextField = new JTextField();
		numOfSharesTextField.setColumns(10);
		numOfSharesTextField.setBounds(200, 40, 100, 20);
		numOfSharesTextField.setMargin(new Insets(0, 3, 0, 3));
		stocksPanel.add(numOfSharesTextField);
		
		JLabel lblPricePerShare = new JLabel("Enter price paid per share:");
		lblPricePerShare.setBounds(10, 70, 150, 20);
		stocksPanel.add(lblPricePerShare);
		
		JTextField pricePerShareTextField = new JTextField();
		pricePerShareTextField.setColumns(10);
		pricePerShareTextField.setBounds(200, 70, 100, 20);
		pricePerShareTextField.setMargin(new Insets(0, 3, 0, 3));
		stocksPanel.add(pricePerShareTextField);
		
		JLabel lblCurrPrice = new JLabel("Enter current price per share:");
		lblCurrPrice.setBounds(10, 100, 200, 20);
		stocksPanel.add(lblCurrPrice);
		
		JTextField currPriceTextField = new JTextField();
		currPriceTextField.setColumns(10);
		currPriceTextField.setBounds(200, 100, 100, 20);
		currPriceTextField.setMargin(new Insets(0, 3, 0, 3));
		stocksPanel.add(currPriceTextField);
				
		// Add stock to file
		JButton btnAddStock = new JButton("Add Stock");
		btnAddStock.setBounds(320, 50, 150, 30);
		stocksPanel.add(btnAddStock);		
		
		// Buttons to display total current value and profit/loss
		JButton btnDisplayCurrValue = new JButton("Display current value of stock");
		btnDisplayCurrValue.setBounds(10, 140, 220, 30);
		stocksPanel.add(btnDisplayCurrValue);
				
		JButton btnDisplayProfitLoss = new JButton("Display profit/loss of stock");
		btnDisplayProfitLoss.setBounds(250, 140, 220, 30);
		stocksPanel.add(btnDisplayProfitLoss);
		
		JButton btnDisplayTotalValue = new JButton("Display total current values");
		btnDisplayTotalValue.setBounds(10, 190, 220, 30);
		stocksPanel.add(btnDisplayTotalValue);
				
		JButton btnDisplayTotalProfitLoss = new JButton("Display total profits/losses");
		btnDisplayTotalProfitLoss.setBounds(250, 190, 220, 30);
		stocksPanel.add(btnDisplayTotalProfitLoss);
		
		// Textfield to display results
		resultsTextField = new JTextField();
		resultsTextField.setBackground(Color.WHITE);
		resultsTextField.setHorizontalAlignment(SwingConstants.CENTER);
		resultsTextField.setEditable(false);
		resultsTextField.setBounds(10, 240, 460, 35);
		stocksPanel.add(resultsTextField);
		
		stockTextArea = new JTextArea();
		stockTextArea.setBounds(10, 275, 460, 75);
		stockTextArea.setEditable(false);
		stockTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		stocksPanel.add(stockTextArea);
		
		// Panel, components and tab for dividend stocks
		JPanel divStocksPanel = new JPanel();
		divStocksPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Dividend Stocks", null, divStocksPanel, null);
		divStocksPanel.setLayout(null);
		
		JLabel divStockSymbolLabel = new JLabel("Enter stock symbol:");
		divStockSymbolLabel.setBounds(10, 10, 150, 20);
		divStockSymbolLabel.setHorizontalAlignment(SwingConstants.LEFT);
		divStocksPanel.add(divStockSymbolLabel);
		
		JTextField divStockSymbTextField = new JTextField();
		divStockSymbTextField.setBounds(200, 10, 100, 20);
		divStockSymbTextField.setMargin(new Insets(0, 3, 0, 3));
		divStocksPanel.add(divStockSymbTextField);
		
		JLabel lblNumOfShares2 = new JLabel("Enter number of shares:");
		lblNumOfShares2.setBounds(10, 40, 150, 20);
		divStocksPanel.add(lblNumOfShares2);
		
		JTextField numOfSharesTextField2 = new JTextField();
		numOfSharesTextField2.setBounds(200, 40, 100, 20);
		numOfSharesTextField2.setMargin(new Insets(0, 3, 0, 3));
		divStocksPanel.add(numOfSharesTextField2);
		
		JLabel lblEnterPricePaid2 = new JLabel("Enter price paid per share:");
		lblEnterPricePaid2.setBounds(10, 70, 150, 20);
		divStocksPanel.add(lblEnterPricePaid2);
		
		JTextField pricePaidTextField2 = new JTextField();
		pricePaidTextField2.setBounds(200, 70, 100, 20);
		pricePaidTextField2.setMargin(new Insets(0, 3, 0, 3));
		divStocksPanel.add(pricePaidTextField2);
		
		JLabel lblDivPaid = new JLabel("Enter dividends paid:");
		lblDivPaid.setBounds(10, 100, 150, 20);
		divStocksPanel.add(lblDivPaid);
		
		JTextField divPaidTextField = new JTextField();
		divPaidTextField.setBounds(200, 100, 100, 20);
		divPaidTextField.setMargin(new Insets(0, 3, 0, 3));
		divStocksPanel.add(divPaidTextField);
		
		JLabel lblCurrentPrice2 = new JLabel("Enter current price per share:");
		lblCurrentPrice2.setBounds(10, 130, 200, 20);
		divStocksPanel.add(lblCurrentPrice2);
		
		JTextField currPriceTextField2 = new JTextField();
		currPriceTextField2.setBounds(200, 130, 100, 20);
		currPriceTextField2.setMargin(new Insets(0, 3, 0, 3));
		divStocksPanel.add(currPriceTextField2);				
		
		JButton btnAddDivStock = new JButton("Add Dividend Stock");
		divStocksPanel.add(btnAddDivStock);
		btnAddDivStock.setBounds(320, 65, 150, 30);
					
		// Current Value button
		JButton btnDisplayCurrValue2 = new JButton("Display current value of stock");
		btnDisplayCurrValue2.setBounds(10, 170, 220, 30);
		divStocksPanel.add(btnDisplayCurrValue2);
			
		// Profit loss button
		JButton btnDisplayProfitLoss2 = new JButton("Display profit/loss of stock");
		btnDisplayProfitLoss2.setBounds(250, 170, 220, 30);
		divStocksPanel.add(btnDisplayProfitLoss2);
		
		// Buttons to display total current value and profit/loss		
		JButton btnDisplayTotalValue2 = new JButton("Display total current values");
		btnDisplayTotalValue2.setBounds(10, 210, 220, 30);
		divStocksPanel.add(btnDisplayTotalValue2);
				
		JButton btnDisplayTotalProfitLoss2 = new JButton("Display total profits/losses");
		btnDisplayTotalProfitLoss2.setBounds(250, 210, 220, 30);
		divStocksPanel.add(btnDisplayTotalProfitLoss2);

		// Result text field and text area
		divStockResultsTextField = new JTextField();
		divStockResultsTextField.setBackground(Color.WHITE);
		divStockResultsTextField.setHorizontalAlignment(SwingConstants.CENTER);
		divStockResultsTextField.setEditable(false);
		divStockResultsTextField.setBounds(10, 260, 460, 35);
		divStocksPanel.add(divStockResultsTextField);
		
		stockTextArea2 = new JTextArea();
		stockTextArea2.setBounds(10, 295, 460, 75);
		stockTextArea2.setEditable(false);
		stockTextArea2.setBorder(new EmptyBorder(10, 10, 10, 10));
		divStocksPanel.add(stockTextArea2);
		
		// Panel, components and tab for mutual funds 		
		JPanel mutualFundsPanel = new JPanel();
		mutualFundsPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Mutual Funds", null, mutualFundsPanel, null);
		mutualFundsPanel.setLayout(null);
		
		JLabel lblStockSymbol3 = new JLabel("Enter stock symbol:");
		lblStockSymbol3.setHorizontalAlignment(SwingConstants.LEFT);
		lblStockSymbol3.setBounds(10, 10, 150, 20);
		mutualFundsPanel.add(lblStockSymbol3);
		
		JTextField stockSymbolTextField3 = new JTextField();
		stockSymbolTextField3.setColumns(10);
		stockSymbolTextField3.setBounds(200, 10, 100, 20);
		stockSymbolTextField3.setMargin(new Insets(0, 3, 0, 3));
		mutualFundsPanel.add(stockSymbolTextField3);
		
		JLabel lblShareAmount = new JLabel("Enter number of shares:");
		lblShareAmount.setBounds(10, 40, 150, 20);
		mutualFundsPanel.add(lblShareAmount);
		
		JTextField shareAmountTextField = new JTextField();
		shareAmountTextField.setColumns(10);
		shareAmountTextField.setBounds(200, 40, 100, 20);
		shareAmountTextField.setMargin(new Insets(0, 3, 0, 3));
		mutualFundsPanel.add(shareAmountTextField);
		
		JLabel lblPricePerShare3 = new JLabel("Enter price paid per share:");
		lblPricePerShare3.setBounds(10, 70, 150, 20);
		mutualFundsPanel.add(lblPricePerShare3);
		
		JTextField pricePerShareTextField3 = new JTextField();
		pricePerShareTextField3.setColumns(10);
		pricePerShareTextField3.setBounds(200, 70, 100, 20);
		pricePerShareTextField3.setMargin(new Insets(0, 3, 0, 3));
		mutualFundsPanel.add(pricePerShareTextField3);
		
		JLabel lblCurrPrice3 = new JLabel("Enter current price per share:");
		lblCurrPrice3.setBounds(10, 100, 200, 20);
		mutualFundsPanel.add(lblCurrPrice3);
		
		JTextField currPriceTextField3 = new JTextField();
		currPriceTextField3.setColumns(10);
		currPriceTextField3.setBounds(200, 100, 100, 20);
		currPriceTextField3.setMargin(new Insets(0, 3, 0, 3));
		mutualFundsPanel.add(currPriceTextField3);
		
		// Add mutual fund button
		JButton btnAddMutualFund = new JButton("Add Mutual Fund");
		btnAddMutualFund.setBounds(320, 50, 140, 30);
		mutualFundsPanel.add(btnAddMutualFund);
				
		// Profit loss button
		JButton btnDisplayProfitLoss3 = new JButton("Display profit/loss of stock");
		btnDisplayProfitLoss3.setBounds(250, 140, 220, 30);
		mutualFundsPanel.add(btnDisplayProfitLoss3);
		
		// Buttons to display total current value and profit/loss		
		JButton btnDisplayTotalValue3 = new JButton("Display total current values");
		btnDisplayTotalValue3.setBounds(10, 190, 220, 30);
		mutualFundsPanel.add(btnDisplayTotalValue3);
				
		JButton btnDisplayTotalProfitLoss3 = new JButton("Display total profits/losses");
		btnDisplayTotalProfitLoss3.setBounds(250, 190, 220, 30);
		mutualFundsPanel.add(btnDisplayTotalProfitLoss3);
		
		// Results text field
		mutFundsResultsTextField = new JTextField();
		mutFundsResultsTextField.setBackground(Color.WHITE);
		mutFundsResultsTextField.setHorizontalAlignment(SwingConstants.CENTER);
		mutFundsResultsTextField.setEditable(false);
		mutFundsResultsTextField.setBounds(10, 240, 460, 35);
		mutualFundsPanel.add(mutFundsResultsTextField);

		stockTextArea3 = new JTextArea();
		stockTextArea3.setBounds(10, 275, 460, 75);
		stockTextArea3.setEditable(false);
		stockTextArea3.setBorder(new EmptyBorder(10, 10, 10, 10));
		mutualFundsPanel.add(stockTextArea3);
		
		// Panel, components and tab for managing assets
		JPanel manageAssets = new JPanel();
		manageAssets.setBackground(Color.WHITE);
		tabbedPane.addTab("Manage Assets", null, manageAssets, null);
		manageAssets.setLayout(null);

		// Edit mutual fund components
		
		JLabel lblEditMutalFund = new JLabel("Edit an asset:");
		lblEditMutalFund.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditMutalFund.setBounds(10, 10, 150, 20);
		lblEditMutalFund.setFont(new Font("Tahoma", Font.BOLD, 14));
		manageAssets.add(lblEditMutalFund);
				
		JRadioButton rdbtnStock = new JRadioButton("Manage a stock");
		rdbtnStock.setBackground(Color.WHITE);
		rdbtnStock.setBounds(10, 40, 125, 20);
		manageAssets.add(rdbtnStock);
		
		JRadioButton rdbtnDivStock = new JRadioButton("Manage a dividend stock");
		rdbtnDivStock.setBackground(Color.WHITE);
		rdbtnDivStock.setBounds(145, 40, 175, 20);
		manageAssets.add(rdbtnDivStock);
		
		JRadioButton rdbtnMutualFund = new JRadioButton("Manage a mutual fund");
		rdbtnMutualFund.setBackground(Color.WHITE);
		rdbtnMutualFund.setBounds(320, 40, 150, 20);
		manageAssets.add(rdbtnMutualFund);
		
		// Manage radio buttons
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnStock);
		buttonGroup.add(rdbtnDivStock);
		buttonGroup.add(rdbtnMutualFund);
		
		JLabel lblenterMutalFund = new JLabel("Enter stock symbol:");
		lblenterMutalFund.setHorizontalAlignment(SwingConstants.LEFT);
		lblenterMutalFund.setBounds(10, 80, 150, 20);
		manageAssets.add(lblenterMutalFund);
		
		JTextField editAssetTextField = new JTextField();
		editAssetTextField.setBounds(190, 80, 100, 20);
		editAssetTextField.setMargin(new Insets(0, 3, 0, 3));
		manageAssets.add(editAssetTextField);
		
		JLabel lblEditShares = new JLabel("Enter new number of shares:");
		lblEditShares.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditShares.setBounds(10, 110, 180, 20);
		manageAssets.add(lblEditShares);
		
		JTextField editSharesTextField = new JTextField();
		editSharesTextField.setBounds(190, 110, 100, 20);
		editSharesTextField.setMargin(new Insets(0, 3, 0, 3));
		manageAssets.add(editSharesTextField);
		
		JLabel lblEditPurchPrice = new JLabel("Enter new purchase value:");
		lblEditPurchPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditPurchPrice.setBounds(10, 140, 150, 20);
		manageAssets.add(lblEditPurchPrice);
		
		JTextField editPriceTextField = new JTextField();
		editPriceTextField.setBounds(190, 140, 100, 20);
		editPriceTextField.setMargin(new Insets(0, 3, 0, 3));
		manageAssets.add(editPriceTextField);
		
		JLabel lblEditcurrVal3 = new JLabel("Enter new current value:");
		lblEditcurrVal3.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditcurrVal3.setBounds(10, 170, 150, 20);
		manageAssets.add(lblEditcurrVal3);
		
		JTextField editcurrValTextField = new JTextField();
		editcurrValTextField.setBounds(190, 170, 100, 20);
		editcurrValTextField.setMargin(new Insets(0, 3, 0, 3));
		manageAssets.add(editcurrValTextField);
		
		JLabel lblEditDivPaid = new JLabel("Enter new dividend Paid:");
		lblEditDivPaid.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditDivPaid.setBounds(10, 200, 150, 20);
		manageAssets.add(lblEditDivPaid);
		
		editDivPaidTextField = new JTextField();
		editDivPaidTextField.setBounds(190, 200, 100, 20);
		editDivPaidTextField.setMargin(new Insets(0, 3, 0, 3));
		manageAssets.add(editDivPaidTextField);
				
		// Edit mutual fund button
		JButton btnEditAsset = new JButton("Edit Asset");
		btnEditAsset.setBounds(320, 135, 140, 30);
		manageAssets.add(btnEditAsset);
		
		// Textfield to display results
		resultsTextArea = new JTextArea();
		resultsTextArea.setBackground(Color.WHITE);
		//resultsTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		resultsTextArea.setEditable(false);
		resultsTextArea.setBounds(10, 240, 460, 50);
		manageAssets.add(resultsTextArea);
		
				
		// Delete asset components
		JLabel lblDelAsset = new JLabel("Delete asset:");
		lblDelAsset.setHorizontalAlignment(SwingConstants.LEFT);
		lblDelAsset.setBounds(10, 300, 150, 20);
		lblDelAsset.setFont(new Font("Tahoma", Font.BOLD, 14));
		manageAssets.add(lblDelAsset);
		
		JLabel lblenterStockSymbol = new JLabel("Enter stock symbol:");
		lblenterStockSymbol.setHorizontalAlignment(SwingConstants.LEFT);
		lblenterStockSymbol.setBounds(10, 330, 150, 20);
		manageAssets.add(lblenterStockSymbol);
		
		JTextField delSymbolTextField = new JTextField();
		delSymbolTextField.setColumns(10);
		delSymbolTextField.setBounds(190, 330, 100, 20);
		delSymbolTextField.setMargin(new Insets(0, 3, 0, 3));
		manageAssets.add(delSymbolTextField);
		
		// Delete mutual fund button
		JButton btnDelAsset = new JButton("Delete asset");
		btnDelAsset.setBounds(320, 325, 140, 30);
		manageAssets.add(btnDelAsset);		
		
		// Panel, components and tab for portfolio
		JPanel portfolioPanel = new JPanel();
		portfolioPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Portfolio", null, portfolioPanel, null);
		portfolioPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Investment Portfolio");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 50, 460, 20);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		portfolioPanel.add(lblTitle);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(20, 103, 326, 198);
		textArea.setEditable(false);
		textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		portfolioPanel.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(20, 85, 440, 270);
		portfolioPanel.add(scrollPane);
										
		JButton btnDisplayPortfolio = new JButton("Display Portfolio");
		btnDisplayPortfolio.setBounds(165, 10, 150, 30);
		portfolioPanel.add(btnDisplayPortfolio);
		
		// Current Value Button for Mutual Funds
		JButton btnDisplayCurrValue3 = new JButton("Display current market value");
		btnDisplayCurrValue3.setBounds(10, 140, 220, 30);
		mutualFundsPanel.add(btnDisplayCurrValue3);
		
///////////////////////////////////////////////////////////////////////////////////////
//																					 //
//							Action Events for buttons								 //
//                                                                                   //
///////////////////////////////////////////////////////////////////////////////////////
		
		// Button event for adding a new stock
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Create new stock object
					Stocks newStock;
					// set variables
					   String symbol = "";
					   int shares = 0;
					   double purchasePrice = 0;
					   double currentPrice = 0;
					   
					   // Clear results text box and text area
					   resultsTextField.setText("");
					   stockTextArea.setText("");
					
					// Get user input and validate data
					// validate stock symbol user input
					   if (!stockSymbolTextField.getText().isEmpty()){ 
						   symbol = stockSymbolTextField.getText();
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter a stock symbol."); 
						   return;
					   }
					// validate share user input
					   if (!numOfSharesTextField.getText().isEmpty()) {
						   shares = Integer.parseInt(numOfSharesTextField.getText());
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the number of shares.");
						   numOfSharesTextField.setText("");
						   return;
					   }
					   if (shares < 0) {
		                	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
		                	numOfSharesTextField.setText("");
		                	return;
					   } 
					// validate purchase price user input
					   if (!pricePerShareTextField.getText().isEmpty()) {
						   purchasePrice = Double.parseDouble(pricePerShareTextField.getText());
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the price per share");
						   return;
					   }
					   if (Double.isNaN(purchasePrice) || purchasePrice < 0) {
			                	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
			                	pricePerShareTextField.setText("");
			                	return;
					   } 
					// validate current price user input
					   if (!currPriceTextField.getText().isEmpty()) {
						   currentPrice = Double.parseDouble(currPriceTextField.getText());
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the current price"); 
						   return;
					   }
					   if (Double.isNaN(currentPrice) || currentPrice < 0) {
						   JOptionPane.showMessageDialog(null,"Please enter a positive number.");
						   currPriceTextField.setText("");
						   return;
				   } 					   
					   // set data to Stock object
					   newStock = new Stocks();   
					   newStock.setStock(symbol, shares, purchasePrice, currentPrice);
					   // Transfer to stock object for processing
					   
					   addStock(newStock);
					   
					   stock = newStock;
					   // add new stock to array list
					   
					   // write to file
					   String data = setStockString(stock);
					   writeToFile(sType, data);
					   
					   // display results
					   stockTextArea.append(data);
					   				   
					   // clear text boxes
					   stockSymbolTextField.setText("");
					   numOfSharesTextField.setText("");
					   pricePerShareTextField.setText("");
					   currPriceTextField.setText("");					   				
					   					   
				} catch (Exception excpt) {
					// Display an alert dialog 
					JOptionPane.showMessageDialog(frame, "Unable to save to  file", sType, JOptionPane.ERROR_MESSAGE);
					}
				} // end action performed
				        
			});
		
		// Event handler for current market value button
		btnDisplayCurrValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String symbol = stock.getSymbol();
				double value = stock.currentValue();
				String data = setCurrValueString(symbol, value);
				resultsTextField.setText(data);
				writeToFile(sType, data);			
			}
		});

		
		// Event handler for profit/loss button
		btnDisplayProfitLoss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symbol = stock.getSymbol();
				double value = stock.profitLoss();
				String data = setProfitLossString(symbol, value);
				resultsTextField.setText(data);
				writeToFile(sType, data);	
			}
		});		
		
		btnDisplayTotalValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = totalStockValue();
				String data = setTotalValueString(value);
				resultsTextField.setText(data);
				writeToFile(sType, data);	
			}
		});
		
		btnDisplayTotalProfitLoss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = totalStockProfitLoss();
				String data = setTotalProfitLossString(value);
				resultsTextField.setText(data);
				writeToFile(sType, data);	
			}
		});
		
		// Action event for adding a dividend Stock
		btnAddDivStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {					   
					   // set variables
					   String symbol = "";
					   int shares = 0;
					   double purchasePrice = 0;
					   double currentPrice = 0;
					   double divPaid = 0;
					   
					   // Clear results text box
					   divStockResultsTextField.setText("");
					   stockTextArea2.setText("");
					
					   // Get user input and validate data
					   // validate stock symbol user input
					   if (!divStockSymbTextField.getText().isEmpty()){ 
						   symbol = divStockSymbTextField.getText();
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter a stock symbol."); 
						   return;
					   }
					   // validate share user input
					   if (!numOfSharesTextField2.getText().isEmpty()) {
						   shares = Integer.parseInt(numOfSharesTextField2.getText());
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the number of shares."); 
						   return;
					   }
					   if (shares < 0) {
		                	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
		                	numOfSharesTextField2.setText("");
		                	return;
					   } 
					   // validate purchase price user input
					   if (!pricePaidTextField2.getText().isEmpty()) {
						   purchasePrice = Double.parseDouble(pricePaidTextField2.getText());
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the price per share");
						   return;
					   }
					   if (Double.isNaN(purchasePrice) || purchasePrice < 0) {
			                	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
			                	pricePaidTextField2.setText("");
			                	return;
					   } 
					   // validate current price user input
					   if (!currPriceTextField2.getText().isEmpty()) {
						   currentPrice = Double.parseDouble(currPriceTextField2.getText());
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the current price");
						   return;
					   }
					   if (Double.isNaN(currentPrice) || currentPrice < 0) {
						   JOptionPane.showMessageDialog(null,"Please enter a positive number.");
						   currPriceTextField.setText("");
						   return;
					   } 					   
					   // validate dividend paid user input
					   if (!divPaidTextField.getText().isEmpty()) {
						   divPaid = Double.parseDouble(divPaidTextField.getText());
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the dividend paid"); 
						   return;
					   }
					   if (Double.isNaN(divPaid) || divPaid < 0) {
						   JOptionPane.showMessageDialog(null,"Please enter a positive number.");
						   divPaidTextField.setText("");
						   return;
					   }
					   
					   // set dividend stock data
					   DividendStocks newDivStock = new DividendStocks(symbol, shares, divPaid, divPaid, divPaid);		
					   newDivStock.setDivStock(symbol, shares, purchasePrice, currentPrice, divPaid); 
					   divStock = newDivStock;
					   // add to array
					   divStockList.add(divStock);
					   			   
					   // Write to file 
					   String data = setDivStockString(divStock);
					   writeToFile(dsType, data);		
					   
					   // display results
					   stockTextArea2.append(data);
					   
					   // clear text boxes
					   divStockSymbTextField.setText("");
					   numOfSharesTextField2.setText("");
					   pricePaidTextField2.setText("");
					   currPriceTextField2.setText("");
					   divPaidTextField.setText("");					   
				} catch (Exception excpt) {
					// Display an alert dialog 
					JOptionPane.showMessageDialog(frame, "Unable to save to  file", dsType, JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
		// Action event for current value button
	btnDisplayCurrValue2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String symbol = divStock.getSymbol();
			double value = divStock.currentValue();
			String data = setCurrValueString(symbol, value);
			divStockResultsTextField.setText(data);
			writeToFile(dsType, data);	
		}
	});

		
		// Action event for profit/loss button
		btnDisplayProfitLoss2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symbol = divStock.getSymbol();
				double value = divStock.profitLoss();
				String data = setProfitLossString(symbol, value);
				divStockResultsTextField.setText(data);
				writeToFile(dsType, data);
			}
		});
		
		btnDisplayTotalValue2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = totalDivStockValue();
				String data = setTotalValueString(value);
				divStockResultsTextField.setText(data);
				writeToFile(sType, data);	
			}

		});
		
		btnDisplayTotalProfitLoss2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = totalDivStockProfitLoss();
				String data = setTotalProfitLossString(value);
				divStockResultsTextField.setText(data);
				writeToFile(sType, data);	
			}
		});
		
		// Action event to add mutual fund
		btnAddMutualFund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					   // set variables
					   String symbol = "";
					   double dblShares = 0;
					   double purchasePrice = 0;
					   double currentPrice = 0;
					   
					   // Clear results text box and text area
					   mutFundsResultsTextField.setText("");
					   stockTextArea.setText("");
					
					// Get user input and validate data
					// validate stock symbol user input
					   if (!stockSymbolTextField3.getText().isEmpty()){ 
						   symbol = stockSymbolTextField3.getText();
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter a stock symbol."); 
						   return;
					   }
					// validate share user input
					   if (!shareAmountTextField.getText().isEmpty()) {
						   dblShares = Double.parseDouble(shareAmountTextField.getText());
						  
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the share amount."); 	
						   return;
					   }
					   if (Double.isNaN(dblShares) || dblShares < 0.0) {
		                	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
		                	shareAmountTextField.setText("");
		                	return;
					   } 
					// validate purchase price user input
					   if (!pricePerShareTextField3.getText().isEmpty()) {
						   purchasePrice = Double.parseDouble(pricePerShareTextField3.getText());
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the price per share");
						   return;
					   }
					   if (Double.isNaN(purchasePrice) || purchasePrice < 0) {
			                	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
			                	pricePerShareTextField3.setText("");
			                	return;
					   } 
					// validate current price user input
					   if (!currPriceTextField3.getText().isEmpty()) {
						   currentPrice = Double.parseDouble(currPriceTextField3.getText());
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the current price");
						   return;
					   }
					   if (Double.isNaN(currentPrice) || currentPrice < 0) {
						   JOptionPane.showMessageDialog(null,"Please enter a positive number.");
						   currPriceTextField3.setText("");
						   return;
					   } 
					   
					   // Fill mutual fund object with data
						MutualFunds newMutualFund = new MutualFunds();
					   // set data to Stock object
						newMutualFund.setMutualFund(symbol, dblShares, purchasePrice, currentPrice);
						mutualFund = newMutualFund;
						// add to array
						mfList.add(mutualFund);
						
						// Write to file
						String data = setMutualFundString(mutualFund);
						writeToFile(mfType, data);
						
						// display results
						stockTextArea3.append(data);
											   
					   // clear text boxes
					   stockSymbolTextField3.setText("");
					   shareAmountTextField.setText("");
					   pricePerShareTextField3.setText("");
					   currPriceTextField3.setText("");						   		  					   
				} catch (Exception excpt) {
					// Display an alert dialog 
					JOptionPane.showMessageDialog(frame, "Unable to save to file", "Mutual Fund", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
		// Action event for profit/loss button
		btnDisplayProfitLoss3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symbol = mutualFund.getSymbol();
				double value = mutualFund.profitLoss();
				String data = setProfitLossString(symbol, value);
				mutFundsResultsTextField.setText(data);
				writeToFile(sType, data);
			}
		});
		
		
		// Action event for current value button
		btnDisplayCurrValue3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symbol = mutualFund.getSymbol();
				double value = mutualFund.currentValue();
				String data = setCurrValueString(symbol, value);
				mutFundsResultsTextField.setText(data);
				writeToFile(mfType, data);	
			}
		});
		
		btnDisplayTotalValue3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = totalMFValue();
				String data = setTotalValueString(value);
				mutFundsResultsTextField.setText(data);
				writeToFile(sType, data);	
			}

		});
		
		btnDisplayTotalProfitLoss3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double value = totalMFProfitLoss();
				String data = setTotalProfitLossString(value);
				mutFundsResultsTextField.setText(data);
				writeToFile(sType, data);	
			}
		});
		
		
		
		// Button event for edit button
		btnEditAsset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			   			   
				// set variables
			   String symbol = "";
			   int shares = 0;
			   double dblShares = 0;
			   double purchasePrice = 0;
			   double currentPrice = 0;
			   double divPaid = 0;
			   
			   // clear text area
			   resultsTextArea.setText("");
				
				try {
					// validate share user input
					   if (!rdbtnStock.isSelected() && !rdbtnDivStock.isSelected() && !rdbtnMutualFund.isSelected()) {
							JOptionPane.showMessageDialog(null,"Please check an asset");
						}
					   if (rdbtnStock.isSelected() || rdbtnMutualFund.isSelected()) {
						   	editDivPaidTextField.setText("0"); // sets to zero to pass data validation
					   }
					   // Get user input and validate data
					   // validate stock symbol user input
					   if (!editAssetTextField.getText().isEmpty()){ 
						   symbol = editAssetTextField.getText();
					   } else {
						   	JOptionPane.showMessageDialog(null,"Please enter a stock symbol."); 
						   	return;
					   }
					   if (!editSharesTextField.getText().isEmpty()) {
							shares = Integer.parseInt(editSharesTextField.getText());
							if (rdbtnMutualFund.isSelected()) {
								dblShares = Double.parseDouble(editSharesTextField.getText());
							}
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter the number of shares."); 
						   return;
					   }
					   if (shares < 0) {
		                	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
		                	editSharesTextField.setText("");
		                	return;
					   } 
					   if (Double.isNaN(dblShares) || dblShares < 0) {
						   	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
						   	editSharesTextField.setText("");
						   	return;
					   }
					   // validate purchase price user input
					   if (!editPriceTextField.getText().isEmpty()) {
						   	purchasePrice = Double.parseDouble(editPriceTextField.getText());
					   } else {
						   	JOptionPane.showMessageDialog(null,"Please enter the price per share");
						   	return;
					   }
					   if (Double.isNaN(purchasePrice) || purchasePrice < 0) {
		                	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
		                	editPriceTextField.setText("");
		                	return;
					   } 
					   // validate current price user input
					   if (!editcurrValTextField.getText().isEmpty()) {
						   	currentPrice = Double.parseDouble(editcurrValTextField.getText());
					   } else {
						   	JOptionPane.showMessageDialog(null,"Please enter the current price");
						   	return;
					   }
					   if (Double.isNaN(currentPrice) || currentPrice < 0) {
						   	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
						   	editcurrValTextField.setText("");
						   	return;
					   } 					   
					   // validate dividend paid user input
					   if (!editDivPaidTextField.getText().isEmpty()) {
						   	divPaid = Double.parseDouble(editDivPaidTextField.getText());
					   } else {
						   	JOptionPane.showMessageDialog(null,"Please enter the dividend paid"); 
						   	return;
					   }
					   if (Double.isNaN(divPaid) || divPaid < 0) {
						   	JOptionPane.showMessageDialog(null,"Please enter a positive number.");
						   	editDivPaidTextField.setText("");
						   	return;
					   }					   
					   if (rdbtnStock.isSelected()) {	
							// Create new stock object
						   Stocks newStock = new Stocks(symbol, shares, purchasePrice, currentPrice);
							
						   // modify stock					
							modifyStock(stock, symbol, shares, purchasePrice, currentPrice);
							
							// Write to file and display results
							String data = setStockString(newStock);
							writeToFile(sType, data);
							// Display new current value and profit and loss
							double value = stock.currentValue();
							double profitValue = stock.profitLoss();
							String currVal = setCurrValueString(symbol, value);
							String profit = setProfitLossString(symbol, profitValue);
							String results = "New " + currVal + "\nNew " + profit;
							resultsTextArea.append(results);
						} 					   
					   if (rdbtnDivStock.isSelected()) {
						   // Create new stock object
						   DividendStocks newStock = new DividendStocks(symbol, shares, purchasePrice, currentPrice, divPaid);
						   // modify Dividend stock							
						   modifyDivStock(divStock, symbol, shares, purchasePrice, currentPrice, divPaid);
						   
						   // Write to file and display results
						   String data = setDivStockString(newStock);
						   writeToFile(dsType, data);
						   
						   // Display new current value and profit and loss
							double value = divStock.currentValue();
							double profitValue = divStock.profitLoss();
							String currVal = setCurrValueString(symbol, value);
							String profit = setProfitLossString(symbol, profitValue);
							String results = "New " + currVal + "\nNew " + profit;
							resultsTextArea.append(results);
						} 
					   if (rdbtnMutualFund.isSelected()) {
						   // create mutual funds object
						   MutualFunds newStock = new MutualFunds(symbol, dblShares, purchasePrice, currentPrice);
						   // modify mutual fund	
						   modifyMutualFund(mutualFund, symbol, dblShares, purchasePrice, currentPrice);
						   	
							// Write to file and display results
						   String data = setMutualFundString(newStock);
							writeToFile(mfType, data);
							
							// Display new current value and profit and loss
							double value = mutualFund.currentValue();
							double profitValue = mutualFund.profitLoss();
							String currVal = setCurrValueString(symbol, value);
							String profit = setProfitLossString(symbol, profitValue);
							String results = "New " + currVal + "\nNew " + profit;
							resultsTextArea.append(results);
							
						}
				} catch (Exception excpt) {
					// Display an alert dialog 
					JOptionPane.showMessageDialog(frame, "Unable to modify", "Asset", JOptionPane.ERROR_MESSAGE);
					}	
				
				// clear text boxes
				editAssetTextField.setText("");
				editSharesTextField.setText("");
				editPriceTextField.setText("");
				editcurrValTextField.setText("");
				editDivPaidTextField.setText("");	   				
			}								
		});
		
		
		// Button event for the delete button
		btnDelAsset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symbol = "";
				String string = "";
			
				try {
					// Validate user input
					if (!rdbtnStock.isSelected() && !rdbtnDivStock.isSelected() && !rdbtnMutualFund.isSelected()) {
						JOptionPane.showMessageDialog(null,"Please check an asset");
					}
					if (!delSymbolTextField.getText().isEmpty()){ 
						   symbol = delSymbolTextField.getText();
					   } else {
						   JOptionPane.showMessageDialog(null,"Please enter a stock symbol."); 
						   return;
					   }
					if (rdbtnStock.isSelected() == true) {						
						removeStock(symbol);
						// write to file removed stock
						string = symbol + " REMOVED";
						writeToFile(sType, string);
					} 
					if (rdbtnDivStock.isSelected()) {
						removeDivStock(symbol);
						// write to file removed stock
						string = symbol + " REMOVED";
						writeToFile(dsType, string);
					} 
					if (rdbtnMutualFund.isSelected()) {
						removeMF(symbol);
						// write to file removed stock
						string = symbol + " REMOVED";
						writeToFile(mfType, string);
					}
				} catch (Exception excpt) {
					// Display an alert dialog 
					JOptionPane.showMessageDialog(frame, "Unable to delete", "Asset", JOptionPane.ERROR_MESSAGE);
					}
				// clear text box
				delSymbolTextField.setText("");
			}
		});

	
		// Button Event for displaying stocks
		btnDisplayPortfolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Scanner fileInput = null;
				textArea.setText(""); // clears text area
				String filename = "portfolio.txt";
				File fileObject = new File(filename);
								
				if (!fileObject.exists()) {
					textArea.setText("No file exists.");
				} else {
					try {
						// open file for reading
						fileInput = new Scanner(fileObject);
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					}
					// read file and append each line to the text area
					while(fileInput.hasNext()) {
						String str = fileInput.nextLine();
						textArea.append(str + "\n");
					}					
					// close file stream
					fileInput.close();
				}
			}
		});
	     return;		
	} // end main	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//																															//
	//											Methods for Stocks																//
	//                                                                                                                          //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// methods to add stock to Stock array
	public static void addStock(Stocks stock) {
		stockList.add(stock);
	}
	
	// method to modify a stock
	public static void modifyStock(Stocks stock, String symbol, int shares, double purchasePrice, double currentPrice) {
	   boolean itemFound = false;	// variable to hold if found or not
	   Stocks newStock;
       // Loop through array to find name to modify
	   for(int i = 0; i < stockList.size(); i++) {
		   // Determine if the item's name at current index is matching
		   if (stockList.get(i).getSymbol().equals(symbol)) {			   
			   newStock = stockList.get(i);
			   itemFound = true;
			   // Determine if item has default values
			   if(!symbol.equals(null)) {
				  newStock.setSymbol(symbol); 
               } 
               if(shares != 0) {
            	   newStock.setShares(shares);
               }
               if(purchasePrice != 0) {
            	   newStock.setCurrentPrice(currentPrice);
               }
               if(purchasePrice != 0) {
            	   newStock.setPurchasePrice(purchasePrice);
               }	               
               JOptionPane.showMessageDialog(null, symbol + " modified"); 
               return;
           } else {
        	   itemFound = false;
           }
       }
	   if (itemFound == false) {
		   JOptionPane.showMessageDialog(null, symbol + " not found. Nothing modified.");
	   }
	   return;
   }
	
	// Method to remove item from cart or display message if not found
	   public static void removeStock(String symbol) {
		   boolean itemFound = false;
		   for(int i = 0; i < stockList.size(); i++) {
	    	   // if current item matches user input
			   if (stockList.get(i).getSymbol().equals(symbol)) {
				   //remove item from list array and return
				   stockList.remove(i); 
	               itemFound = true;
	               JOptionPane.showMessageDialog(null, symbol + " removed.");
	               return;	               
	           } else {
	        	   itemFound = false;
	           }
	       }
		// Item not found, display message
		   if (itemFound == false) {
			   JOptionPane.showMessageDialog(null, symbol + " not found. Nothing removed.");
		   }
	   }	
	   
		public static Double totalStockInvestmentValue() {
			double total = 0;
		       for(int i = 0; i < stockList.size(); i++) {
		           // calculate total cost of item and increment to cart total
		    	   total += (stockList.get(i).getShares() * stockList.get(i).getPurchasePrice());
		       }
		       return total;
		}
	   
	   // method to get current market value of stock list
		public static Double totalStockValue() {
			double total = 0;
			// total values in stock list
			for(int i = 0; i < stockList.size(); i++) {
				// calculate current value and increment
				//total current value = all(shares * purchasePrice)
				total += (stockList.get(i).getShares() * stockList.get(i).getCurrentPrice());
			}
			return total;
		}
		
		// method to get total profit/loss of stock list
		public static Double totalStockProfitLoss() { 
			double total = 0;
			for(int i = 0; i < stockList.size(); i++) {
				// calculate current value and increment
				// total profit/loss = all(shares * currentPrice) - (shares * purchasePrice)
		    	  total += totalStockValue() - totalStockInvestmentValue();
			}
			return total;
		}
	   
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//																															//
	//											Methods for DividendStocks														//
	//                                                                                                                          //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public void addDivStock(DividendStocks divStock) {
			divStockList.add(divStock);
		}
		
		// method to modify a stock
		public static void modifyDivStock(DividendStocks divStock, String symbol, int shares, double purchasePrice, double currentPrice, double divPaid) {
		   boolean itemFound = false;	// variable to hold if found or not
		   DividendStocks newStock;
	       // Loop through array to find name to modify
		   for(int i = 0; i < divStockList.size(); i++) {
			   // Determine if the item's name at current index is matching
			   if (divStockList.get(i).getSymbol().equals(symbol)) {		   
				   newStock = divStockList.get(i);
				   itemFound = true;
				   // delete old stock
				   divStockList.remove(newStock);
				// Determine if item has default values
				   if(!symbol.equals(null)) {
					   newStock.setSymbol(symbol); 
	               } 
	               if(shares != 0) {
	            	   newStock.setShares(shares);
	               }
	               if(purchasePrice != 0) {
	            	   newStock.setCurrentPrice(currentPrice);
	               }
	               if(purchasePrice != 0) {
	            	   newStock.setPurchasePrice(purchasePrice);
	               }	               
	               JOptionPane.showMessageDialog(null, symbol + " modified"); 
	               return;
	           } else {
	        	   itemFound = false;
	           }
	       }
		   if (itemFound == false) {
			   JOptionPane.showMessageDialog(null, symbol + " not found. Nothing modified.");
		   }
		   return;
	   }
		
		// Method to remove item from cart or display message if not found
		   public static void removeDivStock(String symbol) {
			   boolean itemFound = false;
			   for(int i = 0; i < divStockList.size(); i++) {
		    	   // if current item matches user input
				   if (divStockList.get(i).getSymbol().equals(symbol)) {
					   //remove item from list array and return
					   divStockList.remove(i); 
		               itemFound = true;
		               JOptionPane.showMessageDialog(null, symbol + " removed.");
		               return;	               
		           } else {
		        	   itemFound = false;
		           }
		       }
			// Item not found, display message
			   if (itemFound == false) {
				   JOptionPane.showMessageDialog(null, symbol + " not found. Nothing removed.");
			   }
		   }	
		   
		   // get total dividend stock investment value
		   public static Double totalDivStockInvestmentValue() {
			double total = 0;
		       for(int i = 0; i <divStockList.size(); i++) {
		           // calculate total cost of item and increment to cart total
		    	   total += (divStockList.get(i).getShares() * divStockList.get(i).getPurchasePrice())
		    			   + divStockList.get(i).getDivPaid();
		       }
		       return total;
		}
		   
			// method to get current market value of stock list
			public static Double totalDivStockValue() {
				double total = 0;	
				// total values in divStock list
				for(int i = 0; i < divStockList.size(); i++) {
					// calculate current value and increment
					// total current value = all(shares/dblShares * purchasePrice) + divPaid)
					total += ((divStockList.get(i).getShares() * divStockList.get(i).getCurrentPrice()) 
								+ divStockList.get(i).getDivPaid());
				}
				return total;
			}
			
			// method to get total profit/loss of stock list
			public static Double totalDivStockProfitLoss() { 
				double total = 0;
				for(int i = 0; i < divStockList.size(); i++) {
					// calculate current value and increment
					// total profit/loss = all(shares * currentPrice) - (shares * purchasePrice)
			    	  total += totalDivStockValue() - totalDivStockInvestmentValue();
				}
				return total;
			}

		   
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//																															//
	//											Methods for MutualFunds															//
	//                                                                                                                          //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			public void addMF(MutualFunds mutualFund) {
				mfList.add(mutualFund);
			}	
			
			public static void modifyMutualFund(MutualFunds mutualFund, String symbol, double dblShares, double purchasePrice, double currentPrice) {
				   boolean itemFound = false;	// variable to hold if found or not
				   MutualFunds newStock;
			       // Loop through array to find name to modify
				   for(int i = 0; i < mfList.size(); i++) {
					   // Determine if the item's name at current index is matching
					   if (mfList.get(i).getSymbol().equals(symbol)) {				   
						   itemFound = true;
						   newStock = mfList.get(i);
						   // Determine if item has default values
						   if(!symbol.equals(null)) {
							   newStock.setSymbol(symbol); 
			               } 
			               if(dblShares != 0) {
			            	   newStock.setDblShares(dblShares);
			               }
			               if(purchasePrice != 0) {
			            	   newStock.setCurrentPrice(currentPrice);
			               }
			               if(purchasePrice != 0) {
			            	   newStock.setPurchasePrice(purchasePrice);
			               }	               
			               JOptionPane.showMessageDialog(null, symbol + " modified"); 
			               return;
			           } else {
			        	   itemFound = false;
			           }
			       }
				   if (itemFound == false) {
					   JOptionPane.showMessageDialog(null, symbol + " not found. Nothing modified.");
				   }
				   return;
			   }
			
			// Method to remove item from cart or display message if not found
			   public static void removeMF(String symbol) {
				   boolean itemFound = false;
				   for(int i = 0; i < mfList.size(); i++) {
			    	   // if current item matches user input
					   if (mfList.get(i).getSymbol().equals(symbol)) {
						   //remove item from list array and return
						   mfList.remove(i); 
			               itemFound = true;
			               JOptionPane.showMessageDialog(null, symbol + " removed.");
			               return;	               
			           } else {
			        	   itemFound = false;
			           }
			       }
				// Item not found, display message
				   if (itemFound == false) {
					   JOptionPane.showMessageDialog(null, symbol + " not found. Nothing removed.");
				   }
			   }	 
			   
			   // get total dividend stock investment value
			   public static Double totalMFStockInvestmentValue() {
				double total = 0;
			       for(int i = 0; i <mfList.size(); i++) {
			           // calculate total cost of item and increment to cart total
			    	   total += (mfList.get(i).getDblShares() * mfList.get(i).getPurchasePrice());
			       }
			       return total;
			}
			   
			// method to get current market value of stock list
			public static Double totalMFValue() {
				double total = 0;
				
				// total values in mutual funds
				for(int i = 0; i < mfList.size(); i++) {
					// calculate current value and increment
					// total current value = all(dblShares * purchasePrice) + divPaid)
					total += ((mfList.get(i).getDblShares()) * mfList.get(i).getCurrentPrice());
				}
				return total;
			}
		
				
			// method to get total profit/loss of stock list
			public static Double totalMFProfitLoss() { 
				double total = 0;
				for(int i = 0; i < mfList.size(); i++) {
					// calculate current value and increment
					// total profit/loss = all(shares * currentPrice) - (shares * purchasePrice)
			    	  total += totalMFValue() - totalMFStockInvestmentValue();
				}
				return total;
			}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//																															//
	//											Methods for Investment Portfolio												//
	//                                                                                                                          //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Declare variables for printing and displaying
		//public static String portfolioFile = "portfolio.txt";
		//public static String displayFile = "display.txt";
		
		// Code to format currency
		private static String pattern = "$###,###.00";
		private static DecimalFormat currency = new DecimalFormat(pattern);
			
		// Methods to set strings
		public static String setStockString(Stocks stock) {
			String string = "Symbol: " + stock.getSymbol() + "\nNumber of shares " + stock.getShares() + "\nPurchased price: " 
			+ currency.format(stock.getPurchasePrice()) + "\nCurrent Price: " + currency.format(stock.getCurrentPrice());
			return string;
		}
		
		public static String setDivStockString(DividendStocks divStock) {
			String string = "Symbol: " + divStock.getSymbol() + "\nNumber of shares " + divStock.getShares() + "\nPurchased price: " 
					 		+ currency.format(divStock.getPurchasePrice()) + "\nCurrent Price: "
					 		+ currency.format(divStock.getCurrentPrice()) + "\nDividends Paid: " + currency.format(divStock.getDivPaid());
			return string; 
		}
		
		public static String setMutualFundString(MutualFunds mutualFund) {
			String string = "Symbol: " + mutualFund.getSymbol() + "\nShare amount: " + mutualFund.getDblShares() + "\nPurchased price: " 
							+ currency.format(mutualFund.getPurchasePrice()) + "\nCurrent Price: " 
							+ currency.format(mutualFund.getCurrentPrice());
			return string;
		}
		
		public static String stockString(Stocks stock) {
			String string = "Symbol: " + stock.getSymbol() + " | " +    				 
   				 "Shares: " + stock.getShares() + " | " + "\n" +
   				 "Purchase price: " + stock.getPurchasePrice() + " | " +
   				 "Current Price: " + stock.getCurrentPrice(); 
			return string;
		}
		
		public static String setCurrValueString(String symbol, double value) {
			String string = "";
			try {
				string = "Current Market Value of " + symbol + " is " + currency.format(value);
				return string;				
			}catch (Exception excpt) {
				// Display an alert dialog 
				JOptionPane.showMessageDialog(null, "Unable to calculate and display.", "Current Value", JOptionPane.ERROR_MESSAGE);
				}
				return string;
		}
		
		// methods to display a stock' value on the portfolio panel
		public static String setProfitLossString(String symbol, double value) {
			String string = "";
			try {
				if(value > 0) {	
					string = "Profit of " + symbol + " is " + currency.format(value);
					return string;
				} else if (value < 0) {
					string = "Loss of " + symbol + " is " + currency.format(value * -1);
					return string;
				} else if (value == 0){
					string = symbol + " has no profit or loss";
					return string;
				} else {
					// Display an alert dialog 
					JOptionPane.showMessageDialog(null, "Unable to calculate and display.", "Profit or Loss", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception excpt) {
				// Display an alert dialog 
				JOptionPane.showMessageDialog(null, "Unable to calculate and display.", "Profit or Loss", JOptionPane.ERROR_MESSAGE);
				}
				return string;
		}
		
		// method to set total current value string
		public static String setTotalValueString(double value) {
			String string = "";
			try {
				string = "Current market value of all stocks is " + currency.format(value);
				return string;				
			}catch (Exception excpt) {
				// Display an alert dialog 
				JOptionPane.showMessageDialog(null, "Unable to calculate and display.", "Current Value", JOptionPane.ERROR_MESSAGE);
				}
				return string;
		}
		
		// methods to display a stock' value on the portfolio panel
		public static String setTotalProfitLossString(double value) {
			String string = "";
			try {
				if(value > 0) {	
					string = "Total profit from all stocks is " + currency.format(value);
					return string;
				} else if (value < 0) {
					string = "Total loss from all stocks is " + currency.format(value * -1);
					return string;
				} else if (value == 0){
					string = "Total of all stocks has no profit or no loss";
					return string;
				} else {
					// Display an alert dialog 
					JOptionPane.showMessageDialog(null, "Unable to calculate and display.", "Profit or Loss", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception excpt) {
				// Display an alert dialog 
				JOptionPane.showMessageDialog(null, "Unable to calculate and display.", "Profit or Loss", JOptionPane.ERROR_MESSAGE);
				}
				return string;
		}
				
		// Method to write data to a file
		public static void writeToFile(String type, String data) {
			try {

				File dir = new File(".");
				String portfolio="portfolio.txt";
				String source = dir.getCanonicalPath() + File.separator + portfolio;
				File file = new File(source);

				// if file doesn't exists, then create it
				if (!file.exists()) {
	               file.createNewFile();
				}
				
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.newLine();
				bw.write(type);
				bw.newLine();
				bw.write(data);
				bw.newLine();
				bw.close();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Unable to write to file.", "Write to File", JOptionPane.ERROR_MESSAGE);
			}
	   }// end writeToFile method
		
		// method to get current market value of stock list
		public static Double totalCurrentValue() {
			double total = 0;
			// total values in stock list
			for(int i = 0; i < stockList.size(); i++) {
				// calculate current value and increment
				//total current value = all(shares * purchasePrice)
				total += (stockList.get(i).getShares() * stockList.get(i).getPurchasePrice());
			}
			// total values in divStock list
			for(int i = 0; i < divStockList.size(); i++) {
				// calculate current value and increment
				// total current value = all(shares/dblShares * purchasePrice) + divPaid)
				total += ((divStockList.get(i).getShares() * divStockList.get(i).getPurchasePrice()) 
							+ divStockList.get(i).getDivPaid());
			}
			// total values in mutual funds
			for(int i = 0; i < mfList.size(); i++) {
				// calculate current value and increment
				// total current value = all(dblShares * purchasePrice) + divPaid)
				total += ((mfList.get(i).getDblShares()) * mfList.get(i).getPurchasePrice());
			}
			return total;
		}				
} // end InvestmentPortfolio class
