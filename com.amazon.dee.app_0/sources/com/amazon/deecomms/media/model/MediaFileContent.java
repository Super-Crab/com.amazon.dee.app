package com.amazon.deecomms.media.model;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.File;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public class MediaFileContent extends MediaContentBase {
    private File file;

    public MediaFileContent() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaFileContent)) {
            return false;
        }
        MediaFileContent mediaFileContent = (MediaFileContent) obj;
        return Objects.equals(this.file, mediaFileContent.file) && Objects.equals(getMediaId(), mediaFileContent.getMediaId()) && Objects.equals(Long.valueOf(getClientId()), Long.valueOf(mediaFileContent.getClientId())) && Objects.equals(getContentType(), mediaFileContent.getContentType());
    }

    public File getFile() {
        return this.file;
    }

    public int hashCode() {
        return Objects.hash(this.file);
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("MediaFileContent{file = ");
        outline1.append(this.file.getAbsolutePath());
        outline1.append(", mediaId = ");
        outline1.append(getMediaId());
        outline1.append(", clientId = ");
        outline1.append(getClientId());
        outline1.append(", contentType = ");
        outline1.append(getContentType());
        outline1.append(JsonReaderKt.END_OBJ);
        return outline1.toString();
    }

    public MediaFileContent(File file) {
        this.file = file;
    }
}
