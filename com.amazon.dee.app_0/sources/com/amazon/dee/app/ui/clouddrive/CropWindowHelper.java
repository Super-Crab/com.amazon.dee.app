package com.amazon.dee.app.ui.clouddrive;

import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes12.dex */
public class CropWindowHelper implements Parcelable {
    public static final Parcelable.Creator<CropWindowHelper> CREATOR = new Parcelable.Creator<CropWindowHelper>() { // from class: com.amazon.dee.app.ui.clouddrive.CropWindowHelper.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public CropWindowHelper mo3600createFromParcel(Parcel parcel) {
            return new CropWindowHelper(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public CropWindowHelper[] mo3601newArray(int i) {
            return new CropWindowHelper[i];
        }
    };
    private final float aspectRatio;
    private final CropGeometry cropGeometry;
    private RectF cropRect;
    private float imageViewHeight;
    private float imageViewWidth;

    public CropWindowHelper(DeviceScreenConfiguration deviceScreenConfiguration, float f, float f2) {
        this.aspectRatio = deviceScreenConfiguration.aspectRatio;
        this.cropGeometry = deviceScreenConfiguration.geometry;
        this.imageViewWidth = f;
        this.imageViewHeight = f2;
        this.cropRect = null;
    }

    private RectF calculateCircularCropView(RectF rectF) {
        RectF rectF2 = new RectF(rectF);
        float height = rectF.height();
        float width = rectF.width();
        float abs = Math.abs(width - height) / 2.0f;
        if (abs > 0.0f) {
            if (width > height) {
                rectF2.left += abs;
                rectF2.right -= abs;
            } else {
                rectF2.top += abs;
                rectF2.bottom -= abs;
            }
        }
        float f = this.imageViewWidth - rectF2.right;
        float f2 = rectF2.left;
        float width2 = rectF2.width();
        rectF2.left = (f + f2) / 2.0f;
        rectF2.right = rectF2.left + width2;
        return rectF2;
    }

    private RectF calculateRectangularCropView(RectF rectF) {
        RectF rectF2 = new RectF(rectF);
        float f = this.imageViewWidth;
        float f2 = f / this.aspectRatio;
        rectF2.top = (this.imageViewHeight - f2) / 2.0f;
        rectF2.bottom = rectF2.top + f2;
        rectF2.right = f;
        return rectF2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CropGeometry getCropGeometry() {
        return this.cropGeometry;
    }

    public RectF getCropRect() {
        return this.cropRect;
    }

    public float getCropWindowHeight() {
        RectF rectF = this.cropRect;
        return rectF.bottom - rectF.top;
    }

    public float getCropWindowWidth() {
        RectF rectF = this.cropRect;
        return rectF.right - rectF.left;
    }

    public void initializeCropView(RectF rectF) {
        if (this.cropGeometry == CropGeometry.CIRCULAR) {
            this.cropRect = calculateCircularCropView(rectF);
        } else {
            this.cropRect = calculateRectangularCropView(rectF);
        }
    }

    public void setImageViewSize(int i, int i2) {
        this.imageViewHeight = i2;
        this.imageViewWidth = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.aspectRatio);
        parcel.writeInt(this.cropGeometry.ordinal());
        parcel.writeFloat(this.imageViewWidth);
        parcel.writeFloat(this.imageViewHeight);
        parcel.writeParcelable(this.cropRect, i);
    }

    public CropWindowHelper(Parcel parcel) {
        this.aspectRatio = parcel.readFloat();
        this.cropGeometry = CropGeometry.values()[parcel.readInt()];
        this.imageViewWidth = parcel.readFloat();
        this.imageViewHeight = parcel.readFloat();
        this.cropRect = (RectF) parcel.readParcelable(RectF.class.getClassLoader());
    }
}
