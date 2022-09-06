package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
/* loaded from: classes11.dex */
public class Offer {
    private BundlePolicy bundlePolicy;
    private List<MediaStream> mediaStreams;

    /* loaded from: classes11.dex */
    public enum BundlePolicy {
        MAXBUNDLE,
        MAXCOMPAT,
        BALANCED
    }

    /* loaded from: classes11.dex */
    public static class OfferBuilder {
        private BundlePolicy bundlePolicy;
        private List<MediaStream> mediaStreams;

        OfferBuilder() {
        }

        public Offer build() {
            return new Offer(this.mediaStreams, this.bundlePolicy);
        }

        public OfferBuilder bundlePolicy(BundlePolicy bundlePolicy) {
            this.bundlePolicy = bundlePolicy;
            return this;
        }

        public OfferBuilder mediaStreams(List<MediaStream> list) {
            this.mediaStreams = list;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Offer.OfferBuilder(mediaStreams=");
            outline107.append(this.mediaStreams);
            outline107.append(", bundlePolicy=");
            outline107.append(this.bundlePolicy);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public Offer() {
    }

    public static OfferBuilder builder() {
        return new OfferBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof Offer;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Offer)) {
            return false;
        }
        Offer offer = (Offer) obj;
        if (!offer.canEqual(this)) {
            return false;
        }
        List<MediaStream> mediaStreams = getMediaStreams();
        List<MediaStream> mediaStreams2 = offer.getMediaStreams();
        if (mediaStreams != null ? !mediaStreams.equals(mediaStreams2) : mediaStreams2 != null) {
            return false;
        }
        BundlePolicy bundlePolicy = getBundlePolicy();
        BundlePolicy bundlePolicy2 = offer.getBundlePolicy();
        return bundlePolicy != null ? bundlePolicy.equals(bundlePolicy2) : bundlePolicy2 == null;
    }

    public BundlePolicy getBundlePolicy() {
        return this.bundlePolicy;
    }

    public List<MediaStream> getMediaStreams() {
        return this.mediaStreams;
    }

    public int hashCode() {
        List<MediaStream> mediaStreams = getMediaStreams();
        int i = 43;
        int hashCode = mediaStreams == null ? 43 : mediaStreams.hashCode();
        BundlePolicy bundlePolicy = getBundlePolicy();
        int i2 = (hashCode + 59) * 59;
        if (bundlePolicy != null) {
            i = bundlePolicy.hashCode();
        }
        return i2 + i;
    }

    public void setBundlePolicy(BundlePolicy bundlePolicy) {
        this.bundlePolicy = bundlePolicy;
    }

    public void setMediaStreams(List<MediaStream> list) {
        this.mediaStreams = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Offer(mediaStreams=");
        outline107.append(getMediaStreams());
        outline107.append(", bundlePolicy=");
        outline107.append(getBundlePolicy());
        outline107.append(")");
        return outline107.toString();
    }

    private Offer(List<MediaStream> list, BundlePolicy bundlePolicy) {
        this.mediaStreams = list;
        this.bundlePolicy = bundlePolicy;
    }
}
