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

package com.nuance.service.common;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LogUtil {
    public static final Marker NUANCE_REPORT_MARKER = MarkerFactory.getMarker("report");
    public static final Marker NUANCE_AUDIT_MARKER = MarkerFactory.getMarker("audit");
    public static final Marker NUANCE_SECURITY_MARKER = MarkerFactory.getMarker("security");
    public static final Marker NUANCE_INFO_MARKER = MarkerFactory.getMarker("info");
    public static final Marker NUANCE_WARNING_MARKER = MarkerFactory.getMarker("warning");
    public static final Marker NUANCE_ERROR_MARKER = MarkerFactory.getMarker("error");
    public static final Marker NUANCE_DEBUG_MARKER = MarkerFactory.getMarker("debug");
    public static final Marker NUANCE_MONITORING_MARKER = MarkerFactory.getMarker("monitoring");
    public static final Marker NUANCE_EVENT_MARKER = MarkerFactory.getMarker("event");

}
