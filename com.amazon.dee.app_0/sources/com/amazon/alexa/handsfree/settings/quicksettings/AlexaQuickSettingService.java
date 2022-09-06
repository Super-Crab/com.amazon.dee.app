package com.amazon.alexa.handsfree.settings.quicksettings;

import android.graphics.drawable.Icon;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.R;
import com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
@RequiresApi(api = 24)
/* loaded from: classes8.dex */
public class AlexaQuickSettingService extends TileService implements QSTileContract.View {
    private static final String TAG = AlexaQuickSettingService.class.getSimpleName();
    private EventBus mEventBus;
    private Icon mIconOff;
    private Icon mIconOn;
    private final Initializer mInitializer;
    private QsTilePresenter mQsTilePresenter;
    private MultiFilterSubscriber mSubscriber;
    private Tile mTile;
    private UVRConnector mUVRConnector;

    public AlexaQuickSettingService() {
        this.mInitializer = InitializerProvider.getInitializer();
    }

    private void initializeQsTilePresenter() {
        this.mInitializer.initialize(this);
        this.mQsTilePresenter = new QsTilePresenter(this, this);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract.View
    public boolean isTileNull() {
        return this.mTile == null;
    }

    public /* synthetic */ void lambda$onStartListening$1$AlexaQuickSettingService(Message message) {
        this.mQsTilePresenter.syncHandsFree();
    }

    @Override // android.service.quicksettings.TileService
    public void onClick() {
        Log.d(TAG, "onClick");
        if (isLocked()) {
            unlockAndRun(new Runnable() { // from class: com.amazon.alexa.handsfree.settings.quicksettings.AlexaQuickSettingService.1
                @Override // java.lang.Runnable
                public void run() {
                    AlexaQuickSettingService.this.mQsTilePresenter.updateHandsFree();
                }
            });
        } else {
            this.mQsTilePresenter.updateHandsFree();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (this.mQsTilePresenter == null) {
            this.mInitializer.initialize(this);
            this.mIconOn = Icon.createWithResource(this, R.drawable.ic_amazon_alexa_handsfree);
            this.mIconOff = Icon.createWithResource(this, R.drawable.ic_amazon_alexa_handsfree_off);
            this.mQsTilePresenter = new QsTilePresenter(this, this);
            this.mEventBus = (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
        }
    }

    @Override // android.service.quicksettings.TileService
    public void onStartListening() {
        super.onStartListening();
        Log.d(TAG, "onStartListening");
        this.mTile = getQsTile();
        if (this.mQsTilePresenter == null) {
            initializeQsTilePresenter();
        }
        if (UVRModule.INSTANCE.isUVRAvailable()) {
            this.mUVRConnector = UVRModule.INSTANCE.getUVRContract().getUVRConnector();
            this.mUVRConnector.startConnection(this, false);
        }
        this.mQsTilePresenter.syncHandsFree();
        this.mSubscriber = this.mEventBus.getNewSubscriber();
        this.mSubscriber.subscribeFilter($$Lambda$AlexaQuickSettingService$IjE4WM2e4jOcgMbZgu8HbtM2uRg.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.handsfree.settings.quicksettings.-$$Lambda$AlexaQuickSettingService$XZ_6s62uDHW6uEJT1VUR-c0ZlFA
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AlexaQuickSettingService.this.lambda$onStartListening$1$AlexaQuickSettingService(message);
            }
        });
    }

    @Override // android.service.quicksettings.TileService
    public void onStopListening() {
        super.onStopListening();
        Log.d(TAG, "onStopListening");
        this.mEventBus.unsubscribe(this.mSubscriber);
        if (this.mQsTilePresenter == null) {
            initializeQsTilePresenter();
        }
        this.mQsTilePresenter.stopListening();
        if (UVRModule.INSTANCE.isUVRAvailable()) {
            this.mUVRConnector.endConnection(this);
        }
    }

    @Override // android.service.quicksettings.TileService
    public void onTileAdded() {
        if (this.mQsTilePresenter == null) {
            initializeQsTilePresenter();
        }
        this.mQsTilePresenter.onTileAdded();
    }

    @Override // android.service.quicksettings.TileService
    public void onTileRemoved() {
        if (this.mQsTilePresenter == null) {
            initializeQsTilePresenter();
        }
        this.mQsTilePresenter.onTileRemoved();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract.View
    public void setContentDescription(int i) {
        this.mTile.setContentDescription(getString(i));
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract.View
    public void setState(int i) {
        this.mTile.setState(i);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract.View
    public void setTileActive(boolean z) {
        this.mTile.setIcon(z ? this.mIconOn : this.mIconOff);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract.View
    public void updateTile() {
        this.mTile.updateTile();
    }

    @VisibleForTesting
    AlexaQuickSettingService(@NonNull QsTilePresenter qsTilePresenter, @NonNull Icon icon, @NonNull Icon icon2, @NonNull Tile tile, @NonNull Initializer initializer, @NonNull EventBus eventBus) {
        this.mQsTilePresenter = qsTilePresenter;
        this.mIconOn = icon;
        this.mIconOff = icon2;
        this.mTile = tile;
        this.mInitializer = initializer;
        this.mEventBus = eventBus;
    }
}
