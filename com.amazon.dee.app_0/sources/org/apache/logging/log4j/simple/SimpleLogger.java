package org.apache.logging.log4j.simple;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.Strings;
/* loaded from: classes4.dex */
public class SimpleLogger extends AbstractLogger {
    private static final char SPACE = ' ';
    private static final long serialVersionUID = 1;
    private final DateFormat dateFormatter;
    private Level level;
    private final String logName;
    private final boolean showContextMap;
    private final boolean showDateTime;
    private PrintStream stream;

    public SimpleLogger(String str, Level level, boolean z, boolean z2, boolean z3, boolean z4, String str2, MessageFactory messageFactory, PropertiesUtil propertiesUtil, PrintStream printStream) {
        super(str, messageFactory);
        SimpleDateFormat simpleDateFormat;
        this.level = Level.toLevel(propertiesUtil.getStringProperty("org.apache.logging.log4j.simplelog." + str + ".level"), level);
        if (z2) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf > 0 && lastIndexOf < str.length()) {
                this.logName = str.substring(lastIndexOf + 1);
            } else {
                this.logName = str;
            }
        } else if (z) {
            this.logName = str;
        } else {
            this.logName = null;
        }
        this.showDateTime = z3;
        this.showContextMap = z4;
        this.stream = printStream;
        if (z3) {
            try {
                simpleDateFormat = new SimpleDateFormat(str2);
            } catch (IllegalArgumentException unused) {
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS zzz");
            }
            this.dateFormatter = simpleDateFormat;
            return;
        }
        this.dateFormatter = null;
    }

    @Override // org.apache.logging.log4j.Logger
    public Level getLevel() {
        return this.level;
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, Message message, Throwable th) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logMessage(String str, Level level, Marker marker, Message message, Throwable th) {
        String format;
        StringBuilder sb = new StringBuilder();
        if (this.showDateTime) {
            Date date = new Date();
            synchronized (this.dateFormatter) {
                format = this.dateFormatter.format(date);
            }
            sb.append(format);
            sb.append(' ');
        }
        sb.append(level.toString());
        sb.append(' ');
        if (Strings.isNotEmpty(this.logName)) {
            sb.append(this.logName);
            sb.append(' ');
        }
        sb.append(message.getFormattedMessage());
        if (this.showContextMap) {
            Map<String, String> immutableContext = ThreadContext.getImmutableContext();
            if (immutableContext.size() > 0) {
                sb.append(' ');
                sb.append(immutableContext.toString());
                sb.append(' ');
            }
        }
        Object[] parameters = message.getParameters();
        if (th == null && parameters != null && parameters.length > 0 && (parameters[parameters.length - 1] instanceof Throwable)) {
            th = (Throwable) parameters[parameters.length - 1];
        }
        this.stream.println(sb.toString());
        if (th != null) {
            this.stream.print(' ');
            th.printStackTrace(this.stream);
        }
    }

    public void setLevel(Level level) {
        if (level != null) {
            this.level = level;
        }
    }

    public void setStream(PrintStream printStream) {
        this.stream = printStream;
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, CharSequence charSequence, Throwable th) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, Object obj, Throwable th) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object... objArr) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return this.level.intLevel() >= level.intLevel();
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public boolean isEnabled(Level level, Marker marker, String str, Throwable th) {
        return this.level.intLevel() >= level.intLevel();
    }
}
