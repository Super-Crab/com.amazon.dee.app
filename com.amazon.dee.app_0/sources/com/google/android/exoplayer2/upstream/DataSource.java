package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public interface DataSource extends DataReader {

    /* loaded from: classes2.dex */
    public interface Factory {
        /* renamed from: createDataSource */
        DataSource mo7468createDataSource();
    }

    void addTransferListener(TransferListener transferListener);

    void close() throws IOException;

    default Map<String, List<String>> getResponseHeaders() {
        return Collections.emptyMap();
    }

    @Nullable
    Uri getUri();

    long open(DataSpec dataSpec) throws IOException;
}
