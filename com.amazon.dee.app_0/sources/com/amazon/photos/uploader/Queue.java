package com.amazon.photos.uploader;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Queue.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/amazon/photos/uploader/Queue;", "", "name", "", "priority", "Lcom/amazon/photos/uploader/Priority;", "constraints", "", "Lcom/amazon/photos/uploader/QueueConstraint;", "(Ljava/lang/String;Lcom/amazon/photos/uploader/Priority;Ljava/util/Set;)V", "getConstraints", "()Ljava/util/Set;", "getName", "()Ljava/lang/String;", "getPriority", "()Lcom/amazon/photos/uploader/Priority;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Queue {
    @NotNull
    private final Set<QueueConstraint> constraints;
    @NotNull
    private final String name;
    @NotNull
    private final Priority priority;

    /* JADX WARN: Multi-variable type inference failed */
    public Queue(@NotNull String name, @NotNull Priority priority, @NotNull Set<? extends QueueConstraint> constraints) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(priority, "priority");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        this.name = name;
        this.priority = priority;
        this.constraints = constraints;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Queue copy$default(Queue queue, String str, Priority priority, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            str = queue.name;
        }
        if ((i & 2) != 0) {
            priority = queue.priority;
        }
        if ((i & 4) != 0) {
            set = queue.constraints;
        }
        return queue.copy(str, priority, set);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final Priority component2() {
        return this.priority;
    }

    @NotNull
    public final Set<QueueConstraint> component3() {
        return this.constraints;
    }

    @NotNull
    public final Queue copy(@NotNull String name, @NotNull Priority priority, @NotNull Set<? extends QueueConstraint> constraints) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(priority, "priority");
        Intrinsics.checkParameterIsNotNull(constraints, "constraints");
        return new Queue(name, priority, constraints);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Queue)) {
                return false;
            }
            Queue queue = (Queue) obj;
            return Intrinsics.areEqual(this.name, queue.name) && Intrinsics.areEqual(this.priority, queue.priority) && Intrinsics.areEqual(this.constraints, queue.constraints);
        }
        return true;
    }

    @NotNull
    public final Set<QueueConstraint> getConstraints() {
        return this.constraints;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Priority getPriority() {
        return this.priority;
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Priority priority = this.priority;
        int hashCode2 = (hashCode + (priority != null ? priority.hashCode() : 0)) * 31;
        Set<QueueConstraint> set = this.constraints;
        if (set != null) {
            i = set.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Queue(name=");
        outline107.append(this.name);
        outline107.append(", priority=");
        outline107.append(this.priority);
        outline107.append(", constraints=");
        outline107.append(this.constraints);
        outline107.append(")");
        return outline107.toString();
    }
}
