/***
  Copyright (c) 2008-2012 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  From _The Busy Coder's Guide to Advanced Android Development_
    http://commonsware.com/AdvAndroid
*/

package com.jacksonke.remoteService;

import java.math.BigInteger;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;


import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;


public class BshService extends Service {
	
	boolean _debug = false;
	
	
  private final IScript.Stub binder=new IScript.Stub() {
	  int mTimeStamp = 0;
	  String mDeviceId = "";
	  
	  BigInteger myBigInteger = new BigInteger("3d40fee9", 16);
	  
//	  所有者: CN=Android Debug, O=Android, C=US
//			  发布者: CN=Android Debug, O=Android, C=US
//			  序列号: 5bac446d
//			  有效期开始日期: Wed Apr 09 18:43:39 CST 2013, 截止日期: Fri Apr 01 18:43:39 CST
//			  2044
//			  证书指纹:
//			           MD5: 34:DE:33:0A:FD:E4:2E:21:58:9F:8A:57:0E:80:05:20
//			           SHA1: B0:23:B2:20:03:33:82:70:21:D2:0B:8C:22:68:F8:D8:F4:54:A5:B9
//			           SHA256: 53:28:F6:92:A7:F8:83:D6:9C:54:B6:2B:EC:C8:78:31:B9:3D:93:02:EC:
//			  39:65:C8:E1:F6:DB:3C:26:A2:B7:2C
//			           签名算法名称: SHA256withRSA
//			           版本: 3
//
//			  扩展:
//
//			  #1: ObjectId: 2.5.29.14 Criticality=false
//			  SubjectKeyIdentifier [
//			  KeyIdentifier [
//			  0000: 75 8F 78 E1 8A E9 B4 CB   79 3D 58 9C 63 F9 88 1C  u.x.....y=X.c...
//			  0010: 03 02 25 86                                        ..%.
//			  ]
//			  ]
	  BigInteger myBigInteger2 = new BigInteger("5bac446d", 16);
	  
	  
	  boolean callerValide = false;
	  

	boolean checkCallValide(){
		  String packageName =getPackageManager().getNameForUid(Binder.getCallingUid()); 
		  AddonLog.log("caller name =" + packageName);

		  try {
	          PackageInfo pi = getApplicationContext().getPackageManager().getPackageInfo(packageName, 
	        		  PackageManager.GET_SIGNATURES);
	          try {
	               X509Certificate certificate = X509Certificate.getInstance(pi.signatures[0].toByteArray());
	              
	               BigInteger serialBigInteger = certificate.getSerialNumber();
	               AddonLog.log("" + serialBigInteger.toString(16));
	               
	               if (_debug){
	            	   return true;
	               }
	               else{
	            	   if (serialBigInteger.compareTo(myBigInteger2) == 0
	            			   || serialBigInteger.compareTo(myBigInteger) == 0){
	            		   callerValide = true;
		            	   return true;
		               }
		               else{
		            	   callerValide = false;
		            	   return false;
		               }
	               }
	          } catch (CertificateException e) {
	               e.printStackTrace();
	          }
	     } catch (NameNotFoundException e) {
	          e.printStackTrace();
	     }
		  
		  callerValide = false;
		  
		  return false;
	  }

	@Override
	public int executeCommand(String strcmd){
		AddonLog.log("executeCommand  nCmd=" + strcmd);
		
		// test
		showPermissionInfo();
		
		checkCallValide();
		
		AddonLog.log("caller is valide:" + callerValide);
		
		if (! callerValide){
			return 0;
		}
		
		return 0;
	}
	
  };
  
  @Override
  public void onCreate() {
    super.onCreate();
  }
  
  void showPermissionInfo(){
		  int myPid = Process.myPid();
		  int myUid = Process.myUid();
		  AddonLog.log("myPid=" + myPid + " myUid="+ myUid);
		  AddonLog.log("Test if this process has permission WRITE_SECURE_SETTINGS");
		  if (checkPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS, myPid, myUid) 
				  == PackageManager.PERMISSION_GRANTED){
			  AddonLog.log("-------------yes");
		  }
		  else{
			  AddonLog.log("-------------no");
		  }
		  
		  AddonLog.log("BinderCallingPid =" + Binder.getCallingPid());
		  AddonLog.log("BinderCallingUid =" + Binder.getCallingUid());
		  
		  AddonLog.log("Test if BinderCalling has permission WRITE_SECURE_SETTINGS");
		  if (checkPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS, Binder.getCallingPid(), Binder.getCallingUid()) 
				  == PackageManager.PERMISSION_GRANTED){
			  	AddonLog.log("-----------------yes.");
			}
		  else{
			  AddonLog.log("-----------------no.");
		  }
  }
  
  @Override
  public IBinder onBind(Intent intent) {
	AddonLog.log("onBind");
	
//	showPermissionInfo();
	
    return(binder);
  }
  
  @Override
  public void onDestroy() {
	  AddonLog.log("onDestroy");
	  
	  super.onDestroy();
  }
}