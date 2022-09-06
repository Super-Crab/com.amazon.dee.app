package com.amazon.alexa.redesign.view.homeFeed;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.templates.CarouselChipTemplateModel;
import com.amazon.alexa.redesign.entity.templates.CarouselGridTemplateModel;
import com.amazon.alexa.redesign.entity.viewtypes.EmptyModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.utils.TestIdUtil;
import com.amazon.alexa.redesign.view.ViewTypeFactory;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.amazon.alexa.redesign.view.templates.carouselChipTemplate.CarouselChipTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.carouselGridTemplate.CarouselGridTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.domainCardTemplate.DomainCardTemplateViewItem;
import com.amazon.alexa.redesign.view.templates.miniTemplate.MiniTemplateViewItem;
import com.amazon.alexa.redesign.view.viewtypes.ViewType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public abstract class BaseCardViewHolder<T extends RecyclerViewItem> extends RecyclerView.ViewHolder {
    private final View cardBackgroundView;
    private final FrameLayout cardForegroundView;
    protected final View cardView;
    private Context context;
    protected ImageView dots;
    protected ViewTypeFactory factory;
    protected HomeContract.Presenter homePresenter;
    protected HomeMetricsRecorder metricsRecorder;
    private final View removeCardButton;
    protected List<LinearLayout> slots;
    private TemplateHelperUtil templateHelperUtil;

    public BaseCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, View view, HomeContract.Presenter presenter, Context context) {
        super(layoutInflater.inflate(R.layout.amahc_base_card, viewGroup, false));
        this.cardBackgroundView = this.itemView.findViewById(R.id.card_background_base);
        this.cardForegroundView = (FrameLayout) this.itemView.findViewById(R.id.card_foreground_base);
        this.cardForegroundView.addView(view);
        this.removeCardButton = this.cardBackgroundView.findViewById(R.id.remove_card_button);
        this.homePresenter = presenter;
        this.cardView = view;
        this.context = context;
    }

    @VisibleForTesting
    static boolean modelHasSwipeToDismiss(RecyclerViewItem recyclerViewItem) {
        return (recyclerViewItem instanceof MiniTemplateViewItem) || (recyclerViewItem instanceof DomainCardTemplateViewItem) || (recyclerViewItem instanceof CarouselChipTemplateViewItem) || (recyclerViewItem instanceof CarouselGridTemplateViewItem);
    }

    public static boolean shouldAnimate(CardModel cardModel) {
        return !Constants.VIEW_UPDATE_TYPE_CACHE.equalsIgnoreCase(cardModel.getViewUpdateType()) || !"universal-device-picker".equalsIgnoreCase(cardModel.getLastRouteName()) || CardScrollListener.isScrolling;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applyBackgroundColorToView(View view, int i) {
        view.setBackgroundColor(ThemeUtil.getColorFromAttribute(this.context, i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void bind(T t, int i, boolean z) {
        List<LinearLayout> list;
        CardModel mo2390getModel = t.mo2390getModel();
        if (mo2390getModel != null) {
            final String dismissCardId = mo2390getModel.getDismissCardId();
            final Map<String, Object> topLevelMetricsObject = mo2390getModel.getTopLevelMetricsObject();
            if (mo2390getModel.isDismissible() && modelHasSwipeToDismiss(t)) {
                ((TextView) this.removeCardButton).setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
                this.cardBackgroundView.setBackgroundColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicStatus20));
                this.removeCardButton.setVisibility(0);
                TemplateHelperUtil.scaleTextViewWithFontFireOS((TextView) this.removeCardButton, this.context, R.integer.amahc_fireos_font_scaling_extra_small_text);
                this.removeCardButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.homeFeed.-$$Lambda$BaseCardViewHolder$OuHzOFQfYNgKZ2fp7SwjLAkuQtM
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseCardViewHolder.this.lambda$bind$0$BaseCardViewHolder(topLevelMetricsObject, dismissCardId, view);
                    }
                });
            }
            if (mo2390getModel.getChildViewModels() == null || (list = this.slots) == null) {
                return;
            }
            for (LinearLayout linearLayout : list) {
                linearLayout.removeAllViews();
            }
            List<ViewTypeModel> childViewModels = mo2390getModel.getChildViewModels();
            for (int i2 = 0; i2 < childViewModels.size(); i2++) {
                bindSlot(childViewModels.get(i2), topLevelMetricsObject, mo2390getModel);
            }
            if (!Constants.AutomationConstants.isQABuild) {
                return;
            }
            TestIdUtil.setTestId(this.itemView, (String) topLevelMetricsObject.get("contentProvider"), (String) topLevelMetricsObject.get("contentType"), "_card");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void bindDots(final CardModel cardModel) {
        Resources resources = this.context.getResources();
        this.dots = new ImageView(this.context);
        View findViewById = this.itemView.findViewById(R.id.slot0);
        RelativeLayout relativeLayout = (RelativeLayout) this.itemView.findViewById(R.id.slot0wrapper);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        relativeLayout.removeViews(1, relativeLayout.getChildCount() - 1);
        this.dots.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicIcon90));
        int dimension = (int) resources.getDimension(R.dimen.amahc_margin_large);
        int dimension2 = (int) resources.getDimension(R.dimen.amahc_margin_medium);
        int dimension3 = dimension + dimension2 + ((int) resources.getDimension(R.dimen.amahc_width_medium));
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).setMarginEnd(dimension3);
        }
        this.dots.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.homeFeed.-$$Lambda$BaseCardViewHolder$WTmxxrCPlMNrCQtUN2AT5DeU8Es
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseCardViewHolder.this.lambda$bindDots$2$BaseCardViewHolder(cardModel, view);
            }
        });
        this.dots.setContentDescription(this.context.getResources().getString(R.string.amahc_more_options));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(resources.getDimensionPixelSize(R.dimen.amahc_width_medium), resources.getDimensionPixelSize(R.dimen.amahc_height_medium));
        layoutParams2.addRule(21);
        layoutParams2.setMarginStart(dimension);
        layoutParams2.setMarginEnd(dimension2);
        if (!(cardModel instanceof CarouselChipTemplateModel) && !(cardModel instanceof CarouselGridTemplateModel)) {
            layoutParams2.topMargin = dimension2;
        } else {
            layoutParams2.topMargin = 0;
        }
        this.dots.setLayoutParams(layoutParams2);
        this.dots.setImageResource(R.drawable.amahc_ic_overflow);
        if (relativeLayout.getChildCount() < 2) {
            relativeLayout.addView(this.dots);
            relativeLayout.requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewType bindSlot(ViewTypeModel viewTypeModel, Map<String, Object> map, final CardModel cardModel) {
        int modelPosition = viewTypeModel.getModelPosition();
        LinearLayout linearLayout = this.slots.get(modelPosition);
        linearLayout.removeAllViews();
        if (viewTypeModel instanceof EmptyModel) {
            this.slots.get(modelPosition).setVisibility(8);
            return null;
        }
        this.slots.get(modelPosition).setVisibility(0);
        ViewType viewType = this.factory.getViewType(viewTypeModel.getModelType(), linearLayout);
        final Action action = viewTypeModel.getAction();
        if (action != null) {
            viewType.bind(viewTypeModel, map, action.getAccessibilityLabel());
            action.bind(this.homePresenter, viewType, getAdapterPosition());
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.homeFeed.-$$Lambda$BaseCardViewHolder$fy8HfVEnhdkNd8dfORJQrKs9_9o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseCardViewHolder.this.lambda$bindSlot$1$BaseCardViewHolder(action, cardModel, view);
                }
            });
        } else {
            viewType.bind(viewTypeModel, map, "");
        }
        return viewType;
    }

    public abstract void detach();

    public View getCardBackgroundView() {
        return this.cardBackgroundView;
    }

    public View getCardForegroundView() {
        return this.cardForegroundView;
    }

    public /* synthetic */ void lambda$bind$0$BaseCardViewHolder(Map map, String str, View view) {
        this.homePresenter.onRemoveCardClicked(getAdapterPosition(), map, str);
        this.cardForegroundView.setX(0.0f);
    }

    public /* synthetic */ void lambda$bindDots$2$BaseCardViewHolder(CardModel cardModel, View view) {
        if (cardModel != null) {
            this.homePresenter.onThreeDotsClicked(cardModel.getDismissCardId());
        }
    }

    public /* synthetic */ void lambda$bindSlot$1$BaseCardViewHolder(Action action, CardModel cardModel, View view) {
        if (this.metricsRecorder != null) {
            action.execute();
            this.templateHelperUtil.emitClickMetrics(this.metricsRecorder, cardModel, action);
            this.homePresenter.handleClickToDismiss(getAdapterPosition(), cardModel);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void scaleDomainCardMinHeight(View view) {
        Resources resources = this.context.getResources();
        view.setMinimumHeight(Math.round(resources.getDimension(R.dimen.amahc_height_large_80) * Settings.System.getFloat(this.context.getContentResolver(), "font_scale", 1.0f)));
    }

    protected void setSpaceHolderHeight(View view, int i) {
        ((LinearLayout.LayoutParams) view.getLayoutParams()).height = (int) (i * this.context.getResources().getDisplayMetrics().density);
    }

    protected void setViewBottomMargin(View view, int i) {
        ((LinearLayout.LayoutParams) view.getLayoutParams()).bottomMargin = (int) (i * this.context.getResources().getDisplayMetrics().density);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BaseCardViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, View view, ViewTypeFactory viewTypeFactory, HomeMetricsRecorder homeMetricsRecorder, HomeContract.Presenter presenter, int[] iArr, Context context) {
        this(layoutInflater, viewGroup, view, presenter, context);
        this.factory = viewTypeFactory;
        this.metricsRecorder = homeMetricsRecorder;
        this.slots = new ArrayList();
        for (int i : iArr) {
            this.slots.add(this.itemView.findViewById(i));
        }
        this.templateHelperUtil = new TemplateHelperUtil();
    }
}
