package com.amazon.alexa.voice.ui.onedesign.traffic;

import androidx.annotation.DrawableRes;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class TrafficRoute implements TrafficRouteModel {
    private final int indicator;
    private final boolean primary;
    private final CharSequence subTitle;
    private final CharSequence title;

    public TrafficRoute(CharSequence charSequence, CharSequence charSequence2, boolean z, int i) {
        this.title = charSequence;
        this.subTitle = charSequence2;
        this.primary = z;
        this.indicator = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrafficRoute.class != obj.getClass()) {
            return false;
        }
        TrafficRoute trafficRoute = (TrafficRoute) obj;
        CharSequence charSequence = this.title;
        if (charSequence == null ? trafficRoute.title != null : !charSequence.equals(trafficRoute.title)) {
            return false;
        }
        CharSequence charSequence2 = this.subTitle;
        if (charSequence2 == null ? trafficRoute.subTitle != null : !charSequence2.equals(trafficRoute.subTitle)) {
            return false;
        }
        return this.primary == trafficRoute.primary && this.indicator == trafficRoute.indicator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficRouteModel
    @DrawableRes
    public int getIndicator() {
        return this.indicator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficRouteModel
    public CharSequence getSubTitle() {
        return this.subTitle;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficRouteModel
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        CharSequence charSequence = this.title;
        int i = 0;
        int hashCode = (JfifUtil.MARKER_EOI + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.subTitle;
        if (charSequence2 != null) {
            i = charSequence2.hashCode();
        }
        return ((((hashCode + i) * 31) + (this.primary ? 1 : 0)) * 31) + this.indicator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficRouteModel
    public boolean isPrimary() {
        return this.primary;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TrafficRoute{title=");
        outline107.append((Object) this.title);
        outline107.append(", subTitle=");
        outline107.append((Object) this.subTitle);
        outline107.append(", primary=");
        outline107.append(this.primary);
        outline107.append(", indicator=");
        return GeneratedOutlineSupport1.outline85(outline107, this.indicator, JsonReaderKt.END_OBJ);
    }
}
