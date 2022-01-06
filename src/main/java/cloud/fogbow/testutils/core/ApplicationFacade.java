package cloud.fogbow.testutils.core;

import java.io.IOException;
import java.security.GeneralSecurityException;

import cloud.fogbow.common.exceptions.InternalServerErrorException;
import cloud.fogbow.common.exceptions.UnauthenticatedUserException;
import cloud.fogbow.testutils.core.utils.AzureImageId;
import cloud.fogbow.testutils.core.utils.FsCrypto;
import cloud.fogbow.testutils.core.utils.UserData;

public class ApplicationFacade {

    private static ApplicationFacade instance;
    
    private FsCrypto crypto;
    private AzureImageId azureImageId;
    private UserData userData;
    
    public static ApplicationFacade getInstance() {
        synchronized (ApplicationFacade.class) {
            if (instance == null) {
                instance = new ApplicationFacade();
            }
            return instance;
        }
    }

    public void setCrypto(FsCrypto crypto) {
        this.crypto = crypto;
    }
    
    public void setAzureImageId(AzureImageId azureImageId) {
        this.azureImageId = azureImageId;
    }
    
    public void setUserData(UserData userData) {
        this.userData = userData;
    }
    
    public String decrypt(String encryptedString) throws UnauthenticatedUserException {
        return this.crypto.decrypt(encryptedString);
    }

    public String rewrap(String encryptedString, String encryptKey) throws UnauthenticatedUserException, 
    InternalServerErrorException, GeneralSecurityException {
        return this.crypto.rewrap(encryptedString, encryptKey);
    }
    
    public String getAzureImageId(String offer, String publisher, String sku) {
        return this.azureImageId.getAzureImageId(offer, publisher, sku);
    }
    
    public String getUserData() throws IOException {
        return this.userData.getEncryptedScript();
    }
}
