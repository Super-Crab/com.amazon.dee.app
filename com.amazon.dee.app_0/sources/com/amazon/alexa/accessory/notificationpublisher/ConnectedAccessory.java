package com.amazon.alexa.accessory.notificationpublisher;

import com.amazon.alexa.accessory.protocol.Input;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ConnectedAccessory.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J/\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/accessory/notificationpublisher/ConnectedAccessory;", "", "deviceType", "", "vipFilterCapableValue", "", "configList", "", "Lcom/amazon/alexa/accessory/protocol/Input$InputBehaviorConfiguration;", "(Ljava/lang/String;ILjava/util/List;)V", "getConfigList", "()Ljava/util/List;", "setConfigList", "(Ljava/util/List;)V", "getDeviceType", "()Ljava/lang/String;", "setDeviceType", "(Ljava/lang/String;)V", "getVipFilterCapableValue", "()I", "setVipFilterCapableValue", "(I)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "AlexaAccessoryNotificationPublisherAndroid_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ConnectedAccessory {
    @Nullable
    private List<Input.InputBehaviorConfiguration> configList;
    @NotNull
    private String deviceType;
    private int vipFilterCapableValue;

    public ConnectedAccessory(@NotNull String deviceType, int i, @Nullable List<Input.InputBehaviorConfiguration> list) {
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        this.deviceType = deviceType;
        this.vipFilterCapableValue = i;
        this.configList = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ConnectedAccessory copy$default(ConnectedAccessory connectedAccessory, String str, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = connectedAccessory.deviceType;
        }
        if ((i2 & 2) != 0) {
            i = connectedAccessory.vipFilterCapableValue;
        }
        if ((i2 & 4) != 0) {
            list = connectedAccessory.configList;
        }
        return connectedAccessory.copy(str, i, list);
    }

    @NotNull
    public final String component1() {
        return this.deviceType;
    }

    public final int component2() {
        return this.vipFilterCapableValue;
    }

    @Nullable
    public final List<Input.InputBehaviorConfiguration> component3() {
        return this.configList;
    }

    @NotNull
    public final ConnectedAccessory copy(@NotNull String deviceType, int i, @Nullable List<Input.InputBehaviorConfiguration> list) {
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        return new ConnectedAccessory(deviceType, i, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ConnectedAccessory)) {
                return false;
            }
            ConnectedAccessory connectedAccessory = (ConnectedAccessory) obj;
            return Intrinsics.areEqual(this.deviceType, connectedAccessory.deviceType) && this.vipFilterCapableValue == connectedAccessory.vipFilterCapableValue && Intrinsics.areEqual(this.configList, connectedAccessory.configList);
        }
        return true;
    }

    @Nullable
    public final List<Input.InputBehaviorConfiguration> getConfigList() {
        return this.configList;
    }

    @NotNull
    public final String getDeviceType() {
        return this.deviceType;
    }

    public final int getVipFilterCapableValue() {
        return this.vipFilterCapableValue;
    }

    public int hashCode() {
        String str = this.deviceType;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.vipFilterCapableValue) * 31;
        List<Input.InputBehaviorConfiguration> list = this.configList;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public final void setConfigList(@Nullable List<Input.InputBehaviorConfiguration> list) {
        this.configList = list;
    }

    public final void setDeviceType(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceType = str;
    }

    public final void setVipFilterCapableValue(int i) {
        this.vipFilterCapableValue = i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ConnectedAccessory(deviceType=");
        outline107.append(this.deviceType);
        outline107.append(", vipFilterCapableValue=");
        outline107.append(this.vipFilterCapableValue);
        outline107.append(", configList=");
        return GeneratedOutlineSupport1.outline95(outline107, this.configList, ")");
    }
}
