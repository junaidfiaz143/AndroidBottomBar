package com.example.bottomappbarbehaviour;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by jd on 04-May-19.
 */

public class BottomNavigationDrawerFrag extends BottomSheetDialogFragment {

    NavigationView navigation_view;

    ImageView close_imageview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_navigation_drawer, container, false);
        navigation_view = v.findViewById(R.id.navigation_view);
        close_imageview = v.findViewById(R.id.close_imageview);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return true;
            }
        });

        disableNavigationViewScrollbars(navigation_view);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                BottomSheetDialog d = new BottomSheetDialog(getActivity());

                FrameLayout bottomSheet = d.findViewById(R.id.design_bottom_sheet);
                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

                bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View view, int i) {
                        if (i != 0) {
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            dismiss();
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View view, float v) {
                        if (v > 0.5) {
                            close_imageview.setVisibility(View.VISIBLE);
                        } else {
                            close_imageview.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        return dialog;
    }

    private void disableNavigationViewScrollbars(NavigationView navigation_view) {
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigation_view.getChildAt(0);
        navigationMenuView.setVerticalScrollBarEnabled(false);
    }
}
