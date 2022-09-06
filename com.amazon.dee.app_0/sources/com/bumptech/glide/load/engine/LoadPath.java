package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DecodePath;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
public class LoadPath<Data, ResourceType, Transcode> {
    private final Class<Data> dataClass;
    private final List<? extends DecodePath<Data, ResourceType, Transcode>> decodePaths;
    private final String failureMessage;
    private final Pools.Pool<List<Throwable>> listPool;

    public LoadPath(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<DecodePath<Data, ResourceType, Transcode>> list, Pools.Pool<List<Throwable>> pool) {
        this.dataClass = cls;
        this.listPool = pool;
        this.decodePaths = (List) Preconditions.checkNotEmpty(list);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed LoadPath{");
        outline107.append(cls.getSimpleName());
        outline107.append("->");
        outline107.append(cls2.getSimpleName());
        outline107.append("->");
        outline107.append(cls3.getSimpleName());
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        this.failureMessage = outline107.toString();
    }

    private Resource<Transcode> loadWithExceptionList(DataRewinder<Data> dataRewinder, @NonNull Options options, int i, int i2, DecodePath.DecodeCallback<ResourceType> decodeCallback, List<Throwable> list) throws GlideException {
        int size = this.decodePaths.size();
        Resource<Transcode> resource = null;
        for (int i3 = 0; i3 < size; i3++) {
            try {
                resource = this.decodePaths.get(i3).decode(dataRewinder, i, i2, options, decodeCallback);
            } catch (GlideException e) {
                list.add(e);
            }
            if (resource != null) {
                break;
            }
        }
        if (resource != null) {
            return resource;
        }
        throw new GlideException(this.failureMessage, new ArrayList(list));
    }

    public Class<Data> getDataClass() {
        return this.dataClass;
    }

    public Resource<Transcode> load(DataRewinder<Data> dataRewinder, @NonNull Options options, int i, int i2, DecodePath.DecodeCallback<ResourceType> decodeCallback) throws GlideException {
        List<Throwable> list = (List) Preconditions.checkNotNull(this.listPool.acquire());
        try {
            return loadWithExceptionList(dataRewinder, options, i, i2, decodeCallback, list);
        } finally {
            this.listPool.release(list);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LoadPath{decodePaths=");
        outline107.append(Arrays.toString(this.decodePaths.toArray()));
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
