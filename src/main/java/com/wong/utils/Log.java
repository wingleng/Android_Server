package com.wong.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * log4j2 日志辅助类
 */

public class Log {
   private static final org.apache.logging.log4j.Logger LOGGER =  LogManager.getLogger();
   private Log(){
       throw new UnsupportedOperationException("cannot be instantiated");
   }
   public static org.apache.logging.log4j.Logger getLogger(){
       return LOGGER;
   }
   public static void t(String msg){
       LOGGER.trace(msg);
   }
   public static void d(String msg){
       LOGGER.debug(msg);
   }
   public static void i(String msg){
       LOGGER.info(msg);
   }
   public static void w(String msg){
       LOGGER.warn(msg);
   }
   public static void e(String msg){
       LOGGER.error(msg);
   }

}
