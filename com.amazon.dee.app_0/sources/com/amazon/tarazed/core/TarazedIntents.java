package com.amazon.tarazed.core;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedIntents.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/tarazed/core/TarazedIntents;", "", "()V", "SessionAndroidService", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedIntents {
    public static final TarazedIntents INSTANCE = new TarazedIntents();

    /* compiled from: TarazedIntents.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/tarazed/core/TarazedIntents$SessionAndroidService;", "", "()V", "DISPLAY_DRAWER", "", "END_SESSION", "END_SESSION_IMMEDIATE", "END_SESSION_ON_3P_APP_BACKGROUND_TIMEOUT", "END_SESSION_ON_3P_APP_EVENT", "END_SESSION_ON_PROFILE_SWITCH", "END_SESSION_ON_SHUTDOWN", "EXTRA_LAUNCH_REQUEST", "FORCE_SHOW_END_SESSION_NOTIFICATION", "PAUSE_SESSION", "PAUSE_SESSION_3P_APP_STOP", "RESUME_SESSION", "RESUME_SUSPENDED_SESSION", "START_SESSION", "SUSPEND_SESSION", "SUSPEND_SESSION_DEVICE_SHUTDOWN", "SUSPEND_SESSION_PROFILE_SWITCH", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class SessionAndroidService {
        @NotNull
        public static final String DISPLAY_DRAWER = "com.amazon.tarazed.DISPLAY_DRAWER";
        @NotNull
        public static final String END_SESSION = "com.amazon.tarazed.END_SESSION";
        @NotNull
        public static final String END_SESSION_IMMEDIATE = "com.amazon.tarazed.END_SESSION_IMMEDIATE";
        @NotNull
        public static final String END_SESSION_ON_3P_APP_BACKGROUND_TIMEOUT = "com.amazon.tarazed.END_SESSION_ON_3P_APP_BACKGROUND_TIMEOUT";
        @NotNull
        public static final String END_SESSION_ON_3P_APP_EVENT = "com.amazon.tarazed.END_SESSION_ON_3P_APP_EVENT";
        @NotNull
        public static final String END_SESSION_ON_PROFILE_SWITCH = "com.amazon.tarazed.END_SESSION_ON_PROFILE_SWITCH";
        @NotNull
        public static final String END_SESSION_ON_SHUTDOWN = "com.amazon.tarazed.END_SESSION_ON_SHUTDOWN";
        @NotNull
        public static final String EXTRA_LAUNCH_REQUEST = "com.amazon.tarazed.LAUNCH_REQUEST";
        @NotNull
        public static final String FORCE_SHOW_END_SESSION_NOTIFICATION = "com.amazon.tarazed.FORCE_SHOW_END_SESSION_NOTIFICATION";
        public static final SessionAndroidService INSTANCE = new SessionAndroidService();
        @NotNull
        public static final String PAUSE_SESSION = "com.amazon.tarazed.PAUSE_SESSION";
        @NotNull
        public static final String PAUSE_SESSION_3P_APP_STOP = "com.amazon.tarazed.PAUSE_SESSION_3P_APP_STOP";
        @NotNull
        public static final String RESUME_SESSION = "com.amazon.tarazed.RESUME_SESSION";
        @NotNull
        public static final String RESUME_SUSPENDED_SESSION = "com.amazon.tarazed.RESUME_SUSPENDED_SESSION";
        @NotNull
        public static final String START_SESSION = "com.amazon.tarazed.START_SESSION";
        @NotNull
        public static final String SUSPEND_SESSION = "com.amazon.tarazed.SUSPEND_SESSION";
        @NotNull
        public static final String SUSPEND_SESSION_DEVICE_SHUTDOWN = "com.amazon.tarazed.SUSPEND_SESSION_DEVICE_SHUTDOWN";
        @NotNull
        public static final String SUSPEND_SESSION_PROFILE_SWITCH = "com.amazon.tarazed.SUSPEND_SESSION_PROFILE_SWITCH";

        private SessionAndroidService() {
        }
    }

    private TarazedIntents() {
    }
}
