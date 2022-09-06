package com.amazon.tarazed.core.webrtc.signals;

import com.amazon.rtcsc.android.typedapi.types.WebRTCHandshake;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Answer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0081\b\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\u0006\u0010\u0013\u001a\u00020\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/signals/Answer;", "", "seen1", "", WebRTCSignalFormats.SDP, "", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/lang/String;)V", "getSdp", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "toWebRTCHandshake", "Lcom/amazon/rtcsc/android/typedapi/types/WebRTCHandshake;", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class Answer {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String sdp;

    /* compiled from: Answer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/signals/Answer$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/webrtc/signals/Answer;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<Answer> serializer() {
            return Answer$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Answer(int i, @Nullable String str, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.sdp = str;
            return;
        }
        throw new MissingFieldException(WebRTCSignalFormats.SDP);
    }

    public Answer(@NotNull String sdp) {
        Intrinsics.checkParameterIsNotNull(sdp, "sdp");
        this.sdp = sdp;
    }

    public static /* synthetic */ Answer copy$default(Answer answer, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = answer.sdp;
        }
        return answer.copy(str);
    }

    @JvmStatic
    public static final void write$Self(@NotNull Answer self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.sdp);
    }

    @NotNull
    public final String component1() {
        return this.sdp;
    }

    @NotNull
    public final Answer copy(@NotNull String sdp) {
        Intrinsics.checkParameterIsNotNull(sdp, "sdp");
        return new Answer(sdp);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof Answer) && Intrinsics.areEqual(this.sdp, ((Answer) obj).sdp);
        }
        return true;
    }

    @NotNull
    public final String getSdp() {
        return this.sdp;
    }

    public int hashCode() {
        String str = this.sdp;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Answer(sdp="), this.sdp, ")");
    }

    @NotNull
    public final WebRTCHandshake toWebRTCHandshake() {
        return new WebRTCHandshake(WebRTCSignalFormats.SDP, this.sdp);
    }
}
