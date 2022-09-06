package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UserProfileRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/UserProfileRepository;", "", MetricsConstants.OperationalMetrics.GET_USER_PROFILE, "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "profileDirectedId", "", "getUserProfileForCurrentUser", "saveUserProfile", "", "userProfile", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface UserProfileRepository {
    @Nullable
    UserProfile getUserProfile(@NotNull String str);

    @Nullable
    UserProfile getUserProfileForCurrentUser();

    void saveUserProfile(@NotNull UserProfile userProfile);
}
