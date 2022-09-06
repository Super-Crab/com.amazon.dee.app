package org.apache.logging.log4j.simple;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.apache.logging.log4j.util.PropertiesUtil;
/* loaded from: classes4.dex */
public class SimpleLoggerContext implements LoggerContext {
    protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
    private static final String SYSTEM_ERR = "system.err";
    private static final String SYSTEM_OUT = "system.out";
    protected static final String SYSTEM_PREFIX = "org.apache.logging.log4j.simplelog.";
    private final String dateTimeFormat;
    private final PrintStream stream;
    private final LoggerRegistry<ExtendedLogger> loggerRegistry = new LoggerRegistry<>();
    private final PropertiesUtil props = new PropertiesUtil("log4j2.simplelog.properties");
    private final boolean showContextMap = this.props.getBooleanProperty("org.apache.logging.log4j.simplelog.showContextMap", false);
    private final boolean showLogName = this.props.getBooleanProperty("org.apache.logging.log4j.simplelog.showlogname", false);
    private final boolean showShortName = this.props.getBooleanProperty("org.apache.logging.log4j.simplelog.showShortLogname", true);
    private final boolean showDateTime = this.props.getBooleanProperty("org.apache.logging.log4j.simplelog.showdatetime", false);
    private final Level defaultLevel = Level.toLevel(this.props.getStringProperty("org.apache.logging.log4j.simplelog.level"), Level.ERROR);

    public SimpleLoggerContext() {
        PrintStream printStream;
        this.dateTimeFormat = this.showDateTime ? this.props.getStringProperty("org.apache.logging.log4j.simplelog.dateTimeFormat", DEFAULT_DATE_TIME_FORMAT) : null;
        String stringProperty = this.props.getStringProperty("org.apache.logging.log4j.simplelog.logFile", SYSTEM_ERR);
        if (SYSTEM_ERR.equalsIgnoreCase(stringProperty)) {
            printStream = System.err;
        } else if (SYSTEM_OUT.equalsIgnoreCase(stringProperty)) {
            printStream = System.out;
        } else {
            try {
                printStream = new PrintStream(new FileOutputStream(stringProperty));
            } catch (FileNotFoundException unused) {
                printStream = System.err;
            }
        }
        this.stream = printStream;
    }

    @Override // org.apache.logging.log4j.spi.LoggerContext
    public Object getExternalContext() {
        return null;
    }

    @Override // org.apache.logging.log4j.spi.LoggerContext
    public ExtendedLogger getLogger(String str) {
        return getLogger(str, (MessageFactory) null);
    }

    @Override // org.apache.logging.log4j.spi.LoggerContext
    public boolean hasLogger(String str) {
        return false;
    }

    @Override // org.apache.logging.log4j.spi.LoggerContext
    public boolean hasLogger(String str, Class<? extends MessageFactory> cls) {
        return false;
    }

    @Override // org.apache.logging.log4j.spi.LoggerContext
    public boolean hasLogger(String str, MessageFactory messageFactory) {
        return false;
    }

    @Override // org.apache.logging.log4j.spi.LoggerContext
    public ExtendedLogger getLogger(String str, MessageFactory messageFactory) {
        ExtendedLogger logger = this.loggerRegistry.getLogger(str, messageFactory);
        if (logger != null) {
            AbstractLogger.checkMessageFactory(logger, messageFactory);
            return logger;
        }
        this.loggerRegistry.putIfAbsent(str, messageFactory, new SimpleLogger(str, this.defaultLevel, this.showLogName, this.showShortName, this.showDateTime, this.showContextMap, this.dateTimeFormat, messageFactory, this.props, this.stream));
        return this.loggerRegistry.getLogger(str, messageFactory);
    }
}
