package com.amazon.alexa.redesign.view.templates;
/* loaded from: classes10.dex */
public enum TemplateType {
    ReactCard(1),
    VoxIngress(2),
    MiniTemplate(3),
    HeroTemplate(4),
    ListTemplate(5),
    CustomTemplate(6),
    EmptyState(7),
    SkillsSingle(8),
    PollingMiniTemplate(9),
    CarouselChipTemplate(10),
    CarouselGridTemplate(11),
    UNKNOWN(-1);
    
    private final int templateType;

    TemplateType(int i) {
        this.templateType = i;
    }

    public static TemplateType from(int i) {
        TemplateType[] values;
        for (TemplateType templateType : values()) {
            if (templateType.templateType == i) {
                return templateType;
            }
        }
        return UNKNOWN;
    }

    public int getTemplateTypeIntValue() {
        return this.templateType;
    }
}
