package com.amazon.alexa.fitness.model.event;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UserProfileDTO.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J>\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/fitness/model/event/UserProfileUpdateDTO;", "", "heightInCentimeters", "", "weightInKilograms", "gender", "", "birthday", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;)V", "getBirthday", "()Ljava/lang/String;", "getGender", "getHeightInCentimeters", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getWeightInKilograms", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;)Lcom/amazon/alexa/fitness/model/event/UserProfileUpdateDTO;", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class UserProfileUpdateDTO {
    @Nullable
    private final String birthday;
    @Nullable
    private final String gender;
    @Nullable
    private final Double heightInCentimeters;
    @Nullable
    private final Double weightInKilograms;

    public UserProfileUpdateDTO(@Nullable Double d, @Nullable Double d2, @Nullable String str, @Nullable String str2) {
        this.heightInCentimeters = d;
        this.weightInKilograms = d2;
        this.gender = str;
        this.birthday = str2;
    }

    public static /* synthetic */ UserProfileUpdateDTO copy$default(UserProfileUpdateDTO userProfileUpdateDTO, Double d, Double d2, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = userProfileUpdateDTO.heightInCentimeters;
        }
        if ((i & 2) != 0) {
            d2 = userProfileUpdateDTO.weightInKilograms;
        }
        if ((i & 4) != 0) {
            str = userProfileUpdateDTO.gender;
        }
        if ((i & 8) != 0) {
            str2 = userProfileUpdateDTO.birthday;
        }
        return userProfileUpdateDTO.copy(d, d2, str, str2);
    }

    @Nullable
    public final Double component1() {
        return this.heightInCentimeters;
    }

    @Nullable
    public final Double component2() {
        return this.weightInKilograms;
    }

    @Nullable
    public final String component3() {
        return this.gender;
    }

    @Nullable
    public final String component4() {
        return this.birthday;
    }

    @NotNull
    public final UserProfileUpdateDTO copy(@Nullable Double d, @Nullable Double d2, @Nullable String str, @Nullable String str2) {
        return new UserProfileUpdateDTO(d, d2, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UserProfileUpdateDTO)) {
                return false;
            }
            UserProfileUpdateDTO userProfileUpdateDTO = (UserProfileUpdateDTO) obj;
            return Intrinsics.areEqual((Object) this.heightInCentimeters, (Object) userProfileUpdateDTO.heightInCentimeters) && Intrinsics.areEqual((Object) this.weightInKilograms, (Object) userProfileUpdateDTO.weightInKilograms) && Intrinsics.areEqual(this.gender, userProfileUpdateDTO.gender) && Intrinsics.areEqual(this.birthday, userProfileUpdateDTO.birthday);
        }
        return true;
    }

    @Nullable
    public final String getBirthday() {
        return this.birthday;
    }

    @Nullable
    public final String getGender() {
        return this.gender;
    }

    @Nullable
    public final Double getHeightInCentimeters() {
        return this.heightInCentimeters;
    }

    @Nullable
    public final Double getWeightInKilograms() {
        return this.weightInKilograms;
    }

    public int hashCode() {
        Double d = this.heightInCentimeters;
        int i = 0;
        int hashCode = (d != null ? d.hashCode() : 0) * 31;
        Double d2 = this.weightInKilograms;
        int hashCode2 = (hashCode + (d2 != null ? d2.hashCode() : 0)) * 31;
        String str = this.gender;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.birthday;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UserProfileUpdateDTO(heightInCentimeters=");
        outline107.append(this.heightInCentimeters);
        outline107.append(", weightInKilograms=");
        outline107.append(this.weightInKilograms);
        outline107.append(", gender=");
        outline107.append(this.gender);
        outline107.append(", birthday=");
        return GeneratedOutlineSupport1.outline91(outline107, this.birthday, ")");
    }
}
