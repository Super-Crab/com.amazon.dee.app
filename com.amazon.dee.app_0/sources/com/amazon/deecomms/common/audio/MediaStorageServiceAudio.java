package com.amazon.deecomms.common.audio;

import android.text.TextUtils;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.amazon.deecomms.media.MediaStorageServiceClient;
import com.facebook.react.bridge.ReadableMap;
import java.io.File;
/* loaded from: classes12.dex */
public abstract class MediaStorageServiceAudio {
    private static final String COMMSID_KEY = "senderCommsId";
    private static final String FILEPATH_KEY = "filePath";
    private String errorMessage;
    private String filePath;
    private File mediaFile;
    protected String senderCommsId;
    final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MediaStorageServiceClient.class.getSimpleName());
    final String QUERY_PARAM_INCLUDE_TRANSCRIPT = "include-transcript";
    final String QUERY_PARAM_PREPEND_CHIME = "prepend-chime";
    final String INVALID_FILE_PATH_ERROR = "Null or empty file path";
    final String EMPTY_COMMS_ID_ERROR = "Null or empty sender CommsId";
    final String FILE_NOT_EXIST_ERROR = "File does not exist";
    final String FILE_TOO_SMALL_ERROR = "File must be more than 100 bytes";

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaStorageServiceAudio(ReadableMap readableMap) {
        this.senderCommsId = readableMap.getString(COMMSID_KEY);
        this.filePath = readableMap.getString(FILEPATH_KEY);
        this.mediaFile = new File(this.filePath);
    }

    public static MediaStorageServiceAudio createPayloadFromMediaType(ReadableMap readableMap, String str) {
        char c;
        String lowerCase = str.toLowerCase();
        int hashCode = lowerCase.hashCode();
        if (hashCode != -1440008444) {
            if (hashCode == 156781895 && lowerCase.equals(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (lowerCase.equals(OkHttpClientWrapper.MESSAGING_CLIENT)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return new MediaStorageServiceAudioMessage(readableMap);
            }
            return new MediaStorageServiceAudioMessage(readableMap);
        }
        return new MediaStorageServiceAudioAnnouncement(readableMap);
    }

    public abstract IHttpClient.Request generateAudioUploadRequest();

    abstract OkHttpClientWrapper generateWrapper();

    public String getErrorMessage() {
        if (TextUtils.isEmpty(this.filePath)) {
            this.errorMessage = "Null or empty file path";
        } else if (TextUtils.isEmpty(this.senderCommsId)) {
            this.errorMessage = "Null or empty sender CommsId";
        } else if (!this.mediaFile.exists()) {
            this.errorMessage = "File does not exist";
        } else if (this.mediaFile.length() < 100) {
            this.errorMessage = "File must be more than 100 bytes";
        }
        return this.errorMessage;
    }

    public String getFilePath() {
        return this.filePath;
    }

    abstract String getFullUrlEndpoint(String str);

    public File getMediaFile() {
        return this.mediaFile;
    }

    public abstract String getResourceType();

    public String getSenderCommsId() {
        return this.senderCommsId;
    }

    void setFile(File file) {
        this.mediaFile = file;
    }

    public boolean validate() {
        return TextUtils.isEmpty(getErrorMessage());
    }
}
