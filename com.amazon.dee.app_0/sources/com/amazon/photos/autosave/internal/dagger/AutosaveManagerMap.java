package com.amazon.photos.autosave.internal.dagger;

import com.amazon.photos.autosave.AutosaveManager;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveManagerMap.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\tJ\u0013\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0000¢\u0006\u0002\b\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u000fJ\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0014J\u001d\u0010\u0015\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0017R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/autosave/internal/dagger/AutosaveManagerMap;", "", "()V", "autosaveManagerMap", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/amazon/photos/autosave/AutosaveManager;", "getAutosaveManagerForAccount", "hashedDirectedId", "getAutosaveManagerForAccount$AndroidPhotosAutosave_release", "getRegisteredHashedDirectedIds", "", "getRegisteredHashedDirectedIds$AndroidPhotosAutosave_release", "isAccountRegistered", "", "isAccountRegistered$AndroidPhotosAutosave_release", "removeAutosaveManager", "", "removeAutosaveManager$AndroidPhotosAutosave_release", "reset", "reset$AndroidPhotosAutosave_release", "setAutosaveManagerForAccount", "autosaveManager", "setAutosaveManagerForAccount$AndroidPhotosAutosave_release", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveManagerMap {
    public static final AutosaveManagerMap INSTANCE = new AutosaveManagerMap();
    private static final ConcurrentHashMap<String, AutosaveManager> autosaveManagerMap = new ConcurrentHashMap<>();

    private AutosaveManagerMap() {
    }

    @NotNull
    public final AutosaveManager getAutosaveManagerForAccount$AndroidPhotosAutosave_release(@NotNull String hashedDirectedId) {
        Intrinsics.checkParameterIsNotNull(hashedDirectedId, "hashedDirectedId");
        AutosaveManager autosaveManager = autosaveManagerMap.get(hashedDirectedId);
        if (autosaveManager != null) {
            return autosaveManager;
        }
        throw new IllegalArgumentException("No AutosaveManager registered for this account.");
    }

    @NotNull
    public final Set<String> getRegisteredHashedDirectedIds$AndroidPhotosAutosave_release() {
        Set<String> keySet = autosaveManagerMap.keySet();
        Intrinsics.checkExpressionValueIsNotNull(keySet, "autosaveManagerMap.keys");
        return keySet;
    }

    public final boolean isAccountRegistered$AndroidPhotosAutosave_release(@NotNull String hashedDirectedId) {
        Intrinsics.checkParameterIsNotNull(hashedDirectedId, "hashedDirectedId");
        return autosaveManagerMap.containsKey(hashedDirectedId);
    }

    public final void removeAutosaveManager$AndroidPhotosAutosave_release(@NotNull String hashedDirectedId) {
        Intrinsics.checkParameterIsNotNull(hashedDirectedId, "hashedDirectedId");
        autosaveManagerMap.remove(hashedDirectedId);
    }

    public final void reset$AndroidPhotosAutosave_release() {
        autosaveManagerMap.clear();
    }

    public final void setAutosaveManagerForAccount$AndroidPhotosAutosave_release(@NotNull String hashedDirectedId, @NotNull AutosaveManager autosaveManager) {
        Intrinsics.checkParameterIsNotNull(hashedDirectedId, "hashedDirectedId");
        Intrinsics.checkParameterIsNotNull(autosaveManager, "autosaveManager");
        if (autosaveManagerMap.get(hashedDirectedId) == null) {
            autosaveManagerMap.put(hashedDirectedId, autosaveManager);
            return;
        }
        throw new IllegalArgumentException("AutosaveManager instance already created for this account.");
    }
}
