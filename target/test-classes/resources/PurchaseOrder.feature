@tag
Feature: Purchase the order from Ecommerce Website
		
	Background:
	Given I landed on Ecommerce Page	
		
	@Regression
	Scenario Outline: Submit order for <productName> using <name>
		Given Logged in with username <name> and password <password>
		When I add product <productName> to Cart
		And Checkout <productName> and submit the order
		Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
		
		Examples:
			| name  				  | password 			  | productName     |
			| misterdcs92@gmail.com   | 1999222dst!taN1999222 | ZARA COAT 3     |
			| misterdcs1992@gmail.com | 1999222dst!taN1999222 | ADIDAS ORIGINAL |
			

