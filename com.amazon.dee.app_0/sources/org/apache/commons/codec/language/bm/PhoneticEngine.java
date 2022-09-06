package org.apache.commons.codec.language.bm;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommand;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes4.dex */
public class PhoneticEngine {
    private static final int DEFAULT_MAX_PHONEMES = 20;
    private static final Map<NameType, Set<String>> NAME_PREFIXES = new EnumMap(NameType.class);
    private final boolean concat;
    private final Lang lang;
    private final int maxPhonemes;
    private final NameType nameType;
    private final RuleType ruleType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.commons.codec.language.bm.PhoneticEngine$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$codec$language$bm$NameType = new int[NameType.values().length];

        static {
            try {
                $SwitchMap$org$apache$commons$codec$language$bm$NameType[NameType.SEPHARDIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$codec$language$bm$NameType[NameType.ASHKENAZI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$codec$language$bm$NameType[NameType.GENERIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class PhonemeBuilder {
        private final Set<Rule.Phoneme> phonemes;

        /* synthetic */ PhonemeBuilder(Set set, AnonymousClass1 anonymousClass1) {
            this(set);
        }

        public static PhonemeBuilder empty(Languages.LanguageSet languageSet) {
            return new PhonemeBuilder(new Rule.Phoneme("", languageSet));
        }

        public void append(CharSequence charSequence) {
            for (Rule.Phoneme phoneme : this.phonemes) {
                phoneme.append(charSequence);
            }
        }

        public void apply(Rule.PhonemeExpr phonemeExpr, int i) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(i);
            loop0: for (Rule.Phoneme phoneme : this.phonemes) {
                for (Rule.Phoneme phoneme2 : phonemeExpr.mo12628getPhonemes()) {
                    Languages.LanguageSet restrictTo = phoneme.getLanguages().restrictTo(phoneme2.getLanguages());
                    if (!restrictTo.isEmpty()) {
                        Rule.Phoneme phoneme3 = new Rule.Phoneme(phoneme, phoneme2, restrictTo);
                        if (linkedHashSet.size() < i) {
                            linkedHashSet.add(phoneme3);
                            if (linkedHashSet.size() >= i) {
                                break loop0;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            this.phonemes.clear();
            this.phonemes.addAll(linkedHashSet);
        }

        public Set<Rule.Phoneme> getPhonemes() {
            return this.phonemes;
        }

        public String makeString() {
            StringBuilder sb = new StringBuilder();
            for (Rule.Phoneme phoneme : this.phonemes) {
                if (sb.length() > 0) {
                    sb.append(AccessoryMetricsConstants.DELIMITER);
                }
                sb.append(phoneme.getPhonemeText());
            }
            return sb.toString();
        }

        private PhonemeBuilder(Rule.Phoneme phoneme) {
            this.phonemes = new LinkedHashSet();
            this.phonemes.add(phoneme);
        }

        private PhonemeBuilder(Set<Rule.Phoneme> set) {
            this.phonemes = set;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class RulesApplication {
        private final Map<String, List<Rule>> finalRules;
        private boolean found;
        private int i;
        private final CharSequence input;
        private final int maxPhonemes;
        private PhonemeBuilder phonemeBuilder;

        public RulesApplication(Map<String, List<Rule>> map, CharSequence charSequence, PhonemeBuilder phonemeBuilder, int i, int i2) {
            if (map != null) {
                this.finalRules = map;
                this.phonemeBuilder = phonemeBuilder;
                this.input = charSequence;
                this.i = i;
                this.maxPhonemes = i2;
                return;
            }
            throw new NullPointerException("The finalRules argument must not be null");
        }

        public int getI() {
            return this.i;
        }

        public PhonemeBuilder getPhonemeBuilder() {
            return this.phonemeBuilder;
        }

        public RulesApplication invoke() {
            int i;
            this.found = false;
            Map<String, List<Rule>> map = this.finalRules;
            CharSequence charSequence = this.input;
            int i2 = this.i;
            List<Rule> list = map.get(charSequence.subSequence(i2, i2 + 1));
            int i3 = 1;
            if (list != null) {
                Iterator<Rule> it2 = list.iterator();
                i = 1;
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Rule next = it2.next();
                    int length = next.getPattern().length();
                    if (next.patternAndContextMatches(this.input, this.i)) {
                        this.phonemeBuilder.apply(next.getPhoneme(), this.maxPhonemes);
                        this.found = true;
                        i = length;
                        break;
                    }
                    i = length;
                }
            } else {
                i = 1;
            }
            if (this.found) {
                i3 = i;
            }
            this.i += i3;
            return this;
        }

        public boolean isFound() {
            return this.found;
        }
    }

    static {
        NAME_PREFIXES.put(NameType.ASHKENAZI, Collections.unmodifiableSet(new HashSet(Arrays.asList("bar", "ben", ProvisioningCommand.DATA_KEY, "de", "van", "von"))));
        NAME_PREFIXES.put(NameType.SEPHARDIC, Collections.unmodifiableSet(new HashSet(Arrays.asList("al", "el", ProvisioningCommand.DATA_KEY, "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
        NAME_PREFIXES.put(NameType.GENERIC, Collections.unmodifiableSet(new HashSet(Arrays.asList(ProvisioningCommand.DATA_KEY, "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
    }

    public PhoneticEngine(NameType nameType, RuleType ruleType, boolean z) {
        this(nameType, ruleType, z, 20);
    }

    private PhonemeBuilder applyFinalRules(PhonemeBuilder phonemeBuilder, Map<String, List<Rule>> map) {
        if (map != null) {
            if (map.isEmpty()) {
                return phonemeBuilder;
            }
            TreeMap treeMap = new TreeMap(Rule.Phoneme.COMPARATOR);
            for (Rule.Phoneme phoneme : phonemeBuilder.getPhonemes()) {
                PhonemeBuilder empty = PhonemeBuilder.empty(phoneme.getLanguages());
                String charSequence = phoneme.getPhonemeText().toString();
                PhonemeBuilder phonemeBuilder2 = empty;
                int i = 0;
                while (i < charSequence.length()) {
                    RulesApplication invoke = new RulesApplication(map, charSequence, phonemeBuilder2, i, this.maxPhonemes).invoke();
                    boolean isFound = invoke.isFound();
                    phonemeBuilder2 = invoke.getPhonemeBuilder();
                    if (!isFound) {
                        phonemeBuilder2.append(charSequence.subSequence(i, i + 1));
                    }
                    i = invoke.getI();
                }
                for (Rule.Phoneme phoneme2 : phonemeBuilder2.getPhonemes()) {
                    if (treeMap.containsKey(phoneme2)) {
                        Rule.Phoneme mergeWithLanguage = ((Rule.Phoneme) treeMap.remove(phoneme2)).mergeWithLanguage(phoneme2.getLanguages());
                        treeMap.put(mergeWithLanguage, mergeWithLanguage);
                    } else {
                        treeMap.put(phoneme2, phoneme2);
                    }
                }
            }
            return new PhonemeBuilder(treeMap.keySet(), null);
        }
        throw new NullPointerException("finalRules can not be null");
    }

    private static String join(Iterable<String> iterable, String str) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it2 = iterable.iterator();
        if (it2.hasNext()) {
            sb.append(it2.next());
        }
        while (it2.hasNext()) {
            sb.append(str);
            sb.append(it2.next());
        }
        return sb.toString();
    }

    public String encode(String str) {
        return encode(str, this.lang.guessLanguages(str));
    }

    public Lang getLang() {
        return this.lang;
    }

    public int getMaxPhonemes() {
        return this.maxPhonemes;
    }

    public NameType getNameType() {
        return this.nameType;
    }

    public RuleType getRuleType() {
        return this.ruleType;
    }

    public boolean isConcat() {
        return this.concat;
    }

    public PhoneticEngine(NameType nameType, RuleType ruleType, boolean z, int i) {
        if (ruleType != RuleType.RULES) {
            this.nameType = nameType;
            this.ruleType = ruleType;
            this.concat = z;
            this.lang = Lang.instance(nameType);
            this.maxPhonemes = i;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ruleType must not be ");
        outline107.append(RuleType.RULES);
        throw new IllegalArgumentException(outline107.toString());
    }

    public String encode(String str, Languages.LanguageSet languageSet) {
        String str2;
        Map<String, List<Rule>> instanceMap = Rule.getInstanceMap(this.nameType, RuleType.RULES, languageSet);
        Map<String, List<Rule>> instanceMap2 = Rule.getInstanceMap(this.nameType, this.ruleType, "common");
        Map<String, List<Rule>> instanceMap3 = Rule.getInstanceMap(this.nameType, this.ruleType, languageSet);
        String trim = str.toLowerCase(Locale.ENGLISH).replace('-', Chars.SPACE).trim();
        if (this.nameType == NameType.GENERIC) {
            if (trim.length() >= 2 && trim.substring(0, 2).equals("d'")) {
                String substring = trim.substring(2);
                String outline72 = GeneratedOutlineSupport1.outline72("d", substring);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(");
                outline107.append(encode(substring));
                outline107.append(")-(");
                outline107.append(encode(outline72));
                outline107.append(")");
                return outline107.toString();
            }
            for (String str3 : NAME_PREFIXES.get(this.nameType)) {
                if (trim.startsWith(str3 + " ")) {
                    String substring2 = trim.substring(str3.length() + 1);
                    String outline722 = GeneratedOutlineSupport1.outline72(str3, substring2);
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("(");
                    outline1072.append(encode(substring2));
                    outline1072.append(")-(");
                    outline1072.append(encode(outline722));
                    outline1072.append(")");
                    return outline1072.toString();
                }
            }
        }
        List<String> asList = Arrays.asList(trim.split("\\s+"));
        ArrayList<String> arrayList = new ArrayList();
        int ordinal = this.nameType.ordinal();
        if (ordinal == 0) {
            arrayList.addAll(asList);
            arrayList.removeAll(NAME_PREFIXES.get(this.nameType));
        } else if (ordinal == 1) {
            arrayList.addAll(asList);
        } else if (ordinal == 2) {
            for (String str4 : asList) {
                String[] split = str4.split("'");
                arrayList.add(split[split.length - 1]);
            }
            arrayList.removeAll(NAME_PREFIXES.get(this.nameType));
        } else {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Unreachable case: ");
            outline1073.append(this.nameType);
            throw new IllegalStateException(outline1073.toString());
        }
        if (this.concat) {
            str2 = join(arrayList, " ");
        } else if (arrayList.size() == 1) {
            str2 = (String) asList.iterator().next();
        } else {
            StringBuilder sb = new StringBuilder();
            for (String str5 : arrayList) {
                sb.append(ProcessIdUtil.DEFAULT_PROCESSID);
                sb.append(encode(str5));
            }
            return sb.substring(1);
        }
        int i = 0;
        PhonemeBuilder empty = PhonemeBuilder.empty(languageSet);
        while (i < str2.length()) {
            RulesApplication invoke = new RulesApplication(instanceMap, str2, empty, i, this.maxPhonemes).invoke();
            i = invoke.getI();
            empty = invoke.getPhonemeBuilder();
        }
        return applyFinalRules(applyFinalRules(empty, instanceMap2), instanceMap3).makeString();
    }
}
