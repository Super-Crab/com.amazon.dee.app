package org.slf4j.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
/* loaded from: classes5.dex */
public class AlexaSlf4jAndroidLoggerFactory implements ILoggerFactory {
    private ConcurrentMap<String, AlexaSlf4jAndroidLogger> loggerMap = new ConcurrentHashMap();
    public static final AlexaSlf4jAndroidLoggerFactory SINGLETON = new AlexaSlf4jAndroidLoggerFactory();
    private static final List<ILoggerFactory> LOGGER_FACTORIES = new ArrayList();

    public static synchronized void subscribe(ILoggerFactory iLoggerFactory) {
        synchronized (AlexaSlf4jAndroidLoggerFactory.class) {
            if (LOGGER_FACTORIES.contains(iLoggerFactory)) {
                return;
            }
            LOGGER_FACTORIES.add(iLoggerFactory);
            for (Map.Entry<String, AlexaSlf4jAndroidLogger> entry : SINGLETON.loggerMap.entrySet()) {
                entry.getValue().subscribe(iLoggerFactory);
            }
        }
    }

    private void subscribeFactories(AlexaSlf4jAndroidLogger alexaSlf4jAndroidLogger) {
        for (ILoggerFactory iLoggerFactory : LOGGER_FACTORIES) {
            alexaSlf4jAndroidLogger.subscribe(iLoggerFactory);
        }
    }

    public static synchronized void unsubscribe(ILoggerFactory iLoggerFactory) {
        synchronized (AlexaSlf4jAndroidLoggerFactory.class) {
            if (LOGGER_FACTORIES.contains(iLoggerFactory)) {
                LOGGER_FACTORIES.remove(iLoggerFactory);
                for (Map.Entry<String, AlexaSlf4jAndroidLogger> entry : SINGLETON.loggerMap.entrySet()) {
                    entry.getValue().unsubscribe(iLoggerFactory);
                }
            }
        }
    }

    @Override // org.slf4j.ILoggerFactory
    public Logger getLogger(String str) {
        AlexaSlf4jAndroidLogger alexaSlf4jAndroidLogger = this.loggerMap.get(str);
        if (alexaSlf4jAndroidLogger != null) {
            return alexaSlf4jAndroidLogger;
        }
        AlexaSlf4jAndroidLogger alexaSlf4jAndroidLogger2 = new AlexaSlf4jAndroidLogger(str);
        AlexaSlf4jAndroidLogger putIfAbsent = this.loggerMap.putIfAbsent(str, alexaSlf4jAndroidLogger2);
        if (putIfAbsent == null) {
            subscribeFactories(alexaSlf4jAndroidLogger2);
        }
        return putIfAbsent == null ? alexaSlf4jAndroidLogger2 : putIfAbsent;
    }
}
