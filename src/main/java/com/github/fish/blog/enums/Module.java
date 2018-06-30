package com.github.fish.blog.enums;

/**
 * 模块编号
 */
public enum Module {

    BASE("BASE", "1");

    private String name;
    private String index;

    private String getName() {
        return name;
    }


    private String getIndex() {
        return index;
    }


    Module(String name, String index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(String index) {
        for (Module pt : Module.values()) {
            if (pt.getIndex().equals(index)) {
                return pt.getName();
            }
        }
        return null;
    }
}