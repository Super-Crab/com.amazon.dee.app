package com.amazon.clouddrive.cdasdk.cdus;
/* loaded from: classes11.dex */
public enum UploadErrorCode {
    DuplicatesConflictError,
    MissingContentName,
    MissingNodeId,
    MissingUploadId,
    MissingFileSize,
    InvalidFileSize,
    MissingFileMd5,
    MissingPartMd5,
    MissingPartSize,
    InvalidNodeKind,
    UnsupportedNodeKind,
    UnsupportedNodeSubKind,
    MissingFileContent,
    MissingNodeMetadata,
    InvalidNodeMetadataJsonSyntax,
    FileSizeTooSmall,
    FileSizeTooLarge,
    InvalidContentMd5Error,
    InvalidPartNumber,
    InvalidPartSize,
    FileSizeSmallerThanExpected,
    FileSizeLargerThanExpected,
    PartSizeSmallerThanExpected,
    PartSizeLargerThanExpected,
    Md5MismatchError,
    InvalidDateTimeFormat,
    DateTimeNotInUTC,
    SocketException,
    ConcurrentContentEdit,
    ContentSignatureMismatch,
    ContentSignatureDuplicate,
    InvalidContentSignatureType,
    MultipartUploadNotFound,
    MultipartUploadAlreadyAborted,
    MultipartUploadAlreadyCompleted,
    MultipartUploadCompletionInProgress,
    MultipartUploadExpired,
    CompleteUploadWithMissingPartsError,
    CompleteMultipartUploadUnknownError,
    InvalidNodeStateForUpdate,
    AuthenticationError,
    NoActiveSubscriptionFound,
    InsufficientStorage,
    InvalidParent,
    NameAlreadyExists,
    ParentNodeIdNotFound,
    ParentNodeInTrash,
    InvalidRequest,
    UnauthourizedAccess,
    ForbiddenAccess,
    NodeNotFound,
    DuplicateFile,
    TooManyRequests,
    PreconditionFailed,
    FamilyConflict,
    UnclassifiedClientError
}
