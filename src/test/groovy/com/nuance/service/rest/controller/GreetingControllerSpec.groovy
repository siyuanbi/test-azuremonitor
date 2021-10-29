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

package com.nuance.service.rest.controller

import com.nuance.service.SampleApplicationProperties
import com.nuance.service.rest.model.Greeting
import io.opentracing.mock.MockTracer
import spock.lang.Specification

public class GreetingControllerSpec extends Specification {

    def "test say Hello for get request"() {
        given: "request and mock constructor args"
        def request = "test"
        def tracer = new MockTracer()
        def properties = Mock(SampleApplicationProperties)
        def controller = new GreetingController(tracer, properties)

        when: "say hello request is made"
        def response = controller.sayhello(request)

        then: "correct response is received"
        response.contains(request)
        1 * properties.getServerName() >> "Greeting"
    }

    def "test say Hello for post request for english language"() {
        given: "request and mock constructor args"
        def request = new Greeting(caller: "test", language: "english")
        def tracer = new MockTracer()
        def properties = Mock(SampleApplicationProperties)
        def controller = new GreetingController(tracer, properties)

        when: "say hello request is made"
        def response = controller.sayhello(request)

        then: "correct response is received"
        response.getReplyMessage().contains(request.getCaller())
        1 * properties.getServerName() >> "Greeting"
    }

    def "test say Hello for post request for spanish language"() {
        given: "request and mock constructor args"
        def request = new Greeting(caller: "test", language: "spanish")
        def tracer = new MockTracer()
        def properties = Mock(SampleApplicationProperties)
        def controller = new GreetingController(tracer, properties)

        when: "say hello request is made"
        def response = controller.sayhello(request)

        then: "correct response is received"
        !response.getReplyMessage().contains(request.getCaller())
        1 * properties.getServerName() >> "Greeting"
    }
}
