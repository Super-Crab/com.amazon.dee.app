package org.apache.logging.log4j;

import amazon.speech.simclient.SimClient;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.StructuredDataMessage;
import org.apache.logging.log4j.spi.ExtendedLogger;
/* loaded from: classes4.dex */
public final class EventLogger {
    public static final Marker EVENT_MARKER = MarkerManager.getMarker(SimClient.EVENT_DATA_KEY_EVENT);
    private static final String FQCN = EventLogger.class.getName();
    private static final String NAME = "EventLogger";
    private static final ExtendedLogger LOGGER = LogManager.getContext(false).getLogger(NAME);

    private EventLogger() {
    }

    public static void logEvent(StructuredDataMessage structuredDataMessage) {
        LOGGER.logIfEnabled(FQCN, Level.OFF, EVENT_MARKER, (Message) structuredDataMessage, (Throwable) null);
    }

    public static void logEvent(StructuredDataMessage structuredDataMessage, Level level) {
        LOGGER.logIfEnabled(FQCN, level, EVENT_MARKER, (Message) structuredDataMessage, (Throwable) null);
    }
}
