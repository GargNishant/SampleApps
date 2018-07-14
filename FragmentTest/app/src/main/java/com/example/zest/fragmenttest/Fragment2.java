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

public class Fragment2 extends Fragment {

    Button b_Frag2ToFrag1, b_Frag2ToFrag3, b_Frag2ToFrag4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layout = R.layout.fragment2_layout;
        View rootView = inflater.inflate(layout,container,false);

        b_Frag2ToFrag1 = rootView.findViewById(R.id.b_Frag2ToFrag1);
        b_Frag2ToFrag3 = rootView.findViewById(R.id.b_Frag2ToFrag3);
        b_Frag2ToFrag4 = rootView.findViewById(R.id.b_Frag2ToFrag4);

        b_Frag2ToFrag1.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment1, null));
        b_Frag2ToFrag3.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment3, null));
        b_Frag2ToFrag4.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment4, null));

        return rootView;
    }
}
