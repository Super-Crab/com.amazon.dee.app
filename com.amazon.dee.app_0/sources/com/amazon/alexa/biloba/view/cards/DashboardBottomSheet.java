package com.amazon.alexa.biloba.view.cards;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.storage.CardsStore;
import com.amazon.alexa.biloba.view.common.DismissHandler;
import com.amazon.alexa.mosaic.view.ListItem;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class DashboardBottomSheet extends BottomSheetDialogFragment implements DismissHandler {
    private final String cardId;
    @Inject
    Lazy<CardsStore> cardsStore;
    private final SharedPreferences sharedPreferences;
    @VisibleForTesting
    DialogInterface.OnShowListener showListener;

    public DashboardBottomSheet(String str, SharedPreferences sharedPreferences) {
        BilobaDependencies.inject(this);
        this.cardId = str;
        this.sharedPreferences = sharedPreferences;
    }

    @Override // com.amazon.alexa.biloba.view.common.DismissHandler
    public void handleDismiss(View view) {
        this.cardsStore.mo358get().dismissCard(this.cardId, this.sharedPreferences);
    }

    public /* synthetic */ void lambda$onCreateView$0$DashboardBottomSheet(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$onCreateView$1$DashboardBottomSheet(View view) {
        handleDismiss(view);
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.TransparentBottomSheetDialogTheme);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(bundle);
        if (this.showListener == null) {
            this.showListener = $$Lambda$DashboardBottomSheet$oziftzF8H8LM7dkgU4bBYlQtV_0.INSTANCE;
        }
        bottomSheetDialog.setOnShowListener(this.showListener);
        return bottomSheetDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet, viewGroup, false);
        ((RelativeLayout) inflate.findViewById(R.id.bottom_sheet_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.cards.-$$Lambda$DashboardBottomSheet$Cl3CWII7J9s2LC6gzJTsWm7Weyw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardBottomSheet.this.lambda$onCreateView$0$DashboardBottomSheet(view);
            }
        });
        ((ListItem) inflate.findViewById(R.id.bottom_sheet_menu_dismiss)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.cards.-$$Lambda$DashboardBottomSheet$yFSZRWJ-9ZEmos-gJbqxPckk_uo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DashboardBottomSheet.this.lambda$onCreateView$1$DashboardBottomSheet(view);
            }
        });
        return inflate;
    }

    @VisibleForTesting
    DashboardBottomSheet(String str, SharedPreferences sharedPreferences, Lazy<CardsStore> lazy) {
        this.cardId = str;
        this.sharedPreferences = sharedPreferences;
        this.cardsStore = lazy;
    }
}
