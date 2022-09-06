package javax.mail;

import java.util.Vector;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.MailEvent;
/* loaded from: classes3.dex */
public abstract class Service {
    private boolean connected = false;
    private final Vector connectionListeners = new Vector();
    protected boolean debug;
    private final EventQueue q;
    protected Session session;
    protected URLName url;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Service(javax.mail.Session r10, javax.mail.URLName r11) {
        /*
            r9 = this;
            r9.<init>()
            r0 = 0
            r9.url = r0
            r1 = 0
            r9.debug = r1
            r9.connected = r1
            java.util.Vector r1 = new java.util.Vector
            r1.<init>()
            r9.connectionListeners = r1
            r9.session = r10
            boolean r1 = r10.getDebug()
            r9.debug = r1
            r9.url = r11
            javax.mail.URLName r11 = r9.url
            if (r11 == 0) goto L48
            java.lang.String r0 = r11.getProtocol()
            javax.mail.URLName r11 = r9.url
            java.lang.String r11 = r11.getHost()
            javax.mail.URLName r1 = r9.url
            int r1 = r1.getPort()
            javax.mail.URLName r2 = r9.url
            java.lang.String r2 = r2.getUsername()
            javax.mail.URLName r3 = r9.url
            java.lang.String r3 = r3.getPassword()
            javax.mail.URLName r4 = r9.url
            java.lang.String r4 = r4.getFile()
            r5 = r1
            r8 = r3
            r6 = r4
            r3 = r0
            r0 = r2
            goto L4e
        L48:
            r1 = -1
            r11 = r0
            r3 = r11
            r6 = r3
            r8 = r6
            r5 = r1
        L4e:
            if (r3 == 0) goto L86
            java.lang.String r1 = "mail."
            if (r11 != 0) goto L6c
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r1)
            r11.append(r3)
            java.lang.String r2 = ".host"
            r11.append(r2)
            java.lang.String r11 = r11.toString()
            java.lang.String r11 = r10.getProperty(r11)
        L6c:
            if (r0 != 0) goto L86
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = ".user"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r10.getProperty(r0)
        L86:
            if (r11 != 0) goto L8e
            java.lang.String r11 = "mail.host"
            java.lang.String r11 = r10.getProperty(r11)
        L8e:
            r4 = r11
            if (r0 != 0) goto L97
            java.lang.String r11 = "mail.user"
            java.lang.String r0 = r10.getProperty(r11)
        L97:
            if (r0 != 0) goto La1
            java.lang.String r11 = "user.name"
            java.lang.String r11 = java.lang.System.getProperty(r11)     // Catch: java.lang.SecurityException -> La1
            r7 = r11
            goto La2
        La1:
            r7 = r0
        La2:
            javax.mail.URLName r11 = new javax.mail.URLName
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r9.url = r11
            java.util.Properties r11 = r10.getProperties()
            java.lang.String r0 = "mail.event.scope"
            java.lang.String r1 = "folder"
            java.lang.String r11 = r11.getProperty(r0, r1)
            java.util.Properties r0 = r10.getProperties()
            java.lang.String r1 = "mail.event.executor"
            java.lang.Object r0 = r0.get(r1)
            java.util.concurrent.Executor r0 = (java.util.concurrent.Executor) r0
            java.lang.String r1 = "application"
            boolean r1 = r11.equalsIgnoreCase(r1)
            if (r1 == 0) goto Ld1
            javax.mail.EventQueue r10 = javax.mail.EventQueue.getApplicationEventQueue(r0)
            r9.q = r10
            goto Le7
        Ld1:
            java.lang.String r1 = "session"
            boolean r11 = r11.equalsIgnoreCase(r1)
            if (r11 == 0) goto Le0
            javax.mail.EventQueue r10 = r10.getEventQueue()
            r9.q = r10
            goto Le7
        Le0:
            javax.mail.EventQueue r10 = new javax.mail.EventQueue
            r10.<init>(r0)
            r9.q = r10
        Le7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Service.<init>(javax.mail.Session, javax.mail.URLName):void");
    }

    public void addConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.addElement(connectionListener);
    }

    public synchronized void close() throws MessagingException {
        setConnected(false);
        notifyConnectionListeners(3);
    }

    public void connect() throws MessagingException {
        connect(null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        this.q.terminateQueue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventQueue getEventQueue() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session getSession() {
        return this.session;
    }

    public synchronized URLName getURLName() {
        if (this.url != null && (this.url.getPassword() != null || this.url.getFile() != null)) {
            return new URLName(this.url.getProtocol(), this.url.getHost(), this.url.getPort(), null, this.url.getUsername(), null);
        }
        return this.url;
    }

    public synchronized boolean isConnected() {
        return this.connected;
    }

    protected void notifyConnectionListeners(int i) {
        if (this.connectionListeners.size() > 0) {
            queueEvent(new ConnectionEvent(this, i), this.connectionListeners);
        }
        if (i == 3) {
            this.q.terminateQueue();
        }
    }

    protected boolean protocolConnect(String str, int i, String str2, String str3) throws MessagingException {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void queueEvent(MailEvent mailEvent, Vector vector) {
        this.q.enqueue(mailEvent, (Vector) vector.clone());
    }

    public void removeConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.removeElement(connectionListener);
    }

    protected synchronized void setConnected(boolean z) {
        this.connected = z;
    }

    protected synchronized void setURLName(URLName uRLName) {
        this.url = uRLName;
    }

    public String toString() {
        URLName uRLName = getURLName();
        if (uRLName != null) {
            return uRLName.toString();
        }
        return super.toString();
    }

    public void connect(String str, String str2, String str3) throws MessagingException {
        connect(str, -1, str2, str3);
    }

    public void connect(String str, String str2) throws MessagingException {
        connect(null, str, str2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:6|(5:8|(1:10)(1:86)|11|(1:13)(1:85)|(4:15|(2:17|18)|80|18)(3:(2:84|18)|80|18))(1:87)|(2:(1:21)|(1:23))|(1:25)|(1:27)|(2:76|77)|29|(1:75)(2:33|(1:(1:36)(2:71|(1:73)))(6:74|38|39|40|(5:60|61|62|63|(1:65))|(1:(1:(1:(2:46|47)(2:49|50))(2:51|52))(1:53))(4:54|(1:56)|57|58)))|37|38|39|40|(0)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x010e, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x010f, code lost:
        r14 = r0;
        r0 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0159 A[Catch: all -> 0x0188, TryCatch #1 {, blocks: (B:4:0x0005, B:6:0x000b, B:8:0x000f, B:10:0x0017, B:14:0x0025, B:17:0x002f, B:19:0x0037, B:25:0x0055, B:29:0x0069, B:31:0x0087, B:33:0x00a5, B:35:0x00af, B:37:0x00b9, B:40:0x00c2, B:42:0x00c6, B:45:0x00e8, B:53:0x0108, B:58:0x0113, B:61:0x011a, B:63:0x0126, B:68:0x0140, B:69:0x0147, B:70:0x0148, B:71:0x014f, B:72:0x0150, B:73:0x0157, B:74:0x0158, B:75:0x0159, B:77:0x016a, B:78:0x0178, B:46:0x00f1, B:48:0x00fb, B:21:0x0040, B:23:0x004c, B:81:0x0180, B:82:0x0187), top: B:89:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0113 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void connect(java.lang.String r19, int r20, java.lang.String r21, java.lang.String r22) throws javax.mail.MessagingException {
        /*
            Method dump skipped, instructions count: 395
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Service.connect(java.lang.String, int, java.lang.String, java.lang.String):void");
    }
}
