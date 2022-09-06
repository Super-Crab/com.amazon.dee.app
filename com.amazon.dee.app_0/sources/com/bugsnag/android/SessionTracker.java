package com.bugsnag.android;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bugsnag.android.NativeInterface;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SessionTracker extends Observable implements Application.ActivityLifecycleCallbacks {
    private static final int DEFAULT_TIMEOUT_MS = 30000;
    private static final String KEY_LIFECYCLE_CALLBACK = "ActivityLifecycle";
    final Client client;
    final Configuration configuration;
    private final AtomicReference<Session> currentSession;
    private final Semaphore flushingRequest;
    private final Collection<String> foregroundActivities;
    private final ForegroundDetector foregroundDetector;
    private final AtomicLong lastEnteredForegroundMs;
    private final AtomicLong lastExitedForegroundMs;
    final SessionStore sessionStore;
    private final long timeoutMs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionTracker(Configuration configuration, Client client, SessionStore sessionStore) {
        this(configuration, client, 30000L, sessionStore);
    }

    private String getActivityName(@NonNull Activity activity) {
        return activity.getClass().getSimpleName();
    }

    private String getReleaseStage() {
        return MapUtils.getStringFromMap("releaseStage", this.client.appData.getAppDataSummary());
    }

    private void leaveBreadcrumb(String str, String str2) {
        if (this.configuration.isAutomaticallyCollectingBreadcrumbs()) {
            try {
                this.client.leaveBreadcrumb(str, BreadcrumbType.NAVIGATION, GeneratedOutlineSupport1.outline133(KEY_LIFECYCLE_CALLBACK, str2));
            } catch (Exception e) {
                Logger.warn(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Failed to leave breadcrumb in SessionTracker: ")));
            }
        }
    }

    private void notifyNdkInForeground() {
        Boolean isInForeground = isInForeground();
        if (isInForeground != null) {
            notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.UPDATE_IN_FOREGROUND, Arrays.asList(isInForeground, getContextActivity())));
        }
    }

    private void notifySessionStartObserver(Session session) {
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.START_SESSION, Arrays.asList(session.getId(), DateUtils.toIso8601(session.getStartedAt()), Integer.valueOf(session.getHandledCount()), Integer.valueOf(session.getUnhandledCount()))));
    }

    private void trackSessionIfNeeded(final Session session) {
        if (this.configuration.shouldNotifyForReleaseStage(getReleaseStage())) {
            if ((!this.configuration.getAutoCaptureSessions() && session.isAutoCaptured()) || !session.isTracked().compareAndSet(false, true)) {
                return;
            }
            notifySessionStartObserver(session);
            try {
                this.configuration.getSessionEndpoint();
                Async.run(new Runnable() { // from class: com.bugsnag.android.SessionTracker.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SessionTracker.this.flushStoredSessions();
                        Session session2 = session;
                        Client client = SessionTracker.this.client;
                        SessionTrackingPayload sessionTrackingPayload = new SessionTrackingPayload(session2, null, client.appData, client.deviceData);
                        try {
                            for (BeforeSendSession beforeSendSession : SessionTracker.this.configuration.getSessionCallbacks()) {
                                beforeSendSession.beforeSendSession(sessionTrackingPayload);
                            }
                            SessionTracker.this.configuration.getDelivery().deliver(sessionTrackingPayload, SessionTracker.this.configuration);
                        } catch (DeliveryFailureException e) {
                            Logger.warn("Storing session payload for future delivery", e);
                            SessionTracker.this.sessionStore.write(session);
                        } catch (Exception e2) {
                            Logger.warn("Dropping invalid session tracking payload", e2);
                        }
                    }
                });
            } catch (RejectedExecutionException unused) {
                this.sessionStore.write(session);
            }
        }
    }

    void flushStoredSessions() {
        if (this.flushingRequest.tryAcquire(1)) {
            try {
                List<File> findStoredFiles = this.sessionStore.findStoredFiles();
                if (!findStoredFiles.isEmpty()) {
                    try {
                        try {
                            this.configuration.getDelivery().deliver(new SessionTrackingPayload(null, findStoredFiles, this.client.appData, this.client.deviceData), this.configuration);
                            this.sessionStore.deleteStoredFiles(findStoredFiles);
                        } catch (Exception e) {
                            Logger.warn("Deleting invalid session tracking payload", e);
                            this.sessionStore.deleteStoredFiles(findStoredFiles);
                        }
                    } catch (DeliveryFailureException e2) {
                        this.sessionStore.cancelQueuedFiles(findStoredFiles);
                        Logger.warn("Leaving session payload for future delivery", e2);
                    }
                }
            } finally {
                this.flushingRequest.release(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getContextActivity() {
        if (this.foregroundActivities.isEmpty()) {
            return null;
        }
        int size = this.foregroundActivities.size();
        return ((String[]) this.foregroundActivities.toArray(new String[size]))[size - 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Session getCurrentSession() {
        Session session = this.currentSession.get();
        if (session == null || session.isStopped.get()) {
            return null;
        }
        return session;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Long getDurationInForegroundMs(long j) {
        long j2 = this.lastEnteredForegroundMs.get();
        Boolean isInForeground = isInForeground();
        if (isInForeground == null) {
            return null;
        }
        long j3 = (!isInForeground.booleanValue() || j2 == 0) ? 0L : j - j2;
        if (j3 <= 0) {
            j3 = 0;
        }
        return Long.valueOf(j3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session incrementHandledAndCopy() {
        Session currentSession = getCurrentSession();
        if (currentSession != null) {
            return currentSession.incrementHandledAndCopy();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session incrementUnhandledAndCopy() {
        Session currentSession = getCurrentSession();
        if (currentSession != null) {
            return currentSession.incrementUnhandledAndCopy();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Boolean isInForeground() {
        return this.foregroundDetector.isInForeground();
    }

    void leaveLifecycleBreadcrumb(String str, String str2) {
        leaveBreadcrumb(str, str2);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {
        leaveLifecycleBreadcrumb(getActivityName(activity), "onCreate()");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NonNull Activity activity) {
        leaveLifecycleBreadcrumb(getActivityName(activity), "onDestroy()");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NonNull Activity activity) {
        leaveLifecycleBreadcrumb(getActivityName(activity), "onPause()");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NonNull Activity activity) {
        leaveLifecycleBreadcrumb(getActivityName(activity), "onResume()");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NonNull Activity activity, Bundle bundle) {
        leaveLifecycleBreadcrumb(getActivityName(activity), "onSaveInstanceState()");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NonNull Activity activity) {
        String activityName = getActivityName(activity);
        leaveLifecycleBreadcrumb(activityName, "onStart()");
        updateForegroundTracker(activityName, true, System.currentTimeMillis());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(@NonNull Activity activity) {
        String activityName = getActivityName(activity);
        leaveLifecycleBreadcrumb(activityName, "onStop()");
        updateForegroundTracker(activityName, false, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onAutoCaptureEnabled() {
        Session session = this.currentSession.get();
        if (session == null || this.foregroundActivities.isEmpty()) {
            return;
        }
        trackSessionIfNeeded(session);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Session registerExistingSession(@Nullable Date date, @Nullable String str, @Nullable User user, int i, int i2) {
        Session session = null;
        if (date != null && str != null) {
            session = new Session(str, date, user, i, i2);
            notifySessionStartObserver(session);
        } else {
            setChanged();
            notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.STOP_SESSION, null));
        }
        this.currentSession.set(session);
        return session;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean resumeSession() {
        Session session = this.currentSession.get();
        boolean z = false;
        if (session == null) {
            session = startSession(false);
        } else {
            z = session.isStopped.compareAndSet(true, false);
        }
        if (session != null) {
            notifySessionStartObserver(session);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startFirstSession(Activity activity) {
        if (this.currentSession.get() == null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.lastEnteredForegroundMs.set(currentTimeMillis);
            startNewSession(new Date(currentTimeMillis), this.client.getUser(), true);
            this.foregroundActivities.add(getActivityName(activity));
        }
    }

    @Nullable
    @VisibleForTesting
    Session startNewSession(@NonNull Date date, @Nullable User user, boolean z) {
        if (this.configuration.getSessionEndpoint() == null) {
            Logger.warn("The session tracking endpoint has not been set. Session tracking is disabled");
            return null;
        }
        Session session = new Session(UUID.randomUUID().toString(), date, user, z);
        this.currentSession.set(session);
        trackSessionIfNeeded(session);
        return session;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session startSession(boolean z) {
        return startNewSession(new Date(), this.client.getUser(), z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopSession() {
        Session session = this.currentSession.get();
        if (session != null) {
            session.isStopped.set(true);
            setChanged();
            notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.STOP_SESSION, null));
        }
    }

    void updateForegroundTracker(String str, boolean z, long j) {
        if (z) {
            long j2 = j - this.lastExitedForegroundMs.get();
            if (this.foregroundActivities.isEmpty()) {
                this.lastEnteredForegroundMs.set(j);
                if (j2 >= this.timeoutMs && this.configuration.getAutoCaptureSessions()) {
                    startNewSession(new Date(j), this.client.getUser(), true);
                }
            }
            this.foregroundActivities.add(str);
        } else {
            this.foregroundActivities.remove(str);
            if (this.foregroundActivities.isEmpty()) {
                this.lastExitedForegroundMs.set(j);
            }
        }
        setChanged();
        notifyNdkInForeground();
    }

    SessionTracker(Configuration configuration, Client client, long j, SessionStore sessionStore) {
        this.foregroundActivities = new ConcurrentLinkedQueue();
        this.lastExitedForegroundMs = new AtomicLong(0L);
        this.lastEnteredForegroundMs = new AtomicLong(0L);
        this.currentSession = new AtomicReference<>();
        this.flushingRequest = new Semaphore(1);
        this.configuration = configuration;
        this.client = client;
        this.timeoutMs = j;
        this.sessionStore = sessionStore;
        this.foregroundDetector = new ForegroundDetector(client.appContext);
        notifyNdkInForeground();
    }
}
