package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.voice.tta.Constants;
import com.amazon.clouddrive.extended.model.GetJobStatusResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class GetJobStatusResponseDeserializer extends AbstractDeserializer<GetJobStatusResponse, GetJobStatusResponse> {
    public static final JsonDeserializer<GetJobStatusResponse> INSTANCE = new GetJobStatusResponseDeserializer();

    /* loaded from: classes11.dex */
    private static class GetJobStatusFieldDeserializer implements JsonFieldDeserializer<GetJobStatusResponse> {
        private GetJobStatusFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetJobStatusResponse) obj));
        }

        public <U extends GetJobStatusResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (Constants.IntentParameters.START_TIMESTAMP.equals(str)) {
                u.setStartTimestamp(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("lastModifiedTimestamp".equals(str)) {
                u.setLastModifiedTimestamp(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("count".equals(str)) {
                u.setCount(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("succeedJobs".equals(str)) {
                u.setSucceedJobs(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("failedJobs".equals(str)) {
                u.setFailedJobs(SimpleDeserializers.deserializeLong(jsonParser));
                return true;
            } else if ("status".equals(str)) {
                u.setStatus(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"taskId".equals(str)) {
                return false;
            } else {
                u.setTaskId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GetJobStatusResponseDeserializer() {
        super(new GetJobStatusFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GetJobStatusResponse mo3156createValue() {
        return new GetJobStatusResponse();
    }
}
