package com.amazon.alexa.fitness.model.directive;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaFitnessDirectivePayload.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/model/directive/DirectiveSession;", "", "id", "Ljava/util/UUID;", "(Ljava/util/UUID;)V", "getId", "()Ljava/util/UUID;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DirectiveSession {
    @NotNull
    private final UUID id;

    public DirectiveSession(@NotNull UUID id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.id = id;
    }

    public static /* synthetic */ DirectiveSession copy$default(DirectiveSession directiveSession, UUID uuid, int i, Object obj) {
        if ((i & 1) != 0) {
            uuid = directiveSession.id;
        }
        return directiveSession.copy(uuid);
    }

    @NotNull
    public final UUID component1() {
        return this.id;
    }

    @NotNull
    public final DirectiveSession copy(@NotNull UUID id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        return new DirectiveSession(id);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof DirectiveSession) && Intrinsics.areEqual(this.id, ((DirectiveSession) obj).id);
        }
        return true;
    }

    @NotNull
    public final UUID getId() {
        return this.id;
    }

    public int hashCode() {
        UUID uuid = this.id;
        if (uuid != null) {
            return uuid.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DirectiveSession(id=");
        outline107.append(this.id);
        outline107.append(")");
        return outline107.toString();
    }
}
