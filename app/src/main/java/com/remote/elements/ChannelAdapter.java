package com.remote.elements;
        import android.app.Activity;
        import android.content.Context;
        import android.graphics.Color;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.LinearLayout;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.remote.R;
        import com.remote.utilities.ActionClickListener;
        import com.remote.utilities.ServerRequest;
        import com.remote.utilities.StatusButton;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import mehdi.sakout.fancybuttons.FancyButton;


/**
 * Created by cameronridgewell on 1/21/15.
 */
public class ChannelAdapter extends ArrayAdapter<String> {
    private List<String> channel_list = new ArrayList<String>();
    private Context context;
    private ServerRequest svreq = ServerRequest.getInstance();
    private TextView text;
    private LinearLayout background;
    private String string;
    private int position;
    private Spinner spinner;

    public ChannelAdapter(Context context, List<String> channel_list, Spinner spinner) {
        super(context, R.layout.button_adapter_item, R.id.channel_adapter_id, channel_list);
        this.channel_list = channel_list;
        this.context = context;
        this.spinner = spinner;
    }

    @Override
    public int getCount() {
        return this.channel_list.size();
    }

    @Override
    public String getItem(int position) {

        return channel_list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.button_adapter_item, null);
        }

        text = (TextView) view.findViewById(R.id.channel_adapter_id);
        background = (LinearLayout) view.findViewById(R.id.channel_adapter_background);
        background.setBackground(context.getResources().getDrawable(R.drawable.transparent_rectangle));
        text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                spinner.performClick();
            }
        });
        select();
        return view;
    }

    public void select() {
        text.setTextColor(context.getResources().getColor(R.color.secondary_button_color));
        background.setBackground(context.getResources().getDrawable(R.drawable.light_grey_rectangle));
    }

    public int getTextView() {
        return text.getId();
    }
}