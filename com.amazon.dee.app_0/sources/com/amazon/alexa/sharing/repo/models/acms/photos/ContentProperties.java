package com.amazon.alexa.sharing.repo.models.acms.photos;

import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.repo.models.acms.photos.enums.AttachmentMediaType;
import com.amazon.alexa.sharing.repo.models.acms.photos.enums.FileExtension;
import java.util.Date;
/* loaded from: classes10.dex */
public class ContentProperties {
    @Nullable
    private Date creationDate;
    @Nullable
    private String data;
    private FileExtension fileExtension;
    private String filename;
    private int height;
    @Nullable
    private String localIdentifier;
    private AttachmentMediaType mediaType;
    @Nullable
    private String mime;
    @Nullable
    private Date modificationDate;
    @Nullable
    private int orientation;
    @Nullable
    private String path;
    @Nullable
    private int size;
    @Nullable
    private String sourceURL;
    private String thumbnailUri;
    private int width;
}
