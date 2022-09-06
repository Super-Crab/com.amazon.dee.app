package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.fitness.api.util.DateTime;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UserProfile.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001%B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0015JB\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015¨\u0006&"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "Ljava/io/Serializable;", "directedId", "", "gender", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile$Gender;", "dateOfBirth", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "heightInCentimetres", "", "weightInKilograms", "(Ljava/lang/String;Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile$Gender;Lcom/amazon/alexa/fitness/api/util/DateTime;DLjava/lang/Double;)V", "getDateOfBirth", "()Lcom/amazon/alexa/fitness/api/util/DateTime;", "getDirectedId", "()Ljava/lang/String;", "getGender", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile$Gender;", "getHeightInCentimetres", "()D", "getWeightInKilograms", "()Ljava/lang/Double;", "Ljava/lang/Double;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile$Gender;Lcom/amazon/alexa/fitness/api/util/DateTime;DLjava/lang/Double;)Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "equals", "", "other", "", "hashCode", "", "toString", "Gender", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class UserProfile implements Serializable {
    @NotNull
    private final DateTime dateOfBirth;
    @NotNull
    private final String directedId;
    @NotNull
    private final Gender gender;
    private final double heightInCentimetres;
    @Nullable
    private final Double weightInKilograms;

    /* compiled from: UserProfile.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile$Gender;", "", "(Ljava/lang/String;I)V", "MALE", "FEMALE", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public enum Gender {
        MALE,
        FEMALE
    }

    public UserProfile(@NotNull String directedId, @NotNull Gender gender, @NotNull DateTime dateOfBirth, double d, @Nullable Double d2) {
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        Intrinsics.checkParameterIsNotNull(gender, "gender");
        Intrinsics.checkParameterIsNotNull(dateOfBirth, "dateOfBirth");
        this.directedId = directedId;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.heightInCentimetres = d;
        this.weightInKilograms = d2;
    }

    public static /* synthetic */ UserProfile copy$default(UserProfile userProfile, String str, Gender gender, DateTime dateTime, double d, Double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userProfile.directedId;
        }
        if ((i & 2) != 0) {
            gender = userProfile.gender;
        }
        Gender gender2 = gender;
        if ((i & 4) != 0) {
            dateTime = userProfile.dateOfBirth;
        }
        DateTime dateTime2 = dateTime;
        if ((i & 8) != 0) {
            d = userProfile.heightInCentimetres;
        }
        double d3 = d;
        if ((i & 16) != 0) {
            d2 = userProfile.weightInKilograms;
        }
        return userProfile.copy(str, gender2, dateTime2, d3, d2);
    }

    @NotNull
    public final String component1() {
        return this.directedId;
    }

    @NotNull
    public final Gender component2() {
        return this.gender;
    }

    @NotNull
    public final DateTime component3() {
        return this.dateOfBirth;
    }

    public final double component4() {
        return this.heightInCentimetres;
    }

    @Nullable
    public final Double component5() {
        return this.weightInKilograms;
    }

    @NotNull
    public final UserProfile copy(@NotNull String directedId, @NotNull Gender gender, @NotNull DateTime dateOfBirth, double d, @Nullable Double d2) {
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        Intrinsics.checkParameterIsNotNull(gender, "gender");
        Intrinsics.checkParameterIsNotNull(dateOfBirth, "dateOfBirth");
        return new UserProfile(directedId, gender, dateOfBirth, d, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UserProfile)) {
                return false;
            }
            UserProfile userProfile = (UserProfile) obj;
            return Intrinsics.areEqual(this.directedId, userProfile.directedId) && Intrinsics.areEqual(this.gender, userProfile.gender) && Intrinsics.areEqual(this.dateOfBirth, userProfile.dateOfBirth) && Double.compare(this.heightInCentimetres, userProfile.heightInCentimetres) == 0 && Intrinsics.areEqual((Object) this.weightInKilograms, (Object) userProfile.weightInKilograms);
        }
        return true;
    }

    @NotNull
    public final DateTime getDateOfBirth() {
        return this.dateOfBirth;
    }

    @NotNull
    public final String getDirectedId() {
        return this.directedId;
    }

    @NotNull
    public final Gender getGender() {
        return this.gender;
    }

    public final double getHeightInCentimetres() {
        return this.heightInCentimetres;
    }

    @Nullable
    public final Double getWeightInKilograms() {
        return this.weightInKilograms;
    }

    public int hashCode() {
        String str = this.directedId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Gender gender = this.gender;
        int hashCode2 = (hashCode + (gender != null ? gender.hashCode() : 0)) * 31;
        DateTime dateTime = this.dateOfBirth;
        int hashCode3 = dateTime != null ? dateTime.hashCode() : 0;
        long doubleToLongBits = Double.doubleToLongBits(this.heightInCentimetres);
        int i2 = (((hashCode2 + hashCode3) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        Double d = this.weightInKilograms;
        if (d != null) {
            i = d.hashCode();
        }
        return i2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UserProfile(directedId=");
        outline107.append(this.directedId);
        outline107.append(", gender=");
        outline107.append(this.gender);
        outline107.append(", dateOfBirth=");
        outline107.append(this.dateOfBirth);
        outline107.append(", heightInCentimetres=");
        outline107.append(this.heightInCentimetres);
        outline107.append(", weightInKilograms=");
        outline107.append(this.weightInKilograms);
        outline107.append(")");
        return outline107.toString();
    }
}
