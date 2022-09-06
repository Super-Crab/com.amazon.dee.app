package com.amazon.clouddrive.internal;

import com.amazon.alexa.mobilytics.event.LogLevel;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.NoRetryServiceException;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
class ErrorDeserializer {
    private static final int TOO_MANY_REQUEST_CODE = 429;

    ErrorDeserializer() {
    }

    static String extractRawMessage(InputStream inputStream) throws CloudDriveException {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = null;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            byte[] bArr = new byte[8000];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
            return str;
        } catch (IOException e2) {
            e = e2;
            throw new CloudDriveException("Failed to extract raw error message.", e);
        } catch (Throwable th2) {
            th = th2;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CloudDriveException getCloudDriveException(InputStream inputStream, int i) throws NoRetryServiceException {
        try {
            String str = "Non-successful response code: " + i;
            JsonParser createJsonParser = new JsonFactory().createJsonParser(inputStream);
            JsonToken nextToken = createJsonParser.nextToken();
            if (nextToken != JsonToken.VALUE_NULL && nextToken == JsonToken.START_OBJECT) {
                String str2 = null;
                String str3 = null;
                String str4 = null;
                while (createJsonParser.nextToken() != JsonToken.END_OBJECT) {
                    if (createJsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                        String currentName = createJsonParser.getCurrentName();
                        if ("message".equals(currentName)) {
                            if (createJsonParser.nextToken() != JsonToken.VALUE_NULL) {
                                str = createJsonParser.getText();
                            }
                        } else if ("code".equals(currentName)) {
                            if (createJsonParser.nextToken() != JsonToken.VALUE_NULL) {
                                str2 = createJsonParser.getText();
                            }
                        } else if ("logref".equals(currentName)) {
                            if (createJsonParser.nextToken() != JsonToken.VALUE_NULL) {
                                str3 = createJsonParser.getText();
                            }
                        } else if (LogLevel.INFO.equals(currentName)) {
                            if (createJsonParser.nextToken() == JsonToken.START_OBJECT) {
                                while (createJsonParser.nextToken() != JsonToken.END_OBJECT) {
                                    if (createJsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                                        if (AlexaDeviceBackgroundImageBridge.KEY_NODE_ID.equals(createJsonParser.getCurrentName())) {
                                            if (createJsonParser.nextToken() != JsonToken.VALUE_NULL) {
                                                str4 = createJsonParser.getText();
                                            }
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
                return new CloudDriveExceptionBuilder(i).withMessage(str).withServerErrorCode(str2).withLogRef(str3).withConflictNodeId(str4).build();
            }
            throw new NoRetryServiceException("Unexpected response format.");
        } catch (IOException e) {
            throw new NoRetryServiceException("Failed to parse Cloud Drive service error message", e);
        }
    }
}
