package com.itis.androidlab.parsing.models;

public enum VkPostType {
    POST("post"),
    COPY("copy"),
    REPLY("reply"),
    POSTPONE("postpone"),
    SUGGEST("suggest");

    private String mValue;

    VkPostType(String value) {
        mValue = value;
    }

    public static VkPostType getEnum(String value) {
        for (VkPostType v : values()) {
            if (v.toString().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return mValue;
    }
}
