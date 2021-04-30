package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 获取ip地址
 * @author huyongjin
 *
 */
public class IpAddressUtil {
	private static final Logger logger = LoggerFactory.getLogger(IpAddressUtil.class);
	 public static boolean isWindowsOS(){	
		 boolean isWindowsOS = false;	
		 String osName = System.getProperty("os.name");	
		 if(osName.toLowerCase().indexOf("windows") > -1){
		     isWindowsOS = true;
		 }
		 return isWindowsOS;	
	 }

	  /**
	   * 获取本机ip地址，并自动区分Windows还是linux操作系统
	   * @return
	   */
	  public static String getLocalIP(){	
		  String sIP = "";	
		  InetAddress ip = null;	
		  try {				  
			  if(isWindowsOS()){	//如果是Windows操作系统	
				  ip = InetAddress.getLocalHost();	
			  } else {//如果是Linux操作系统	
				  boolean bFindIP = false;	
				  Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
				  while (netInterfaces.hasMoreElements()) {	
					  if(bFindIP){	
						  break;	
					  }
					  NetworkInterface ni = netInterfaces.nextElement();
					  String name = ni.getName(); //----------特定情况，可以考虑用ni.getName判断	
					  if ("eth0".equals(name) || "eth1".equals(name)) {
						  Enumeration<InetAddress> ips = ni.getInetAddresses();	
						  while (ips.hasMoreElements()) {	//遍历所有ip	
							  ip = ips.nextElement();
							  if( ip.isSiteLocalAddress() 	
									  && !ip.isLoopbackAddress()   //127.开头的都是lookback地址	
									  && ip.getHostAddress().indexOf(":") == -1){
								  bFindIP = true;	
								  break; 
							  }	
						  }		 	
					  }					  				  					  
				  }	
			  }
	    } catch (Exception e) {
			  logger.error(e.getMessage());
	    }
	    if(null != ip){
	    	sIP = ip.getHostAddress();
	    }
	    return sIP;	
	  }

}
