package com.amazon.alexa.mobilytics.session;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.mobilytics.util.Utils;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Action1;
@Singleton
/* loaded from: classes9.dex */
public class SessionManager {
    private static final String TAG = Log.tag(SessionManager.class);
    private final Context context;
    private Session session;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Timeout {
        private static final long RESUME = TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS);
        private static final long RESTART = TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS);

        private Timeout() {
        }
    }

    @Inject
    public SessionManager(@NonNull Context context, @NonNull @Named("InstallationId") String str, @NonNull final SessionStorage sessionStorage) {
        this.context = (Context) Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(sessionStorage);
        Session session = sessionStorage.get();
        if (session == null) {
            Log.d(TAG, "MobilyticsSession could not be initialized from storage");
            session = new Session(str);
        }
        this.session = session;
        Observable<Session> filter = this.session.onStateChanged().filter($$Lambda$SessionManager$yyPPwJXhURJfxy8zFepIMtoKpk8.INSTANCE);
        sessionStorage.getClass();
        filter.subscribe(new Action1() { // from class: com.amazon.alexa.mobilytics.session.-$$Lambda$gqq3slBRuB0zqpynAWxB2-Y4qg0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SessionStorage.this.put((Session) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MobilyticsSession lambda$onSessionStateChange$1(Session session) {
        return session;
    }

    private void restartSession() {
        Log.enter();
        try {
            this.session.stop();
        } catch (IllegalStateException e) {
            Log.w(TAG, e, "[%s] MobilyticsSession possibly stopped already", this.session.id());
        }
        this.session.start();
        Log.leave();
    }

    private void resumeSession() {
        Log.enter();
        if (System.currentTimeMillis() - this.session.stopTime() < Timeout.RESUME) {
            Log.i(TAG, "[%s] MobilyticsSession Resuming session ...", this.session.id());
            this.session.resume();
        } else {
            Log.i(TAG, "[%s] MobilyticsSession Session has timed out. Restarting ...", this.session.id());
            restartSession();
        }
        Log.leave();
    }

    public Observable<MobilyticsSession> onSessionStateChange() {
        return this.session.onStateChanged().map($$Lambda$SessionManager$h0H1a5uzZDfkZyiKVHhakxExmY.INSTANCE);
    }

    public void pauseSession() {
        Log.enter();
        if (this.session.state() == 1 || this.session.state() == 3) {
            Log.i(TAG, "[%s] MobilyticsSession Pausing session ...", this.session.id());
            this.session.pause();
        }
        Log.leave();
    }

    @NonNull
    public MobilyticsSession session() {
        return this.session;
    }

    public void startSession() {
        Log.d(TAG, "MobilyticsSession startSession called due to lifecycle change");
        startSessionWithForce(false);
    }

    public void startSessionWithForce(boolean z) {
        Log.enter();
        if (!z && !Utils.isAppOnForeground(this.context)) {
            Log.d(TAG, "MobilyticsSession did not start due to app being in background");
            return;
        }
        if (this.session.state() == 2) {
            Log.i(TAG, "[%s] MobilyticsSession is PAUSED. Resuming session ...", this.session.id());
            resumeSession();
        } else if (this.session.state() == 0) {
            Log.i(TAG, "[%s] MobilyticsSession is STOPPED. Starting ...", this.session.id());
            this.session.start();
        } else {
            Log.d(TAG, "MobilyticsSession is already started and is not in pause of stop state");
        }
        Log.leave();
    }
}
