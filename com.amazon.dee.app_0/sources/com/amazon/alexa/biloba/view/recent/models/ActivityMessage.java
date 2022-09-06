package com.amazon.alexa.biloba.view.recent.models;

import androidx.databinding.library.baseAdapters.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
/* loaded from: classes6.dex */
public class ActivityMessage extends BaseRecyclerItem {
    public ActivityMessage(String str) {
        super(str, R.layout.recent_activity_message, BR.activityMessage);
    }

    public String getText() {
        return (String) getData();
    }
}
