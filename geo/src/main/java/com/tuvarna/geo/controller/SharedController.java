package com.tuvarna.geo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class SharedController {
    protected final Logger logger = LogManager.getLogger(this.getClass().getName());
}
