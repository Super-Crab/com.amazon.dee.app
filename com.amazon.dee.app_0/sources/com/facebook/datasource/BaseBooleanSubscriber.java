package com.facebook.datasource;
/* loaded from: classes2.dex */
public abstract class BaseBooleanSubscriber implements DataSubscriber<Boolean> {
    @Override // com.facebook.datasource.DataSubscriber
    public void onCancellation(DataSource<Boolean> dataSource) {
    }

    @Override // com.facebook.datasource.DataSubscriber
    public void onFailure(DataSource<Boolean> dataSource) {
        try {
            onFailureImpl(dataSource);
        } finally {
            dataSource.close();
        }
    }

    protected abstract void onFailureImpl(DataSource<Boolean> dataSource);

    @Override // com.facebook.datasource.DataSubscriber
    public void onNewResult(DataSource<Boolean> dataSource) {
        try {
            onNewResultImpl(dataSource.mo6898getResult().booleanValue());
        } finally {
            dataSource.close();
        }
    }

    protected abstract void onNewResultImpl(boolean isFoundInDisk);

    @Override // com.facebook.datasource.DataSubscriber
    public void onProgressUpdate(DataSource<Boolean> dataSource) {
    }
}
