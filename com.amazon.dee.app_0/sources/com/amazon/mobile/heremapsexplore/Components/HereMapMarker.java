package com.amazon.mobile.heremapsexplore.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import com.amazon.mobile.heremapsexplore.MapObjects.CustomMapMarker;
import com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject;
import com.amazon.mobile.heremapsexplore.Utilities.UIUtils;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.views.view.ReactViewGroup;
import com.here.sdk.core.Anchor2D;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import com.here.sdk.mapview.MapView;
/* loaded from: classes13.dex */
public class HereMapMarker extends ReactViewGroup implements CustomMapObject {
    private Anchor2D anchor;
    private boolean anchorIsSet;
    private final Context context;
    private GeoCoordinates coordinates;
    private boolean hasCustomMarkerView;
    private int height;
    private Bitmap icon;
    private String iconSource;
    private Bitmap lastBitmapCreated;
    private CustomMapMarker marker;
    private int width;

    public HereMapMarker(Context context) {
        super(context);
        this.context = context;
    }

    private void clearDrawableCache() {
        this.lastBitmapCreated = null;
    }

    private Bitmap createDrawable() {
        int i = this.width;
        int i2 = 100;
        if (i <= 0) {
            i = 100;
        }
        int i3 = this.height;
        if (i3 > 0) {
            i2 = i3;
        }
        buildDrawingCache();
        Bitmap bitmap = this.lastBitmapCreated;
        if (bitmap != null && !bitmap.isRecycled() && bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            bitmap.eraseColor(0);
        } else {
            bitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            this.lastBitmapCreated = bitmap;
        }
        draw(new Canvas(bitmap));
        return bitmap;
    }

    private MapImage getIcon() {
        Bitmap bitmap = this.icon;
        if (bitmap != null) {
            return MapImageFactory.fromBitmap(bitmap);
        }
        if (!this.hasCustomMarkerView) {
            return null;
        }
        return MapImageFactory.fromBitmap(createDrawable());
    }

    private void updateMarkerIcon() {
        MapImage icon;
        if (this.marker == null || (icon = getIcon()) == null) {
            return;
        }
        this.marker.setIcon(icon);
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void addToMap(MapView mapView) {
        this.marker = new CustomMapMarker(this.coordinates, mapView);
        update(true);
        this.marker.addToMap(mapView);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        super.addView(view, i);
        this.hasCustomMarkerView = true;
        update(true);
    }

    public String getIconSource() {
        return this.iconSource;
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public Object getObject() {
        return this.marker;
    }

    @Override // com.amazon.mobile.heremapsexplore.MapObjects.CustomMapObject
    public void removeFromMap(MapView mapView) {
        CustomMapMarker customMapMarker = this.marker;
        if (customMapMarker == null) {
            return;
        }
        customMapMarker.removeFromMap(mapView);
        this.marker = null;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        if (getChildCount() != 0 || !this.hasCustomMarkerView) {
            return;
        }
        this.hasCustomMarkerView = false;
        clearDrawableCache();
        update(true);
    }

    public void setAnchor(double d, double d2) {
        setAnchor(new Anchor2D(d, d2));
    }

    public void setCoordinate(ReadableMap readableMap) {
        this.coordinates = new GeoCoordinates(readableMap.getDouble("latitude"), readableMap.getDouble("longitude"));
        CustomMapMarker customMapMarker = this.marker;
        if (customMapMarker != null) {
            customMapMarker.setCoordinates(this.coordinates);
        }
    }

    public void setIcon(Bitmap bitmap) {
        this.icon = bitmap;
        update(true);
    }

    public void setIconSource(String str) {
        this.iconSource = str;
    }

    public void update(int i, int i2) {
        update(true);
    }

    public void updateChildrenSize(ReadableMap readableMap) {
        int i = readableMap.getInt("width");
        int i2 = readableMap.getInt("height");
        this.width = (int) UIUtils.dpToPx(this.context, i);
        this.height = (int) UIUtils.dpToPx(this.context, i2);
        update(true);
    }

    public void setAnchor(Anchor2D anchor2D) {
        this.anchorIsSet = true;
        this.anchor = anchor2D;
        CustomMapMarker customMapMarker = this.marker;
        if (customMapMarker != null) {
            customMapMarker.setAnchor(anchor2D);
        }
        update(false);
    }

    public void update(boolean z) {
        if (this.marker == null) {
            return;
        }
        if (z) {
            updateMarkerIcon();
        }
        if (!this.anchorIsSet) {
            return;
        }
        this.marker.setAnchor(this.anchor);
    }
}
