package cloud.fogbow.testutils.core.utils;

import java.security.interfaces.RSAPrivateKey;

import cloud.fogbow.as.core.util.TokenProtector;
import cloud.fogbow.common.exceptions.InternalServerErrorException;
import cloud.fogbow.common.exceptions.UnauthenticatedUserException;
import cloud.fogbow.common.util.ServiceAsymmetricKeysHolder;

public class FsCrypto {
    
    private RSAPrivateKey key;
    private static final String SEPARATOR = "!^!"; 
    
    public FsCrypto() throws InternalServerErrorException {
        ServiceAsymmetricKeysHolder.getInstance().
        setPrivateKeyFilePath("src/main/resources/private/private.key");
        this.key = ServiceAsymmetricKeysHolder.getInstance().getPrivateKey();
    }
    
    public String decrypt(String encryptedString) throws UnauthenticatedUserException {
        return TokenProtector.decrypt(key, encryptedString, SEPARATOR);
    }
}
