package rx.android.plugins;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicReference;
import rx.annotations.Experimental;
/* loaded from: classes5.dex */
public final class RxAndroidPlugins {
    private static final RxAndroidPlugins INSTANCE = new RxAndroidPlugins();
    private final AtomicReference<RxAndroidSchedulersHook> schedulersHook = new AtomicReference<>();

    RxAndroidPlugins() {
    }

    public static RxAndroidPlugins getInstance() {
        return INSTANCE;
    }

    public RxAndroidSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            this.schedulersHook.compareAndSet(null, RxAndroidSchedulersHook.getDefaultInstance());
        }
        return this.schedulersHook.get();
    }

    public void registerSchedulersHook(RxAndroidSchedulersHook rxAndroidSchedulersHook) {
        if (this.schedulersHook.compareAndSet(null, rxAndroidSchedulersHook)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Another strategy was already registered: ");
        outline107.append(this.schedulersHook.get());
        throw new IllegalStateException(outline107.toString());
    }

    @Experimental
    public void reset() {
        this.schedulersHook.set(null);
    }
}
