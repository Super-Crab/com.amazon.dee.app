package com.amazon.alexa.redesign.debug.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.debug.menu.DebugMenuContract;
import com.amazon.alexa.redesign.entity.DebugMenuModel;
import com.amazon.regulator.ViewController;
/* loaded from: classes10.dex */
public class DebugMenuController extends ViewController implements DebugMenuContract.View {
    private Switch bypass;
    private Button exitButton;
    private Switch localJSON;
    private DebugMenuContract.Presenter presenter;
    private View view;

    public static DebugMenuController create() {
        return new DebugMenuController();
    }

    @Override // com.amazon.alexa.redesign.debug.menu.DebugMenuContract.View
    public void bindDebugModel(DebugMenuModel debugMenuModel) {
        this.bypass.setChecked(debugMenuModel.getBypass());
        this.localJSON.setChecked(debugMenuModel.getLocalJSON());
    }

    @Override // com.amazon.alexa.redesign.debug.menu.DebugMenuContract.View
    public void close() {
        getRouter().popController(this);
    }

    public /* synthetic */ void lambda$onCreateView$0$DebugMenuController(View view) {
        this.presenter.onExitClicked();
    }

    public /* synthetic */ void lambda$onCreateView$1$DebugMenuController(CompoundButton compoundButton, boolean z) {
        this.presenter.onBypassSwitchChanged(z);
    }

    public /* synthetic */ void lambda$onCreateView$2$DebugMenuController(CompoundButton compoundButton, boolean z) {
        this.presenter.onLocalJSONSwitchChanged(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        this.presenter.onStart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        this.presenter = new DebugMenuPresenter(this, new DebugMenuInteractor());
    }

    @Override // com.amazon.regulator.ViewController
    protected View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.view = layoutInflater.inflate(R.layout.amahc_debug_menu, viewGroup, false);
        this.exitButton = (Button) this.view.findViewById(R.id.exit_button);
        this.exitButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.debug.menu.-$$Lambda$DebugMenuController$I69IChydxucWfdHPvL1iuDBW_Xk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DebugMenuController.this.lambda$onCreateView$0$DebugMenuController(view);
            }
        });
        this.bypass = (Switch) this.view.findViewById(R.id.bypass_switch);
        this.bypass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.alexa.redesign.debug.menu.-$$Lambda$DebugMenuController$xVbmDWx-JMD91adzZJYrNUFdHW4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DebugMenuController.this.lambda$onCreateView$1$DebugMenuController(compoundButton, z);
            }
        });
        this.localJSON = (Switch) this.view.findViewById(R.id.localJSON_switch);
        this.localJSON.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.alexa.redesign.debug.menu.-$$Lambda$DebugMenuController$TBvXX994mC6LuYsEVaXzVsIPHV0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DebugMenuController.this.lambda$onCreateView$2$DebugMenuController(compoundButton, z);
            }
        });
        return this.view;
    }
}
