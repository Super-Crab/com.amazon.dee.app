package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.device.messaging.ADMConstants;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class Error implements JsonStream.Streamable {
    private Breadcrumbs breadcrumbs;
    @NonNull
    final Configuration config;
    @Nullable
    private String context;
    private final BugsnagException exception;
    private final Exceptions exceptions;
    @Nullable
    private String groupingHash;
    private final HandledState handledState;
    private String[] projectPackages;
    private final Session session;
    @Nullable
    private Severity severity;
    private final ThreadState threadState;
    @NonNull
    private Map<String, Object> appData = new HashMap();
    @NonNull
    private Map<String, Object> deviceData = new HashMap();
    @NonNull
    private User user = new User();
    @NonNull
    private MetaData metaData = new MetaData();
    private boolean incomplete = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Error(@NonNull Configuration configuration, @NonNull Throwable th, HandledState handledState, @NonNull Severity severity, Session session, ThreadState threadState) {
        this.threadState = threadState;
        this.config = configuration;
        if (th instanceof BugsnagException) {
            this.exception = (BugsnagException) th;
        } else {
            this.exception = new BugsnagException(th);
        }
        this.handledState = handledState;
        this.severity = severity;
        this.session = session;
        this.projectPackages = configuration.getProjectPackages();
        this.exceptions = new Exceptions(configuration, this.exception);
    }

    public void addToTab(@NonNull String str, @NonNull String str2, @Nullable Object obj) {
        this.metaData.addToTab(str, str2, obj);
    }

    public void clearTab(@NonNull String str) {
        this.metaData.clearTab(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Map<String, Object> getAppData() {
        return this.appData;
    }

    @Nullable
    public String getContext() {
        return this.context;
    }

    @NonNull
    public Map<String, Object> getDeviceData() {
        return this.deviceData;
    }

    @NonNull
    public Throwable getException() {
        return this.exception;
    }

    @NonNull
    public String getExceptionMessage() {
        String message = this.exception.getMessage();
        return message != null ? message : "";
    }

    @NonNull
    public String getExceptionName() {
        return this.exception.getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Exceptions getExceptions() {
        return this.exceptions;
    }

    @Nullable
    public String getGroupingHash() {
        return this.groupingHash;
    }

    @NonNull
    public HandledState getHandledState() {
        return this.handledState;
    }

    @NonNull
    public MetaData getMetaData() {
        return this.metaData;
    }

    String[] getProjectPackages() {
        return this.projectPackages;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Session getSession() {
        return this.session;
    }

    @Nullable
    public Severity getSeverity() {
        return this.severity;
    }

    @NonNull
    public User getUser() {
        return this.user;
    }

    boolean isIncomplete() {
        return this.incomplete;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAppData(@NonNull Map<String, Object> map) {
        this.appData = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBreadcrumbs(Breadcrumbs breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public void setContext(@Nullable String str) {
        this.context = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDeviceData(@NonNull Map<String, Object> map) {
        this.deviceData = map;
    }

    public void setDeviceId(@Nullable String str) {
        this.deviceData.put("id", str);
    }

    public void setExceptionMessage(@NonNull String str) {
        this.exception.setMessage(str);
    }

    public void setExceptionName(@NonNull String str) {
        this.exception.setName(str);
    }

    public void setGroupingHash(@Nullable String str) {
        this.groupingHash = str;
    }

    void setIncomplete(boolean z) {
        this.incomplete = z;
    }

    public void setMetaData(@NonNull MetaData metaData) {
        if (metaData == null) {
            this.metaData = new MetaData();
        } else {
            this.metaData = metaData;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProjectPackages(String[] strArr) {
        this.projectPackages = strArr;
        Exceptions exceptions = this.exceptions;
        if (exceptions != null) {
            exceptions.setProjectPackages(strArr);
        }
    }

    public void setSeverity(@Nullable Severity severity) {
        if (severity != null) {
            this.severity = severity;
            this.handledState.setCurrentSeverity(severity);
        }
    }

    public void setUser(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.user = new User(str, str2, str3);
    }

    public void setUserEmail(@Nullable String str) {
        this.user = new User(this.user);
        this.user.setEmail(str);
    }

    public void setUserId(@Nullable String str) {
        this.user = new User(this.user);
        this.user.setId(str);
    }

    public void setUserName(@Nullable String str) {
        this.user = new User(this.user);
        this.user.setName(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldIgnoreClass() {
        return this.config.shouldIgnoreClass(getExceptionName());
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        MetaData merge = MetaData.merge(this.config.getMetaData(), this.metaData);
        jsonStream.beginObject();
        jsonStream.mo6745name("context").value(this.context);
        jsonStream.mo6745name("metaData").value((JsonStream.Streamable) merge);
        jsonStream.mo6745name("severity").value((JsonStream.Streamable) this.severity);
        jsonStream.mo6745name("severityReason").value((JsonStream.Streamable) this.handledState);
        jsonStream.mo6745name("unhandled").value(this.handledState.isUnhandled());
        jsonStream.mo6745name("incomplete").value(this.incomplete);
        if (this.projectPackages != null) {
            jsonStream.mo6745name("projectPackages").beginArray();
            for (String str : this.projectPackages) {
                jsonStream.value(str);
            }
            jsonStream.endArray();
        }
        jsonStream.mo6745name("exceptions").value((JsonStream.Streamable) this.exceptions);
        jsonStream.mo6745name("user").value((JsonStream.Streamable) this.user);
        jsonStream.mo6745name(ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT).value(this.appData);
        jsonStream.mo6745name("device").value(this.deviceData);
        jsonStream.mo6745name("breadcrumbs").value((JsonStream.Streamable) this.breadcrumbs);
        jsonStream.mo6745name("groupingHash").value(this.groupingHash);
        if (this.config.getSendThreads()) {
            jsonStream.mo6745name("threads").value((JsonStream.Streamable) this.threadState);
        }
        if (this.session != null) {
            jsonStream.mo6745name("session").beginObject();
            jsonStream.mo6745name("id").value(this.session.getId());
            jsonStream.mo6745name("startedAt").value(DateUtils.toIso8601(this.session.getStartedAt()));
            jsonStream.mo6745name(DefaultDeliveryClient.EVENTS_DIRECTORY).beginObject();
            jsonStream.mo6745name("handled").value(this.session.getHandledCount());
            jsonStream.mo6745name("unhandled").value(this.session.getUnhandledCount());
            jsonStream.endObject();
            jsonStream.endObject();
        }
        jsonStream.endObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUser(@NonNull User user) {
        this.user = user;
    }

    /* loaded from: classes.dex */
    static class Builder {
        private String attributeValue;
        private final Configuration config;
        private final Throwable exception;
        private MetaData metaData;
        private final SessionTracker sessionTracker;
        private Severity severity;
        private String severityReasonType;
        private final ThreadState threadState;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(@NonNull Configuration configuration, @NonNull Throwable th, SessionTracker sessionTracker, @NonNull Thread thread, boolean z) {
            this.severity = Severity.WARNING;
            this.threadState = new ThreadState(configuration, thread, Thread.getAllStackTraces(), z ? th : null);
            this.config = configuration;
            this.exception = th;
            this.severityReasonType = "userSpecifiedSeverity";
            this.sessionTracker = sessionTracker;
        }

        private Session getSession(HandledState handledState) {
            Session currentSession;
            SessionTracker sessionTracker = this.sessionTracker;
            if (sessionTracker == null || (currentSession = sessionTracker.getCurrentSession()) == null) {
                return null;
            }
            if (!this.config.getAutoCaptureSessions() && currentSession.isAutoCaptured()) {
                return null;
            }
            if (handledState.isUnhandled()) {
                return this.sessionTracker.incrementUnhandledAndCopy();
            }
            return this.sessionTracker.incrementHandledAndCopy();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder attributeValue(String str) {
            this.attributeValue = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Error build() {
            HandledState newInstance = HandledState.newInstance(this.severityReasonType, this.severity, this.attributeValue);
            Error error = new Error(this.config, this.exception, newInstance, this.severity, getSession(newInstance), this.threadState);
            MetaData metaData = this.metaData;
            if (metaData != null) {
                error.setMetaData(metaData);
            }
            return error;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder metaData(MetaData metaData) {
            this.metaData = metaData;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder severity(Severity severity) {
            this.severity = severity;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder severityReasonType(String str) {
            this.severityReasonType = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(@NonNull Configuration configuration, @NonNull String str, @NonNull String str2, @NonNull StackTraceElement[] stackTraceElementArr, SessionTracker sessionTracker, Thread thread) {
            this(configuration, new BugsnagException(str, str2, stackTraceElementArr), sessionTracker, thread, false);
        }
    }
}
