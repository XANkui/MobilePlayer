package com.example.xan.mobileplayer.myfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xan.mobileplayer.base.BasePager;

@SuppressLint("ValidFragment")
public class RepalaceFragment extends Fragment {

        private BasePager basePager;


        public RepalaceFragment(BasePager basePager){

            this.basePager = basePager;

        }


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if (basePager != null) {

                return basePager.rootView;

            }
            return null;
        }



}
