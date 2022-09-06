package com.google.android.exoplayer2.upstream;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.FileDataSource;
@Deprecated
/* loaded from: classes2.dex */
public final class FileDataSourceFactory implements DataSource.Factory {
    private final FileDataSource.Factory wrappedFactory;

    public FileDataSourceFactory() {
        this(null);
    }

    public FileDataSourceFactory(@Nullable TransferListener transferListener) {
        this.wrappedFactory = new FileDataSource.Factory().setListener(transferListener);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
    /* renamed from: createDataSource  reason: collision with other method in class */
    public FileDataSource mo7468createDataSource() {
        return this.wrappedFactory.mo7468createDataSource();
    }
}
