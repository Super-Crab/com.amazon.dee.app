package com.amazon.alexa.photos.events;
/* loaded from: classes9.dex */
public enum PhotosUploaderEventType {
    START("photos::upload::start"),
    SUCCESS("photos::upload::success"),
    FAILURE("photos::upload::error"),
    COMPLETE("photos::upload::complete"),
    REMOTELY_DISABLED("photos::upload::remotelyDisabled"),
    LOW_DEVICE_SPACE("photos::upload::lowDeviceStorage"),
    NO_PERMISSIONS("photos::upload::noPermission"),
    INSUFFICIENT_NETWORK("photos::upload::insufficientNetwork"),
    NO_WIFI("photos::upload::noWifi"),
    LOW_BATTERY("photos::upload::lowBattery"),
    NO_STORAGE("photos::upload::noStorage"),
    AUTH_FAILED("photos::upload::authenticationFailed"),
    UPLOAD_PROGRESS_STATUS("photos::upload::uploadProgressStatus"),
    UPLOAD_PROCESSING_COMPLETE("photos::upload::processingComplete");
    
    private final String value;

    PhotosUploaderEventType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
