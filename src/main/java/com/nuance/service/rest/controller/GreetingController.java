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

package com.nuance.service.rest.controller;

import com.nuance.service.SampleApplicationProperties;
import com.nuance.service.common.LogUtil;
import com.nuance.service.rest.model.Greeting;
import com.nuance.service.rest.model.GreetingReply;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.micrometer.core.instrument.Metrics;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.owasp.security.logging.SecurityMarkers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// TODO: This can be removed once you define your own REST API

@RestController
@Tag(name = "sample")
@RequiredArgsConstructor
public class GreetingController {

  private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

  private final SampleApplicationProperties appProperties;

  /**
   * GET method implementation for plain-text request.
   *
   * @param caller caller name
   * @return greeting message
   */
  @GetMapping(value = "/sayhello",
      produces = "text/plain; charset=utf-8")
  @SuppressFBWarnings(value = "SPRING_ENDPOINT", justification = "Verified")
  public String sayhello(@RequestParam("caller") final String caller) {

    LOGGER.info(LogUtil.NUANCE_REPORT_MARKER, "Serving plain-text call from {}", caller);
    // dummy line just to demonstrate the security feature
    LOGGER.info(SecurityMarkers.CONFIDENTIAL, "User password={}", caller.hashCode());
    // dummy line just to demonstrate CRLF injection
    LOGGER.info("Username={}", "username-goes-here\nwith another line injected\nby the caller");

    final var greetingTextCounter = Metrics.counter("greeting.plaintext.count");
    greetingTextCounter.increment();

    nestedCall();

    return appProperties.getServerName() + " Service greets you, " + caller + "!";
  }

  /**
   * GET method implementation for JSON request.
   *
   * @param greeting greeting request
   * @return greeting response
   */
  @PostMapping(value = "/sayhello")
  @SuppressFBWarnings(value = "SPRING_ENDPOINT", justification = "Verified")
  public @ResponseBody
  GreetingReply sayhello(final @Valid @RequestBody Greeting greeting) {

    LOGGER.info(LogUtil.NUANCE_AUDIT_MARKER, "Serving REST call from {}", greeting.getCaller());

    final var greetingJsonCounter = Metrics.counter("greeting.json.count");
    greetingJsonCounter.increment();

    final String msg;
    if (StringUtils.startsWith(greeting.getLanguage(), "en")) {
      msg = appProperties.getServerName() + " Service greets you, " + greeting.getCaller() + "!";
    } else {
      msg = appProperties.getServerName() + " only speaks English :(";
    }
    return GreetingReply.builder().replyMessage(msg).build();
  }

  //TOOD:
  private void nestedCall() {
//    final var serverSpan = tracer.activeSpan();
//
//    final var localSpan = tracer.buildSpan("nestedLocalSpan").asChildOf(serverSpan)
//        .withTag("tag1", "value1").start();
//    try {
//      localSpan.log("event1");
//      localSpan.log("event2");
//      // do something important
//    } finally {
//      localSpan.finish();
//    }
  }
}
