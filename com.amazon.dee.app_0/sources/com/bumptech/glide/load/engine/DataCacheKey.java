package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.Key;
import java.security.MessageDigest;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class DataCacheKey implements Key {
    private final Key signature;
    private final Key sourceKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataCacheKey(Key key, Key key2) {
        this.sourceKey = key;
        this.signature = key2;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof DataCacheKey) {
            DataCacheKey dataCacheKey = (DataCacheKey) obj;
            return this.sourceKey.equals(dataCacheKey.sourceKey) && this.signature.equals(dataCacheKey.signature);
        }
        return false;
    }

    Key getSourceKey() {
        return this.sourceKey;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.signature.hashCode() + (this.sourceKey.hashCode() * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DataCacheKey{sourceKey=");
        outline107.append(this.sourceKey);
        outline107.append(", signature=");
        outline107.append(this.signature);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        this.sourceKey.updateDiskCacheKey(messageDigest);
        this.signature.updateDiskCacheKey(messageDigest);
    }
}
