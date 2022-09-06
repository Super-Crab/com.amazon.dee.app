package com.amazon.alexa.redesign.view.viewtypes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.viewtypes.CarouselModel;
import com.amazon.alexa.redesign.entity.viewtypes.GridIconTitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleModel;
import com.amazon.alexa.redesign.metrics.ViewRecorderViewApi;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.CarouselTouchHandler;
import com.amazon.alexa.redesign.view.carousel.CarouselAdapter;
import com.amazon.alexa.redesign.view.carousel.CarouselLayoutManager;
import com.amazon.alexa.redesign.view.carousel.CarouselScrollListener;
import com.amazon.alexa.redesign.view.carousel.CarouselViewRecorder;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class CarouselView extends LinearLayout implements ViewType, ViewRecorderViewApi {
    @VisibleForTesting
    CarouselAdapter carouselAdapter;
    CarouselScrollListener carouselScrollListener;
    private CarouselTouchHandler carouselTouchHandler;
    @VisibleForTesting
    Context context;
    private int dividerDrawableId;
    @VisibleForTesting
    LinearLayoutManager layoutManager;
    private HomeMetricsRecorder metricsRecorder;
    @SuppressLint({"ClickableViewAccessibility"})
    @VisibleForTesting
    View.OnTouchListener onTouchListener;
    @VisibleForTesting
    RecyclerView recyclerView;
    @VisibleForTesting
    Map<String, Object> topLevelMetricsObject;

    public CarouselView(Context context) {
        super(context);
        this.onTouchListener = new View.OnTouchListener() { // from class: com.amazon.alexa.redesign.view.viewtypes.-$$Lambda$CarouselView$Uky4NdzcXkPuNitQs7Irf09yULE
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CarouselView.this.lambda$new$0$CarouselView(view, motionEvent);
            }
        };
    }

    @Override // com.amazon.alexa.redesign.view.viewtypes.ViewType
    public void bind(ViewTypeModel viewTypeModel, Map<String, Object> map, String str) {
        if (viewTypeModel instanceof CarouselModel) {
            List<ViewTypeModel> items = ((CarouselModel) viewTypeModel).getItems();
            this.layoutManager = new CarouselLayoutManager(this.context, 0, false, this);
            this.recyclerView.setLayoutManager(this.layoutManager);
            this.carouselAdapter = new CarouselAdapter(items, getContext(), this.metricsRecorder, map);
            this.recyclerView.setAdapter(this.carouselAdapter);
            this.topLevelMetricsObject = map;
            this.carouselScrollListener = new CarouselScrollListener(this.layoutManager, this.carouselAdapter, new CarouselViewRecorder(this, this.metricsRecorder));
            this.recyclerView.addOnScrollListener(this.carouselScrollListener);
            this.recyclerView.setOnTouchListener(this.onTouchListener);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.context, 0);
            Drawable drawable = ContextCompat.getDrawable(this.context, this.dividerDrawableId);
            if (drawable == null) {
                return;
            }
            dividerItemDecoration.setDrawable(drawable);
            this.recyclerView.addItemDecoration(dividerItemDecoration);
        }
    }

    public int getDividerDrawableId() {
        return this.dividerDrawableId;
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public int getFirstVisibleItemPosition() {
        return this.layoutManager.findFirstVisibleItemPosition();
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public int getLastVisibleItemPosition() {
        return this.layoutManager.findLastVisibleItemPosition();
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public double getPercentCardOnScreen(boolean z, int i) {
        double d;
        int width;
        Rect rect = new Rect();
        this.recyclerView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        View findViewByPosition = this.layoutManager.findViewByPosition(i);
        if (findViewByPosition != null) {
            findViewByPosition.getGlobalVisibleRect(rect2);
            if (z) {
                d = rect2.right - rect.left;
                width = findViewByPosition.getWidth();
            } else {
                d = rect.right - rect2.left;
                width = findViewByPosition.getWidth();
            }
            return d / width;
        }
        return -1.0d;
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public Map<String, Object> getTopLevelMetricsObject(Object obj) {
        String str;
        String str2;
        String str3 = "";
        if (obj instanceof ChipIconTitleModel) {
            ChipIconTitleModel chipIconTitleModel = (ChipIconTitleModel) obj;
            str3 = chipIconTitleModel.getCarouselItemId();
            str2 = chipIconTitleModel.getCarouselItemType();
            str = chipIconTitleModel.getCarouselItemProvider();
        } else if (obj instanceof GridIconTitleModel) {
            GridIconTitleModel gridIconTitleModel = (GridIconTitleModel) obj;
            str3 = gridIconTitleModel.getCarouselItemId();
            str2 = gridIconTitleModel.getCarouselItemType();
            str = gridIconTitleModel.getCarouselItemProvider();
        } else {
            str = str3;
            str2 = str;
        }
        this.topLevelMetricsObject.put("metaActionType", str3);
        this.topLevelMetricsObject.put("contentId", str3);
        this.topLevelMetricsObject.put("contentType", str2);
        this.topLevelMetricsObject.put("contentProvider", str);
        return this.topLevelMetricsObject;
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    /* renamed from: getViewItemFromPosition */
    public Object mo2380getViewItemFromPosition(int i) {
        return this.carouselAdapter.getViewTypeModel(i);
    }

    public /* synthetic */ boolean lambda$new$0$CarouselView(View view, MotionEvent motionEvent) {
        return this.carouselTouchHandler.handleTouchEvent(motionEvent, view.getParent());
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public void recordCardsSeenOnColdStart(int i, int i2) {
        this.carouselScrollListener.recordCardsSeenOnColdStart(i, i2);
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public void recordViewsForVisibleCarouselItems() {
        this.carouselScrollListener.updateAndRecordCardsOnScreen();
    }

    @Override // com.amazon.alexa.redesign.metrics.ViewRecorderViewApi
    public boolean shouldRecordViewForCard(double d, int i) {
        return d >= 1.0d;
    }

    public CarouselView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater, int i, HomeMetricsRecorder homeMetricsRecorder) {
        super(context);
        this.onTouchListener = new View.OnTouchListener() { // from class: com.amazon.alexa.redesign.view.viewtypes.-$$Lambda$CarouselView$Uky4NdzcXkPuNitQs7Irf09yULE
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CarouselView.this.lambda$new$0$CarouselView(view, motionEvent);
            }
        };
        this.recyclerView = (RecyclerView) layoutInflater.inflate(R.layout.amahc_viewtype_carousel, viewGroup, true).findViewById(R.id.carousel);
        this.context = context;
        this.dividerDrawableId = i;
        this.metricsRecorder = homeMetricsRecorder;
        this.carouselTouchHandler = new CarouselTouchHandler(context);
    }
}
