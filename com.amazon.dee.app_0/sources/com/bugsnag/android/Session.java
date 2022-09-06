package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Session implements JsonStream.Streamable {
    private final AtomicBoolean autoCaptured;
    private AtomicInteger handledCount;
    private final String id;
    final AtomicBoolean isStopped;
    private final Date startedAt;
    private AtomicBoolean tracked;
    private AtomicInteger unhandledCount;
    private final User user;

    public Session(String str, Date date, User user, boolean z) {
        this.unhandledCount = new AtomicInteger();
        this.handledCount = new AtomicInteger();
        this.tracked = new AtomicBoolean(false);
        this.isStopped = new AtomicBoolean(false);
        this.id = str;
        this.startedAt = new Date(date.getTime());
        this.user = user;
        this.autoCaptured = new AtomicBoolean(z);
    }

    static Session copySession(Session session) {
        Session session2 = new Session(session.id, session.startedAt, session.user, session.unhandledCount.get(), session.handledCount.get());
        session2.tracked.set(session.tracked.get());
        session2.autoCaptured.set(session.isAutoCaptured());
        return session2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getHandledCount() {
        return this.handledCount.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getId() {
        return this.id;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Date getStartedAt() {
        return new Date(this.startedAt.getTime());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getUnhandledCount() {
        return this.unhandledCount.intValue();
    }

    User getUser() {
        return this.user;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session incrementHandledAndCopy() {
        this.handledCount.incrementAndGet();
        return copySession(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session incrementUnhandledAndCopy() {
        this.unhandledCount.incrementAndGet();
        return copySession(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAutoCaptured() {
        return this.autoCaptured.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AtomicBoolean isTracked() {
        return this.tracked;
    }

    void setAutoCaptured(boolean z) {
        this.autoCaptured.set(z);
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginObject().mo6745name("id").value(this.id).mo6745name("startedAt").value(DateUtils.toIso8601(this.startedAt));
        if (this.user != null) {
            jsonStream.mo6745name("user").value((JsonStream.Streamable) this.user);
        }
        jsonStream.endObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session(String str, Date date, User user, int i, int i2) {
        this.unhandledCount = new AtomicInteger();
        this.handledCount = new AtomicInteger();
        this.tracked = new AtomicBoolean(false);
        this.isStopped = new AtomicBoolean(false);
        this.id = str;
        this.startedAt = new Date(date.getTime());
        this.user = user;
        this.autoCaptured = new AtomicBoolean(false);
        this.unhandledCount = new AtomicInteger(i);
        this.handledCount = new AtomicInteger(i2);
        this.tracked = new AtomicBoolean(true);
    }
}
