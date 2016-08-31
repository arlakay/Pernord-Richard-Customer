package com.com.rahmandev.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.com.rahmandev.R;
import com.com.rahmandev.app.AppController;
import com.com.rahmandev.model.Event;

import java.util.List;

/**
 * Created by Ersa Rizki Dimitri on 12-12-2015.
 */
public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.VersionViewHolder> {

    //List<String> versionModels;
    //Boolean isHomeList = false;
    //public static List<String> homeActivitiesList = new ArrayList<String>();
    //public static List<String> homeActivitiesSubList = new ArrayList<String>();
    private List<Event> restaurantList;
    //private final LayoutInflater mInflater;
    Context context;
    OnItemClickListener clickListener;
    ImageLoader imageLoader;

    /*
    public void setHomeActivitiesList(Context context) {
        String[] listArray = context.getResources().getStringArray(R.array.home_activities);
        String[] subTitleArray = context.getResources().getStringArray(R.array.home_activities_subtitle);
        for (int i = 0; i < listArray.length; ++i) {
            homeActivitiesList.add(listArray[i]);
            homeActivitiesSubList.add(subTitleArray[i]);
        }
    }
    */

    /*
    public SimpleRecyclerAdapter(Context context, List<Restaurant> models) {
        mInflater = LayoutInflater.from(context);
        restaurantList = new ArrayList<>(models);
    }*/

    public EventRecyclerAdapter(Context context) {
        //isHomeList = true;
        this.context = context;
        //setHomeActivitiesList(context);
    }

    public EventRecyclerAdapter(List<Event> restaurantList, OnItemClickListener listener) {
        //isHomeList = false;
        this.restaurantList = restaurantList;
        this.clickListener = listener;

    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_event, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        imageLoader = AppController.getInstance().getImageLoader();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        //if (isHomeList) {
        //versionViewHolder.titleRes.setText(restaurantList.get(i).title);
        //versionViewHolder.alamatRes.setText(restaurantList.get(i).alamat);
        //versionViewHolder.picRes.setImageUrl(restaurantList.get(i).urlpic, imageLoader);

        final Event model = restaurantList.get(i);
        versionViewHolder.bind(model, clickListener);

        //} else {
        //versionViewHolder.title.setText(restaurantList.get(i).title);
        //versionViewHolder.alamat.setText(restaurantList.get(i).alamat);
        //}
    }

    @Override
    public int getItemCount() {
        //if (isHomeList)
        //return homeActivitiesList == null ? 0 : homeActivitiesList.size();
        //else
        return restaurantList == null ? 0 : restaurantList.size();
    }

    public void animateTo(List<Event> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Event> newModels) {
        for (int i = restaurantList.size() - 1; i >= 0; i--) {
            final Event model = restaurantList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Event> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Event model = newModels.get(i);
            if (!restaurantList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Event> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Event model = newModels.get(toPosition);
            final int fromPosition = restaurantList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Event removeItem(int position) {
        final Event model = restaurantList.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Event model) {
        restaurantList.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Event model = restaurantList.remove(fromPosition);
        restaurantList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }


    class VersionViewHolder extends RecyclerView.ViewHolder {
        CardView cardItemLayout;
        TextView titleRes;
        TextView alamatRes;
        TextView isiBerita;
        ImageView picRes;
        TextView btnReadMore;

        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            picRes = (ImageView) itemView.findViewById(R.id.imageView2);
            titleRes = (TextView) itemView.findViewById(R.id.merchantName);

            //if (isHomeList) {
            //itemView.setOnClickListener(this);
            //} else {
            //alamatRes.setVisibility(View.GONE);
            //}

        }

        public void bind(final Event model, final OnItemClickListener listener) {
            titleRes.setText(model.getStore_name());
            //picRes.setImageUrl(model.getGambar(), imageLoader);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(model);

                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(Event model);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


}
