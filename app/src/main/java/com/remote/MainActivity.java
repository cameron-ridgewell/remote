package com.remote;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.inputmethod.InputMethodManager;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.remote.utilities.ServerRequest;


public class MainActivity extends ActionBarActivity implements MainRemote.OnFragmentInteractionListener,
        NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private FloatingActionMenu power_menu;
    private FloatingActionButton TVPower;
    private FloatingActionButton AVPower;
    private FloatingActionButton CMPower;

    private ServerRequest svreq = ServerRequest.getInstance();

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        //Create Runnable for launching the new activity from create event button
        Runnable create_launcher = new Runnable() {
            @Override
            public void run() {
                //IntentProtocol.launchCreateEvent(MainActivity.this);
            }};

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        powerMenuSetup();
        setupBackground();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, ActiveFragment.newInstance(position + 1))
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ActiveFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber) {
            Fragment activeFragment;
            switch (sectionNumber) {
                case 1:
                    activeFragment = new MainRemote();
                    break;

                default:
                    activeFragment = new Fragment();
            }
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            activeFragment.setArguments(args);
            return activeFragment;
        }

        public ActiveFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    /**
     * Sets up the FAB power menu which allows powering off individual devices
     */
    private void powerMenuSetup() {
        power_menu = (FloatingActionMenu) findViewById(R.id.power_menu);
        TVPower = (FloatingActionButton) findViewById(R.id.TVPower);
        AVPower = (FloatingActionButton) findViewById(R.id.AVPower);
        CMPower = (FloatingActionButton) findViewById(R.id.CMPower);

        power_menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    power_menu.setMenuButtonColorNormal(getResources()
                            .getColor(R.color.success_color_light));
                } else {
                    power_menu.setMenuButtonColorNormal(getResources()
                            .getColor(R.color.success_color));
                }
            }
        });

        TVPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.toggleTVPower();
                power_menu.close(true);
            }
        });
        AVPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.toggleAVPower();
                power_menu.close(true);
            }
        });
        CMPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.toggleCMPower();
                power_menu.close(true);
            }
        });
    }

    /**
     * This function allows any clicks on nonbutton entities to cause the power_menu to close
     */
    private void setupBackground() {
        View back = findViewById(R.id.container);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (power_menu.isOpened()) {
                    power_menu.close(true);

                }
            }
        });
    }
}
