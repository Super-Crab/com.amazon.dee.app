package com.amazon.alexa.drive.entertainment.repository;

import android.util.Log;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardItem;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardList;
/* loaded from: classes7.dex */
public final class EntertainmentDataRepository {
    private static final String TAG = "EntertainmentDataRepository";
    private EntertainmentCardList mEntertainmentCardList;

    public EntertainmentDataRepository() {
        Log.i(TAG, "EntertainmentDataRepository constructor");
        initialize(new EntertainmentCardList());
    }

    public void addEntertainmentCardItem(EntertainmentCardItem entertainmentCardItem) {
        Log.i(TAG, "addEntertainmentCardItem ");
        this.mEntertainmentCardList.addEntertainmentCardItem(entertainmentCardItem);
    }

    public EntertainmentCardList getEntertainmentCardList() {
        return this.mEntertainmentCardList;
    }

    void initialize(EntertainmentCardList entertainmentCardList) {
        Log.i(TAG, "initialize");
        this.mEntertainmentCardList = entertainmentCardList;
    }

    public void removeEntertainmentCardItem(String str) {
        Log.i(TAG, "removeEntertainmentCardItem ");
        this.mEntertainmentCardList.removeEntertainmentCardItem(str);
    }

    public void updateEntertainmentCardItem(EntertainmentCardItem entertainmentCardItem) {
        Log.i(TAG, "updateEntertainmentCardItem ");
        this.mEntertainmentCardList.updateEntertainmentCardItem(entertainmentCardItem);
    }
}
