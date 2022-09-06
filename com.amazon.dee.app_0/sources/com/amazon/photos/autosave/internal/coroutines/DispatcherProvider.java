package com.amazon.photos.autosave.internal.coroutines;

import com.amazon.alexa.routing.data.RouteName;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
/* compiled from: DispatcherProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005¨\u0006\f"}, d2 = {"Lcom/amazon/photos/autosave/internal/coroutines/DispatcherProvider;", "", "default", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDefault", "()Lkotlinx/coroutines/CoroutineDispatcher;", "io", "getIo", RouteName.MAIN, "getMain", "unconfined", "getUnconfined", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface DispatcherProvider {

    /* compiled from: DispatcherProvider.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        @NotNull
        public static CoroutineDispatcher getDefault(DispatcherProvider dispatcherProvider) {
            return Dispatchers.getDefault();
        }

        @NotNull
        public static CoroutineDispatcher getIo(DispatcherProvider dispatcherProvider) {
            return Dispatchers.getIO();
        }

        @NotNull
        public static CoroutineDispatcher getMain(DispatcherProvider dispatcherProvider) {
            return Dispatchers.getMain();
        }

        @NotNull
        public static CoroutineDispatcher getUnconfined(DispatcherProvider dispatcherProvider) {
            return Dispatchers.getUnconfined();
        }
    }

    @NotNull
    CoroutineDispatcher getDefault();

    @NotNull
    CoroutineDispatcher getIo();

    @NotNull
    CoroutineDispatcher getMain();

    @NotNull
    CoroutineDispatcher getUnconfined();
}
