package com.amazon.alexa.biloba.model;

import java.util.List;
/* loaded from: classes6.dex */
public class TimeZoneRegionItem {
    private final TimeZoneItem region;
    private final List<TimeZoneItem> zones;

    public TimeZoneRegionItem(TimeZoneItem timeZoneItem, List<TimeZoneItem> list) {
        this.region = timeZoneItem;
        this.zones = list;
    }

    public TimeZoneItem getRegion() {
        return this.region;
    }

    public List<TimeZoneItem> getZones() {
        return this.zones;
    }
}
