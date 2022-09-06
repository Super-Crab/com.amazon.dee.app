package com.amazon.mobile.heremapsexplore.Factories.Builders;

import android.graphics.Bitmap;
import com.amazon.mobile.heremapsexplore.Constants.Resources;
import com.amazon.mobile.heremapsexplore.R;
import com.amazon.mobile.heremapsexplore.Utilities.ImageUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class FindMyDeviceImageBuilder implements ImageBuilder {
    private Bitmap foreground = null;
    private Bitmap background = null;
    private Bitmap result = null;
    private DeviceState state = DeviceState.ACTIVE;
    private DeviceImageSize size = DeviceImageSize.SMALL;
    private boolean hasShadowDropped = true;
    private boolean hasWhiteCircle = true;

    /* loaded from: classes13.dex */
    public enum DeviceImageSize {
        SMALL,
        LARGE
    }

    /* loaded from: classes13.dex */
    public enum DeviceState {
        ACTIVE,
        INACTIVE
    }

    private FindMyDeviceImageBuilder() {
    }

    private void addWhiteCircleUnderForeground() {
        if (this.foreground == null || !this.hasWhiteCircle) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("radius", Integer.valueOf(this.size == DeviceImageSize.SMALL ? 14 : 18));
        hashMap.put("color", -1);
        this.foreground = ImageUtils.getInstance().combineImage(this.foreground, ImageUtils.getInstance().drawCircles(Arrays.asList(hashMap)), ImageUtils.ImageLayoutPosition.CENTER);
    }

    private void combineForegroundWithBackgroundIntoResult() {
        Bitmap bitmap = this.foreground;
        if (bitmap == null) {
            this.result = this.background;
            return;
        }
        Bitmap bitmap2 = this.background;
        if (bitmap2 == null) {
            this.result = bitmap;
        } else if (this.size == DeviceImageSize.SMALL) {
            this.result = ImageUtils.getInstance().combineImage(this.foreground, this.background, ImageUtils.ImageLayoutPosition.CENTER);
        } else {
            int round = (int) Math.round((bitmap2.getWidth() - this.foreground.getWidth()) / 2.0d);
            this.result = ImageUtils.getInstance().combineImageWithForegroundOriginInPx(this.foreground, this.background, round, round);
        }
    }

    private void createBackground() {
        if (this.size == DeviceImageSize.SMALL) {
            ArrayList arrayList = new ArrayList();
            if (this.state == DeviceState.ACTIVE) {
                HashMap hashMap = new HashMap();
                hashMap.put("radius", 20);
                hashMap.put("color", Integer.valueOf(Resources.Colors.SPEECH_RED_SECONDARY));
                arrayList.add(hashMap);
                HashMap hashMap2 = new HashMap();
                hashMap2.put("radius", 18);
                hashMap2.put("color", Integer.valueOf(Resources.Colors.DELETE_RED));
                arrayList.add(hashMap2);
            } else {
                HashMap hashMap3 = new HashMap();
                hashMap3.put("radius", 20);
                hashMap3.put("color", Integer.valueOf(Resources.Colors.BLUE_BAYOUX));
                arrayList.add(hashMap3);
            }
            this.background = ImageUtils.getInstance().drawCircles(arrayList);
        } else if (this.state == DeviceState.ACTIVE) {
            this.background = ImageUtils.getInstance().loadImage(R.drawable.ic_pin_active_findmy);
        } else {
            this.background = ImageUtils.getInstance().loadImage(R.drawable.ic_pin_inactive_findmy);
        }
    }

    private void dropShadowOnResult() {
        if (this.result == null || !this.hasShadowDropped) {
            return;
        }
        if (this.size == DeviceImageSize.SMALL) {
            this.result = ImageUtils.getInstance().dropShadow(this.result, 0, 2, 4, Resources.Colors.BLUE_BAYOUX);
            return;
        }
        this.result = ImageUtils.getInstance().combineImage(ImageUtils.getInstance().loadImage(R.drawable.ic_shadow_findmy), this.result, 16, 64);
    }

    public static FindMyDeviceImageBuilder newBuilder() {
        return new FindMyDeviceImageBuilder();
    }

    @Override // com.amazon.mobile.heremapsexplore.Factories.Builders.ImageBuilder
    public Bitmap build() {
        createBackground();
        addWhiteCircleUnderForeground();
        combineForegroundWithBackgroundIntoResult();
        dropShadowOnResult();
        return this.result;
    }

    public FindMyDeviceImageBuilder withForeground(Bitmap bitmap) {
        this.foreground = bitmap;
        return this;
    }

    public FindMyDeviceImageBuilder withShadowDropped(boolean z) {
        this.hasShadowDropped = z;
        return this;
    }

    public FindMyDeviceImageBuilder withSize(DeviceImageSize deviceImageSize) {
        this.size = deviceImageSize;
        return this;
    }

    public FindMyDeviceImageBuilder withState(DeviceState deviceState) {
        this.state = deviceState;
        return this;
    }

    public FindMyDeviceImageBuilder withWhiteCircle(boolean z) {
        this.hasWhiteCircle = z;
        return this;
    }
}
