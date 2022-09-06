package com.amazon.alexa.sharing.api.exceptions;
/* loaded from: classes10.dex */
public enum BridgeError {
    ImagePickerCancelled("1001", "User cancelled image selection"),
    FailedToStartCameraActivity("1002", "Failed to start camera activity"),
    FailedToStartPickerActivity("1003", "Failed to start picker activity"),
    ActivityNotExists("1004", "Android Activity does not exist"),
    ImageDataNotFound("1005", "Unable to get image data from uri"),
    PermissionsDenied("1006", "Required permission to launch camera/picker is denied by user"),
    CameraNotAvailable("1007", "Camera is not found in user device"),
    FailedToLaunchCamera("1008", "Failed to launch camera"),
    FailedToInvokeCallable("1009", "Failed to Invoke callable"),
    InvalidUploadRequestPayload("2200", "Invalid media upload request"),
    CDSUploadSetupFailure("2201", "CDS setup for upload failed"),
    InvalidDownloadRequestPayload("2300", "Invalid media download request"),
    CDSDownloadSetupFailure("2301", "CDS setup for download failed"),
    InvalidMediaFileRemovalRequestPayload("2400", "Invalid media file removal request"),
    InvalidSendMessageRequestPayload("2500", "Invalid Send Message Request Payload"),
    CreateNewMessageAPIFailure("2501", "Create new message ACMS API failure"),
    CreateNewMessageAPINullResponse("2502", "Create new message ACMS API returned null response"),
    InvalidACMSResponsePayload("2503", "ACMS response payload syntax is invalid. Failed to parse"),
    NullACMSResponsePayload("2504", "Response payload is null while parsing the ACMS response"),
    MessagePayloadMissingInACMSResponse("2505", "Response payload does not contain message payload"),
    ZeroMessagesInACMSResponsePayload("2506", "Response payload contain 0 messages"),
    InvalidMessagePayloadInACMSResponse("2507", "Response payload contains invalid message payload"),
    InvalidMessageTypeInACMSResponse("2508", "Response payload contains message payload with invalid type");
    
    private final String code;
    private final String message;

    BridgeError(String str, String str2) {
        this.code = str;
        this.message = str2;
    }

    public final String code() {
        return this.code;
    }

    public final String message() {
        return this.message;
    }
}
