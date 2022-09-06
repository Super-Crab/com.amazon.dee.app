package com.amazon.tarazed.core.session.sessionEvents;

import amazon.speech.simclient.settings.Settings;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StateChangeEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u0000 \u001a2\u00020\u0001:\u0002\u0019\u001aB7\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/amazon/tarazed/core/session/sessionEvents/StateChangeEvent;", "", "seen1", "", "stateType", "", "state", Settings.ListeningSettings.EXTRA_REASON, "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "getState", "getStateType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class StateChangeEvent {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final String reason;
    @NotNull
    private final String state;
    @NotNull
    private final String stateType;

    /* compiled from: StateChangeEvent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/session/sessionEvents/StateChangeEvent$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/session/sessionEvents/StateChangeEvent;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<StateChangeEvent> serializer() {
            return StateChangeEvent$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public StateChangeEvent(@NotNull String stateType, @NotNull String state, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(stateType, "stateType");
        Intrinsics.checkParameterIsNotNull(state, "state");
        this.stateType = stateType;
        this.state = state;
        this.reason = str;
    }

    public static /* synthetic */ StateChangeEvent copy$default(StateChangeEvent stateChangeEvent, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = stateChangeEvent.stateType;
        }
        if ((i & 2) != 0) {
            str2 = stateChangeEvent.state;
        }
        if ((i & 4) != 0) {
            str3 = stateChangeEvent.reason;
        }
        return stateChangeEvent.copy(str, str2, str3);
    }

    @JvmStatic
    public static final void write$Self(@NotNull StateChangeEvent self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.stateType);
        output.encodeStringElement(serialDesc, 1, self.state);
        if ((!Intrinsics.areEqual(self.reason, (Object) null)) || output.shouldEncodeElementDefault(serialDesc, 2)) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.reason);
        }
    }

    @NotNull
    public final String component1() {
        return this.stateType;
    }

    @NotNull
    public final String component2() {
        return this.state;
    }

    @Nullable
    public final String component3() {
        return this.reason;
    }

    @NotNull
    public final StateChangeEvent copy(@NotNull String stateType, @NotNull String state, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(stateType, "stateType");
        Intrinsics.checkParameterIsNotNull(state, "state");
        return new StateChangeEvent(stateType, state, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof StateChangeEvent)) {
                return false;
            }
            StateChangeEvent stateChangeEvent = (StateChangeEvent) obj;
            return Intrinsics.areEqual(this.stateType, stateChangeEvent.stateType) && Intrinsics.areEqual(this.state, stateChangeEvent.state) && Intrinsics.areEqual(this.reason, stateChangeEvent.reason);
        }
        return true;
    }

    @Nullable
    public final String getReason() {
        return this.reason;
    }

    @NotNull
    public final String getState() {
        return this.state;
    }

    @NotNull
    public final String getStateType() {
        return this.stateType;
    }

    public int hashCode() {
        String str = this.stateType;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.state;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.reason;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StateChangeEvent(stateType=");
        outline107.append(this.stateType);
        outline107.append(", state=");
        outline107.append(this.state);
        outline107.append(", reason=");
        return GeneratedOutlineSupport1.outline91(outline107, this.reason, ")");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ StateChangeEvent(int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.stateType = str;
            if ((i & 2) == 0) {
                throw new MissingFieldException("state");
            }
            this.state = str2;
            if ((i & 4) != 0) {
                this.reason = str3;
                return;
            } else {
                this.reason = null;
                return;
            }
        }
        throw new MissingFieldException("stateType");
    }

    public /* synthetic */ StateChangeEvent(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3);
    }
}
