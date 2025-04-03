package com.example.mega_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewHot, recyclerViewGamers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager2 viewPagerImages = rootView.findViewById(R.id.viewPagerImages);
        // Список URL изображений
        List<String> imageUrls = Arrays.asList(
                "https://example.com/image1.jpg",
                "https://www.oat.ru/images/students/alumniEmploymentCenterOmsk/contract.jpg",
                "https://example.com/image3.jpg"
        );

        ImageAdapter adapter = new ImageAdapter(getContext(), imageUrls);
        viewPagerImages.setAdapter(adapter);

        // Инициализация TabLayout
        TabLayout tabLayout = rootView.findViewById(R.id.tabLayoutImages);

        // Подключаем TabLayout к ViewPager2
        new TabLayoutMediator(tabLayout, viewPagerImages, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                // Можем настроить внешний вид вкладок или индикаторов здесь
            }
        }).attach();

        recyclerViewHot = rootView.findViewById(R.id.RecyclerView2);
        recyclerViewHot.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Item> hotItemList = new ArrayList<>();
        hotItemList.add(new Item("Сборка Ахмед",  "Игровая", "Игровая сборка с мощной видеокартой.", 119999.0, "https://andpro.ru/upload/iblock/bc4/Computer_assembly.jpg"));
        hotItemList.add(new Item("Сборка Разрыв", "Рабочая", "Для работы и игр. Современный процессор.", 89999.0, "https://i.ytimg.com/vi/09lEqOMbITI/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLB0cgJCFy1yexfW4y5_VT2ndfujpQ"));
        hotItemList.add(new Item("Сборка Бюджет", "Бюджетная", "Бюджетная сборка для повседневных задач.", 39999.0, "https://img.ixbt.site/live/topics/preview/00/00/67/03/a42edaa2e9.jpg"));

        recyclerViewHot.setHasFixedSize(true);
        recyclerViewHot.setVerticalScrollBarEnabled(false);
        recyclerViewHot.setHorizontalScrollBarEnabled(false);

        recyclerViewHot.setAdapter(new ItemAdapter(getContext(), ItemAdapter.TypeAdapter.TYPE_VERTICAL, hotItemList, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onOrderClick(Item item) {
                // Логика для кнопки "Order"
            }

            @Override
            public void onBuyClick(Item item) {
                // Логика для кнопки "Buy"
            }

            @Override
            public void onDeleteClick(Item item) {
                // Логика для кнопки "Delete"
            }
        }));

        recyclerViewGamers = rootView.findViewById(R.id.RecyclerView3);
        recyclerViewGamers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<Item> gamersItemList = new ArrayList<>();
        gamersItemList.add(new Item("Сборка Ахмед",  "Игровая", "Игровая сборка с мощной видеокартой.", 119999.0, "https://andpro.ru/upload/iblock/bc4/Computer_assembly.jpg"));
        gamersItemList.add(new Item("Сборка Разрыв", "Рабочая", "Для работы и игр. Современный процессор.", 89999.0, "https://i.ytimg.com/vi/09lEqOMbITI/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLB0cgJCFy1yexfW4y5_VT2ndfujpQ"));
        gamersItemList.add(new Item("Сборка Бюджет", "Бюджетная", "Бюджетная сборка для повседневных задач.", 39999.0, "https://img.ixbt.site/live/topics/preview/00/00/67/03/a42edaa2e9.jpg"));

        recyclerViewGamers.setAdapter(new ItemAdapter(getContext(), ItemAdapter.TypeAdapter.TYPE_HORIZONTAL, gamersItemList, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onOrderClick(Item item) {
                // Логика для кнопки "Order"
            }

            @Override
            public void onBuyClick(Item item) {
                // Логика для кнопки "Buy"
            }

            @Override
            public void onDeleteClick(Item item) {
                // Логика для кнопки "Delete"
            }
        }));

        return rootView;
    }
}