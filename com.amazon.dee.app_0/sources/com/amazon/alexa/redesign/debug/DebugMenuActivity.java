package com.amazon.alexa.redesign.debug;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.debug.menu.DebugMenuController;
import com.amazon.alexa.voice.ui.DefaultWindowInteractor;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
import com.amazon.regulator.Component;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
/* loaded from: classes10.dex */
public class DebugMenuActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.amahc_debug_menu_container);
        Component component = new Component();
        component.provide((Class<? extends Class>) WindowInteractor.class, (Class) new DefaultWindowInteractor(getWindow())).register();
        final Router router = new Router(this, component);
        router.attach((ViewGroup) findViewById(R.id.debug_menu_container));
        router.addOnPopTransactionListener(new Router.OnTransactionAdapter() { // from class: com.amazon.alexa.redesign.debug.DebugMenuActivity.1
            @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
            public void onAfterTransition(ControllerTransaction controllerTransaction) {
                if (!router.hasRootController()) {
                    DebugMenuActivity.this.finish();
                }
            }
        });
        if (router.isAttached()) {
            router.pushController(new ControllerTransaction(DebugMenuController.create()));
        }
    }
}
