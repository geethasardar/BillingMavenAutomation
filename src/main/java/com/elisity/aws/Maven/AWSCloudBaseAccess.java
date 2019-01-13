package com.elisity.aws.Maven;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;

public class AWSCloudBaseAccess {
	
    protected final static String awsAccessKey = "AKIAJU4PIFBGNWZHA7VQ";
    protected final static String awsSecretKey = "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";
    
   // protected final static String[] regions = {"US_EAST_1", "US_EAST_2", "US_WEST_1", "US_WEST_2"};

    protected static AmazonCloudWatchClient client(final String awsAccessKey, final String awsSecretKey) {
        final AmazonCloudWatchClient client = new AmazonCloudWatchClient(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
        client.setEndpoint("http://monitoring.us-west-2.amazonaws.com/");
        return client;
    }
    
    protected static GetMetricStatisticsResult result(
            final AmazonCloudWatchClient client, final GetMetricStatisticsRequest request) {
         return client.getMetricStatistics(request);
    }

}
