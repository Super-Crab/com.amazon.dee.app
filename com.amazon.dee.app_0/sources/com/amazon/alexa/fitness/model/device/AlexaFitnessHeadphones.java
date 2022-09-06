package com.amazon.alexa.fitness.model.device;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAccessory.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/model/device/AlexaFitnessHeadphones;", "Lcom/amazon/alexa/fitness/model/device/FitnessAccessory;", "address", "", "transport", "deviceType", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeviceType", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AlexaFitnessHeadphones extends FitnessAccessory {
    @NotNull
    private final String deviceType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlexaFitnessHeadphones(@NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4) {
        super(str, str2, str4, null);
        GeneratedOutlineSupport1.outline169(str, "address", str2, "transport", str3, "deviceType");
        this.deviceType = str3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(AlexaFitnessHeadphones.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            AlexaFitnessHeadphones alexaFitnessHeadphones = (AlexaFitnessHeadphones) obj;
            return Intrinsics.areEqual(getAddress(), alexaFitnessHeadphones.getAddress()) && Intrinsics.areEqual(getTransport(), alexaFitnessHeadphones.getTransport());
        }
        throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.model.device.AlexaFitnessHeadphones");
    }

    @Override // com.amazon.alexa.fitness.model.device.FitnessAccessory
    @NotNull
    public String getDeviceType() {
        return this.deviceType;
    }

    public int hashCode() {
        return Objects.hash(getAddress(), getTransport());
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceType: ");
        outline107.append(getDeviceType());
        outline107.append(", Address: ");
        outline107.append(getAddress());
        outline107.append(", Transport: ");
        outline107.append(getTransport());
        outline107.append(", Name: ");
        outline107.append(getName());
        return outline107.toString();
    }

    public /* synthetic */ AlexaFitnessHeadphones(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : str4);
    }
}
