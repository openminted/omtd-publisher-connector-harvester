/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.toolkit.pubmedcentral.retriever;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;
import org.slf4j.LoggerFactory;

/**
 * This class handles the shutdown process for the executorService.
 * 
 * It Ensures that the shutdown of the executorService has run before the application exits
 * @author samuel
 */
public class ExecutorShutdownHook {

    private final ExecutorService executorService;

    org.slf4j.Logger logger = LoggerFactory.getLogger("ProcessDownloadedDocument");

    public ExecutorShutdownHook(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @PreDestroy
    public void destroy() throws InterruptedException {
        logger.warn("Shutting down Task Executor");
        executorService.shutdown();
        logger.warn("Waiting for Task Executor to shutdown");
        executorService.awaitTermination(1, TimeUnit.HOURS);
        logger.warn("Task Executor has shutdown");
    }
}
