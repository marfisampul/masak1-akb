package com.example.masakapah.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.masakapah.Fragment.Setting.AboutFragment;
import com.example.masakapah.Fragment.Setting.ContactFragment;
import com.example.masakapah.R;


public class FragmentSetting extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        Button btn = (Button) view.findViewById(R.id.btn_contact);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.page_profile, new ContactFragment());
                fr.commit();
            }
        });

        Button conbtn = (Button) view.findViewById(R.id.btn_abaout);
        conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.page_profile, new AboutFragment());
                fr.commit();
            }
        });

        return view;
    }
}