package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.http.HttpResponse;
import com.amazon.alexa.accessory.io.Source;
import java.io.IOException;
/* loaded from: classes.dex */
public final class NoResponseFactory implements HttpResponse.Factory<NoResponse> {

    /* loaded from: classes.dex */
    public static final class NoResponse {
        public static final NoResponse INSTANCE = new NoResponse();

        private NoResponse() {
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessory.internal.http.HttpResponse.Factory
    /* renamed from: create */
    public NoResponse mo309create(Source source) throws IOException {
        return NoResponse.INSTANCE;
    }
}
