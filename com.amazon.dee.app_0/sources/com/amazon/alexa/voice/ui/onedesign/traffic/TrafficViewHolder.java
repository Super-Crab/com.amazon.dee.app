package com.amazon.alexa.voice.ui.onedesign.traffic;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
final class TrafficViewHolder extends BindableViewHolder<TrafficRouteModel> {
    private final ImageView indicatorView;
    private final TextView subTitleView;
    private final TextView titleView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrafficViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_traffic_route, viewGroup, false));
        this.titleView = (TextView) this.itemView.findViewById(R.id.title);
        this.subTitleView = (TextView) this.itemView.findViewById(R.id.subTitle);
        this.indicatorView = (ImageView) this.itemView.findViewById(R.id.indicator);
        if (z) {
            FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.titleView);
            TextView textView = this.titleView;
            textView.setTextSize(0, textView.getContext().getResources().getDimension(R.dimen.voice_ui_od_traffic_primary_route_text_size));
        } else {
            FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.titleView);
            TextView textView2 = this.titleView;
            textView2.setTextSize(0, textView2.getContext().getResources().getDimension(R.dimen.voice_ui_od_traffic_secondary_route_text_size));
        }
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.subTitleView);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder
    public void bind(TrafficRouteModel trafficRouteModel) {
        this.titleView.setText(trafficRouteModel.getTitle());
        this.subTitleView.setText(trafficRouteModel.getSubTitle());
        this.indicatorView.setImageResource(trafficRouteModel.getIndicator());
    }
}
