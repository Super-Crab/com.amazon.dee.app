package org.apache.logging.log4j.internal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LambdaUtil;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.log4j.util.Supplier;
/* loaded from: classes4.dex */
public class DefaultLogBuilder implements LogBuilder {
    private static Message EMPTY_MESSAGE = new SimpleMessage("");
    private static final String FQCN = DefaultLogBuilder.class.getName();
    private static final Logger LOGGER = StatusLogger.getLogger();
    private volatile boolean inUse;
    private Level level;
    private StackTraceElement location;
    private final Logger logger;
    private Marker marker;
    private long threadId;
    private Throwable throwable;

    public DefaultLogBuilder(Logger logger, Level level) {
        this.logger = logger;
        this.level = level;
        this.threadId = Thread.currentThread().getId();
        this.inUse = true;
    }

    private boolean isValid() {
        if (!this.inUse) {
            LOGGER.warn("Attempt to reuse LogBuilder was ignored. {}", StackLocatorUtil.getCallerClass(2));
            return false;
        } else if (this.threadId == Thread.currentThread().getId()) {
            return true;
        } else {
            LOGGER.warn("LogBuilder can only be used on the owning thread. {}", StackLocatorUtil.getCallerClass(2));
            return false;
        }
    }

    private void logMessage(Message message) {
        try {
            this.logger.logMessage(this.level, this.marker, FQCN, this.location, message, this.throwable);
        } finally {
            this.inUse = false;
        }
    }

    public boolean isInUse() {
        return this.inUse;
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(Message message) {
        if (isValid()) {
            logMessage(message);
        }
    }

    public LogBuilder reset(Level level) {
        this.inUse = true;
        this.level = level;
        this.marker = null;
        this.throwable = null;
        this.location = null;
        return this;
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public LogBuilder withLocation() {
        this.location = StackLocatorUtil.getStackTraceElement(2);
        return this;
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public LogBuilder withMarker(Marker marker) {
        this.marker = marker;
        return this;
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public LogBuilder withThrowable(Throwable th) {
        this.throwable = th;
        return this;
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public LogBuilder withLocation(StackTraceElement stackTraceElement) {
        this.location = stackTraceElement;
        return this;
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(CharSequence charSequence) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(charSequence));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str));
        }
    }

    public DefaultLogBuilder(Logger logger) {
        this.logger = logger;
        this.inUse = false;
        this.threadId = Thread.currentThread().getId();
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object... objArr) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, objArr));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Supplier<?>... supplierArr) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, LambdaUtil.getAll(supplierArr)));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(Supplier<Message> supplier) {
        if (isValid()) {
            logMessage(supplier.get());
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(Object obj) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(obj));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj, Object obj2) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj, Object obj2, Object obj3) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9));
        }
    }

    @Override // org.apache.logging.log4j.LogBuilder
    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10));
        }
    }
}
