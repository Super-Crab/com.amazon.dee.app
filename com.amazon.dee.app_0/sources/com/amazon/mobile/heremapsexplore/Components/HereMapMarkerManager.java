package com.amazon.mobile.heremapsexplore.Components;

import android.graphics.Bitmap;
import android.view.View;
import com.amazon.mobile.heremapsexplore.Constants.Events;
import com.amazon.mobile.heremapsexplore.Constants.MarkerIconType;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.mobile.heremapsexplore.Factories.ImageFactory;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes13.dex */
public class HereMapMarkerManager extends ViewGroupManager<HereMapMarker> {
    private static final String REACT_CLASS_NAME = "HereMapMarker";
    private Map<String, HereMapMarkerSharedIcon> sharedIcons = new ConcurrentHashMap();

    private void setAnchorForMarkerWithIcontype(HereMapMarker hereMapMarker, String str) {
        MarkerIconType of;
        if (str == null || (of = MarkerIconType.of(str)) == null) {
            return;
        }
        hereMapMarker.setAnchor(of.getLayoutPosition().getAnchor());
    }

    private void setIconSourceForMarker(HereMapMarker hereMapMarker, String str, Map map) {
        boolean z;
        String str2;
        String iconSource = hereMapMarker.getIconSource();
        if (iconSource != null) {
            removeMarkerForSharedIcon(iconSource, hereMapMarker);
            removeSharedIconIfEmpty(iconSource);
        }
        if (str != null) {
            str2 = str.equals(MarkerIconType.CLUSTER_ICON.toString()) ? HereMapMarkerSharedIcon.generateIconKey(MarkerIconType.CLUSTER_ICON.toString(), map) : str;
            HereMapMarkerSharedIcon sharedIcon = getSharedIcon(str2);
            sharedIcon.addMarker(hereMapMarker);
            z = sharedIcon.shouldLoadImage();
        } else {
            z = true;
            str2 = str;
        }
        hereMapMarker.setIconSource(str2);
        if (!z) {
            return;
        }
        if (str == null) {
            hereMapMarker.setIcon(null);
            return;
        }
        MarkerIconType of = MarkerIconType.of(str);
        if (of == null) {
            return;
        }
        updateIconForSharedIcon(str2, ImageFactory.getImage(of.getImageId(), map));
        doneLoadingImageForIcon(str2);
    }

    public void doneLoadingImageForIcon(String str) {
        getSharedIcon(str).doneLoadingImage();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put(Events.HereMapMarker.ON_PRESS, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", Events.HereMapMarker.ON_PRESS))).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return REACT_CLASS_NAME;
    }

    public HereMapMarkerSharedIcon getSharedIcon(String str) {
        HereMapMarkerSharedIcon hereMapMarkerSharedIcon = this.sharedIcons.get(str);
        if (hereMapMarkerSharedIcon == null) {
            synchronized (this) {
                hereMapMarkerSharedIcon = this.sharedIcons.get(str);
                if (hereMapMarkerSharedIcon == null) {
                    hereMapMarkerSharedIcon = new HereMapMarkerSharedIcon();
                    this.sharedIcons.put(str, hereMapMarkerSharedIcon);
                }
            }
        }
        return hereMapMarkerSharedIcon;
    }

    public void removeMarkerForSharedIcon(String str, HereMapMarker hereMapMarker) {
        getSharedIcon(str).removeMarker(hereMapMarker);
    }

    public void removeSharedIconIfEmpty(String str) {
        HereMapMarkerSharedIcon hereMapMarkerSharedIcon = this.sharedIcons.get(str);
        if (hereMapMarkerSharedIcon != null && !hereMapMarkerSharedIcon.hasMarker()) {
            synchronized (this) {
                HereMapMarkerSharedIcon hereMapMarkerSharedIcon2 = this.sharedIcons.get(str);
                if (hereMapMarkerSharedIcon2 != null && !hereMapMarkerSharedIcon2.hasMarker()) {
                    this.sharedIcons.remove(str);
                }
            }
        }
    }

    @ReactProp(name = "anchor")
    public void setAnchor(HereMapMarker hereMapMarker, ReadableMap readableMap) {
        double d = 0.5d;
        double d2 = (readableMap == null || !readableMap.hasKey(ReactProperties.HereMapMarker.X)) ? 0.5d : readableMap.getDouble(ReactProperties.HereMapMarker.X);
        if (readableMap != null && readableMap.hasKey(ReactProperties.HereMapMarker.Y)) {
            d = readableMap.getDouble(ReactProperties.HereMapMarker.Y);
        }
        hereMapMarker.setAnchor(d2, d);
    }

    @ReactProp(name = ReactProperties.HereMapMarker.CHILDREN_SIZE)
    public void setChildrenSize(HereMapMarker hereMapMarker, ReadableMap readableMap) {
        hereMapMarker.updateChildrenSize(readableMap);
    }

    @ReactProp(name = "coordinate")
    public void setCoordinate(HereMapMarker hereMapMarker, ReadableMap readableMap) {
        hereMapMarker.setCoordinate(readableMap);
    }

    @ReactProp(name = ReactProperties.HereMapMarker.ICON_DATA)
    public void setIconData(HereMapMarker hereMapMarker, ReadableMap readableMap) {
        String string = readableMap.getString(ReactProperties.HereMapMarker.ICON_TYPE);
        HashMap<String, Object> hashMap = new HashMap<>();
        if (readableMap.hasKey(ReactProperties.HereMapMarker.EXTRA_DATA)) {
            hashMap = readableMap.mo6945getMap(ReactProperties.HereMapMarker.EXTRA_DATA).toHashMap();
        }
        setIconSourceForMarker(hereMapMarker, string, hashMap);
        setAnchorForMarkerWithIcontype(hereMapMarker, string);
    }

    public void updateIconForSharedIcon(String str, Bitmap bitmap) {
        getSharedIcon(str).updateIcon(bitmap);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(HereMapMarker hereMapMarker, View view, int i) {
        super.addView((HereMapMarkerManager) hereMapMarker, view, i);
        hereMapMarker.update(true);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    /* renamed from: createShadowNodeInstance */
    public LayoutShadowNode mo10136createShadowNodeInstance() {
        return new SizeReportingShadowNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public HereMapMarker mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new HereMapMarker(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(HereMapMarker hereMapMarker, int i) {
        super.removeViewAt((HereMapMarkerManager) hereMapMarker, i);
        hereMapMarker.update(true);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void updateExtraData(HereMapMarker hereMapMarker, Object obj) {
        HashMap hashMap = (HashMap) obj;
        hereMapMarker.update((int) ((Float) hashMap.get("width")).floatValue(), (int) ((Float) hashMap.get("height")).floatValue());
    }
}
