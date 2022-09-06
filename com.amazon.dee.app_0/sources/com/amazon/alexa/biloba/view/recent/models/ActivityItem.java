package com.amazon.alexa.biloba.view.recent.models;

import android.text.TextUtils;
import androidx.databinding.library.baseAdapters.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.models.Activity;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
/* loaded from: classes6.dex */
public class ActivityItem extends BaseRecyclerItem<Activity> {
    public ActivityItem(Activity activity) {
        super(activity, R.layout.recent_activity_item, BR.activityItem);
    }

    public String getIconAltText() {
        return getData().getIconAltText();
    }

    public String getIconUrl() {
        return getData().getIconUrl();
    }

    public String getPrimaryText() {
        return getData().getTitle();
    }

    public String getSecondaryText() {
        if (TextUtils.isEmpty(getData().getDeviceName())) {
            return getData().getLocalizedDateTime().getTime();
        }
        return String.format("%1$s - %2$s", getData().getLocalizedDateTime().getTime(), getData().getDeviceName());
    }
}
