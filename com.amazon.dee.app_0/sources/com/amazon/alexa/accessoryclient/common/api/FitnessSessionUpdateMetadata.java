package com.amazon.alexa.accessoryclient.common.api;

import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionUpdateMetadata.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/api/FitnessSessionUpdateMetadata;", "", "origin", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSessionUpdate$Origin;", "fitnessSession", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSession;", "metadataUuid", "", "(Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSessionUpdate$Origin;Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSession;Ljava/lang/String;)V", "getFitnessSession", "()Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSession;", "getMetadataUuid", "()Ljava/lang/String;", "getOrigin", "()Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSessionUpdate$Origin;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class FitnessSessionUpdateMetadata {
    @NotNull
    private final FitnessSession fitnessSession;
    @NotNull
    private final String metadataUuid;
    @NotNull
    private final FitnessSessionUpdate.Origin origin;

    public FitnessSessionUpdateMetadata(@NotNull FitnessSessionUpdate.Origin origin, @NotNull FitnessSession fitnessSession, @NotNull String metadataUuid) {
        Intrinsics.checkParameterIsNotNull(origin, "origin");
        Intrinsics.checkParameterIsNotNull(fitnessSession, "fitnessSession");
        Intrinsics.checkParameterIsNotNull(metadataUuid, "metadataUuid");
        this.origin = origin;
        this.fitnessSession = fitnessSession;
        this.metadataUuid = metadataUuid;
    }

    public static /* synthetic */ FitnessSessionUpdateMetadata copy$default(FitnessSessionUpdateMetadata fitnessSessionUpdateMetadata, FitnessSessionUpdate.Origin origin, FitnessSession fitnessSession, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            origin = fitnessSessionUpdateMetadata.origin;
        }
        if ((i & 2) != 0) {
            fitnessSession = fitnessSessionUpdateMetadata.fitnessSession;
        }
        if ((i & 4) != 0) {
            str = fitnessSessionUpdateMetadata.metadataUuid;
        }
        return fitnessSessionUpdateMetadata.copy(origin, fitnessSession, str);
    }

    @NotNull
    public final FitnessSessionUpdate.Origin component1() {
        return this.origin;
    }

    @NotNull
    public final FitnessSession component2() {
        return this.fitnessSession;
    }

    @NotNull
    public final String component3() {
        return this.metadataUuid;
    }

    @NotNull
    public final FitnessSessionUpdateMetadata copy(@NotNull FitnessSessionUpdate.Origin origin, @NotNull FitnessSession fitnessSession, @NotNull String metadataUuid) {
        Intrinsics.checkParameterIsNotNull(origin, "origin");
        Intrinsics.checkParameterIsNotNull(fitnessSession, "fitnessSession");
        Intrinsics.checkParameterIsNotNull(metadataUuid, "metadataUuid");
        return new FitnessSessionUpdateMetadata(origin, fitnessSession, metadataUuid);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof FitnessSessionUpdateMetadata)) {
                return false;
            }
            FitnessSessionUpdateMetadata fitnessSessionUpdateMetadata = (FitnessSessionUpdateMetadata) obj;
            return Intrinsics.areEqual(this.origin, fitnessSessionUpdateMetadata.origin) && Intrinsics.areEqual(this.fitnessSession, fitnessSessionUpdateMetadata.fitnessSession) && Intrinsics.areEqual(this.metadataUuid, fitnessSessionUpdateMetadata.metadataUuid);
        }
        return true;
    }

    @NotNull
    public final FitnessSession getFitnessSession() {
        return this.fitnessSession;
    }

    @NotNull
    public final String getMetadataUuid() {
        return this.metadataUuid;
    }

    @NotNull
    public final FitnessSessionUpdate.Origin getOrigin() {
        return this.origin;
    }

    public int hashCode() {
        FitnessSessionUpdate.Origin origin = this.origin;
        int i = 0;
        int hashCode = (origin != null ? origin.hashCode() : 0) * 31;
        FitnessSession fitnessSession = this.fitnessSession;
        int hashCode2 = (hashCode + (fitnessSession != null ? fitnessSession.hashCode() : 0)) * 31;
        String str = this.metadataUuid;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessSessionUpdateMetadata(origin=");
        outline107.append(this.origin);
        outline107.append(", fitnessSession=");
        outline107.append(this.fitnessSession);
        outline107.append(", metadataUuid=");
        return GeneratedOutlineSupport1.outline91(outline107, this.metadataUuid, ")");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ FitnessSessionUpdateMetadata(com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate.Origin r1, com.amazon.alexa.accessory.repositories.fitness.FitnessSession r2, java.lang.String r3, int r4, kotlin.jvm.internal.DefaultConstructorMarker r5) {
        /*
            r0 = this;
            r4 = r4 & 4
            if (r4 == 0) goto L11
            java.util.UUID r3 = java.util.UUID.randomUUID()
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "UUID.randomUUID().toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
        L11:
            r0.<init>(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessoryclient.common.api.FitnessSessionUpdateMetadata.<init>(com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate$Origin, com.amazon.alexa.accessory.repositories.fitness.FitnessSession, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
