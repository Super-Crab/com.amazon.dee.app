package com.amazon.alexa.voice.ui.onedesign.tta.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.rv.RvDataProvider;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.items.UserInputTtaItem;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class TtaUserRequestViewHolder extends RvViewHolder {
    private static final String TAG = "TtaRequestViewHolder";
    private final TextView userRequestTextView;

    public TtaUserRequestViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, RvActionHandler rvActionHandler, RvDataProvider rvDataProvider) {
        super(layoutInflater.inflate(R.layout.text_ui_od_tta_user_request_item, viewGroup, false), rvActionHandler, rvDataProvider);
        this.userRequestTextView = (TextView) this.itemView.findViewById(R.id.user_request);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder
    public void bind(Object obj) {
        super.bind(obj);
        UserInputTtaItem userInputTtaItem = (UserInputTtaItem) obj;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Setting text to : ");
        outline107.append((Object) userInputTtaItem.getItemText());
        outline107.toString();
        this.userRequestTextView.setText(userInputTtaItem.getItemText());
    }
}
