package my.fadzlirazali.myfind;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends BaseActivity {


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.ColorPrimaryDark,getTheme()));
        }
        setContentView(R.layout.activity_main);


        /* init toolbar from base class */
        getActionToolbar();

        /* Initializing NavigationView */
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        /* Setting NV item listener to handle item click */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                /* Checking if the item is in checked state or not */
                if (menuItem.isChecked())
                    menuItem.setChecked(false);
                else
                    menuItem.setChecked(true);

                /* Close drawer */
                drawerLayout.closeDrawers();

                Fragment fragment;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                /* Check to see which item is being clicked */
                switch (menuItem.getItemId()){

                    /* Replacing main content */
                    case R.id.walkabouts:
                        fragment = new WalkaboutFragment();
                        transaction.replace(R.id.frame_container, fragment); //to replace the frame container into fragment
                        transaction.commit();
                        return true;

                    case R.id.perosna:
                        fragment = new PersonaFragment();
                        transaction.replace(R.id.frame_container, fragment);
                        transaction.commit();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,   /* curr activity */
                drawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        };

        /* Setting the actionbarToggle to drawer layout */
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        /* calling sync state is needed or else ur icon didnt show up*/
        actionBarDrawerToggle.syncState();

        /* By Default fragment */
        Fragment fragment = new WalkaboutFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

}
