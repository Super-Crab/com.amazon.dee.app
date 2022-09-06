package io.ktor.http;

import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Ranges.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lio/ktor/http/ContentRange;", "", "()V", "Bounded", "Suffix", "TailFrom", "Lio/ktor/http/ContentRange$Bounded;", "Lio/ktor/http/ContentRange$TailFrom;", "Lio/ktor/http/ContentRange$Suffix;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class ContentRange {

    /* compiled from: Ranges.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lio/ktor/http/ContentRange$Bounded;", "Lio/ktor/http/ContentRange;", ADMConstants.EXTRA_FROM, "", "to", "(JJ)V", "getFrom", "()J", "getTo", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Bounded extends ContentRange {
        private final long from;
        private final long to;

        public Bounded(long j, long j2) {
            super(null);
            this.from = j;
            this.to = j2;
        }

        @NotNull
        public static /* synthetic */ Bounded copy$default(Bounded bounded, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = bounded.from;
            }
            if ((i & 2) != 0) {
                j2 = bounded.to;
            }
            return bounded.copy(j, j2);
        }

        public final long component1() {
            return this.from;
        }

        public final long component2() {
            return this.to;
        }

        @NotNull
        public final Bounded copy(long j, long j2) {
            return new Bounded(j, j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof Bounded) {
                    Bounded bounded = (Bounded) obj;
                    if (this.from == bounded.from) {
                        if (this.to == bounded.to) {
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public final long getFrom() {
            return this.from;
        }

        public final long getTo() {
            return this.to;
        }

        public int hashCode() {
            long j = this.from;
            long j2 = this.to;
            return (((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2));
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.from);
            sb.append('-');
            sb.append(this.to);
            return sb.toString();
        }
    }

    /* compiled from: Ranges.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/ktor/http/ContentRange$Suffix;", "Lio/ktor/http/ContentRange;", "lastCount", "", "(J)V", "getLastCount", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Suffix extends ContentRange {
        private final long lastCount;

        public Suffix(long j) {
            super(null);
            this.lastCount = j;
        }

        @NotNull
        public static /* synthetic */ Suffix copy$default(Suffix suffix, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = suffix.lastCount;
            }
            return suffix.copy(j);
        }

        public final long component1() {
            return this.lastCount;
        }

        @NotNull
        public final Suffix copy(long j) {
            return new Suffix(j);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof Suffix) {
                    if (this.lastCount == ((Suffix) obj).lastCount) {
                    }
                }
                return false;
            }
            return true;
        }

        public final long getLastCount() {
            return this.lastCount;
        }

        public int hashCode() {
            long j = this.lastCount;
            return (int) (j ^ (j >>> 32));
        }

        @NotNull
        public String toString() {
            StringBuilder outline104 = GeneratedOutlineSupport1.outline104('-');
            outline104.append(this.lastCount);
            return outline104.toString();
        }
    }

    /* compiled from: Ranges.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/ktor/http/ContentRange$TailFrom;", "Lio/ktor/http/ContentRange;", ADMConstants.EXTRA_FROM, "", "(J)V", "getFrom", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class TailFrom extends ContentRange {
        private final long from;

        public TailFrom(long j) {
            super(null);
            this.from = j;
        }

        @NotNull
        public static /* synthetic */ TailFrom copy$default(TailFrom tailFrom, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = tailFrom.from;
            }
            return tailFrom.copy(j);
        }

        public final long component1() {
            return this.from;
        }

        @NotNull
        public final TailFrom copy(long j) {
            return new TailFrom(j);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof TailFrom) {
                    if (this.from == ((TailFrom) obj).from) {
                    }
                }
                return false;
            }
            return true;
        }

        public final long getFrom() {
            return this.from;
        }

        public int hashCode() {
            long j = this.from;
            return (int) (j ^ (j >>> 32));
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.from);
            sb.append('-');
            return sb.toString();
        }
    }

    private ContentRange() {
    }

    public /* synthetic */ ContentRange(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
