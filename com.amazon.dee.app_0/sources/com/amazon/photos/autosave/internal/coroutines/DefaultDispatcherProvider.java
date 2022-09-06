package com.amazon.photos.autosave.internal.coroutines;

import com.amazon.photos.autosave.internal.coroutines.DispatcherProvider;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultDispatcherProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/photos/autosave/internal/coroutines/DefaultDispatcherProvider;", "Lcom/amazon/photos/autosave/internal/coroutines/DispatcherProvider;", "()V", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DefaultDispatcherProvider implements DispatcherProvider {
    @Override // com.amazon.photos.autosave.internal.coroutines.DispatcherProvider
    @NotNull
    public CoroutineDispatcher getDefault() {
        return DispatcherProvider.DefaultImpls.getDefault(this);
    }

    @Override // com.amazon.photos.autosave.internal.coroutines.DispatcherProvider
    @NotNull
    public CoroutineDispatcher getIo() {
        return DispatcherProvider.DefaultImpls.getIo(this);
    }

    @Override // com.amazon.photos.autosave.internal.coroutines.DispatcherProvider
    @NotNull
    public CoroutineDispatcher getMain() {
        return DispatcherProvider.DefaultImpls.getMain(this);
    }

    @Override // com.amazon.photos.autosave.internal.coroutines.DispatcherProvider
    @NotNull
    public CoroutineDispatcher getUnconfined() {
        return DispatcherProvider.DefaultImpls.getUnconfined(this);
    }
}
