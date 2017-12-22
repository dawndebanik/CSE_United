package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Adapter for the main gallery page
 * Created by Debanik on 20-11-2017.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ImageHolder>{

    private List<String> imageList = Collections.emptyList();
    private ImageClickListener imageClickListener;
    private LayoutInflater inflater;
    private Context context;

    GalleryAdapter(Context context, List<String> imageList){
        this.imageList = imageList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public void setImageClickListener(ImageClickListener imageClickListener){
        this.imageClickListener = imageClickListener;
    }
    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.image_holder, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        Picasso.with(context.getApplicationContext()).load(imageList.get(position).toString()).placeholder(R.drawable.placeholder).into(holder.image);
    }

    @Override
    public int getItemCount() {
        Log.d("SIZE", String.valueOf(imageList.size()));
        return imageList.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder{
        ImageView image;
        ImageHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(imageClickListener!=null)
                        imageClickListener.onImageClick(view);
                }
            });
            this.image = (ImageView) itemView.findViewById(R.id.gallery_item);
        }
    }
    public interface ImageClickListener{
        void onImageClick(View view);
    }
}