package com.hopon.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerSingleton {
  private static LoggerSingleton instance = null;
  private final static Logger LOG = Logger.getLogger(LoggerSingleton.class.getName());
  
  private LoggerSingleton() {
  } // just to make sure that from no where object of this class can be
    // created.
  
  public static LoggerSingleton getInstance() {
    if (instance == null) {
      instance = new LoggerSingleton();
    }
    return instance;
  }
  
  public void debug(String message) {
    LoggerSingleton.LOG.log(Level.DEBUG, message);
  }
  
  public void info(String message) {
    LoggerSingleton.LOG.log(Level.INFO, message);
  }
  
  public void warn(String message) {
    LoggerSingleton.LOG.log(Level.WARN, message);
  }
  
  public void fatal(String message) {
    LoggerSingleton.LOG.log(Level.FATAL, message);
  }
  
  public void error(String message) {
    LoggerSingleton.LOG.log(Level.ERROR, message);
  }
  
  public void debug(String message, Exception ex) {
    LoggerSingleton.LOG.log(Level.DEBUG, message, ex);
  }
  
  public void info(String message, Exception ex) {
    LoggerSingleton.LOG.log(Level.INFO, message, ex);
  }
  
  public void warn(String message, Exception ex) {
    LoggerSingleton.LOG.log(Level.WARN, message, ex);
  }
  
  public void fatal(String message, Exception ex) {
    LoggerSingleton.LOG.log(Level.FATAL, message, ex);
  }
  
  public void error(String message, Exception ex) {
    LoggerSingleton.LOG.log(Level.ERROR, message, ex);
  }
  
  public void debug(Exception ex) {
    String message = ex.getStackTrace()[0].getClassName() + "->" + ex.getStackTrace()[0].getMethodName() + "() : "
        + ex.getStackTrace()[0].getLineNumber() + " :: " + ex.getMessage();
    LoggerSingleton.LOG.log(Level.DEBUG, message, ex);
  }
  
  public void info(Exception ex) {
    String message = ex.getStackTrace()[0].getClassName() + "->" + ex.getStackTrace()[0].getMethodName() + "() : "
        + ex.getStackTrace()[0].getLineNumber() + " :: " + ex.getMessage();
    LoggerSingleton.LOG.log(Level.INFO, message, ex);
  }
  
  public void warn(Exception ex) {
    String message = ex.getStackTrace()[0].getClassName() + "->" + ex.getStackTrace()[0].getMethodName() + "() : "
        + ex.getStackTrace()[0].getLineNumber() + " :: " + ex.getMessage();
    LoggerSingleton.LOG.log(Level.WARN, message, ex);
  }
  
  public void fatal(Exception ex) {
    String message = ex.getStackTrace()[0].getClassName() + "->" + ex.getStackTrace()[0].getMethodName() + "() : "
        + ex.getStackTrace()[0].getLineNumber() + " :: " + ex.getMessage();
    LoggerSingleton.LOG.log(Level.FATAL, message, ex);
  }
  
  public void error(Exception ex) {
    String message = ex.getStackTrace()[0].getClassName() + "->" + ex.getStackTrace()[0].getMethodName() + "() : "
        + ex.getStackTrace()[0].getLineNumber() + " :: " + ex.getMessage();
    LoggerSingleton.LOG.log(Level.ERROR, message, ex);
  }
}
