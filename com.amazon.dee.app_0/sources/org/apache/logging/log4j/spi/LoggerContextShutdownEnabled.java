package org.apache.logging.log4j.spi;

import java.util.List;
/* loaded from: classes4.dex */
public interface LoggerContextShutdownEnabled {
    void addShutdownListener(LoggerContextShutdownAware loggerContextShutdownAware);

    List<LoggerContextShutdownAware> getListeners();
}
