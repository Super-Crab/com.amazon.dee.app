package com.amazon.alexa.fitness.api.afx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/Coaching;", "", "coach", "Lcom/amazon/alexa/fitness/api/afx/Name;", "workout", "(Lcom/amazon/alexa/fitness/api/afx/Name;Lcom/amazon/alexa/fitness/api/afx/Name;)V", "getCoach", "()Lcom/amazon/alexa/fitness/api/afx/Name;", "getWorkout", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Coaching {
    @NotNull
    private final Name coach;
    @NotNull
    private final Name workout;

    public Coaching(@NotNull Name coach, @NotNull Name workout) {
        Intrinsics.checkParameterIsNotNull(coach, "coach");
        Intrinsics.checkParameterIsNotNull(workout, "workout");
        this.coach = coach;
        this.workout = workout;
    }

    public static /* synthetic */ Coaching copy$default(Coaching coaching, Name name, Name name2, int i, Object obj) {
        if ((i & 1) != 0) {
            name = coaching.coach;
        }
        if ((i & 2) != 0) {
            name2 = coaching.workout;
        }
        return coaching.copy(name, name2);
    }

    @NotNull
    public final Name component1() {
        return this.coach;
    }

    @NotNull
    public final Name component2() {
        return this.workout;
    }

    @NotNull
    public final Coaching copy(@NotNull Name coach, @NotNull Name workout) {
        Intrinsics.checkParameterIsNotNull(coach, "coach");
        Intrinsics.checkParameterIsNotNull(workout, "workout");
        return new Coaching(coach, workout);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Coaching)) {
                return false;
            }
            Coaching coaching = (Coaching) obj;
            return Intrinsics.areEqual(this.coach, coaching.coach) && Intrinsics.areEqual(this.workout, coaching.workout);
        }
        return true;
    }

    @NotNull
    public final Name getCoach() {
        return this.coach;
    }

    @NotNull
    public final Name getWorkout() {
        return this.workout;
    }

    public int hashCode() {
        Name name = this.coach;
        int i = 0;
        int hashCode = (name != null ? name.hashCode() : 0) * 31;
        Name name2 = this.workout;
        if (name2 != null) {
            i = name2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Coaching(coach=");
        outline107.append(this.coach);
        outline107.append(", workout=");
        outline107.append(this.workout);
        outline107.append(")");
        return outline107.toString();
    }
}
