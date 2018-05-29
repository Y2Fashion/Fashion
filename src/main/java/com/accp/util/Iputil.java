package com.accp.util;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Iputil {
        public static String getIpAddr(HttpServletRequest request) {
            String ip = request.getHeader("x-forwarded-for");
            if (!checkIP(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (!checkIP(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (!checkIP(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split(".").length != 4) {
            return false;
        }
        return true;
    }

}
