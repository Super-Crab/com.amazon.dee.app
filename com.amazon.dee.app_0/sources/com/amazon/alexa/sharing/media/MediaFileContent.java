package com.amazon.alexa.sharing.media;

import com.amazon.alexa.sharing.media.model.MediaContentBase;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes10.dex */
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaFileContent{file = ");
        outline107.append(this.file.getAbsolutePath());
        outline107.append(", mediaId = ");
        outline107.append(getMediaId());
        outline107.append(", clientId = ");
        outline107.append(getClientId());
        outline107.append(", contentType = ");
        outline107.append(getContentType());
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public MediaFileContent(File file) {
        this.file = file;
    }
}
