package com.amazon.alexa.voice.ui.player;

import androidx.annotation.DrawableRes;
import com.amazon.alexa.voice.ui.R;
/* loaded from: classes11.dex */
public enum AudioProviderIcon {
    AMAZON_MUSIC,
    DHITS,
    UTA_PASS,
    SAAVN,
    AUDIBLE,
    KINDLE,
    DEEZER;

    /* renamed from: com.amazon.alexa.voice.ui.player.AudioProviderIcon$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$voice$ui$player$AudioProviderIcon = new int[AudioProviderIcon.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$player$AudioProviderIcon[AudioProviderIcon.AMAZON_MUSIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$player$AudioProviderIcon[AudioProviderIcon.DHITS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$player$AudioProviderIcon[AudioProviderIcon.UTA_PASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$player$AudioProviderIcon[AudioProviderIcon.SAAVN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$player$AudioProviderIcon[AudioProviderIcon.AUDIBLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$player$AudioProviderIcon[AudioProviderIcon.KINDLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$player$AudioProviderIcon[AudioProviderIcon.DEEZER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static AudioProviderIcon fromString(String str) {
        if (str == null) {
            return null;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1598982884:
                if (str.equals("amazon-music")) {
                    c = 0;
                    break;
                }
                break;
            case -1309495999:
                if (str.equals("ebotts")) {
                    c = 5;
                    break;
                }
                break;
            case -791483102:
                if (str.equals("apricot")) {
                    c = 4;
                    break;
                }
                break;
            case 65017220:
                if (str.equals("DHITS")) {
                    c = 1;
                    break;
                }
                break;
            case 78653867:
                if (str.equals("SAAVN")) {
                    c = 3;
                    break;
                }
                break;
            case 1301965998:
                if (str.equals("UTA_PASS")) {
                    c = 2;
                    break;
                }
                break;
            case 2012649507:
                if (str.equals("DEEZER")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return AMAZON_MUSIC;
            case 1:
                return DHITS;
            case 2:
                return UTA_PASS;
            case 3:
                return SAAVN;
            case 4:
                return AUDIBLE;
            case 5:
                return KINDLE;
            case 6:
                return DEEZER;
            default:
                return null;
        }
    }

    @DrawableRes
    public int getDrawableResourceId() {
        switch (ordinal()) {
            case 0:
                return R.drawable.ic_logo_amazonmusic;
            case 1:
                return R.drawable.ic_logo_dhits;
            case 2:
                return R.drawable.ic_logo_utapass;
            case 3:
                return R.drawable.ic_logo_saavn;
            case 4:
                return R.drawable.ic_logo_audible;
            case 5:
                return R.drawable.ic_logo_kindle;
            case 6:
                return R.drawable.ic_logo_deezer;
            default:
                throw new IllegalStateException("Unknown drawable resource id: " + this);
        }
    }
}
