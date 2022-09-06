package com.amazon.alexa.sharing.sharingsdk.photos;

import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingSDKException;
import com.amazon.alexa.sharing.media.picker.util.ImageUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes10.dex */
public class CommonContentProperties {
    private static final String FILE_PREFIX = "file://";
    private HashMap<String, String> exif;
    private int height;
    private String mime;
    private String modificationDate;
    private int orientation;
    private String path;
    private int size;
    private int width;

    /* loaded from: classes10.dex */
    public static class ImageBuilder {
        public String path = null;
        public String mime = null;
        public String modificationDate = null;
        public int orientation = -1;
        public int size = -1;
        public int width = -1;
        public int height = -1;
        public HashMap<String, String> exif = null;

        public CommonContentProperties build() {
            if (this.path != null) {
                CommonContentProperties commonContentProperties = new CommonContentProperties();
                commonContentProperties.setExif(this.exif);
                commonContentProperties.setHeight(this.height);
                commonContentProperties.setMime(this.mime);
                commonContentProperties.setModificationDate(this.modificationDate);
                commonContentProperties.setOrientation(this.orientation);
                commonContentProperties.setPath(this.path);
                commonContentProperties.setSize(this.size);
                commonContentProperties.setWidth(this.width);
                return commonContentProperties;
            }
            throw new IllegalArgumentException("Builder requires a path to be provided");
        }

        @NonNull
        public ImageBuilder setExif(@Nullable HashMap<String, String> hashMap) {
            this.exif = hashMap;
            return this;
        }

        @NonNull
        public ImageBuilder setHeight(int i) {
            this.height = i;
            return this;
        }

        @NonNull
        public ImageBuilder setMime(@NonNull String str) {
            this.mime = str;
            return this;
        }

        @NonNull
        public ImageBuilder setModificationDate(@NonNull String str) {
            this.modificationDate = str;
            return this;
        }

        @NonNull
        public ImageBuilder setOrientation(int i) {
            this.orientation = i;
            return this;
        }

        @NonNull
        public ImageBuilder setPath(@NonNull String str) {
            this.path = str;
            return this;
        }

        @NonNull
        public ImageBuilder setSize(int i) {
            this.size = i;
            return this;
        }

        @NonNull
        public ImageBuilder setWidth(int i) {
            this.width = i;
            return this;
        }
    }

    public static CommonContentProperties fromPath(@NonNull Uri uri, @NonNull ImageUtil imageUtil, @NonNull boolean z) throws AlexaSharingSDKException, IOException {
        BitmapOptionsFile prepareOptionsForContentURI;
        String uri2 = uri.toString();
        if (!uri2.startsWith("http://") && !uri2.startsWith("https://")) {
            if (uri2.startsWith(FILE_PREFIX)) {
                prepareOptionsForContentURI = imageUtil.prepareOptionsForFileURI(uri);
            } else {
                prepareOptionsForContentURI = imageUtil.prepareOptionsForContentURI(uri);
            }
            File file = prepareOptionsForContentURI.getFile();
            BitmapFactory.Options options = prepareOptionsForContentURI.getOptions();
            long lastModified = file.lastModified();
            int orientationFromFile = imageUtil.getOrientationFromFile(uri2);
            HashMap<String, String> hashMap = null;
            if (z) {
                hashMap = imageUtil.extractExifFromFile(uri2);
            }
            return new ImageBuilder().setPath(uri2).setMime(options.outMimeType).setModificationDate(String.valueOf(lastModified)).setOrientation(orientationFromFile).setSize((int) file.length()).setWidth(options.outWidth).setHeight(options.outHeight).setExif(hashMap).build();
        }
        throw new AlexaSharingSDKException("Cannot select remote files");
    }

    public static String getMediaTypeFromMimeType(@Nullable String str) {
        if (str != null) {
            return str.split("/")[0].toLowerCase();
        }
        throw new IllegalArgumentException("mimeType is null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CommonContentProperties.class != obj.getClass()) {
            return false;
        }
        CommonContentProperties commonContentProperties = (CommonContentProperties) obj;
        return this.orientation == commonContentProperties.orientation && this.size == commonContentProperties.size && this.width == commonContentProperties.width && this.height == commonContentProperties.height && Objects.equals(this.path, commonContentProperties.path) && Objects.equals(this.mime, commonContentProperties.mime) && Objects.equals(this.modificationDate, commonContentProperties.modificationDate) && Objects.equals(this.exif, commonContentProperties.exif);
    }

    public HashMap<String, String> getExif() {
        return this.exif;
    }

    public int getHeight() {
        return this.height;
    }

    public String getMimeType() {
        return this.mime;
    }

    public String getModificationDate() {
        return this.modificationDate;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public String getPath() {
        return this.path;
    }

    public int getSize() {
        return this.size;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return Objects.hash(this.path, this.mime, this.modificationDate, Integer.valueOf(this.orientation), Integer.valueOf(this.size), Integer.valueOf(this.width), Integer.valueOf(this.height), this.exif);
    }

    public void setExif(HashMap<String, String> hashMap) {
        this.exif = hashMap;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setMime(String str) {
        this.mime = str;
    }

    public void setModificationDate(String str) {
        this.modificationDate = str;
    }

    public void setOrientation(int i) {
        this.orientation = i;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setSize(int i) {
        this.size = i;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CommonContentProperties{path='");
        GeneratedOutlineSupport1.outline176(outline107, this.path, Chars.QUOTE, ", mime='");
        GeneratedOutlineSupport1.outline176(outline107, this.mime, Chars.QUOTE, ", modificationDate='");
        GeneratedOutlineSupport1.outline176(outline107, this.modificationDate, Chars.QUOTE, ", orientation=");
        outline107.append(this.orientation);
        outline107.append(", size=");
        outline107.append(this.size);
        outline107.append(", width=");
        outline107.append(this.width);
        outline107.append(", height=");
        outline107.append(this.height);
        outline107.append(", exif=");
        outline107.append(this.exif);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
