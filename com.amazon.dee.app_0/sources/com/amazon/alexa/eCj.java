package com.amazon.alexa;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.MQV;
import com.amazon.alexa.Suv;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_AlexaLocation;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_Altitude;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_Coordinate;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_GeolocationStatePayload;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_Heading;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_LocationServices;
import com.amazon.alexa.client.alexaservice.geolocation.AutoValue_Speed;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.utils.TimeProvider;
import java.util.Date;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: GeolocationComponent.java */
/* loaded from: classes.dex */
public abstract class eCj extends Bwo {
    public final Context BIo;
    public DRc JTe;
    public boolean LPk;
    public final Dtt Qle;
    public final TimeProvider jiA;
    public final LocationManager zQM;
    public final vYS zyO;

    public eCj(AlexaClientEventBus alexaClientEventBus, Context context, LocationManager locationManager, vYS vys, TimeProvider timeProvider, Dtt dtt) {
        super(AvsApiConstants.Geolocation.zZm, AvsApiConstants.Geolocation.ComponentStates.GeolocationState.zZm);
        this.BIo = context;
        this.zQM = locationManager;
        this.zyO = vys;
        this.jiA = timeProvider;
        this.Qle = dtt;
        alexaClientEventBus.zZm(this);
    }

    public boolean BIo() {
        boolean z = this.LPk;
        this.LPk = JTe() && jiA();
        if (z && !this.LPk) {
            yPL();
        } else if (!z && this.LPk) {
            LPk();
        }
        return this.LPk;
    }

    public final boolean JTe() {
        return this.zQM.isProviderEnabled("gps") || this.zQM.isProviderEnabled("network");
    }

    public abstract void LPk();

    public abstract void Mlj();

    public final boolean Qle() {
        int i = Build.VERSION.SDK_INT;
        boolean z = this.Qle.zZm(this.BIo, "android.permission.ACCESS_FINE_LOCATION") == 0;
        boolean z2 = this.Qle.zZm(this.BIo, "android.permission.ACCESS_COARSE_LOCATION") == 0;
        if (Build.VERSION.SDK_INT <= 30) {
            if (z && z2) {
                return true;
            }
        } else if (z || z2) {
            return true;
        }
        return false;
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        Suv.zZm zZm = Suv.zZm().zZm(this.jiA.getCurrentTimestamp()).zZm(zyO());
        DRc dRc = this.JTe;
        if (BIo() && dRc != null) {
            YOR yor = (YOR) dRc;
            MQV.zZm zzm = (MQV.zZm) zZm.zZm(yor.zZm);
            zzm.zQM = yor.BIo;
            zzm.zyO = yor.zQM;
            zzm.jiA = yor.zyO;
            zzm.Qle = yor.jiA;
        }
        MQV.zZm zzm2 = (MQV.zZm) zZm;
        String str = "";
        if (zzm2.zZm == null) {
            str = C0179Pya.zZm(str, " locationServices");
        }
        if (zzm2.BIo == null) {
            str = C0179Pya.zZm(str, " timestamp");
        }
        if (str.isEmpty()) {
            return ComponentState.create(zZm(), new AutoValue_GeolocationStatePayload(zzm2.zZm, zzm2.BIo, zzm2.zQM, zzm2.zyO, zzm2.jiA, zzm2.Qle));
        }
        throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
    }

    public boolean jiA() {
        if (Build.VERSION.SDK_INT <= 28 || this.zyO.zQM()) {
            return Qle();
        }
        return this.Qle.zZm(this.BIo, "android.permission.ACCESS_BACKGROUND_LOCATION") == 0 && Qle();
    }

    @Subscribe
    public void on(ZBK zbk) {
        BIo();
    }

    @MainThread
    public void onLocationChanged(Location location) {
        AutoValue_Altitude autoValue_Altitude;
        if (!location.hasAccuracy() || location.getAccuracy() <= 0.0f) {
            return;
        }
        Date date = new Date(location.getTime());
        Double valueOf = Double.valueOf(location.getLatitude());
        Double valueOf2 = Double.valueOf(location.getLongitude());
        Double valueOf3 = Double.valueOf(location.getAccuracy());
        String str = "";
        String zZm = valueOf == null ? C0179Pya.zZm(str, " latitudeInDegrees") : str;
        if (valueOf2 == null) {
            zZm = C0179Pya.zZm(zZm, " longitudeInDegrees");
        }
        if (valueOf3 == null) {
            zZm = C0179Pya.zZm(zZm, " accuracyInMeters");
        }
        if (zZm.isEmpty()) {
            AutoValue_Coordinate autoValue_Coordinate = new AutoValue_Coordinate(valueOf.doubleValue(), valueOf2.doubleValue(), valueOf3.doubleValue());
            if (location.hasAltitude()) {
                Double valueOf4 = Double.valueOf(location.getAltitude());
                Double valueOf5 = Double.valueOf(location.getAccuracy());
                if (valueOf4 == null) {
                    str = C0179Pya.zZm(str, " altitudeInMeters");
                }
                if (valueOf5 == null) {
                    str = C0179Pya.zZm(str, " accuracyInMeters");
                }
                if (str.isEmpty()) {
                    autoValue_Altitude = new AutoValue_Altitude(valueOf4.doubleValue(), valueOf5.doubleValue());
                } else {
                    throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
                }
            } else {
                autoValue_Altitude = null;
            }
            this.JTe = new AutoValue_AlexaLocation(date, autoValue_Coordinate, autoValue_Altitude, new AutoValue_Heading(location.getBearing()), new AutoValue_Speed(location.getSpeed()));
            return;
        }
        throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", zZm));
    }

    public abstract void yPL();

    public LocationManager zQM() {
        return this.zQM;
    }

    @VisibleForTesting
    public Xdr zyO() {
        return new AutoValue_LocationServices((!Qle() || !JTe()) ? gZN.DISABLED : gZN.ENABLED, (!jiA() || !JTe()) ? kNm.STOPPED : kNm.RUNNING);
    }

    @Subscribe
    public void on(zCQ zcq) {
        BIo();
    }
}
