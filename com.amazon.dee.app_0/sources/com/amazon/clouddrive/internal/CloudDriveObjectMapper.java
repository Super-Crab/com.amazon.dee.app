package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.ActionRequiredException;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.RebuildRequestException;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
class CloudDriveObjectMapper {
    CloudDriveObjectMapper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T readStream(InputStream inputStream, JsonDeserializer<T> jsonDeserializer) throws CloudDriveException, InterruptedException {
        JsonParser jsonParser = null;
        try {
            try {
                jsonParser = new JsonFactory().createJsonParser(inputStream);
                jsonParser.nextToken();
                T mo3229deserialize = jsonDeserializer.mo3229deserialize(jsonParser);
                try {
                    jsonParser.close();
                } catch (IOException unused) {
                }
                return mo3229deserialize;
            } catch (Throwable th) {
                if (jsonParser != null) {
                    try {
                        jsonParser.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (InterruptedIOException unused3) {
            throw new InterruptedException();
        } catch (IOException e) {
            throw new ActionRequiredException("Failed to read stream from the service.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void writeStream(OutputStream outputStream, T t, JsonSerializer<T> jsonSerializer) throws CloudDriveException, InterruptedException {
        JsonGenerator jsonGenerator = null;
        try {
            try {
                jsonGenerator = new JsonFactory().createJsonGenerator(outputStream, JsonEncoding.UTF8);
                jsonSerializer.serialize(t, jsonGenerator);
                if (jsonGenerator == null) {
                    return;
                }
                try {
                    jsonGenerator.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                if (jsonGenerator != null) {
                    try {
                        jsonGenerator.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (InterruptedIOException unused3) {
            throw new InterruptedException();
        } catch (IOException e) {
            throw new RebuildRequestException("Failed to write request to the service.", e);
        }
    }
}
