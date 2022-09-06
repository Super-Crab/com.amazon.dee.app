package com.amazon.alexa.handsfree.settings.quicksettings;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager;
import com.amazon.alexa.handsfree.settings.R;
import com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract;
import com.amazon.alexa.handsfree.settings.contract.quicksettings.exceptions.TileNotPresentException;
import com.amazon.alexa.handsfree.settings.metrics.ClickMetricMetadata;
import com.amazon.alexa.handsfree.settings.quicksettings.AlexaQuickSettingTileStore;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class QsTilePresenter implements QSTileContract.Listener {
    private AlexaHandsFreeManager mAlexaHandsFreeManager;
    private AlexaQuickSettingTileStore mAlexaQuickSettingTileStore;
    private final QSTileContract.View mQsView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public QsTilePresenter(@NonNull QSTileContract.View view, @NonNull Context context) {
        this.mQsView = view;
        this.mAlexaHandsFreeManager = new AlexaQuickSettingTileManager(context, this);
        this.mAlexaQuickSettingTileStore = new AlexaQuickSettingTileStore(context);
    }

    private void assertTileNotNull() throws TileNotPresentException {
        if (!this.mQsView.isTileNull()) {
            return;
        }
        throw new TileNotPresentException("Tile is no longer present, unable to do an operation on it");
    }

    @VisibleForTesting
    int getTileDescriptionResource(int i) {
        if (i != 1) {
            if (i != 2) {
                return R.string.alexa_handsfree_tile_description_unavailable;
            }
            return R.string.alexa_handsfree_tile_description_active;
        }
        return R.string.alexa_handsfree_tile_description_inactive;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onTileAdded() {
        this.mAlexaHandsFreeManager.reportClickMetric(ClickMetricMetadata.Action.TILE_ADDED);
        this.mAlexaQuickSettingTileStore.setQsTileLocation(AlexaQuickSettingTileStore.QuickSettingTileLocation.MAIN_MENU);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onTileRemoved() {
        this.mAlexaHandsFreeManager.reportClickMetric(ClickMetricMetadata.Action.TILE_REMOVED);
        this.mAlexaQuickSettingTileStore.setQsTileLocation(AlexaQuickSettingTileStore.QuickSettingTileLocation.EDIT_MENU);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract.Listener
    public void setState(int i) throws TileNotPresentException {
        assertTileNotNull();
        this.mQsView.setState(i);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract.Listener
    public void setTileActive(boolean z) throws TileNotPresentException {
        assertTileNotNull();
        this.mQsView.setTileActive(z);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract.Listener
    public void setTileDescription(int i) throws TileNotPresentException {
        assertTileNotNull();
        this.mQsView.setContentDescription(getTileDescriptionResource(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopListening() {
        this.mAlexaHandsFreeManager.releaseCheckSignInThread();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void syncHandsFree() {
        this.mAlexaHandsFreeManager.refreshHandsFree();
        this.mAlexaHandsFreeManager.connectAndCheckSignIn();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateHandsFree() {
        this.mAlexaHandsFreeManager.updateHandsFreeState();
        this.mAlexaQuickSettingTileStore.markInteractedWithQsTile();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract.Listener
    public void updateTile() throws TileNotPresentException {
        assertTileNotNull();
        this.mQsView.updateTile();
    }

    @VisibleForTesting
    QsTilePresenter(@NonNull QSTileContract.View view, @NonNull AlexaHandsFreeManager alexaHandsFreeManager, @NonNull AlexaQuickSettingTileStore alexaQuickSettingTileStore) {
        this.mQsView = view;
        this.mAlexaHandsFreeManager = alexaHandsFreeManager;
        this.mAlexaQuickSettingTileStore = alexaQuickSettingTileStore;
    }
}
