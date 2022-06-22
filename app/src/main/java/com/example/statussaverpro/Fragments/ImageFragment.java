package com.example.statussaverpro.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.statussaverpro.Adapters.WhatsappAdapter;
import com.example.statussaverpro.Models.WhatsappStatusModel;
import com.example.statussaverpro.R;
import com.example.statussaverpro.databinding.FragmentImageBinding;
import com.example.statussaverpro.databinding.WhatsappItemsLayoutBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageFragment extends Fragment {

    private FragmentImageBinding binding;
    private ArrayList<WhatsappStatusModel> Whatsapplistimg;
    private WhatsappAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image,container,false);

        Whatsapplistimg = new ArrayList<>();
        getData();
        binding.refreshView.setOnRefreshListener(()->{

            Whatsapplistimg = new ArrayList<>();
            getData();
            binding.refreshView.setRefreshing(false);
                });

        return binding.getRoot();
    }

    private void getData() {

        WhatsappStatusModel model;

        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/WhatsApp/Media/.statuses";
        File TargetDirectory = new File(targetPath);
        File[] allfiles = TargetDirectory.listFiles();

        String targetPathBusiness = Environment.getExternalStorageDirectory().getAbsolutePath()+"/WhatsApp Business/Media/.statuses";
        File TargetDirectoryBuiseness = new File(targetPathBusiness);
        File[] allfilesBuiseness = TargetDirectoryBuiseness.listFiles();

        Arrays.sort(allfiles, ((o1,o2)->{

            if (o1.lastModified()> o2.lastModified()) return -1;
            else if (o1.lastModified()< o2.lastModified()) return +1;
            else return 0;
        }));

        for (int i=0; i<allfiles.length; i++){

            File file = allfiles[i];
            if (Uri.fromFile(file).toString().endsWith(".png")||Uri.
                    fromFile(file).toString().endsWith(".jpg")){

                model = new WhatsappStatusModel("whats"+i,
                        Uri.fromFile(file),allfiles[i].getAbsolutePath(),file.getName());

                Whatsapplistimg.add(model);
            }
        }
        Arrays.sort(allfilesBuiseness, ((o11,o22)->{

            if (o11.lastModified()> o22.lastModified()) return -1;
            else if (o11.lastModified()< o22.lastModified()) return +1;
            else return 0;
        }));

        for (int i=0; i<allfilesBuiseness.length; i++){

            File file = allfilesBuiseness[i];
            if (Uri.fromFile(file).toString().endsWith(".png")||Uri.
                    fromFile(file).toString().endsWith(".jpg")){

                model = new WhatsappStatusModel("whatsBuiseness"+i,
                        Uri.fromFile(file),allfilesBuiseness[i].getAbsolutePath(),file.getName());

                Whatsapplistimg.add(model);
            }
        }
        adapter= new WhatsappAdapter(Whatsapplistimg,getActivity());
        binding.whatsappImageRecycler.setAdapter(adapter);

    }
    }