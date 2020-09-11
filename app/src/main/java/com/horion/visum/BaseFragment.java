package com.horion.visum;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    private MainActivity getMainActivity(){
        return (MainActivity) getActivity();
    }

    public void loadFragment(NavigationRule rule){
        getMainActivity().loadFragment(rule);
    }

    public void onBackPressed(){
        getMainActivity().onBackPressed();
    }

}
