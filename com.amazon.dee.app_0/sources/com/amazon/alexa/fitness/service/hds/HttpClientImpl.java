package com.amazon.alexa.fitness.service.hds;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpClientImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HttpClientImpl;", "Lcom/amazon/alexa/fitness/service/hds/HttpClient;", "()V", "openConnection", "Ljavax/net/ssl/HttpsURLConnection;", "endpoint", "", "postHttpRequest", "clientConfiguration", "Lcom/amazon/alexa/fitness/service/hds/HttpClientConfiguration;", "request", "Lcom/amazon/alexa/fitness/service/hds/HttpRequest;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HttpClientImpl implements HttpClient {
    private final HttpsURLConnection openConnection(String str) {
        URLConnection openConnection = new URL(str).openConnection();
        if (openConnection != null) {
            return (HttpsURLConnection) openConnection;
        }
        throw new TypeCastException("null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
    }

    @Override // com.amazon.alexa.fitness.service.hds.HttpClient
    @Nullable
    public String postHttpRequest(@NotNull HttpClientConfiguration clientConfiguration, @NotNull HttpRequest request) {
        Intrinsics.checkParameterIsNotNull(clientConfiguration, "clientConfiguration");
        Intrinsics.checkParameterIsNotNull(request, "request");
        HttpsURLConnection openConnection = openConnection(clientConfiguration.getEndpoint());
        openConnection.setConnectTimeout(clientConfiguration.getConnectTimeoutInMs());
        openConnection.setReadTimeout(clientConfiguration.getReadTimeoutInMs());
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            openConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        openConnection.setRequestMethod("POST");
        openConnection.setDoOutput(true);
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openConnection.getOutputStream());
            outputStreamWriter.write(request.getPayload());
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStreamWriter, null);
            InputStream stream = openConnection.getResponseCode() < 400 ? openConnection.getInputStream() : openConnection.getErrorStream();
            Intrinsics.checkExpressionValueIsNotNull(stream, "stream");
            InputStreamReader inputStreamReader = new InputStreamReader(stream, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            String readText = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            return readText;
        } finally {
            openConnection.disconnect();
        }
    }
}
