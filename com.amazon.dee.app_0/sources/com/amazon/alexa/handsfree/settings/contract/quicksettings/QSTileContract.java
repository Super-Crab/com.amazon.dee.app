package com.amazon.alexa.handsfree.settings.contract.quicksettings;

import com.amazon.alexa.handsfree.settings.contract.quicksettings.exceptions.TileNotPresentException;
/* loaded from: classes8.dex */
public interface QSTileContract {

    /* loaded from: classes8.dex */
    public interface Listener {
        void setState(int i) throws TileNotPresentException;

        void setTileActive(boolean z) throws TileNotPresentException;

        void setTileDescription(int i) throws TileNotPresentException;

        void updateTile() throws TileNotPresentException;
    }

    /* loaded from: classes8.dex */
    public interface View {
        boolean isTileNull();

        void setContentDescription(int i);

        void setState(int i);

        void setTileActive(boolean z);

        void updateTile();
    }
}
