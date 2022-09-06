package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.SessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class DefaultSessionSupplier implements SessionSupplier {
    private final List<AccessorySession> sessions = new ArrayList();
    private final List<AccessorySessionListener> sessionListeners = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SessionListenerDelegate extends SessionListener {
        private final Accessory accessory;
        private final SessionListener listener;

        @Override // com.amazon.alexa.accessory.SessionListener
        public void onSessionConnected() {
            Logger.d("SessionSupplier: A session was connected %s", this.accessory);
            SessionListener sessionListener = this.listener;
            if (sessionListener != null) {
                sessionListener.onSessionConnected();
            }
            for (int size = DefaultSessionSupplier.this.sessionListeners.size() - 1; size >= 0; size--) {
                ((AccessorySessionListener) DefaultSessionSupplier.this.sessionListeners.get(size)).onAccessorySessionConnected(this.accessory);
            }
        }

        @Override // com.amazon.alexa.accessory.SessionListener
        public void onSessionDisconnected() {
            Logger.d("SessionSupplier: A session was disconnected %s", this.accessory);
            DefaultSessionSupplier.this.removeSession(this.accessory);
            SessionListener sessionListener = this.listener;
            if (sessionListener != null) {
                sessionListener.onSessionDisconnected();
            }
            for (int size = DefaultSessionSupplier.this.sessionListeners.size() - 1; size >= 0; size--) {
                AccessorySessionListener accessorySessionListener = (AccessorySessionListener) DefaultSessionSupplier.this.sessionListeners.get(size);
                accessorySessionListener.onAccessorySessionDisconnected(this.accessory);
                accessorySessionListener.onAccessorySessionReleased(this.accessory);
            }
        }

        @Override // com.amazon.alexa.accessory.SessionListener
        public void onSessionFailed(Throwable th) {
            Logger.d("SessionSupplier: A session failed %s", th, this.accessory);
            DefaultSessionSupplier.this.removeSession(this.accessory);
            SessionListener sessionListener = this.listener;
            if (sessionListener != null) {
                sessionListener.onSessionFailed(th);
            }
            for (int size = DefaultSessionSupplier.this.sessionListeners.size() - 1; size >= 0; size--) {
                AccessorySessionListener accessorySessionListener = (AccessorySessionListener) DefaultSessionSupplier.this.sessionListeners.get(size);
                accessorySessionListener.onAccessorySessionFailed(this.accessory, th);
                accessorySessionListener.onAccessorySessionReleased(this.accessory);
            }
        }

        @Override // com.amazon.alexa.accessory.SessionListener
        public void onSessionTransportChanged(Accessory accessory, AccessoryTransport.Type type, AccessoryTransport.Type type2) {
            Logger.d("SessionSupplier: A session had a transport change %s from %s to %s", this.accessory, type, type2);
            SessionListener sessionListener = this.listener;
            if (sessionListener != null) {
                sessionListener.onSessionTransportChanged(this.accessory, type, type2);
            }
            for (int size = DefaultSessionSupplier.this.sessionListeners.size() - 1; size >= 0; size--) {
                ((AccessorySessionListener) DefaultSessionSupplier.this.sessionListeners.get(size)).onAccessorySessionTransportChanged(this.accessory, type, accessory, type2);
            }
        }

        private SessionListenerDelegate(Accessory accessory, SessionListener sessionListener) {
            Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
            this.accessory = accessory;
            this.listener = sessionListener;
        }
    }

    @Override // com.amazon.alexa.accessory.SessionSupplier
    public void addSessionListener(AccessorySessionListener accessorySessionListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessorySessionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.sessionListeners.add(accessorySessionListener);
    }

    @Override // com.amazon.alexa.accessory.SessionSupplier
    public AccessorySession createSession(Accessory accessory, AccessorySession.Factory factory) {
        return createSession(accessory, factory, null);
    }

    @Override // com.amazon.alexa.accessory.SessionSupplier
    public List<AccessorySession> getActiveSessions() {
        Preconditions.mainThread();
        return Collections.unmodifiableList(new ArrayList(this.sessions));
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0018  */
    @Override // com.amazon.alexa.accessory.SessionSupplier
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.alexa.accessory.AccessorySession getSession(com.amazon.alexa.accessory.Accessory r4) {
        /*
            r3 = this;
            com.amazon.alexa.accessory.internal.util.Preconditions.mainThread()
            java.lang.String r0 = "accessory"
            com.amazon.alexa.accessory.internal.util.Preconditions.notNull(r4, r0)
            java.lang.String r4 = r4.getAddress()
            java.util.List<com.amazon.alexa.accessory.AccessorySession> r0 = r3.sessions
            java.util.Iterator r0 = r0.iterator()
        L12:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L37
            java.lang.Object r1 = r0.next()
            com.amazon.alexa.accessory.AccessorySession r1 = (com.amazon.alexa.accessory.AccessorySession) r1
            com.amazon.alexa.accessory.Accessory r2 = r1.getAccessory()
            java.lang.String r2 = r2.getAddress()
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L36
            java.lang.String r2 = r1.getAddress()
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L12
        L36:
            return r1
        L37:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.internal.session.DefaultSessionSupplier.getSession(com.amazon.alexa.accessory.Accessory):com.amazon.alexa.accessory.AccessorySession");
    }

    @Override // com.amazon.alexa.accessory.SessionSupplier
    public boolean hasActiveSession(Accessory accessory) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        return getSession(accessory) != null;
    }

    @Override // com.amazon.alexa.accessory.SessionSupplier
    public boolean hasActiveSessions() {
        Preconditions.mainThread();
        return !this.sessions.isEmpty();
    }

    @Override // com.amazon.alexa.accessory.SessionSupplier
    public void removeSession(Accessory accessory) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        this.sessions.remove(getSession(accessory));
    }

    @Override // com.amazon.alexa.accessory.SessionSupplier
    public void removeSessionListener(AccessorySessionListener accessorySessionListener) {
        Preconditions.mainThread();
        Preconditions.notNull(accessorySessionListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.sessionListeners.remove(accessorySessionListener);
    }

    @Override // com.amazon.alexa.accessory.SessionSupplier
    public AccessorySession createSession(Accessory accessory, AccessorySession.Factory factory, SessionListener sessionListener) {
        return createSession(accessory, factory, sessionListener, new AccessorySessionOptions());
    }

    @Override // com.amazon.alexa.accessory.SessionSupplier
    public AccessorySession createSession(Accessory accessory, AccessorySession.Factory factory, SessionListener sessionListener, AccessorySessionOptions accessorySessionOptions) {
        Preconditions.mainThread();
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(factory, "factory");
        if (!hasActiveSession(accessory)) {
            Logger.d("SessionSupplier: creating session for accessory %s", accessory);
            AccessorySession create = factory.create(accessory, new SessionListenerDelegate(accessory, sessionListener), accessorySessionOptions);
            this.sessions.add(create);
            for (int size = this.sessionListeners.size() - 1; size >= 0; size--) {
                this.sessionListeners.get(size).onAccessorySessionCreated(accessory);
            }
            return create;
        }
        throw new IllegalStateException("Accessory session is already created for " + accessory);
    }
}
