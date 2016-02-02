package com.remote;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.remote.elements.ChannelAdapter;
import com.remote.utilities.ButtonAction;
import com.remote.utilities.ActionClickListener;
import com.remote.utilities.ServerRequest;
import com.remote.utilities.StatusButton;
import com.remote.utilities.StoredResources;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

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

    private Spinner channelSelect;
    private int currentDevice;

    private StatusButton powerButton;


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

        avPowerButtonSetup(rootView);
        hdmiChannelSelectorSetup(rootView);
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

    @Override
    public void onResume() {
        super.onResume();
        getInitialValues();
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

    private void avPowerButtonSetup(View rootView) {
        final StatusButton avPowerButton = new StatusButton((FancyButton) rootView
                .findViewById(R.id.AVPower));
        powerButton = avPowerButton;
        svreq.getAVPower(new ButtonAction() {
            @Override
            public void action(String input) {
                Log.v("Returned", input);
                if (input.equals("Off") || input.equals("Standby")) {
                    avPowerButton.setStatus("Off");
                    avPowerButton.deselectButton(getResources()
                            .getColor(R.color.powerbutton));
                } else if (input.equals("On")) {
                    avPowerButton.setStatus("On");
                    avPowerButton.selectButton(getResources()
                            .getColor(R.color.powerbutton));
                }
            }
        });
        Map<String, View> newmap = new HashMap<>();
        newmap.put("PowerButton", avPowerButton);
        avPowerButton.setOnClickListener(new ActionClickListener(newmap) {
            @Override
            public void onClick(View v) {
                StatusButton button = ((StatusButton) viewLibrary.get("PowerButton"));
                Log.v("Current Status", button.getStatus());
                if (button.getStatus() == "Off") {
                    avPowerButton.selectButton(getResources()
                            .getColor(R.color.powerbutton));
                    button.setStatus("On");
                    svreq.setAVPowerOn(new ButtonAction() {
                        public void action(String input) {
                            if (input.toLowerCase().equals("on")) {

                            } else if (input.toLowerCase().equals("off") ||
                                    input.toLowerCase().equals("standby")) {
                                StatusButton button = ((StatusButton) viewLibrary
                                        .get("PowerButton"));
                                avPowerButton.deselectButton(getResources()
                                        .getColor(R.color.powerbutton));
                                button.setStatus("Off");
                            } else {
                                Log.v("Error", "Invalid return from power status request");
                            }
                        }
                    });
                } else if (button.getStatus() == "On") {
                    avPowerButton.deselectButton(getResources()
                            .getColor(R.color.powerbutton));
                    button.setStatus("Off");
                    svreq.setAVPowerOff(new ButtonAction() {
                        public void action(String input) {
                            if (input.toLowerCase().equals("on")) {
                                StatusButton button = ((StatusButton) viewLibrary.get("PowerButton"));
                                avPowerButton.selectButton(getResources()
                                        .getColor(R.color.powerbutton));
                                button.setStatus("On");
                            } else if (input.toLowerCase().equals("off") ||
                                    input.toLowerCase().equals("standby")) {
                            } else {
                                Log.v("Error", "Invalid return from power status request");
                            }
                        }
                    });
                }
            }
        });
    }

    private void hdmiChannelSelectorSetup(final View rootView) {
        currentDevice = 0;
        channelSelect = (Spinner) rootView.findViewById(R.id.channelSelect);
        ChannelAdapter adapter = new ChannelAdapter(getActivity().getApplicationContext(),
                Arrays.asList(getResources().getStringArray(R.array.devices_array)), channelSelect);
        channelSelect.setAdapter(adapter);
        svreq.getAVChannel(new ButtonAction() {
            @Override
            public void action(String input) {
                if (input.substring(0, 4).equals("HDMI")) {
                    int inputDev = Integer.parseInt(input.substring(4, 5)) - 1;
                    channelSelect.setSelection(inputDev);
                    TextView text = ((TextView) rootView.findViewById(R.id.channel_adapter_id));
                    Log.v("Text", text.getText().toString());
                    text.setText(channelSelect.getItemAtPosition(inputDev).toString());
                }
            }
        });
        channelSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("current", currentDevice + "");
                if (position != currentDevice) {
                    currentDevice = position;
                    TextView text = ((TextView) view.findViewById(R.id.channel_adapter_id));
                    text.setText(channelSelect.getItemAtPosition(position).toString());
                    svreq.setAVChannel("HDMI" + (position + 1), new ButtonAction() {
                        @Override
                        public void action(String input) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getInitialValues() {
        svreq.getAVPower(new ButtonAction() {
            @Override
            public void action(String input) {
                Log.v("Returned", input);
                if (input.equals("Off") || input.equals("Standby")) {
                    powerButton.setStatus("Off");
                    powerButton.deselectButton(getResources()
                            .getColor(R.color.powerbutton));
                } else if (input.equals("On")) {
                    powerButton.setStatus("On");
                    powerButton.selectButton(getResources()
                            .getColor(R.color.powerbutton));
                    getInitialValuesHelper();
                }
            }
        });

    }

    private void getInitialValuesHelper() {
        svreq.getAVChannel(new ButtonAction() {
            @Override
            public void action(String input) {
                if (input.substring(0, 4).equals("HDMI")) {
                    int inputDev = Integer.parseInt(input.substring(4, 5)) - 1;
                    channelSelect.setSelection(inputDev);
                    TextView text = ((TextView) rootView.findViewById(R.id.channel_adapter_id));
                    Log.v("Text", text.getText().toString());
                    text.setText(channelSelect.getItemAtPosition(inputDev).toString());
                }
            }
        });
    }
}
