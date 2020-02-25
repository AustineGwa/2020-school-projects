package com.appgeeksarena.getahouse.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appgeeksarena.getahouse.R;
import com.appgeeksarena.getahouse.models.Section;
import com.appgeeksarena.getahouse.utils.listeners.OnSectionClickListener;

import java.util.List;
import java.util.Random;

public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.SectionViewHolder> {

    private  List<Section> sectionList;
    private  OnSectionClickListener onSectionClickListener;

    public SectionsAdapter(List<Section> sectionList, OnSectionClickListener onSectionClickListener) {
        this.sectionList = sectionList;
        this.onSectionClickListener = onSectionClickListener;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_holder, parent, false);
        SectionViewHolder sectionViewHolder = new SectionViewHolder(view, onSectionClickListener);
        return sectionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {

        holder.icon.setImageResource(sectionList.get(position).getImage());
        holder.name.setText(sectionList.get(position).getName());
        holder.layout.setBackgroundColor(getRandomColor());

    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    class SectionViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{

       private  ImageView icon;
       private  TextView  name;
       private LinearLayout layout;
       private OnSectionClickListener onSectionClickListener;


        public SectionViewHolder(@NonNull View itemView, OnSectionClickListener onSectionClickListener) {
            super(itemView);

            this.icon = itemView.findViewById(R.id.section_icon);
            this.name = itemView.findViewById(R.id.section_name);
            this.onSectionClickListener = onSectionClickListener;
            layout = itemView.findViewById(R.id.section_holder_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSectionClickListener.onSectionClicked(sectionList.get(getAdapterPosition()).getName());

        }
    }

    private int getRandomColor() {

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }
}
