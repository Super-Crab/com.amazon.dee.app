package org.bouncycastle.jsse.provider;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.tls.SessionID;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.joda.time.DateTimeConstants;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ProvSSLSessionContext implements SSLSessionContext {
    private static final Logger LOG = Logger.getLogger(ProvSSLSessionContext.class.getName());
    private static final int provSessionCacheSize = PropertyUtils.getIntegerSystemProperty("javax.net.ssl.sessionCacheSize", CacheDataSink.DEFAULT_BUFFER_SIZE, 0, Integer.MAX_VALUE);
    protected final ContextData contextData;
    protected final Map<SessionID, SessionEntry> sessionsByID = new LinkedHashMap<SessionID, SessionEntry>(16, 0.75f, true) { // from class: org.bouncycastle.jsse.provider.ProvSSLSessionContext.1
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<SessionID, SessionEntry> entry) {
            boolean z = ProvSSLSessionContext.this.sessionCacheSize > 0 && size() > ProvSSLSessionContext.this.sessionCacheSize;
            if (z) {
                ProvSSLSessionContext.this.removeSessionByPeer(entry.getValue());
            }
            return z;
        }
    };
    protected final Map<String, SessionEntry> sessionsByPeer = new HashMap();
    protected final ReferenceQueue<ProvSSLSession> sessionsQueue = new ReferenceQueue<>();
    protected int sessionCacheSize = provSessionCacheSize;
    protected int sessionTimeoutSeconds = DateTimeConstants.SECONDS_PER_DAY;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class SessionEntry extends SoftReference<ProvSSLSession> {
        private final String peerKey;
        private final SessionID sessionID;

        SessionEntry(SessionID sessionID, ProvSSLSession provSSLSession, ReferenceQueue<ProvSSLSession> referenceQueue) {
            super(provSSLSession, referenceQueue);
            if (sessionID == null || provSSLSession == null || referenceQueue == null) {
                throw new NullPointerException();
            }
            this.sessionID = sessionID;
            this.peerKey = ProvSSLSessionContext.makePeerKey(provSSLSession);
        }

        public String getPeerKey() {
            return this.peerKey;
        }

        public SessionID getSessionID() {
            return this.sessionID;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSessionContext(ContextData contextData) {
        this.contextData = contextData;
    }

    private ProvSSLSession accessSession(SessionEntry sessionEntry) {
        if (sessionEntry != null) {
            ProvSSLSession provSSLSession = sessionEntry.get();
            if (provSSLSession != null) {
                long currentTimeMillis = System.currentTimeMillis();
                if (!invalidateIfCreatedBefore(sessionEntry, getCreationTimeLimit(currentTimeMillis))) {
                    provSSLSession.accessedAt(currentTimeMillis);
                    return provSSLSession;
                }
            }
            removeSession(sessionEntry);
            return null;
        }
        return null;
    }

    private long getCreationTimeLimit(long j) {
        int i = this.sessionTimeoutSeconds;
        if (i < 1) {
            return Long.MIN_VALUE;
        }
        return j - (i * 1000);
    }

    private boolean invalidateIfCreatedBefore(SessionEntry sessionEntry, long j) {
        ProvSSLSession provSSLSession = sessionEntry.get();
        if (provSSLSession == null) {
            return true;
        }
        if (provSSLSession.getCreationTime() < j) {
            provSSLSession.invalidate();
        }
        return !provSSLSession.isValid();
    }

    private static String makePeerKey(String str, int i) {
        if (str == null || i < 0) {
            return null;
        }
        StringBuilder outline108 = GeneratedOutlineSupport1.outline108(str, JsonReaderKt.COLON);
        outline108.append(Integer.toString(i));
        return outline108.toString().toLowerCase(Locale.ENGLISH);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String makePeerKey(ProvSSLSession provSSLSession) {
        if (provSSLSession == null) {
            return null;
        }
        return makePeerKey(provSSLSession.getPeerHost(), provSSLSession.getPeerPort());
    }

    private static SessionID makeSessionID(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return null;
        }
        return new SessionID(bArr);
    }

    private static <K, V> void mapAdd(Map<K, V> map, K k, V v) {
        if (map == null || v == null) {
            throw new NullPointerException();
        }
        if (k == null) {
            return;
        }
        map.put(k, v);
    }

    private static <K, V> V mapGet(Map<K, V> map, K k) {
        if (map != null) {
            if (k != null) {
                return map.get(k);
            }
            return null;
        }
        throw new NullPointerException();
    }

    private static <K, V> V mapRemove(Map<K, V> map, K k) {
        if (map != null) {
            if (k != null) {
                return map.remove(k);
            }
            return null;
        }
        throw new NullPointerException();
    }

    private static <K, V> boolean mapRemove(Map<K, V> map, K k, V v) {
        if (map == null || v == null) {
            throw new NullPointerException();
        }
        if (k == null) {
            return false;
        }
        V remove = map.remove(k);
        if (remove == v) {
            return true;
        }
        if (remove == null) {
            return false;
        }
        map.put(k, remove);
        return false;
    }

    private void processQueue() {
        int i = 0;
        while (true) {
            SessionEntry sessionEntry = (SessionEntry) this.sessionsQueue.poll();
            if (sessionEntry == null) {
                break;
            }
            removeSession(sessionEntry);
            i++;
        }
        if (i > 0) {
            Logger logger = LOG;
            logger.fine("Processed " + i + " session entries (soft references) from the reference queue");
        }
    }

    private void removeAllExpiredSessions() {
        processQueue();
        long creationTimeLimit = getCreationTimeLimit(System.currentTimeMillis());
        Iterator<SessionEntry> it2 = this.sessionsByID.values().iterator();
        while (it2.hasNext()) {
            SessionEntry next = it2.next();
            if (invalidateIfCreatedBefore(next, creationTimeLimit)) {
                it2.remove();
                removeSessionByPeer(next);
            }
        }
    }

    private void removeSession(SessionEntry sessionEntry) {
        mapRemove(this.sessionsByID, sessionEntry.getSessionID(), sessionEntry);
        removeSessionByPeer(sessionEntry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeSessionByPeer(SessionEntry sessionEntry) {
        return mapRemove(this.sessionsByPeer, sessionEntry.getPeerKey(), sessionEntry);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JcaTlsCrypto getCrypto() {
        return this.contextData.getCrypto();
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized Enumeration<byte[]> getIds() {
        ArrayList arrayList;
        removeAllExpiredSessions();
        arrayList = new ArrayList(this.sessionsByID.size());
        for (SessionID sessionID : this.sessionsByID.keySet()) {
            arrayList.add(sessionID.getBytes());
        }
        return Collections.enumeration(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLContextSpi getSSLContext() {
        return this.contextData.getContext();
    }

    @Override // javax.net.ssl.SSLSessionContext
    public SSLSession getSession(byte[] bArr) {
        if (bArr != null) {
            return getSessionImpl(bArr);
        }
        throw new NullPointerException("'sessionID' cannot be null");
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized int getSessionCacheSize() {
        return this.sessionCacheSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ProvSSLSession getSessionImpl(String str, int i) {
        ProvSSLSession accessSession;
        processQueue();
        SessionEntry sessionEntry = (SessionEntry) mapGet(this.sessionsByPeer, makePeerKey(str, i));
        accessSession = accessSession(sessionEntry);
        if (accessSession != null) {
            this.sessionsByID.get(sessionEntry.getSessionID());
        }
        return accessSession;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ProvSSLSession getSessionImpl(byte[] bArr) {
        processQueue();
        return accessSession((SessionEntry) mapGet(this.sessionsByID, makeSessionID(bArr)));
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized int getSessionTimeout() {
        return this.sessionTimeoutSeconds;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void removeSession(byte[] bArr) {
        SessionEntry sessionEntry = (SessionEntry) mapRemove(this.sessionsByID, makeSessionID(bArr));
        if (sessionEntry != null) {
            removeSessionByPeer(sessionEntry);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0044 A[Catch: all -> 0x004f, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:8:0x0020, B:16:0x0044, B:12:0x0029, B:14:0x0036, B:6:0x0018), top: B:22:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized org.bouncycastle.jsse.provider.ProvSSLSession reportSession(java.lang.String r10, int r11, org.bouncycastle.tls.TlsSession r12, org.bouncycastle.jsse.provider.JsseSessionParameters r13) {
        /*
            r9 = this;
            monitor-enter(r9)
            r9.processQueue()     // Catch: java.lang.Throwable -> L4f
            byte[] r0 = r12.getSessionID()     // Catch: java.lang.Throwable -> L4f
            org.bouncycastle.tls.SessionID r0 = makeSessionID(r0)     // Catch: java.lang.Throwable -> L4f
            java.util.Map<org.bouncycastle.tls.SessionID, org.bouncycastle.jsse.provider.ProvSSLSessionContext$SessionEntry> r1 = r9.sessionsByID     // Catch: java.lang.Throwable -> L4f
            java.lang.Object r1 = mapGet(r1, r0)     // Catch: java.lang.Throwable -> L4f
            org.bouncycastle.jsse.provider.ProvSSLSessionContext$SessionEntry r1 = (org.bouncycastle.jsse.provider.ProvSSLSessionContext.SessionEntry) r1     // Catch: java.lang.Throwable -> L4f
            if (r1 != 0) goto L18
            r2 = 0
            goto L1e
        L18:
            java.lang.Object r2 = r1.get()     // Catch: java.lang.Throwable -> L4f
            org.bouncycastle.jsse.provider.ProvSSLSession r2 = (org.bouncycastle.jsse.provider.ProvSSLSession) r2     // Catch: java.lang.Throwable -> L4f
        L1e:
            if (r2 == 0) goto L29
            org.bouncycastle.tls.TlsSession r3 = r2.getTlsSession()     // Catch: java.lang.Throwable -> L4f
            if (r3 == r12) goto L27
            goto L29
        L27:
            r8 = r2
            goto L42
        L29:
            org.bouncycastle.jsse.provider.ProvSSLSession r8 = new org.bouncycastle.jsse.provider.ProvSSLSession     // Catch: java.lang.Throwable -> L4f
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r13
            r2.<init>(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L4f
            if (r0 == 0) goto L42
            org.bouncycastle.jsse.provider.ProvSSLSessionContext$SessionEntry r1 = new org.bouncycastle.jsse.provider.ProvSSLSessionContext$SessionEntry     // Catch: java.lang.Throwable -> L4f
            java.lang.ref.ReferenceQueue<org.bouncycastle.jsse.provider.ProvSSLSession> r10 = r9.sessionsQueue     // Catch: java.lang.Throwable -> L4f
            r1.<init>(r0, r8, r10)     // Catch: java.lang.Throwable -> L4f
            java.util.Map<org.bouncycastle.tls.SessionID, org.bouncycastle.jsse.provider.ProvSSLSessionContext$SessionEntry> r10 = r9.sessionsByID     // Catch: java.lang.Throwable -> L4f
            r10.put(r0, r1)     // Catch: java.lang.Throwable -> L4f
        L42:
            if (r1 == 0) goto L4d
            java.util.Map<java.lang.String, org.bouncycastle.jsse.provider.ProvSSLSessionContext$SessionEntry> r10 = r9.sessionsByPeer     // Catch: java.lang.Throwable -> L4f
            java.lang.String r11 = r1.getPeerKey()     // Catch: java.lang.Throwable -> L4f
            mapAdd(r10, r11, r1)     // Catch: java.lang.Throwable -> L4f
        L4d:
            monitor-exit(r9)
            return r8
        L4f:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jsse.provider.ProvSSLSessionContext.reportSession(java.lang.String, int, org.bouncycastle.tls.TlsSession, org.bouncycastle.jsse.provider.JsseSessionParameters):org.bouncycastle.jsse.provider.ProvSSLSession");
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized void setSessionCacheSize(int i) throws IllegalArgumentException {
        int size;
        if (this.sessionCacheSize == i) {
            return;
        }
        if (i < 0) {
            throw new IllegalArgumentException("'size' cannot be < 0");
        }
        this.sessionCacheSize = i;
        removeAllExpiredSessions();
        if (this.sessionCacheSize > 0 && (size = this.sessionsByID.size()) > this.sessionCacheSize) {
            Iterator<SessionEntry> it2 = this.sessionsByID.values().iterator();
            for (size = this.sessionsByID.size(); it2.hasNext() && size > this.sessionCacheSize; size--) {
                it2.remove();
                removeSessionByPeer(it2.next());
            }
        }
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized void setSessionTimeout(int i) throws IllegalArgumentException {
        if (this.sessionTimeoutSeconds == i) {
            return;
        }
        if (i < 0) {
            throw new IllegalArgumentException("'seconds' cannot be < 0");
        }
        this.sessionTimeoutSeconds = i;
        removeAllExpiredSessions();
    }
}
