package com.amazon.alexa.fitness.repository;

import kotlin.Metadata;
/* compiled from: CacheProviderImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"ACCESSORY_FITNESS_SESSION_KEY", "", "ACCESSORY_FITNESS_SESSION_NAME_FORMAT", "POOL_SIZE", "", "SAMPLE_KEY", "SAMPLE_NAME_FORMAT", "SESSION_RECOVERY_KEY", "SESSION_RECOVERY_NAME_FORMAT", "SESSION_SUMMARY_KEY", "SESSION_SUMMARY_NAME_FORMAT", "USER_PROFILE_KEY", "USER_PROFILE_NAME_FORMAT", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CacheProviderImplKt {
    private static final String ACCESSORY_FITNESS_SESSION_KEY = "AccessoryFitnessSession";
    private static final String ACCESSORY_FITNESS_SESSION_NAME_FORMAT = "accessory-fitness-session-cache-%d";
    private static final int POOL_SIZE = 1;
    private static final String SAMPLE_KEY = "samples";
    private static final String SAMPLE_NAME_FORMAT = "sample-cache-%d";
    private static final String SESSION_RECOVERY_KEY = "sessionRecovery";
    private static final String SESSION_RECOVERY_NAME_FORMAT = "session-recovery-cache-%d";
    private static final String SESSION_SUMMARY_KEY = "fitnessSession";
    private static final String SESSION_SUMMARY_NAME_FORMAT = "session-summary-cache-%d";
    private static final String USER_PROFILE_KEY = "userProfile";
    private static final String USER_PROFILE_NAME_FORMAT = "user-profile-cache-%d";
}
