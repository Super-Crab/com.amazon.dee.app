package com.amazon.comms.ringservice.authtoken;

import android.os.Handler;
import android.os.SystemClock;
import com.amazon.comms.log.CommsLogger;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public class HandlerAuthTokenManager implements AuthTokenManager {
    private static final CommsLogger sLog = CommsLogger.getLogger(HandlerAuthTokenManager.class);
    private long expireTime;
    private AuthTokenManagerListener listener;
    private final Handler orchestratorHandler;
    private final int REFRESH_BEFORE_IN_SECS = 300;
    private final int REFRESH_RANDOM_INTERVAL_IN_SECS = 10;
    private final int VALIDITY_BUFFER_IN_SECS = 10;
    private final Runnable refreshAuthTokenRunnable = new Runnable() { // from class: com.amazon.comms.ringservice.authtoken.HandlerAuthTokenManager.1
        @Override // java.lang.Runnable
        public void run() {
            long elapsedRealtime = HandlerAuthTokenManager.this.expireTime - (SystemClock.elapsedRealtime() / 1000);
            CommsLogger commsLogger = HandlerAuthTokenManager.sLog;
            commsLogger.i("Refresh auth token: " + elapsedRealtime + " secs to expire");
            HandlerAuthTokenManager.this.listener.onAuthTokenExpiry(elapsedRealtime);
        }
    };

    public HandlerAuthTokenManager(AuthTokenManagerListener authTokenManagerListener, Handler handler) {
        boolean z = true;
        Preconditions.checkArgument(authTokenManagerListener != null, "listener cannot be null.");
        Preconditions.checkArgument(handler == null ? false : z, "handler cannot be null.");
        this.listener = authTokenManagerListener;
        this.orchestratorHandler = handler;
    }

    @Override // com.amazon.comms.ringservice.authtoken.AuthTokenManager
    public void cancelAuthTokenAlarm() {
        sLog.d("cancelAuthTokenAlarm");
        this.orchestratorHandler.removeCallbacks(this.refreshAuthTokenRunnable);
    }

    @Override // com.amazon.comms.ringservice.authtoken.AuthTokenManager
    public boolean isAuthTokenValid() {
        return this.expireTime - (SystemClock.elapsedRealtime() / 1000) > 10;
    }

    @Override // com.amazon.comms.ringservice.authtoken.AuthTokenManager
    public void setAuthTokenAlarm(int i, long j) {
        CommsLogger commsLogger = sLog;
        commsLogger.d("setAuthTokenAlarm: expiryInterval= " + i + " authTokenExpireTime= " + j);
        this.orchestratorHandler.removeCallbacks(this.refreshAuthTokenRunnable);
        this.expireTime = j;
        this.orchestratorHandler.postDelayed(this.refreshAuthTokenRunnable, (((long) (i + (-300))) + ((long) (Math.random() * 10.0d))) * 1000);
    }
}
