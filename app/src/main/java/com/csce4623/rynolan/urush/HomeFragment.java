package com.csce4623.rynolan.urush;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csce4623.rynolan.urush.adapter.NewsFeedArrayAdapter;
import com.csce4623.rynolan.urush.configurations.GlobalResources;
import com.csce4623.rynolan.urush.models.Announcement;
import com.csce4623.rynolan.urush.models.NewsItem;
import com.csce4623.rynolan.urush.restapi.AnnouncementAPI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnHomeFragmentInteractionListener mListener;

    private ArrayList<NewsItem> newsItems;
    private ListView listView;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvDescription = (TextView)view.findViewById(R.id.tvHomeDescription);

        if(GlobalResources.selectedFraternity == null) {
            GlobalResources.selectedFraternity = "Theta Tau";
        }
        tvDescription.setText(getString(R.string.home_description) + GlobalResources.selectedFraternity + "!");

        listView = (ListView)view.findViewById(R.id.lvNewsFeed);

        newsItems = new ArrayList<>();
        final NewsFeedArrayAdapter newsFeedArrayAdapter = new NewsFeedArrayAdapter(this.getActivity(), newsItems);
        listView.setAdapter(newsFeedArrayAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnnouncementAPI announcementAPI = retrofit.create(AnnouncementAPI.class);
        Call<List<Announcement>> call = announcementAPI.getAnnouncements();

        call.enqueue(new Callback<List<Announcement>>() {
            @Override
            public void onResponse(Call<List<Announcement>> call, Response<List<Announcement>> response) {
                if(response.isSuccessful()){
                    for(Announcement a : response.body()){
                        NewsItem tmp = new NewsItem(a.getEventTime(), a.getEventLocation(), a.getEventTitle());
                        newsFeedArrayAdapter.add(tmp);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Announcement>> call, Throwable t) {

            }
        });

        return view;
    }

    public static String formatDate(String s){
        String oldDate = s;
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return date.toString();
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Context context) {
        if (mListener != null) {
            mListener.onHomeFragmentInteraction(context);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeFragmentInteractionListener) {
            mListener = (OnHomeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnHomeFragmentInteractionListener {
        // TODO: Update argument type and name
        void onHomeFragmentInteraction(Context context);
    }
}
