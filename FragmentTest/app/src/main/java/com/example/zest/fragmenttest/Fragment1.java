package com.example.zest.fragmenttest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.Navigation;

public class Fragment1 extends Fragment {

    Button b_Frag1ToFrag2, b_Frag1ToFrag3, b_Frag1ToFrag4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layout = R.layout.fragment1_layout;
        View rootView = inflater.inflate(layout,container,false);

        b_Frag1ToFrag2 = rootView.findViewById(R.id.b_Frag1ToFrag2);
        b_Frag1ToFrag3 = rootView.findViewById(R.id.b_Frag1ToFrag3);
        b_Frag1ToFrag4 = rootView.findViewById(R.id.b_Frag1ToFrag4);

        b_Frag1ToFrag2.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment2, null));
        b_Frag1ToFrag3.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment3, null));
        b_Frag1ToFrag4.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment4, null));

        return rootView;
    }
}
