package com.amazon.alexa;

import com.amazon.alexa.api.AlertType;
import com.amazon.alexa.api.AlertsListener;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AlertsAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class QIY {
    public static final String zZm = "QIY";
    public final AlexaClientEventBus BIo;
    public final Set<AlertsListener> zQM;

    @Inject
    public QIY(AlexaClientEventBus alexaClientEventBus) {
        HashSet hashSet = new HashSet();
        this.BIo = alexaClientEventBus;
        this.zQM = hashSet;
        alexaClientEventBus.zZm(this);
    }

    public synchronized void BIo(AlertsListener alertsListener) {
        this.zQM.remove(alertsListener);
    }

    @Subscribe
    public synchronized void on(aBV abv) {
        String str = ((C0187aUD) abv).BIo;
        AlertType alertType = ((C0187aUD) abv).zQM;
        for (AlertsListener alertsListener : this.zQM) {
            alertsListener.onAlertStarted(str, alertType);
        }
    }

    public void zZm() {
        this.zQM.clear();
        this.BIo.BIo(this);
    }

    public synchronized void zZm(AlertsListener alertsListener) {
        Preconditions.notNull(alertsListener, "alertsListener is null");
        this.zQM.add(alertsListener);
    }

    @Subscribe
    public synchronized void on(thq thqVar) {
        String str = ((TGU) thqVar).BIo;
        AlertType alertType = ((TGU) thqVar).zQM;
        for (AlertsListener alertsListener : this.zQM) {
            alertsListener.onAlertFinished(str, alertType);
        }
    }
}
