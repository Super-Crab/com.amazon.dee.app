package com.amazon.alexa.redesign.view.carousel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.redesign.utils.ViewRecorder;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class CarouselScrollListener extends RecyclerView.OnScrollListener {
    private Map<Object, Long> cardsSeenBeforeScroll = new HashMap();
    CarouselAdapter carouselAdapter;
    LinearLayoutManager layoutManager;
    ViewRecorder viewRecorder;

    public CarouselScrollListener(LinearLayoutManager linearLayoutManager, CarouselAdapter carouselAdapter, ViewRecorder viewRecorder) {
        this.layoutManager = linearLayoutManager;
        this.carouselAdapter = carouselAdapter;
        this.viewRecorder = viewRecorder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0) {
            onScrolledToCarouselItem(this.layoutManager.findFirstVisibleItemPosition(), this.layoutManager.findLastVisibleItemPosition());
        }
    }

    public void onScrolledToCarouselItem(int i, int i2) {
        Map<Object, Long> recordCardsInRange = this.viewRecorder.recordCardsInRange(i, i2);
        this.viewRecorder.recordViewEventForCardsOutOfView(this.viewRecorder.getCardsOutOfView(this.cardsSeenBeforeScroll, recordCardsInRange), false);
        this.viewRecorder.updateCardsSeenBeforeNextScroll(this.cardsSeenBeforeScroll, recordCardsInRange);
    }

    public void recordCardsSeenOnColdStart(int i, int i2) {
        this.cardsSeenBeforeScroll = this.viewRecorder.recordCardsInRange(i, i2);
    }

    public void updateAndRecordCardsOnScreen() {
        this.viewRecorder.updateAndRecordCardsOnScreen(this.cardsSeenBeforeScroll, true);
    }
}
