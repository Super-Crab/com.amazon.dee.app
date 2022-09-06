package com.amazon.alexa.accessory.repositories.crypto;

import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.internal.PeripheralDevice;
import com.amazon.alexa.accessory.internal.PeripheralDevices;
import com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* loaded from: classes6.dex */
public class KeyExchangeInvalidator {
    private static final int KEY_STORE_PAGE_SIZE_LIMIT = 100;
    private final CryptoKeyDataStore accessoryKeyStore;
    private final BluetoothBondObserver bluetoothBondObserver;
    private final Scheduler ioWorkScheduler;
    private boolean isActive;

    /* loaded from: classes6.dex */
    private class BluetoothBondObserver implements BluetoothBondMonitor.Observer {
        private BluetoothBondObserver() {
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor.Observer
        public void onBondRemoved(PeripheralDevice peripheralDevice) {
            Preconditions.notNull(peripheralDevice, "peripheralDevice");
            Logger.d("[KeyExchangeInvalidator] Bluetooth bond removed for %s - deleting all associated crypto keys", peripheralDevice);
            KeyExchangeInvalidator.this.invalidateAllCryptoKeys(peripheralDevice.getAddress());
        }

        @Override // com.amazon.alexa.accessory.internal.monitor.BluetoothBondMonitor.Observer
        public void onBondedAdded(PeripheralDevice peripheralDevice) {
        }
    }

    public KeyExchangeInvalidator(CryptoKeyDataStore cryptoKeyDataStore) {
        this(cryptoKeyDataStore, Schedulers.io());
    }

    private void invalidateAllUnpairedDevices(final Set<String> set) {
        Completable.fromAction(new Action() { // from class: com.amazon.alexa.accessory.repositories.crypto.-$$Lambda$KeyExchangeInvalidator$EIzxtha1cRMnDFLYemLYkl4CLP8
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                KeyExchangeInvalidator.this.lambda$invalidateAllUnpairedDevices$1$KeyExchangeInvalidator(set);
            }
        }).subscribeOn(this.ioWorkScheduler).subscribe(new CompletableObserver() { // from class: com.amazon.alexa.accessory.repositories.crypto.KeyExchangeInvalidator.2
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                Logger.d("Successfully invalidated keys for unpaired devices.");
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                Logger.e("Error invalidating keys for unpaired devices", th);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    private Set<String> listUnpairedDevices(Set<String> set) {
        HashSet hashSet = new HashSet();
        CryptoKeyDataStore.PaginationToken paginationToken = CryptoKeyDataStore.PaginationToken.EMPTY;
        while (true) {
            CryptoKeyDataStore.ListResult listNegotiatedAccessories = this.accessoryKeyStore.listNegotiatedAccessories(100, paginationToken);
            CryptoKeyDataStore.PaginationToken paginationToken2 = listNegotiatedAccessories.paginationToken;
            for (String str : listNegotiatedAccessories.identifiers) {
                if (!set.contains(str)) {
                    hashSet.add(str);
                }
            }
            if (paginationToken2 == CryptoKeyDataStore.PaginationToken.EMPTY) {
                return hashSet;
            }
            paginationToken = paginationToken2;
        }
    }

    private static Set<String> mapToIdentifiers(PeripheralDevices peripheralDevices) {
        HashSet hashSet = new HashSet();
        Iterator<PeripheralDevice> it2 = peripheralDevices.iterator();
        while (it2.hasNext()) {
            hashSet.add(it2.next().getAddress());
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public void activate(BluetoothBondMonitor bluetoothBondMonitor) {
        Preconditions.mainThread();
        if (this.isActive) {
            return;
        }
        if (bluetoothBondMonitor != null) {
            PeripheralDevices bondedDevices = bluetoothBondMonitor.getBondedDevices();
            if (bondedDevices != null && bondedDevices.areAvailable()) {
                invalidateAllUnpairedDevices(mapToIdentifiers(bondedDevices));
            }
            bluetoothBondMonitor.addObserver(this.bluetoothBondObserver);
        }
        this.isActive = true;
    }

    public void deactivate(BluetoothBondMonitor bluetoothBondMonitor) {
        Preconditions.mainThread();
        if (!this.isActive) {
            return;
        }
        if (bluetoothBondMonitor != null) {
            bluetoothBondMonitor.removeObserver(this.bluetoothBondObserver);
        }
        this.isActive = false;
    }

    public void invalidateAllCryptoKeys(final String str) {
        Completable.fromAction(new Action() { // from class: com.amazon.alexa.accessory.repositories.crypto.-$$Lambda$KeyExchangeInvalidator$yGY_zAfGfK3Yoy51KAd4FMP7O0s
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                KeyExchangeInvalidator.this.lambda$invalidateAllCryptoKeys$0$KeyExchangeInvalidator(str);
            }
        }).subscribeOn(this.ioWorkScheduler).subscribe(new CompletableObserver() { // from class: com.amazon.alexa.accessory.repositories.crypto.KeyExchangeInvalidator.1
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                Logger.d("Successfully invalidated keys for accessory.");
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                Logger.e("Error invalidating keys for accessory", th);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    public /* synthetic */ void lambda$invalidateAllCryptoKeys$0$KeyExchangeInvalidator(String str) throws Throwable {
        this.accessoryKeyStore.removeData(str);
    }

    public /* synthetic */ void lambda$invalidateAllUnpairedDevices$1$KeyExchangeInvalidator(Set set) throws Throwable {
        this.accessoryKeyStore.removeData(listUnpairedDevices(set));
    }

    public KeyExchangeInvalidator(CryptoKeyDataStore cryptoKeyDataStore, Scheduler scheduler) {
        this.isActive = false;
        Preconditions.notNull(cryptoKeyDataStore, "accessoryKeyStore");
        Preconditions.notNull(scheduler, "ioWorkScheduler");
        this.accessoryKeyStore = cryptoKeyDataStore;
        this.ioWorkScheduler = scheduler;
        this.bluetoothBondObserver = new BluetoothBondObserver();
    }
}
