package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
/* compiled from: MainDispatchers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/internal/MainDispatcherLoader;", "", "()V", "FAST_SERVICE_LOADER_ENABLED", "", "dispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "loadMainDispatcher", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MainDispatcherLoader {
    private static final boolean FAST_SERVICE_LOADER_ENABLED;
    public static final MainDispatcherLoader INSTANCE;
    @JvmField
    @NotNull
    public static final MainCoroutineDispatcher dispatcher;

    static {
        MainDispatcherLoader mainDispatcherLoader = new MainDispatcherLoader();
        INSTANCE = mainDispatcherLoader;
        FAST_SERVICE_LOADER_ENABLED = SystemPropsKt.systemProp("kotlinx.coroutines.fast.service.loader", true);
        dispatcher = mainDispatcherLoader.loadMainDispatcher();
    }

    private MainDispatcherLoader() {
    }

    private final MainCoroutineDispatcher loadMainDispatcher() {
        List<MainDispatcherFactory> list;
        Object next;
        MainCoroutineDispatcher tryCreateDispatcher;
        try {
            if (FAST_SERVICE_LOADER_ENABLED) {
                list = FastServiceLoader.INSTANCE.loadMainDispatcherFactory$kotlinx_coroutines_core();
            } else {
                list = SequencesKt.toList(SequencesKt.asSequence(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator()));
            }
            Iterator<T> it2 = list.iterator();
            if (!it2.hasNext()) {
                next = null;
            } else {
                next = it2.next();
                if (it2.hasNext()) {
                    int loadPriority = ((MainDispatcherFactory) next).getLoadPriority();
                    do {
                        Object next2 = it2.next();
                        int loadPriority2 = ((MainDispatcherFactory) next2).getLoadPriority();
                        if (loadPriority < loadPriority2) {
                            next = next2;
                            loadPriority = loadPriority2;
                        }
                    } while (it2.hasNext());
                }
            }
            MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) next;
            return (mainDispatcherFactory == null || (tryCreateDispatcher = MainDispatchersKt.tryCreateDispatcher(mainDispatcherFactory, list)) == null) ? MainDispatchersKt.createMissingDispatcher$default(null, null, 3, null) : tryCreateDispatcher;
        } catch (Throwable th) {
            return MainDispatchersKt.createMissingDispatcher$default(th, null, 2, null);
        }
    }
}
