package com.amazon.alexa.redesign.view.carousel;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.core.graphics.ColorUtils;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleState;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.viewtypes.ViewType;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class ChipIconTitleViewHolder extends CarouselViewHolder implements ViewType {
    public static final String STATE_DEFAULT = "DEFAULT";
    public static final String STATE_DISABLED = "DISABLED";
    public static final String STATE_PRESSED = "PRESSED";
    public static final String STATE_SELECTED = "SELECTED";
    private final Context context;
    public final ImageView icon;
    private final HomeMetricsRecorder metricsRecorder;
    public final Space space;
    public final TextView title;

    public ChipIconTitleViewHolder(ViewGroup viewGroup, HomeMetricsRecorder homeMetricsRecorder) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.amahc_viewtype_chip_icon_title, viewGroup, false));
        this.icon = (ImageView) this.itemView.findViewById(R.id.icon);
        this.title = (TextView) this.itemView.findViewById(R.id.title);
        this.space = (Space) this.itemView.findViewById(R.id.space);
        this.context = viewGroup.getContext();
        this.metricsRecorder = homeMetricsRecorder;
    }

    @Override // com.amazon.alexa.redesign.view.carousel.CarouselViewHolder, com.amazon.alexa.redesign.view.viewtypes.ViewType
    public void bind(ViewTypeModel viewTypeModel, Map<String, Object> map, String str) {
        super.bind(viewTypeModel, map, str);
        if (viewTypeModel instanceof ChipIconTitleModel) {
            ChipIconTitleModel chipIconTitleModel = (ChipIconTitleModel) viewTypeModel;
            bindState(chipIconTitleModel.getStates(), 0, chipIconTitleModel.isCircular(), map);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @VisibleForTesting
    void bindBackground(ChipIconTitleState chipIconTitleState) {
        char c;
        int dimension = (int) this.context.getResources().getDimension(R.dimen.amahc_stroke_width_small);
        String state = chipIconTitleState.getState();
        switch (state.hashCode()) {
            case -2032180703:
                if (state.equals(STATE_DEFAULT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 399718402:
                if (state.equals(STATE_PRESSED)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1053567612:
                if (state.equals("DISABLED")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1974198939:
                if (state.equals("SELECTED")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.itemView.setBackgroundResource(R.drawable.amahc_chip_background_default);
        } else if (c == 1) {
            this.itemView.setBackgroundResource(R.drawable.amahc_chip_background_pressed);
            GradientDrawable gradientDrawable = (GradientDrawable) this.itemView.getBackground();
            gradientDrawable.setAlpha(153);
            gradientDrawable.setStroke(dimension, ColorUtils.setAlphaComponent(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction50), 153));
        } else if (c != 2) {
            if (c != 3) {
                return;
            }
            this.itemView.setBackgroundResource(R.drawable.amahc_chip_background_selected);
            ((GradientDrawable) this.itemView.getBackground()).setStroke(dimension, ColorUtils.setAlphaComponent(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral40), 153));
        } else {
            this.itemView.setBackgroundResource(R.drawable.amahc_chip_background_disabled);
            GradientDrawable gradientDrawable2 = (GradientDrawable) this.itemView.getBackground();
            gradientDrawable2.setAlpha(77);
            gradientDrawable2.setStroke(dimension, ColorUtils.setAlphaComponent(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction50), 77));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0079, code lost:
        if (r0.equals(com.amazon.alexa.redesign.view.carousel.ChipIconTitleViewHolder.STATE_DEFAULT) != false) goto L13;
     */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void bindIcon(com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleState r8) {
        /*
            r7 = this;
            java.lang.String r0 = r8.getImageUrl()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L29
            android.widget.ImageView r8 = r7.icon
            r0 = 8
            r8.setVisibility(r0)
            android.widget.Space r8 = r7.space
            r8.setVisibility(r0)
            android.widget.TextView r8 = r7.title
            android.content.Context r0 = r7.context
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.amazon.alexa.redesign.R.dimen.amahc_margin_large
            float r0 = r0.getDimension(r1)
            int r0 = (int) r0
            r7.setMarginStart(r8, r0)
            return
        L29:
            android.widget.ImageView r0 = r7.icon
            r1 = 0
            r0.setVisibility(r1)
            android.widget.Space r0 = r7.space
            r0.setVisibility(r1)
            android.widget.TextView r0 = r7.title
            r7.setMarginStart(r0, r1)
            java.lang.String r0 = r8.getImageType()
            java.lang.String r2 = "Icon"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto Lc3
            java.lang.String r0 = r8.getState()
            r2 = -1
            int r3 = r0.hashCode()
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r3) {
                case -2032180703: goto L73;
                case 399718402: goto L69;
                case 1053567612: goto L5f;
                case 1974198939: goto L55;
                default: goto L54;
            }
        L54:
            goto L7c
        L55:
            java.lang.String r1 = "SELECTED"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L7c
            r1 = r4
            goto L7d
        L5f:
            java.lang.String r1 = "DISABLED"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L7c
            r1 = r5
            goto L7d
        L69:
            java.lang.String r1 = "PRESSED"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L7c
            r1 = r6
            goto L7d
        L73:
            java.lang.String r3 = "DEFAULT"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L7c
            goto L7d
        L7c:
            r1 = r2
        L7d:
            if (r1 == 0) goto Lb6
            if (r1 == r6) goto La8
            if (r1 == r5) goto L94
            if (r1 == r4) goto L86
            goto Lc3
        L86:
            android.widget.ImageView r0 = r7.icon
            android.content.Context r1 = r7.context
            int r2 = com.amazon.alexa.redesign.R.attr.mosaicIcon110
            int r1 = com.amazon.alexa.mosaic.components.ThemeUtil.getColorFromAttribute(r1, r2)
            r0.setColorFilter(r1)
            goto Lc3
        L94:
            android.content.Context r0 = r7.context
            int r1 = com.amazon.alexa.redesign.R.attr.mosaicIcon80
            int r0 = com.amazon.alexa.mosaic.components.ThemeUtil.getColorFromAttribute(r0, r1)
            r1 = 77
            int r0 = androidx.core.graphics.ColorUtils.setAlphaComponent(r0, r1)
            android.widget.ImageView r1 = r7.icon
            r1.setColorFilter(r0)
            goto Lc3
        La8:
            android.widget.ImageView r0 = r7.icon
            android.content.Context r1 = r7.context
            int r2 = com.amazon.alexa.redesign.R.attr.mosaicIcon80
            int r1 = com.amazon.alexa.mosaic.components.ThemeUtil.getColorFromAttribute(r1, r2)
            r0.setColorFilter(r1)
            goto Lc3
        Lb6:
            android.widget.ImageView r0 = r7.icon
            android.content.Context r1 = r7.context
            int r2 = com.amazon.alexa.redesign.R.attr.mosaicIcon80
            int r1 = com.amazon.alexa.mosaic.components.ThemeUtil.getColorFromAttribute(r1, r2)
            r0.setColorFilter(r1)
        Lc3:
            android.content.Context r0 = r7.context
            android.content.Context r0 = r0.getApplicationContext()
            com.bumptech.glide.RequestManager r0 = com.bumptech.glide.Glide.with(r0)
            java.lang.String r8 = r8.getImageUrl()
            com.bumptech.glide.RequestBuilder r8 = r0.mo6762load(r8)
            android.widget.ImageView r0 = r7.icon
            r8.into(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.redesign.view.carousel.ChipIconTitleViewHolder.bindIcon(com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleState):void");
    }

    @VisibleForTesting
    void bindState(final List<ChipIconTitleState> list, final int i, final boolean z, final Map<String, Object> map) {
        ChipIconTitleState chipIconTitleState = list.get(i);
        bindTitle(chipIconTitleState);
        bindBackground(chipIconTitleState);
        final Action action = chipIconTitleState.getAction();
        if (action == null && i == list.size() - 1 && !z) {
            this.itemView.setEnabled(false);
            this.itemView.setOnClickListener(null);
        } else {
            this.itemView.setEnabled(true);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.carousel.-$$Lambda$ChipIconTitleViewHolder$NNVam0oq6R9l6GylceuMDPUWRpM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ChipIconTitleViewHolder.this.lambda$bindState$0$ChipIconTitleViewHolder(action, list, i, z, map, view);
                }
            });
        }
        bindIcon(chipIconTitleState);
        this.itemView.setOnTouchListener(this.onTouchListener);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @VisibleForTesting
    void bindTitle(ChipIconTitleState chipIconTitleState) {
        char c;
        String state = chipIconTitleState.getState();
        switch (state.hashCode()) {
            case -2032180703:
                if (state.equals(STATE_DEFAULT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 399718402:
                if (state.equals(STATE_PRESSED)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1053567612:
                if (state.equals("DISABLED")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1974198939:
                if (state.equals("SELECTED")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.title.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction10));
        } else if (c == 1) {
            this.title.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction10));
        } else if (c == 2) {
            this.title.setTextColor(ColorUtils.setAlphaComponent(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction10), 77));
        } else if (c == 3) {
            this.title.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        }
        this.title.setText(chipIconTitleState.getTitle());
        setTitleTypeface();
    }

    public /* synthetic */ void lambda$bindState$0$ChipIconTitleViewHolder(Action action, List list, int i, boolean z, Map map, View view) {
        onItemClicked(action, list, i, z, map);
    }

    @VisibleForTesting
    void onItemClicked(Action action, List<ChipIconTitleState> list, int i, boolean z, Map<String, Object> map) {
        if (action != null) {
            action.execute();
            emitCarouselItemClickMetrics(map, action.getMetricData(), this.metricsRecorder);
        }
        transitionToNextState(list, i, z, map);
    }

    @VisibleForTesting
    void setTitleTypeface() {
        this.title.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_REGULAR));
    }

    @VisibleForTesting
    void transitionToNextState(List<ChipIconTitleState> list, int i, boolean z, Map<String, Object> map) {
        int size = list.size() - 1;
        if (i >= 0 && i < size) {
            bindState(list, i + 1, z, map);
        } else if (!z || i != size) {
        } else {
            bindState(list, 0, z, map);
        }
    }
}
