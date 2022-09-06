package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;
import java.util.Map;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
class EngineKey implements Key {
    private int hashCode;
    private final int height;
    private final Object model;
    private final Options options;
    private final Class<?> resourceClass;
    private final Key signature;
    private final Class<?> transcodeClass;
    private final Map<Class<?>, Transformation<?>> transformations;
    private final int width;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EngineKey(Object obj, Key key, int i, int i2, Map<Class<?>, Transformation<?>> map, Class<?> cls, Class<?> cls2, Options options) {
        this.model = Preconditions.checkNotNull(obj);
        this.signature = (Key) Preconditions.checkNotNull(key, "Signature must not be null");
        this.width = i;
        this.height = i2;
        this.transformations = (Map) Preconditions.checkNotNull(map);
        this.resourceClass = (Class) Preconditions.checkNotNull(cls, "Resource class must not be null");
        this.transcodeClass = (Class) Preconditions.checkNotNull(cls2, "Transcode class must not be null");
        this.options = (Options) Preconditions.checkNotNull(options);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof EngineKey) {
            EngineKey engineKey = (EngineKey) obj;
            return this.model.equals(engineKey.model) && this.signature.equals(engineKey.signature) && this.height == engineKey.height && this.width == engineKey.width && this.transformations.equals(engineKey.transformations) && this.resourceClass.equals(engineKey.resourceClass) && this.transcodeClass.equals(engineKey.transcodeClass) && this.options.equals(engineKey.options);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.model.hashCode();
            this.hashCode = this.signature.hashCode() + (this.hashCode * 31);
            this.hashCode = (this.hashCode * 31) + this.width;
            this.hashCode = (this.hashCode * 31) + this.height;
            this.hashCode = this.transformations.hashCode() + (this.hashCode * 31);
            this.hashCode = this.resourceClass.hashCode() + (this.hashCode * 31);
            this.hashCode = this.transcodeClass.hashCode() + (this.hashCode * 31);
            this.hashCode = this.options.hashCode() + (this.hashCode * 31);
        }
        return this.hashCode;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EngineKey{model=");
        outline107.append(this.model);
        outline107.append(", width=");
        outline107.append(this.width);
        outline107.append(", height=");
        outline107.append(this.height);
        outline107.append(", resourceClass=");
        outline107.append(this.resourceClass);
        outline107.append(", transcodeClass=");
        outline107.append(this.transcodeClass);
        outline107.append(", signature=");
        outline107.append(this.signature);
        outline107.append(", hashCode=");
        outline107.append(this.hashCode);
        outline107.append(", transformations=");
        outline107.append(this.transformations);
        outline107.append(", options=");
        outline107.append(this.options);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
