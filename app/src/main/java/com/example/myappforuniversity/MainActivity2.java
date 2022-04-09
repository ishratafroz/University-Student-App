package com.example.myappforuniversity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivity2 extends AppCompatActivity {
 private AppBarConfiguration mAppBarConfiguration;
 FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth=FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open, R.string.nav_close );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mAppBarConfiguration=new AppBarConfiguration.Builder(R.id.nav_home,R.id.nav_library,R.id.nav_courses,R.id.nav_event,
        R.id.nav_exam,R.id.nav_selflearn,R.id.nav_classlink,R.id.nav_performance,R.id.nav_profile,R.id.nav_aboutkuet,
        R.id.nav_vcmessage).setDrawerLayout(drawer).build();
        NavController navController=Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);
    }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        @Override
          public void onBackPressed()
        { DrawerLayout drawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        int count=getSupportFragmentManager().getBackStackEntryCount();
        if(count>0) {getSupportFragmentManager().popBackStack();}
            if(drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
                else super.onBackPressed();
             }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true; }
        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        { int id=item.getItemId();
            if(id==R.id.action_settings )
            { Intent intent=new Intent(getApplicationContext(),Setting.class);startActivity(intent);
        }
                        if(id==R.id.signoutmenuid)
            { FirebaseAuth.getInstance().signOut(); finish();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);startActivity(intent); }
            return super.onOptionsItemSelected(item);
        }
}