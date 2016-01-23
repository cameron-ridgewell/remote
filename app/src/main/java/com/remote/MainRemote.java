package com.remote;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.remote.utilities.AppData;
import com.remote.utilities.ServerRequest;
import com.remote.utilities.StoredResources;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainRemote.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainRemote#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainRemote extends Fragment {

    private OnFragmentInteractionListener mListener;
    
    private View rootView;

    private ServerRequest svreq = ServerRequest.getInstance();

    private Spinner deviceSelect;
    private String currentDevice = null;

    private BootstrapButton powerAll;
    private BootstrapButton button0;
    private BootstrapButton button1;
    private BootstrapButton button2;
    private BootstrapButton button3;
    private BootstrapButton button4;
    private BootstrapButton button5;
    private BootstrapButton button6;
    private BootstrapButton button7;
    private BootstrapButton button8;
    private BootstrapButton button9;

    private BootstrapButton volumeUp;
    private BootstrapButton volumeDown;

    private BootstrapButton channelUp;
    private BootstrapButton channelDown;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TV_Remote.
     */
    public static MainRemote newInstance() {
        MainRemote fragment = new MainRemote();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MainRemote() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        svreq.loadUser(this.getActivity().getApplicationContext());
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main_remote, container, false);

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.increaseAVVolume();
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void deviceSelectSetup() {
        //deviceSelect = (Spinner) rootView.findViewById(R.id.device_select);

        deviceSelect.setAdapter(new SpinnerImageAdapter(getActivity(), R.layout.image_row,
                getResources().getStringArray(R.array.devices_array)));

        final String[] deviceList = getResources().getStringArray(R.array.devices_array);

        deviceSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {

                if ((!deviceList[position].equals(currentDevice)) && position != 0) {
                    svreq.changeHDMI(deviceList[position]);
                }

                if (position == 0) {
                    currentDevice = null;
                } else {
                    currentDevice = deviceList[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

    }

    private void channelButtonSetup() {

    }

    public class SpinnerImageAdapter extends ArrayAdapter<String>{

        Context context;
        String[] strings;

        public SpinnerImageAdapter(Context context, int textViewResourceId,   String[] objects) {
            super(context, textViewResourceId, objects);
            this.context = context;
            this.strings = objects;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View v;
            if (position == 0) {
                TextView tv = new TextView(getContext());
                tv.setVisibility(View.GONE);
                tv.setHeight(0);
                v = tv;
            } else {
                v = getCustomView(position, convertView, parent);
            }
            return v;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View row = inflater.inflate(R.layout.image_row, parent, false);

            TextView label=(TextView) row.findViewById(R.id.title);
            label.setText(strings[position]);
            if (position == 0) {
                label.setTypeface(label.getTypeface(), Typeface.ITALIC);
            }
            ImageView icon = (ImageView) row.findViewById(R.id.image);
            if (position != 0) {
                icon.setImageResource(StoredResources.getInstance().getdeviceIconDict()
                        .get(strings[position]));
            } else {
                icon.setImageResource(R.drawable.ic_slanted_bars_20);
            }
            return row;
        }
    }
}
