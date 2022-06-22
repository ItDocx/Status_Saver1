package com.example.statussaverpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.statussaverpro.Fragments.ImageFragment;
import com.example.statussaverpro.Fragments.VideoFragment;
import com.example.statussaverpro.databinding.ActivityMainBinding;
import com.example.statussaverpro.databinding.ActivityWhatsappBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

public class WhatsappActivity extends AppCompatActivity {


    private ActivityWhatsappBinding binding;
    private WhatsappActivity whatsappActivity;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_whatsapp);
        whatsappActivity = this;

        initView();

    }

    private void initView() {

        adapter = new ViewPagerAdapter(whatsappActivity.getSupportFragmentManager(),whatsappActivity.getLifecycle());

        adapter.addFragment(new ImageFragment(),"Images");
        adapter.addFragment(new VideoFragment(),"Vidoes");

        binding.pagerWhatsapp.setAdapter(adapter);
        binding.pagerWhatsapp.setOffscreenPageLimit(1);

        new TabLayoutMediator(binding.tablayout,binding.pagerWhatsapp,((tab, position) -> {

            tab.setText(adapter.fragmentTitleList.get(position));
        })).attach();

        for (int i=0; i<binding.tablayout.getTabCount();i++){

            TextView tv = (TextView) LayoutInflater.from(whatsappActivity)
                    .inflate(R.layout.custom_tab,null);

            binding.tablayout.getTabAt(i).setCustomView(tv);

        }

    }

    class ViewPagerAdapter extends FragmentStateAdapter{

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        public void addFragment(Fragment fragment, String title){

            fragmentList.add(fragment);
            fragmentTitleList.add(title);

        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }

}