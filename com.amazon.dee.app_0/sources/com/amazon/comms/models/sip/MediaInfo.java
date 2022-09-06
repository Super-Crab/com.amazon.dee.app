package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = MediaInfoBuilder.class)
/* loaded from: classes11.dex */
public final class MediaInfo {
    private final SRTPConfig srtp;
    private final RDNEndpoint stun;
    private final RDNEndpoint turnTCP;
    private final RDNEndpoint turnUDP;

    @JsonPOJOBuilder(withPrefix = "")
    /* loaded from: classes11.dex */
    public static class MediaInfoBuilder {
        private SRTPConfig srtp;
        private RDNEndpoint stun;
        private RDNEndpoint turnTCP;
        private RDNEndpoint turnUDP;

        MediaInfoBuilder() {
        }

        public MediaInfo build() {
            return new MediaInfo(this.turnUDP, this.turnTCP, this.stun, this.srtp);
        }

        public MediaInfoBuilder srtp(SRTPConfig sRTPConfig) {
            this.srtp = sRTPConfig;
            return this;
        }

        public MediaInfoBuilder stun(RDNEndpoint rDNEndpoint) {
            this.stun = rDNEndpoint;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaInfo.MediaInfoBuilder(turnUDP=");
            outline107.append(this.turnUDP);
            outline107.append(", turnTCP=");
            outline107.append(this.turnTCP);
            outline107.append(", stun=");
            outline107.append(this.stun);
            outline107.append(", srtp=");
            outline107.append(this.srtp);
            outline107.append(")");
            return outline107.toString();
        }

        public MediaInfoBuilder turnTCP(RDNEndpoint rDNEndpoint) {
            this.turnTCP = rDNEndpoint;
            return this;
        }

        public MediaInfoBuilder turnUDP(RDNEndpoint rDNEndpoint) {
            this.turnUDP = rDNEndpoint;
            return this;
        }
    }

    MediaInfo(RDNEndpoint rDNEndpoint, RDNEndpoint rDNEndpoint2, RDNEndpoint rDNEndpoint3, SRTPConfig sRTPConfig) {
        this.turnUDP = rDNEndpoint;
        this.turnTCP = rDNEndpoint2;
        this.stun = rDNEndpoint3;
        this.srtp = sRTPConfig;
    }

    public static MediaInfoBuilder builder() {
        return new MediaInfoBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        RDNEndpoint turnUDP = getTurnUDP();
        RDNEndpoint turnUDP2 = mediaInfo.getTurnUDP();
        if (turnUDP != null ? !turnUDP.equals(turnUDP2) : turnUDP2 != null) {
            return false;
        }
        RDNEndpoint turnTCP = getTurnTCP();
        RDNEndpoint turnTCP2 = mediaInfo.getTurnTCP();
        if (turnTCP != null ? !turnTCP.equals(turnTCP2) : turnTCP2 != null) {
            return false;
        }
        RDNEndpoint stun = getStun();
        RDNEndpoint stun2 = mediaInfo.getStun();
        if (stun != null ? !stun.equals(stun2) : stun2 != null) {
            return false;
        }
        SRTPConfig srtp = getSrtp();
        SRTPConfig srtp2 = mediaInfo.getSrtp();
        return srtp != null ? srtp.equals(srtp2) : srtp2 == null;
    }

    public SRTPConfig getSrtp() {
        return this.srtp;
    }

    public RDNEndpoint getStun() {
        return this.stun;
    }

    public RDNEndpoint getTurnTCP() {
        return this.turnTCP;
    }

    public RDNEndpoint getTurnUDP() {
        return this.turnUDP;
    }

    public int hashCode() {
        RDNEndpoint turnUDP = getTurnUDP();
        int i = 43;
        int hashCode = turnUDP == null ? 43 : turnUDP.hashCode();
        RDNEndpoint turnTCP = getTurnTCP();
        int hashCode2 = ((hashCode + 59) * 59) + (turnTCP == null ? 43 : turnTCP.hashCode());
        RDNEndpoint stun = getStun();
        int hashCode3 = (hashCode2 * 59) + (stun == null ? 43 : stun.hashCode());
        SRTPConfig srtp = getSrtp();
        int i2 = hashCode3 * 59;
        if (srtp != null) {
            i = srtp.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaInfo(turnUDP=");
        outline107.append(getTurnUDP());
        outline107.append(", turnTCP=");
        outline107.append(getTurnTCP());
        outline107.append(", stun=");
        outline107.append(getStun());
        outline107.append(", srtp=");
        outline107.append(getSrtp());
        outline107.append(")");
        return outline107.toString();
    }
}
