package com.example.statussaverpro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.statussaverpro.Models.WhatsappStatusModel;
import com.example.statussaverpro.R;
import com.example.statussaverpro.Util;
import com.example.statussaverpro.databinding.WhatsappItemsLayoutBinding;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class WhatsappAdapter extends RecyclerView.Adapter<WhatsappAdapter.ViewHolder> {

    private ArrayList<WhatsappStatusModel> whatsappArraylist;
    private Context context;
    private LayoutInflater layoutInflater;
    private String filepath = Util.rootDirectoryWhatsapp+"/";

    public WhatsappAdapter(ArrayList<WhatsappStatusModel> whatsappArraylist, Context context) {
        this.whatsappArraylist = whatsappArraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public WhatsappAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){

            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        return new ViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.whatsapp_items_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsappAdapter.ViewHolder holder, int position) {

        WhatsappStatusModel item = whatsappArraylist.get(position);
        if (item.getUri().toString().endsWith(".mp4"))
            holder.binding.btnPlay.setVisibility(View.VISIBLE);
        else holder.binding.btnPlay.setVisibility(View.GONE);


        Glide.with(context).load(item.getPath()).into(holder.binding.statusImages);


        holder.binding.btnDownloadwtsapp.setOnClickListener(v->{

            Util.createFileFolder();
            final String path = item.getPath();
            final File file = new File(path);
            File destFile = new File(filepath);

            try {
                FileUtils.copyFileToDirectory(file , destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(context,"Saved to: "+ filepath,Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    public int getItemCount() {
        return whatsappArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        WhatsappItemsLayoutBinding binding;


        public ViewHolder(WhatsappItemsLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}
