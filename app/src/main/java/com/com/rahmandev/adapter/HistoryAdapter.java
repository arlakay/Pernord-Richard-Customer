package com.com.rahmandev.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.com.rahmandev.R;
import com.com.rahmandev.model.Event;

import java.util.List;


/**
 * Created by E.R.D on 4/2/2016.
 */
public class HistoryAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Event> movieItems;



    public HistoryAdapter(Activity activity, List<Event> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_item_history, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView desc = (TextView) convertView.findViewById(R.id.desc);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView iv_firstLetter = (ImageView) convertView.findViewById(R.id.iv_firstLetter);

        // getting movie data for the row
        final Event m = movieItems.get(position);


//        // title
//        title.setText(m.getThumbnailUrl());
//
//        // rating
//        desc.setText(m.getDesc());
//
//        // release year
//        date.setText(m.getDate());
//
//        name.setText(m.getStoreName());
//        //name.setSingleLine(true);
//
//        // get the first letter in merchant name
//        String firstLetter = String.valueOf(m.getThumbnailUrl().charAt(0));
//
//        ColorGenerator generator = ColorGenerator.MATERIAL;
//
//        // generate random color
//        int color = generator.getColor(m.getThumbnailUrl());
//
//        TextDrawable drawable = TextDrawable.builder()
//                .buildRound(firstLetter, color); // radius in px
//
//        iv_firstLetter.setImageDrawable(drawable);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(activity, StrukDetailHistory.class);
//
//                // Pass all data
//                intent.putExtra("merchantName", m.getThumbnailUrl());
//                intent.putExtra("date", m.getDesc());
//                intent.putExtra("diskon", m.getDiskon());
//                intent.putExtra("storeName", m.getStoreName());
//
//                // Start SingleItemView Class
//                activity.startActivity(intent);
            }
        });

        return convertView;
    }

}
