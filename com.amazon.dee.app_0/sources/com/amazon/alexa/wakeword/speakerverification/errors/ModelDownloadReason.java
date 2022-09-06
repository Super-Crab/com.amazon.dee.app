package com.amazon.alexa.wakeword.speakerverification.errors;
/* loaded from: classes11.dex */
public enum ModelDownloadReason {
    DOWNLOADED_NEW_MODEL,
    ALREADY_DOWNLOADED,
    ENGINE_INCOMPATIBLE,
    MODEL_METADATA_NOT_PRESENT,
    MODEL_DATA_NOT_PRESENT,
    DOWNLOAD_FAILURE,
    DOWNLOAD_INTERRUPTED,
    IDENTITY_SERVICE_NOT_AVAILABLE,
    USER_NOT_AUTHENTICATED,
    USER_NOT_LOGGED_IN,
    USER_NOT_ACCEPTED_EULA,
    APP_NOT_INITIALIZED,
    HANDS_FREE_STATE_EXCEPTION;
    
    private static final String DEFAULT_ERROR_DETAILS = "";
    private static final String SEPARATOR = "_";
    private String mErrorDetails = "";

    ModelDownloadReason() {
    }

    public String getFullName() {
        if (this.mErrorDetails.equals("")) {
            return name();
        }
        return name() + "_" + this.mErrorDetails;
    }

    public void setErrorDetails(String str) {
        this.mErrorDetails = str;
    }
}
