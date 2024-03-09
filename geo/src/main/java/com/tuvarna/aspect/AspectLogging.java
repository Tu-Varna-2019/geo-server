package com.tuvarna.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AspectLogging {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    public void afterReturn(Object returnValue) throws Throwable {
        logger.info("value return was {}", returnValue);
    }
}
