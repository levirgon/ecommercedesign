package com.levirgon.e_commercedesign.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by noushad on 12/3/17.
 */

public class MiniCategory extends ExpandableGroup {

    public MiniCategory(String title, List items) {
        super(title, items);
    }
}
