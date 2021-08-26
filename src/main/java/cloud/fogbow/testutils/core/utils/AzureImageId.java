package cloud.fogbow.testutils.core.utils;

import java.util.Base64;

// TODO needs testing
public class AzureImageId {
    
    private static final String SEPARATOR = "@#"; 
    
    public String getAzureImageId(String offer, String publisher, String sku) {
        String expectedId = publisher + SEPARATOR + offer + SEPARATOR + sku;
        return Base64.getEncoder().encodeToString(expectedId.getBytes());
    }
}
