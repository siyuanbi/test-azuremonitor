/*-
 * #%L
 * test-azuremonitor
 * %%
 * Copyright (C) 2018 - 2021 Nuance Communications Inc. All Rights Reserved.
 * %%
 * The copyright to the computer program(s) herein is the property of
 * Nuance Communications Inc. The program(s) may be used and/or copied
 * only with the written permission from Nuance Communications Inc.
 * or in accordance with the terms and conditions stipulated in the
 * agreement/contract under which the program(s) have been supplied.
 * 
 * Author: siyuan_bi
 * Date  : Oct 29, 2021, 11:14:02 AM
 * #L%
 */

package com.nuance.service;

import ch.sbb.esta.openshift.gracefullshutdown.GracefulshutdownSpringApplication;
import io.jaegertracing.Configuration;
import io.jaegertracing.micrometer.MicrometerMetricsFactory;
import javax.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class.
 */
@SpringBootApplication
public class SampleApplication {
    /** Spring Boot app entry point. */
    public static void main(final String[] args) {
        GracefulshutdownSpringApplication.run(SampleApplication.class, args);
    }

    /**
     * Initializes Jaeger Metrics support.
     */
    @PostConstruct
    public void initializeJaegerMetrics() {
        // this will expose Jaeger internal metrics
        final var metricsReporter = new MicrometerMetricsFactory();
        final var configuration = new Configuration("test-azuremonitor");
        configuration.getTracerBuilder().withMetricsFactory(metricsReporter).build();
    }
}
