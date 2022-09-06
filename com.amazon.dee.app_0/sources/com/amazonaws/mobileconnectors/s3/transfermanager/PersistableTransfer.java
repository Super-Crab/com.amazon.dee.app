package com.amazonaws.mobileconnectors.s3.transfermanager;

import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonReader;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
@Deprecated
/* loaded from: classes13.dex */
public abstract class PersistableTransfer {
    public static <T extends PersistableTransfer> T deserializeFrom(InputStream inputStream) {
        AwsJsonReader jsonReader = JsonUtils.getJsonReader(new BufferedReader(new InputStreamReader(inputStream, StringUtils.UTF8)));
        try {
            jsonReader.beginObject();
            String str = null;
            long j = -1;
            long j2 = -1;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            long[] jArr = null;
            ResponseHeaderOverrides responseHeaderOverrides = null;
            boolean z = false;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (nextName.equals("pauseType")) {
                    str = jsonReader.nextString();
                } else if (nextName.equals("bucketName")) {
                    str4 = jsonReader.nextString();
                } else if (nextName.equals("key")) {
                    str5 = jsonReader.nextString();
                } else if (nextName.equals("file")) {
                    str2 = jsonReader.nextString();
                } else if (nextName.equals("multipartUploadId")) {
                    str3 = jsonReader.nextString();
                } else if (nextName.equals("partSize")) {
                    j = Long.parseLong(jsonReader.nextString());
                } else if (nextName.equals("mutlipartUploadThreshold")) {
                    j2 = Long.parseLong(jsonReader.nextString());
                } else if (nextName.equals("versionId")) {
                    str6 = jsonReader.nextString();
                } else if (nextName.equals("range")) {
                    jsonReader.beginArray();
                    jArr = new long[]{Long.parseLong(jsonReader.nextString()), Long.parseLong(jsonReader.nextString())};
                    jsonReader.endArray();
                } else if (nextName.equals("responseHeaders")) {
                    responseHeaderOverrides = new ResponseHeaderOverrides();
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName2 = jsonReader.nextName();
                        if (nextName2.equals("contentType")) {
                            responseHeaderOverrides.setContentType(jsonReader.nextString());
                        } else if (nextName2.equals("contentLanguage")) {
                            responseHeaderOverrides.setContentLanguage(jsonReader.nextString());
                        } else if (nextName2.equals("expires")) {
                            responseHeaderOverrides.setExpires(jsonReader.nextString());
                        } else if (nextName2.equals("cacheControl")) {
                            responseHeaderOverrides.setCacheControl(jsonReader.nextString());
                        } else if (nextName2.equals("contentDisposition")) {
                            responseHeaderOverrides.setContentDisposition(jsonReader.nextString());
                        } else if (nextName2.equals("contentEncoding")) {
                            responseHeaderOverrides.setContentEncoding(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                } else if (nextName.equals("isRequesterPays")) {
                    z = Boolean.parseBoolean(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            if ("download".equals(str)) {
                return new PersistableDownload(str4, str5, str6, jArr, responseHeaderOverrides, z, str2);
            }
            if ("upload".equals(str)) {
                return new PersistableUpload(str4, str5, str2, str3, j, j2);
            }
            throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline72("Unsupported paused transfer type: ", str));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public abstract String serialize();

    public final void serialize(OutputStream outputStream) throws IOException {
        outputStream.write(serialize().getBytes(StringUtils.UTF8));
        outputStream.flush();
    }

    public static <T extends PersistableTransfer> T deserializeFrom(String str) {
        if (str == null) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(StringUtils.UTF8));
        try {
            return (T) deserializeFrom(byteArrayInputStream);
        } finally {
            try {
                byteArrayInputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
