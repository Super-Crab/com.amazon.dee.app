package com.amazon.alexa.protocols.service.api;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.List;
@RequiresApi(21)
/* loaded from: classes9.dex */
public abstract class ComponentRegistry {
    @VisibleForTesting
    @Deprecated
    static ComponentRegistry instance;

    /* loaded from: classes9.dex */
    public interface ComponentFactory<T> {
        @Deprecated
        default T create(Context context) {
            return null;
        }

        default T create(ComponentGetter componentGetter, Context context) {
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public interface ConcreteComponentFactory<T> {
        T create(Context context);
    }

    @NonNull
    public static ComponentRegistry getInstance() {
        Preconditions.checkState(instance != null, "init() must be called before getInstance()");
        return instance;
    }

    @NonNull
    public static ComponentRegistry init(Context context) {
        Preconditions.checkState(instance == null);
        instance = new DefaultComponentRegistry(context);
        return instance;
    }

    public static void resetForTest() {
        instance = null;
    }

    @NonNull
    public abstract <T> ComponentRegistry bind(@NonNull Class<T> cls, @NonNull String str);

    @NonNull
    public abstract <T> ComponentRegistry bindConcreteFactory(@NonNull Class<T> cls, @NonNull ConcreteComponentFactory<? extends T> concreteComponentFactory);

    @NonNull
    public abstract <T> ComponentRegistry bindFactory(@NonNull Class<T> cls, @NonNull String str);

    @NonNull
    public abstract <T> ComponentRegistry bindList(@NonNull Class<T> cls, @NonNull String... strArr);

    @NonNull
    public abstract <T> Optional<T> get(@NonNull Class<T> cls);

    @NonNull
    public abstract <T> LazyComponent<T> getLazy(@NonNull Class<T> cls);

    @NonNull
    public abstract <T> LazyComponent<List<T>> getLazyList(@NonNull Class<T> cls);
}
