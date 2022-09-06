package com.sun.mail.imap;

import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.iap.BadCommandException;
import com.sun.mail.iap.CommandFailedException;
import com.sun.mail.iap.ConnectionException;
import com.sun.mail.iap.Protocol;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.iap.ResponseHandler;
import com.sun.mail.imap.protocol.IMAPProtocol;
import com.sun.mail.imap.protocol.ListInfo;
import com.sun.mail.imap.protocol.Namespaces;
import com.sun.mail.util.MailConnectException;
import com.sun.mail.util.MailLogger;
import com.sun.mail.util.PropUtil;
import com.sun.mail.util.SocketConnectException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Quota;
import javax.mail.QuotaAwareStore;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.StoreClosedException;
import javax.mail.URLName;
import kotlin.jvm.internal.CharCompanionObject;
/* loaded from: classes3.dex */
public class IMAPStore extends Store implements QuotaAwareStore, ResponseHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String ID_ADDRESS = "address";
    public static final String ID_ARGUMENTS = "arguments";
    public static final String ID_COMMAND = "command";
    public static final String ID_DATE = "date";
    public static final String ID_ENVIRONMENT = "environment";
    public static final String ID_NAME = "name";
    public static final String ID_OS = "os";
    public static final String ID_OS_VERSION = "os-version";
    public static final String ID_SUPPORT_URL = "support-url";
    public static final String ID_VENDOR = "vendor";
    public static final String ID_VERSION = "version";
    public static final int RESPONSE = 1000;
    private final int appendBufferSize;
    protected String authorizationID;
    private final int blksize;
    private boolean closeFoldersOnStoreFailure;
    private volatile boolean connectionFailed;
    private final Object connectionFailedLock;
    private boolean debugpassword;
    private boolean debugusername;
    protected final int defaultPort;
    private boolean disableAuthLogin;
    private boolean disableAuthNtlm;
    private boolean disableAuthPlain;
    private boolean enableImapEvents;
    private boolean enableResponseEvents;
    private boolean enableSASL;
    private boolean enableStartTLS;
    private volatile Constructor folderConstructor;
    private volatile Constructor folderConstructorLI;
    private volatile boolean forceClose;
    private boolean forcePasswordRefresh;
    private String guid;
    protected String host;
    private boolean ignoreSize;
    protected final boolean isSSL;
    protected MailLogger logger;
    private boolean messageCacheDebug;
    private final int minIdleTime;
    protected final String name;
    private Namespaces namespaces;
    private ResponseHandler nonStoreResponseHandler;
    protected String password;
    private boolean peek;
    private final ConnectionPool pool;
    private volatile int port;
    protected String proxyAuthUser;
    private boolean requireStartTLS;
    private String[] saslMechanisms;
    protected String saslRealm;
    private final int statusCacheTimeout;
    private boolean throwSearchException;
    protected String user;
    private boolean usingSSL;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ConnectionPool {
        private static final int ABORTING = 2;
        private static final int IDLE = 1;
        private static final int RUNNING = 0;
        private final long clientTimeoutInterval;
        private Vector folders;
        private IMAPProtocol idleProtocol;
        private final MailLogger logger;
        private final int poolSize;
        private final long pruningInterval;
        private final boolean separateStoreConnection;
        private final long serverTimeoutInterval;
        private Vector authenticatedConnections = new Vector();
        private boolean storeConnectionInUse = false;
        private int idleState = 0;
        private long lastTimePruned = System.currentTimeMillis();

        ConnectionPool(String str, MailLogger mailLogger, Session session) {
            this.logger = mailLogger.getSubLogger("connectionpool", "DEBUG IMAP CP", GeneratedOutlineSupport1.outline191("mail.", str, ".connectionpool.debug", session, false));
            int intSessionProperty = PropUtil.getIntSessionProperty(session, "mail." + str + ".connectionpoolsize", -1);
            if (intSessionProperty > 0) {
                this.poolSize = intSessionProperty;
                if (this.logger.isLoggable(Level.CONFIG)) {
                    MailLogger mailLogger2 = this.logger;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mail.imap.connectionpoolsize: ");
                    outline107.append(this.poolSize);
                    mailLogger2.config(outline107.toString());
                }
            } else {
                this.poolSize = 1;
            }
            int intSessionProperty2 = PropUtil.getIntSessionProperty(session, "mail." + str + ".connectionpooltimeout", -1);
            if (intSessionProperty2 > 0) {
                this.clientTimeoutInterval = intSessionProperty2;
                if (this.logger.isLoggable(Level.CONFIG)) {
                    MailLogger mailLogger3 = this.logger;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("mail.imap.connectionpooltimeout: ");
                    outline1072.append(this.clientTimeoutInterval);
                    mailLogger3.config(outline1072.toString());
                }
            } else {
                this.clientTimeoutInterval = 45000L;
            }
            int intSessionProperty3 = PropUtil.getIntSessionProperty(session, "mail." + str + ".servertimeout", -1);
            if (intSessionProperty3 > 0) {
                this.serverTimeoutInterval = intSessionProperty3;
                if (this.logger.isLoggable(Level.CONFIG)) {
                    MailLogger mailLogger4 = this.logger;
                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("mail.imap.servertimeout: ");
                    outline1073.append(this.serverTimeoutInterval);
                    mailLogger4.config(outline1073.toString());
                }
            } else {
                this.serverTimeoutInterval = 1800000L;
            }
            int intSessionProperty4 = PropUtil.getIntSessionProperty(session, "mail." + str + ".pruninginterval", -1);
            if (intSessionProperty4 > 0) {
                this.pruningInterval = intSessionProperty4;
                if (this.logger.isLoggable(Level.CONFIG)) {
                    MailLogger mailLogger5 = this.logger;
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("mail.imap.pruninginterval: ");
                    outline1074.append(this.pruningInterval);
                    mailLogger5.config(outline1074.toString());
                }
            } else {
                this.pruningInterval = 60000L;
            }
            this.separateStoreConnection = GeneratedOutlineSupport1.outline191("mail.", str, ".separatestoreconnection", session, false);
            if (this.separateStoreConnection) {
                this.logger.config("dedicate a store connection");
            }
        }
    }

    public IMAPStore(Session session, URLName uRLName) {
        this(session, uRLName, "imap", false);
    }

    private void checkConnected() {
        if (super.isConnected()) {
            return;
        }
        throw new IllegalStateException("Not connected");
    }

    private synchronized void cleanup() {
        boolean z;
        boolean z2;
        if (!super.isConnected()) {
            this.logger.fine("IMAPStore cleanup, not connected");
            return;
        }
        synchronized (this.connectionFailedLock) {
            z = this.forceClose;
            this.forceClose = false;
            this.connectionFailed = false;
        }
        if (this.logger.isLoggable(Level.FINE)) {
            this.logger.fine("IMAPStore cleanup, force " + z);
        }
        if (!z || this.closeFoldersOnStoreFailure) {
            Vector vector = null;
            while (true) {
                synchronized (this.pool) {
                    if (this.pool.folders != null) {
                        vector = this.pool.folders;
                        this.pool.folders = null;
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                }
                if (z2) {
                    break;
                }
                int size = vector.size();
                for (int i = 0; i < size; i++) {
                    IMAPFolder iMAPFolder = (IMAPFolder) vector.elementAt(i);
                    if (z) {
                        try {
                            this.logger.fine("force folder to close");
                            iMAPFolder.forceClose();
                        } catch (IllegalStateException | MessagingException unused) {
                        }
                    } else {
                        this.logger.fine("close folder");
                        iMAPFolder.close(false);
                    }
                }
            }
        }
        synchronized (this.pool) {
            emptyConnectionPool(z);
        }
        try {
            super.close();
        } catch (MessagingException unused2) {
        }
        this.logger.fine("IMAPStore cleanup done");
    }

    private void emptyConnectionPool(boolean z) {
        synchronized (this.pool) {
            for (int size = this.pool.authenticatedConnections.size() - 1; size >= 0; size--) {
                try {
                    IMAPProtocol iMAPProtocol = (IMAPProtocol) this.pool.authenticatedConnections.elementAt(size);
                    iMAPProtocol.removeResponseHandler(this);
                    if (z) {
                        iMAPProtocol.disconnect();
                    } else {
                        iMAPProtocol.logout();
                    }
                } catch (ProtocolException unused) {
                }
            }
            this.pool.authenticatedConnections.removeAllElements();
        }
        this.pool.logger.fine("removed all authenticated connections from pool");
    }

    private synchronized Namespaces getNamespaces() throws MessagingException {
        checkConnected();
        IMAPProtocol iMAPProtocol = null;
        if (this.namespaces == null) {
            try {
                iMAPProtocol = getStoreProtocol();
                this.namespaces = iMAPProtocol.namespace();
            } catch (BadCommandException unused) {
            } catch (ConnectionException e) {
                throw new StoreClosedException(this, e.getMessage());
            } catch (ProtocolException e2) {
                throw new MessagingException(e2.getMessage(), e2);
            }
            releaseStoreProtocol(iMAPProtocol);
        }
        return this.namespaces;
    }

    private IMAPProtocol getStoreProtocol() throws ProtocolException {
        IMAPProtocol iMAPProtocol = null;
        while (iMAPProtocol == null) {
            synchronized (this.pool) {
                waitIfIdle();
                if (this.pool.authenticatedConnections.isEmpty()) {
                    this.pool.logger.fine("getStoreProtocol() - no connections in the pool, creating a new one");
                    try {
                        if (this.forcePasswordRefresh) {
                            refreshPassword();
                        }
                        iMAPProtocol = newIMAPProtocol(this.host, this.port);
                        login(iMAPProtocol, this.user, this.password);
                    } catch (Exception unused) {
                        if (iMAPProtocol != null) {
                            try {
                                iMAPProtocol.logout();
                            } catch (Exception unused2) {
                            }
                        }
                        iMAPProtocol = null;
                    }
                    if (iMAPProtocol != null) {
                        iMAPProtocol.addResponseHandler(this);
                        this.pool.authenticatedConnections.addElement(iMAPProtocol);
                    } else {
                        throw new ConnectionException("failed to create new store connection");
                    }
                } else {
                    if (this.pool.logger.isLoggable(Level.FINE)) {
                        this.pool.logger.fine("getStoreProtocol() - connection available -- size: " + this.pool.authenticatedConnections.size());
                    }
                    iMAPProtocol = (IMAPProtocol) this.pool.authenticatedConnections.firstElement();
                    if (this.proxyAuthUser != null && !this.proxyAuthUser.equals(iMAPProtocol.getProxyAuthUser()) && iMAPProtocol.hasCapability("X-UNAUTHENTICATE")) {
                        iMAPProtocol.unauthenticate();
                        login(iMAPProtocol, this.user, this.password);
                    }
                }
                if (!this.pool.storeConnectionInUse) {
                    this.pool.storeConnectionInUse = true;
                    this.pool.logger.fine("getStoreProtocol() -- storeConnectionInUse");
                } else {
                    try {
                        this.pool.wait();
                        iMAPProtocol = null;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new ProtocolException("Interrupted getStoreProtocol", e);
                    }
                }
                timeoutConnections();
            }
        }
        return iMAPProtocol;
    }

    private void login(IMAPProtocol iMAPProtocol, String str, String str2) throws ProtocolException {
        if ((this.enableStartTLS || this.requireStartTLS) && !iMAPProtocol.isSSL()) {
            if (iMAPProtocol.hasCapability("STARTTLS")) {
                iMAPProtocol.startTLS();
                iMAPProtocol.capability();
            } else if (this.requireStartTLS) {
                this.logger.fine("STARTTLS required but not supported by server");
                throw new ProtocolException("STARTTLS required but not supported by server");
            }
        }
        if (iMAPProtocol.isAuthenticated()) {
            return;
        }
        preLogin(iMAPProtocol);
        if (this.guid != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("GUID", this.guid);
            iMAPProtocol.id(hashMap);
        }
        iMAPProtocol.getCapabilities().put("__PRELOGIN__", "");
        String str3 = this.authorizationID;
        if (str3 == null && (str3 = this.proxyAuthUser) == null) {
            str3 = null;
        }
        if (this.enableSASL) {
            try {
                iMAPProtocol.sasllogin(this.saslMechanisms, this.saslRealm, str3, str, str2);
                if (!iMAPProtocol.isAuthenticated()) {
                    throw new CommandFailedException("SASL authentication failed");
                }
            } catch (UnsupportedOperationException unused) {
            }
        }
        if (!iMAPProtocol.isAuthenticated()) {
            if (iMAPProtocol.hasCapability("AUTH=PLAIN") && !this.disableAuthPlain) {
                iMAPProtocol.authplain(str3, str, str2);
            } else if ((iMAPProtocol.hasCapability("AUTH-LOGIN") || iMAPProtocol.hasCapability("AUTH=LOGIN")) && !this.disableAuthLogin) {
                iMAPProtocol.authlogin(str, str2);
            } else if (iMAPProtocol.hasCapability("AUTH=NTLM") && !this.disableAuthNtlm) {
                iMAPProtocol.authntlm(str3, str, str2);
            } else if (!iMAPProtocol.hasCapability("LOGINDISABLED")) {
                iMAPProtocol.login(str, str2);
            } else {
                throw new ProtocolException("No login methods supported!");
            }
        }
        String str4 = this.proxyAuthUser;
        if (str4 != null) {
            iMAPProtocol.proxyauth(str4);
        }
        if (!iMAPProtocol.hasCapability("__PRELOGIN__")) {
            return;
        }
        try {
            iMAPProtocol.capability();
        } catch (ConnectionException e) {
            throw e;
        } catch (ProtocolException unused2) {
        }
    }

    private Folder[] namespaceToFolders(Namespaces.Namespace[] namespaceArr, String str) {
        Folder[] folderArr = new Folder[namespaceArr.length];
        for (int i = 0; i < folderArr.length; i++) {
            String str2 = namespaceArr[i].prefix;
            if (str == null) {
                int length = str2.length();
                if (length > 0) {
                    int i2 = length - 1;
                    if (str2.charAt(i2) == namespaceArr[i].delimiter) {
                        str2 = str2.substring(0, i2);
                    }
                }
            } else {
                str2 = GeneratedOutlineSupport1.outline72(str2, str);
            }
            folderArr[i] = newIMAPFolder(str2, namespaceArr[i].delimiter, Boolean.valueOf(str == null));
        }
        return folderArr;
    }

    private void refreshPassword() {
        InetAddress inetAddress;
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("refresh password, user: ");
            outline107.append(traceUser(this.user));
            mailLogger.fine(outline107.toString());
        }
        try {
            inetAddress = InetAddress.getByName(this.host);
        } catch (UnknownHostException unused) {
            inetAddress = null;
        }
        PasswordAuthentication requestPasswordAuthentication = this.session.requestPasswordAuthentication(inetAddress, this.port, this.name, null, this.user);
        if (requestPasswordAuthentication != null) {
            this.user = requestPasswordAuthentication.getUserName();
            this.password = requestPasswordAuthentication.getPassword();
        }
    }

    private void releaseStoreProtocol(IMAPProtocol iMAPProtocol) {
        boolean z;
        if (iMAPProtocol == null) {
            cleanup();
            return;
        }
        synchronized (this.connectionFailedLock) {
            z = this.connectionFailed;
            this.connectionFailed = false;
        }
        synchronized (this.pool) {
            this.pool.storeConnectionInUse = false;
            this.pool.notifyAll();
            this.pool.logger.fine("releaseStoreProtocol()");
            timeoutConnections();
        }
        if (!z) {
            return;
        }
        cleanup();
    }

    private void timeoutConnections() {
        synchronized (this.pool) {
            if (System.currentTimeMillis() - this.pool.lastTimePruned > this.pool.pruningInterval && this.pool.authenticatedConnections.size() > 1) {
                if (this.pool.logger.isLoggable(Level.FINE)) {
                    this.pool.logger.fine("checking for connections to prune: " + (System.currentTimeMillis() - this.pool.lastTimePruned));
                    this.pool.logger.fine("clientTimeoutInterval: " + this.pool.clientTimeoutInterval);
                }
                for (int size = this.pool.authenticatedConnections.size() - 1; size > 0; size--) {
                    IMAPProtocol iMAPProtocol = (IMAPProtocol) this.pool.authenticatedConnections.elementAt(size);
                    if (this.pool.logger.isLoggable(Level.FINE)) {
                        this.pool.logger.fine("protocol last used: " + (System.currentTimeMillis() - iMAPProtocol.getTimestamp()));
                    }
                    if (System.currentTimeMillis() - iMAPProtocol.getTimestamp() > this.pool.clientTimeoutInterval) {
                        this.pool.logger.fine("authenticated connection timed out, logging out the connection");
                        iMAPProtocol.removeResponseHandler(this);
                        this.pool.authenticatedConnections.removeElementAt(size);
                        try {
                            iMAPProtocol.logout();
                        } catch (ProtocolException unused) {
                        }
                    }
                }
                this.pool.lastTimePruned = System.currentTimeMillis();
            }
        }
    }

    private String tracePassword(String str) {
        return this.debugpassword ? str : str == null ? DefaultRecordChecker.Regex.EMPTY : "<non-null>";
    }

    private String traceUser(String str) {
        return this.debugusername ? str : "<user name suppressed>";
    }

    private void waitIfIdle() throws ProtocolException {
        while (this.pool.idleState != 0) {
            if (this.pool.idleState == 1) {
                this.pool.idleProtocol.idleAbort();
                this.pool.idleState = 2;
            }
            try {
                this.pool.wait();
            } catch (InterruptedException e) {
                throw new ProtocolException("Interrupted waitIfIdle", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean allowReadOnlySelect() {
        Session session = this.session;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("mail.");
        outline107.append(this.name);
        outline107.append(".allowreadonlyselect");
        return PropUtil.getBooleanSessionProperty(session, outline107.toString(), false);
    }

    @Override // javax.mail.Service
    public synchronized void close() throws MessagingException {
        boolean isEmpty;
        if (!super.isConnected()) {
            return;
        }
        try {
            synchronized (this.pool) {
                isEmpty = this.pool.authenticatedConnections.isEmpty();
            }
            if (isEmpty) {
                this.pool.logger.fine("close() - no connections ");
                cleanup();
                releaseStoreProtocol(null);
                return;
            }
            IMAPProtocol storeProtocol = getStoreProtocol();
            synchronized (this.pool) {
                this.pool.authenticatedConnections.removeElement(storeProtocol);
            }
            storeProtocol.logout();
            releaseStoreProtocol(storeProtocol);
        } catch (ProtocolException e) {
            throw new MessagingException(e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Service
    public void finalize() throws Throwable {
        super.finalize();
        close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getAppendBufferSize() {
        return this.appendBufferSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MailLogger getConnectionPoolLogger() {
        return this.pool.logger;
    }

    @Override // javax.mail.Store
    public synchronized Folder getDefaultFolder() throws MessagingException {
        checkConnected();
        return new DefaultFolder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFetchBlockSize() {
        return this.blksize;
    }

    @Override // javax.mail.Store
    public synchronized Folder getFolder(String str) throws MessagingException {
        checkConnected();
        return newIMAPFolder(str, CharCompanionObject.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IMAPProtocol getFolderStoreProtocol() throws ProtocolException {
        IMAPProtocol storeProtocol = getStoreProtocol();
        storeProtocol.removeResponseHandler(this);
        storeProtocol.addResponseHandler(this.nonStoreResponseHandler);
        return storeProtocol;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getMessageCacheDebug() {
        return this.messageCacheDebug;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinIdleTime() {
        return this.minIdleTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getPeek() {
        return this.peek;
    }

    @Override // javax.mail.Store
    public Folder[] getPersonalNamespaces() throws MessagingException {
        Namespaces.Namespace[] namespaceArr;
        Namespaces namespaces = getNamespaces();
        if (namespaces != null && (namespaceArr = namespaces.personal) != null) {
            return namespaceToFolders(namespaceArr, null);
        }
        return super.getPersonalNamespaces();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(11:6|7|(10:16|(1:18)|19|20|(2:47|48)|22|(2:28|29)|38|39|(3:41|(1:43)|44))|56|57|58|(1:60)|61|(1:63)|39|(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x010d, code lost:
        if (r1 != null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x010f, code lost:
        r1.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0112, code lost:
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x013f, code lost:
        throw new javax.mail.MessagingException("connection failure");
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011a A[Catch: all -> 0x0140, TryCatch #2 {, blocks: (B:7:0x0007, B:9:0x0013, B:11:0x0020, B:13:0x0028, B:16:0x0032, B:18:0x003c, B:19:0x005c, B:22:0x0084, B:27:0x00a3, B:29:0x00a7, B:31:0x00b3, B:33:0x00bb, B:36:0x00de, B:38:0x00e1, B:49:0x0115, B:51:0x011a, B:53:0x0122, B:54:0x012c, B:55:0x0135, B:25:0x00a0, B:39:0x00e5, B:40:0x00ec, B:42:0x00f0, B:43:0x00f3, B:57:0x0138, B:58:0x013f, B:46:0x010f), top: B:68:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.sun.mail.imap.protocol.IMAPProtocol getProtocol(com.sun.mail.imap.IMAPFolder r8) throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPStore.getProtocol(com.sun.mail.imap.IMAPFolder):com.sun.mail.imap.protocol.IMAPProtocol");
    }

    public String getProxyAuthUser() {
        return this.proxyAuthUser;
    }

    @Override // javax.mail.QuotaAwareStore
    public synchronized Quota[] getQuota(String str) throws MessagingException {
        Quota[] quotaRoot;
        checkConnected();
        try {
            try {
                try {
                    IMAPProtocol storeProtocol = getStoreProtocol();
                    quotaRoot = storeProtocol.getQuotaRoot(str);
                    releaseStoreProtocol(storeProtocol);
                } catch (BadCommandException e) {
                    throw new MessagingException("QUOTA not supported", e);
                }
            } catch (ConnectionException e2) {
                throw new StoreClosedException(this, e2.getMessage());
            }
        } catch (ProtocolException e3) {
            throw new MessagingException(e3.getMessage(), e3);
        }
        return quotaRoot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session getSession() {
        return this.session;
    }

    @Override // javax.mail.Store
    public Folder[] getSharedNamespaces() throws MessagingException {
        Namespaces.Namespace[] namespaceArr;
        Namespaces namespaces = getNamespaces();
        if (namespaces != null && (namespaceArr = namespaces.shared) != null) {
            return namespaceToFolders(namespaceArr, null);
        }
        return super.getSharedNamespaces();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStatusCacheTimeout() {
        return this.statusCacheTimeout;
    }

    @Override // javax.mail.Store
    public Folder[] getUserNamespaces(String str) throws MessagingException {
        Namespaces.Namespace[] namespaceArr;
        Namespaces namespaces = getNamespaces();
        if (namespaces != null && (namespaceArr = namespaces.otherUsers) != null) {
            return namespaceToFolders(namespaceArr, str);
        }
        return super.getUserNamespaces(str);
    }

    @Override // com.sun.mail.iap.ResponseHandler
    public void handleResponse(Response response) {
        if (response.isOK() || response.isNO() || response.isBAD() || response.isBYE()) {
            handleResponseCode(response);
        }
        if (response.isBYE()) {
            this.logger.fine("IMAPStore connection dead");
            synchronized (this.connectionFailedLock) {
                this.connectionFailed = true;
                if (response.isSynthetic()) {
                    this.forceClose = true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleResponseCode(Response response) {
        if (this.enableResponseEvents) {
            notifyStoreListeners(1000, response.toString());
        }
        String rest = response.getRest();
        boolean z = false;
        if (rest.startsWith("[")) {
            int indexOf = rest.indexOf(93);
            if (indexOf > 0 && rest.substring(0, indexOf + 1).equalsIgnoreCase("[ALERT]")) {
                z = true;
            }
            rest = rest.substring(indexOf + 1).trim();
        }
        if (z) {
            notifyStoreListeners(1, rest);
        } else if (!response.isUnTagged() || rest.length() <= 0) {
        } else {
            notifyStoreListeners(2, rest);
        }
    }

    public synchronized boolean hasCapability(String str) throws MessagingException {
        boolean hasCapability;
        try {
            IMAPProtocol storeProtocol = getStoreProtocol();
            hasCapability = storeProtocol.hasCapability(str);
            releaseStoreProtocol(storeProtocol);
        } catch (ProtocolException e) {
            throw new MessagingException(e.getMessage(), e);
        }
        return hasCapability;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasSeparateStoreConnection() {
        return this.pool.separateStoreConnection;
    }

    public synchronized Map<String, String> id(Map<String, String> map) throws MessagingException {
        Map<String, String> id;
        checkConnected();
        try {
            try {
                try {
                    IMAPProtocol storeProtocol = getStoreProtocol();
                    id = storeProtocol.id(map);
                    releaseStoreProtocol(storeProtocol);
                } catch (BadCommandException e) {
                    throw new MessagingException("ID not supported", e);
                }
            } catch (ConnectionException e2) {
                throw new StoreClosedException(this, e2.getMessage());
            }
        } catch (ProtocolException e3) {
            throw new MessagingException(e3.getMessage(), e3);
        }
        return id;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0052, code lost:
        if (r8.enableImapEvents == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0058, code lost:
        if (r3.isUnTagged() == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005a, code lost:
        notifyStoreListeners(1000, r3.toString());
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00e5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0096 -> B:55:0x0097). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void idle() throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPStore.idle():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean ignoreBodyStructureSize() {
        return this.ignoreSize;
    }

    @Override // javax.mail.Service
    public synchronized boolean isConnected() {
        if (!super.isConnected()) {
            return false;
        }
        IMAPProtocol iMAPProtocol = null;
        try {
            iMAPProtocol = getStoreProtocol();
            iMAPProtocol.noop();
        } catch (ProtocolException unused) {
        }
        releaseStoreProtocol(iMAPProtocol);
        return super.isConnected();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isConnectionPoolFull() {
        boolean z;
        synchronized (this.pool) {
            if (this.pool.logger.isLoggable(Level.FINE)) {
                MailLogger mailLogger = this.pool.logger;
                mailLogger.fine("connection pool current size: " + this.pool.authenticatedConnections.size() + "   pool size: " + this.pool.poolSize);
            }
            z = this.pool.authenticatedConnections.size() >= this.pool.poolSize;
        }
        return z;
    }

    public synchronized boolean isSSL() {
        return this.usingSSL;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.sun.mail.imap.IMAPFolder newIMAPFolder(java.lang.String r5, char r6, java.lang.Boolean r7) {
        /*
            r4 = this;
            java.lang.reflect.Constructor r0 = r4.folderConstructor
            if (r0 == 0) goto L2a
            r0 = 4
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L20
            r1 = 0
            r0[r1] = r5     // Catch: java.lang.Exception -> L20
            r1 = 1
            java.lang.Character r2 = java.lang.Character.valueOf(r6)     // Catch: java.lang.Exception -> L20
            r0[r1] = r2     // Catch: java.lang.Exception -> L20
            r1 = 2
            r0[r1] = r4     // Catch: java.lang.Exception -> L20
            r1 = 3
            r0[r1] = r7     // Catch: java.lang.Exception -> L20
            java.lang.reflect.Constructor r1 = r4.folderConstructor     // Catch: java.lang.Exception -> L20
            java.lang.Object r0 = r1.newInstance(r0)     // Catch: java.lang.Exception -> L20
            com.sun.mail.imap.IMAPFolder r0 = (com.sun.mail.imap.IMAPFolder) r0     // Catch: java.lang.Exception -> L20
            goto L2b
        L20:
            r0 = move-exception
            com.sun.mail.util.MailLogger r1 = r4.logger
            java.util.logging.Level r2 = java.util.logging.Level.FINE
            java.lang.String r3 = "exception creating IMAPFolder class"
            r1.log(r2, r3, r0)
        L2a:
            r0 = 0
        L2b:
            if (r0 != 0) goto L32
            com.sun.mail.imap.IMAPFolder r0 = new com.sun.mail.imap.IMAPFolder
            r0.<init>(r5, r6, r4, r7)
        L32:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPStore.newIMAPFolder(java.lang.String, char, java.lang.Boolean):com.sun.mail.imap.IMAPFolder");
    }

    protected IMAPProtocol newIMAPProtocol(String str, int i) throws IOException, ProtocolException {
        return new IMAPProtocol(this.name, str, i, this.session.getProperties(), this.isSSL, this.logger);
    }

    protected void preLogin(IMAPProtocol iMAPProtocol) throws ProtocolException {
    }

    @Override // javax.mail.Service
    protected synchronized boolean protocolConnect(String str, int i, String str2, String str3) throws MessagingException {
        boolean isEmpty;
        Protocol protocol = null;
        if (str != null && str3 != null && str2 != null) {
            if (i != -1) {
                this.port = i;
            } else {
                Session session = this.session;
                this.port = PropUtil.getIntSessionProperty(session, "mail." + this.name + ".port", this.port);
            }
            if (this.port == -1) {
                this.port = this.defaultPort;
            }
            try {
                try {
                    try {
                        try {
                            synchronized (this.pool) {
                                isEmpty = this.pool.authenticatedConnections.isEmpty();
                            }
                            if (isEmpty) {
                                if (this.logger.isLoggable(Level.FINE)) {
                                    MailLogger mailLogger = this.logger;
                                    mailLogger.fine("trying to connect to host \"" + str + "\", port " + this.port + ", isSSL " + this.isSSL);
                                }
                                IMAPProtocol newIMAPProtocol = newIMAPProtocol(str, this.port);
                                if (this.logger.isLoggable(Level.FINE)) {
                                    MailLogger mailLogger2 = this.logger;
                                    mailLogger2.fine("protocolConnect login, host=" + str + ", user=" + traceUser(str2) + ", password=" + tracePassword(str3));
                                }
                                newIMAPProtocol.addResponseHandler(this.nonStoreResponseHandler);
                                login(newIMAPProtocol, str2, str3);
                                newIMAPProtocol.removeResponseHandler(this.nonStoreResponseHandler);
                                newIMAPProtocol.addResponseHandler(this);
                                this.usingSSL = newIMAPProtocol.isSSL();
                                this.host = str;
                                this.user = str2;
                                this.password = str3;
                                synchronized (this.pool) {
                                    this.pool.authenticatedConnections.addElement(newIMAPProtocol);
                                }
                            }
                            return true;
                        } catch (SocketConnectException e) {
                            throw new MailConnectException(e);
                        }
                    } catch (IOException e2) {
                        throw new MessagingException(e2.getMessage(), e2);
                    }
                } catch (ProtocolException e3) {
                    if (0 != 0) {
                        protocol.disconnect();
                    }
                    throw new MessagingException(e3.getMessage(), e3);
                }
            } catch (CommandFailedException e4) {
                if (0 != 0) {
                    protocol.disconnect();
                }
                throw new AuthenticationFailedException(e4.getResponse().getRest());
            }
        }
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger3 = this.logger;
            mailLogger3.fine("protocolConnect returning false, host=" + str + ", user=" + traceUser(str2) + ", password=" + tracePassword(str3));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseFolderStoreProtocol(IMAPProtocol iMAPProtocol) {
        if (iMAPProtocol == null) {
            return;
        }
        iMAPProtocol.removeResponseHandler(this.nonStoreResponseHandler);
        iMAPProtocol.addResponseHandler(this);
        synchronized (this.pool) {
            this.pool.storeConnectionInUse = false;
            this.pool.notifyAll();
            this.pool.logger.fine("releaseFolderStoreProtocol()");
            timeoutConnections();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseProtocol(IMAPFolder iMAPFolder, IMAPProtocol iMAPProtocol) {
        synchronized (this.pool) {
            if (iMAPProtocol != null) {
                if (!isConnectionPoolFull()) {
                    iMAPProtocol.addResponseHandler(this);
                    this.pool.authenticatedConnections.addElement(iMAPProtocol);
                    if (this.logger.isLoggable(Level.FINE)) {
                        MailLogger mailLogger = this.logger;
                        mailLogger.fine("added an Authenticated connection -- size: " + this.pool.authenticatedConnections.size());
                    }
                } else {
                    this.logger.fine("pool is full, not adding an Authenticated connection");
                    try {
                        iMAPProtocol.logout();
                    } catch (ProtocolException unused) {
                    }
                }
            }
            if (this.pool.folders != null) {
                this.pool.folders.removeElement(iMAPFolder);
            }
            timeoutConnections();
        }
    }

    public synchronized void setPassword(String str) {
        this.password = str;
    }

    public void setProxyAuthUser(String str) {
        this.proxyAuthUser = str;
    }

    @Override // javax.mail.QuotaAwareStore
    public synchronized void setQuota(Quota quota) throws MessagingException {
        checkConnected();
        try {
            try {
                try {
                    IMAPProtocol storeProtocol = getStoreProtocol();
                    storeProtocol.setQuota(quota);
                    releaseStoreProtocol(storeProtocol);
                } catch (BadCommandException e) {
                    throw new MessagingException("QUOTA not supported", e);
                }
            } catch (ConnectionException e2) {
                throw new StoreClosedException(this, e2.getMessage());
            }
        } catch (ProtocolException e3) {
            throw new MessagingException(e3.getMessage(), e3);
        }
    }

    public synchronized void setUsername(String str) {
        this.user = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean throwSearchException() {
        return this.throwSearchException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPStore(Session session, URLName uRLName, String str, boolean z) {
        super(session, uRLName);
        Class<?> cls;
        this.port = -1;
        this.disableAuthLogin = false;
        this.disableAuthPlain = false;
        this.disableAuthNtlm = false;
        this.enableStartTLS = false;
        this.requireStartTLS = false;
        this.usingSSL = false;
        this.enableSASL = false;
        this.forcePasswordRefresh = false;
        this.enableResponseEvents = false;
        this.enableImapEvents = false;
        this.throwSearchException = false;
        this.peek = false;
        this.closeFoldersOnStoreFailure = true;
        this.connectionFailed = false;
        this.forceClose = false;
        this.connectionFailedLock = new Object();
        this.folderConstructor = null;
        this.folderConstructorLI = null;
        this.nonStoreResponseHandler = new ResponseHandler() { // from class: com.sun.mail.imap.IMAPStore.1
            @Override // com.sun.mail.iap.ResponseHandler
            public void handleResponse(Response response) {
                if (response.isOK() || response.isNO() || response.isBAD() || response.isBYE()) {
                    IMAPStore.this.handleResponseCode(response);
                }
                if (response.isBYE()) {
                    IMAPStore.this.logger.fine("IMAPStore non-store connection dead");
                }
            }
        };
        str = uRLName != null ? uRLName.getProtocol() : str;
        this.name = str;
        z = !z ? GeneratedOutlineSupport1.outline191("mail.", str, ".ssl.enable", session, false) : z;
        if (z) {
            this.defaultPort = 993;
        } else {
            this.defaultPort = 143;
        }
        this.isSSL = z;
        this.debug = session.getDebug();
        this.debugusername = PropUtil.getBooleanSessionProperty(session, "mail.debug.auth.username", true);
        this.debugpassword = PropUtil.getBooleanSessionProperty(session, "mail.debug.auth.password", false);
        Class<?> cls2 = getClass();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DEBUG ");
        outline107.append(str.toUpperCase(Locale.ENGLISH));
        this.logger = new MailLogger(cls2, outline107.toString(), session);
        if (!GeneratedOutlineSupport1.outline191("mail.", str, ".partialfetch", session, true)) {
            this.blksize = -1;
            this.logger.config("mail.imap.partialfetch: false");
        } else {
            this.blksize = PropUtil.getIntSessionProperty(session, "mail." + str + ".fetchsize", 16384);
            if (this.logger.isLoggable(Level.CONFIG)) {
                MailLogger mailLogger = this.logger;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("mail.imap.fetchsize: ");
                outline1072.append(this.blksize);
                mailLogger.config(outline1072.toString());
            }
        }
        this.ignoreSize = GeneratedOutlineSupport1.outline191("mail.", str, ".ignorebodystructuresize", session, false);
        if (this.logger.isLoggable(Level.CONFIG)) {
            MailLogger mailLogger2 = this.logger;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("mail.imap.ignorebodystructuresize: ");
            outline1073.append(this.ignoreSize);
            mailLogger2.config(outline1073.toString());
        }
        this.statusCacheTimeout = PropUtil.getIntSessionProperty(session, "mail." + str + ".statuscachetimeout", 1000);
        if (this.logger.isLoggable(Level.CONFIG)) {
            MailLogger mailLogger3 = this.logger;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("mail.imap.statuscachetimeout: ");
            outline1074.append(this.statusCacheTimeout);
            mailLogger3.config(outline1074.toString());
        }
        this.appendBufferSize = PropUtil.getIntSessionProperty(session, "mail." + str + ".appendbuffersize", -1);
        if (this.logger.isLoggable(Level.CONFIG)) {
            MailLogger mailLogger4 = this.logger;
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("mail.imap.appendbuffersize: ");
            outline1075.append(this.appendBufferSize);
            mailLogger4.config(outline1075.toString());
        }
        this.minIdleTime = PropUtil.getIntSessionProperty(session, "mail." + str + ".minidletime", 10);
        if (this.logger.isLoggable(Level.CONFIG)) {
            MailLogger mailLogger5 = this.logger;
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("mail.imap.minidletime: ");
            outline1076.append(this.minIdleTime);
            mailLogger5.config(outline1076.toString());
        }
        String property = session.getProperty("mail." + str + ".proxyauth.user");
        if (property != null) {
            this.proxyAuthUser = property;
            if (this.logger.isLoggable(Level.CONFIG)) {
                MailLogger mailLogger6 = this.logger;
                StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("mail.imap.proxyauth.user: ");
                outline1077.append(this.proxyAuthUser);
                mailLogger6.config(outline1077.toString());
            }
        }
        this.disableAuthLogin = GeneratedOutlineSupport1.outline191("mail.", str, ".auth.login.disable", session, false);
        if (this.disableAuthLogin) {
            this.logger.config("disable AUTH=LOGIN");
        }
        this.disableAuthPlain = GeneratedOutlineSupport1.outline191("mail.", str, ".auth.plain.disable", session, false);
        if (this.disableAuthPlain) {
            this.logger.config("disable AUTH=PLAIN");
        }
        this.disableAuthNtlm = GeneratedOutlineSupport1.outline191("mail.", str, ".auth.ntlm.disable", session, false);
        if (this.disableAuthNtlm) {
            this.logger.config("disable AUTH=NTLM");
        }
        this.enableStartTLS = GeneratedOutlineSupport1.outline191("mail.", str, ".starttls.enable", session, false);
        if (this.enableStartTLS) {
            this.logger.config("enable STARTTLS");
        }
        this.requireStartTLS = GeneratedOutlineSupport1.outline191("mail.", str, ".starttls.required", session, false);
        if (this.requireStartTLS) {
            this.logger.config("require STARTTLS");
        }
        this.enableSASL = GeneratedOutlineSupport1.outline191("mail.", str, ".sasl.enable", session, false);
        if (this.enableSASL) {
            this.logger.config("enable SASL");
        }
        if (this.enableSASL) {
            String property2 = session.getProperty("mail." + str + ".sasl.mechanisms");
            if (property2 != null && property2.length() > 0) {
                if (this.logger.isLoggable(Level.CONFIG)) {
                    MailLogger mailLogger7 = this.logger;
                    mailLogger7.config("SASL mechanisms allowed: " + property2);
                }
                Vector vector = new Vector(5);
                StringTokenizer stringTokenizer = new StringTokenizer(property2, " ,");
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    if (nextToken.length() > 0) {
                        vector.addElement(nextToken);
                    }
                }
                this.saslMechanisms = new String[vector.size()];
                vector.copyInto(this.saslMechanisms);
            }
        }
        String property3 = session.getProperty("mail." + str + ".sasl.authorizationid");
        if (property3 != null) {
            this.authorizationID = property3;
            this.logger.log(Level.CONFIG, "mail.imap.sasl.authorizationid: {0}", this.authorizationID);
        }
        String property4 = session.getProperty("mail." + str + ".sasl.realm");
        if (property4 != null) {
            this.saslRealm = property4;
            this.logger.log(Level.CONFIG, "mail.imap.sasl.realm: {0}", this.saslRealm);
        }
        this.forcePasswordRefresh = GeneratedOutlineSupport1.outline191("mail.", str, ".forcepasswordrefresh", session, false);
        if (this.forcePasswordRefresh) {
            this.logger.config("enable forcePasswordRefresh");
        }
        this.enableResponseEvents = GeneratedOutlineSupport1.outline191("mail.", str, ".enableresponseevents", session, false);
        if (this.enableResponseEvents) {
            this.logger.config("enable IMAP response events");
        }
        this.enableImapEvents = GeneratedOutlineSupport1.outline191("mail.", str, ".enableimapevents", session, false);
        if (this.enableImapEvents) {
            this.logger.config("enable IMAP IDLE events");
        }
        this.messageCacheDebug = GeneratedOutlineSupport1.outline191("mail.", str, ".messagecache.debug", session, false);
        this.guid = session.getProperty("mail." + str + ".yahoo.guid");
        String str2 = this.guid;
        if (str2 != null) {
            this.logger.log(Level.CONFIG, "mail.imap.yahoo.guid: {0}", str2);
        }
        this.throwSearchException = GeneratedOutlineSupport1.outline191("mail.", str, ".throwsearchexception", session, false);
        if (this.throwSearchException) {
            this.logger.config("throw SearchException");
        }
        this.peek = GeneratedOutlineSupport1.outline191("mail.", str, ".peek", session, false);
        if (this.peek) {
            this.logger.config("peek");
        }
        this.closeFoldersOnStoreFailure = GeneratedOutlineSupport1.outline191("mail.", str, ".closefoldersonstorefailure", session, true);
        if (this.closeFoldersOnStoreFailure) {
            this.logger.config("closeFoldersOnStoreFailure");
        }
        String property5 = session.getProperty("mail." + str + ".folder.class");
        if (property5 != null) {
            this.logger.log(Level.CONFIG, "IMAP: folder class: {0}", property5);
            try {
                try {
                    cls = Class.forName(property5, false, getClass().getClassLoader());
                } catch (ClassNotFoundException unused) {
                    cls = Class.forName(property5);
                }
                this.folderConstructor = cls.getConstructor(String.class, Character.TYPE, IMAPStore.class, Boolean.class);
                this.folderConstructorLI = cls.getConstructor(ListInfo.class, IMAPStore.class);
            } catch (Exception e) {
                this.logger.log(Level.CONFIG, "IMAP: failed to load folder class", (Throwable) e);
            }
        }
        this.pool = new ConnectionPool(str, this.logger, session);
    }

    @Override // javax.mail.Store
    public synchronized Folder getFolder(URLName uRLName) throws MessagingException {
        checkConnected();
        return newIMAPFolder(uRLName.getFile(), CharCompanionObject.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IMAPFolder newIMAPFolder(String str, char c) {
        return newIMAPFolder(str, c, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.sun.mail.imap.IMAPFolder newIMAPFolder(com.sun.mail.imap.protocol.ListInfo r5) {
        /*
            r4 = this;
            java.lang.reflect.Constructor r0 = r4.folderConstructorLI
            if (r0 == 0) goto L20
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L16
            r1 = 0
            r0[r1] = r5     // Catch: java.lang.Exception -> L16
            r1 = 1
            r0[r1] = r4     // Catch: java.lang.Exception -> L16
            java.lang.reflect.Constructor r1 = r4.folderConstructorLI     // Catch: java.lang.Exception -> L16
            java.lang.Object r0 = r1.newInstance(r0)     // Catch: java.lang.Exception -> L16
            com.sun.mail.imap.IMAPFolder r0 = (com.sun.mail.imap.IMAPFolder) r0     // Catch: java.lang.Exception -> L16
            goto L21
        L16:
            r0 = move-exception
            com.sun.mail.util.MailLogger r1 = r4.logger
            java.util.logging.Level r2 = java.util.logging.Level.FINE
            java.lang.String r3 = "exception creating IMAPFolder class LI"
            r1.log(r2, r3, r0)
        L20:
            r0 = 0
        L21:
            if (r0 != 0) goto L28
            com.sun.mail.imap.IMAPFolder r0 = new com.sun.mail.imap.IMAPFolder
            r0.<init>(r5, r4)
        L28:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.imap.IMAPStore.newIMAPFolder(com.sun.mail.imap.protocol.ListInfo):com.sun.mail.imap.IMAPFolder");
    }
}
