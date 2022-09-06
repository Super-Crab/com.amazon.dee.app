package org.apache.logging.log4j.spi;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.StackLocatorUtil;
/* loaded from: classes4.dex */
public class ExtendedLoggerWrapper extends AbstractLogger {
    private static final long serialVersionUID = 1;
    protected final ExtendedLogger logger;

    public ExtendedLoggerWrapper(ExtendedLogger extendedLogger, String str, MessageFactory messageFactory) {
        super(str, messageFactory);
        this.logger = extendedLogger;
    }

    @Override // org.apache.logging.log4j.Logger
    public Level getLevel() {
        return this.logger.getLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, Message message, Throwable th) {
        return this.logger.isEnabled(level, marker, message, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logMessage(String str, Level level, Marker marker, Message message, Throwable th) {
        if ((this.logger instanceof LocationAwareLogger) && requiresLocation()) {
            ((LocationAwareLogger) this.logger).logMessage(level, marker, str, StackLocatorUtil.calcLocation(str), message, th);
        }
        this.logger.logMessage(str, level, marker, message, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, CharSequence charSequence, Throwable th) {
        return this.logger.isEnabled(level, marker, charSequence, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, Object obj, Throwable th) {
        return this.logger.isEnabled(level, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str) {
        return this.logger.isEnabled(level, marker, str);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object... objArr) {
        return this.logger.isEnabled(level, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj) {
        return this.logger.isEnabled(level, marker, str, obj);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2) {
        return this.logger.isEnabled(level, marker, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Throwable th) {
        return this.logger.isEnabled(level, marker, str, th);
    }
}
