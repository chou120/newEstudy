package com.easy.zadmin.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author sanye
 * @Date 2023/9/16 22:04
 * @Version 1.0
 */
public class RequestLimit {

    /**

     */

    private static final String UNKNOWN = "unknown";
    /**
     * 分钟级接口次数限制
     * @param request
     * @param limit
     * @return
     */
//    private boolean checkRequestLimit(HttpServletRequest request, Integer limit){//60
//        String minTime=DateUtils.formatDate("yyyyMMddHHmm",new Date());
//        String method=getApiMethod(request);
//        String requestIpAddr=getIpAddr(request);
//        String methodLimitKey=requestIpAddr+":"+minTime+":"+method;
//        /**获取已经请求的次数**/
//        String useRequestLimit=redisTemplate.opsForValue().get(methodLimitKey);
//        if(StringUtils.isBlank(useRequestLimit)){
//            redisTemplate.opsForValue().set(methodLimitKey,"0",1, TimeUnit.MINUTES);
//            redisTemplate.opsForValue().increment(methodLimitKey,1);
//        }else{
//            if(Integer.valueOf(useRequestLimit)>=limit){//TODO
//                return false;
//            }
//            redisTemplate.opsForValue().increment(methodLimitKey,1);
//        }
//        return true;
//    }
//
//    private static String getIpAddr(HttpServletRequest request) {
//        String ip = null;
//        try {
//            ip = request.getHeader("x-forwarded-for");
//            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//                ip = request.getHeader("Proxy-Client-IP");
//            }
//            if (StringUtils.isEmpty(ip) || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//                ip = request.getHeader("WL-Proxy-Client-IP");
//            }
//            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//                ip = request.getHeader("HTTP_CLIENT_IP");
//            }
//            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//            }
//            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
//                ip = request.getRemoteAddr();
//            }
//        } catch (Exception e) {
//            LOGGER.error("IPUtils ERROR ", e);
//        }
//        return ip;
//    }
//
//    private String getApiMethod(HttpServletRequest request){
//        String method=null;
//        String requestURI=request.getRequestURI().toString();
//        String [] paths=requestURI.split("/");
//        if(paths.length>=3){
//            method=paths[2];
//        }
//        return method;
//    }


}
