package com.amazon.alexa;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Stage;
import com.amazon.alexa.client.metrics.core.DefaultMetricsTimer;
import com.amazon.alexa.client.metrics.core.MetricsCounter;
import com.amazon.alexa.client.metrics.core.MetricsTimer;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.voice.ui.CardMetricsInteractorImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Map;
/* compiled from: BaseVoiceCardMetricsAuthority.java */
/* loaded from: classes.dex */
public abstract class ZhG {
    public static final String zZm = "ZhG";
    public final paF BIo;
    public final Lazy<ClientConfiguration> zQM;

    public ZhG(paF paf, TimeProvider timeProvider, Lazy<ClientConfiguration> lazy) {
        this.BIo = paf;
        this.zQM = lazy;
    }

    public void BIo(Bundle bundle) {
        BIo("card_render_controller_failed");
    }

    public void JTe(Bundle bundle) {
        long j = bundle.getLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), -1L);
        if (j >= 0) {
            zZm("card_views_created_latency", j);
        }
    }

    public void LPk(Bundle bundle) {
        String string = bundle.getString(AlexaMetadataBundleKey.ERROR_TYPE.name());
        String string2 = bundle.getString(AlexaMetadataBundleKey.MESSAGE.name());
        if (string == null) {
            Log.w(zZm, "Unknown JSON Parsing error. Null error type");
            return;
        }
        char c = 65535;
        int hashCode = string.hashCode();
        if (hashCode != -1421201840) {
            if (hashCode != -634921136) {
                if (hashCode == -379084147 && string.equals("CARD_DATA_NULL")) {
                    c = 0;
                }
            } else if (string.equals("MISSING_CARD_TYPE")) {
                c = 2;
            }
        } else if (string.equals("INVALID_JSON")) {
            c = 1;
        }
        if (c == 0) {
            zZm("card_data_null", "Null card data");
        } else if (c == 1) {
            zZm("card_invalid_json", string2);
        } else if (c != 2) {
            GeneratedOutlineSupport1.outline164("Unknown JSON Parsing error: ", string, zZm);
        } else {
            zZm("card_type_error", string2);
        }
    }

    public void Qle(Bundle bundle) {
        if (bundle.getBoolean(AlexaMetadataBundleKey.SUCCESS.name())) {
            BIo("occurrence_card_render_success");
        } else {
            zZm("occurrence_card_render_success");
        }
    }

    public void jiA(Bundle bundle) {
        long j = bundle.getLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), -1L);
        if (j >= 0) {
            zZm("card_render_latency", j);
        }
    }

    public void yPL(Bundle bundle) {
        if (bundle.getBoolean(AlexaMetadataBundleKey.SUCCESS.name())) {
            BIo("occurrence_card_valid_json");
        } else {
            zZm("occurrence_card_valid_json");
        }
    }

    public void zQM(Bundle bundle) {
        long j = bundle.getLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), -1L);
        if (j >= 0) {
            zZm("card_json_parse_latency", j);
        }
    }

    public abstract String zZm();

    public void zZm(Bundle bundle, boolean z) {
        if (z) {
            BIo("occurrence_card_matching_success");
            return;
        }
        zZm("occurrence_card_matching_success");
        zZm("card_no_matching_renderer", bundle.getString(AlexaMetadataBundleKey.CARD_NAME.name()));
    }

    public void zyO(Bundle bundle) {
        String zZm2 = C0179Pya.zZm(CardMetricsInteractorImpl.EVENT_USER_INTERACTION, bundle.getString(AlexaMetadataBundleKey.CARD_NAME.name()));
        if (bundle.getBoolean(AlexaMetadataBundleKey.CARD_WAS_INTERACTED_WITH.name())) {
            BIo(zZm2);
        } else {
            zZm(zZm2);
        }
    }

    public void BIo(String str) {
        MetricsCounter zZm2 = this.BIo.zZm(str, zZm(), BIo(), null);
        zZm2.incrementCounter();
        this.BIo.zZm(zZm2);
    }

    public void zZm(Bundle bundle) {
        zZm("card_create_controller_failed", bundle.getString(AlexaMetadataBundleKey.CARD_NAME.name()));
    }

    public final void zZm(String str, String str2) {
        this.BIo.BIo(str, str2, zZm(), BIo(), null);
    }

    public String BIo() {
        Stage stage = this.zQM.mo358get().getStage();
        if (stage == null) {
            stage = Stage.PROD;
        }
        StringBuilder zZm2 = C0179Pya.zZm("AlexaMobileAndroid_");
        zZm2.append(stage.toString());
        return zZm2.toString();
    }

    public void zZm(String str) {
        this.BIo.zZm(this.BIo.zZm(str, zZm(), BIo(), null));
    }

    public void zZm(String str, String str2, long j, @Nullable Map<String, Object> map) {
        this.BIo.zZm((MetricsTimer) new DefaultMetricsTimer(str, str2, BIo(), map, j, false));
    }

    public void zZm(String str, long j) {
        zZm(str, zZm(), j, null);
    }
}
