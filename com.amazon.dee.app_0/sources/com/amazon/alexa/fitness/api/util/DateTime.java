package com.amazon.alexa.fitness.api.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DateTime.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0011\u0010\r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0086\u0002J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0086\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/api/util/DateTime;", "Ljava/io/Serializable;", "epochMilli", "", "(J)V", "getEpochMilli", "()J", "equals", "", "other", "", "hashCode", "", "minus", "Lcom/amazon/alexa/fitness/api/util/TimeInterval;", "plus", "toString", "", "until", "Companion", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DateTime implements Serializable {
    public static final Companion Companion = new Companion(null);
    private final long epochMilli;

    /* compiled from: DateTime.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/api/util/DateTime$Companion;", "", "()V", "now", "Lcom/amazon/alexa/fitness/api/util/DateTime;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final DateTime now() {
            return new DateTime(System.currentTimeMillis());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DateTime(long j) {
        this.epochMilli = j;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(DateTime.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.util.DateTime");
        }
        return this.epochMilli == ((DateTime) obj).epochMilli;
    }

    public final long getEpochMilli() {
        return this.epochMilli;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.epochMilli));
    }

    @NotNull
    public final DateTime minus(@NotNull TimeInterval other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new DateTime(this.epochMilli - other.getMilli());
    }

    @NotNull
    public final DateTime plus(@NotNull TimeInterval other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new DateTime(other.getMilli() + this.epochMilli);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DateTime(epochMilli=");
        outline107.append(this.epochMilli);
        outline107.append(')');
        return outline107.toString();
    }

    @NotNull
    public final TimeInterval until(@NotNull DateTime other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new TimeInterval(other.epochMilli - this.epochMilli);
    }
}
