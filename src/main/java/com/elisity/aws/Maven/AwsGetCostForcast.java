package com.elisity.aws.Maven;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.costexplorer.AWSCostExplorer;
import com.amazonaws.services.costexplorer.AWSCostExplorerClientBuilder;
import com.amazonaws.services.costexplorer.model.*;


public class AwsGetCostForcast extends AWSCloudBaseAccess{

    private static AWSCostExplorer awsCostExplorerClient;
    String granularity;
    boolean isServicesCostBreakDown;
    static GetCostForecastRequest getCostForecastRequest;
    static GroupDefinition groupDefinition;

    public AwsGetCostForcast(String granularity){

		String currentDate = LocalDate.now()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		LocalDate nextday = LocalDate.now().plus(2, ChronoUnit.DAYS);
		String nextDate =  nextday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		LocalDate next2Week = LocalDate.now().plus(2, ChronoUnit.WEEKS);
		LocalDate next4week = LocalDate.now().plus(4, ChronoUnit.WEEKS);

		String next2weekDate = next2Week.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String next4weekDate = next4week.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
        // Basic Connection to the AWS.
        AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);

        final AWSCostExplorerClientBuilder builder = AWSCostExplorerClientBuilder.standard();

        awsCostExplorerClient = builder.withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1).build();
		
        //Requesting for AWS ForeCast
        System.out.println("ForeCast for a given " +granularity +" " + nextDate + "  -  "+ next4weekDate);
    	
		getCostForecastRequest = new GetCostForecastRequest()
					.withTimePeriod(new DateInterval().withStart(nextDate).withEnd(next4weekDate))
					.withGranularity(granularity).
					withMetric("BLENDED_COST")
					;
		
        
        GetCostForecastResult result = awsCostExplorerClient.getCostForecast(getCostForecastRequest);

        result.getForecastResultsByTime().forEach(resultByTime -> {
            System.out.println(resultByTime.toString());
        });

        awsCostExplorerClient.shutdown();
    }
}