package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.NoRetryServiceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class UploadFileExtendedV2ServiceExceptionProvider implements ServiceExceptionProvider {
    private String getNodeId(JsonParser jsonParser) throws IOException {
        List<String> textList = getTextList(jsonParser);
        if (textList == null || textList.isEmpty()) {
            return null;
        }
        return textList.get(0);
    }

    private String getText(JsonParser jsonParser) throws IOException {
        if (jsonParser.nextToken() != JsonToken.VALUE_NULL) {
            return jsonParser.getText();
        }
        return null;
    }

    private List<String> getTextList(JsonParser jsonParser) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                String text = jsonParser.getText();
                if (text != null && !text.isEmpty()) {
                    arrayList.add(text);
                }
            }
        }
        return arrayList;
    }

    @Override // com.amazon.clouddrive.internal.ServiceExceptionProvider
    public CloudDriveException getCloudDriveException(InputStream inputStream, int i) throws NoRetryServiceException {
        try {
            String str = "Non-successful response code: " + i;
            JsonParser createJsonParser = new JsonFactory().createJsonParser(inputStream);
            JsonToken nextToken = createJsonParser.nextToken();
            if (nextToken != JsonToken.VALUE_NULL && nextToken == JsonToken.START_OBJECT) {
                String str2 = null;
                String str3 = null;
                String str4 = null;
                String str5 = null;
                while (createJsonParser.nextToken() != JsonToken.END_OBJECT) {
                    if (createJsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                        String currentName = createJsonParser.getCurrentName();
                        if ("message".equals(currentName)) {
                            str = getText(createJsonParser);
                        } else if ("errorCode".equals(currentName)) {
                            str2 = getText(createJsonParser);
                        } else if ("timeStamp".equals(currentName)) {
                            str5 = getText(createJsonParser);
                        } else if ("requestId".equals(currentName)) {
                            str3 = getText(createJsonParser);
                        } else if ("errorDetails".equals(currentName)) {
                            if (createJsonParser.nextToken() == JsonToken.START_OBJECT) {
                                while (createJsonParser.nextToken() != JsonToken.END_OBJECT) {
                                    if (createJsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                                        if ("conflictNodeIds".equals(createJsonParser.getCurrentName())) {
                                            str4 = getNodeId(createJsonParser);
                                        } else {
                                            createJsonParser.skipChildren();
                                        }
                                    }
                                }
                            } else {
                                createJsonParser.skipChildren();
                            }
                        } else {
                            createJsonParser.skipChildren();
                        }
                    }
                }
                return new CloudDriveExceptionBuilder(i).withMessage(str).withServerErrorCode(str2).withLogRef(str3).withConflictNodeId(str4).withTimestamp(str5).build();
            }
            throw new NoRetryServiceException("Unexpected response format.");
        } catch (IOException e) {
            throw new NoRetryServiceException("Failed to parse Cloud Drive service error message", e);
        }
    }
}
