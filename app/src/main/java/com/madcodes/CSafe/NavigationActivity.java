package com.madcodes.CSafe;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.madcodes.CSafe.Utils.CSafePreferences;
import com.madcodes.CSafe.Utils.CommonClass;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NavigationActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    TextView tv_name, tv_mobile;
    NavigationView navigationView;
    DrawerLayout drawer;
    NavController navController;
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        initViews();
        clickListeners();


    }

    private void clickListeners() {

    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        /* */

        navigationView = findViewById(R.id.nav_view);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_help, R.id.nav_about, R.id.nav_share, R.id.nav_rate, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        System.out.println("User Relations : " + gson.toJson(CSafePreferences.getUserRelations()));

/*        tv_name = (TextView) navigationView.findViewById(R.id.tv_name);
        tv_mobile = (TextView) navigationView.findViewById(R.id.tv_mobile);
        tv_name.setText(KavalanPreferences.getUserName());
        tv_mobile.setText(KavalanPreferences.getMsisdn());*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
