package amazon.speech.config;
/* loaded from: classes.dex */
public abstract class ProactiveReportingPolicy {
    public static final ProactiveReportingPolicy DEFAULT = new ProactiveReportingPolicy() { // from class: amazon.speech.config.ProactiveReportingPolicy.1
        @Override // amazon.speech.config.ProactiveReportingPolicy
        public boolean allowProactiveStateReporting(String str) {
            return false;
        }
    };
    public static final ProactiveReportingPolicy ENABLED = new ProactiveReportingPolicy() { // from class: amazon.speech.config.ProactiveReportingPolicy.2
        @Override // amazon.speech.config.ProactiveReportingPolicy
        public boolean allowProactiveStateReporting(String str) {
            return true;
        }
    };

    public abstract boolean allowProactiveStateReporting(String str);
}
