package com.example.myapplication.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Fragment_Main  extends FragmentStatePagerAdapter {

    private Fragment fragment;

    public Fragment_Main(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                fragment = new Fragment_Agendamento();
                return fragment;
            case 1:
                fragment = new Fragment_Status_Agendamento();
                break;
        }
        return fragment;

    }

    @Override
    public int getCount() {
        return 2;
    }
}
