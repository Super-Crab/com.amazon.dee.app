package com.amazon.deecomms.calling.incallexperiences.effects.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract;
import com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsDataStore;
import com.amazon.deecomms.calling.ui.GlideImageLoader;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class EffectsBottomSheetDialogFragmentView extends BottomSheetDialogFragment implements EffectsMenuViewContract {
    private static final CommsLogger LOG = CommsLogger.getLogger(EffectsBottomSheetDialogFragmentView.class);
    private LinearLayout callButtonLayout;
    private View contentView;
    private final Context context;
    private List<Drawable> effectThumbnails;
    private RelativeLayout effectsButtonWrapper;
    private ImageButton effectsMenuButton;
    private EffectsMenuPresenter effectsMenuPresenter;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private Handler uiHandler;

    public EffectsBottomSheetDialogFragmentView(Context context) {
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.context = context;
    }

    private void initializeCategoryRecyclerView(View view) {
        this.recyclerView = (RecyclerView) view.findViewById(R.id.effects_recycler_view);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context, 0, false));
        this.recyclerView.setAdapter(new EffectsRecyclerViewAdapter(this.effectsMenuPresenter, new GlideImageLoader(), (EffectsDataStore.getEffectsInstance() == null || EffectsDataStore.getEffectsInstance().getEffectData() == null || EffectsDataStore.getEffectsInstance().getEffectData().getEffectIcons() == null) ? new ArrayList<>() : EffectsDataStore.getEffectsInstance().getEffectData().getEffectIcons()));
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract
    public void hideButton() {
        this.effectsButtonWrapper.setVisibility(8);
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract
    public void hideMenu() {
        dismiss();
    }

    public void initialize(LinearLayout linearLayout) {
        this.callButtonLayout = linearLayout;
        this.effectsButtonWrapper = (RelativeLayout) linearLayout.findViewById(R.id.effect_button_wrapper);
        this.effectsMenuButton = (ImageButton) linearLayout.findViewById(R.id.effectMenuButton);
        this.effectsMenuButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.ui.-$$Lambda$EffectsBottomSheetDialogFragmentView$H9a-TfGarwZ58OTkocdGBj8iOHs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EffectsBottomSheetDialogFragmentView.this.lambda$initialize$0$EffectsBottomSheetDialogFragmentView(view);
            }
        });
    }

    public boolean isShowing() {
        return mo3821getDialog() != null && mo3821getDialog().isShowing();
    }

    public /* synthetic */ void lambda$initialize$0$EffectsBottomSheetDialogFragmentView(View view) {
        LOG.i("[EFFECTS] Effects menu button pressed.");
        this.effectsMenuPresenter.onMenuButtonTapped();
    }

    public /* synthetic */ void lambda$refreshMenu$3$EffectsBottomSheetDialogFragmentView() {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null || recyclerView.getAdapter() == null) {
            return;
        }
        this.recyclerView.getAdapter().notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$setupDialog$1$EffectsBottomSheetDialogFragmentView() {
        BottomSheetBehavior from = BottomSheetBehavior.from((FrameLayout) ((BottomSheetDialog) mo3821getDialog()).findViewById(R.id.design_bottom_sheet));
        from.setState(3);
        from.setPeekHeight(0);
    }

    public /* synthetic */ void lambda$setupDialog$2$EffectsBottomSheetDialogFragmentView(Dialog dialog, View view) {
        LOG.i("Effects menu back button pressed");
        this.contentView.setVisibility(8);
        this.callButtonLayout.setVisibility(0);
        dialog.dismiss();
    }

    public /* synthetic */ void lambda$showButton$4$EffectsBottomSheetDialogFragmentView() {
        this.effectsMenuButton.setBackgroundTintList(null);
        this.effectsButtonWrapper.setVisibility(0);
    }

    public /* synthetic */ void lambda$showGrayedOutButton$5$EffectsBottomSheetDialogFragmentView() {
        this.effectsMenuButton.setBackgroundTintList(this.context.getResources().getColorStateList(R.color.effects_disabled_button));
        this.effectsButtonWrapper.setVisibility(0);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.EffectsBottomSheetDialogTheme);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = mo3821getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.0f;
        attributes.flags |= 2;
        window.setAttributes(attributes);
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract
    public void refreshMenu() {
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.ui.-$$Lambda$EffectsBottomSheetDialogFragmentView$puCv1Q1hSmxAUhicUqdUcCWmTIg
            @Override // java.lang.Runnable
            public final void run() {
                EffectsBottomSheetDialogFragmentView.this.lambda$refreshMenu$3$EffectsBottomSheetDialogFragmentView();
            }
        });
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract
    public void setPresenter(EffectsMenuPresenterContract effectsMenuPresenterContract) {
        this.effectsMenuPresenter = (EffectsMenuPresenter) effectsMenuPresenterContract;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract
    public void setThumbnails(List<Drawable> list) {
        this.effectThumbnails = list;
    }

    @Override // androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    public void setupDialog(final Dialog dialog, int i) {
        super.setupDialog(dialog, i);
        this.layoutInflater = dialog.getLayoutInflater();
        this.callButtonLayout = (LinearLayout) this.layoutInflater.inflate(R.layout.fiesta_active_video_call_view_audiopicker, (ViewGroup) null).findViewById(R.id.call_button_layout);
        this.effectsButtonWrapper = (RelativeLayout) this.callButtonLayout.findViewById(R.id.effect_button_wrapper);
        this.contentView = this.layoutInflater.inflate(R.layout.fiesta_effects_menu_layout, (ViewGroup) null);
        LinearLayout linearLayout = this.callButtonLayout;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        dialog.setContentView(this.contentView);
        initializeCategoryRecyclerView(this.contentView);
        this.contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.ui.-$$Lambda$EffectsBottomSheetDialogFragmentView$y4Bqa4bxuUah_gOcMauKHd7dGHw
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                EffectsBottomSheetDialogFragmentView.this.lambda$setupDialog$1$EffectsBottomSheetDialogFragmentView();
            }
        });
        ((LinearLayout) this.contentView.findViewById(R.id.back_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.ui.-$$Lambda$EffectsBottomSheetDialogFragmentView$4Ie1w7SLLif1Nhrfc15Y2vZ_Ht4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EffectsBottomSheetDialogFragmentView.this.lambda$setupDialog$2$EffectsBottomSheetDialogFragmentView(dialog, view);
            }
        });
        dialog.show();
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract
    public void showButton() {
        if (!this.effectsMenuPresenter.areEffectsAvailable()) {
            this.effectsMenuPresenter.fetchScribeSetting();
            this.effectsMenuPresenter.onEffectsNotAvailable();
            return;
        }
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.ui.-$$Lambda$EffectsBottomSheetDialogFragmentView$wFjG2Vn9aRAQsa3CarTsD18TFjQ
            @Override // java.lang.Runnable
            public final void run() {
                EffectsBottomSheetDialogFragmentView.this.lambda$showButton$4$EffectsBottomSheetDialogFragmentView();
            }
        });
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract
    public void showGrayedOutButton() {
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.ui.-$$Lambda$EffectsBottomSheetDialogFragmentView$4-IPn1ZIo9x3osohpGpWZC1ZSk0
            @Override // java.lang.Runnable
            public final void run() {
                EffectsBottomSheetDialogFragmentView.this.lambda$showGrayedOutButton$5$EffectsBottomSheetDialogFragmentView();
            }
        });
    }

    public void showMenu(FragmentTransaction fragmentTransaction, String str) {
        this.callButtonLayout.setVisibility(8);
        show(fragmentTransaction, str);
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract
    public void showToast(Context context, int i) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(context.getString(i));
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.fiesta_effects_message_toast, (ViewGroup) null);
        ((TextView) linearLayout.getChildAt(0)).setText(outline107);
        Toast toast = new Toast(context);
        toast.setView(linearLayout);
        toast.setGravity(49, 0, 0);
        toast.setDuration(1);
        toast.show();
    }

    @VisibleForTesting
    EffectsBottomSheetDialogFragmentView(Handler handler, EffectsMenuPresenter effectsMenuPresenter, Context context) {
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.uiHandler = handler;
        this.effectsMenuPresenter = effectsMenuPresenter;
        this.context = context;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract
    public void showToast(Context context, String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.fiesta_effects_message_toast, (ViewGroup) null);
        ((TextView) linearLayout.getChildAt(0)).setText(outline107);
        Toast toast = new Toast(context);
        toast.setView(linearLayout);
        toast.setGravity(49, 0, 0);
        toast.setDuration(1);
        toast.show();
    }
}
