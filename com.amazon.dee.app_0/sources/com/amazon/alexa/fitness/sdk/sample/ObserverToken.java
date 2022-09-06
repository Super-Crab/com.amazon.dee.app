package com.amazon.alexa.fitness.sdk.sample;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SampleStore.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/sample/ObserverToken;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ObserverToken {
    @NotNull
    private final String id;

    public ObserverToken() {
        this(null, 1, null);
    }

    public ObserverToken(@NotNull String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.id = id;
    }

    public static /* synthetic */ ObserverToken copy$default(ObserverToken observerToken, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = observerToken.id;
        }
        return observerToken.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final ObserverToken copy(@NotNull String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        return new ObserverToken(id);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof ObserverToken) && Intrinsics.areEqual(this.id, ((ObserverToken) obj).id);
        }
        return true;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        String str = this.id;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("ObserverToken(id="), this.id, ")");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ ObserverToken(java.lang.String r1, int r2, kotlin.jvm.internal.DefaultConstructorMarker r3) {
        /*
            r0 = this;
            r2 = r2 & 1
            if (r2 == 0) goto L11
            java.util.UUID r1 = java.util.UUID.randomUUID()
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "UUID.randomUUID().toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
        L11:
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.sdk.sample.ObserverToken.<init>(java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
