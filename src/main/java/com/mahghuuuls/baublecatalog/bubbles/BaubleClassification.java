package com.mahghuuuls.baublecatalog.bubbles;

public final class BaubleClassification {

    private final String baubleTypeId;
    private final String baubleTypeTranslationKey;

    public BaubleClassification(String baubleTypeId, String baubleTypeTranslationKey) {
        this.baubleTypeId = baubleTypeId;
        this.baubleTypeTranslationKey = baubleTypeTranslationKey;
    }

    public String getBaubleTypeId() {
        return baubleTypeId;
    }

    public String getBaubleTypeTranslationKey() {
        return baubleTypeTranslationKey;
    }
}
