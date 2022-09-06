package com.amazon.alexa.wakeword.davs;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WakeWordModelArtifactDataPersister.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/ArtifactData;", "", "id", "", "locale", "ecid", "serializedWakeWords", "timestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getEcid", "()Ljava/lang/String;", "getId", "getLocale", "getSerializedWakeWords", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class ArtifactData {
    @NotNull
    private final String ecid;
    @NotNull
    private final String id;
    @NotNull
    private final String locale;
    @NotNull
    private final String serializedWakeWords;
    private final long timestamp;

    public ArtifactData(@NotNull String id, @NotNull String locale, @NotNull String ecid, @NotNull String serializedWakeWords, long j) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(ecid, "ecid");
        Intrinsics.checkParameterIsNotNull(serializedWakeWords, "serializedWakeWords");
        this.id = id;
        this.locale = locale;
        this.ecid = ecid;
        this.serializedWakeWords = serializedWakeWords;
        this.timestamp = j;
    }

    public static /* synthetic */ ArtifactData copy$default(ArtifactData artifactData, String str, String str2, String str3, String str4, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = artifactData.id;
        }
        if ((i & 2) != 0) {
            str2 = artifactData.locale;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = artifactData.ecid;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = artifactData.serializedWakeWords;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            j = artifactData.timestamp;
        }
        return artifactData.copy(str, str5, str6, str7, j);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.locale;
    }

    @NotNull
    public final String component3() {
        return this.ecid;
    }

    @NotNull
    public final String component4() {
        return this.serializedWakeWords;
    }

    public final long component5() {
        return this.timestamp;
    }

    @NotNull
    public final ArtifactData copy(@NotNull String id, @NotNull String locale, @NotNull String ecid, @NotNull String serializedWakeWords, long j) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(ecid, "ecid");
        Intrinsics.checkParameterIsNotNull(serializedWakeWords, "serializedWakeWords");
        return new ArtifactData(id, locale, ecid, serializedWakeWords, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ArtifactData)) {
                return false;
            }
            ArtifactData artifactData = (ArtifactData) obj;
            return Intrinsics.areEqual(this.id, artifactData.id) && Intrinsics.areEqual(this.locale, artifactData.locale) && Intrinsics.areEqual(this.ecid, artifactData.ecid) && Intrinsics.areEqual(this.serializedWakeWords, artifactData.serializedWakeWords) && this.timestamp == artifactData.timestamp;
        }
        return true;
    }

    @NotNull
    public final String getEcid() {
        return this.ecid;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getLocale() {
        return this.locale;
    }

    @NotNull
    public final String getSerializedWakeWords() {
        return this.serializedWakeWords;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.locale;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.ecid;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.serializedWakeWords;
        if (str4 != null) {
            i = str4.hashCode();
        }
        long j = this.timestamp;
        return ((hashCode3 + i) * 31) + ((int) (j ^ (j >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ArtifactData(id=");
        outline107.append(this.id);
        outline107.append(", locale=");
        outline107.append(this.locale);
        outline107.append(", ecid=");
        outline107.append(this.ecid);
        outline107.append(", serializedWakeWords=");
        outline107.append(this.serializedWakeWords);
        outline107.append(", timestamp=");
        return GeneratedOutlineSupport1.outline87(outline107, this.timestamp, ")");
    }
}
