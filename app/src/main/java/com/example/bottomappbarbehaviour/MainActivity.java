package com.example.bottomappbarbehaviour;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.example.bottomappbarbehaviour.R.id;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton.OnVisibilityChangedListener;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import kotlin.TypeCastException;

public final class MainActivity extends AppCompatActivity {
    private int currentFabAlignmentMode;

    private BottomAppBar bottomAppBar;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.setSupportActionBar((BottomAppBar) findViewById(id.bottom_app_bar));

        final OnVisibilityChangedListener addVisibilityChanged = (new OnVisibilityChangedListener() {
            public void onShown(@Nullable FloatingActionButton fab) {
                super.onShown(fab);
            }

            public void onHidden(@Nullable FloatingActionButton fab) {
                super.onHidden(fab);
                MainActivity context = MainActivity.this;
                bottomAppBar = MainActivity.this.findViewById(id.bottom_app_bar);

                context.toggleFabAlignment(bottomAppBar);

                ((BottomAppBar) context.findViewById(id.bottom_app_bar)).replaceMenu(context.currentFabAlignmentMode == 0 ? R.menu.bottomappbar_menu_secondary : R.menu.bottomappbar_menu_primary);
                if (fab != null) {
                    fab.setImageDrawable(context.currentFabAlignmentMode == 0 ? context.getDrawable(R.drawable.baseline_reply_white_24) : context.getDrawable(R.drawable.baseline_add_white_24));
                }

                if (fab != null) {
                    fab.show();
                }

            }
        });

        ((Button) findViewById(id.toggle_fab_alignment_button)).setOnClickListener((new OnClickListener() {
            public final void onClick(View it) {
                ((FloatingActionButton) MainActivity.this.findViewById(id.fab)).hide(addVisibilityChanged);
                MainActivity.this.invalidateOptionsMenu();
                BottomAppBar bottomAppBar = MainActivity.this.findViewById(id.bottom_app_bar);

                BottomAppBar bottomAppBar1 = MainActivity.this.findViewById(id.bottom_app_bar);

                bottomAppBar.setNavigationIcon(bottomAppBar1.getNavigationIcon() != null ? null : MainActivity.this.getDrawable(R.drawable.baseline_menu_white_24));
                TextView txtScreenLabel = MainActivity.this.findViewById(id.screen_label);

                if (txtScreenLabel.getText().equals("Primary Screen")) {
                    txtScreenLabel.setText("Secondary Screen");
                } else {
                    txtScreenLabel.setText("Primary Screen");
                }

            }
        }));

        ((FloatingActionButton) findViewById(id.fab)).setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {
                MainActivity.this.displayMaterialSnackBar();
            }
        });
    }

    private final void toggleFabAlignment(@NotNull BottomAppBar $receiver) {
        this.currentFabAlignmentMode = $receiver.getFabAlignmentMode();
        $receiver.setFabAlignmentMode(this.currentFabAlignmentMode ^ 1);
    }

    private final void displayMaterialSnackBar() {
        int marginSide = 0;
        int marginBottom = 550;

        Snackbar snackbar = Snackbar.make(findViewById(id.coordinatorLayout2), "FAB Clicked", 0).setAction("UNDO", null);
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorSnackbarButton));
        View snackbarView = snackbar.getView();

        LayoutParams layoutParams = snackbarView.getLayoutParams();

        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
        } else {
            androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams params = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) layoutParams;
            params.setMargins(params.leftMargin + marginSide, params.topMargin, params.rightMargin + marginSide, params.bottomMargin + marginBottom);
            snackbarView.setLayoutParams((LayoutParams) params);
            snackbar.show();
        }
    }

    public boolean onCreateOptionsMenu(@NotNull Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.bottomappbar_menu_primary, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        if (item == null) {
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                BottomNavigationDrawerFrag bottomNavDrawerFragment = new BottomNavigationDrawerFrag();
                bottomNavDrawerFragment.show(this.getSupportFragmentManager(), bottomNavDrawerFragment.getTag());
            default:
                return true;
        }
    }

}
