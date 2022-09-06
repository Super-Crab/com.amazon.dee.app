package com.amazon.alexa.fitness.model.device;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAccessory.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b\u0082\u0001\u0001\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/model/device/FitnessAccessory;", "Ljava/io/Serializable;", "address", "", "transport", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "deviceType", "getDeviceType", "getName", "getTransport", "Lcom/amazon/alexa/fitness/model/device/AlexaFitnessHeadphones;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class FitnessAccessory implements Serializable {
    @NotNull
    private final String address;
    @Nullable
    private final String name;
    @NotNull
    private final String transport;

    private FitnessAccessory(String str, String str2, String str3) {
        this.address = str;
        this.transport = str2;
        this.name = str3;
    }

    @NotNull
    public final String getAddress() {
        return this.address;
    }

    @NotNull
    public abstract String getDeviceType();

    @Nullable
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getTransport() {
        return this.transport;
    }

    public /* synthetic */ FitnessAccessory(String str, String str2, String str3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3);
    }
}
