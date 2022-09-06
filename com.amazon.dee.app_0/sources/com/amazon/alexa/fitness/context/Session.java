package com.amazon.alexa.fitness.context;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaFitnessContextProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/context/Session;", "", "id", "Ljava/util/UUID;", "startTime", "", "endTime", "(Ljava/util/UUID;Ljava/lang/String;Ljava/lang/String;)V", "getEndTime", "()Ljava/lang/String;", "getId", "()Ljava/util/UUID;", "getStartTime", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Session {
    @Nullable
    private final String endTime;
    @NotNull
    private final UUID id;
    @NotNull
    private final String startTime;

    public Session(@NotNull UUID id, @NotNull String startTime, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(startTime, "startTime");
        this.id = id;
        this.startTime = startTime;
        this.endTime = str;
    }

    public static /* synthetic */ Session copy$default(Session session, UUID uuid, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            uuid = session.id;
        }
        if ((i & 2) != 0) {
            str = session.startTime;
        }
        if ((i & 4) != 0) {
            str2 = session.endTime;
        }
        return session.copy(uuid, str, str2);
    }

    @NotNull
    public final UUID component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.startTime;
    }

    @Nullable
    public final String component3() {
        return this.endTime;
    }

    @NotNull
    public final Session copy(@NotNull UUID id, @NotNull String startTime, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(startTime, "startTime");
        return new Session(id, startTime, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Session)) {
                return false;
            }
            Session session = (Session) obj;
            return Intrinsics.areEqual(this.id, session.id) && Intrinsics.areEqual(this.startTime, session.startTime) && Intrinsics.areEqual(this.endTime, session.endTime);
        }
        return true;
    }

    @Nullable
    public final String getEndTime() {
        return this.endTime;
    }

    @NotNull
    public final UUID getId() {
        return this.id;
    }

    @NotNull
    public final String getStartTime() {
        return this.startTime;
    }

    public int hashCode() {
        UUID uuid = this.id;
        int i = 0;
        int hashCode = (uuid != null ? uuid.hashCode() : 0) * 31;
        String str = this.startTime;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.endTime;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session(id=");
        outline107.append(this.id);
        outline107.append(", startTime=");
        outline107.append(this.startTime);
        outline107.append(", endTime=");
        return GeneratedOutlineSupport1.outline91(outline107, this.endTime, ")");
    }
}
