package com.amazon.alexa.redesign.view.carousel;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.redesign.metrics.ViewRecorderViewApi;
/* loaded from: classes10.dex */
public class CarouselLayoutManager extends LinearLayoutManager {
    public static boolean homecardsShownOnce = false;
    private ViewRecorderViewApi viewRecorderViewApi;

    public CarouselLayoutManager(Context context, int i, boolean z, ViewRecorderViewApi viewRecorderViewApi) {
        super(context, i, z);
        this.viewRecorderViewApi = viewRecorderViewApi;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = findLastVisibleItemPosition();
        if (!homecardsShownOnce) {
            homecardsShownOnce = true;
            this.viewRecorderViewApi.recordCardsSeenOnColdStart(findFirstVisibleItemPosition, findLastVisibleItemPosition);
        }
    }
}
