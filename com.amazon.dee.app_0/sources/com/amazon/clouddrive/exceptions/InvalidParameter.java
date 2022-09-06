package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class InvalidParameter extends NoRetryException {
    public static final String CONTENT_LENGTH_NOT_MATCH_OVERWRITE_REQ_WRITER = "ContentLengthNotMatchOverwriteReqWriter";
    public static final String CONTENT_LENGTH_NOT_MATCH_RESUME_REQ_WRITER = "ContentLengthNotMatchResumeReqWriter";
    public static final String CONTENT_LENGTH_NOT_MATCH_UPLOAD_REQ_WRITER = "ContentLengthNotMatchUploadReqWriter";
    public static final String FILE_TOO_LARGE = "FileTooLarge";
    private static final long serialVersionUID = -1;

    public InvalidParameter() {
    }

    @Override // com.amazon.clouddrive.exceptions.NoRetryException, com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return 400;
    }

    public InvalidParameter(Throwable th) {
        super(th);
    }

    public InvalidParameter(String str) {
        super(str);
    }

    public InvalidParameter(String str, Throwable th) {
        super(str, th);
    }

    public InvalidParameter(String str, String str2, String str3) {
        super(str, str2, str3);
    }
}
