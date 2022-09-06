package com.sun.mail.pop3;

import com.sun.mail.util.MailLogger;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import java.util.logging.Level;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.MethodNotSupportedException;
import javax.mail.UIDFolder;
/* loaded from: classes3.dex */
public class POP3Folder extends Folder {
    private boolean doneUidl;
    private boolean exists;
    private volatile TempFile fileCache;
    MailLogger logger;
    private Vector message_cache;
    private String name;
    private volatile boolean opened;
    private volatile Protocol port;
    private int size;
    private POP3Store store;
    private int total;

    /* JADX INFO: Access modifiers changed from: protected */
    public POP3Folder(POP3Store pOP3Store, String str) {
        super(pOP3Store);
        this.exists = false;
        this.opened = false;
        this.doneUidl = false;
        this.fileCache = null;
        this.name = str;
        this.store = pOP3Store;
        if (str.equalsIgnoreCase("INBOX")) {
            this.exists = true;
        }
        this.logger = new MailLogger(POP3Folder.class, "DEBUG POP3", pOP3Store.getSession());
    }

    private void checkClosed() throws IllegalStateException {
        if (!this.opened) {
            return;
        }
        throw new IllegalStateException("Folder is Open");
    }

    private void checkOpen() throws IllegalStateException {
        if (this.opened) {
            return;
        }
        throw new IllegalStateException("Folder is not Open");
    }

    private void checkReadable() throws IllegalStateException {
        int i;
        if (!this.opened || !((i = this.mode) == 1 || i == 2)) {
            throw new IllegalStateException("Folder is not Readable");
        }
    }

    @Override // javax.mail.Folder
    public void appendMessages(Message[] messageArr) throws MessagingException {
        throw new MethodNotSupportedException("Append not supported");
    }

    @Override // javax.mail.Folder
    public synchronized void close(boolean z) throws MessagingException {
        checkOpen();
        try {
            if (this.store.rsetBeforeQuit) {
                this.port.rset();
            }
            if (z && this.mode == 2) {
                for (int i = 0; i < this.message_cache.size(); i++) {
                    POP3Message pOP3Message = (POP3Message) this.message_cache.elementAt(i);
                    if (pOP3Message != null && pOP3Message.isSet(Flags.Flag.DELETED)) {
                        try {
                            this.port.dele(i + 1);
                        } catch (IOException e) {
                            throw new MessagingException("Exception deleting messages during close", e);
                        }
                    }
                }
            }
            for (int i2 = 0; i2 < this.message_cache.size(); i2++) {
                POP3Message pOP3Message2 = (POP3Message) this.message_cache.elementAt(i2);
                if (pOP3Message2 != null) {
                    pOP3Message2.invalidate(true);
                }
            }
            this.port.quit();
            this.port = null;
            this.store.closePort(this);
            this.message_cache = null;
            this.opened = false;
            notifyConnectionListeners(3);
        } catch (IOException unused) {
            this.port = null;
            this.store.closePort(this);
            this.message_cache = null;
            this.opened = false;
            notifyConnectionListeners(3);
            if (this.fileCache != null) {
                this.fileCache.close();
            }
        }
        if (this.fileCache != null) {
            this.fileCache.close();
            this.fileCache = null;
        }
    }

    @Override // javax.mail.Folder
    public boolean create(int i) throws MessagingException {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.sun.mail.pop3.POP3Message createMessage(javax.mail.Folder r4, int r5) throws javax.mail.MessagingException {
        /*
            r3 = this;
            com.sun.mail.pop3.POP3Store r4 = r3.store
            java.lang.reflect.Constructor r4 = r4.messageConstructor
            if (r4 == 0) goto L1a
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L1a
            r1 = 0
            r0[r1] = r3     // Catch: java.lang.Exception -> L1a
            r1 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L1a
            r0[r1] = r2     // Catch: java.lang.Exception -> L1a
            java.lang.Object r4 = r4.newInstance(r0)     // Catch: java.lang.Exception -> L1a
            com.sun.mail.pop3.POP3Message r4 = (com.sun.mail.pop3.POP3Message) r4     // Catch: java.lang.Exception -> L1a
            goto L1b
        L1a:
            r4 = 0
        L1b:
            if (r4 != 0) goto L22
            com.sun.mail.pop3.POP3Message r4 = new com.sun.mail.pop3.POP3Message
            r4.<init>(r3, r5)
        L22:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.pop3.POP3Folder.createMessage(javax.mail.Folder, int):com.sun.mail.pop3.POP3Message");
    }

    @Override // javax.mail.Folder
    public boolean delete(boolean z) throws MessagingException {
        throw new MethodNotSupportedException("delete");
    }

    @Override // javax.mail.Folder
    public boolean exists() {
        return this.exists;
    }

    @Override // javax.mail.Folder
    public Message[] expunge() throws MessagingException {
        throw new MethodNotSupportedException("Expunge not supported");
    }

    @Override // javax.mail.Folder
    public synchronized void fetch(Message[] messageArr, FetchProfile fetchProfile) throws MessagingException {
        checkReadable();
        if (!this.doneUidl && this.store.supportsUidl && fetchProfile.contains(UIDFolder.FetchProfileItem.UID)) {
            String[] strArr = new String[this.message_cache.size()];
            try {
                if (!this.port.uidl(strArr)) {
                    return;
                }
                for (int i = 0; i < strArr.length; i++) {
                    if (strArr[i] != null) {
                        ((POP3Message) getMessage(i + 1)).uid = strArr[i];
                    }
                }
                this.doneUidl = true;
            } catch (EOFException e) {
                close(false);
                throw new FolderClosedException(this, e.toString());
            } catch (IOException e2) {
                throw new MessagingException("error getting UIDL", e2);
            }
        }
        if (fetchProfile.contains(FetchProfile.Item.ENVELOPE)) {
            for (Message message : messageArr) {
                try {
                    POP3Message pOP3Message = (POP3Message) message;
                    pOP3Message.getHeader("");
                    pOP3Message.getSize();
                } catch (MessageRemovedException unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Folder
    public void finalize() throws Throwable {
        super.finalize();
        close(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TempFile getFileCache() {
        return this.fileCache;
    }

    @Override // javax.mail.Folder
    public Folder getFolder(String str) throws MessagingException {
        throw new MessagingException("not a directory");
    }

    @Override // javax.mail.Folder
    public String getFullName() {
        return this.name;
    }

    @Override // javax.mail.Folder
    public synchronized Message getMessage(int i) throws MessagingException {
        POP3Message pOP3Message;
        checkOpen();
        int i2 = i - 1;
        pOP3Message = (POP3Message) this.message_cache.elementAt(i2);
        if (pOP3Message == null) {
            pOP3Message = createMessage(this, i);
            this.message_cache.setElementAt(pOP3Message, i2);
        }
        return pOP3Message;
    }

    @Override // javax.mail.Folder
    public synchronized int getMessageCount() throws MessagingException {
        if (!this.opened) {
            return -1;
        }
        checkReadable();
        return this.total;
    }

    @Override // javax.mail.Folder
    public String getName() {
        return this.name;
    }

    @Override // javax.mail.Folder
    public Folder getParent() {
        return new DefaultFolder(this.store);
    }

    @Override // javax.mail.Folder
    public Flags getPermanentFlags() {
        return new Flags();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Protocol getProtocol() throws MessagingException {
        Protocol protocol = this.port;
        checkOpen();
        return protocol;
    }

    @Override // javax.mail.Folder
    public char getSeparator() {
        return (char) 0;
    }

    public synchronized int getSize() throws MessagingException {
        checkOpen();
        return this.size;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
        if (r2 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003f, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
        if (r2 == null) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized int[] getSizes() throws javax.mail.MessagingException {
        /*
            r6 = this;
            monitor-enter(r6)
            r6.checkOpen()     // Catch: java.lang.Throwable -> L64
            int r0 = r6.total     // Catch: java.lang.Throwable -> L64
            int[] r0 = new int[r0]     // Catch: java.lang.Throwable -> L64
            r1 = 0
            com.sun.mail.pop3.Protocol r2 = r6.port     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L58
            java.io.InputStream r2 = r2.list()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L58
            com.sun.mail.util.LineInputStream r3 = new com.sun.mail.util.LineInputStream     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L48
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L48
        L14:
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L5a
            if (r1 == 0) goto L3a
            java.util.StringTokenizer r4 = new java.util.StringTokenizer     // Catch: java.lang.RuntimeException -> L14 java.lang.Throwable -> L43 java.io.IOException -> L5a
            r4.<init>(r1)     // Catch: java.lang.RuntimeException -> L14 java.lang.Throwable -> L43 java.io.IOException -> L5a
            java.lang.String r1 = r4.nextToken()     // Catch: java.lang.RuntimeException -> L14 java.lang.Throwable -> L43 java.io.IOException -> L5a
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.RuntimeException -> L14 java.lang.Throwable -> L43 java.io.IOException -> L5a
            java.lang.String r4 = r4.nextToken()     // Catch: java.lang.RuntimeException -> L14 java.lang.Throwable -> L43 java.io.IOException -> L5a
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.RuntimeException -> L14 java.lang.Throwable -> L43 java.io.IOException -> L5a
            if (r1 <= 0) goto L14
            int r5 = r6.total     // Catch: java.lang.RuntimeException -> L14 java.lang.Throwable -> L43 java.io.IOException -> L5a
            if (r1 > r5) goto L14
            int r1 = r1 + (-1)
            r0[r1] = r4     // Catch: java.lang.RuntimeException -> L14 java.lang.Throwable -> L43 java.io.IOException -> L5a
            goto L14
        L3a:
            r3.close()     // Catch: java.io.IOException -> L3d java.lang.Throwable -> L64
        L3d:
            if (r2 == 0) goto L62
        L3f:
            r2.close()     // Catch: java.io.IOException -> L62 java.lang.Throwable -> L64
            goto L62
        L43:
            r0 = move-exception
            goto L4d
        L45:
            r0 = move-exception
            r3 = r1
            goto L4d
        L48:
            r3 = r1
            goto L5a
        L4a:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L4d:
            if (r3 == 0) goto L52
            r3.close()     // Catch: java.io.IOException -> L52 java.lang.Throwable -> L64
        L52:
            if (r2 == 0) goto L57
            r2.close()     // Catch: java.io.IOException -> L57 java.lang.Throwable -> L64
        L57:
            throw r0     // Catch: java.lang.Throwable -> L64
        L58:
            r2 = r1
            r3 = r2
        L5a:
            if (r3 == 0) goto L5f
            r3.close()     // Catch: java.io.IOException -> L5f java.lang.Throwable -> L64
        L5f:
            if (r2 == 0) goto L62
            goto L3f
        L62:
            monitor-exit(r6)
            return r0
        L64:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.pop3.POP3Folder.getSizes():int[]");
    }

    @Override // javax.mail.Folder
    public int getType() {
        return 1;
    }

    public synchronized String getUID(Message message) throws MessagingException {
        checkOpen();
        if (message instanceof POP3Message) {
            POP3Message pOP3Message = (POP3Message) message;
            try {
                if (!this.store.supportsUidl) {
                    return null;
                }
                if (pOP3Message.uid == "UNKNOWN") {
                    pOP3Message.uid = this.port.uidl(pOP3Message.getMessageNumber());
                }
                return pOP3Message.uid;
            } catch (EOFException e) {
                close(false);
                throw new FolderClosedException(this, e.toString());
            } catch (IOException e2) {
                throw new MessagingException("error getting UIDL", e2);
            }
        }
        throw new MessagingException("message is not a POP3Message");
    }

    @Override // javax.mail.Folder
    public boolean hasNewMessages() throws MessagingException {
        return false;
    }

    @Override // javax.mail.Folder
    public synchronized boolean isOpen() {
        if (!this.opened) {
            return false;
        }
        try {
            try {
                if (this.port.noop()) {
                    return true;
                }
                throw new IOException("NOOP failed");
            } catch (IOException unused) {
                close(false);
                return false;
            }
        } catch (MessagingException unused2) {
            return false;
        } catch (Throwable unused3) {
            return false;
        }
    }

    @Override // javax.mail.Folder
    public Folder[] list(String str) throws MessagingException {
        throw new MessagingException("not a directory");
    }

    public synchronized InputStream listCommand() throws MessagingException, IOException {
        checkOpen();
        return this.port.list();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.mail.Folder
    public void notifyMessageChangedListeners(int i, Message message) {
        super.notifyMessageChangedListeners(i, message);
    }

    @Override // javax.mail.Folder
    public synchronized void open(int i) throws MessagingException {
        POP3Store pOP3Store;
        checkClosed();
        if (this.exists) {
            try {
                this.port = this.store.getPort(this);
                Status stat = this.port.stat();
                this.total = stat.total;
                this.size = stat.size;
                this.mode = i;
                if (this.store.useFileCache) {
                    try {
                        this.fileCache = new TempFile(this.store.fileCacheDir);
                    } catch (IOException e) {
                        this.logger.log(Level.FINE, "failed to create file cache", (Throwable) e);
                        throw e;
                    }
                }
                this.opened = true;
                this.message_cache = new Vector(this.total);
                this.message_cache.setSize(this.total);
                this.doneUidl = false;
                notifyConnectionListeners(1);
            } catch (IOException e2) {
                try {
                    if (this.port != null) {
                        this.port.quit();
                    }
                    this.port = null;
                    pOP3Store = this.store;
                } catch (IOException unused) {
                    this.port = null;
                    pOP3Store = this.store;
                } catch (Throwable th) {
                    this.port = null;
                    this.store.closePort(this);
                    throw th;
                }
                pOP3Store.closePort(this);
                throw new MessagingException("Open failed", e2);
            }
        } else {
            throw new FolderNotFoundException(this, "folder is not INBOX");
        }
    }

    @Override // javax.mail.Folder
    public boolean renameTo(Folder folder) throws MessagingException {
        throw new MethodNotSupportedException("renameTo");
    }
}
