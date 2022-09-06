package com.amazon.alexa.voice.ui.player;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.ui.R;
import com.amazon.alexa.voice.ui.internal.Fonts;
import com.amazon.alexa.voice.ui.internal.widget.ContentLayout;
import com.amazon.alexa.voice.ui.player.PlayerContract;
import com.amazon.alexa.voice.ui.player.accessibility.AccessibilityDelegate;
import com.amazon.regulator.ViewController;
import com.bumptech.glide.Glide;
/* loaded from: classes11.dex */
public final class PlayerController extends ViewController implements PlayerContract.View {
    private static final String EXTRA_CARD = "card";
    private static final String EXTRA_COLLAPSED = "collapsed";
    private static final String EXTRA_MOSAIC_THEME = "mosaic_theme";
    private AccessibilityDelegate accessibilityDelegate;
    private boolean collapsed;
    private View collapsedGroupView;
    private ImageView collapsedPlayPauseView;
    private TextView collapsedSubTitleView;
    private TextView collapsedTitleView;
    private ContentLayout contentLayout;
    private ImageView dismissView;
    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
    private ImageView playPauseView;
    private PlayerContract.Presenter presenter;
    private View scrollView;
    private ImageView trackArtView;
    private TextView trackDetailsView;
    private TextView trackDurationView;
    private TextView trackPositionView;
    private ImageView trackProviderView;
    private SeekBar trackSeekPosition;
    private TextView trackTitleView;

    @Deprecated
    public static PlayerController create(PlayerCardModel playerCardModel, boolean z) {
        return create(playerCardModel, z, false);
    }

    private void displayTextIfPresent(TextView textView, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(4);
            return;
        }
        textView.setText(charSequence);
        textView.setVisibility(0);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.View
    public void collapse() {
        this.contentLayout.collapse();
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.View
    public void expand() {
        this.contentLayout.expand();
    }

    public boolean isCollapsed() {
        ContentLayout contentLayout = this.contentLayout;
        return contentLayout != null && contentLayout.isCollapsed();
    }

    public /* synthetic */ void lambda$onCreateView$0$PlayerController() {
        View view = this.collapsedGroupView;
        if (view == null) {
            return;
        }
        view.getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        this.contentLayout.setStickyContentHeight(this.collapsedGroupView.getHeight());
    }

    public /* synthetic */ void lambda$onCreateView$1$PlayerController(View view) {
        this.contentLayout.collapse();
    }

    public /* synthetic */ void lambda$onCreateView$2$PlayerController(View view) {
        this.presenter.dismiss();
    }

    public /* synthetic */ void lambda$onCreateView$3$PlayerController(View view) {
        this.contentLayout.expand();
    }

    public /* synthetic */ void lambda$onCreateView$4$PlayerController(View view) {
        this.presenter.previousClicked();
    }

    public /* synthetic */ void lambda$onCreateView$5$PlayerController(View view) {
        this.presenter.nextClicked();
    }

    public /* synthetic */ void lambda$onCreateView$6$PlayerController(View view) {
        this.presenter.playPauseClicked();
    }

    public /* synthetic */ void lambda$onCreateView$7$PlayerController(View view) {
        this.presenter.playPauseClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        boolean z;
        Bundle arguments = getArguments();
        PlayerCardModel playerCardModel = arguments != null ? (PlayerCardModel) arguments.getParcelable("card") : null;
        if (arguments != null) {
            this.collapsed = arguments.getBoolean(EXTRA_COLLAPSED);
            z = arguments.getBoolean(EXTRA_MOSAIC_THEME);
        } else {
            z = false;
        }
        overrideTheme(z ? R.style.Theme_Alexa_Voice_DayNight : R.style.Theme_Alexa_Voice_Content_Dark);
        if (playerCardModel != null) {
            PlayerInteractor playerInteractor = new PlayerInteractor(playerCardModel, new PlayerMediator(this), (PlaybackController) getComponent().get(PlaybackController.class), (PlayerCardUpdater) getComponent().get(PlayerCardUpdater.class));
            if (getComponent().isRegistered(PlayerCardService.class)) {
                ((PlayerCardService) getComponent().get(PlayerCardService.class)).onPlayerCollapsibleAvailable(playerInteractor);
            }
            if (getComponent().isRegistered(AccessibilityDelegate.class)) {
                this.accessibilityDelegate = (AccessibilityDelegate) getComponent().get(AccessibilityDelegate.class);
            }
            this.presenter = new PlayerPresenter(this, playerInteractor);
            return;
        }
        throw new IllegalStateException("Use PlayerController.create(PlayerCardModel) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    protected View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_player, viewGroup, false);
        this.scrollView = inflate.findViewById(R.id.scrollView);
        this.dismissView = (ImageView) inflate.findViewById(R.id.dismiss);
        this.collapsedGroupView = inflate.findViewById(R.id.collapsedGroup);
        this.collapsedTitleView = (TextView) inflate.findViewById(R.id.collapsedTitle);
        this.collapsedSubTitleView = (TextView) inflate.findViewById(R.id.collapsedSubTitle);
        this.collapsedPlayPauseView = (ImageView) inflate.findViewById(R.id.collapsedPlayPause);
        this.trackArtView = (ImageView) inflate.findViewById(R.id.trackArt);
        this.trackTitleView = (TextView) inflate.findViewById(R.id.trackTitle);
        this.trackDetailsView = (TextView) inflate.findViewById(R.id.trackDetails);
        this.trackPositionView = (TextView) inflate.findViewById(R.id.position);
        this.trackDurationView = (TextView) inflate.findViewById(R.id.duration);
        this.trackSeekPosition = (SeekBar) inflate.findViewById(R.id.seekPosition);
        this.trackProviderView = (ImageView) inflate.findViewById(R.id.trackProvider);
        this.playPauseView = (ImageView) inflate.findViewById(R.id.playPause);
        View findViewById = inflate.findViewById(R.id.previous);
        View findViewById2 = inflate.findViewById(R.id.next);
        Fonts.EMBER_REGULAR.apply(this.trackTitleView, this.trackDetailsView, this.collapsedTitleView, this.collapsedSubTitleView, this.trackPositionView, this.trackDurationView);
        this.contentLayout = (ContentLayout) inflate.findViewById(R.id.layout);
        this.contentLayout.setSticky(true);
        this.contentLayout.setCollapsed(this.collapsed);
        this.contentLayout.setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.player.PlayerController.1
            private static final float SMALL_DELTA = 1.0E-4f;

            @Override // com.amazon.alexa.voice.ui.internal.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.internal.widget.ContentLayout.OnContentListener
            public void onCollapsed() {
                if (PlayerController.this.accessibilityDelegate != null) {
                    PlayerController.this.accessibilityDelegate.enableAccessibility();
                }
                PlayerController.this.scrollView.setVisibility(4);
                PlayerController.this.collapsedPlayPauseView.setEnabled(true);
                PlayerController.this.dismissView.setEnabled(false);
            }

            @Override // com.amazon.alexa.voice.ui.internal.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.internal.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                if (PlayerController.this.accessibilityDelegate != null) {
                    PlayerController.this.accessibilityDelegate.enableAccessibility();
                }
                PlayerController.this.presenter.dismiss();
            }

            @Override // com.amazon.alexa.voice.ui.internal.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.internal.widget.ContentLayout.OnContentListener
            public void onDragged(int i, int i2, int i3, boolean z, boolean z2) {
                float min = Math.min(1.0f, Math.max(0.0f, i / i3));
                PlayerController.this.collapsedGroupView.setAlpha(min);
                PlayerController.this.scrollView.setAlpha(1.0f - min);
                if (min <= 1.0E-4f) {
                    PlayerController.this.collapsedGroupView.setVisibility(4);
                    PlayerController.this.scrollView.setVisibility(0);
                    PlayerController.this.collapsedPlayPauseView.setEnabled(false);
                    PlayerController.this.dismissView.setEnabled(true);
                } else if (min >= 0.9999f) {
                    PlayerController.this.collapsedGroupView.setVisibility(0);
                    PlayerController.this.scrollView.setVisibility(4);
                    PlayerController.this.collapsedPlayPauseView.setEnabled(true);
                    PlayerController.this.dismissView.setEnabled(false);
                } else {
                    PlayerController.this.collapsedGroupView.setVisibility(0);
                    PlayerController.this.scrollView.setVisibility(0);
                }
            }

            @Override // com.amazon.alexa.voice.ui.internal.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.internal.widget.ContentLayout.OnContentListener
            public void onExpanded() {
                if (PlayerController.this.accessibilityDelegate != null) {
                    PlayerController.this.accessibilityDelegate.disableAccessibility();
                }
                PlayerController.this.collapsedGroupView.setVisibility(4);
                PlayerController.this.collapsedPlayPauseView.setEnabled(false);
                PlayerController.this.dismissView.setEnabled(true);
            }
        });
        this.globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerController$4XJAs9EUp56nty1oovcndsUwkgI
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                PlayerController.this.lambda$onCreateView$0$PlayerController();
            }
        };
        this.collapsedGroupView.getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        inflate.findViewById(R.id.downCaret).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerController$9dlzqzDy7GmgH9tgCGmQYtETMWI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerController.this.lambda$onCreateView$1$PlayerController(view);
            }
        });
        inflate.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerController$t_2WLAsUv8Oy1zGk0ylmpS0OV2E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerController.this.lambda$onCreateView$2$PlayerController(view);
            }
        });
        inflate.findViewById(R.id.upCaret).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerController$jguM5f87p8qUYcfgW7msvRCoFO0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerController.this.lambda$onCreateView$3$PlayerController(view);
            }
        });
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerController$oibPtYW7cHqPGPZ4j_4x1Kji-W4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerController.this.lambda$onCreateView$4$PlayerController(view);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerController$pvWei9CxPcJQIVtJfcTiGu6Mdac
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerController.this.lambda$onCreateView$5$PlayerController(view);
            }
        });
        this.playPauseView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerController$kDPKM32FSaRfmmAhXdUk7KNF3Ac
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerController.this.lambda$onCreateView$6$PlayerController(view);
            }
        });
        this.collapsedPlayPauseView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.player.-$$Lambda$PlayerController$QFpl7kpRUqi00iic_5_jr8_1SeQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerController.this.lambda$onCreateView$7$PlayerController(view);
            }
        });
        this.trackSeekPosition.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.alexa.voice.ui.player.PlayerController.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    PlayerController.this.presenter.positionChanged(i);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(View view) {
        Glide.with(getContext().getApplicationContext()).clear(this.trackArtView);
        View view2 = this.collapsedGroupView;
        if (view2 != null) {
            view2.getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        }
        ContentLayout contentLayout = this.contentLayout;
        if (contentLayout != null) {
            contentLayout.removeOnContentListener();
        }
        AccessibilityDelegate accessibilityDelegate = this.accessibilityDelegate;
        if (accessibilityDelegate != null) {
            accessibilityDelegate.enableAccessibility();
        }
        this.scrollView = null;
        this.collapsedGroupView = null;
        this.collapsedSubTitleView = null;
        this.collapsedTitleView = null;
        this.collapsedPlayPauseView = null;
        this.contentLayout = null;
        this.trackArtView = null;
        this.trackTitleView = null;
        this.trackDetailsView = null;
        this.trackPositionView = null;
        this.trackDurationView = null;
        this.trackSeekPosition = null;
        this.playPauseView = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(View view) {
        this.presenter.stop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onRestoreViewState(View view, Bundle bundle) {
        this.contentLayout.setCollapsed(bundle.getBoolean(EXTRA_COLLAPSED));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onSaveViewState(View view, Bundle bundle) {
        bundle.putBoolean(EXTRA_COLLAPSED, isCollapsed());
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.View
    public void setTrackInformation(TrackInformationModel trackInformationModel) {
        CharSequence title = trackInformationModel.getTitle();
        CharSequence description = trackInformationModel.getDescription();
        CharSequence details = trackInformationModel.getDetails();
        String artImageUrl = trackInformationModel.getArtImageUrl();
        displayTextIfPresent(this.trackTitleView, title);
        boolean z = !TextUtils.isEmpty(details) && !TextUtils.isEmpty(description);
        String str = "";
        if (!TextUtils.isEmpty(details) || !TextUtils.isEmpty(description)) {
            String string = getContext().getString(R.string.voice_ui_player_track_details_separator);
            StringBuilder sb = new StringBuilder();
            sb.append((Object) description);
            if (!z) {
                string = str;
            }
            sb.append(string);
            sb.append((Object) trackInformationModel.getDetails());
            str = sb.toString();
        }
        displayTextIfPresent(this.trackDetailsView, str);
        displayTextIfPresent(this.collapsedTitleView, title);
        displayTextIfPresent(this.collapsedSubTitleView, description);
        int i = 8;
        int i2 = trackInformationModel.isSeekable() ? 0 : 8;
        this.trackDurationView.setText(trackInformationModel.getTotalTime());
        this.trackDurationView.setVisibility(i2);
        this.trackSeekPosition.setMax(trackInformationModel.getMaxPosition());
        this.trackSeekPosition.setVisibility(i2);
        this.trackPositionView.setVisibility(i2);
        Glide.with(getContext().getApplicationContext()).clear(this.trackArtView);
        if (TextUtils.isEmpty(artImageUrl)) {
            this.trackArtView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.voice_ui_player_default_art_background));
            this.trackArtView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_streaming_light));
            this.trackArtView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else {
            this.trackArtView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.trackArtView.setBackground(null);
            Glide.with(getContext().getApplicationContext()).mo6762load(artImageUrl).into(this.trackArtView);
        }
        AudioProviderIcon fromString = AudioProviderIcon.fromString(trackInformationModel.getProviderIconId());
        ImageView imageView = this.trackProviderView;
        if (fromString != null) {
            i = 0;
        }
        imageView.setVisibility(i);
        if (fromString != null) {
            this.trackProviderView.setImageDrawable(ContextCompat.getDrawable(getContext(), fromString.getDrawableResourceId()));
            this.trackProviderView.setContentDescription(trackInformationModel.getProviderName());
        }
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.View
    public void setTrackPlaying(boolean z) {
        int i = z ? R.drawable.ic_voice_ui_player_pause : R.drawable.ic_voice_ui_player_play;
        this.playPauseView.setImageResource(i);
        this.collapsedPlayPauseView.setImageResource(i);
        CharSequence text = getContext().getResources().getText(z ? R.string.voice_ui_player_pause : R.string.voice_ui_player_play);
        this.playPauseView.setContentDescription(text);
        this.collapsedPlayPauseView.setContentDescription(text);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.View
    public void setTrackPosition(CharSequence charSequence, int i) {
        this.trackPositionView.setText(charSequence);
        this.trackSeekPosition.setProgress(i);
    }

    public static PlayerController create(PlayerCardModel playerCardModel, boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", playerCardModel);
        bundle.putBoolean(EXTRA_COLLAPSED, z);
        bundle.putBoolean(EXTRA_MOSAIC_THEME, z2);
        PlayerController playerController = new PlayerController();
        playerController.setArguments(bundle);
        return playerController;
    }

    public static PlayerController create(PlayerCardModel playerCardModel) {
        return create(playerCardModel, false);
    }
}
