package com.amazon.alexa.biloba.view.account.timeZonePicker;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.model.TimeZoneItem;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.common.ListItemClickListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class TimeZonePickerViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "TimeZonePickerViewHolder";
    private final TextView timeZonePrimaryText;
    private final TextView timeZoneSecondaryText;
    private final ImageView timeZoneSelectedIcon;

    public TimeZonePickerViewHolder(@NonNull View view) {
        super(view);
        this.timeZonePrimaryText = (TextView) view.findViewById(R.id.time_zone_name);
        this.timeZoneSecondaryText = (TextView) view.findViewById(R.id.time_zone_city);
        this.timeZoneSelectedIcon = (ImageView) view.findViewById(R.id.timezone_selected_icon);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$bindData$0(TimeZoneItem timeZoneItem, ListItemClickListener listItemClickListener, View view) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Clicked on the timezone selection for timezone ");
        outline107.append(timeZoneItem.getValue());
        LogUtils.i(str, outline107.toString());
        listItemClickListener.onListItemClicked(timeZoneItem);
    }

    public void bindData(final TimeZoneItem timeZoneItem, String str, final ListItemClickListener listItemClickListener) {
        this.timeZonePrimaryText.setText(timeZoneItem.getPrimaryText());
        this.timeZoneSecondaryText.setText(timeZoneItem.getSecondaryText());
        this.timeZoneSelectedIcon.setVisibility(8);
        if (timeZoneItem.getValue().equals(str)) {
            this.timeZoneSelectedIcon.setVisibility(0);
        }
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.account.timeZonePicker.-$$Lambda$TimeZonePickerViewHolder$TBBb0OYZL0OdC6j7wvkLJH0EWqg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TimeZonePickerViewHolder.lambda$bindData$0(TimeZoneItem.this, listItemClickListener, view);
            }
        });
    }
}
