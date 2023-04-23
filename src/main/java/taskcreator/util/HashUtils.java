package taskcreator.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtils {

    public String sha256(String input) {
        String hash = DigestUtils.sha256Hex(input);
        return hash;
    }

}