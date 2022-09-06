package javax.mail;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.MailLogger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import javax.mail.Provider;
/* loaded from: classes3.dex */
public final class Session {
    private static Session defaultSession;
    private final Authenticator authenticator;
    private boolean debug;
    private MailLogger logger;
    private PrintStream out;
    private final Properties props;
    private final EventQueue q;
    private final Hashtable authTable = new Hashtable();
    private final Vector providers = new Vector();
    private final Hashtable providersByProtocol = new Hashtable();
    private final Hashtable providersByClassName = new Hashtable();
    private final Properties addressMap = new Properties();

    private Session(Properties properties, Authenticator authenticator) {
        Class<?> cls;
        this.debug = false;
        this.props = properties;
        this.authenticator = authenticator;
        if (Boolean.valueOf(properties.getProperty("mail.debug")).booleanValue()) {
            this.debug = true;
        }
        initLogger();
        this.logger.log(Level.CONFIG, "JavaMail version {0}", Version.version);
        if (authenticator != null) {
            cls = authenticator.getClass();
        } else {
            cls = Session.class;
        }
        loadProviders(cls);
        loadAddressMap(cls);
        this.q = new EventQueue((Executor) properties.get("mail.event.executor"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.mail.Session.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    return Thread.currentThread().getContextClassLoader();
                } catch (SecurityException unused) {
                    return null;
                }
            }
        });
    }

    public static synchronized Session getDefaultInstance(Properties properties, Authenticator authenticator) {
        Session session;
        synchronized (Session.class) {
            if (defaultSession == null) {
                SecurityManager securityManager = System.getSecurityManager();
                if (securityManager != null) {
                    securityManager.checkSetFactory();
                }
                defaultSession = new Session(properties, authenticator);
            } else if (defaultSession.authenticator != authenticator && (defaultSession.authenticator == null || authenticator == null || defaultSession.authenticator.getClass().getClassLoader() != authenticator.getClass().getClassLoader())) {
                throw new SecurityException("Access to default session denied");
            }
            session = defaultSession;
        }
        return session;
    }

    public static Session getInstance(Properties properties, Authenticator authenticator) {
        return new Session(properties, authenticator);
    }

    private static InputStream getResourceAsStream(final Class cls, final String str) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: javax.mail.Session.4
                @Override // java.security.PrivilegedExceptionAction
                public Object run() throws IOException {
                    return cls.getResourceAsStream(str);
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }

    private static URL[] getResources(final ClassLoader classLoader, final String str) {
        return (URL[]) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.mail.Session.5
            @Override // java.security.PrivilegedAction
            public Object run() {
                URL[] urlArr = null;
                try {
                    Vector vector = new Vector();
                    Enumeration<URL> resources = classLoader.getResources(str);
                    while (resources != null && resources.hasMoreElements()) {
                        URL nextElement = resources.nextElement();
                        if (nextElement != null) {
                            vector.addElement(nextElement);
                        }
                    }
                    if (vector.size() <= 0) {
                        return null;
                    }
                    urlArr = new URL[vector.size()];
                    vector.copyInto(urlArr);
                    return urlArr;
                } catch (IOException | SecurityException unused) {
                    return urlArr;
                }
            }
        });
    }

    private <T extends Service> T getService(Provider provider, URLName uRLName, Class<T> cls) throws NoSuchProviderException {
        ClassLoader classLoader;
        if (provider != null) {
            if (uRLName == null) {
                uRLName = new URLName(provider.getProtocol(), null, -1, null, null, null);
            }
            Authenticator authenticator = this.authenticator;
            if (authenticator != null) {
                classLoader = authenticator.getClass().getClassLoader();
            } else {
                classLoader = Session.class.getClassLoader();
            }
            Class<?> cls2 = null;
            try {
                try {
                    ClassLoader contextClassLoader = getContextClassLoader();
                    if (contextClassLoader != null) {
                        try {
                            cls2 = Class.forName(provider.getClassName(), false, contextClassLoader);
                        } catch (ClassNotFoundException unused) {
                        }
                    }
                    if (cls2 == null || !cls.isAssignableFrom(cls2)) {
                        cls2 = Class.forName(provider.getClassName(), false, classLoader);
                    }
                } catch (Exception e) {
                    this.logger.log(Level.FINE, "Exception loading provider", (Throwable) e);
                    throw new NoSuchProviderException(provider.getProtocol());
                }
            } catch (Exception unused2) {
                cls2 = Class.forName(provider.getClassName());
                if (!cls.isAssignableFrom(cls2)) {
                    throw new ClassCastException(cls.getName() + " " + cls2.getName());
                }
            }
            if (!cls.isAssignableFrom(cls2)) {
                throw new ClassCastException(cls.getName() + " " + cls2.getName());
            }
            try {
                return cls.cast(cls2.getConstructor(Session.class, URLName.class).newInstance(this, uRLName));
            } catch (Exception e2) {
                this.logger.log(Level.FINE, "Exception loading provider", (Throwable) e2);
                throw new NoSuchProviderException(provider.getProtocol());
            }
        }
        throw new NoSuchProviderException("null");
    }

    private static URL[] getSystemResources(final String str) {
        return (URL[]) AccessController.doPrivileged(new PrivilegedAction() { // from class: javax.mail.Session.6
            @Override // java.security.PrivilegedAction
            public Object run() {
                URL[] urlArr = null;
                try {
                    Vector vector = new Vector();
                    Enumeration<URL> systemResources = ClassLoader.getSystemResources(str);
                    while (systemResources != null && systemResources.hasMoreElements()) {
                        URL nextElement = systemResources.nextElement();
                        if (nextElement != null) {
                            vector.addElement(nextElement);
                        }
                    }
                    if (vector.size() <= 0) {
                        return null;
                    }
                    urlArr = new URL[vector.size()];
                    vector.copyInto(urlArr);
                    return urlArr;
                } catch (IOException | SecurityException unused) {
                    return urlArr;
                }
            }
        });
    }

    private final synchronized void initLogger() {
        this.logger = new MailLogger(getClass(), "DEBUG", this.debug, getDebugOut());
    }

    private void loadAddressMap(Class cls) {
        StreamLoader streamLoader = new StreamLoader() { // from class: javax.mail.Session.2
            @Override // javax.mail.StreamLoader
            public void load(InputStream inputStream) throws IOException {
                Session.this.addressMap.load(inputStream);
            }
        };
        loadResource("/META-INF/javamail.default.address.map", cls, streamLoader);
        loadAllResources("META-INF/javamail.address.map", cls, streamLoader);
        try {
            loadFile(System.getProperty("java.home") + File.separator + "lib" + File.separator + "javamail.address.map", streamLoader);
        } catch (SecurityException e) {
            this.logger.log(Level.CONFIG, "can't get java.home", (Throwable) e);
        }
        if (this.addressMap.isEmpty()) {
            this.logger.config("failed to load address map, using defaults");
            this.addressMap.put("rfc822", "smtp");
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:18|19|(3:20|21|(4:23|24|25|26)(1:60))|(2:34|32)|28|29|31|32|15|16) */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x006d, code lost:
        if (r5 == null) goto L54;
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void loadAllResources(java.lang.String r10, java.lang.Class r11, javax.mail.StreamLoader r12) {
        /*
            r9 = this;
            java.lang.String r0 = "Exception loading resource"
            r1 = 0
            java.lang.ClassLoader r2 = getContextClassLoader()     // Catch: java.lang.Exception -> L86
            if (r2 != 0) goto Ld
            java.lang.ClassLoader r2 = r11.getClassLoader()     // Catch: java.lang.Exception -> L86
        Ld:
            if (r2 == 0) goto L14
            java.net.URL[] r2 = getResources(r2, r10)     // Catch: java.lang.Exception -> L86
            goto L18
        L14:
            java.net.URL[] r2 = getSystemResources(r10)     // Catch: java.lang.Exception -> L86
        L18:
            r3 = r1
            if (r2 == 0) goto L90
        L1b:
            int r4 = r2.length     // Catch: java.lang.Exception -> L84
            if (r1 >= r4) goto L90
            r4 = r2[r1]     // Catch: java.lang.Exception -> L84
            r5 = 0
            com.sun.mail.util.MailLogger r6 = r9.logger     // Catch: java.lang.Exception -> L84
            java.util.logging.Level r7 = java.util.logging.Level.CONFIG     // Catch: java.lang.Exception -> L84
            java.lang.String r8 = "URL {0}"
            r6.log(r7, r8, r4)     // Catch: java.lang.Exception -> L84
            r6 = 1
            java.io.InputStream r5 = openStream(r4)     // Catch: java.lang.Throwable -> L53 java.lang.SecurityException -> L56 java.io.IOException -> L63 java.io.FileNotFoundException -> L7c
            if (r5 == 0) goto L47
            r12.load(r5)     // Catch: java.lang.Throwable -> L53 java.lang.SecurityException -> L56 java.io.IOException -> L63 java.io.FileNotFoundException -> L7c
            com.sun.mail.util.MailLogger r3 = r9.logger     // Catch: java.lang.Throwable -> L3f java.lang.SecurityException -> L41 java.io.IOException -> L43 java.io.FileNotFoundException -> L45
            java.util.logging.Level r7 = java.util.logging.Level.CONFIG     // Catch: java.lang.Throwable -> L3f java.lang.SecurityException -> L41 java.io.IOException -> L43 java.io.FileNotFoundException -> L45
            java.lang.String r8 = "successfully loaded resource: {0}"
            r3.log(r7, r8, r4)     // Catch: java.lang.Throwable -> L3f java.lang.SecurityException -> L41 java.io.IOException -> L43 java.io.FileNotFoundException -> L45
            r3 = r6
            goto L50
        L3f:
            r1 = move-exception
            goto L73
        L41:
            r3 = move-exception
            goto L59
        L43:
            r3 = move-exception
            goto L66
        L45:
            r3 = r6
            goto L7c
        L47:
            com.sun.mail.util.MailLogger r6 = r9.logger     // Catch: java.lang.Throwable -> L53 java.lang.SecurityException -> L56 java.io.IOException -> L63 java.io.FileNotFoundException -> L7c
            java.util.logging.Level r7 = java.util.logging.Level.CONFIG     // Catch: java.lang.Throwable -> L53 java.lang.SecurityException -> L56 java.io.IOException -> L63 java.io.FileNotFoundException -> L7c
            java.lang.String r8 = "not loading resource: {0}"
            r6.log(r7, r8, r4)     // Catch: java.lang.Throwable -> L53 java.lang.SecurityException -> L56 java.io.IOException -> L63 java.io.FileNotFoundException -> L7c
        L50:
            if (r5 == 0) goto L81
            goto L7e
        L53:
            r1 = move-exception
            r6 = r3
            goto L73
        L56:
            r4 = move-exception
            r6 = r3
            r3 = r4
        L59:
            com.sun.mail.util.MailLogger r4 = r9.logger     // Catch: java.lang.Throwable -> L3f
            java.util.logging.Level r7 = java.util.logging.Level.CONFIG     // Catch: java.lang.Throwable -> L3f
            r4.log(r7, r0, r3)     // Catch: java.lang.Throwable -> L3f
            if (r5 == 0) goto L71
            goto L6f
        L63:
            r4 = move-exception
            r6 = r3
            r3 = r4
        L66:
            com.sun.mail.util.MailLogger r4 = r9.logger     // Catch: java.lang.Throwable -> L3f
            java.util.logging.Level r7 = java.util.logging.Level.CONFIG     // Catch: java.lang.Throwable -> L3f
            r4.log(r7, r0, r3)     // Catch: java.lang.Throwable -> L3f
            if (r5 == 0) goto L71
        L6f:
            r3 = r6
            goto L7e
        L71:
            r3 = r6
            goto L81
        L73:
            if (r5 == 0) goto L78
            r5.close()     // Catch: java.io.IOException -> L78 java.lang.Exception -> L79
        L78:
            throw r1     // Catch: java.lang.Exception -> L79
        L79:
            r1 = move-exception
            r3 = r6
            goto L89
        L7c:
            if (r5 == 0) goto L81
        L7e:
            r5.close()     // Catch: java.io.IOException -> L81 java.lang.Exception -> L84
        L81:
            int r1 = r1 + 1
            goto L1b
        L84:
            r1 = move-exception
            goto L89
        L86:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L89:
            com.sun.mail.util.MailLogger r2 = r9.logger
            java.util.logging.Level r4 = java.util.logging.Level.CONFIG
            r2.log(r4, r0, r1)
        L90:
            if (r3 != 0) goto L9b
            java.lang.String r0 = "/"
            java.lang.String r10 = com.android.tools.r8.GeneratedOutlineSupport1.outline72(r0, r10)
            r9.loadResource(r10, r11, r12)
        L9b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Session.loadAllResources(java.lang.String, java.lang.Class, javax.mail.StreamLoader):void");
    }

    private void loadFile(String str, StreamLoader streamLoader) {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (FileNotFoundException unused) {
                bufferedInputStream = null;
            } catch (IOException e) {
                e = e;
            } catch (SecurityException e2) {
                e = e2;
            }
            try {
                streamLoader.load(bufferedInputStream);
                this.logger.log(Level.CONFIG, "successfully loaded file: {0}", str);
            } catch (FileNotFoundException unused2) {
                if (bufferedInputStream == null) {
                    return;
                }
                bufferedInputStream.close();
            } catch (IOException e3) {
                e = e3;
                bufferedInputStream2 = bufferedInputStream;
                if (this.logger.isLoggable(Level.CONFIG)) {
                    this.logger.log(Level.CONFIG, "not loading file: " + str, (Throwable) e);
                }
                if (bufferedInputStream2 == null) {
                    return;
                }
                bufferedInputStream2.close();
                return;
            } catch (SecurityException e4) {
                e = e4;
                bufferedInputStream2 = bufferedInputStream;
                if (this.logger.isLoggable(Level.CONFIG)) {
                    this.logger.log(Level.CONFIG, "not loading file: " + str, (Throwable) e);
                }
                if (bufferedInputStream2 == null) {
                    return;
                }
                bufferedInputStream2.close();
                return;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
            bufferedInputStream.close();
        } catch (IOException unused4) {
        }
    }

    private void loadProviders(Class cls) {
        StreamLoader streamLoader = new StreamLoader() { // from class: javax.mail.Session.1
            @Override // javax.mail.StreamLoader
            public void load(InputStream inputStream) throws IOException {
                Session.this.loadProvidersFromStream(inputStream);
            }
        };
        try {
            loadFile(System.getProperty("java.home") + File.separator + "lib" + File.separator + "javamail.providers", streamLoader);
        } catch (SecurityException e) {
            this.logger.log(Level.CONFIG, "can't get java.home", (Throwable) e);
        }
        loadAllResources("META-INF/javamail.providers", cls, streamLoader);
        loadResource("/META-INF/javamail.default.providers", cls, streamLoader);
        if (this.providers.size() == 0) {
            this.logger.config("failed to load any providers, using defaults");
            addProvider(new Provider(Provider.Type.STORE, "imap", "com.sun.mail.imap.IMAPStore", "Oracle", Version.version));
            addProvider(new Provider(Provider.Type.STORE, "imaps", "com.sun.mail.imap.IMAPSSLStore", "Oracle", Version.version));
            addProvider(new Provider(Provider.Type.STORE, "pop3", "com.sun.mail.pop3.POP3Store", "Oracle", Version.version));
            addProvider(new Provider(Provider.Type.STORE, "pop3s", "com.sun.mail.pop3.POP3SSLStore", "Oracle", Version.version));
            addProvider(new Provider(Provider.Type.TRANSPORT, "smtp", "com.sun.mail.smtp.SMTPTransport", "Oracle", Version.version));
            addProvider(new Provider(Provider.Type.TRANSPORT, "smtps", "com.sun.mail.smtp.SMTPSSLTransport", "Oracle", Version.version));
        }
        if (this.logger.isLoggable(Level.CONFIG)) {
            this.logger.config("Tables of loaded providers");
            MailLogger mailLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Providers Listed By Class Name: ");
            outline107.append(this.providersByClassName.toString());
            mailLogger.config(outline107.toString());
            MailLogger mailLogger2 = this.logger;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Providers Listed By Protocol: ");
            outline1072.append(this.providersByProtocol.toString());
            mailLogger2.config(outline1072.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadProvidersFromStream(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            LineInputStream lineInputStream = new LineInputStream(inputStream);
            while (true) {
                String readLine = lineInputStream.readLine();
                if (readLine == null) {
                    return;
                }
                if (!readLine.startsWith(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine, ";");
                    Provider.Type type = null;
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    while (stringTokenizer.hasMoreTokens()) {
                        String trim = stringTokenizer.nextToken().trim();
                        int indexOf = trim.indexOf(Config.Compare.EQUAL_TO);
                        if (trim.startsWith("protocol=")) {
                            str = trim.substring(indexOf + 1);
                        } else if (trim.startsWith("type=")) {
                            String substring = trim.substring(indexOf + 1);
                            if (substring.equalsIgnoreCase(HttpClientModule.ElementsCacheKey.STORE)) {
                                type = Provider.Type.STORE;
                            } else if (substring.equalsIgnoreCase("transport")) {
                                type = Provider.Type.TRANSPORT;
                            }
                        } else if (trim.startsWith("class=")) {
                            str2 = trim.substring(indexOf + 1);
                        } else if (trim.startsWith("vendor=")) {
                            str3 = trim.substring(indexOf + 1);
                        } else if (trim.startsWith("version=")) {
                            str4 = trim.substring(indexOf + 1);
                        }
                    }
                    if (type != null && str != null && str2 != null && str.length() > 0 && str2.length() > 0) {
                        addProvider(new Provider(type, str, str2, str3, str4));
                    } else {
                        this.logger.log(Level.CONFIG, "Bad provider entry: {0}", readLine);
                    }
                }
            }
        }
    }

    private void loadResource(String str, Class cls, StreamLoader streamLoader) {
        InputStream inputStream = null;
        try {
            try {
                inputStream = getResourceAsStream(cls, str);
                if (inputStream != null) {
                    streamLoader.load(inputStream);
                    this.logger.log(Level.CONFIG, "successfully loaded resource: {0}", str);
                }
                if (inputStream == null) {
                    return;
                }
            } catch (IOException e) {
                this.logger.log(Level.CONFIG, "Exception loading resource", (Throwable) e);
                if (inputStream == null) {
                    return;
                }
            } catch (SecurityException e2) {
                this.logger.log(Level.CONFIG, "Exception loading resource", (Throwable) e2);
                if (inputStream == null) {
                    return;
                }
            }
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    private static InputStream openStream(final URL url) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: javax.mail.Session.7
                @Override // java.security.PrivilegedExceptionAction
                public Object run() throws IOException {
                    return url.openStream();
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }

    public synchronized void addProvider(Provider provider) {
        this.providers.addElement(provider);
        this.providersByClassName.put(provider.getClassName(), provider);
        if (!this.providersByProtocol.containsKey(provider.getProtocol())) {
            this.providersByProtocol.put(provider.getProtocol(), provider);
        }
    }

    public synchronized boolean getDebug() {
        return this.debug;
    }

    public synchronized PrintStream getDebugOut() {
        if (this.out == null) {
            return System.out;
        }
        return this.out;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventQueue getEventQueue() {
        return this.q;
    }

    public Folder getFolder(URLName uRLName) throws MessagingException {
        Store store = getStore(uRLName);
        store.connect();
        return store.getFolder(uRLName);
    }

    public PasswordAuthentication getPasswordAuthentication(URLName uRLName) {
        return (PasswordAuthentication) this.authTable.get(uRLName);
    }

    public Properties getProperties() {
        return this.props;
    }

    public String getProperty(String str) {
        return this.props.getProperty(str);
    }

    public synchronized Provider getProvider(String str) throws NoSuchProviderException {
        if (str != null) {
            if (str.length() > 0) {
                Provider provider = null;
                Properties properties = this.props;
                String property = properties.getProperty("mail." + str + ".class");
                if (property != null) {
                    if (this.logger.isLoggable(Level.FINE)) {
                        MailLogger mailLogger = this.logger;
                        mailLogger.fine("mail." + str + ".class property exists and points to " + property);
                    }
                    provider = (Provider) this.providersByClassName.get(property);
                }
                if (provider != null) {
                    return provider;
                }
                Provider provider2 = (Provider) this.providersByProtocol.get(str);
                if (provider2 != null) {
                    if (this.logger.isLoggable(Level.FINE)) {
                        MailLogger mailLogger2 = this.logger;
                        mailLogger2.fine("getProvider() returning " + provider2.toString());
                    }
                    return provider2;
                }
                throw new NoSuchProviderException("No provider for " + str);
            }
        }
        throw new NoSuchProviderException("Invalid protocol: null");
    }

    public synchronized Provider[] getProviders() {
        Provider[] providerArr;
        providerArr = new Provider[this.providers.size()];
        this.providers.copyInto(providerArr);
        return providerArr;
    }

    public Store getStore() throws NoSuchProviderException {
        return getStore(getProperty("mail.store.protocol"));
    }

    public Transport getTransport() throws NoSuchProviderException {
        String property = getProperty("mail.transport.protocol");
        if (property != null) {
            return getTransport(property);
        }
        String str = (String) this.addressMap.get("rfc822");
        if (str != null) {
            return getTransport(str);
        }
        return getTransport("smtp");
    }

    public PasswordAuthentication requestPasswordAuthentication(InetAddress inetAddress, int i, String str, String str2, String str3) {
        Authenticator authenticator = this.authenticator;
        if (authenticator != null) {
            return authenticator.requestPasswordAuthentication(inetAddress, i, str, str2, str3);
        }
        return null;
    }

    public synchronized void setDebug(boolean z) {
        this.debug = z;
        initLogger();
        this.logger.log(Level.CONFIG, "setDebug: JavaMail version {0}", Version.version);
    }

    public synchronized void setDebugOut(PrintStream printStream) {
        this.out = printStream;
        initLogger();
    }

    public void setPasswordAuthentication(URLName uRLName, PasswordAuthentication passwordAuthentication) {
        if (passwordAuthentication == null) {
            this.authTable.remove(uRLName);
        } else {
            this.authTable.put(uRLName, passwordAuthentication);
        }
    }

    public synchronized void setProtocolForAddress(String str, String str2) {
        if (str2 == null) {
            this.addressMap.remove(str);
        } else {
            this.addressMap.put(str, str2);
        }
    }

    public synchronized void setProvider(Provider provider) throws NoSuchProviderException {
        if (provider != null) {
            this.providersByProtocol.put(provider.getProtocol(), provider);
            Properties properties = this.props;
            properties.put("mail." + provider.getProtocol() + ".class", provider.getClassName());
        } else {
            throw new NoSuchProviderException("Can't set null provider");
        }
    }

    public static Session getInstance(Properties properties) {
        return new Session(properties, null);
    }

    public Store getStore(String str) throws NoSuchProviderException {
        return getStore(new URLName(str, null, -1, null, null, null));
    }

    public Store getStore(URLName uRLName) throws NoSuchProviderException {
        return getStore(getProvider(uRLName.getProtocol()), uRLName);
    }

    public Store getStore(Provider provider) throws NoSuchProviderException {
        return getStore(provider, null);
    }

    public Transport getTransport(String str) throws NoSuchProviderException {
        return getTransport(new URLName(str, null, -1, null, null, null));
    }

    private Store getStore(Provider provider, URLName uRLName) throws NoSuchProviderException {
        if (provider != null && provider.getType() == Provider.Type.STORE) {
            return (Store) getService(provider, uRLName, Store.class);
        }
        throw new NoSuchProviderException("invalid provider");
    }

    public Transport getTransport(URLName uRLName) throws NoSuchProviderException {
        return getTransport(getProvider(uRLName.getProtocol()), uRLName);
    }

    public Transport getTransport(Provider provider) throws NoSuchProviderException {
        return getTransport(provider, null);
    }

    public static Session getDefaultInstance(Properties properties) {
        return getDefaultInstance(properties, null);
    }

    public Transport getTransport(Address address) throws NoSuchProviderException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mail.transport.protocol.");
        outline107.append(address.getType());
        String property = getProperty(outline107.toString());
        if (property != null) {
            return getTransport(property);
        }
        String str = (String) this.addressMap.get(address.getType());
        if (str != null) {
            return getTransport(str);
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("No provider for Address type: ");
        outline1072.append(address.getType());
        throw new NoSuchProviderException(outline1072.toString());
    }

    private Transport getTransport(Provider provider, URLName uRLName) throws NoSuchProviderException {
        if (provider != null && provider.getType() == Provider.Type.TRANSPORT) {
            return (Transport) getService(provider, uRLName, Transport.class);
        }
        throw new NoSuchProviderException("invalid provider");
    }
}
