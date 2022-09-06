package com.amazon.communication;

import amazon.communication.Message;
import java.io.IOException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes12.dex */
public interface HttpRequestResponseConverter {
    HttpRequestBase convertMessageToRequest(Message message) throws ProtocolException;

    HttpResponse convertMessageToResponse(Message message) throws ProtocolException;

    Message convertRequestToMessage(HttpRequest httpRequest) throws IOException;

    Message convertResponseToMessage(HttpResponse httpResponse) throws ProtocolException;
}
