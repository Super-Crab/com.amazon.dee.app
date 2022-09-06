package com.amazon.alexa.redesign.utils;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.metrics.ViewRecorderViewApi;
import com.amazon.alexa.redesign.view.templates.carouselChipTemplate.CarouselChipTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.carouselGridTemplate.CarouselGridTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.voxIngressTemplate.VoxIngressTemplateViewItem;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
public class ViewRecorder {
    private final Object cardsSeenBeforeScrollLock = new Object();
    private final HomeContract.HomeMetricsRecorder homeMetricsRecorder;
    protected final ViewRecorderViewApi view;

    public ViewRecorder(ViewRecorderViewApi viewRecorderViewApi, HomeContract.HomeMetricsRecorder homeMetricsRecorder) {
        this.view = viewRecorderViewApi;
        this.homeMetricsRecorder = homeMetricsRecorder;
    }

    private boolean mapContainsCard(Map<Object, Long> map, Object obj) {
        String uniqueStringFromCard = getUniqueStringFromCard(obj);
        for (Object obj2 : map.keySet()) {
            if (uniqueStringFromCard.equals(getUniqueStringFromCard(obj2))) {
                return true;
            }
        }
        return false;
    }

    public Map<Object, Long> getCardsOutOfView(Map<Object, Long> map, Map<Object, Long> map2) {
        HashMap hashMap = new HashMap();
        if (map != null && map2 != null) {
            try {
                synchronized (this.cardsSeenBeforeScrollLock) {
                    Iterator<Map.Entry<Object, Long>> it2 = map.entrySet().iterator();
                    while (it2.hasNext()) {
                        Map.Entry<Object, Long> next = it2.next();
                        Object key = next.getKey();
                        if (!mapContainsCard(map2, key)) {
                            Long value = next.getValue();
                            if (value != null) {
                                hashMap.put(key, value);
                            }
                            it2.remove();
                        }
                    }
                }
            } catch (ConcurrentModificationException e) {
                Log.e("ViewRecorder", "getCardsOutOfView", e);
            }
        }
        return hashMap;
    }

    @VisibleForTesting
    long getCurrentTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    protected Map<String, Object> getTopLevelMetricsObject(Object obj) {
        return null;
    }

    protected String getUniqueStringFromCard(Object obj) {
        return "";
    }

    public Map<Object, Long> recordCardsInRange(int i, int i2) {
        HashMap hashMap = new HashMap();
        if (i >= 0 && i2 >= 0) {
            int i3 = i;
            while (i3 <= i2) {
                Object mo2380getViewItemFromPosition = this.view.mo2380getViewItemFromPosition(i3);
                if (i3 == i || i3 == i2) {
                    if (!this.view.shouldRecordViewForCard(this.view.getPercentCardOnScreen(i3 == i, i3), i3)) {
                        i3++;
                    }
                }
                if (mo2380getViewItemFromPosition != null) {
                    hashMap.put(this.view.mo2380getViewItemFromPosition(i3), Long.valueOf(getCurrentTimeInMillis()));
                }
                i3++;
            }
        }
        return hashMap;
    }

    public void recordViewEventForCardsOutOfView(@Nullable Map<Object, Long> map, boolean z) {
        Map<String, Object> topLevelMetricsObject;
        Long value;
        if (map == null) {
            return;
        }
        for (Map.Entry<Object, Long> entry : map.entrySet()) {
            Object key = entry.getKey();
            if (key != null) {
                boolean z2 = key instanceof CarouselGridTemplateViewItem;
                if (z2 || (key instanceof CarouselChipTemplateViewItem)) {
                    this.view.recordViewsForVisibleCarouselItems();
                }
                if (!(key instanceof VoxIngressTemplateViewItem) && !z2 && (topLevelMetricsObject = getTopLevelMetricsObject(key)) != null && (value = entry.getValue()) != null) {
                    long max = Math.max(getCurrentTimeInMillis() - value.longValue(), 0L);
                    if (z || max >= Constants.MIN_DURATION_FOR_VIEW) {
                        HashMap hashMap = new HashMap(topLevelMetricsObject);
                        hashMap.put("duration", Long.valueOf(max));
                        this.homeMetricsRecorder.recordViewEvent(hashMap, false);
                    }
                }
            }
        }
    }

    public void updateAndRecordCardsOnScreen(Map<Object, Long> map, boolean z) {
        Map<Object, Long> recordCardsInRange = recordCardsInRange(this.view.getFirstVisibleItemPosition(), this.view.getLastVisibleItemPosition());
        try {
            synchronized (this.cardsSeenBeforeScrollLock) {
                Iterator<Object> it2 = map.keySet().iterator();
                while (it2.hasNext()) {
                    if (!mapContainsCard(recordCardsInRange, it2.next())) {
                        it2.remove();
                    }
                }
                for (Object obj : recordCardsInRange.keySet()) {
                    if (!mapContainsCard(map, obj)) {
                        map.put(obj, Long.valueOf(getCurrentTimeInMillis()));
                    }
                }
                recordViewEventForCardsOutOfView(map, z);
            }
        } catch (ConcurrentModificationException e) {
            Log.e("ViewRecorder", "updateAndRecordCardsOnScreen", e);
        }
    }

    public void updateCardsSeenBeforeNextScroll(Map<Object, Long> map, Map<Object, Long> map2) {
        Long value;
        if (map2 == null) {
            return;
        }
        for (Map.Entry<Object, Long> entry : map2.entrySet()) {
            Object key = entry.getKey();
            if (key != null && !mapContainsCard(map, key) && (value = entry.getValue()) != null) {
                map.put(key, value);
            }
        }
    }
}
