package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class ResourceCacheKey implements Key {
    private static final LruCache<Class<?>, byte[]> RESOURCE_CLASS_BYTES = new LruCache<>(50);
    private final ArrayPool arrayPool;
    private final Class<?> decodedResourceClass;
    private final int height;
    private final Options options;
    private final Key signature;
    private final Key sourceKey;
    private final Transformation<?> transformation;
    private final int width;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResourceCacheKey(ArrayPool arrayPool, Key key, Key key2, int i, int i2, Transformation<?> transformation, Class<?> cls, Options options) {
        this.arrayPool = arrayPool;
        this.sourceKey = key;
        this.signature = key2;
        this.width = i;
        this.height = i2;
        this.transformation = transformation;
        this.decodedResourceClass = cls;
        this.options = options;
    }

    private byte[] getResourceClassBytes() {
        byte[] bArr = RESOURCE_CLASS_BYTES.get(this.decodedResourceClass);
        if (bArr == null) {
            byte[] bytes = this.decodedResourceClass.getName().getBytes(Key.CHARSET);
            RESOURCE_CLASS_BYTES.put(this.decodedResourceClass, bytes);
            return bytes;
        }
        return bArr;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof ResourceCacheKey) {
            ResourceCacheKey resourceCacheKey = (ResourceCacheKey) obj;
            return this.height == resourceCacheKey.height && this.width == resourceCacheKey.width && Util.bothNullOrEqual(this.transformation, resourceCacheKey.transformation) && this.decodedResourceClass.equals(resourceCacheKey.decodedResourceClass) && this.sourceKey.equals(resourceCacheKey.sourceKey) && this.signature.equals(resourceCacheKey.signature) && this.options.equals(resourceCacheKey.options);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        int hashCode = ((((this.signature.hashCode() + (this.sourceKey.hashCode() * 31)) * 31) + this.width) * 31) + this.height;
        Transformation<?> transformation = this.transformation;
        if (transformation != null) {
            hashCode = (hashCode * 31) + transformation.hashCode();
        }
        int hashCode2 = this.decodedResourceClass.hashCode();
        return this.options.hashCode() + ((hashCode2 + (hashCode * 31)) * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ResourceCacheKey{sourceKey=");
        outline107.append(this.sourceKey);
        outline107.append(", signature=");
        outline107.append(this.signature);
        outline107.append(", width=");
        outline107.append(this.width);
        outline107.append(", height=");
        outline107.append(this.height);
        outline107.append(", decodedResourceClass=");
        outline107.append(this.decodedResourceClass);
        outline107.append(", transformation='");
        outline107.append(this.transformation);
        outline107.append(Chars.QUOTE);
        outline107.append(", options=");
        outline107.append(this.options);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.arrayPool.getExact(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.width).putInt(this.height).array();
        this.signature.updateDiskCacheKey(messageDigest);
        this.sourceKey.updateDiskCacheKey(messageDigest);
        messageDigest.update(bArr);
        Transformation<?> transformation = this.transformation;
        if (transformation != null) {
            transformation.updateDiskCacheKey(messageDigest);
        }
        this.options.updateDiskCacheKey(messageDigest);
        messageDigest.update(getResourceClassBytes());
        this.arrayPool.put(bArr);
    }
}
