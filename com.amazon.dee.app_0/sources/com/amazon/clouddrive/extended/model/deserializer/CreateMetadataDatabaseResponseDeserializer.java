package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.extended.model.CreateMetadataDatabaseResponse;
import com.amazon.clouddrive.internal.ResponsePropertyReader;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import java.io.IOException;
import java.net.HttpURLConnection;
import okhttp3.Response;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class CreateMetadataDatabaseResponseDeserializer implements JsonDeserializer<CreateMetadataDatabaseResponse>, ResponsePropertyReader {
    private static final String LOCATION_KEY = "Location";
    private final CreateMetadataDatabaseResponse response = new CreateMetadataDatabaseResponse();

    @Override // com.amazon.clouddrive.internal.ResponsePropertyReader
    @Deprecated
    public void readHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException, InterruptedException {
        this.response.setStatusUri(httpURLConnection.getHeaderField("Location"));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CreateMetadataDatabaseResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        return this.response;
    }

    @Override // com.amazon.clouddrive.internal.ResponsePropertyReader
    public void readHeaders(Response response) throws CloudDriveException, InterruptedException {
        this.response.setStatusUri(response.header("Location"));
    }
}
