package com.google.firebase.messaging;

import androidx.annotation.Keep;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.Arrays;
import java.util.List;
/* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
@Keep
@KeepForSdk
/* loaded from: classes3.dex */
public class FirebaseMessagingRegistrar implements ComponentRegistrar {

    /* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    @VisibleForTesting
    /* loaded from: classes3.dex */
    public static class zza implements TransportFactory {
        @Override // com.google.android.datatransport.TransportFactory
        public final <T> Transport<T> getTransport(String str, Class<T> cls, Transformer<T, byte[]> transformer) {
            return new zzb();
        }

        @Override // com.google.android.datatransport.TransportFactory
        public final <T> Transport<T> getTransport(String str, Class<T> cls, Encoding encoding, Transformer<T, byte[]> transformer) {
            return new zzb();
        }
    }

    /* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    /* loaded from: classes3.dex */
    private static class zzb<T> implements Transport<T> {
        private zzb() {
        }

        @Override // com.google.android.datatransport.Transport
        public final void schedule(Event<T> event, TransportScheduleCallback transportScheduleCallback) {
            transportScheduleCallback.onSchedule(null);
        }

        @Override // com.google.android.datatransport.Transport
        public final void send(Event<T> event) {
        }
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    @Keep
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirebaseMessaging.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(FirebaseInstanceId.class)).add(Dependency.optional(TransportFactory.class)).factory(zzj.zza).alwaysEager().build());
    }
}
