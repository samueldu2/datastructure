package com.samueldu.util;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

public class VerifyChecksum {

    public static void main(String [] args){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream("C:\\Users\\shdu\\Downloads\\Docker Desktop Installer.exe");

            byte[] dataBytes = new byte[1024];

            int nread = 0;
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            ;
            byte[] mdbytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Digest(in hex format):: " + sb.toString());
            System.out.println(sb.toString().equals("7e2d34cb7573b81cc067ff9e979e44675d46eb6a26801031c27f83bbf93dfc3b"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
