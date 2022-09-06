package com.facebook.imagepipeline.datasource;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class CloseableProducerToDataSourceAdapter<T> extends AbstractProducerToDataSourceAdapter<CloseableReference<T>> {
    private CloseableProducerToDataSourceAdapter(Producer<CloseableReference<T>> producer, SettableProducerContext settableProducerContext, RequestListener2 listener) {
        super(producer, settableProducerContext, listener);
    }

    public static <T> DataSource<CloseableReference<T>> create(Producer<CloseableReference<T>> producer, SettableProducerContext settableProducerContext, RequestListener2 listener) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("CloseableProducerToDataSourceAdapter#create");
        }
        CloseableProducerToDataSourceAdapter closeableProducerToDataSourceAdapter = new CloseableProducerToDataSourceAdapter(producer, settableProducerContext, listener);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return closeableProducerToDataSourceAdapter;
    }

    @Override // com.facebook.datasource.AbstractDataSource
    protected /* bridge */ /* synthetic */ void closeResult(Object result) {
        closeResult((CloseableReference) ((CloseableReference) result));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.datasource.AbstractProducerToDataSourceAdapter
    public /* bridge */ /* synthetic */ void onNewResultImpl(Object result, int status, ProducerContext producerContext) {
        onNewResultImpl((CloseableReference) ((CloseableReference) result), status, producerContext);
    }

    protected void closeResult(CloseableReference<T> result) {
        CloseableReference.closeSafely((CloseableReference<?>) result);
    }

    @Override // com.facebook.datasource.AbstractDataSource, com.facebook.datasource.DataSource
    @Nullable
    /* renamed from: getResult */
    public CloseableReference<T> mo6898getResult() {
        return CloseableReference.cloneOrNull((CloseableReference) super.mo6898getResult());
    }

    protected void onNewResultImpl(CloseableReference<T> result, int status, ProducerContext producerContext) {
        super.onNewResultImpl((CloseableProducerToDataSourceAdapter<T>) CloseableReference.cloneOrNull(result), status, producerContext);
    }
}
