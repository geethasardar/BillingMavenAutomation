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
import com.amazonaws.services.costexplorer.model.DateInterval;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageRequest;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;
import com.amazonaws.services.costexplorer.model.GroupDefinition;
import com.amazonaws.services.costexplorer.model.GroupDefinitionType;

public class AwsCostExplorer extends AWSCloudBaseAccess{

    private static AWSCostExplorer awsCostExplorerClient;
    String granularity;
    boolean isServicesCostBreakDown;
    static GetCostAndUsageRequest request;
    static GroupDefinition groupDefinition;
    
    // Basic Connection to the AWS.
    AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    
    public AwsCostExplorer(String granularity, boolean isServicesCostBreakDown){

		String currentDate = LocalDate.now()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		LocalDate next2Week = LocalDate.now().plus(2, ChronoUnit.WEEKS);
		LocalDate last4week = LocalDate.now().minus(4, ChronoUnit.WEEKS);

		String next2weekDate = next2Week.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String last4weekDate = last4week.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
        //Configuring AWS Cost Explorer Builder
        final AWSCostExplorerClientBuilder builder = AWSCostExplorerClientBuilder.standard();
        awsCostExplorerClient = builder.withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1).build();

        this.granularity = granularity;
        this.isServicesCostBreakDown = isServicesCostBreakDown;
        
        //Requesting for AWS Cost and Usage
        if(isServicesCostBreakDown) {
        	System.out.println("Cost for a given " +granularity +" " + last4weekDate + "  -  "+ currentDate);
        	
    	   groupDefinition = new GroupDefinition().withType(GroupDefinitionType.DIMENSION).withKey("SERVICE");
       
    	   request = new GetCostAndUsageRequest()
                .withTimePeriod(new DateInterval().withStart(last4weekDate).withEnd(currentDate))
                .withGranularity(granularity)
                .withMetrics("BlendedCost")
                .withGroupBy(groupDefinition)
                ;
        }else {
        	System.out.println("Cost for a given " +granularity +" " + last4weekDate + "  -  "+ currentDate);
        	
     	   request = new GetCostAndUsageRequest()
                   .withTimePeriod(new DateInterval().withStart(last4weekDate).withEnd(currentDate))
                   .withGranularity(granularity)
                   .withMetrics("BlendedCost")
                   ;
        }

		GetCostAndUsageResult result = awsCostExplorerClient.getCostAndUsage(request);

        result.getResultsByTime().forEach(resultByTime -> {
            System.out.println(resultByTime.toString());
        });
        
/*        //Below statement prints Instance description in Json format
			ObjectMapper mapper = new ObjectMapper();
			try {
				System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n");*/
			
        awsCostExplorerClient.shutdown();
        
    }
}