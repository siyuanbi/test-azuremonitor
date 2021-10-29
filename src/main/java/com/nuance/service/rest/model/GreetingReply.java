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

package com.nuance.service.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// TODO: This can be removed

@Builder
@Schema(description = "Reply object for greeting")
public class GreetingReply {
    @Getter
    @Setter
    @NotNull
    private String replyMessage;

}
