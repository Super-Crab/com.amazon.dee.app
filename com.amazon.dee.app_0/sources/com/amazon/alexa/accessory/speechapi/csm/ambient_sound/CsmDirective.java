package com.amazon.alexa.accessory.speechapi.csm.ambient_sound;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CsmDirective.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/ambient_sound/CsmDirective;", "", "name", "", "namespace", "payload", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getNamespace", "getPayload", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmDirective {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String NAMESPACE_KEY = "namespace";
    @NotNull
    public static final String NAME_KEY = "name";
    @NotNull
    public static final String PAYLOAD_KEY = "payload";
    @NotNull
    private final String name;
    @NotNull
    private final String namespace;
    @NotNull
    private final String payload;

    /* compiled from: CsmDirective.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/ambient_sound/CsmDirective$Companion;", "", "()V", "NAMESPACE_KEY", "", "NAME_KEY", "PAYLOAD_KEY", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CsmDirective(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        GeneratedOutlineSupport1.outline169(str, "name", str2, "namespace", str3, "payload");
        this.name = str;
        this.namespace = str2;
        this.payload = str3;
    }

    public static /* synthetic */ CsmDirective copy$default(CsmDirective csmDirective, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = csmDirective.name;
        }
        if ((i & 2) != 0) {
            str2 = csmDirective.namespace;
        }
        if ((i & 4) != 0) {
            str3 = csmDirective.payload;
        }
        return csmDirective.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.namespace;
    }

    @NotNull
    public final String component3() {
        return this.payload;
    }

    @NotNull
    public final CsmDirective copy(@NotNull String name, @NotNull String namespace, @NotNull String payload) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(namespace, "namespace");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        return new CsmDirective(name, namespace, payload);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof CsmDirective)) {
                return false;
            }
            CsmDirective csmDirective = (CsmDirective) obj;
            return Intrinsics.areEqual(this.name, csmDirective.name) && Intrinsics.areEqual(this.namespace, csmDirective.namespace) && Intrinsics.areEqual(this.payload, csmDirective.payload);
        }
        return true;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getNamespace() {
        return this.namespace;
    }

    @NotNull
    public final String getPayload() {
        return this.payload;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.namespace;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.payload;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmDirective(name=");
        outline107.append(this.name);
        outline107.append(", namespace=");
        outline107.append(this.namespace);
        outline107.append(", payload=");
        return GeneratedOutlineSupport1.outline91(outline107, this.payload, ")");
    }
}
