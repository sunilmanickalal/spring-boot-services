package com.sunil.pharmacy.services.sources.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;

import java.io.IOException;

@Slf4j
public class RetryLogic {
    public static String remoteServiceNotAvailable() throws Exception {
        log.info("inside method remoteServiceNotAvailable - to throw exception using remoteServiceNotAvailable");
        throw new RemoteAccessException("service not available");
    }

    public static String ioExceptionThrown() throws Exception {
        log.info("inside method ioExceptionThrown  - to throw exception using ioExceptionThrown");
        throw new IOException("something IOEX thrown");
    }
}
