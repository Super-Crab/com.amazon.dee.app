package com.amazon.alexa.redesign.view.carousel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.entity.viewtypes.GridIconTitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.repository.LocaleRepository;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.viewtypes.ViewType;
import com.bumptech.glide.Glide;
import java.util.Map;
/* loaded from: classes10.dex */
public class GridIconTitleViewHolder extends CarouselViewHolder implements ViewType {
    private final Context context;
    public final ImageView icon;
    private final HomeMetricsRecorder metricsRecorder;
    public final TextView title;

    public GridIconTitleViewHolder(ViewGroup viewGroup, HomeMetricsRecorder homeMetricsRecorder) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.amahc_viewtype_grid_icon_title, viewGroup, false));
        this.icon = (ImageView) this.itemView.findViewById(R.id.icon);
        this.title = (TextView) this.itemView.findViewById(R.id.title);
        this.context = viewGroup.getContext();
        this.metricsRecorder = homeMetricsRecorder;
        setMaxLineLimit();
    }

    private void setMaxLineLimit() {
        this.title.setMaxLines(this.context.getResources().getInteger(LocaleRepository.isEnglish() ? R.integer.amahc_caption_line_limit : R.integer.amahc_caption_line_limit_non_english));
    }

    @Override // com.amazon.alexa.redesign.view.carousel.CarouselViewHolder, com.amazon.alexa.redesign.view.viewtypes.ViewType
    public void bind(ViewTypeModel viewTypeModel, final Map<String, Object> map, String str) {
        super.bind(viewTypeModel, map, str);
        if (viewTypeModel instanceof GridIconTitleModel) {
            GridIconTitleModel gridIconTitleModel = (GridIconTitleModel) viewTypeModel;
            this.title.setText(gridIconTitleModel.getTitle());
            setTitleTypeface();
            final Action action = gridIconTitleModel.getAction();
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.carousel.-$$Lambda$GridIconTitleViewHolder$mSYcKCbdKuOcn-XSpxgVkGACwBM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GridIconTitleViewHolder.this.lambda$bind$0$GridIconTitleViewHolder(action, map, view);
                }
            });
            this.itemView.setOnTouchListener(this.onTouchListener);
            bindIcon(gridIconTitleModel);
        }
    }

    public void bindIcon(GridIconTitleModel gridIconTitleModel) {
        if (gridIconTitleModel.getImageType().equals(Constants.IMAGE_TYPE_ICON)) {
            this.icon.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicIcon100));
        }
        Glide.with(this.context.getApplicationContext()).mo6762load(gridIconTitleModel.getImageUrl()).into(this.icon);
    }

    public /* synthetic */ void lambda$bind$0$GridIconTitleViewHolder(Action action, Map map, View view) {
        if (action != null) {
            action.execute();
            emitCarouselItemClickMetrics(map, action.getMetricData(), this.metricsRecorder);
        }
    }

    @VisibleForTesting
    void setTitleTypeface() {
        this.title.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_REGULAR));
    }
}
