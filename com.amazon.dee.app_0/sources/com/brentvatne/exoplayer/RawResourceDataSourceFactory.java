package com.brentvatne.exoplayer;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
/* loaded from: classes.dex */
class RawResourceDataSourceFactory implements DataSource.Factory {
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RawResourceDataSourceFactory(Context context) {
        this.context = context;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
    /* renamed from: createDataSource */
    public DataSource mo7468createDataSource() {
        return new RawResourceDataSource(this.context);
    }
}
