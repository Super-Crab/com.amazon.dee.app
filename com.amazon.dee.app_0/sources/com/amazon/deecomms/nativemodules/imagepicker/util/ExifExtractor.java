package com.amazon.deecomms.nativemodules.imagepicker.util;

import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public final class ExifExtractor {
    private ExifExtractor() {
    }

    public static WritableMap exifHashMapToWritableMap(HashMap<String, String> hashMap) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            writableNativeMap.putString(entry.getKey(), entry.getValue());
        }
        return writableNativeMap;
    }

    public static WritableMap extract(String str) throws IOException {
        return exifHashMapToWritableMap(extractExif(str));
    }

    public static HashMap<String, String> extractExif(String str) throws IOException {
        List<String> basicAttributes = getBasicAttributes();
        int i = Build.VERSION.SDK_INT;
        basicAttributes.addAll(getLevel23Attributes());
        ExifInterface exifInterface = new ExifInterface(str);
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : basicAttributes) {
            hashMap.put(str2, exifInterface.getAttribute(str2));
        }
        return hashMap;
    }

    private static List<String> getBasicAttributes() {
        return new ArrayList(Arrays.asList(ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_DATETIME, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_FLASH, ExifInterface.TAG_FOCAL_LENGTH, ExifInterface.TAG_GPS_ALTITUDE, ExifInterface.TAG_GPS_ALTITUDE_REF, ExifInterface.TAG_GPS_DATESTAMP, ExifInterface.TAG_GPS_LATITUDE, ExifInterface.TAG_GPS_LATITUDE_REF, ExifInterface.TAG_GPS_LONGITUDE, ExifInterface.TAG_GPS_LONGITUDE_REF, ExifInterface.TAG_GPS_PROCESSING_METHOD, ExifInterface.TAG_GPS_TIMESTAMP, ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.TAG_ISO_SPEED_RATINGS, ExifInterface.TAG_MAKE, ExifInterface.TAG_MODEL, ExifInterface.TAG_ORIENTATION, ExifInterface.TAG_WHITE_BALANCE));
    }

    @RequiresApi(23)
    private static List<String> getLevel23Attributes() {
        return new ArrayList(Arrays.asList(ExifInterface.TAG_DATETIME_DIGITIZED, ExifInterface.TAG_SUBSEC_TIME, ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, ExifInterface.TAG_SUBSEC_TIME_ORIGINAL));
    }
}
