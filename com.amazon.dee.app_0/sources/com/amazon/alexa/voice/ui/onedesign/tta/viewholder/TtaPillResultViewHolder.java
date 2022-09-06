package com.amazon.alexa.voice.ui.onedesign.tta.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.rv.RvDataProvider;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class TtaPillResultViewHolder extends RvViewHolder {
    private static final String TAG = "TtaPillResultViewHolder";
    private final TextView pillResultView;

    public TtaPillResultViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, RvActionHandler rvActionHandler, RvDataProvider rvDataProvider) {
        super(layoutInflater.inflate(R.layout.text_ui_od_tta_pill_result_view_item, viewGroup, false), rvActionHandler, rvDataProvider);
        this.pillResultView = (TextView) this.itemView.findViewById(R.id.pill_result);
        this.pillResultView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.viewholder.-$$Lambda$TtaPillResultViewHolder$STa8ZR_92gbY8WssxjPYTbD8CnQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TtaPillResultViewHolder.this.lambda$new$0$TtaPillResultViewHolder(view);
            }
        });
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder
    public void bind(Object obj) {
        super.bind(obj);
        String charSequence = ((PillResultTtaItem) obj).getItemText().toString();
        GeneratedOutlineSupport1.outline158("Setting text to : ", charSequence);
        this.pillResultView.setText(charSequence);
    }

    public /* synthetic */ void lambda$new$0$TtaPillResultViewHolder(View view) {
        handleAction(1, this.pillResultView);
    }
}
