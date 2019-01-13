package com.elisity.aws.Maven;


/*This is the controller to generate the AWS Cost-incurred reports , AWS cost-forecast reports
 * It calls two classes AwsCostExplorer (to generate the AWS Cost-incurred Reports) and 
 * AWSGetCostForecast (to generate the Forecast of our bill )
 * 
 * Parameters: Granularity = Monthly , isServicesCostBreakDown = False
 * This generates 4 week window AWSCostsIncurredReport and 4 week window for AWS forecast, if 4 week window 
 * falls on monthly-end, it generates forecast for both months
 * 
 * Parameters: Granularity = Monthly , isServicesCostBreakDown = True
 * This generates 4 week window AWSCostsIncurredReport and 4 week window with detailed services costs
 * like AWS EC2, AWS S3, AWS Kinesis that incurred in 4 weeks
 * This combination is not-valid for AWS Forecast report
 * 
 *  * Parameters: Granularity = Daily , isServicesCostBreakDown = False
 * This generates  AWSCostsIncurredReport for Daily incurred cost 
 * This combination is not-valid for AWS Forecast report
 * 
 *   * Parameters: Granularity = Daily , isServicesCostBreakDown = True
 * This generates  AWSCostsIncurredReport for Daily incurred cost with detailed service costs 
 * This combination is not-valid for AWS Forecast report
 * 
 */
public class AwsCostExplorerMain {


	public static void main(String arg[]){

	String granularity = "MONTHLY" ;  //Possible values are MONTHLY or DAILY 

	boolean isServicesCostBreakDown = false;  //Possible values are true or false 

	//Calling Cost Explorer for the period
	AwsCostExplorer cost = new AwsCostExplorer(granularity, isServicesCostBreakDown);
	
	System.out.println("\n");
	
	//Calling ForeCast Explorer for the period
	AwsGetCostForcast amount = new AwsGetCostForcast(granularity);
	System.out.println("\n");
	
	isServicesCostBreakDown = true;  //Possible values are true or false 

	//Calling Cost Explorer for the period
	AwsCostExplorer costwithserv = new AwsCostExplorer(granularity, isServicesCostBreakDown);
	System.out.println("\n");
	
	//Calling Cost Explorer for a day:
	granularity = "DAILY" ;  //Possible values are MONTHLY or DAILY 

	isServicesCostBreakDown = false;  //Possible values are true or false 

	//Calling Cost Explorer for the period
	cost = new AwsCostExplorer(granularity, isServicesCostBreakDown);
	
}
	

}

