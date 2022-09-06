package org.slf4j.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.helpers.MarkerIgnoringBase;
/* loaded from: classes5.dex */
public class AlexaSlf4jAndroidLogger extends MarkerIgnoringBase {
    private ConcurrentMap<ILoggerFactory, Logger> loggerMap = new ConcurrentHashMap();
    private final List<Logger> loggers = new ArrayList();
    private final String name;

    public AlexaSlf4jAndroidLogger(String str) {
        this.name = str;
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        for (Logger logger : this.loggers) {
            try {
                logger.debug(str);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        for (Logger logger : this.loggers) {
            try {
                logger.error(str);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.helpers.MarkerIgnoringBase, org.slf4j.helpers.NamedLoggerBase, org.slf4j.Logger
    public String getName() {
        return this.name;
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        for (Logger logger : this.loggers) {
            try {
                logger.info(str);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        for (Logger logger : this.loggers) {
            if (logger.isInfoEnabled()) {
                return true;
            }
        }
        return false;
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        for (Logger logger : this.loggers) {
            if (logger.isErrorEnabled()) {
                return true;
            }
        }
        return false;
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        for (Logger logger : this.loggers) {
            if (logger.isInfoEnabled()) {
                return true;
            }
        }
        return false;
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        for (Logger logger : this.loggers) {
            if (logger.isTraceEnabled()) {
                return true;
            }
        }
        return false;
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        for (Logger logger : this.loggers) {
            if (logger.isWarnEnabled()) {
                return true;
            }
        }
        return false;
    }

    public synchronized void subscribe(ILoggerFactory iLoggerFactory) {
        if (this.loggerMap.get(iLoggerFactory) != null) {
            return;
        }
        Logger logger = iLoggerFactory.getLogger(this.name);
        if (this.loggerMap.putIfAbsent(iLoggerFactory, logger) == null) {
            this.loggers.add(logger);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        for (Logger logger : this.loggers) {
            try {
                logger.trace(str);
            } catch (Throwable unused) {
            }
        }
    }

    public synchronized void unsubscribe(ILoggerFactory iLoggerFactory) {
        Logger remove = this.loggerMap.remove(iLoggerFactory);
        if (remove != null) {
            this.loggers.remove(remove);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        for (Logger logger : this.loggers) {
            try {
                logger.warn(str);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        for (Logger logger : this.loggers) {
            try {
                logger.debug(str, obj);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj) {
        for (Logger logger : this.loggers) {
            try {
                logger.error(str, obj);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj) {
        for (Logger logger : this.loggers) {
            try {
                logger.info(str, obj);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        for (Logger logger : this.loggers) {
            try {
                logger.trace(str, obj);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        for (Logger logger : this.loggers) {
            try {
                logger.warn(str, obj);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        for (Logger logger : this.loggers) {
            try {
                logger.debug(str, obj, obj2);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object obj, Object obj2) {
        for (Logger logger : this.loggers) {
            try {
                logger.trace(str, obj, obj2);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object obj, Object obj2) {
        for (Logger logger : this.loggers) {
            try {
                logger.info(str, obj, obj2);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        for (Logger logger : this.loggers) {
            try {
                logger.trace(str, obj, obj2);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        for (Logger logger : this.loggers) {
            try {
                logger.warn(str, obj, obj2);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        for (Logger logger : this.loggers) {
            try {
                logger.debug(str, objArr);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void error(String str, Object... objArr) {
        for (Logger logger : this.loggers) {
            try {
                logger.error(str, objArr);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void info(String str, Object... objArr) {
        for (Logger logger : this.loggers) {
            try {
                logger.info(str, objArr);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object... objArr) {
        for (Logger logger : this.loggers) {
            try {
                logger.trace(str, objArr);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object... objArr) {
        for (Logger logger : this.loggers) {
            try {
                logger.warn(str, objArr);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        for (Logger logger : this.loggers) {
            try {
                logger.debug(str, th);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        for (Logger logger : this.loggers) {
            try {
                logger.error(str, th);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        for (Logger logger : this.loggers) {
            try {
                logger.info(str, th);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Throwable th) {
        for (Logger logger : this.loggers) {
            try {
                logger.trace(str, th);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        for (Logger logger : this.loggers) {
            try {
                logger.warn(str, th);
            } catch (Throwable unused) {
            }
        }
    }
}
