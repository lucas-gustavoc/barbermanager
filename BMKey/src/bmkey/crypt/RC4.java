package bmkey.crypt;

/**
 *
 * @author lucas
 */
import java.security.*;
import javax.crypto.*;
  
public class RC4
{
    private static final String ALGORITHM = "RC4";

    public RC4() {}
    
    public String getStringFromByteArray(byte[] ar) {
        return getStringFromByteArray(ar, " ");
    }

    public String getStringFromByteArray(byte[] ar, String div) {
        String strBytes = "";
        for (int i=0; i < ar.length; i++) strBytes += (new Integer(ar[i]) + div);
        return strBytes;
    }
    
    public byte[] getByteArrayFromString(String str) {
        return getByteArrayFromString(str, " ");
    }
    
    public byte[] getByteArrayFromString(String str, String div) {
        String[] strSplitted = str.split(div);
        byte[] newBytes = new byte[strSplitted.length];
        for (int i=0; i < strSplitted.length; i++) newBytes[i] = new Byte(strSplitted[i]);
        return newBytes;
    }

    public byte[] encrypt(String toEncrypt, String key) throws Exception {
        // create a binary key from the argument key (seed)
        SecureRandom sr = new SecureRandom(key.getBytes());
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(sr);
        SecretKey sk = kg.generateKey();

        // create an instance of cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // initialize the cipher with the key
        cipher.init(Cipher.ENCRYPT_MODE, sk);

        // enctypt!
        byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());

        return encrypted;
    }

    public String decrypt(byte[] toDecrypt, String key) throws Exception {
        // create a binary key from the argument key (seed)
        SecureRandom sr = new SecureRandom(key.getBytes());
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(sr);
        SecretKey sk = kg.generateKey();

        // do the decryption with that key
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, sk);
        byte[] decrypted = cipher.doFinal(toDecrypt);

        return new String(decrypted);
    }
}

