package com.amazon.alexa.alertsca.metrics;

import android.text.TextUtils;
import java.util.LinkedList;
import java.util.Objects;
/* loaded from: classes6.dex */
public class MetricsElement {
    private final String name;
    private MetricsElement parent;

    public MetricsElement(String str) {
        this.name = str;
    }

    public MetricsElement element(String str) {
        return new MetricsElement(str, this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return Objects.equals(getEventName(), ((MetricsElement) obj).getEventName());
        }
        return false;
    }

    public MetricsElement failure() {
        return new MetricsElement("Failure", this);
    }

    public String getEventName() {
        LinkedList linkedList = new LinkedList();
        MetricsElement metricsElement = this;
        do {
            String str = metricsElement.name;
            if (str != null) {
                linkedList.add(0, str);
            }
            metricsElement = metricsElement.parent;
        } while (metricsElement != null);
        return TextUtils.join(".", linkedList);
    }

    public int hashCode() {
        return Objects.hash(getEventName());
    }

    public MetricsElement result(boolean z) {
        return z ? success() : failure();
    }

    public MetricsElement success() {
        return new MetricsElement("Success", this);
    }

    public String toString() {
        return getEventName();
    }

    public MetricsElement element(int i) {
        return new MetricsElement(Integer.toString(i), this);
    }

    public MetricsElement(String str, MetricsElement metricsElement) {
        this.name = str;
        this.parent = metricsElement;
    }
}
