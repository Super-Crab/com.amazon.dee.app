package com.amazon.dee.app.ui.clouddrive;

import android.graphics.Bitmap;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.dee.app.R;
/* loaded from: classes12.dex */
public enum DeviceScreenConfiguration {
    BISHOP(1280, 800, "AWZZ5CVHX2CD", CropGeometry.RECTANGULAR),
    KNIGHT(1024, DeviceConfigConstants.VIDEO_BITRATE_600_KBPS, "A1NL4BVLQ4L3N3", CropGeometry.RECTANGULAR),
    ROOK(480, 480, "A10A33FOX2NUBK", CropGeometry.CIRCULAR),
    VEGA(1920, 1200, "A2J0R2SD7G9LPA", CropGeometry.RECTANGULAR),
    CHECKERS(980, 460, "A4ZP7ZC4PI6TO", CropGeometry.RECTANGULAR),
    UNKNOWN(1280, 800, "", CropGeometry.RECTANGULAR);
    
    public final float aspectRatio;
    public final int description = setDescription();
    public final CropGeometry geometry;
    public final int height;
    public final String type;
    public final int width;

    DeviceScreenConfiguration(int i, int i2, String str, CropGeometry cropGeometry) {
        this.width = i;
        this.height = i2;
        this.type = str;
        this.geometry = cropGeometry;
        this.aspectRatio = i / i2;
    }

    public static DeviceScreenConfiguration getByType(String str) {
        DeviceScreenConfiguration[] values;
        for (DeviceScreenConfiguration deviceScreenConfiguration : values()) {
            if (deviceScreenConfiguration.type.equals(str)) {
                return deviceScreenConfiguration;
            }
        }
        return UNKNOWN;
    }

    private int setDescription() {
        return this.geometry == CropGeometry.CIRCULAR ? R.string.alexa_device_background_image_rook_crop_and_zoom_description : R.string.alexa_device_background_image_knight_crop_and_zoom_description;
    }

    public double getScreenScale(Bitmap bitmap) {
        return this.width / bitmap.getWidth();
    }
}
