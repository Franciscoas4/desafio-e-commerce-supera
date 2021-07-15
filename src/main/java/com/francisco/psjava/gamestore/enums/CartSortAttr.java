package com.francisco.psjava.gamestore.enums;

public enum CartSortAttr {

    NAME("NAME"),
    PRICE("PRICE"),
    SCORE("SCORE");

    private String attrSort;

    CartSortAttr(String attrSort) {
        this.attrSort = attrSort;
    }

    public static CartSortAttr stringValue(String attrSort) {
        for (CartSortAttr item : CartSortAttr.values()) {
            if (item.getAttrSort().equals(attrSort)) {
                return item;
            }
        }
        return null;
    }

    public String getAttrSort() {
        return attrSort;
    }
}
