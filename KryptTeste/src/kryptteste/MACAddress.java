package kryptteste;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author lucas
 */
public class MACAddress {
    
    public static String get() {
        String ret = "Telesc";
        
        InetAddress ip;
	try {

            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            ret = sb.toString();

	} catch (UnknownHostException e) {
            e.printStackTrace();
	} catch (SocketException e){
            e.printStackTrace();
	}
        
        return ret;
    }
    
}
