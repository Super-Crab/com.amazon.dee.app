package org.apache.logging.log4j.spi;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.util.LoaderUtil;
/* loaded from: classes4.dex */
public abstract class AbstractLoggerAdapter<L> implements LoggerAdapter<L>, LoggerContextShutdownAware {
    protected final Map<LoggerContext, ConcurrentMap<String, L>> registry = new ConcurrentHashMap();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.lock.writeLock().lock();
        try {
            this.registry.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override // org.apache.logging.log4j.spi.LoggerContextShutdownAware
    public void contextShutdown(LoggerContext loggerContext) {
        this.registry.remove(loggerContext);
    }

    protected abstract LoggerContext getContext();

    protected LoggerContext getContext(Class<?> cls) {
        ClassLoader classLoader = cls != null ? cls.getClassLoader() : null;
        if (classLoader == null) {
            classLoader = LoaderUtil.getThreadContextClassLoader();
        }
        return LogManager.getContext(classLoader, false);
    }

    @Override // org.apache.logging.log4j.spi.LoggerAdapter
    public L getLogger(String str) {
        LoggerContext context = getContext();
        ConcurrentMap<String, L> loggersInContext = getLoggersInContext(context);
        L l = loggersInContext.get(str);
        if (l != null) {
            return l;
        }
        loggersInContext.putIfAbsent(str, newLogger(str, context));
        return loggersInContext.get(str);
    }

    public Set<LoggerContext> getLoggerContexts() {
        return new HashSet(this.registry.keySet());
    }

    public ConcurrentMap<String, L> getLoggersInContext(LoggerContext loggerContext) {
        this.lock.readLock().lock();
        try {
            ConcurrentMap<String, L> concurrentMap = this.registry.get(loggerContext);
            if (concurrentMap != null) {
                return concurrentMap;
            }
            this.lock.writeLock().lock();
            try {
                ConcurrentMap<String, L> concurrentMap2 = this.registry.get(loggerContext);
                if (concurrentMap2 == null) {
                    concurrentMap2 = new ConcurrentHashMap<>();
                    this.registry.put(loggerContext, concurrentMap2);
                    if (loggerContext instanceof LoggerContextShutdownEnabled) {
                        ((LoggerContextShutdownEnabled) loggerContext).addShutdownListener(this);
                    }
                }
                return concurrentMap2;
            } finally {
                this.lock.writeLock().unlock();
            }
        } finally {
            this.lock.readLock().unlock();
        }
    }

    protected abstract L newLogger(String str, LoggerContext loggerContext);
}
