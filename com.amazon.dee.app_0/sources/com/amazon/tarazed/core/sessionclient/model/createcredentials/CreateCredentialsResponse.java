package com.amazon.tarazed.core.sessionclient.model.createcredentials;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CreateCredentialsResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$BE\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eB+\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\u000fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J7\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", "", "seen1", "", "durationSeconds", "loggingCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials;", "signalingCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;", "mediaCredentials", "", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/MediaCredential;", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(IILcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials;Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;Ljava/util/List;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(ILcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials;Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;Ljava/util/List;)V", "getDurationSeconds", "()I", "getLoggingCredentials", "()Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials;", "getMediaCredentials", "()Ljava/util/List;", "getSignalingCredentials", "()Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class CreateCredentialsResponse {
    public static final Companion Companion = new Companion(null);
    private final int durationSeconds;
    @NotNull
    private final LoggingCredentials loggingCredentials;
    @NotNull
    private final List<MediaCredential> mediaCredentials;
    @NotNull
    private final SignalingCredentials signalingCredentials;

    /* compiled from: CreateCredentialsResponse.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<CreateCredentialsResponse> serializer() {
            return CreateCredentialsResponse$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CreateCredentialsResponse(int i, int i2, @Nullable LoggingCredentials loggingCredentials, @Nullable SignalingCredentials signalingCredentials, @Nullable List<MediaCredential> list, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.durationSeconds = i2;
            if ((i & 2) == 0) {
                throw new MissingFieldException("loggingCredentials");
            }
            this.loggingCredentials = loggingCredentials;
            if ((i & 4) == 0) {
                throw new MissingFieldException("signalingCredentials");
            }
            this.signalingCredentials = signalingCredentials;
            if ((i & 8) == 0) {
                throw new MissingFieldException("mediaCredentials");
            }
            this.mediaCredentials = list;
            return;
        }
        throw new MissingFieldException("durationSeconds");
    }

    public CreateCredentialsResponse(int i, @NotNull LoggingCredentials loggingCredentials, @NotNull SignalingCredentials signalingCredentials, @NotNull List<MediaCredential> mediaCredentials) {
        Intrinsics.checkParameterIsNotNull(loggingCredentials, "loggingCredentials");
        Intrinsics.checkParameterIsNotNull(signalingCredentials, "signalingCredentials");
        Intrinsics.checkParameterIsNotNull(mediaCredentials, "mediaCredentials");
        this.durationSeconds = i;
        this.loggingCredentials = loggingCredentials;
        this.signalingCredentials = signalingCredentials;
        this.mediaCredentials = mediaCredentials;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CreateCredentialsResponse copy$default(CreateCredentialsResponse createCredentialsResponse, int i, LoggingCredentials loggingCredentials, SignalingCredentials signalingCredentials, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = createCredentialsResponse.durationSeconds;
        }
        if ((i2 & 2) != 0) {
            loggingCredentials = createCredentialsResponse.loggingCredentials;
        }
        if ((i2 & 4) != 0) {
            signalingCredentials = createCredentialsResponse.signalingCredentials;
        }
        if ((i2 & 8) != 0) {
            list = createCredentialsResponse.mediaCredentials;
        }
        return createCredentialsResponse.copy(i, loggingCredentials, signalingCredentials, list);
    }

    @JvmStatic
    public static final void write$Self(@NotNull CreateCredentialsResponse self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeIntElement(serialDesc, 0, self.durationSeconds);
        output.encodeSerializableElement(serialDesc, 1, LoggingCredentials$$serializer.INSTANCE, self.loggingCredentials);
        output.encodeSerializableElement(serialDesc, 2, SignalingCredentials$$serializer.INSTANCE, self.signalingCredentials);
        output.encodeSerializableElement(serialDesc, 3, new ArrayListSerializer(MediaCredential$$serializer.INSTANCE), self.mediaCredentials);
    }

    public final int component1() {
        return this.durationSeconds;
    }

    @NotNull
    public final LoggingCredentials component2() {
        return this.loggingCredentials;
    }

    @NotNull
    public final SignalingCredentials component3() {
        return this.signalingCredentials;
    }

    @NotNull
    public final List<MediaCredential> component4() {
        return this.mediaCredentials;
    }

    @NotNull
    public final CreateCredentialsResponse copy(int i, @NotNull LoggingCredentials loggingCredentials, @NotNull SignalingCredentials signalingCredentials, @NotNull List<MediaCredential> mediaCredentials) {
        Intrinsics.checkParameterIsNotNull(loggingCredentials, "loggingCredentials");
        Intrinsics.checkParameterIsNotNull(signalingCredentials, "signalingCredentials");
        Intrinsics.checkParameterIsNotNull(mediaCredentials, "mediaCredentials");
        return new CreateCredentialsResponse(i, loggingCredentials, signalingCredentials, mediaCredentials);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof CreateCredentialsResponse)) {
                return false;
            }
            CreateCredentialsResponse createCredentialsResponse = (CreateCredentialsResponse) obj;
            return this.durationSeconds == createCredentialsResponse.durationSeconds && Intrinsics.areEqual(this.loggingCredentials, createCredentialsResponse.loggingCredentials) && Intrinsics.areEqual(this.signalingCredentials, createCredentialsResponse.signalingCredentials) && Intrinsics.areEqual(this.mediaCredentials, createCredentialsResponse.mediaCredentials);
        }
        return true;
    }

    public final int getDurationSeconds() {
        return this.durationSeconds;
    }

    @NotNull
    public final LoggingCredentials getLoggingCredentials() {
        return this.loggingCredentials;
    }

    @NotNull
    public final List<MediaCredential> getMediaCredentials() {
        return this.mediaCredentials;
    }

    @NotNull
    public final SignalingCredentials getSignalingCredentials() {
        return this.signalingCredentials;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.durationSeconds) * 31;
        LoggingCredentials loggingCredentials = this.loggingCredentials;
        int i = 0;
        int hashCode2 = (hashCode + (loggingCredentials != null ? loggingCredentials.hashCode() : 0)) * 31;
        SignalingCredentials signalingCredentials = this.signalingCredentials;
        int hashCode3 = (hashCode2 + (signalingCredentials != null ? signalingCredentials.hashCode() : 0)) * 31;
        List<MediaCredential> list = this.mediaCredentials;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateCredentialsResponse(durationSeconds=");
        outline107.append(this.durationSeconds);
        outline107.append(", loggingCredentials=");
        outline107.append(this.loggingCredentials);
        outline107.append(", signalingCredentials=");
        outline107.append(this.signalingCredentials);
        outline107.append(", mediaCredentials=");
        return GeneratedOutlineSupport1.outline95(outline107, this.mediaCredentials, ")");
    }
}
