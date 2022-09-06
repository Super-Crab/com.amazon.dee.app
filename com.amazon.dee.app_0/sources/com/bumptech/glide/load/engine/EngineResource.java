package com.bumptech.glide.load.engine;

import android.os.Looper;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
class EngineResource<Z> implements Resource<Z> {
    private int acquired;
    private final boolean isCacheable;
    private final boolean isRecyclable;
    private boolean isRecycled;
    private Key key;
    private ResourceListener listener;
    private final Resource<Z> resource;

    /* loaded from: classes6.dex */
    interface ResourceListener {
        void onResourceReleased(Key key, EngineResource<?> engineResource);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EngineResource(Resource<Z> resource, boolean z, boolean z2) {
        this.resource = (Resource) Preconditions.checkNotNull(resource);
        this.isCacheable = z;
        this.isRecyclable = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void acquire() {
        if (!this.isRecycled) {
            if (Looper.getMainLooper().equals(Looper.myLooper())) {
                this.acquired++;
                return;
            }
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        }
        throw new IllegalStateException("Cannot acquire a recycled resource");
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    /* renamed from: get */
    public Z mo6789get() {
        return this.resource.mo6789get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Resource<Z> getResource() {
        return this.resource;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Class<Z> getResourceClass() {
        return this.resource.getResourceClass();
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public int getSize() {
        return this.resource.getSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCacheable() {
        return this.isCacheable;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public void recycle() {
        if (this.acquired <= 0) {
            if (!this.isRecycled) {
                this.isRecycled = true;
                if (!this.isRecyclable) {
                    return;
                }
                this.resource.recycle();
                return;
            }
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
        throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        if (this.acquired > 0) {
            if (Looper.getMainLooper().equals(Looper.myLooper())) {
                int i = this.acquired - 1;
                this.acquired = i;
                if (i != 0) {
                    return;
                }
                this.listener.onResourceReleased(this.key, this);
                return;
            }
            throw new IllegalThreadStateException("Must call release on the main thread");
        }
        throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setResourceListener(Key key, ResourceListener resourceListener) {
        this.key = key;
        this.listener = resourceListener;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EngineResource{isCacheable=");
        outline107.append(this.isCacheable);
        outline107.append(", listener=");
        outline107.append(this.listener);
        outline107.append(", key=");
        outline107.append(this.key);
        outline107.append(", acquired=");
        outline107.append(this.acquired);
        outline107.append(", isRecycled=");
        outline107.append(this.isRecycled);
        outline107.append(", resource=");
        outline107.append(this.resource);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
