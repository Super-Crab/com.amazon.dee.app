package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ip {
    protected static final long rp = jj.b(1, TimeUnit.MILLISECONDS);
    private static volatile boolean rq = false;
    private final eh F = new eh();
    private final ds ia;
    private final Context mContext;
    private final gp rr;

    public ip(Context context) {
        this.rr = new gp(context, "map_version_cache");
        this.mContext = context;
        this.ia = new ds(this.mContext);
    }

    public static String gF() {
        return "20220214P1";
    }

    public JSONObject gG() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("current_version", "20220214P1");
            jSONObject.put("package_name", this.mContext.getPackageName());
            jSONObject.put(MetricsConfiguration.PLATFORM, "Android");
            jSONObject.put("client_metrics_integrated", mq.aQ(this.mContext));
            synchronized (ip.class) {
                String cs = this.rr.cs("map_version_recorded_server");
                if (!"20220214P1".equals(cs)) {
                    jSONObject.put("previous_version", cs);
                    rq = true;
                } else {
                    rq = false;
                }
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public void gH() {
        synchronized (ip.class) {
            if (rq) {
                this.rr.U("map_version_recorded_server", "20220214P1");
                rq = false;
            }
        }
    }

    void gI() {
        this.rr.U("map_version_recorded_client", "20220214P1");
    }

    boolean gJ() {
        boolean z;
        synchronized (ip.class) {
            z = this.rr.cv("last_time_report_version") + rp <= this.F.currentTimeMillis();
            if (z) {
                gL();
            }
        }
        return z;
    }

    boolean gK() {
        boolean z;
        synchronized (ip.class) {
            z = !"20220214P1".equals(this.rr.cs("map_version_recorded_client"));
            if (z) {
                gI();
            }
        }
        return z;
    }

    void gL() {
        this.rr.a("last_time_report_version", this.F.currentTimeMillis());
    }

    public void gM() {
        if (gJ()) {
            if (mz.bb(this.mContext)) {
                mq.aF("Daily_Version_Distribution", "20220214P1");
                mq.incrementCounterAndRecord("20220214P1", new String[0]);
            } else if (this.ia.dj()) {
                mq.aG("Daily_Version_Distribution", "20220214P1");
                mq.incrementCounterAndRecord("20220214P1", new String[0]);
            }
        }
        if (gK()) {
            if (mz.bb(this.mContext)) {
                mq.aF("Bump_Version_Statistics", "20220214P1");
            } else if (!this.ia.dj()) {
            } else {
                mq.aG("Bump_Version_Statistics", "20220214P1");
            }
        }
    }
}
