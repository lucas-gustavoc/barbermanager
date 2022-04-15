package kryptteste;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author lucas
 */
public class HashMD5 {
    
    public static String getHashMD5(String msg) {
        String ret = msg;
        
        try {
            MessageDigest m=MessageDigest.getInstance("MD5");
            m.update(msg.getBytes(),0,msg.length());
            ret = new BigInteger(1,m.digest()).toString(16);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return ret;
    }
    
}
