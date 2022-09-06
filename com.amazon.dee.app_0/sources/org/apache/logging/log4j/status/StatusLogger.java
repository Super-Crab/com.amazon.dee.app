package org.apache.logging.log4j.status;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedNoReferenceMessageFactory;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.Strings;
/* loaded from: classes4.dex */
public final class StatusLogger extends AbstractLogger {
    private static final String NOT_AVAIL = "?";
    public static final String STATUS_DATE_FORMAT = "log4j2.StatusLogger.DateFormat";
    private static final long serialVersionUID = 2;
    private final Collection<StatusListener> listeners;
    private int listenersLevel;
    private final ReadWriteLock listenersLock;
    private final SimpleLogger logger;
    private final Queue<StatusData> messages;
    private final Lock msgLock;
    private static final PropertiesUtil PROPS = new PropertiesUtil("log4j2.StatusLogger.properties");
    public static final String MAX_STATUS_ENTRIES = "log4j2.status.entries";
    private static final int MAX_ENTRIES = PROPS.getIntegerProperty(MAX_STATUS_ENTRIES, 200);
    public static final String DEFAULT_STATUS_LISTENER_LEVEL = "log4j2.StatusLogger.level";
    private static final String DEFAULT_STATUS_LEVEL = PROPS.getStringProperty(DEFAULT_STATUS_LISTENER_LEVEL);
    private static final StatusLogger STATUS_LOGGER = new StatusLogger(StatusLogger.class.getName(), ParameterizedNoReferenceMessageFactory.INSTANCE);

    /* loaded from: classes4.dex */
    private class BoundedQueue<E> extends ConcurrentLinkedQueue<E> {
        private static final long serialVersionUID = -3945953719763255337L;
        private final int size;

        BoundedQueue(int i) {
            this.size = i;
        }

        @Override // java.util.concurrent.ConcurrentLinkedQueue, java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
        public boolean add(E e) {
            int i;
            super.add(e);
            while (true) {
                int size = StatusLogger.this.messages.size();
                i = this.size;
                if (size <= i) {
                    break;
                }
                StatusLogger.this.messages.poll();
            }
            return i > 0;
        }
    }

    private StatusLogger(String str, MessageFactory messageFactory) {
        super(str, messageFactory);
        this.listeners = new CopyOnWriteArrayList();
        this.listenersLock = new ReentrantReadWriteLock();
        this.messages = new BoundedQueue(MAX_ENTRIES);
        this.msgLock = new ReentrantLock();
        String stringProperty = PROPS.getStringProperty(STATUS_DATE_FORMAT, "");
        this.logger = new SimpleLogger("StatusLogger", Level.ERROR, false, true, !Strings.isEmpty(stringProperty), false, stringProperty, messageFactory, PROPS, System.err);
        this.listenersLevel = Level.toLevel(DEFAULT_STATUS_LEVEL, Level.WARN).intLevel();
        if (isDebugPropertyEnabled()) {
            this.logger.setLevel(Level.TRACE);
        }
    }

    private static void closeSilently(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public static StatusLogger getLogger() {
        return STATUS_LOGGER;
    }

    private StackTraceElement getStackTraceElement(String str, StackTraceElement[] stackTraceElementArr) {
        if (str == null) {
            return null;
        }
        boolean z = false;
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            String className = stackTraceElement.getClassName();
            if (z && !str.equals(className)) {
                return stackTraceElement;
            }
            if (str.equals(className)) {
                z = true;
            } else if ("?".equals(className)) {
                break;
            }
        }
        return null;
    }

    private boolean isDebugPropertyEnabled() {
        return PropertiesUtil.getProperties().getBooleanProperty(Constants.LOG4J2_DEBUG, false, true);
    }

    public void clear() {
        this.msgLock.lock();
        try {
            this.messages.clear();
        } finally {
            this.msgLock.unlock();
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public Level getLevel() {
        return this.logger.getLevel();
    }

    public Iterable<StatusListener> getListeners() {
        return this.listeners;
    }

    public List<StatusData> getStatusData() {
        this.msgLock.lock();
        try {
            return new ArrayList(this.messages);
        } finally {
            this.msgLock.unlock();
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Throwable th) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logMessage(String str, Level level, Marker marker, Message message, Throwable th) {
        StatusData statusData = new StatusData(str != null ? getStackTraceElement(str, Thread.currentThread().getStackTrace()) : null, level, message, th, null);
        this.msgLock.lock();
        try {
            this.messages.add(statusData);
            this.msgLock.unlock();
            if (isDebugPropertyEnabled()) {
                this.logger.logMessage(str, level, marker, message, th);
            } else if (this.listeners.size() > 0) {
                for (StatusListener statusListener : this.listeners) {
                    if (statusData.getLevel().isMoreSpecificThan(statusListener.getStatusLevel())) {
                        statusListener.log(statusData);
                    }
                }
            } else {
                this.logger.logMessage(str, level, marker, message, th);
            }
        } catch (Throwable th2) {
            this.msgLock.unlock();
            throw th2;
        }
    }

    public void registerListener(StatusListener statusListener) {
        this.listenersLock.writeLock().lock();
        try {
            this.listeners.add(statusListener);
            Level statusLevel = statusListener.getStatusLevel();
            if (this.listenersLevel < statusLevel.intLevel()) {
                this.listenersLevel = statusLevel.intLevel();
            }
        } finally {
            this.listenersLock.writeLock().unlock();
        }
    }

    public void removeListener(StatusListener statusListener) {
        closeSilently(statusListener);
        this.listenersLock.writeLock().lock();
        try {
            this.listeners.remove(statusListener);
            int intLevel = Level.toLevel(DEFAULT_STATUS_LEVEL, Level.WARN).intLevel();
            for (StatusListener statusListener2 : this.listeners) {
                int intLevel2 = statusListener2.getStatusLevel().intLevel();
                if (intLevel < intLevel2) {
                    intLevel = intLevel2;
                }
            }
            this.listenersLevel = intLevel;
        } finally {
            this.listenersLock.writeLock().unlock();
        }
    }

    public void reset() {
        this.listenersLock.writeLock().lock();
        try {
            for (StatusListener statusListener : this.listeners) {
                closeSilently(statusListener);
            }
        } finally {
            this.listeners.clear();
            this.listenersLock.writeLock().unlock();
            clear();
        }
    }

    public void setLevel(Level level) {
        this.logger.setLevel(level);
    }

    public void updateListenerLevel(Level level) {
        if (level.intLevel() > this.listenersLevel) {
            this.listenersLevel = level.intLevel();
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object... objArr) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, CharSequence charSequence, Throwable th) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, Object obj, Throwable th) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, Message message, Throwable th) {
        return isEnabled(level, marker);
    }

    @Override // org.apache.logging.log4j.spi.AbstractLogger, org.apache.logging.log4j.Logger
    public boolean isEnabled(Level level, Marker marker) {
        if (isDebugPropertyEnabled()) {
            return true;
        }
        if (this.listeners.size() <= 0) {
            return this.logger.isEnabled(level, marker);
        }
        return this.listenersLevel >= level.intLevel();
    }
}
