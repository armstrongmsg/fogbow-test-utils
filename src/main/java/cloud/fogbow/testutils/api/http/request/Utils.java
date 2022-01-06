package cloud.fogbow.testutils.api.http.request;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cloud.fogbow.common.exceptions.InternalServerErrorException;
import cloud.fogbow.common.exceptions.UnauthenticatedUserException;
import cloud.fogbow.testutils.api.http.parameters.AzureImageData;
import cloud.fogbow.testutils.api.http.parameters.EncryptedString;
import cloud.fogbow.testutils.api.http.parameters.RewrapParams;
import cloud.fogbow.testutils.core.ApplicationFacade;

@CrossOrigin
@RestController
@RequestMapping(value = Utils.IMAGE_ENDPOINT)
public class Utils {
    public static final String IMAGE_ENDPOINT = "utils/";

    private final Logger LOGGER = Logger.getLogger(Utils.class);

    @RequestMapping(value = "decrypt", method = RequestMethod.GET)
    public ResponseEntity<String> decrypt(@RequestBody EncryptedString encryptedString) throws UnauthenticatedUserException {
        String decryptedString = ApplicationFacade.getInstance().decrypt(encryptedString.getEncryptedString());
        return new ResponseEntity<String>(decryptedString, HttpStatus.OK);
    }
    
    @RequestMapping(value = "rewrap", method = RequestMethod.GET)
    public ResponseEntity<String> rewrap(@RequestBody RewrapParams rewrapParams) throws UnauthenticatedUserException, 
    InternalServerErrorException, GeneralSecurityException {
        String rewrapString = ApplicationFacade.getInstance().rewrap(rewrapParams.getEncryptedString(), 
                rewrapParams.getEncryptKey());
        return new ResponseEntity<String>(rewrapString, HttpStatus.OK);
    }
    
    @RequestMapping(value = "azureimageid", method = RequestMethod.GET)
    public ResponseEntity<String> getAzureImageId(@RequestBody AzureImageData azureImageData) {
        String azureImageId = ApplicationFacade.getInstance().getAzureImageId(azureImageData.getOffer(), 
                azureImageData.getPublisher(), azureImageData.getSku());
        return new ResponseEntity<String>(azureImageId, HttpStatus.OK);
    }
    
    @RequestMapping(value = "userdata", method = RequestMethod.GET)
    public ResponseEntity<String> getUserData() throws IOException {
        String userData = ApplicationFacade.getInstance().getUserData();
        return new ResponseEntity<String>(userData, HttpStatus.OK);
    }
}
