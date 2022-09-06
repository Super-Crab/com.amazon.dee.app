package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.AutoValue_SendRequest;
import com.google.auto.value.AutoValue;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@AutoValue
/* loaded from: classes2.dex */
public abstract class SendRequest {

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    @AutoValue.Builder
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract SendRequest build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setEncoding(Encoding encoding);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setEvent(Event<?> event);

        public <T> Builder setEvent(Event<T> event, Encoding encoding, Transformer<T, byte[]> transformer) {
            setEvent(event);
            setEncoding(encoding);
            setTransformer(transformer);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setTransformer(Transformer<?, byte[]> transformer);

        public abstract Builder setTransportContext(TransportContext transportContext);

        public abstract Builder setTransportName(String str);
    }

    public static Builder builder() {
        return new AutoValue_SendRequest.Builder();
    }

    public abstract Encoding getEncoding();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Event<?> getEvent();

    public byte[] getPayload() {
        return getTransformer().apply(getEvent().getPayload());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Transformer<?, byte[]> getTransformer();

    public abstract TransportContext getTransportContext();

    public abstract String getTransportName();
}
