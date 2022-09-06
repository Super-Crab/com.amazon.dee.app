package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import java.net.HttpURLConnection;
import okhttp3.Request;
/* loaded from: classes11.dex */
class JsonPostRequestWriter<Request> extends SinglePartPostRequestWriter<Request> {
    private static final String CONTENT_TYPE = "application/json";

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonPostRequestWriter(Request request, JsonSerializer<Request> jsonSerializer) {
        super(request, jsonSerializer);
    }

    @Override // com.amazon.clouddrive.internal.SinglePartPostRequestWriter, com.amazon.clouddrive.internal.RequestPropertyWriter
    @Deprecated
    public void writeHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException {
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
    }

    @Override // com.amazon.clouddrive.internal.SinglePartPostRequestWriter, com.amazon.clouddrive.internal.RequestPropertyWriter
    public void writeHeaders(Request.Builder builder) throws CloudDriveException {
        builder.header("Content-Type", "application/json");
    }
}
