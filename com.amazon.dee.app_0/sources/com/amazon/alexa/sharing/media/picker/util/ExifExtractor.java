package com.amazon.alexa.sharing.media.picker.util;

import androidx.annotation.RequiresApi;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.sharing.util.DeviceInfoUtil;
import com.amazon.alexa.sharing.util.FileUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class ExifExtractor {
    private final DeviceInfoUtil deviceInfoUtil;
    private final FileUtil fileUtil;

    public ExifExtractor() {
        this.deviceInfoUtil = new DeviceInfoUtil();
        this.fileUtil = new FileUtil();
    }

    public HashMap<String, String> extractExif(String str) throws IOException {
        List<String> basicAttributes = getBasicAttributes();
        if (this.deviceInfoUtil.isMAndAbove()) {
            basicAttributes.addAll(getLevel23Attributes());
        }
        ExifInterface createExifInterface = this.fileUtil.createExifInterface(str);
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : basicAttributes) {
            hashMap.put(str2, createExifInterface.getAttribute(str2));
        }
        return hashMap;
    }

    List<String> getBasicAttributes() {
        return new ArrayList(Arrays.asList(ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_DATETIME, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_FLASH, ExifInterface.TAG_FOCAL_LENGTH, ExifInterface.TAG_GPS_ALTITUDE, ExifInterface.TAG_GPS_ALTITUDE_REF, ExifInterface.TAG_GPS_DATESTAMP, ExifInterface.TAG_GPS_LATITUDE, ExifInterface.TAG_GPS_LATITUDE_REF, ExifInterface.TAG_GPS_LONGITUDE, ExifInterface.TAG_GPS_LONGITUDE_REF, ExifInterface.TAG_GPS_PROCESSING_METHOD, ExifInterface.TAG_GPS_TIMESTAMP, ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.TAG_ISO_SPEED_RATINGS, ExifInterface.TAG_MAKE, ExifInterface.TAG_MODEL, ExifInterface.TAG_ORIENTATION, ExifInterface.TAG_WHITE_BALANCE));
    }

    @RequiresApi(23)
    List<String> getLevel23Attributes() {
        return new ArrayList(Arrays.asList(ExifInterface.TAG_DATETIME_DIGITIZED, ExifInterface.TAG_SUBSEC_TIME, ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, ExifInterface.TAG_SUBSEC_TIME_ORIGINAL));
    }

    @Inject
    public ExifExtractor(FileUtil fileUtil, DeviceInfoUtil deviceInfoUtil) {
        this.deviceInfoUtil = deviceInfoUtil;
        this.fileUtil = fileUtil;
    }
}
