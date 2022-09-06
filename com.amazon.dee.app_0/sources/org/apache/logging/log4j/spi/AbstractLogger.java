package org.apache.logging.log4j.spi;

import com.amazon.alexa.drive.userstudy.LogConstants;
import com.amazon.alexa.hho.metrics.HHOMetricsConstants;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LoggingException;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.internal.DefaultLogBuilder;
import org.apache.logging.log4j.message.DefaultFlowMessageFactory;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.FlowMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.MessageFactory2;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.apache.logging.log4j.message.ReusableMessageFactory;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.LambdaUtil;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.log4j.util.Strings;
import org.apache.logging.log4j.util.Supplier;
/* loaded from: classes4.dex */
public abstract class AbstractLogger implements ExtendedLogger, LocationAwareLogger, Serializable {
    private static final String CATCHING = "Catching";
    private static final String THROWING = "Throwing";
    private static final long serialVersionUID = 2;
    private final FlowMessageFactory flowMessageFactory;
    protected final transient ThreadLocal<DefaultLogBuilder> logBuilder;
    private final MessageFactory2 messageFactory;
    protected final String name;
    public static final Marker FLOW_MARKER = MarkerManager.getMarker("FLOW");
    public static final Marker ENTRY_MARKER = MarkerManager.getMarker("ENTER").setParents(FLOW_MARKER);
    public static final Marker EXIT_MARKER = MarkerManager.getMarker("EXIT").setParents(FLOW_MARKER);
    public static final Marker EXCEPTION_MARKER = MarkerManager.getMarker(HHOMetricsConstants.Event.EXCEPTION);
    public static final Marker THROWING_MARKER = MarkerManager.getMarker("THROWING").setParents(EXCEPTION_MARKER);
    public static final Marker CATCHING_MARKER = MarkerManager.getMarker("CATCHING").setParents(EXCEPTION_MARKER);
    public static final Class<? extends MessageFactory> DEFAULT_MESSAGE_FACTORY_CLASS = createClassForProperty("log4j2.messageFactory", ReusableMessageFactory.class, ParameterizedMessageFactory.class);
    public static final Class<? extends FlowMessageFactory> DEFAULT_FLOW_MESSAGE_FACTORY_CLASS = createFlowClassForProperty("log4j2.flowMessageFactory", DefaultFlowMessageFactory.class);
    private static final String FQCN = AbstractLogger.class.getName();
    private static ThreadLocal<int[]> recursionDepthHolder = new ThreadLocal<>();

    /* loaded from: classes4.dex */
    private class LocalLogBuilder extends ThreadLocal<DefaultLogBuilder> {
        private AbstractLogger logger;

        LocalLogBuilder(AbstractLogger abstractLogger) {
            this.logger = abstractLogger;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public DefaultLogBuilder initialValue() {
            return new DefaultLogBuilder(this.logger);
        }
    }

    public AbstractLogger() {
        this.name = getClass().getName();
        this.messageFactory = createDefaultMessageFactory();
        this.flowMessageFactory = createDefaultFlowMessageFactory();
        this.logBuilder = new LocalLogBuilder(this);
    }

    public static void checkMessageFactory(ExtendedLogger extendedLogger, MessageFactory messageFactory) {
        String name = extendedLogger.getName();
        MessageFactory messageFactory2 = extendedLogger.getMessageFactory();
        if (messageFactory != null && !messageFactory2.equals(messageFactory)) {
            StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with the message factory {}, which may create log events with unexpected formatting.", name, messageFactory2, messageFactory);
        } else if (messageFactory != null || messageFactory2.getClass().equals(DEFAULT_MESSAGE_FACTORY_CLASS)) {
        } else {
            StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with a null message factory (defaults to {}), which may create log events with unexpected formatting.", name, messageFactory2, DEFAULT_MESSAGE_FACTORY_CLASS.getName());
        }
    }

    private static Class<? extends MessageFactory> createClassForProperty(String str, Class<ReusableMessageFactory> cls, Class<ParameterizedMessageFactory> cls2) {
        try {
            return LoaderUtil.loadClass(PropertiesUtil.getProperties().getStringProperty(str, Constants.ENABLE_THREADLOCALS ? cls.getName() : cls2.getName())).asSubclass(MessageFactory.class);
        } catch (Throwable unused) {
            return cls2;
        }
    }

    private static FlowMessageFactory createDefaultFlowMessageFactory() {
        try {
            return DEFAULT_FLOW_MESSAGE_FACTORY_CLASS.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static MessageFactory2 createDefaultMessageFactory() {
        try {
            return narrow(DEFAULT_MESSAGE_FACTORY_CLASS.newInstance());
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Class<? extends FlowMessageFactory> createFlowClassForProperty(String str, Class<DefaultFlowMessageFactory> cls) {
        try {
            return LoaderUtil.loadClass(PropertiesUtil.getProperties().getStringProperty(str, cls.getName())).asSubclass(FlowMessageFactory.class);
        } catch (Throwable unused) {
            return cls;
        }
    }

    private static void decrementRecursionDepth() {
        int[] recursionDepthHolder2 = getRecursionDepthHolder();
        recursionDepthHolder2[0] = recursionDepthHolder2[0] - 1;
        if (recursionDepthHolder2[0] >= 0) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Recursion depth became negative: ");
        outline107.append(recursionDepthHolder2[0]);
        throw new IllegalStateException(outline107.toString());
    }

    @PerformanceSensitive
    private StackTraceElement getLocation(String str) {
        if (requiresLocation()) {
            return StackLocatorUtil.calcLocation(str);
        }
        return null;
    }

    private DefaultLogBuilder getLogBuilder(Level level) {
        DefaultLogBuilder defaultLogBuilder = this.logBuilder.get();
        return (!Constants.ENABLE_THREADLOCALS || defaultLogBuilder.isInUse()) ? new DefaultLogBuilder(this, level) : defaultLogBuilder;
    }

    public static int getRecursionDepth() {
        return getRecursionDepthHolder()[0];
    }

    private static int[] getRecursionDepthHolder() {
        int[] iArr = recursionDepthHolder.get();
        if (iArr == null) {
            int[] iArr2 = new int[1];
            recursionDepthHolder.set(iArr2);
            return iArr2;
        }
        return iArr;
    }

    private void handleLogMessageException(Exception exc, String str, Message message) {
        if (!(exc instanceof LoggingException)) {
            String format = message.getFormat();
            StringBuilder sb = new StringBuilder((format == null ? 4 : format.length()) + 100);
            sb.append(str);
            sb.append(" caught ");
            sb.append(exc.getClass().getName());
            sb.append(" logging ");
            sb.append(message.getClass().getSimpleName());
            sb.append(RealTimeTextConstants.COLON_SPACE);
            sb.append(format);
            StatusLogger.getLogger().warn(sb.toString(), (Throwable) exc);
            return;
        }
        throw ((LoggingException) exc);
    }

    private static void incrementRecursionDepth() {
        int[] recursionDepthHolder2 = getRecursionDepthHolder();
        recursionDepthHolder2[0] = recursionDepthHolder2[0] + 1;
    }

    @PerformanceSensitive
    private void logMessageSafely(String str, Level level, Marker marker, Message message, Throwable th) {
        try {
            logMessageTrackRecursion(str, level, marker, message, th);
        } finally {
            ReusableMessageFactory.release(message);
        }
    }

    @PerformanceSensitive
    private void logMessageTrackRecursion(String str, Level level, Marker marker, Message message, Throwable th) {
        try {
            incrementRecursionDepth();
            tryLogMessage(str, getLocation(str), level, marker, message, th);
        } finally {
            decrementRecursionDepth();
        }
    }

    private static MessageFactory2 narrow(MessageFactory messageFactory) {
        if (messageFactory instanceof MessageFactory2) {
            return (MessageFactory2) messageFactory;
        }
        return new MessageFactory2Adapter(messageFactory);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        try {
            Field declaredField = getClass().getDeclaredField("logBuilder");
            declaredField.setAccessible(true);
            declaredField.set(this, new LocalLogBuilder(this));
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            StatusLogger.getLogger().warn("Unable to initialize LogBuilder");
        }
    }

    @PerformanceSensitive
    private void tryLogMessage(String str, StackTraceElement stackTraceElement, Level level, Marker marker, Message message, Throwable th) {
        try {
            log(level, marker, str, stackTraceElement, message, th);
        } catch (Exception e) {
            handleLogMessageException(e, str, message);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder always() {
        DefaultLogBuilder defaultLogBuilder = this.logBuilder.get();
        if (defaultLogBuilder.isInUse()) {
            return new DefaultLogBuilder(this);
        }
        return defaultLogBuilder.reset(Level.OFF);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atDebug() {
        return atLevel(Level.DEBUG);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atError() {
        return atLevel(Level.ERROR);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atFatal() {
        return atLevel(Level.FATAL);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atInfo() {
        return atLevel(Level.INFO);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atLevel(Level level) {
        if (isEnabled(level)) {
            return getLogBuilder(level).reset(level);
        }
        return LogBuilder.NOOP;
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atTrace() {
        return atLevel(Level.TRACE);
    }

    @Override // org.apache.logging.log4j.Logger
    public LogBuilder atWarn() {
        return atLevel(Level.WARN);
    }

    @Override // org.apache.logging.log4j.Logger
    public void catching(Level level, Throwable th) {
        catching(FQCN, level, th);
    }

    protected Message catchingMsg(Throwable th) {
        return this.messageFactory.newMessage(CATCHING);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.DEBUG, marker, charSequence, (Throwable) null);
    }

    protected EntryMessage enter(String str, String str2, Supplier<?>... supplierArr) {
        if (isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            Level level = Level.TRACE;
            Marker marker = ENTRY_MARKER;
            EntryMessage entryMsg = entryMsg(str2, supplierArr);
            logMessageSafely(str, level, marker, entryMsg, null);
            return entryMsg;
        }
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    @Deprecated
    public void entry() {
        entry(FQCN, null);
    }

    protected EntryMessage entryMsg(String str, Object... objArr) {
        int length = objArr == null ? 0 : objArr.length;
        if (length == 0) {
            if (Strings.isEmpty(str)) {
                return this.flowMessageFactory.newEntryMessage(null);
            }
            return this.flowMessageFactory.newEntryMessage(new SimpleMessage(str));
        } else if (str != null) {
            return this.flowMessageFactory.newEntryMessage(new ParameterizedMessage(str, objArr));
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("params(");
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    outline107.append(", ");
                }
                Object obj = objArr[i];
                outline107.append(obj instanceof Message ? ((Message) obj).getFormattedMessage() : String.valueOf(obj));
            }
            outline107.append(')');
            return this.flowMessageFactory.newEntryMessage(new SimpleMessage(outline107));
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    @Deprecated
    public void exit() {
        exit(FQCN, null);
    }

    protected Message exitMsg(String str, Object obj) {
        if (obj == null) {
            if (str == null) {
                return this.messageFactory.newMessage(LogConstants.ACTION_EXIT);
            }
            MessageFactory2 messageFactory2 = this.messageFactory;
            return messageFactory2.newMessage("Exit: " + str);
        } else if (str == null) {
            MessageFactory2 messageFactory22 = this.messageFactory;
            return messageFactory22.newMessage("Exit with(" + obj + ')');
        } else {
            MessageFactory2 messageFactory23 = this.messageFactory;
            return messageFactory23.newMessage("Exit: " + str, obj);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public <MF extends MessageFactory> MF getMessageFactory() {
        return this.messageFactory;
    }

    @Override // org.apache.logging.log4j.Logger
    public String getName() {
        return this.name;
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.INFO, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isDebugEnabled() {
        return isEnabled(Level.DEBUG, null, null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isEnabled(Level level) {
        return isEnabled(level, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isErrorEnabled() {
        return isEnabled(Level.ERROR, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isFatalEnabled() {
        return isEnabled(Level.FATAL, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isInfoEnabled() {
        return isEnabled(Level.INFO, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isTraceEnabled() {
        return isEnabled(Level.TRACE, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isWarnEnabled() {
        return isEnabled(Level.WARN, (Marker) null, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Message message) {
        logIfEnabled(FQCN, level, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, Message message, Throwable th) {
        if (isEnabled(level, marker, message, th)) {
            logMessageSafely(str, level, marker, message, th);
        }
    }

    protected void logMessage(String str, Level level, Marker marker, CharSequence charSequence, Throwable th) {
        logMessageSafely(str, level, marker, this.messageFactory.newMessage(charSequence), th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void printf(Level level, Marker marker, String str, Object... objArr) {
        if (isEnabled(level, marker, str, objArr)) {
            StringFormattedMessage stringFormattedMessage = new StringFormattedMessage(str, objArr);
            logMessageSafely(FQCN, level, marker, stringFormattedMessage, stringFormattedMessage.getThrowable());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requiresLocation() {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public <T extends Throwable> T throwing(T t) {
        return (T) throwing(FQCN, Level.ERROR, t);
    }

    protected Message throwingMsg(Throwable th) {
        return this.messageFactory.newMessage(THROWING);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry() {
        return enter(FQCN, (String) null, (Object[]) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void traceExit() {
        exit(FQCN, null, null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.WARN, marker, message, message != null ? message.getThrowable() : null);
    }

    protected void catching(String str, Level level, Throwable th) {
        if (isEnabled(level, CATCHING_MARKER, (Object) null, (Throwable) null)) {
            logMessageSafely(str, level, CATCHING_MARKER, catchingMsg(th), th);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void entry(Object... objArr) {
        entry(FQCN, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    @Deprecated
    public <R> R exit(R r) {
        return (R) exit(FQCN, r);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isDebugEnabled(Marker marker) {
        return isEnabled(Level.DEBUG, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isEnabled(Level level, Marker marker) {
        return isEnabled(level, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isErrorEnabled(Marker marker) {
        return isEnabled(Level.ERROR, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isFatalEnabled(Marker marker) {
        return isEnabled(Level.FATAL, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isInfoEnabled(Marker marker) {
        return isEnabled(Level.INFO, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isTraceEnabled(Marker marker) {
        return isEnabled(Level.TRACE, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isWarnEnabled(Marker marker) {
        return isEnabled(Level.WARN, marker, (Object) null, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, level, marker, message, th);
    }

    protected void logMessage(String str, Level level, Marker marker, Object obj, Throwable th) {
        logMessageSafely(str, level, marker, this.messageFactory.newMessage(obj), th);
    }

    @Override // org.apache.logging.log4j.Logger
    public <T extends Throwable> T throwing(Level level, T t) {
        return (T) throwing(FQCN, level, t);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(String str, Object... objArr) {
        return enter(FQCN, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(R r) {
        return (R) exit(FQCN, null, r);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, message != null ? message.getThrowable() : null);
    }

    @Deprecated
    protected EntryMessage enter(String str, String str2, MessageSupplier... messageSupplierArr) {
        if (isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            Level level = Level.TRACE;
            Marker marker = ENTRY_MARKER;
            EntryMessage entryMsg = entryMsg(str2, messageSupplierArr);
            logMessageSafely(str, level, marker, entryMsg, null);
            return entryMsg;
        }
        return null;
    }

    protected void entry(String str, Object... objArr) {
        if (isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            if (objArr == null) {
                logMessageSafely(str, Level.TRACE, ENTRY_MARKER, entryMsg((String) null, (Supplier<?>[]) null), null);
            } else {
                logMessageSafely(str, Level.TRACE, ENTRY_MARKER, entryMsg((String) null, objArr), null);
            }
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.ERROR, marker, charSequence, (Throwable) null);
    }

    protected <R> R exit(String str, R r) {
        if (isEnabled(Level.TRACE, EXIT_MARKER, (CharSequence) null, (Throwable) null)) {
            logMessageSafely(str, Level.TRACE, EXIT_MARKER, exitMsg(null, r), null);
        }
        return r;
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.FATAL, marker, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.INFO, marker, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, level, marker, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, MessageSupplier messageSupplier, Throwable th) {
        if (isEnabled(level, marker, messageSupplier, th)) {
            logMessage(str, level, marker, messageSupplier, th);
        }
    }

    protected void logMessage(String str, Level level, Marker marker, MessageSupplier messageSupplier, Throwable th) {
        Message message = LambdaUtil.get(messageSupplier);
        if (th == null && message != null) {
            th = message.getThrowable();
        }
        logMessageSafely(str, level, marker, message, th);
    }

    protected <T extends Throwable> T throwing(String str, Level level, T t) {
        if (isEnabled(level, THROWING_MARKER, (Object) null, (Throwable) null)) {
            logMessageSafely(str, level, THROWING_MARKER, throwingMsg(t), t);
        }
        return t;
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.TRACE, marker, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(Supplier<?>... supplierArr) {
        return enter(FQCN, (String) null, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(String str, R r) {
        return (R) exit(FQCN, str, r);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.WARN, marker, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void catching(Throwable th) {
        if (isEnabled(Level.ERROR, CATCHING_MARKER, (Object) null, (Throwable) null)) {
            logMessageSafely(FQCN, Level.ERROR, CATCHING_MARKER, catchingMsg(th), th);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, CharSequence charSequence, Throwable th) {
        if (isEnabled(level, marker, charSequence, th)) {
            logMessage(FQCN, level, marker, charSequence, th);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void printf(Level level, String str, Object... objArr) {
        if (isEnabled(level, (Marker) null, str, objArr)) {
            StringFormattedMessage stringFormattedMessage = new StringFormattedMessage(str, objArr);
            logMessageSafely(FQCN, level, null, stringFormattedMessage, stringFormattedMessage.getThrowable());
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(String str, Supplier<?>... supplierArr) {
        return enter(FQCN, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void traceExit(EntryMessage entryMessage) {
        if (entryMessage == null || !isEnabled(Level.TRACE, EXIT_MARKER, (Message) entryMessage, (Throwable) null)) {
            return;
        }
        logMessageSafely(FQCN, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage(entryMessage), null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, marker, obj, (Throwable) null);
    }

    protected EntryMessage enter(String str, String str2, Object... objArr) {
        if (isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            Level level = Level.TRACE;
            Marker marker = ENTRY_MARKER;
            EntryMessage entryMsg = entryMsg(str2, objArr);
            logMessageSafely(str, level, marker, entryMsg, null);
            return entryMsg;
        }
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.ERROR, marker, obj, (Throwable) null);
    }

    protected <R> R exit(String str, String str2, R r) {
        if (isEnabled(Level.TRACE, EXIT_MARKER, (CharSequence) null, (Throwable) null)) {
            logMessageSafely(str, Level.TRACE, EXIT_MARKER, exitMsg(str2, r), null);
        }
        return r;
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.FATAL, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.INFO, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, Object obj, Throwable th) {
        if (isEnabled(level, marker, obj, th)) {
            logMessage(str, level, marker, obj, th);
        }
    }

    protected void logMessage(String str, Level level, Marker marker, Supplier<?> supplier, Throwable th) {
        Message message = LambdaUtil.getMessage(supplier, this.messageFactory);
        if (th == null && message != null) {
            th = message.getThrowable();
        }
        logMessageSafely(str, level, marker, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.TRACE, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(Message message) {
        return enter(FQCN, message);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.WARN, marker, obj, (Throwable) null);
    }

    public AbstractLogger(String str) {
        this(str, createDefaultMessageFactory());
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Object obj) {
        logIfEnabled(FQCN, level, marker, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(EntryMessage entryMessage, R r) {
        if (entryMessage != null && isEnabled(Level.TRACE, EXIT_MARKER, (Message) entryMessage, (Throwable) null)) {
            logMessageSafely(FQCN, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage((Object) r, entryMessage), null);
        }
        return r;
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, obj, th);
    }

    public AbstractLogger(String str, MessageFactory messageFactory) {
        this.name = str;
        this.messageFactory = messageFactory == null ? createDefaultMessageFactory() : narrow(messageFactory);
        this.flowMessageFactory = createDefaultFlowMessageFactory();
        this.logBuilder = new LocalLogBuilder(this);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, (Throwable) null);
    }

    @Deprecated
    protected EntryMessage enter(String str, MessageSupplier messageSupplier) {
        if (isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            Level level = Level.TRACE;
            Marker marker = ENTRY_MARKER;
            EntryMessage newEntryMessage = this.flowMessageFactory.newEntryMessage(messageSupplier.get());
            logMessageSafely(str, level, marker, newEntryMessage, null);
            return newEntryMessage;
        }
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str) {
        logIfEnabled(FQCN, Level.INFO, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Object obj, Throwable th) {
        if (isEnabled(level, marker, obj, th)) {
            logMessage(FQCN, level, marker, obj, th);
        }
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, CharSequence charSequence, Throwable th) {
        if (isEnabled(level, marker, charSequence, th)) {
            logMessage(str, level, marker, charSequence, th);
        }
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Throwable th) {
        logMessageSafely(str, level, marker, this.messageFactory.newMessage(str2), th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str) {
        logIfEnabled(FQCN, Level.WARN, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.INFO, marker, str, objArr);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2) {
        Message newMessage = this.messageFactory.newMessage(str2);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(Message message, R r) {
        if (message != null && isEnabled(Level.TRACE, EXIT_MARKER, message, (Throwable) null)) {
            logMessageSafely(FQCN, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage(r, message), null);
        }
        return r;
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.WARN, marker, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str) {
        logIfEnabled(FQCN, level, marker, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, Supplier<?> supplier, Throwable th) {
        if (isEnabled(level, marker, supplier, th)) {
            logMessage(str, level, marker, supplier, th);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Message message) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Message message) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Message message) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Message message) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, level, marker, str, objArr);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object... objArr) {
        Message newMessage = this.messageFactory.newMessage(str2, objArr);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Message message) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Message message) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, th);
    }

    protected EntryMessage enter(String str, Message message) {
        if (isEnabled(Level.TRACE, ENTRY_MARKER, (Object) null, (Throwable) null)) {
            Level level = Level.TRACE;
            Marker marker = ENTRY_MARKER;
            EntryMessage newEntryMessage = this.flowMessageFactory.newEntryMessage(message);
            logMessageSafely(str, level, marker, newEntryMessage, null);
            return newEntryMessage;
        }
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, level, marker, str, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2) {
        if (isEnabled(level, marker, str2)) {
            logMessage(str, level, marker, str2);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, charSequence, (Throwable) null);
    }

    protected EntryMessage entryMsg(String str, MessageSupplier... messageSupplierArr) {
        int length = messageSupplierArr == null ? 0 : messageSupplierArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = messageSupplierArr[i].get();
            objArr[i] = objArr[i] != null ? ((Message) objArr[i]).getFormattedMessage() : null;
        }
        return entryMsg(str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Message message) {
        logIfEnabled(FQCN, level, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj) {
        Message newMessage = this.messageFactory.newMessage(str2, obj);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, charSequence, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Message message, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, message, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Supplier<?>... supplierArr) {
        if (isEnabled(level, marker, str2)) {
            logMessage(str, level, marker, str2, supplierArr);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, charSequence, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Object obj) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Object obj) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Object obj) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, CharSequence charSequence) {
        logIfEnabled(FQCN, level, (Marker) null, charSequence, (Throwable) null);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Object obj) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Object obj) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, obj, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, charSequence, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object... objArr) {
        if (isEnabled(level, marker, str2, objArr)) {
            logMessage(str, level, marker, str2, objArr);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Object obj) {
        logIfEnabled(FQCN, level, (Marker) null, obj, (Throwable) null);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, objArr);
    }

    protected EntryMessage entryMsg(String str, Supplier<?>... supplierArr) {
        int length = supplierArr == null ? 0 : supplierArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = supplierArr[i].get();
            if (objArr[i] instanceof Message) {
                objArr[i] = ((Message) objArr[i]).getFormattedMessage();
            }
        }
        return entryMsg(str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Object obj, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, obj, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj) {
        if (isEnabled(level, marker, str2, obj)) {
            logMessage(str, level, marker, str2, obj);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str) {
        logIfEnabled(FQCN, level, (Marker) null, str, (Throwable) null);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object... objArr) {
        logIfEnabled(FQCN, level, (Marker) null, str, objArr);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2) {
        if (isEnabled(level, marker, str2, obj, obj2)) {
            logMessage(str, level, marker, str2, obj, obj2);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, str, th);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.DEBUG, marker, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.ERROR, marker, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.FATAL, marker, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.INFO, marker, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Supplier<?> supplier) {
        logIfEnabled(FQCN, level, (Marker) null, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.TRACE, marker, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Supplier<?> supplier) {
        logIfEnabled(FQCN, Level.WARN, marker, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.INFO, marker, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, supplier, th);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.WARN, marker, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Supplier<?> supplier) {
        logIfEnabled(FQCN, level, marker, supplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, supplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, level, marker, str, supplierArr);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6, obj7);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, supplierArr);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, level, marker, supplier, th);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, level, (Marker) null, str, supplierArr);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, level, marker, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, level, marker, messageSupplier, th);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, messageSupplier, th);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, MessageSupplier messageSupplier) {
        logIfEnabled(FQCN, level, (Marker) null, messageSupplier, (Throwable) null);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, messageSupplier, th);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, level, marker, str, obj);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2);
    }

    protected void logMessage(String str, Level level, Marker marker, String str2, Supplier<?>... supplierArr) {
        Message newMessage = this.messageFactory.newMessage(str2, LambdaUtil.getAll(supplierArr));
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger, org.apache.logging.log4j.spi.LocationAwareLogger
    public void logMessage(Level level, Marker marker, String str, StackTraceElement stackTraceElement, Message message, Throwable th) {
        try {
            try {
                incrementRecursionDepth();
                log(level, marker, str, stackTraceElement, message, th);
            } catch (Exception e) {
                handleLogMessageException(e, str, message);
            }
        } finally {
            decrementRecursionDepth();
            ReusableMessageFactory.release(message);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.spi.ExtendedLogger
    public void logIfEnabled(String str, Level level, Marker marker, String str2, Throwable th) {
        if (isEnabled(level, marker, str2, th)) {
            logMessage(str, level, marker, str2, th);
        }
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.DEBUG, null, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.ERROR, null, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.FATAL, null, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.INFO, null, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.TRACE, null, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.WARN, null, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.DEBUG, null, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.ERROR, null, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.FATAL, null, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.INFO, null, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.TRACE, null, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.WARN, null, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.DEBUG, null, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.ERROR, null, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.FATAL, null, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.INFO, null, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, level, null, str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.TRACE, null, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.WARN, null, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.DEBUG, null, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.ERROR, null, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.FATAL, null, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.INFO, null, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, level, null, str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.TRACE, null, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.WARN, null, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.DEBUG, null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.ERROR, null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.FATAL, null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.INFO, null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, level, null, str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.TRACE, null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.WARN, null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.DEBUG, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.ERROR, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.FATAL, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.INFO, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, level, null, str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.TRACE, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.WARN, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.DEBUG, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.ERROR, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.FATAL, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.INFO, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, level, null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.TRACE, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.WARN, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.DEBUG, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.ERROR, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.FATAL, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.INFO, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, level, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.TRACE, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.WARN, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.DEBUG, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.ERROR, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.FATAL, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.INFO, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, level, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.TRACE, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.WARN, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, level, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, level, null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    protected void log(Level level, Marker marker, String str, StackTraceElement stackTraceElement, Message message, Throwable th) {
        logMessage(str, level, marker, message, th);
    }
}
