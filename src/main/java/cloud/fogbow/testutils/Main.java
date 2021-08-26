package cloud.fogbow.testutils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import cloud.fogbow.common.exceptions.InternalServerErrorException;
import cloud.fogbow.testutils.core.ApplicationFacade;
import cloud.fogbow.testutils.core.utils.AzureImageId;
import cloud.fogbow.testutils.core.utils.FsCrypto;
import cloud.fogbow.testutils.core.utils.UserData;

@Component
public class Main implements ApplicationRunner {
    
    @Override
    public void run(ApplicationArguments args) throws InternalServerErrorException {
        FsCrypto crypto = new FsCrypto();
        AzureImageId azureImageId = new AzureImageId();
        UserData userData = new UserData();
        
        ApplicationFacade.getInstance().setCrypto(crypto);
        ApplicationFacade.getInstance().setAzureImageId(azureImageId);
        ApplicationFacade.getInstance().setUserData(userData);
    }
}
