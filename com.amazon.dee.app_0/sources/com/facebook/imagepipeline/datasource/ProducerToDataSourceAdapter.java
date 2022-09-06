package com.facebook.imagepipeline.datasource;

import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class ProducerToDataSourceAdapter<T> extends AbstractProducerToDataSourceAdapter<T> {
    private ProducerToDataSourceAdapter(Producer<T> producer, SettableProducerContext settableProducerContext, RequestListener2 listener) {
        super(producer, settableProducerContext, listener);
    }

    public static <T> DataSource<T> create(Producer<T> producer, SettableProducerContext settableProducerContext, RequestListener2 listener) {
        return new ProducerToDataSourceAdapter(producer, settableProducerContext, listener);
    }
}
