package com.horion.visum;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.horion.visum.Data.NavigationRuleEnum;


public class NavigationRule {

    public NavigationRuleEnum rule;
    public Fragment fragment;
    public String tag;

    public NavigationRule(NavigationRuleEnum rule, Fragment fragment, @Nullable String tag) {
        this.rule = rule;
        this.fragment = fragment;
        this.tag = tag;
    }
}
