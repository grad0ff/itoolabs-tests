package com.itoolabs.index_page_tests;

import lombok.Getter;

public enum HeadFeature {
    CALLCENTER("ОБЛАЧНЫЙ CALL-ЦЕНТР"),
    OFFICE("ВИРТУАЛЬНЫЙ ОФИС"),
    TECHNIC("ДОПОЛНИТЕЛЬНЫЕ ВОЗМОЖНОСТИ"),
    SIP("ОБЛАЧНАЯ АТС"),
    NUMBER("ВИРТУАЛЬНЫЕ НОМЕРА");

    @Getter
    private String text;

    HeadFeature(String s) {
    }

    public String getName() {
        return this.text;
    }
}
