package com.example.jotlapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jotlapp.R;
import com.example.jotlapp.models.Hero;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class HeroRecyclerAdapter extends RecyclerView.Adapter<HeroRecyclerAdapter.ViewHolder> {

    private ArrayList<Hero> mHeroes = new ArrayList<>();
    private OnHeroListener mOnHeroListener;
    private Context mContext;

    public HeroRecyclerAdapter(ArrayList<Hero> heroes, OnHeroListener onHeroListener, Context context) {
        this.mHeroes = heroes;
        this.mOnHeroListener = onHeroListener;
        this.mContext = context;
    }

    // Method responsible for instantiating viewholder method
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hero_list_item, parent, false);
        return new ViewHolder(view, mOnHeroListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String character;
        String imgURL;
        ImageLoader imageLoader;

        setupImageLoader();

        holder.level.setText(mHeroes.get(position).getLevel());
        holder.name.setText(mHeroes.get(position).getName());

        character = mHeroes.get(position).getCharacter();
        imgURL = getImageUrl(character);

        imageLoader = ImageLoader.getInstance();

        int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed", null, mContext.getPackageName());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        imageLoader.displayImage(imgURL, holder.characterImg, options);

    }

    @Override
    public int getItemCount() {
        return mHeroes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, level;
        ImageView characterImg;
        OnHeroListener onHeroListener;

        public ViewHolder(@NonNull View itemView, OnHeroListener onHeroListener) {
            super(itemView);

            name = itemView.findViewById(R.id.LayoutHeroListItem_TextView_Name);
            level = itemView.findViewById(R.id.LayoutHeroListItem_TextView_Level);
            characterImg = itemView.findViewById(R.id.LayoutHeroListItem_ImageView_Character);

            this.onHeroListener = onHeroListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onHeroListener.onHeroClick(getAdapterPosition());
        }
    }

    public interface OnHeroListener {
        void onHeroClick(int position);
    }

    private void setupImageLoader() {
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
    }

    private String getImageUrl(String character) {
        switch (character) {
            case "Voidwarden" : return "drawable://" + R.drawable.voidwarden_hero_class;
            case "Red Guard" : return "drawable://" + R.drawable.red_guard_hero_class;
            case "Demolitionist" : return "drawable://" + R.drawable.demolitionist_hero_class;
            case "Hatchet" : return "drawable://" + R.drawable.hatchet_hero_class;
        }
        return null;
    }
}
