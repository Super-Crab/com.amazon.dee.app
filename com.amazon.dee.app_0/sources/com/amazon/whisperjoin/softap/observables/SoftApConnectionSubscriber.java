package com.amazon.whisperjoin.softap.observables;

import com.amazon.whisperjoin.softap.exceptions.SoftAPUnableToEstablishConnectionException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnexpectedConnectionLostException;
import com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector;
import rx.Subscriber;
/* loaded from: classes13.dex */
public class SoftApConnectionSubscriber implements WifiNetworkConnector.OnConnectionListener {
    private final Subscriber<? super Void> subscriber;

    public SoftApConnectionSubscriber(Subscriber<? super Void> subscriber) {
        this.subscriber = subscriber;
    }

    @Override // com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector.OnConnectionListener
    public void onConnectionDropped() {
        if (this.subscriber.isUnsubscribed()) {
            return;
        }
        this.subscriber.onError(new SoftAPUnexpectedConnectionLostException("Connection dropped"));
    }

    @Override // com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector.OnConnectionListener
    public void onConnectionFailure() {
        if (this.subscriber.isUnsubscribed()) {
            return;
        }
        this.subscriber.onError(new SoftAPUnableToEstablishConnectionException("Connection failed"));
    }

    @Override // com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector.OnConnectionListener
    public void onConnectionFinished() {
        if (this.subscriber.isUnsubscribed()) {
            return;
        }
        this.subscriber.onCompleted();
    }

    @Override // com.amazon.whisperjoin.softap.wifi.WifiNetworkConnector.OnConnectionListener
    public void onConnectionSuccess() {
        if (this.subscriber.isUnsubscribed()) {
            return;
        }
        this.subscriber.onNext(null);
    }
}
