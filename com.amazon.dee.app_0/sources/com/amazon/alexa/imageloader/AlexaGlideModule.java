package com.amazon.alexa.imageloader;

import android.content.Context;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.MemoryCacheAdapter;
import com.bumptech.glide.module.AppGlideModule;
/* loaded from: classes9.dex */
public class AlexaGlideModule extends AppGlideModule {
    @Override // com.bumptech.glide.module.AppGlideModule, com.bumptech.glide.module.AppliesOptions
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        glideBuilder.setMemoryCache(new MemoryCacheAdapter());
    }
}
