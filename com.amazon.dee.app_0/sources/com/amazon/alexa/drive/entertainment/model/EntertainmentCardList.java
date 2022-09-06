package com.amazon.alexa.drive.entertainment.model;

import android.util.Log;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public class EntertainmentCardList {
    private static final String TAG = "EntertainmentCardList";
    private List<EntertainmentCardItem> mEntertainmentCardItemArrayList;
    private List<EntertainmentLandingPageContract.EntertainmentCardListChangeListener> mEntertainmentCardListChangeListenerList;

    public EntertainmentCardList() {
        Log.i(TAG, "EntertainmentCardList Constructor");
        this.mEntertainmentCardItemArrayList = new ArrayList();
        this.mEntertainmentCardListChangeListenerList = new ArrayList();
    }

    public void addEntertainmentCardItem(EntertainmentCardItem entertainmentCardItem) {
        Log.i(TAG, "addEntertainmentCardItem");
        this.mEntertainmentCardItemArrayList.add(entertainmentCardItem);
        for (EntertainmentLandingPageContract.EntertainmentCardListChangeListener entertainmentCardListChangeListener : this.mEntertainmentCardListChangeListenerList) {
            List<EntertainmentCardItem> list = this.mEntertainmentCardItemArrayList;
            entertainmentCardListChangeListener.onItemRangeInserted(list, list.size() - 1, 1);
        }
    }

    public void addEntertainmentCardItemListListener(EntertainmentLandingPageContract.EntertainmentCardListChangeListener entertainmentCardListChangeListener) {
        Log.i(TAG, "addEntertainmentCardItemListListener");
        this.mEntertainmentCardListChangeListenerList.add(entertainmentCardListChangeListener);
    }

    public void clear() {
        Log.i(TAG, MetricsConstants.Method.CACHE_CLEAR);
        int size = this.mEntertainmentCardItemArrayList.size();
        this.mEntertainmentCardItemArrayList.clear();
        for (EntertainmentLandingPageContract.EntertainmentCardListChangeListener entertainmentCardListChangeListener : this.mEntertainmentCardListChangeListenerList) {
            entertainmentCardListChangeListener.onItemRangeRemoved(this.mEntertainmentCardItemArrayList, 0, size);
        }
    }

    public List getEntertainmentCardItemList() {
        return this.mEntertainmentCardItemArrayList;
    }

    public void removeEntertainmentCardItem(String str) {
        GeneratedOutlineSupport1.outline163("removeEntertainmentCardItem ", str, TAG);
        for (int i = 0; i < this.mEntertainmentCardItemArrayList.size(); i++) {
            if (this.mEntertainmentCardItemArrayList.get(i).getQueueId().equals(str)) {
                this.mEntertainmentCardItemArrayList.remove(i);
                for (EntertainmentLandingPageContract.EntertainmentCardListChangeListener entertainmentCardListChangeListener : this.mEntertainmentCardListChangeListenerList) {
                    entertainmentCardListChangeListener.onItemRangeRemoved(this.mEntertainmentCardItemArrayList, i, 1);
                }
                return;
            }
        }
    }

    public void removeEntertainmentCardItemListListener(EntertainmentLandingPageContract.EntertainmentCardListChangeListener entertainmentCardListChangeListener) {
        Log.i(TAG, "removeEntertainmentCardItemListListener");
        this.mEntertainmentCardListChangeListenerList.remove(entertainmentCardListChangeListener);
    }

    public void updateEntertainmentCardItem(EntertainmentCardItem entertainmentCardItem) {
        Log.i(TAG, "updateEntertainmentCardItem ");
        for (int i = 0; i < this.mEntertainmentCardItemArrayList.size(); i++) {
            if (this.mEntertainmentCardItemArrayList.get(i).equals(entertainmentCardItem)) {
                this.mEntertainmentCardItemArrayList.set(i, entertainmentCardItem);
                for (EntertainmentLandingPageContract.EntertainmentCardListChangeListener entertainmentCardListChangeListener : this.mEntertainmentCardListChangeListenerList) {
                    entertainmentCardListChangeListener.onItemRangeChanged(this.mEntertainmentCardItemArrayList, i, 1);
                }
                return;
            }
        }
    }
}
