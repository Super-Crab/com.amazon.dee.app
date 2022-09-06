package com.amazon.mobile.heremapsexplore.Components;

import android.graphics.Bitmap;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
/* loaded from: classes13.dex */
public class HereMapMarkerSharedIcon {
    private Bitmap icon;
    private Set<HereMapMarker> markers = Collections.newSetFromMap(new WeakHashMap());
    private boolean loadImageStarted = false;

    public static String generateIconKey(String str, Map map) {
        StringBuilder sb = new StringBuilder();
        sb.append(str + MqttTopic.MULTI_LEVEL_WILDCARD);
        for (Object obj : map.keySet()) {
            sb.append(obj + ":" + map.get(obj).toString() + MqttTopic.MULTI_LEVEL_WILDCARD);
        }
        return sb.toString();
    }

    public synchronized void addMarker(HereMapMarker hereMapMarker) {
        this.markers.add(hereMapMarker);
        if (this.icon != null) {
            hereMapMarker.setIcon(this.icon);
        }
    }

    public synchronized boolean containsMarker(HereMapMarker hereMapMarker) {
        return this.markers.contains(hereMapMarker);
    }

    public synchronized void doneLoadingImage() {
        this.loadImageStarted = true;
    }

    public synchronized boolean hasMarker() {
        return !this.markers.isEmpty();
    }

    public synchronized void removeMarker(HereMapMarker hereMapMarker) {
        this.markers.remove(hereMapMarker);
    }

    public synchronized boolean shouldLoadImage() {
        return !this.loadImageStarted;
    }

    public synchronized void updateIcon(Bitmap bitmap) {
        this.icon = bitmap;
        if (this.markers.isEmpty()) {
            return;
        }
        for (HereMapMarker hereMapMarker : this.markers) {
            if (hereMapMarker != null) {
                hereMapMarker.setIcon(bitmap);
            }
        }
    }
}
