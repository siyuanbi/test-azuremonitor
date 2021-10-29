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

        import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// TODO: You might want to change the name "sample-application"

/**
 * Configuration properties for this Spring Boot application
 */
@Configuration
@ConfigurationProperties(prefix = "sample-application")
@EqualsAndHashCode
@Data
public class SampleApplicationProperties {

    /**
     * Name of this server - to be used in the sample response to identify the instance.
     */
    private String serverName;

}
