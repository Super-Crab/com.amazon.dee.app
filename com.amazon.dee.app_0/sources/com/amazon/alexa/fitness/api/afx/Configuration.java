package com.amazon.alexa.fitness.api.afx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/Configuration;", "", "target", "Lcom/amazon/alexa/fitness/api/afx/Target;", "coaching", "Lcom/amazon/alexa/fitness/api/afx/Coaching;", "(Lcom/amazon/alexa/fitness/api/afx/Target;Lcom/amazon/alexa/fitness/api/afx/Coaching;)V", "getCoaching", "()Lcom/amazon/alexa/fitness/api/afx/Coaching;", "getTarget", "()Lcom/amazon/alexa/fitness/api/afx/Target;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Configuration {
    @Nullable
    private final Coaching coaching;
    @Nullable
    private final Target target;

    public Configuration(@Nullable Target target, @Nullable Coaching coaching) {
        this.target = target;
        this.coaching = coaching;
    }

    public static /* synthetic */ Configuration copy$default(Configuration configuration, Target target, Coaching coaching, int i, Object obj) {
        if ((i & 1) != 0) {
            target = configuration.target;
        }
        if ((i & 2) != 0) {
            coaching = configuration.coaching;
        }
        return configuration.copy(target, coaching);
    }

    @Nullable
    public final Target component1() {
        return this.target;
    }

    @Nullable
    public final Coaching component2() {
        return this.coaching;
    }

    @NotNull
    public final Configuration copy(@Nullable Target target, @Nullable Coaching coaching) {
        return new Configuration(target, coaching);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Configuration)) {
                return false;
            }
            Configuration configuration = (Configuration) obj;
            return Intrinsics.areEqual(this.target, configuration.target) && Intrinsics.areEqual(this.coaching, configuration.coaching);
        }
        return true;
    }

    @Nullable
    public final Coaching getCoaching() {
        return this.coaching;
    }

    @Nullable
    public final Target getTarget() {
        return this.target;
    }

    public int hashCode() {
        Target target = this.target;
        int i = 0;
        int hashCode = (target != null ? target.hashCode() : 0) * 31;
        Coaching coaching = this.coaching;
        if (coaching != null) {
            i = coaching.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Configuration(target=");
        outline107.append(this.target);
        outline107.append(", coaching=");
        outline107.append(this.coaching);
        outline107.append(")");
        return outline107.toString();
    }
}
