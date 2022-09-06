package com.amazon.photos.autosave.internal.dagger.component;

import com.amazon.photos.autosave.AutosaveManager;
import com.amazon.photos.autosave.internal.dagger.PerAccount;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule;
import com.amazon.photos.autosave.internal.dagger.module.DatabaseModule;
import com.amazon.photos.autosave.internal.workers.AutosaveWorker;
import com.amazon.photos.autosave.internal.workers.CancelAndRescheduleWorker;
import com.amazon.photos.autosave.internal.workers.CancelUploadsWorker;
import dagger.Component;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: AutosaveComponent.kt */
@PerAccount
@Component(modules = {AutosaveModule.class, DatabaseModule.class})
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, d2 = {"Lcom/amazon/photos/autosave/internal/dagger/component/AutosaveComponent;", "", "inject", "", "autosaveManager", "Lcom/amazon/photos/autosave/AutosaveManager;", "autosaveWorker", "Lcom/amazon/photos/autosave/internal/workers/AutosaveWorker;", "cancelAndRescheduleWorker", "Lcom/amazon/photos/autosave/internal/workers/CancelAndRescheduleWorker;", "cancelUploadsWorker", "Lcom/amazon/photos/autosave/internal/workers/CancelUploadsWorker;", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface AutosaveComponent {
    void inject(@Nullable AutosaveManager autosaveManager);

    void inject(@Nullable AutosaveWorker autosaveWorker);

    void inject(@Nullable CancelAndRescheduleWorker cancelAndRescheduleWorker);

    void inject(@Nullable CancelUploadsWorker cancelUploadsWorker);
}
