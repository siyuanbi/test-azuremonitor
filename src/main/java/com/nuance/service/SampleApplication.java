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
import com.azure.core.credential.TokenCredential;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.profile.AzureProfile;
import com.azure.identity.AzureCliCredentialBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.resourcemanager.AzureResourceManager;
import com.nuance.service.azure.MonitorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main application class.
 */
@SpringBootApplication
public class SampleApplication {
    /** Spring Boot app entry point. */
    public static void main(final String[] args) {
        GracefulshutdownSpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    public AzureResourceManager azure() {
        AzureProfile profile = new AzureProfile(AzureEnvironment.AZURE);
        // Use this line for auto credential detection, for this SPIKE, we will use Azure CLI
//        TokenCredential credential = new DefaultAzureCredentialBuilder().build();
        TokenCredential credential = new AzureCliCredentialBuilder().build();
        return AzureResourceManager
                .authenticate(credential, profile)
                .withSubscription("416e4f7f-3466-4cd0-b530-0c50960d6d2c");
    }
}
