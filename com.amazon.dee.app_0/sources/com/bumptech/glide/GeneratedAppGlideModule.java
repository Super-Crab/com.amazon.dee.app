package com.bumptech.glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class GeneratedAppGlideModule extends AppGlideModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public abstract Set<Class<?>> getExcludedModuleClasses();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: getRequestManagerFactory */
    public RequestManagerRetriever.RequestManagerFactory mo6746getRequestManagerFactory() {
        return null;
    }
}
