package com.amazon.deecomms.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.blueprints.BlueprintsEndpointConstants;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public enum Stage {
    PROD("prod"),
    PROD_QA("prod", Constants.APP_ARCUS_CONFIG_ID_QA),
    PREPROD("prod"),
    GAMMA("gamma"),
    BETA("beta"),
    ALPHA("alpha");
    
    @NonNull
    private final String arcusConfigId;
    @NonNull
    private final String arcusEquivalentStage;

    Stage(@NonNull String str) {
        this.arcusEquivalentStage = str;
        this.arcusConfigId = Constants.APP_ARCUS_CONFIG_ID;
    }

    @NonNull
    public static Stage toStage(@Nullable String str) {
        if (str == null) {
            return PROD;
        }
        String lowerCase = str.toLowerCase();
        char c = 65535;
        switch (lowerCase.hashCode()) {
            case -979814489:
                if (lowerCase.equals("prodqa")) {
                    c = 4;
                    break;
                }
                break;
            case -318354310:
                if (lowerCase.equals(BlueprintsEndpointConstants.PREPROD)) {
                    c = 3;
                    break;
                }
                break;
            case -309494792:
                if (lowerCase.equals("prod_qa")) {
                    c = 5;
                    break;
                }
                break;
            case 3020272:
                if (lowerCase.equals("beta")) {
                    c = 1;
                    break;
                }
                break;
            case 92909918:
                if (lowerCase.equals("alpha")) {
                    c = 0;
                    break;
                }
                break;
            case 98120615:
                if (lowerCase.equals("gamma")) {
                    c = 2;
                    break;
                }
                break;
        }
        if (c == 0) {
            return ALPHA;
        }
        if (c == 1) {
            return BETA;
        }
        if (c == 2) {
            return GAMMA;
        }
        if (c == 3) {
            return PREPROD;
        }
        if (c == 4) {
            return PROD_QA;
        }
        if (c != 5) {
            return PROD;
        }
        return PROD_QA;
    }

    @NonNull
    public String getArcusConfigId() {
        return this.arcusConfigId;
    }

    @NonNull
    public String getArcusEquivalentStage() {
        return this.arcusEquivalentStage;
    }

    Stage(@NonNull String str, @NonNull String str2) {
        this.arcusEquivalentStage = str;
        this.arcusConfigId = str2;
    }
}
