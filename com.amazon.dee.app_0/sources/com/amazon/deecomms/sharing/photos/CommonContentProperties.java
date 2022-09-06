package com.amazon.deecomms.sharing.photos;

import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.storage.LocalStorageHelper;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.deecomms.nativemodules.imagepicker.util.Compression;
import com.amazon.deecomms.nativemodules.imagepicker.util.ExifExtractor;
import com.amazon.deecomms.sharing.exceptions.SharingSDKException;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public class CommonContentProperties {
    private String base64;
    private HashMap<String, String> exif;
    private int height;
    private String mime;
    private String modificationDate;
    private int orientation;
    private String path;
    private int size;
    private int width;

    /* loaded from: classes12.dex */
    public static class ImageBuilder {
        public String path = null;
        public String mime = null;
        public String modificationDate = null;
        public int orientation = -1;
        public int size = -1;
        public int width = -1;
        public int height = -1;
        public HashMap<String, String> exif = null;
        public String base64 = null;

        public CommonContentProperties build() {
            if (this.base64 == null && this.path == null) {
                throw new IllegalArgumentException("Builder requires a path or data to be provided");
            }
            CommonContentProperties commonContentProperties = new CommonContentProperties();
            commonContentProperties.setBase64(this.base64);
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

        @NonNull
        public ImageBuilder setBase64(@Nullable String str) {
            this.base64 = str;
            return this;
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

    public static CommonContentProperties fromPath(@NonNull Uri uri, @NonNull Compression compression, @Nullable ReadableMap readableMap, @NonNull boolean z, @NonNull boolean z2) throws SharingSDKException, IOException {
        BitmapOptionsFile prepareOptionsForContentURI;
        String uri2 = uri.toString();
        if (!uri2.startsWith("http://") && !uri2.startsWith("https://")) {
            if (uri2.startsWith("file://")) {
                prepareOptionsForContentURI = compression.prepareOptionsForFileURI(uri);
            } else {
                prepareOptionsForContentURI = compression.prepareOptionsForContentURI(uri);
            }
            File file = prepareOptionsForContentURI.getFile();
            BitmapFactory.Options options = prepareOptionsForContentURI.getOptions();
            if (readableMap != null) {
                file = compression.compressImage(readableMap, uri2, options);
                uri2 = file.getPath();
                options = compression.validateImage(uri2);
            }
            long lastModified = file.lastModified();
            int orientationFromFile = compression.getOrientationFromFile(uri2);
            String str = null;
            HashMap<String, String> extractExifFromFile = z ? compression.extractExifFromFile(uri2) : null;
            if (z2) {
                str = compression.getBase64StringFromFile(uri2);
            }
            return new ImageBuilder().setPath(uri2).setMime(options.outMimeType).setModificationDate(String.valueOf(lastModified)).setOrientation(orientationFromFile).setSize((int) prepareOptionsForContentURI.getFile().length()).setWidth(options.outWidth).setHeight(options.outHeight).setExif(extractExifFromFile).setBase64(str).build();
        }
        throw new SharingSDKException("Cannot select remote files");
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
        return this.orientation == commonContentProperties.orientation && this.size == commonContentProperties.size && this.width == commonContentProperties.width && this.height == commonContentProperties.height && Objects.equals(this.path, commonContentProperties.path) && Objects.equals(this.mime, commonContentProperties.mime) && Objects.equals(this.modificationDate, commonContentProperties.modificationDate) && Objects.equals(this.exif, commonContentProperties.exif) && Objects.equals(this.base64, commonContentProperties.base64);
    }

    public String getMimeType() {
        return this.mime;
    }

    public int hashCode() {
        return Objects.hash(this.path, this.mime, this.modificationDate, Integer.valueOf(this.orientation), Integer.valueOf(this.size), Integer.valueOf(this.width), Integer.valueOf(this.height), this.exif, this.base64);
    }

    public void setBase64(String str) {
        this.base64 = str;
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
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("CommonContentProperties{path='");
        GeneratedOutlineSupport1.outline176(outline1, this.path, Chars.QUOTE, ", mime='");
        GeneratedOutlineSupport1.outline176(outline1, this.mime, Chars.QUOTE, ", modificationDate='");
        GeneratedOutlineSupport1.outline176(outline1, this.modificationDate, Chars.QUOTE, ", orientation=");
        outline1.append(this.orientation);
        outline1.append(", size=");
        outline1.append(this.size);
        outline1.append(", width=");
        outline1.append(this.width);
        outline1.append(", height=");
        outline1.append(this.height);
        outline1.append(", exif=");
        outline1.append(this.exif);
        outline1.append(", base64='");
        return GeneratedOutlineSupport1.outline90(outline1, this.base64, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    public WritableMap toWritableMap() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("file://");
        outline1.append(this.path);
        writableNativeMap.putString(RouteParameter.PATH, outline1.toString());
        writableNativeMap.putInt("width", this.width);
        writableNativeMap.putInt("height", this.height);
        writableNativeMap.putString("mime", this.mime);
        writableNativeMap.putString("sourceURL", "file://" + this.path);
        writableNativeMap.putInt("size", (int) new File(this.path).length());
        writableNativeMap.putString(LocalStorageHelper.MODIFICATION_DATE_KEY, String.valueOf(this.modificationDate));
        writableNativeMap.putInt("orientation", this.orientation);
        String str = this.base64;
        if (str != null) {
            writableNativeMap.putString("data", str);
        }
        HashMap<String, String> hashMap = this.exif;
        if (hashMap != null) {
            writableNativeMap.putMap("exif", ExifExtractor.exifHashMapToWritableMap(hashMap));
        }
        return writableNativeMap;
    }
}
