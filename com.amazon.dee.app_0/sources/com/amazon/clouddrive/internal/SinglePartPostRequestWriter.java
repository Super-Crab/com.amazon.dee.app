package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import okhttp3.Request;
/* loaded from: classes11.dex */
class SinglePartPostRequestWriter<Request> implements PostRequestWriter {
    static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private final Request mRequest;
    private final JsonSerializer<Request> mSerializer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SinglePartPostRequestWriter(Request request, JsonSerializer<Request> jsonSerializer) {
        this.mRequest = request;
        this.mSerializer = jsonSerializer;
    }

    @Override // com.amazon.clouddrive.internal.RequestPropertyWriter
    @Deprecated
    public void writeHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException {
        httpURLConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
    }

    @Override // com.amazon.clouddrive.internal.PostRequestWriter
    public void writeRequest(OutputStream outputStream) throws CloudDriveException, InterruptedException {
        JsonSerializer<Request> jsonSerializer = this.mSerializer;
        if (jsonSerializer != null) {
            CloudDriveObjectMapper.writeStream(outputStream, this.mRequest, jsonSerializer);
        }
    }

    @Override // com.amazon.clouddrive.internal.RequestPropertyWriter
    public void writeHeaders(Request.Builder builder) throws CloudDriveException, InterruptedException {
        builder.header("Content-Type", CONTENT_TYPE);
    }
}
