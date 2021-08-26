package cloud.fogbow.testutils.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

// TODO needs testing
public class UserData {
    
    private static final String FILE_PATH = "data.sh";
    
    public UserData() {
        
    }
    
    public String getEncryptedScript() throws IOException {
        InputStream inputStream = new FileInputStream(FILE_PATH);
        String cloudInitScript = IOUtils.toString(inputStream);

        byte[] scriptBytes = cloudInitScript.getBytes(StandardCharsets.UTF_8);

        byte[] encryptedScriptBytes = Base64.encodeBase64(scriptBytes);
        return new String(encryptedScriptBytes, StandardCharsets.UTF_8);
    }
}
