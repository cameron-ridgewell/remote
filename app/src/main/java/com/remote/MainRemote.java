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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
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
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main_remote, container, false);

        powerButtonSetup();
        numberPadSetup();
        deviceSelectSetup();

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

    public void powerButtonSetup() {
        powerAll = (BootstrapButton) rootView.findViewById(R.id.power_button);
        powerAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.toggleCMPower();
                svreq.toggleAVPower();
                svreq.toggleTVPower();
            }
        });
    }

    public void numberPadSetup() {
        button0 = (BootstrapButton) rootView.findViewById(R.id.button_0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(0);
            }
        });
        button1 = (BootstrapButton) rootView.findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(1);
            }
        });
        button2 = (BootstrapButton) rootView.findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(2);
            }
        });
        button3 = (BootstrapButton) rootView.findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(3);
            }
        });
        button4 = (BootstrapButton) rootView.findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(4);
            }
        });
        button5 = (BootstrapButton) rootView.findViewById(R.id.button_5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(5);
            }
        });
        button6 = (BootstrapButton) rootView.findViewById(R.id.button_6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(6);
            }
        });
        button7 = (BootstrapButton) rootView.findViewById(R.id.button_7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(7);
            }
        });
        button8 = (BootstrapButton) rootView.findViewById(R.id.button_8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(8);
            }
        });
        button9 = (BootstrapButton) rootView.findViewById(R.id.button_9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svreq.inputCMNumber(9);
            }
        });
    }

    public void deviceSelectSetup() {
        deviceSelect = (Spinner) rootView.findViewById(R.id.device_select);

        deviceSelect.setAdapter(new SpinnerImageAdapter(getActivity(), R.layout.image_row,
                getResources().getStringArray(R.array.devices_array)));

        final String[] deviceList = getResources().getStringArray(R.array.devices_array);

        deviceSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {

                if (!deviceList[position].equals(currentDevice)) {
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

        final Drawable spinnerDrawable = deviceSelect.getBackground().getConstantState().newDrawable();

        spinnerDrawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        ViewTreeObserver vto = deviceSelect.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                // Remove after the first run so it doesn't fire forever
                deviceSelect.getViewTreeObserver().removeOnPreDrawListener(this);
                int spinnerWidth = deviceSelect.getMeasuredWidth();
                View grey_rect = rootView.findViewById(R.id.spinner_background);
                int otherHeight = grey_rect.getMeasuredHeight();
                grey_rect.setLayoutParams(new RelativeLayout.LayoutParams(spinnerWidth + 25, otherHeight + 6));
                return true;
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            deviceSelect.setBackground(spinnerDrawable);
        } else{
            deviceSelect.setBackgroundDrawable(spinnerDrawable);
        }

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
