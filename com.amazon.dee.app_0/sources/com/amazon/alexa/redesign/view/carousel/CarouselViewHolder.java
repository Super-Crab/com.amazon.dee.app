package com.amazon.alexa.redesign.view.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.viewtypes.GridIconTitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.CarouselTouchHandler;
import com.amazon.alexa.redesign.view.viewtypes.ViewType;
import java.util.Map;
/* loaded from: classes10.dex */
public class CarouselViewHolder extends RecyclerView.ViewHolder implements ViewType {
    protected String carouselItemId;
    protected String carouselItemProvider;
    protected String carouselItemType;
    private CarouselTouchHandler carouselTouchHandler;
    private final Context context;
    @SuppressLint({"ClickableViewAccessibility"})
    protected View.OnTouchListener onTouchListener;

    public CarouselViewHolder(@NonNull View view) {
        super(view);
        this.onTouchListener = new View.OnTouchListener() { // from class: com.amazon.alexa.redesign.view.carousel.-$$Lambda$CarouselViewHolder$-4Jw9EI9S11-djWDHg5PeKZYKKQ
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return CarouselViewHolder.this.lambda$new$0$CarouselViewHolder(view2, motionEvent);
            }
        };
        this.context = view.getContext();
        this.carouselTouchHandler = new CarouselTouchHandler(this.context);
    }

    private void bindCarouselItemIdentifiers(ViewTypeModel viewTypeModel) {
        if (viewTypeModel instanceof ChipIconTitleModel) {
            ChipIconTitleModel chipIconTitleModel = (ChipIconTitleModel) viewTypeModel;
            this.carouselItemId = chipIconTitleModel.getCarouselItemId();
            this.carouselItemType = chipIconTitleModel.getCarouselItemType();
            this.carouselItemProvider = chipIconTitleModel.getCarouselItemProvider();
        } else if (!(viewTypeModel instanceof GridIconTitleModel)) {
        } else {
            GridIconTitleModel gridIconTitleModel = (GridIconTitleModel) viewTypeModel;
            this.carouselItemId = gridIconTitleModel.getCarouselItemId();
            this.carouselItemType = gridIconTitleModel.getCarouselItemType();
            this.carouselItemProvider = gridIconTitleModel.getCarouselItemProvider();
        }
    }

    @Override // com.amazon.alexa.redesign.view.viewtypes.ViewType
    public void bind(ViewTypeModel viewTypeModel, Map<String, Object> map, String str) {
        boolean z = false;
        if (viewTypeModel.getModelPosition() == 0) {
            if (this.context.getResources().getConfiguration().getLayoutDirection() == 1) {
                z = true;
            }
            int dimension = (int) this.context.getResources().getDimension(R.dimen.amahc_margin_extra_large_68);
            if (z) {
                dimension -= (int) this.context.getResources().getDimension(R.dimen.amahc_margin_medium);
            }
            setMarginStart(this.itemView, dimension);
        } else {
            setMarginStart(this.itemView, 0);
        }
        bindCarouselItemIdentifiers(viewTypeModel);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void emitCarouselItemClickMetrics(Map<String, Object> map, Map<String, Object> map2, HomeMetricsRecorder homeMetricsRecorder) {
        if (this.carouselItemId == null || this.carouselItemProvider == null || this.carouselItemType == null) {
            return;
        }
        map.putAll(map2);
        map.put("metaActionType", this.carouselItemId);
        map.put("contentId", this.carouselItemId);
        map.put("contentType", this.carouselItemType);
        map.put("contentProvider", this.carouselItemProvider);
        homeMetricsRecorder.recordClickEvent(map);
    }

    public /* synthetic */ boolean lambda$new$0$CarouselViewHolder(View view, MotionEvent motionEvent) {
        return this.carouselTouchHandler.handleTouchEvent(motionEvent, view.getParent().getParent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setMarginStart(View view, int i) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMarginStart(i);
        }
    }
}
