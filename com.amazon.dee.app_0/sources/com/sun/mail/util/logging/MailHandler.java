package com.sun.mail.util.logging;

import androidx.core.app.NotificationCompat;
import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.ErrorManager;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import javax.activation.DataHandler;
import javax.activation.FileTypeMap;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessageContext;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Service;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class MailHandler extends Handler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MIN_HEADER_SIZE = 1024;
    private volatile Filter[] attachmentFilters;
    private Formatter[] attachmentFormatters;
    private Formatter[] attachmentNames;
    private Authenticator auth;
    private int capacity;
    private Comparator<? super LogRecord> comparator;
    private FileTypeMap contentTypes;
    private LogRecord[] data;
    private String encoding;
    private volatile Filter filter;
    private Formatter formatter;
    private boolean isWriting;
    private Properties mailProps;
    private Filter pushFilter;
    private Level pushLevel;
    private volatile boolean sealed;
    private Session session;
    private int size;
    private Formatter subjectFormatter;
    private static final Filter[] EMPTY_FILTERS = new Filter[0];
    private static final Formatter[] EMPTY_FORMATTERS = new Formatter[0];
    private static final int offValue = Level.OFF.intValue();
    private static final PrivilegedAction<Object> MAILHANDLER_LOADER = new GetAndSetContext(MailHandler.class);
    private static final ThreadLocal<Level> MUTEX = new ThreadLocal<>();
    private static final Level MUTEX_PUBLISH = Level.ALL;
    private static final Level MUTEX_REPORT = Level.OFF;
    private volatile Level logLevel = Level.ALL;
    private volatile ErrorManager errorManager = defaultErrorManager();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class DefaultAuthenticator extends Authenticator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final String pass;

        DefaultAuthenticator(String str) {
            this.pass = str;
        }

        @Override // javax.mail.Authenticator
        protected final PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(getDefaultUserName(), this.pass);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class GetAndSetContext implements PrivilegedAction<Object> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final Object NOT_MODIFIED = GetAndSetContext.class;
        private final Object source;

        GetAndSetContext(Object obj) {
            this.source = obj;
        }

        @Override // java.security.PrivilegedAction
        public final Object run() {
            ClassLoader classLoader;
            Thread currentThread = Thread.currentThread();
            ClassLoader contextClassLoader = currentThread.getContextClassLoader();
            Object obj = this.source;
            if (obj == null) {
                classLoader = null;
            } else if (obj instanceof ClassLoader) {
                classLoader = (ClassLoader) obj;
            } else if (obj instanceof Class) {
                classLoader = ((Class) obj).getClassLoader();
            } else if (obj instanceof Thread) {
                classLoader = ((Thread) obj).getContextClassLoader();
            } else {
                classLoader = obj.getClass().getClassLoader();
            }
            if (contextClassLoader != classLoader) {
                currentThread.setContextClassLoader(classLoader);
                return contextClassLoader;
            }
            return NOT_MODIFIED;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class TailNameFormatter extends Formatter {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final String name;

        TailNameFormatter(String str) {
            this.name = str;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof TailNameFormatter) {
                return this.name.equals(((TailNameFormatter) obj).name);
            }
            return false;
        }

        @Override // java.util.logging.Formatter
        public final String format(LogRecord logRecord) {
            return "";
        }

        @Override // java.util.logging.Formatter
        public final String getTail(Handler handler) {
            return this.name;
        }

        public final int hashCode() {
            return this.name.hashCode() + TailNameFormatter.class.hashCode();
        }

        public final String toString() {
            return this.name;
        }
    }

    public MailHandler() {
        init(null);
        this.sealed = true;
        checkAccess();
    }

    private boolean allowRestrictedHeaders() {
        return LogManagerProperties.hasLogManager();
    }

    private void appendContentLang(MimePart mimePart, Locale locale) {
        int length;
        String concat;
        try {
            String languageTag = LogManagerProperties.toLanguageTag(locale);
            if (languageTag.length() == 0) {
                return;
            }
            String header = mimePart.getHeader("Content-Language", null);
            if (isEmpty(header)) {
                mimePart.setHeader("Content-Language", languageTag);
            } else if (header.equalsIgnoreCase(languageTag)) {
            } else {
                String concat2 = ",".concat(languageTag);
                int i = 0;
                do {
                    i = header.indexOf(concat2, i);
                    if (i <= -1 || (i = i + concat2.length()) == header.length()) {
                        break;
                    }
                } while (header.charAt(i) != ',');
                if (i >= 0) {
                    return;
                }
                int lastIndexOf = header.lastIndexOf("\r\n\t");
                if (lastIndexOf < 0) {
                    length = header.length() + 20;
                } else {
                    length = (header.length() - lastIndexOf) + 8;
                }
                if (length + concat2.length() > 76) {
                    concat = header.concat("\r\n\t".concat(concat2));
                } else {
                    concat = header.concat(concat2);
                }
                mimePart.setHeader("Content-Language", concat);
            }
        } catch (MessagingException e) {
            reportError(e.getMessage(), e, 5);
        }
    }

    private void appendFileName(Part part, String str) {
        if (str != null) {
            if (str.length() <= 0) {
                return;
            }
            appendFileName0(part, str);
            return;
        }
        reportNullError(5);
    }

    private void appendFileName0(Part part, String str) {
        try {
            String replaceAll = str.replaceAll("[\\x00-\\x1F\\x7F]+", "");
            String fileName = part.getFileName();
            if (fileName != null) {
                replaceAll = fileName.concat(replaceAll);
            }
            part.setFileName(replaceAll);
        } catch (MessagingException e) {
            reportError(e.getMessage(), e, 5);
        }
    }

    private void appendSubject(Message message, String str) {
        if (str != null) {
            if (str.length() <= 0) {
                return;
            }
            appendSubject0(message, str);
            return;
        }
        reportNullError(5);
    }

    private void appendSubject0(Message message, String str) {
        try {
            String replaceAll = str.replaceAll("[\\x00-\\x1F\\x7F]+", "");
            String encodingName = getEncodingName();
            String subject = message.getSubject();
            MimeMessage mimeMessage = (MimeMessage) message;
            if (subject != null) {
                replaceAll = subject.concat(replaceAll);
            }
            mimeMessage.setSubject(replaceAll, MimeUtility.mimeCharset(encodingName));
        } catch (MessagingException e) {
            reportError(e.getMessage(), e, 5);
        }
    }

    private static String atIndexMsg(int i) {
        return "At index: " + i + '.';
    }

    private static MessagingException attach(MessagingException messagingException, Exception exc) {
        if (exc != null && !messagingException.setNextException(exc) && (exc instanceof MessagingException)) {
            MessagingException messagingException2 = (MessagingException) exc;
            if (messagingException2.setNextException(messagingException)) {
                return messagingException2;
            }
        }
        return messagingException;
    }

    private static RuntimeException attachmentMismatch(String str) {
        return new IndexOutOfBoundsException(str);
    }

    private void checkAccess() {
        if (this.sealed) {
            LogManagerProperties.checkLogManagerAccess();
        }
    }

    private String contentWithEncoding(String str, String str2) {
        try {
            ContentType contentType = new ContentType(str);
            contentType.setParameter(HttpAuthHeader.Parameters.Charset, MimeUtility.mimeCharset(str2));
            String contentType2 = contentType.toString();
            return !isEmpty(contentType2) ? contentType2 : str;
        } catch (MessagingException e) {
            reportError(str, e, 5);
            return str;
        }
    }

    private static <T> T[] copyOf(T[] tArr, int i) {
        return (T[]) copyOf(tArr, i, tArr.getClass());
    }

    private MimeBodyPart createBodyPart() throws MessagingException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDisposition(Part.INLINE);
        mimeBodyPart.setDescription(descriptionFrom(getFormatter(), getFilter(), this.subjectFormatter));
        setAcceptLang(mimeBodyPart);
        return mimeBodyPart;
    }

    private ErrorManager defaultErrorManager() {
        ErrorManager errorManager;
        try {
            errorManager = super.getErrorManager();
        } catch (RuntimeException unused) {
            errorManager = null;
        }
        return errorManager == null ? new ErrorManager() : errorManager;
    }

    private String descriptionFrom(Comparator<?> comparator, Level level, Filter filter) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sorted using ");
        outline107.append(comparator == null ? "no comparator" : comparator.getClass().getName());
        outline107.append(", pushed when ");
        outline107.append(level.getName());
        outline107.append(", and ");
        return GeneratedOutlineSupport1.outline89(outline107, filter == null ? "no push filter" : filter.getClass().getName(), '.');
    }

    private static Filter[] emptyFilterArray() {
        return EMPTY_FILTERS;
    }

    private static Formatter[] emptyFormatterArray() {
        return EMPTY_FORMATTERS;
    }

    private void envelopeFor(Message message, boolean z) {
        setAcceptLang(message);
        setFrom(message);
        if (!setRecipient(message, "mail.to", Message.RecipientType.TO)) {
            setDefaultRecipient(message, Message.RecipientType.TO);
        }
        setRecipient(message, "mail.cc", Message.RecipientType.CC);
        setRecipient(message, "mail.bcc", Message.RecipientType.BCC);
        setReplyTo(message);
        setSender(message);
        setMailer(message);
        setAutoSubmitted(message);
        if (z) {
            setPriority(message);
        }
        try {
            message.setSentDate(new Date());
        } catch (MessagingException e) {
            reportError(e.getMessage(), e, 5);
        }
    }

    private boolean fixUpAttachmentFilters() {
        int length = this.attachmentFormatters.length;
        int length2 = this.attachmentFilters.length;
        boolean z = false;
        if (length2 != length) {
            this.attachmentFilters = (Filter[]) copyOf(this.attachmentFilters, length);
            if (length2 != 0) {
                z = true;
            }
            Filter filter = this.filter;
            if (filter != null) {
                while (length2 < length) {
                    this.attachmentFilters[length2] = filter;
                    length2++;
                }
            }
        }
        if (length == 0) {
            this.attachmentFilters = emptyFilterArray();
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001f A[LOOP:0: B:10:0x001f->B:14:0x0036, LOOP_START, PHI: r3 
      PHI: (r3v1 int) = (r3v0 int), (r3v2 int) binds: [B:8:0x0016, B:14:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean fixUpAttachmentNames() {
        /*
            r6 = this;
            java.util.logging.Formatter[] r0 = r6.attachmentFormatters
            int r0 = r0.length
            java.util.logging.Formatter[] r1 = r6.attachmentNames
            int r2 = r1.length
            r3 = 0
            if (r2 == r0) goto L15
            java.lang.Object[] r1 = copyOf(r1, r0)
            java.util.logging.Formatter[] r1 = (java.util.logging.Formatter[]) r1
            r6.attachmentNames = r1
            if (r2 == 0) goto L15
            r1 = 1
            goto L16
        L15:
            r1 = r3
        L16:
            if (r0 != 0) goto L1f
            java.util.logging.Formatter[] r0 = emptyFormatterArray()
            r6.attachmentNames = r0
            goto L39
        L1f:
            if (r3 >= r0) goto L39
            java.util.logging.Formatter[] r2 = r6.attachmentNames
            r4 = r2[r3]
            if (r4 != 0) goto L36
            com.sun.mail.util.logging.MailHandler$TailNameFormatter r4 = new com.sun.mail.util.logging.MailHandler$TailNameFormatter
            java.util.logging.Formatter[] r5 = r6.attachmentFormatters
            r5 = r5[r3]
            java.lang.String r5 = r6.toString(r5)
            r4.<init>(r5)
            r2[r3] = r4
        L36:
            int r3 = r3 + 1
            goto L1f
        L39:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.logging.MailHandler.fixUpAttachmentNames():boolean");
    }

    private void fixUpContent(MimeMessage mimeMessage, String str, Throwable th) {
        MimeBodyPart createBodyPart;
        String descriptionFrom;
        String classId;
        String name;
        try {
            synchronized (this) {
                createBodyPart = createBodyPart();
                descriptionFrom = descriptionFrom(this.comparator, this.pushLevel, this.pushFilter);
                classId = getClassId(this.subjectFormatter);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Formatted using ");
            if (th == null) {
                name = Throwable.class.getName();
            } else {
                name = th.getClass().getName();
            }
            sb.append(name);
            sb.append(", filtered with ");
            sb.append(str);
            sb.append(", and named by ");
            sb.append(classId);
            sb.append('.');
            createBodyPart.setDescription(sb.toString());
            setContent(createBodyPart, toMsgString(th), Constants.TEXT_PLAIN_MEDIA_TYPE);
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(createBodyPart);
            mimeMessage.setContent(mimeMultipart);
            mimeMessage.setDescription(descriptionFrom);
            setAcceptLang(mimeMessage);
            mimeMessage.saveChanges();
        } catch (RuntimeException e) {
            reportError("Unable to create body.", e, 4);
        } catch (MessagingException e2) {
            reportError("Unable to create body.", e2, 4);
        }
    }

    private Session fixUpSession() {
        if (this.mailProps.getProperty("verify") != null) {
            return initSession();
        }
        this.session = null;
        return null;
    }

    private String format(Formatter formatter, LogRecord logRecord) {
        try {
            return formatter.format(logRecord);
        } catch (RuntimeException e) {
            reportError(e.getMessage(), e, 5);
            return "";
        }
    }

    private Object getAndSetContextClassLoader(Object obj) {
        PrivilegedAction getAndSetContext;
        if (obj != GetAndSetContext.NOT_MODIFIED) {
            try {
                if (obj instanceof PrivilegedAction) {
                    getAndSetContext = (PrivilegedAction) obj;
                } else {
                    getAndSetContext = new GetAndSetContext(obj);
                }
                return AccessController.doPrivileged(getAndSetContext);
            } catch (SecurityException unused) {
            }
        }
        return GetAndSetContext.NOT_MODIFIED;
    }

    private String getClassId(Formatter formatter) {
        if (formatter instanceof TailNameFormatter) {
            return String.class.getName();
        }
        return formatter.getClass().getName();
    }

    private String getContentType(String str) {
        String contentType = this.contentTypes.getContentType(str);
        if ("application/octet-stream".equalsIgnoreCase(contentType)) {
            return null;
        }
        return contentType;
    }

    private String getEncodingName() {
        String encoding = getEncoding();
        return encoding == null ? MimeUtility.getDefaultJavaCharset() : encoding;
    }

    private String getLocalHost(Service service) {
        try {
            return LogManagerProperties.getLocalHost(service);
        } catch (Exception e) {
            reportError(service.toString(), e, 4);
            return null;
        } catch (LinkageError | NoSuchMethodException | SecurityException unused) {
            return null;
        }
    }

    private Session getSession(Message message) {
        if (message != null) {
            return new MessageContext(message).getSession();
        }
        throw new NullPointerException();
    }

    private void grow() {
        int length = this.data.length;
        int i = (length >> 1) + length + 1;
        if (i > this.capacity || i < length) {
            i = this.capacity;
        }
        this.data = (LogRecord[]) copyOf(this.data, i);
    }

    private static boolean hasValue(String str) {
        return !isEmpty(str) && !"null".equalsIgnoreCase(str);
    }

    private String head(Formatter formatter) {
        try {
            return formatter.getHead(this);
        } catch (RuntimeException e) {
            reportError(e.getMessage(), e, 5);
            return "";
        }
    }

    private synchronized void init(Properties properties) {
        String name = getClass().getName();
        this.mailProps = new Properties();
        Object andSetContextClassLoader = getAndSetContextClassLoader(MAILHANDLER_LOADER);
        this.contentTypes = FileTypeMap.getDefaultFileTypeMap();
        getAndSetContextClassLoader(andSetContextClassLoader);
        initErrorManager(name);
        initLevel(name);
        initFilter(name);
        initCapacity(name);
        initAuthenticator(name);
        initEncoding(name);
        initFormatter(name);
        initComparator(name);
        initPushLevel(name);
        initPushFilter(name);
        initSubject(name);
        initAttachmentFormaters(name);
        initAttachmentFilters(name);
        initAttachmentNames(name);
        if (properties == null && LogManagerProperties.fromLogManager(name.concat(".verify")) != null) {
            verifySettings(initSession());
        }
        intern();
    }

    private void initAttachmentFilters(String str) {
        String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".attachment.filters"));
        if (!isEmpty(fromLogManager)) {
            String[] split = fromLogManager.split(",");
            Filter[] filterArr = new Filter[split.length];
            for (int i = 0; i < filterArr.length; i++) {
                split[i] = split[i].trim();
                if (!"null".equalsIgnoreCase(split[i])) {
                    try {
                        filterArr[i] = LogManagerProperties.newFilter(split[i]);
                    } catch (SecurityException e) {
                        throw e;
                    } catch (Exception e2) {
                        reportError(e2.getMessage(), e2, 4);
                    }
                }
            }
            this.attachmentFilters = filterArr;
            if (!fixUpAttachmentFilters()) {
                return;
            }
            reportError("Attachment filters.", attachmentMismatch("Length mismatch."), 4);
            return;
        }
        this.attachmentFilters = emptyFilterArray();
        fixUpAttachmentFilters();
    }

    private void initAttachmentFormaters(String str) {
        Formatter[] formatterArr;
        String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".attachment.formatters"));
        if (!isEmpty(fromLogManager)) {
            String[] split = fromLogManager.split(",");
            if (split.length == 0) {
                formatterArr = emptyFormatterArray();
            } else {
                formatterArr = new Formatter[split.length];
            }
            for (int i = 0; i < formatterArr.length; i++) {
                split[i] = split[i].trim();
                if (!"null".equalsIgnoreCase(split[i])) {
                    try {
                        formatterArr[i] = LogManagerProperties.newFormatter(split[i]);
                        if (formatterArr[i] instanceof TailNameFormatter) {
                            reportError("Attachment formatter.", new ClassNotFoundException(formatterArr[i].toString()), 4);
                            formatterArr[i] = new SimpleFormatter();
                        }
                    } catch (SecurityException e) {
                        throw e;
                    } catch (Exception e2) {
                        reportError(e2.getMessage(), e2, 4);
                        formatterArr[i] = new SimpleFormatter();
                    }
                } else {
                    reportError("Attachment formatter.", new NullPointerException(atIndexMsg(i)), 4);
                    formatterArr[i] = new SimpleFormatter();
                }
            }
            this.attachmentFormatters = formatterArr;
            return;
        }
        this.attachmentFormatters = emptyFormatterArray();
    }

    private void initAttachmentNames(String str) {
        String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".attachment.names"));
        if (!isEmpty(fromLogManager)) {
            String[] split = fromLogManager.split(",");
            Formatter[] formatterArr = new Formatter[split.length];
            for (int i = 0; i < formatterArr.length; i++) {
                split[i] = split[i].trim();
                if (!"null".equalsIgnoreCase(split[i])) {
                    try {
                        try {
                            formatterArr[i] = LogManagerProperties.newFormatter(split[i]);
                        } catch (ClassCastException unused) {
                            formatterArr[i] = new TailNameFormatter(split[i]);
                        } catch (ClassNotFoundException unused2) {
                            formatterArr[i] = new TailNameFormatter(split[i]);
                        }
                    } catch (SecurityException e) {
                        throw e;
                    } catch (Exception e2) {
                        reportError(e2.getMessage(), e2, 4);
                    }
                } else {
                    reportError("Attachment names.", new NullPointerException(atIndexMsg(i)), 4);
                }
            }
            this.attachmentNames = formatterArr;
            if (!fixUpAttachmentNames()) {
                return;
            }
            reportError("Attachment names.", attachmentMismatch("Length mismatch."), 4);
            return;
        }
        this.attachmentNames = emptyFormatterArray();
        fixUpAttachmentNames();
    }

    private void initAuthenticator(String str) {
        String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".authenticator"));
        if (hasValue(fromLogManager)) {
            try {
                this.auth = (Authenticator) LogManagerProperties.newObjectFrom(fromLogManager, Authenticator.class);
            } catch (ClassCastException unused) {
                this.auth = new DefaultAuthenticator(fromLogManager);
            } catch (ClassNotFoundException unused2) {
                this.auth = new DefaultAuthenticator(fromLogManager);
            } catch (SecurityException e) {
                throw e;
            } catch (Exception e2) {
                reportError(e2.getMessage(), e2, 4);
            }
        }
    }

    private void initCapacity(String str) {
        try {
            String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".capacity"));
            if (fromLogManager != null) {
                setCapacity0(Integer.parseInt(fromLogManager));
            } else {
                setCapacity0(1000);
            }
        } catch (SecurityException e) {
            throw e;
        } catch (RuntimeException e2) {
            reportError(e2.getMessage(), e2, 4);
        }
        if (this.capacity <= 0) {
            this.capacity = 1000;
        }
        this.data = new LogRecord[1];
    }

    private void initComparator(String str) {
        try {
            String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".comparator"));
            String fromLogManager2 = LogManagerProperties.fromLogManager(str.concat(".comparator.reverse"));
            if (hasValue(fromLogManager)) {
                this.comparator = LogManagerProperties.newComparator(fromLogManager);
                if (!Boolean.parseBoolean(fromLogManager2)) {
                    return;
                }
                this.comparator = LogManagerProperties.reverseOrder(this.comparator);
            } else if (!isEmpty(fromLogManager2)) {
                throw new IllegalArgumentException("No comparator to reverse.");
            }
        } catch (SecurityException e) {
            throw e;
        } catch (Exception e2) {
            reportError(e2.getMessage(), e2, 4);
        }
    }

    private void initEncoding(String str) {
        try {
            String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".encoding"));
            if (fromLogManager == null) {
                return;
            }
            setEncoding0(fromLogManager);
        } catch (UnsupportedEncodingException e) {
            reportError(e.getMessage(), e, 4);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RuntimeException e3) {
            reportError(e3.getMessage(), e3, 4);
        }
    }

    private void initErrorManager(String str) {
        try {
            String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".errorManager"));
            if (fromLogManager == null) {
                return;
            }
            this.errorManager = LogManagerProperties.newErrorManager(fromLogManager);
        } catch (SecurityException e) {
            throw e;
        } catch (Exception e2) {
            reportError(e2.getMessage(), e2, 4);
        }
    }

    private void initFilter(String str) {
        try {
            String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".filter"));
            if (!hasValue(fromLogManager)) {
                return;
            }
            this.filter = LogManagerProperties.newFilter(fromLogManager);
        } catch (SecurityException e) {
            throw e;
        } catch (Exception e2) {
            reportError(e2.getMessage(), e2, 4);
        }
    }

    private void initFormatter(String str) {
        try {
            String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".formatter"));
            if (hasValue(fromLogManager)) {
                Formatter newFormatter = LogManagerProperties.newFormatter(fromLogManager);
                if (!(newFormatter instanceof TailNameFormatter)) {
                    this.formatter = newFormatter;
                } else {
                    this.formatter = new SimpleFormatter();
                }
            } else {
                this.formatter = new SimpleFormatter();
            }
        } catch (SecurityException e) {
            throw e;
        } catch (Exception e2) {
            reportError(e2.getMessage(), e2, 4);
            this.formatter = new SimpleFormatter();
        }
    }

    private void initLevel(String str) {
        try {
            String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".level"));
            if (fromLogManager != null) {
                this.logLevel = Level.parse(fromLogManager);
            } else {
                this.logLevel = Level.WARNING;
            }
        } catch (SecurityException e) {
            throw e;
        } catch (RuntimeException e2) {
            reportError(e2.getMessage(), e2, 4);
            this.logLevel = Level.WARNING;
        }
    }

    private void initPushFilter(String str) {
        try {
            String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".pushFilter"));
            if (!hasValue(fromLogManager)) {
                return;
            }
            this.pushFilter = LogManagerProperties.newFilter(fromLogManager);
        } catch (SecurityException e) {
            throw e;
        } catch (Exception e2) {
            reportError(e2.getMessage(), e2, 4);
        }
    }

    private void initPushLevel(String str) {
        try {
            String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".pushLevel"));
            if (fromLogManager != null) {
                this.pushLevel = Level.parse(fromLogManager);
            }
        } catch (RuntimeException e) {
            reportError(e.getMessage(), e, 4);
        }
        if (this.pushLevel == null) {
            this.pushLevel = Level.OFF;
        }
    }

    private Session initSession() {
        this.session = Session.getInstance(new LogManagerProperties(this.mailProps, MailHandler.class.getName()), this.auth);
        return this.session;
    }

    private void initSubject(String str) {
        String fromLogManager = LogManagerProperties.fromLogManager(str.concat(".subject"));
        if (hasValue(fromLogManager)) {
            try {
                this.subjectFormatter = LogManagerProperties.newFormatter(fromLogManager);
            } catch (ClassCastException unused) {
                this.subjectFormatter = new TailNameFormatter(fromLogManager);
            } catch (ClassNotFoundException unused2) {
                this.subjectFormatter = new TailNameFormatter(fromLogManager);
            } catch (SecurityException e) {
                throw e;
            } catch (Exception e2) {
                this.subjectFormatter = new TailNameFormatter(fromLogManager);
                reportError(e2.getMessage(), e2, 4);
            }
        } else if (fromLogManager != null) {
            this.subjectFormatter = new TailNameFormatter(fromLogManager);
        }
        if (this.subjectFormatter == null) {
            this.subjectFormatter = new TailNameFormatter("");
        }
    }

    private void intern() {
        try {
            Map<Object, Object> hashMap = new HashMap<>();
            try {
                intern(hashMap, this.errorManager);
            } catch (SecurityException e) {
                reportError(e.getMessage(), e, 4);
            }
            try {
                Object obj = this.filter;
                Object intern = intern(hashMap, obj);
                if (intern != obj && (intern instanceof Filter)) {
                    this.filter = (Filter) intern;
                }
                Object obj2 = this.formatter;
                Object intern2 = intern(hashMap, obj2);
                if (intern2 != obj2 && (intern2 instanceof Formatter)) {
                    this.formatter = (Formatter) intern2;
                }
            } catch (SecurityException e2) {
                reportError(e2.getMessage(), e2, 4);
            }
            Object obj3 = this.subjectFormatter;
            Object intern3 = intern(hashMap, obj3);
            if (intern3 != obj3 && (intern3 instanceof Formatter)) {
                this.subjectFormatter = (Formatter) intern3;
            }
            Object obj4 = this.pushFilter;
            Object intern4 = intern(hashMap, obj4);
            if (intern4 != obj4 && (intern4 instanceof Filter)) {
                this.pushFilter = (Filter) intern4;
            }
            for (int i = 0; i < this.attachmentFormatters.length; i++) {
                Object obj5 = this.attachmentFormatters[i];
                Object intern5 = intern(hashMap, obj5);
                if (intern5 != obj5 && (intern5 instanceof Formatter)) {
                    this.attachmentFormatters[i] = (Formatter) intern5;
                }
                Object obj6 = this.attachmentFilters[i];
                Object intern6 = intern(hashMap, obj6);
                if (intern6 != obj6 && (intern6 instanceof Filter)) {
                    this.attachmentFilters[i] = (Filter) intern6;
                }
                Object obj7 = this.attachmentNames[i];
                Object intern7 = intern(hashMap, obj7);
                if (intern7 != obj7 && (intern7 instanceof Formatter)) {
                    this.attachmentNames[i] = (Formatter) intern7;
                }
            }
        } catch (Exception e3) {
            reportError(e3.getMessage(), e3, 4);
        } catch (LinkageError e4) {
            reportError(e4.getMessage(), new InvocationTargetException(e4), 4);
        }
    }

    private boolean isAttachmentLoggable(LogRecord logRecord) {
        Filter[] readOnlyAttachmentFilters;
        for (Filter filter : readOnlyAttachmentFilters()) {
            if (filter == null || filter.isLoggable(logRecord)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private boolean isPushable(LogRecord logRecord) {
        int intValue = getPushLevel().intValue();
        if (intValue == offValue || logRecord.getLevel().intValue() < intValue) {
            return false;
        }
        Filter pushFilter = getPushFilter();
        return pushFilter == null || pushFilter.isLoggable(logRecord);
    }

    private Locale localeFor(LogRecord logRecord) {
        ResourceBundle resourceBundle = logRecord.getResourceBundle();
        if (resourceBundle != null) {
            Locale locale = resourceBundle.getLocale();
            return (locale == null || isEmpty(locale.getLanguage())) ? Locale.getDefault() : locale;
        }
        return null;
    }

    private void publish0(LogRecord logRecord) {
        Message message;
        boolean z;
        synchronized (this) {
            if (this.size == this.data.length && this.size < this.capacity) {
                grow();
            }
            message = null;
            if (this.size < this.data.length) {
                this.data[this.size] = logRecord;
                this.size++;
                z = isPushable(logRecord);
                if (z || this.size >= this.capacity) {
                    message = writeLogRecords(1);
                }
            } else {
                z = false;
            }
        }
        if (message != null) {
            send(message, z, 1);
        }
    }

    private Filter[] readOnlyAttachmentFilters() {
        return this.attachmentFilters;
    }

    private void releaseMutex() {
        MUTEX.remove();
    }

    private void reportFilterError(LogRecord logRecord) {
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Log record ");
        outline107.append(logRecord.getSequenceNumber());
        outline107.append(" was filtered from all message parts.  ");
        outline107.append(head(simpleFormatter));
        outline107.append(format(simpleFormatter, logRecord));
        outline107.append(tail(simpleFormatter, ""));
        String sb = outline107.toString();
        reportError(sb, new IllegalArgumentException(getFilter() + ", " + Arrays.asList(readOnlyAttachmentFilters())), 5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0063, code lost:
        if (r8 != 2) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void reportLinkageError(java.lang.Throwable r7, int r8) {
        /*
            r6 = this;
            if (r7 == 0) goto L79
            java.lang.StackTraceElement[] r0 = r7.getStackTrace()
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 <= r3) goto L62
            r8 = r3
        Lc:
            int r1 = r0.length
            if (r8 >= r1) goto L66
            r1 = r0[r8]
            java.lang.String r4 = r1.getMethodName()
            java.lang.String r5 = "error"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L2a
            java.lang.String r4 = r1.getClassName()
            java.lang.String r5 = "java.util.logging.ErrorManager"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L2a
            goto L67
        L2a:
            java.lang.String r4 = r1.getMethodName()
            java.lang.String r5 = "reportError"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L5f
            java.lang.String r1 = r1.getClassName()
            java.lang.String r4 = "java.util.logging.Handler"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L5f
            int r1 = r8 + (-1)
            r1 = r0[r1]
            java.lang.String r4 = r1.getMethodName()
            java.lang.String r5 = "println"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L67
            java.lang.String r1 = r1.getMethodName()
            java.lang.String r4 = "printStackTrace"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L5f
            goto L67
        L5f:
            int r8 = r8 + 1
            goto Lc
        L62:
            r0 = 2
            if (r8 == r0) goto L66
            goto L67
        L66:
            r2 = r3
        L67:
            if (r2 == 0) goto L78
            boolean r8 = r7 instanceof java.lang.Error
            if (r8 != 0) goto L75
            boolean r8 = r7 instanceof java.lang.RuntimeException
            if (r8 != 0) goto L72
            goto L78
        L72:
            java.lang.RuntimeException r7 = (java.lang.RuntimeException) r7
            throw r7
        L75:
            java.lang.Error r7 = (java.lang.Error) r7
            throw r7
        L78:
            return
        L79:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.logging.MailHandler.reportLinkageError(java.lang.Throwable, int):void");
    }

    private void reportNonDiscriminating(Object obj, Object obj2) {
        reportError("Non discriminating equals implementation.", new IllegalArgumentException(obj.getClass().getName() + " should not be equal to " + obj2.getClass().getName()), 4);
    }

    private void reportNonSymmetric(Object obj, Object obj2) {
        reportError("Non symmetric equals implementation.", new IllegalArgumentException(obj.getClass().getName() + " is not equal to " + obj2.getClass().getName()), 4);
    }

    private void reportNullError(int i) {
        reportError("null", new NullPointerException(), i);
    }

    private void reportUnPublishedError(LogRecord logRecord) {
        String str;
        if (MUTEX_PUBLISH.equals(MUTEX.get())) {
            MUTEX.set(MUTEX_REPORT);
            if (logRecord != null) {
                try {
                    SimpleFormatter simpleFormatter = new SimpleFormatter();
                    str = "Log record " + logRecord.getSequenceNumber() + " was not published. " + head(simpleFormatter) + format(simpleFormatter, logRecord) + tail(simpleFormatter, "");
                } catch (Throwable th) {
                    MUTEX.set(MUTEX_PUBLISH);
                    throw th;
                }
            } else {
                str = null;
            }
            reportError(str, new IllegalStateException("Recursive publish detected by thread " + Thread.currentThread()), 1);
            MUTEX.set(MUTEX_PUBLISH);
        }
    }

    private void reportUnexpectedSend(MimeMessage mimeMessage, String str, Exception exc) {
        Exception messagingException = new MessagingException("An empty message was sent.", exc);
        fixUpContent(mimeMessage, str, messagingException);
        reportError(mimeMessage, messagingException, 4);
    }

    private void reset() {
        int i = this.size;
        LogRecord[] logRecordArr = this.data;
        if (i < logRecordArr.length) {
            Arrays.fill(logRecordArr, 0, i, (Object) null);
        } else {
            Arrays.fill(logRecordArr, (Object) null);
        }
        this.size = 0;
    }

    private void send(Message message, boolean z, int i) {
        try {
            envelopeFor(message, z);
            Object andSetContextClassLoader = getAndSetContextClassLoader(MAILHANDLER_LOADER);
            Transport.send(message);
            getAndSetContextClassLoader(andSetContextClassLoader);
        } catch (RuntimeException e) {
            reportError(message, e, i);
        } catch (Exception e2) {
            reportError(message, e2, i);
        }
    }

    private void setAcceptLang(Part part) {
        try {
            String languageTag = LogManagerProperties.toLanguageTag(Locale.getDefault());
            if (languageTag.length() == 0) {
                return;
            }
            part.setHeader("Accept-Language", languageTag);
        } catch (MessagingException e) {
            reportError(e.getMessage(), e, 5);
        }
    }

    private void setAuthenticator0(Authenticator authenticator) {
        Session fixUpSession;
        checkAccess();
        synchronized (this) {
            if (!this.isWriting) {
                this.auth = authenticator;
                fixUpSession = fixUpSession();
            } else {
                throw new IllegalStateException();
            }
        }
        verifySettings(fixUpSession);
    }

    private void setAutoSubmitted(Message message) {
        if (allowRestrictedHeaders()) {
            try {
                message.setHeader("auto-submitted", "auto-generated");
            } catch (MessagingException e) {
                reportError(e.getMessage(), e, 5);
            }
        }
    }

    private synchronized void setCapacity0(int i) {
        checkAccess();
        if (i > 0) {
            if (!this.isWriting) {
                if (this.capacity < 0) {
                    this.capacity = -i;
                } else {
                    this.capacity = i;
                }
            } else {
                throw new IllegalStateException();
            }
        } else {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
    }

    private void setContent(MimeBodyPart mimeBodyPart, CharSequence charSequence, String str) throws MessagingException {
        String encodingName = getEncodingName();
        if (str != null && !Constants.TEXT_PLAIN_MEDIA_TYPE.equalsIgnoreCase(str)) {
            try {
                mimeBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(charSequence.toString(), contentWithEncoding(str, encodingName))));
                return;
            } catch (IOException e) {
                reportError(e.getMessage(), e, 5);
                mimeBodyPart.setText(charSequence.toString(), encodingName);
                return;
            }
        }
        mimeBodyPart.setText(charSequence.toString(), MimeUtility.mimeCharset(encodingName));
    }

    private void setDefaultFrom(Message message) {
        try {
            message.setFrom();
        } catch (MessagingException e) {
            reportError(e.getMessage(), e, 5);
        }
    }

    private void setDefaultRecipient(Message message, Message.RecipientType recipientType) {
        try {
            InternetAddress localAddress = InternetAddress.getLocalAddress(getSession(message));
            if (localAddress != null) {
                message.setRecipient(recipientType, localAddress);
                return;
            }
            MimeMessage mimeMessage = new MimeMessage(getSession(message));
            mimeMessage.setFrom();
            Address[] from = mimeMessage.getFrom();
            if (from.length > 0) {
                message.setRecipients(recipientType, from);
                return;
            }
            throw new MessagingException("No local address.");
        } catch (RuntimeException e) {
            reportError("Unable to compute a default recipient.", e, 5);
        } catch (MessagingException e2) {
            reportError("Unable to compute a default recipient.", e2, 5);
        }
    }

    private void setEncoding0(String str) throws UnsupportedEncodingException {
        if (str != null) {
            try {
                if (!Charset.isSupported(str)) {
                    throw new UnsupportedEncodingException(str);
                }
            } catch (IllegalCharsetNameException unused) {
                throw new UnsupportedEncodingException(str);
            }
        }
        synchronized (this) {
            this.encoding = str;
        }
    }

    private void setFrom(Message message) {
        String property = getSession(message).getProperty("mail.from");
        if (property != null) {
            try {
                InternetAddress[] parse = InternetAddress.parse(property, false);
                if (parse.length > 0) {
                    if (parse.length == 1) {
                        message.setFrom(parse[0]);
                    } else {
                        message.addFrom(parse);
                    }
                }
                return;
            } catch (MessagingException e) {
                reportError(e.getMessage(), e, 5);
                setDefaultFrom(message);
                return;
            }
        }
        setDefaultFrom(message);
    }

    private void setIncompleteCopy(Message message) {
        try {
            message.setHeader("Incomplete-Copy", "");
        } catch (MessagingException e) {
            reportError(e.getMessage(), e, 5);
        }
    }

    private void setMailProperties0(Properties properties) {
        Session fixUpSession;
        checkAccess();
        Properties properties2 = (Properties) properties.clone();
        synchronized (this) {
            if (!this.isWriting) {
                this.mailProps = properties2;
                fixUpSession = fixUpSession();
            } else {
                throw new IllegalStateException();
            }
        }
        verifySettings(fixUpSession);
    }

    private void setMailer(Message message) {
        String replaceAll;
        String fold;
        try {
            Class<?> cls = getClass();
            if (cls == MailHandler.class) {
                fold = MailHandler.class.getName();
            } else {
                try {
                    replaceAll = MimeUtility.encodeText(cls.getName());
                } catch (UnsupportedEncodingException e) {
                    reportError(e.getMessage(), e, 5);
                    replaceAll = cls.getName().replaceAll("[^\\x00-\\x7F]", "\u001a");
                }
                fold = MimeUtility.fold(10, MailHandler.class.getName() + " using the " + replaceAll + " extension.");
            }
            message.setHeader("X-Mailer", fold);
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private void setPriority(Message message) {
        try {
            message.setHeader("Importance", DCMEndpoint.Priority.HIGH);
            message.setHeader("Priority", "urgent");
            message.setHeader("X-Priority", "2");
        } catch (MessagingException e) {
            reportError(e.getMessage(), e, 5);
        }
    }

    private boolean setRecipient(Message message, String str, Message.RecipientType recipientType) {
        String property = getSession(message).getProperty(str);
        boolean z = property != null;
        if (!isEmpty(property)) {
            try {
                InternetAddress[] parse = InternetAddress.parse(property, false);
                if (parse.length > 0) {
                    message.setRecipients(recipientType, parse);
                }
            } catch (MessagingException e) {
                reportError(e.getMessage(), e, 5);
            }
        }
        return z;
    }

    private void setReplyTo(Message message) {
        String property = getSession(message).getProperty("mail.reply.to");
        if (!isEmpty(property)) {
            try {
                InternetAddress[] parse = InternetAddress.parse(property, false);
                if (parse.length <= 0) {
                    return;
                }
                message.setReplyTo(parse);
            } catch (MessagingException e) {
                reportError(e.getMessage(), e, 5);
            }
        }
    }

    private void setSender(Message message) {
        String property = getSession(message).getProperty("mail.sender");
        if (!isEmpty(property)) {
            try {
                InternetAddress[] parse = InternetAddress.parse(property, false);
                if (parse.length <= 0) {
                    return;
                }
                ((MimeMessage) message).setSender(parse[0]);
                if (parse.length <= 1) {
                    return;
                }
                reportError("Ignoring other senders.", tooManyAddresses(parse, 1), 5);
            } catch (MessagingException e) {
                reportError(e.getMessage(), e, 5);
            }
        }
    }

    private void sort() {
        Comparator<? super LogRecord> comparator = this.comparator;
        if (comparator != null) {
            try {
                if (this.size != 1) {
                    Arrays.sort(this.data, 0, this.size, comparator);
                } else if (comparator.compare(this.data[0], this.data[0]) != 0) {
                    throw new IllegalArgumentException(this.comparator.getClass().getName());
                }
            } catch (RuntimeException e) {
                reportError(e.getMessage(), e, 5);
            }
        }
    }

    private String tail(Formatter formatter, String str) {
        try {
            return formatter.getTail(this);
        } catch (RuntimeException e) {
            reportError(e.getMessage(), e, 5);
            return str;
        }
    }

    private String toMsgString(Throwable th) {
        if (th == null) {
            return "null";
        }
        String encodingName = getEncodingName();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, encodingName));
            printWriter.println(th.getMessage());
            th.printStackTrace(printWriter);
            printWriter.flush();
            printWriter.close();
            return byteArrayOutputStream.toString(encodingName);
        } catch (RuntimeException e) {
            return th.toString() + Chars.SPACE + e.toString();
        } catch (Exception e2) {
            return th.toString() + Chars.SPACE + e2.toString();
        }
    }

    private String toRawString(Message message) throws MessagingException, IOException {
        if (message != null) {
            Object andSetContextClassLoader = getAndSetContextClassLoader(MAILHANDLER_LOADER);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(message.getSize() + 1024, 1024));
                message.writeTo(byteArrayOutputStream);
                return byteArrayOutputStream.toString("US-ASCII");
            } finally {
                getAndSetContextClassLoader(andSetContextClassLoader);
            }
        }
        return null;
    }

    private String toString(Formatter formatter) {
        String obj = formatter.toString();
        return !isEmpty(obj) ? obj : getClassId(formatter);
    }

    private AddressException tooManyAddresses(Address[] addressArr, int i) {
        return new AddressException(Arrays.asList(addressArr).subList(i, addressArr.length).toString());
    }

    private boolean tryMutex() {
        if (MUTEX.get() == null) {
            MUTEX.set(MUTEX_PUBLISH);
            return true;
        }
        return false;
    }

    private static void verifyAddresses(Address[] addressArr) throws AddressException {
        if (addressArr != null) {
            for (Address address : addressArr) {
                if (address instanceof InternetAddress) {
                    ((InternetAddress) address).validate();
                }
            }
        }
    }

    private static InetAddress verifyHost(String str) throws IOException {
        InetAddress byName;
        if (isEmpty(str)) {
            byName = InetAddress.getLocalHost();
        } else {
            byName = InetAddress.getByName(str);
        }
        if (byName.getCanonicalHostName().length() != 0) {
            return byName;
        }
        throw new UnknownHostException();
    }

    private void verifySettings(Session session) {
        if (session != null) {
            Object put = session.getProperties().put("verify", "");
            if (!(put instanceof String)) {
                if (put == null) {
                    return;
                }
                verifySettings0(session, put.getClass().toString());
                return;
            }
            String str = (String) put;
            if (!hasValue(str)) {
                return;
            }
            verifySettings0(session, str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x0277 A[Catch: Exception -> 0x02f6, RuntimeException -> 0x02fe, TryCatch #8 {Exception -> 0x02f6, blocks: (B:28:0x0090, B:30:0x0097, B:31:0x0099, B:36:0x00a4, B:38:0x00a7, B:45:0x00cc, B:47:0x00d4, B:51:0x00df, B:54:0x00e5, B:70:0x00fb, B:72:0x0101, B:75:0x010c, B:77:0x0112, B:79:0x0115, B:80:0x011b, B:82:0x0121, B:84:0x0124, B:86:0x0129, B:99:0x01f9, B:101:0x0201, B:103:0x0209, B:104:0x020d, B:110:0x022a, B:112:0x025f, B:117:0x0269, B:114:0x0264, B:115:0x0267, B:118:0x0274, B:120:0x0277, B:122:0x0286, B:123:0x028c, B:125:0x0296, B:127:0x0299, B:128:0x029c, B:130:0x029f, B:132:0x02a7, B:133:0x02aa, B:134:0x02ca, B:136:0x02cd, B:137:0x02d5, B:138:0x02e1, B:139:0x02e2, B:140:0x02e9, B:107:0x0212, B:109:0x021f, B:64:0x00f1, B:67:0x00f6, B:87:0x0132, B:89:0x01af, B:91:0x01cc, B:93:0x01d4, B:96:0x01e1, B:98:0x01ee, B:39:0x00b3, B:40:0x00bd, B:34:0x009e, B:42:0x00bf, B:44:0x00c9, B:146:0x02f2, B:147:0x02f5), top: B:167:0x0090 }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02e2 A[Catch: Exception -> 0x02f6, RuntimeException -> 0x02fe, TryCatch #8 {Exception -> 0x02f6, blocks: (B:28:0x0090, B:30:0x0097, B:31:0x0099, B:36:0x00a4, B:38:0x00a7, B:45:0x00cc, B:47:0x00d4, B:51:0x00df, B:54:0x00e5, B:70:0x00fb, B:72:0x0101, B:75:0x010c, B:77:0x0112, B:79:0x0115, B:80:0x011b, B:82:0x0121, B:84:0x0124, B:86:0x0129, B:99:0x01f9, B:101:0x0201, B:103:0x0209, B:104:0x020d, B:110:0x022a, B:112:0x025f, B:117:0x0269, B:114:0x0264, B:115:0x0267, B:118:0x0274, B:120:0x0277, B:122:0x0286, B:123:0x028c, B:125:0x0296, B:127:0x0299, B:128:0x029c, B:130:0x029f, B:132:0x02a7, B:133:0x02aa, B:134:0x02ca, B:136:0x02cd, B:137:0x02d5, B:138:0x02e1, B:139:0x02e2, B:140:0x02e9, B:107:0x0212, B:109:0x021f, B:64:0x00f1, B:67:0x00f6, B:87:0x0132, B:89:0x01af, B:91:0x01cc, B:93:0x01d4, B:96:0x01e1, B:98:0x01ee, B:39:0x00b3, B:40:0x00bd, B:34:0x009e, B:42:0x00bf, B:44:0x00c9, B:146:0x02f2, B:147:0x02f5), top: B:167:0x0090 }] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0201 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0101 A[Catch: Exception -> 0x02f6, RuntimeException -> 0x02fe, TryCatch #8 {Exception -> 0x02f6, blocks: (B:28:0x0090, B:30:0x0097, B:31:0x0099, B:36:0x00a4, B:38:0x00a7, B:45:0x00cc, B:47:0x00d4, B:51:0x00df, B:54:0x00e5, B:70:0x00fb, B:72:0x0101, B:75:0x010c, B:77:0x0112, B:79:0x0115, B:80:0x011b, B:82:0x0121, B:84:0x0124, B:86:0x0129, B:99:0x01f9, B:101:0x0201, B:103:0x0209, B:104:0x020d, B:110:0x022a, B:112:0x025f, B:117:0x0269, B:114:0x0264, B:115:0x0267, B:118:0x0274, B:120:0x0277, B:122:0x0286, B:123:0x028c, B:125:0x0296, B:127:0x0299, B:128:0x029c, B:130:0x029f, B:132:0x02a7, B:133:0x02aa, B:134:0x02ca, B:136:0x02cd, B:137:0x02d5, B:138:0x02e1, B:139:0x02e2, B:140:0x02e9, B:107:0x0212, B:109:0x021f, B:64:0x00f1, B:67:0x00f6, B:87:0x0132, B:89:0x01af, B:91:0x01cc, B:93:0x01d4, B:96:0x01e1, B:98:0x01ee, B:39:0x00b3, B:40:0x00bd, B:34:0x009e, B:42:0x00bf, B:44:0x00c9, B:146:0x02f2, B:147:0x02f5), top: B:167:0x0090 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0129 A[Catch: Exception -> 0x02f6, RuntimeException -> 0x02fe, TryCatch #8 {Exception -> 0x02f6, blocks: (B:28:0x0090, B:30:0x0097, B:31:0x0099, B:36:0x00a4, B:38:0x00a7, B:45:0x00cc, B:47:0x00d4, B:51:0x00df, B:54:0x00e5, B:70:0x00fb, B:72:0x0101, B:75:0x010c, B:77:0x0112, B:79:0x0115, B:80:0x011b, B:82:0x0121, B:84:0x0124, B:86:0x0129, B:99:0x01f9, B:101:0x0201, B:103:0x0209, B:104:0x020d, B:110:0x022a, B:112:0x025f, B:117:0x0269, B:114:0x0264, B:115:0x0267, B:118:0x0274, B:120:0x0277, B:122:0x0286, B:123:0x028c, B:125:0x0296, B:127:0x0299, B:128:0x029c, B:130:0x029f, B:132:0x02a7, B:133:0x02aa, B:134:0x02ca, B:136:0x02cd, B:137:0x02d5, B:138:0x02e1, B:139:0x02e2, B:140:0x02e9, B:107:0x0212, B:109:0x021f, B:64:0x00f1, B:67:0x00f6, B:87:0x0132, B:89:0x01af, B:91:0x01cc, B:93:0x01d4, B:96:0x01e1, B:98:0x01ee, B:39:0x00b3, B:40:0x00bd, B:34:0x009e, B:42:0x00bf, B:44:0x00c9, B:146:0x02f2, B:147:0x02f5), top: B:167:0x0090 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void verifySettings0(javax.mail.Session r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 777
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.logging.MailHandler.verifySettings0(javax.mail.Session, java.lang.String):void");
    }

    private Message writeLogRecords(int i) {
        try {
            synchronized (this) {
                if (this.size <= 0 || this.isWriting) {
                    return null;
                }
                this.isWriting = true;
                Message writeLogRecords0 = writeLogRecords0();
                this.isWriting = false;
                if (this.size > 0) {
                    reset();
                }
                return writeLogRecords0;
            }
        } catch (RuntimeException e) {
            reportError(e.getMessage(), e, i);
            return null;
        } catch (Exception e2) {
            reportError(e2.getMessage(), e2, i);
            return null;
        }
    }

    private Message writeLogRecords0() throws Exception {
        int i;
        StringBuilder sb;
        boolean z;
        sort();
        if (this.session == null) {
            initSession();
        }
        MimeMessage mimeMessage = new MimeMessage(this.session);
        mimeMessage.setDescription(descriptionFrom(this.comparator, this.pushLevel, this.pushFilter));
        MimeBodyPart[] mimeBodyPartArr = new MimeBodyPart[this.attachmentFormatters.length];
        CharSequence[] charSequenceArr = new StringBuilder[mimeBodyPartArr.length];
        appendSubject(mimeMessage, head(this.subjectFormatter));
        MimeBodyPart createBodyPart = createBodyPart();
        Formatter formatter = getFormatter();
        Filter filter = getFilter();
        LogRecord logRecord = null;
        StringBuilder sb2 = null;
        Object obj = null;
        String str = null;
        int i2 = 0;
        while (i2 < this.size) {
            LogRecord[] logRecordArr = this.data;
            LogRecord logRecord2 = logRecordArr[i2];
            logRecordArr[i2] = logRecord;
            Locale localeFor = localeFor(logRecord2);
            appendSubject(mimeMessage, format(this.subjectFormatter, logRecord2));
            if (filter == null || filter.isLoggable(logRecord2)) {
                if (sb2 == null) {
                    sb2 = new StringBuilder();
                    String head = head(formatter);
                    sb2.append(head);
                    str = contentTypeOf(head);
                }
                sb2.append(format(formatter, logRecord2));
                if (localeFor != null && !localeFor.equals(obj)) {
                    appendContentLang(createBodyPart, localeFor);
                }
                z = true;
            } else {
                z = false;
            }
            boolean z2 = z;
            for (int i3 = 0; i3 < mimeBodyPartArr.length; i3++) {
                Filter filter2 = this.attachmentFilters[i3];
                if (filter2 == null || filter2.isLoggable(logRecord2)) {
                    if (mimeBodyPartArr[i3] == null) {
                        mimeBodyPartArr[i3] = createBodyPart(i3);
                        charSequenceArr[i3] = new StringBuilder();
                        charSequenceArr[i3].append(head(this.attachmentFormatters[i3]));
                        appendFileName(mimeBodyPartArr[i3], head(this.attachmentNames[i3]));
                    }
                    appendFileName(mimeBodyPartArr[i3], format(this.attachmentNames[i3], logRecord2));
                    charSequenceArr[i3].append(format(this.attachmentFormatters[i3], logRecord2));
                    if (localeFor != null && !localeFor.equals(obj)) {
                        appendContentLang(mimeBodyPartArr[i3], localeFor);
                    }
                    z2 = true;
                }
            }
            if (z2) {
                if (localeFor != null && !localeFor.equals(obj)) {
                    appendContentLang(mimeMessage, localeFor);
                }
            } else {
                reportFilterError(logRecord2);
            }
            i2++;
            obj = localeFor;
            logRecord = null;
        }
        this.size = 0;
        for (int length = mimeBodyPartArr.length - 1; length >= 0; length--) {
            if (mimeBodyPartArr[length] != null) {
                appendFileName(mimeBodyPartArr[length], tail(this.attachmentNames[length], NotificationCompat.CATEGORY_ERROR));
                charSequenceArr[length].append(tail(this.attachmentFormatters[length], ""));
                if (charSequenceArr[length].length() > 0) {
                    String fileName = mimeBodyPartArr[length].getFileName();
                    if (isEmpty(fileName)) {
                        fileName = toString(this.attachmentFormatters[length]);
                        mimeBodyPartArr[length].setFileName(fileName);
                    }
                    setContent(mimeBodyPartArr[length], charSequenceArr[length], getContentType(fileName));
                    sb = null;
                } else {
                    setIncompleteCopy(mimeMessage);
                    sb = null;
                    mimeBodyPartArr[length] = null;
                }
                charSequenceArr[length] = sb;
            }
        }
        if (sb2 != null) {
            sb2.append(tail(formatter, ""));
            i = 0;
        } else {
            i = 0;
            sb2 = new StringBuilder(0);
        }
        appendSubject(mimeMessage, tail(this.subjectFormatter, ""));
        MimeMultipart mimeMultipart = new MimeMultipart();
        String contentType = getContentType(formatter.getClass().getName());
        if (contentType == null) {
            contentType = str;
        }
        setContent(createBodyPart, sb2, contentType);
        mimeMultipart.addBodyPart(createBodyPart);
        while (i < mimeBodyPartArr.length) {
            if (mimeBodyPartArr[i] != null) {
                mimeMultipart.addBodyPart(mimeBodyPartArr[i]);
            }
            i++;
        }
        mimeMessage.setContent(mimeMultipart);
        return mimeMessage;
    }

    @Override // java.util.logging.Handler
    public void close() {
        Message writeLogRecords;
        checkAccess();
        synchronized (this) {
            writeLogRecords = writeLogRecords(3);
            this.logLevel = Level.OFF;
            if (this.capacity > 0) {
                this.capacity = -this.capacity;
            }
            if (this.size == 0 && this.data.length != 1) {
                this.data = new LogRecord[1];
            }
        }
        if (writeLogRecords != null) {
            send(writeLogRecords, false, 3);
        }
    }

    final String contentTypeOf(String str) {
        if (!isEmpty(str)) {
            if (str.length() > 25) {
                str = str.substring(0, 25);
            }
            try {
                return URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(str.getBytes(getEncodingName())));
            } catch (IOException e) {
                reportError(e.getMessage(), e, 5);
                return null;
            }
        }
        return null;
    }

    @Override // java.util.logging.Handler
    public void flush() {
        push(false, 2);
    }

    public final Filter[] getAttachmentFilters() {
        return (Filter[]) readOnlyAttachmentFilters().clone();
    }

    public final Formatter[] getAttachmentFormatters() {
        Formatter[] formatterArr;
        synchronized (this) {
            formatterArr = this.attachmentFormatters;
        }
        return (Formatter[]) formatterArr.clone();
    }

    public final Formatter[] getAttachmentNames() {
        Formatter[] formatterArr;
        synchronized (this) {
            formatterArr = this.attachmentNames;
        }
        return (Formatter[]) formatterArr.clone();
    }

    public final synchronized Authenticator getAuthenticator() {
        checkAccess();
        return this.auth;
    }

    public final synchronized int getCapacity() {
        return Math.abs(this.capacity);
    }

    public final synchronized Comparator<? super LogRecord> getComparator() {
        return this.comparator;
    }

    @Override // java.util.logging.Handler
    public synchronized String getEncoding() {
        return this.encoding;
    }

    @Override // java.util.logging.Handler
    public ErrorManager getErrorManager() {
        checkAccess();
        return this.errorManager;
    }

    @Override // java.util.logging.Handler
    public Filter getFilter() {
        return this.filter;
    }

    @Override // java.util.logging.Handler
    public synchronized Formatter getFormatter() {
        return this.formatter;
    }

    @Override // java.util.logging.Handler
    public Level getLevel() {
        return this.logLevel;
    }

    public final Properties getMailProperties() {
        Properties properties;
        checkAccess();
        synchronized (this) {
            properties = this.mailProps;
        }
        return (Properties) properties.clone();
    }

    public final synchronized Filter getPushFilter() {
        return this.pushFilter;
    }

    public final synchronized Level getPushLevel() {
        return this.pushLevel;
    }

    public final synchronized Formatter getSubject() {
        return this.subjectFormatter;
    }

    @Override // java.util.logging.Handler
    public boolean isLoggable(LogRecord logRecord) {
        int intValue = getLevel().intValue();
        if (logRecord.getLevel().intValue() < intValue || intValue == offValue) {
            return false;
        }
        Filter filter = getFilter();
        if (filter != null && !filter.isLoggable(logRecord)) {
            return isAttachmentLoggable(logRecord);
        }
        return true;
    }

    final boolean isMissingContent(Message message, Throwable th) {
        Throwable th2;
        Throwable cause = th.getCause();
        while (true) {
            Throwable th3 = cause;
            th2 = th;
            th = th3;
            if (th == null) {
                break;
            }
            cause = th.getCause();
        }
        Object andSetContextClassLoader = getAndSetContextClassLoader(MAILHANDLER_LOADER);
        try {
            try {
                try {
                    message.writeTo(new ByteArrayOutputStream(1024));
                } catch (RuntimeException e) {
                    throw e;
                }
            } catch (Exception e2) {
                String message2 = e2.getMessage();
                if (!isEmpty(message2) && e2.getClass() == th2.getClass()) {
                    boolean equals = message2.equals(th2.getMessage());
                    getAndSetContextClassLoader(andSetContextClassLoader);
                    return equals;
                }
            }
            getAndSetContextClassLoader(andSetContextClassLoader);
            return false;
        } catch (Throwable th4) {
            getAndSetContextClassLoader(andSetContextClassLoader);
            throw th4;
        }
    }

    public void postConstruct() {
    }

    public void preDestroy() {
        push(false, 3);
    }

    @Override // java.util.logging.Handler
    public void publish(LogRecord logRecord) {
        if (tryMutex()) {
            try {
                if (isLoggable(logRecord)) {
                    logRecord.getSourceMethodName();
                    publish0(logRecord);
                }
                return;
            } finally {
                releaseMutex();
            }
        }
        reportUnPublishedError(logRecord);
    }

    public void push() {
        push(true, 2);
    }

    @Override // java.util.logging.Handler
    protected void reportError(String str, Exception exc, int i) {
        try {
            if (str != null) {
                this.errorManager.error(Level.SEVERE.getName().concat(RealTimeTextConstants.COLON_SPACE).concat(str), exc, i);
            } else {
                this.errorManager.error(null, exc, i);
            }
        } catch (LinkageError e) {
            reportLinkageError(e, i);
        } catch (RuntimeException e2) {
            reportLinkageError(e2, i);
        }
    }

    public final void setAttachmentFilters(Filter... filterArr) {
        checkAccess();
        Filter[] filterArr2 = (Filter[]) copyOf(filterArr, filterArr.length, Filter[].class);
        synchronized (this) {
            if (this.attachmentFormatters.length == filterArr2.length) {
                if (!this.isWriting) {
                    this.attachmentFilters = filterArr2;
                } else {
                    throw new IllegalStateException();
                }
            } else {
                throw attachmentMismatch(this.attachmentFormatters.length, filterArr2.length);
            }
        }
    }

    public final void setAttachmentFormatters(Formatter... formatterArr) {
        Formatter[] formatterArr2;
        checkAccess();
        if (formatterArr.length == 0) {
            formatterArr2 = emptyFormatterArray();
        } else {
            formatterArr2 = (Formatter[]) copyOf(formatterArr, formatterArr.length, Formatter[].class);
            for (int i = 0; i < formatterArr2.length; i++) {
                if (formatterArr2[i] == null) {
                    throw new NullPointerException(atIndexMsg(i));
                }
            }
        }
        synchronized (this) {
            if (!this.isWriting) {
                this.attachmentFormatters = formatterArr2;
                fixUpAttachmentFilters();
                fixUpAttachmentNames();
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public final void setAttachmentNames(String... strArr) {
        Formatter[] formatterArr;
        checkAccess();
        if (strArr.length == 0) {
            formatterArr = emptyFormatterArray();
        } else {
            formatterArr = new Formatter[strArr.length];
        }
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str != null) {
                if (str.length() > 0) {
                    formatterArr[i] = new TailNameFormatter(str);
                } else {
                    throw new IllegalArgumentException(atIndexMsg(i));
                }
            } else {
                throw new NullPointerException(atIndexMsg(i));
            }
        }
        synchronized (this) {
            if (this.attachmentFormatters.length == strArr.length) {
                if (!this.isWriting) {
                    this.attachmentNames = formatterArr;
                } else {
                    throw new IllegalStateException();
                }
            } else {
                throw attachmentMismatch(this.attachmentFormatters.length, strArr.length);
            }
        }
    }

    public final void setAuthenticator(Authenticator authenticator) {
        setAuthenticator0(authenticator);
    }

    public final synchronized void setComparator(Comparator<? super LogRecord> comparator) {
        checkAccess();
        if (!this.isWriting) {
            this.comparator = comparator;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override // java.util.logging.Handler
    public void setEncoding(String str) throws UnsupportedEncodingException {
        checkAccess();
        setEncoding0(str);
    }

    @Override // java.util.logging.Handler
    public void setErrorManager(ErrorManager errorManager) {
        checkAccess();
        if (errorManager != null) {
            synchronized (this) {
                this.errorManager = errorManager;
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.logging.Handler
    public void setFilter(Filter filter) {
        checkAccess();
        synchronized (this) {
            this.filter = filter;
        }
    }

    @Override // java.util.logging.Handler
    public synchronized void setFormatter(Formatter formatter) throws SecurityException {
        checkAccess();
        if (formatter != null) {
            this.formatter = formatter;
        } else {
            throw new NullPointerException();
        }
    }

    @Override // java.util.logging.Handler
    public void setLevel(Level level) {
        if (level != null) {
            checkAccess();
            synchronized (this) {
                if (this.capacity > 0) {
                    this.logLevel = level;
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    public final void setMailProperties(Properties properties) {
        setMailProperties0(properties);
    }

    public final synchronized void setPushFilter(Filter filter) {
        checkAccess();
        if (!this.isWriting) {
            this.pushFilter = filter;
        } else {
            throw new IllegalStateException();
        }
    }

    public final synchronized void setPushLevel(Level level) {
        checkAccess();
        if (level != null) {
            if (!this.isWriting) {
                this.pushLevel = level;
            } else {
                throw new IllegalStateException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    public final void setSubject(String str) {
        if (str != null) {
            setSubject(new TailNameFormatter(str));
        } else {
            checkAccess();
            throw new NullPointerException();
        }
    }

    private static RuntimeException attachmentMismatch(int i, int i2) {
        return attachmentMismatch("Attachments mismatched, expected " + i + " but given " + i2 + '.');
    }

    private static <T, U> T[] copyOf(U[] uArr, int i, Class<? extends T[]> cls) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance(cls.getComponentType(), i));
        System.arraycopy(uArr, 0, tArr, 0, Math.min(i, uArr.length));
        return tArr;
    }

    private void push(boolean z, int i) {
        if (tryMutex()) {
            try {
                Message writeLogRecords = writeLogRecords(i);
                if (writeLogRecords != null) {
                    send(writeLogRecords, z, i);
                }
                return;
            } finally {
                releaseMutex();
            }
        }
        reportUnPublishedError(null);
    }

    public final void setAuthenticator(char... cArr) {
        if (cArr == null) {
            setAuthenticator0(null);
        } else {
            setAuthenticator0(new DefaultAuthenticator(new String(cArr)));
        }
    }

    private String descriptionFrom(Formatter formatter, Filter filter, Formatter formatter2) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Formatted using ");
        outline107.append(getClassId(formatter));
        outline107.append(", filtered with ");
        outline107.append(filter == null ? "no filter" : filter.getClass().getName());
        outline107.append(", and named by ");
        return GeneratedOutlineSupport1.outline89(outline107, getClassId(formatter2), '.');
    }

    public final void setSubject(Formatter formatter) {
        checkAccess();
        if (formatter != null) {
            synchronized (this) {
                if (!this.isWriting) {
                    this.subjectFormatter = formatter;
                } else {
                    throw new IllegalStateException();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    public MailHandler(int i) {
        init(null);
        this.sealed = true;
        setCapacity0(i);
    }

    private MimeBodyPart createBodyPart(int i) throws MessagingException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDisposition("attachment");
        mimeBodyPart.setDescription(descriptionFrom(this.attachmentFormatters[i], this.attachmentFilters[i], this.attachmentNames[i]));
        setAcceptLang(mimeBodyPart);
        return mimeBodyPart;
    }

    private void reportError(Message message, Exception exc, int i) {
        try {
            this.errorManager.error(toRawString(message), exc, i);
        } catch (RuntimeException e) {
            reportError(toMsgString(e), exc, i);
        } catch (Exception e2) {
            reportError(toMsgString(e2), exc, i);
        } catch (LinkageError e3) {
            reportLinkageError(e3, i);
        }
    }

    public MailHandler(Properties properties) {
        if (properties != null) {
            init(properties);
            this.sealed = true;
            setMailProperties0(properties);
            return;
        }
        throw new NullPointerException();
    }

    public final void setAttachmentNames(Formatter... formatterArr) {
        checkAccess();
        Formatter[] formatterArr2 = (Formatter[]) copyOf(formatterArr, formatterArr.length, Formatter[].class);
        for (int i = 0; i < formatterArr2.length; i++) {
            if (formatterArr2[i] == null) {
                throw new NullPointerException(atIndexMsg(i));
            }
        }
        synchronized (this) {
            if (this.attachmentFormatters.length == formatterArr2.length) {
                if (!this.isWriting) {
                    this.attachmentNames = formatterArr2;
                } else {
                    throw new IllegalStateException();
                }
            } else {
                throw attachmentMismatch(this.attachmentFormatters.length, formatterArr2.length);
            }
        }
    }

    private Object intern(Map<Object, Object> map, Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Object newInstance = obj.getClass().getName().equals(TailNameFormatter.class.getName()) ? obj : obj.getClass().getConstructor(new Class[0]).newInstance(new Object[0]);
        if (newInstance.getClass() != obj.getClass()) {
            return obj;
        }
        Object obj2 = map.get(newInstance);
        if (obj2 == null) {
            boolean equals = newInstance.equals(obj);
            boolean equals2 = obj.equals(newInstance);
            if (!equals || !equals2) {
                if (equals == equals2) {
                    return obj;
                }
                reportNonSymmetric(obj, newInstance);
                return obj;
            }
            Object put = map.put(obj, obj);
            if (put == null) {
                return obj;
            }
            reportNonDiscriminating(newInstance, put);
            Object remove = map.remove(newInstance);
            if (remove == obj) {
                return obj;
            }
            reportNonDiscriminating(newInstance, remove);
            map.clear();
            return obj;
        } else if (obj.getClass() == obj2.getClass()) {
            return obj2;
        } else {
            reportNonDiscriminating(obj, obj2);
            return obj;
        }
    }
}
