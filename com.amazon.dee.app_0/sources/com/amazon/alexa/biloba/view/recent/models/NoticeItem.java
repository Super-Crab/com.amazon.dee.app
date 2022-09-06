package com.amazon.alexa.biloba.view.recent.models;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.models.Message;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
/* loaded from: classes6.dex */
public class NoticeItem extends BaseRecyclerItem {
    private static final String TAG = "NoticeItem";

    public NoticeItem(@NonNull Message message) {
        super(message, R.layout.recent_activity_notice, BR.noticeItem);
    }

    private Message getMessage() {
        return (Message) getData();
    }

    public String getIconAltText() {
        return getMessage().getIconAltText();
    }

    public String getIconUrl() {
        return getMessage().getIconUrl();
    }

    public String getLinkText() {
        return getMessage().getLinkUrlText();
    }

    public String getLinkUrl() {
        return getMessage().getLinkUrl();
    }

    public String getPrimaryText() {
        return getMessage().getLocalizedPrimaryMessage();
    }

    public void onClick(View view) {
        LogUtils.i(TAG, "NoticeItem Link Clicked");
    }
}
