package com.amazon.alexa.mobilytics.session;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.mobilytics.util.Utils;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.google.common.base.Preconditions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import rx.Observable;
import rx.subjects.PublishSubject;
/* loaded from: classes9.dex */
public class Session implements MobilyticsSession, Cloneable {
    private static final String SESSION_ID_DATE_FORMAT = "yyyyMMdd-HHmmssSSS";
    private static final char SESSION_ID_DELIMITER = '-';
    private static final char SESSION_ID_PAD_CHAR = '_';
    private static final int SESSION_ID_PREFIX_LENGTH = 8;
    private String id;
    private final DateFormat sessionIdFormat;
    private final String sessionIdPrefix;
    private final PublishSubject<String> sessionIdSubject;
    private final PublishSubject<Session> sessionSubject;
    private long startTime;
    private int state;
    private long stopTime;
    private static int[][] TRANSITIONS = {new int[]{1, 4, 4, 4}, new int[]{4, 2, 4, 0}, new int[]{4, 4, 3, 0}, new int[]{4, 2, 4, 0}, new int[]{4, 4, 4, 4}};
    private static final String TAG = Log.tag(Session.class);

    public Session(@NonNull String str) {
        this.sessionIdFormat = new SimpleDateFormat(SESSION_ID_DATE_FORMAT, Locale.US);
        this.sessionSubject = PublishSubject.create();
        this.sessionIdSubject = PublishSubject.create();
        this.startTime = 0L;
        this.stopTime = 0L;
        this.state = 0;
        this.sessionIdPrefix = str;
        this.sessionIdFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0030, code lost:
        if (r8 != 3) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void triggerEvent(int r8) {
        /*
            r7 = this;
            java.lang.String r0 = com.amazon.alexa.mobilytics.session.Session.TAG
            r1 = 3
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = r7.id
            r4 = 0
            r2[r4] = r3
            int r3 = r7.state
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r5 = 1
            r2[r5] = r3
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            r6 = 2
            r2[r6] = r3
            java.lang.String r3 = "[%s] Current MobilyticsSession state is %d. Event received is %d"
            com.amazon.alexa.mobilytics.util.Log.d(r0, r3, r2)
            int[][] r0 = com.amazon.alexa.mobilytics.session.Session.TRANSITIONS
            int r2 = r7.state
            r0 = r0[r2]
            r0 = r0[r8]
            r2 = 4
            if (r0 == r2) goto L7b
            if (r8 == 0) goto L3f
            if (r8 == r5) goto L38
            if (r8 == r6) goto L33
            if (r8 == r1) goto L38
            goto L4f
        L33:
            r1 = 0
            r7.stopTime = r1
            goto L4f
        L38:
            long r1 = java.lang.System.currentTimeMillis()
            r7.stopTime = r1
            goto L4f
        L3f:
            java.lang.String r8 = com.amazon.alexa.mobilytics.session.Session.TAG
            java.lang.String r1 = "MobilyticsSession start event called"
            com.amazon.alexa.mobilytics.util.Log.d(r8, r1)
            long r1 = java.lang.System.currentTimeMillis()
            r7.startTime = r1
            r7.generateSessionId()
        L4f:
            r7.state = r0
            java.lang.String r8 = com.amazon.alexa.mobilytics.session.Session.TAG     // Catch: java.lang.CloneNotSupportedException -> L6e
            java.lang.String r0 = "MobilyticsSession change callback for state [%d]"
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch: java.lang.CloneNotSupportedException -> L6e
            int r2 = r7.state     // Catch: java.lang.CloneNotSupportedException -> L6e
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.CloneNotSupportedException -> L6e
            r1[r4] = r2     // Catch: java.lang.CloneNotSupportedException -> L6e
            com.amazon.alexa.mobilytics.util.Log.d(r8, r0, r1)     // Catch: java.lang.CloneNotSupportedException -> L6e
            rx.subjects.PublishSubject<com.amazon.alexa.mobilytics.session.Session> r8 = r7.sessionSubject     // Catch: java.lang.CloneNotSupportedException -> L6e
            java.lang.Object r0 = r7.clone()     // Catch: java.lang.CloneNotSupportedException -> L6e
            com.amazon.alexa.mobilytics.session.Session r0 = (com.amazon.alexa.mobilytics.session.Session) r0     // Catch: java.lang.CloneNotSupportedException -> L6e
            r8.onNext(r0)     // Catch: java.lang.CloneNotSupportedException -> L6e
            goto L7a
        L6e:
            r8 = move-exception
            java.lang.String r0 = com.amazon.alexa.mobilytics.session.Session.TAG
            java.lang.Object[] r1 = new java.lang.Object[r5]
            r1[r4] = r8
            java.lang.String r8 = "[MobilyticsSession] Unable to create session clone"
            com.amazon.alexa.mobilytics.util.Log.e(r0, r8, r1)
        L7a:
            return
        L7b:
            java.lang.String r8 = com.amazon.alexa.mobilytics.session.Session.TAG
            java.lang.String r0 = "Illegal state change occurred in MobilyticsSession"
            com.amazon.alexa.mobilytics.util.Log.e(r8, r0)
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "illegal event"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.session.Session.triggerEvent(int):void");
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // com.amazon.alexa.mobilytics.session.MobilyticsSession
    public long elapsedTime() {
        int i = this.state;
        long currentTimeMillis = (i == 0 || i == 2) ? this.stopTime : System.currentTimeMillis();
        long j = this.startTime;
        if (j == 0 || currentTimeMillis < j) {
            return 0L;
        }
        return currentTimeMillis - j;
    }

    @VisibleForTesting
    void generateSessionId() {
        String format = this.sessionIdFormat.format(Long.valueOf(this.startTime));
        this.id = Utils.trimOrPadString(this.sessionIdPrefix, 8, SESSION_ID_PAD_CHAR) + SESSION_ID_DELIMITER + format;
        Log.v(TAG, "[%s] Allocated new MobilyticsSession ID", this.id);
        this.sessionIdSubject.onNext(id());
    }

    @Override // com.amazon.alexa.mobilytics.session.MobilyticsSession
    @Nullable
    public String id() {
        return this.id;
    }

    @Override // com.amazon.alexa.mobilytics.session.MobilyticsSession
    public Observable<String> onIdChanged() {
        return this.sessionIdSubject.asObservable();
    }

    public Observable<Session> onStateChanged() {
        return this.sessionSubject.asObservable();
    }

    public void pause() {
        triggerEvent(1);
    }

    public void resume() {
        triggerEvent(2);
    }

    public void start() {
        triggerEvent(0);
    }

    @Override // com.amazon.alexa.mobilytics.session.MobilyticsSession
    public long startTime() {
        return this.startTime;
    }

    @Override // com.amazon.alexa.mobilytics.session.MobilyticsSession
    public int state() {
        return this.state;
    }

    public void stop() {
        triggerEvent(3);
    }

    @Override // com.amazon.alexa.mobilytics.session.MobilyticsSession
    public long stopTime() {
        return this.stopTime;
    }

    public Session(@NonNull String str, @NonNull long j, @NonNull long j2, @NonNull int i) {
        this.sessionIdFormat = new SimpleDateFormat(SESSION_ID_DATE_FORMAT, Locale.US);
        this.sessionSubject = PublishSubject.create();
        this.sessionIdSubject = PublishSubject.create();
        this.startTime = 0L;
        this.stopTime = 0L;
        this.state = 0;
        this.sessionIdPrefix = (String) Preconditions.checkNotNull(str);
        this.startTime = j;
        this.stopTime = j2;
        this.state = i;
        Log.d(TAG, "MobilyticsSession initialized with prefix [%s], startTime [%d], stopTime [%d], state [%d]", this.sessionIdPrefix, Long.valueOf(this.startTime), Long.valueOf(this.stopTime), Integer.valueOf(this.state));
        this.sessionIdFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        if (this.startTime > 0) {
            generateSessionId();
        }
    }

    public Session(@NonNull Session session) {
        this.sessionIdFormat = new SimpleDateFormat(SESSION_ID_DATE_FORMAT, Locale.US);
        this.sessionSubject = PublishSubject.create();
        this.sessionIdSubject = PublishSubject.create();
        this.startTime = 0L;
        this.stopTime = 0L;
        this.state = 0;
        this.sessionIdPrefix = session.sessionIdPrefix;
        this.startTime = session.startTime;
        this.stopTime = session.stopTime;
        this.state = session.state;
        this.sessionIdFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
    }
}
