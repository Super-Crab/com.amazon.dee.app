package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J3\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "Ljava/io/Serializable;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "userProfile", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "workoutType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/WorkoutType;", "coaching", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching;", "(Ljava/util/UUID;Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;Lcom/amazon/alexa/fitness/api/fitnessSdk/WorkoutType;Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching;)V", "getCoaching", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching;", "getSessionId", "()Ljava/util/UUID;", MetricsConstants.OperationalMetrics.GET_USER_PROFILE, "()Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "getWorkoutType", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/WorkoutType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionConfiguration implements Serializable {
    @NotNull
    private final Coaching coaching;
    @NotNull
    private final UUID sessionId;
    @Nullable
    private final UserProfile userProfile;
    @NotNull
    private final WorkoutType workoutType;

    public SessionConfiguration(@NotNull UUID sessionId, @Nullable UserProfile userProfile, @NotNull WorkoutType workoutType, @NotNull Coaching coaching) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(workoutType, "workoutType");
        Intrinsics.checkParameterIsNotNull(coaching, "coaching");
        this.sessionId = sessionId;
        this.userProfile = userProfile;
        this.workoutType = workoutType;
        this.coaching = coaching;
    }

    public static /* synthetic */ SessionConfiguration copy$default(SessionConfiguration sessionConfiguration, UUID uuid, UserProfile userProfile, WorkoutType workoutType, Coaching coaching, int i, Object obj) {
        if ((i & 1) != 0) {
            uuid = sessionConfiguration.sessionId;
        }
        if ((i & 2) != 0) {
            userProfile = sessionConfiguration.userProfile;
        }
        if ((i & 4) != 0) {
            workoutType = sessionConfiguration.workoutType;
        }
        if ((i & 8) != 0) {
            coaching = sessionConfiguration.coaching;
        }
        return sessionConfiguration.copy(uuid, userProfile, workoutType, coaching);
    }

    @NotNull
    public final UUID component1() {
        return this.sessionId;
    }

    @Nullable
    public final UserProfile component2() {
        return this.userProfile;
    }

    @NotNull
    public final WorkoutType component3() {
        return this.workoutType;
    }

    @NotNull
    public final Coaching component4() {
        return this.coaching;
    }

    @NotNull
    public final SessionConfiguration copy(@NotNull UUID sessionId, @Nullable UserProfile userProfile, @NotNull WorkoutType workoutType, @NotNull Coaching coaching) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(workoutType, "workoutType");
        Intrinsics.checkParameterIsNotNull(coaching, "coaching");
        return new SessionConfiguration(sessionId, userProfile, workoutType, coaching);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SessionConfiguration)) {
                return false;
            }
            SessionConfiguration sessionConfiguration = (SessionConfiguration) obj;
            return Intrinsics.areEqual(this.sessionId, sessionConfiguration.sessionId) && Intrinsics.areEqual(this.userProfile, sessionConfiguration.userProfile) && Intrinsics.areEqual(this.workoutType, sessionConfiguration.workoutType) && Intrinsics.areEqual(this.coaching, sessionConfiguration.coaching);
        }
        return true;
    }

    @NotNull
    public final Coaching getCoaching() {
        return this.coaching;
    }

    @NotNull
    public final UUID getSessionId() {
        return this.sessionId;
    }

    @Nullable
    public final UserProfile getUserProfile() {
        return this.userProfile;
    }

    @NotNull
    public final WorkoutType getWorkoutType() {
        return this.workoutType;
    }

    public int hashCode() {
        UUID uuid = this.sessionId;
        int i = 0;
        int hashCode = (uuid != null ? uuid.hashCode() : 0) * 31;
        UserProfile userProfile = this.userProfile;
        int hashCode2 = (hashCode + (userProfile != null ? userProfile.hashCode() : 0)) * 31;
        WorkoutType workoutType = this.workoutType;
        int hashCode3 = (hashCode2 + (workoutType != null ? workoutType.hashCode() : 0)) * 31;
        Coaching coaching = this.coaching;
        if (coaching != null) {
            i = coaching.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SessionConfiguration(sessionId=");
        outline107.append(this.sessionId);
        outline107.append(", userProfile=");
        outline107.append(this.userProfile);
        outline107.append(", workoutType=");
        outline107.append(this.workoutType);
        outline107.append(", coaching=");
        outline107.append(this.coaching);
        outline107.append(")");
        return outline107.toString();
    }
}
