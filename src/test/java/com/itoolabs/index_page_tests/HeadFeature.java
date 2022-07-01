package com.itoolabs.index_page_tests;

public enum HeadFeature {
    CALLCENTER("ОБЛАЧНЫЙ CALL-ЦЕНТР"),
    OFFICE("ВИРТУАЛЬНЫЙ ОФИС"),
    TECHNIC("ДОПОЛНИТЕЛЬНЫЕ ВОЗМОЖНОСТИ"),
    SIP("ОБЛАЧНАЯ АТС"),
    NUMBER("ВИРТУАЛЬНЫЕ НОМЕРА");

    private final String popupText;

    HeadFeature(String popupText) {
        this.popupText = popupText;
    }

    @Override
    public String toString() {
        return popupText;
    }
}
