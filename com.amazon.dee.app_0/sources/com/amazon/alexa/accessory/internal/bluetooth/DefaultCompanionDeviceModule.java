package com.amazon.alexa.accessory.internal.bluetooth;

import android.app.Activity;
import android.app.PendingIntent;
import android.companion.AssociationRequest;
import android.companion.BluetoothDeviceFilter;
import android.companion.CompanionDeviceManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class DefaultCompanionDeviceModule implements CompanionDeviceModule {
    private static final String ADDRESS_KEY = "address";
    private static final String TAG = "DefaultCompanionDeviceModule:";
    private static final Subject<Pair<String, Boolean>> associateSubject = PublishSubject.create().toSerialized();
    private static final Subject<Pair<String, Boolean>> associationsChangedSubject = PublishSubject.create().toSerialized();
    private final BluetoothBondMonitor bluetoothBondMonitor;
    private final CompanionDeviceManager companionDeviceManager;
    private final boolean companionDeviceSetupAvailable;
    private final Context context;

    /* loaded from: classes.dex */
    public static final class RequestCompanionDeviceActivity extends Activity {
        private static final int SELECT_DEVICE_REQUEST_CODE = 42;
        private String address;
        private CompanionDeviceManager companionDeviceManager;

        @RequiresApi(api = 26)
        private CompanionDeviceManager.Callback createCallback() {
            return new CompanionDeviceManager.Callback() { // from class: com.amazon.alexa.accessory.internal.bluetooth.DefaultCompanionDeviceModule.RequestCompanionDeviceActivity.1
                @Override // android.companion.CompanionDeviceManager.Callback
                public void onDeviceFound(IntentSender intentSender) {
                    try {
                        Logger.d("%s onDeviceFound! Executing chooser launcher %s", DefaultCompanionDeviceModule.TAG, intentSender.toString());
                        RequestCompanionDeviceActivity.this.startIntentSenderForResult(intentSender, 42, null, 0, 0, 0);
                    } catch (IntentSender.SendIntentException e) {
                        Logger.e("%s onDeviceFound! But there was an error, cannot launch device chooser", e, DefaultCompanionDeviceModule.TAG);
                        DefaultCompanionDeviceModule.associateSubject.onNext(new Pair(RequestCompanionDeviceActivity.this.address, false));
                        RequestCompanionDeviceActivity.this.finish();
                    }
                }

                @Override // android.companion.CompanionDeviceManager.Callback
                public void onFailure(CharSequence charSequence) {
                    Logger.e("%s Failure finding device to associate to: %s", charSequence, DefaultCompanionDeviceModule.TAG, RequestCompanionDeviceActivity.this.address);
                    DefaultCompanionDeviceModule.associateSubject.onNext(new Pair(RequestCompanionDeviceActivity.this.address, false));
                    RequestCompanionDeviceActivity.this.finish();
                }
            };
        }

        private void preventActivityAnimation() {
            overridePendingTransition(0, 0);
        }

        @RequiresApi(api = 26)
        private void requestDeviceAssociation() {
            Logger.d("%s Attempting to make companion with %s", DefaultCompanionDeviceModule.TAG, this.address);
            try {
                if (DefaultCompanionDeviceModule.isCompanionDevice(this.address, this.companionDeviceManager, getApplicationContext().getPackageManager())) {
                    Logger.d("%s Already a companion device, exiting from RequestCompanionDeviceActivity: %s", DefaultCompanionDeviceModule.TAG, this.address);
                    DefaultCompanionDeviceModule.associateSubject.onNext(new Pair(this.address, true));
                    finish();
                    return;
                }
                AssociationRequest build = new AssociationRequest.Builder().addDeviceFilter(new BluetoothDeviceFilter.Builder().setAddress(this.address).build()).setSingleDevice(true).build();
                CompanionDeviceManager.Callback createCallback = createCallback();
                Logger.d("%s calling deviceManager.associate with filter for address %s", DefaultCompanionDeviceModule.TAG, this.address);
                this.companionDeviceManager.associate(build, createCallback, null);
            } catch (IllegalAccessException e) {
                Logger.e("%s Caught exception in RequestCompanionDeviceActivity for accessory %s", e, DefaultCompanionDeviceModule.TAG, this.address);
                DefaultCompanionDeviceModule.associateSubject.onNext(new Pair(this.address, false));
                finish();
            }
        }

        private void setWindowFlags(int i, boolean z) {
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (z) {
                attributes.flags = i | attributes.flags;
            } else {
                attributes.flags = (~i) & attributes.flags;
            }
            window.setAttributes(attributes);
        }

        private void setWindowUntouchable() {
            setWindowFlags(-2140667760, true);
        }

        @Override // android.app.Activity
        public void finish() {
            super.finish();
            preventActivityAnimation();
        }

        @Override // android.app.Activity
        public void onActivityResult(int i, int i2, Intent intent) {
            if (i != 42 || i2 != -1) {
                Logger.d("%s Result from chooser was not successful, did not make companion device with: %s", DefaultCompanionDeviceModule.TAG, this.address);
                DefaultCompanionDeviceModule.associateSubject.onNext(new Pair(this.address, false));
            } else {
                Logger.d("%s Result from chooser was successful, linked companion device with: %s", DefaultCompanionDeviceModule.TAG, this.address);
                DefaultCompanionDeviceModule.associationsChangedSubject.onNext(new Pair(this.address, true));
                DefaultCompanionDeviceModule.associateSubject.onNext(new Pair(this.address, true));
            }
            finish();
        }

        @Override // android.app.Activity
        @RequiresApi(api = 26)
        protected void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            preventActivityAnimation();
            setWindowUntouchable();
            this.companionDeviceManager = (CompanionDeviceManager) getSystemService(CompanionDeviceManager.class);
            this.address = getIntent().getStringExtra("address");
            Preconditions.notNull(this.address, "address");
            Logger.d("%s onCreate called for RequestCompanionDeviceActivity for accessory %s", DefaultCompanionDeviceModule.TAG, this.address);
            requestDeviceAssociation();
        }

        @Override // android.app.Activity
        protected void onPause() {
            super.onPause();
            preventActivityAnimation();
        }

        @Override // android.app.Activity
        protected void onStop() {
            super.onStop();
            preventActivityAnimation();
        }
    }

    public DefaultCompanionDeviceModule(Context context, BluetoothBondMonitor bluetoothBondMonitor) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(bluetoothBondMonitor, "bluetoothBondMonitor");
        this.context = context;
        this.bluetoothBondMonitor = bluetoothBondMonitor;
        int i = Build.VERSION.SDK_INT;
        this.companionDeviceSetupAvailable = CompanionDeviceModule.companionDeviceSetupAvailable(context.getPackageManager());
        this.companionDeviceManager = (CompanionDeviceManager) context.getSystemService(CompanionDeviceManager.class);
    }

    private Intent createActivityIntent(String str) {
        Intent intent = new Intent(this.context, RequestCompanionDeviceActivity.class);
        intent.putExtra("address", str);
        intent.setFlags(268500992);
        return intent;
    }

    private boolean isBondedDevice(String str) {
        Iterator<PeripheralDevice> it2 = this.bluetoothBondMonitor.getBondedDevices().iterator();
        while (it2.hasNext()) {
            if (it2.next().getAddress().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$queryNewCompanionDevices$5(Pair pair) throws Throwable {
        return (String) pair.first;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$queryRemovedCompanionDevices$6(Pair pair) throws Throwable {
        return !((Boolean) pair.second).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$queryRemovedCompanionDevices$7(Pair pair) throws Throwable {
        return (String) pair.first;
    }

    private void startActivity(String str) {
        this.context.startActivity(createActivityIntent(str));
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule
    @RequiresApi(api = 26)
    public PendingIntent getRequestCompanionDevicePendingIntent(String str) throws IllegalAccessException {
        if (this.companionDeviceSetupAvailable) {
            return PendingIntent.getActivity(this.context, str.hashCode(), createActivityIntent(str), 268435456);
        }
        throw new IllegalAccessException("Companion Device Setup not supported");
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule
    @RequiresApi(api = 26)
    public boolean isCompanionDevice(String str) throws IllegalAccessException {
        return isCompanionDevice(str, this.companionDeviceManager, this.context.getPackageManager());
    }

    public /* synthetic */ void lambda$null$0$DefaultCompanionDeviceModule(String str, Disposable disposable) throws Throwable {
        startActivity(str);
    }

    public /* synthetic */ SingleSource lambda$requestCompanionDevice$3$DefaultCompanionDeviceModule(final String str) throws Throwable {
        Logger.d("%s requestCompanionDevice: %s", TAG, str);
        if (isCompanionDevice(str)) {
            Logger.d("%s Already a companion device: %s", TAG, str);
            return Single.just(true);
        } else if (!isBondedDevice(str)) {
            Logger.e("%s Address %s does not map to a bonded bluetooth device. Cannot request companion device. Rejecting request without requesting companion device.", TAG, str);
            return Single.just(false);
        } else {
            return associateSubject.doOnSubscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultCompanionDeviceModule$HUkezSvXdxaQaehnrq-98ECsP1k
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultCompanionDeviceModule.this.lambda$null$0$DefaultCompanionDeviceModule(str, (Disposable) obj);
                }
            }).filter(new Predicate() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultCompanionDeviceModule$2gN5hXMe6yyb3_fM6VqXmqutHdA
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object obj) {
                    boolean equals;
                    equals = ((String) ((Pair) obj).first).equals(str);
                    return equals;
                }
            }).firstOrError().map($$Lambda$DefaultCompanionDeviceModule$yqV63ndTiAvi31ZSayUm4GPcG2A.INSTANCE);
        }
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule
    @RequiresApi(api = 26)
    public Observable<String> queryNewCompanionDevices() {
        if (!this.companionDeviceSetupAvailable) {
            return Observable.error(new IllegalAccessException("Companion Device Setup not supported"));
        }
        return associationsChangedSubject.filter($$Lambda$DefaultCompanionDeviceModule$5XNlAU4fiSJIs2_nmGvGPGJtoo.INSTANCE).map($$Lambda$DefaultCompanionDeviceModule$P2dZdHBdvG3xwJLBMuDt8IvtOY.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule
    @RequiresApi(api = 26)
    public Observable<String> queryRemovedCompanionDevices() {
        if (!this.companionDeviceSetupAvailable) {
            return Observable.error(new IllegalAccessException("Companion Device Setup not supported"));
        }
        return associationsChangedSubject.filter($$Lambda$DefaultCompanionDeviceModule$FLOV0V3Gcbb5klZ_FTUagwEDbA8.INSTANCE).map($$Lambda$DefaultCompanionDeviceModule$AzA0CnOqEfdKgkzIf1iRk0Tu5I.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule
    @RequiresApi(api = 26)
    public void removeCompanionDevice(String str) throws IllegalAccessException {
        Preconditions.notNull(str, "identifier");
        if (this.companionDeviceSetupAvailable) {
            Logger.d("%s Removing a companion device: %s", TAG, str);
            if (!isCompanionDevice(str)) {
                return;
            }
            this.companionDeviceManager.disassociate(str);
            associationsChangedSubject.onNext(new Pair<>(str, false));
            return;
        }
        throw new IllegalAccessException("Companion Device Setup not supported");
    }

    @Override // com.amazon.alexa.accessory.internal.bluetooth.CompanionDeviceModule
    @RequiresApi(api = 26)
    public Single<Boolean> requestCompanionDevice(final String str) {
        Preconditions.notNull(str, "identifier");
        if (!this.companionDeviceSetupAvailable) {
            return Single.error(new IllegalAccessException("Companion Device Setup not supported"));
        }
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultCompanionDeviceModule$C4p-9ZI15ilXWXpPFhO1PiuW5ac
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return DefaultCompanionDeviceModule.this.lambda$requestCompanionDevice$3$DefaultCompanionDeviceModule(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 26)
    public static boolean isCompanionDevice(String str, CompanionDeviceManager companionDeviceManager, PackageManager packageManager) throws IllegalAccessException {
        Preconditions.notNull(str, "identifier");
        Preconditions.notNull(companionDeviceManager, "companionDeviceManager");
        if (CompanionDeviceModule.companionDeviceSetupAvailable(packageManager)) {
            List<String> associations = companionDeviceManager.getAssociations();
            Logger.d("%s Current companion device(s): %s", TAG, associations);
            return associations.contains(str);
        }
        throw new IllegalAccessException("Companion Device Setup not supported");
    }
}
