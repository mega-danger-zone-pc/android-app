package com.example.mega_app;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardShowFragment extends Fragment {
    private static final String ARG_ITEM = "item_selected";
    public static CardShowFragment newInstance(Item item) {
        CardShowFragment fragment = new CardShowFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }
    ConstraintLayout constraintLayoutTOP;
    BottomNavigationView bottomNavigationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_show, container, false);

        constraintLayoutTOP = getActivity().findViewById(R.id.constraintLayoutTOP);
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.animate().translationY(bottomNavigationView.getHeight()).setDuration(100).withEndAction(() -> bottomNavigationView.setVisibility(View.GONE)).start();
        constraintLayoutTOP.animate().translationY(-constraintLayoutTOP.getHeight()).setDuration(100).withEndAction(() -> constraintLayoutTOP.setVisibility(View.GONE)).start();

        if (getArguments() != null) {
            Item item = (Item) getArguments().getSerializable(ARG_ITEM);

            if (item != null) {
                ViewPager2 viewPagerImages = view.findViewById(R.id.viewPagerImages);
                List<String> imageUrls = new ArrayList<>();

                imageUrls.add(item.getImageUrl());
                if (item.getImagesUrl() != null) {
                    for (String s: item.getImagesUrl()) {
                        imageUrls.add(s);
                    }
                }

                ImageAdapter adapter = new ImageAdapter(getContext(), imageUrls);
                viewPagerImages.setAdapter(adapter);

                TabLayout tabLayout = view.findViewById(R.id.tabLayoutImages);

                new TabLayoutMediator(tabLayout, viewPagerImages, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(TabLayout.Tab tab, int position) {
                    }
                }).attach();

                TextView title = view.findViewById(R.id.textViewTitle);
                TextView description = view.findViewById(R.id.textViewDesc);
                //TextView price = view.findViewById(R.id.textViewCeil);

                view.findViewById(R.id.imageButtonBack).setOnClickListener(v -> toBack());

                title.setText(item.getName());
                description.setText(item.getDescription());
                //price.setText(new DecimalFormat("#,##0.00").format(item.getPrice()) + "₽");
            }
        }

        return view;
    }

    private void toBack() {
        requireActivity().getSupportFragmentManager().popBackStack();
        bottomNavigationView.animate().translationY(0).setDuration(100).withStartAction(() -> bottomNavigationView.setVisibility(View.VISIBLE)).start();
        constraintLayoutTOP.animate().translationY(0).setDuration(100).withStartAction(() -> constraintLayoutTOP.setVisibility(View.VISIBLE)).start();
    }
}