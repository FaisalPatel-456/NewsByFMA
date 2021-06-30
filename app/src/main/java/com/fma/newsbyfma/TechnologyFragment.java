package com.fma.newsbyfma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechnologyFragment extends Fragment {

    private final String api = "e4caaadfe1f54763877da8213d87f505";
    private ArrayList<Model> modelArrayList;
    private Adapter adapter;
    private String country = "in";
    private RecyclerView recyclerViewTechnology;
    private String category = "technology";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.technology_fragment, null);

        recyclerViewTechnology = view.findViewById(R.id.recycler_view_technology);
        modelArrayList = new ArrayList<>();
        recyclerViewTechnology.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelArrayList);
        recyclerViewTechnology.setAdapter(adapter);

        findNews();

        return view;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country, category, 100, api).enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if(response.isSuccessful()){
                    modelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {

            }
        });
    }
}
