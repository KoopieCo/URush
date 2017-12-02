package com.csce4623.rynolan.urush;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.csce4623.rynolan.urush.models.RusheeInfo;
import com.csce4623.rynolan.urush.restapi.RegistrationAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnSubmit;
    private EditText etFirstName, etLastName, etEmail;
    private Spinner spinnerYear;

    private OnFormFragmentInteractionListener mListener;

    public FormFragment() {}

    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
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
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        // Set views programmatically here
        etFirstName = (EditText)view.findViewById(R.id.etFormFirstName);
        etLastName = (EditText)view.findViewById(R.id.etFormLastName);
        etEmail = (EditText)view.findViewById(R.id.etFormEmail);
        spinnerYear = (Spinner)view.findViewById(R.id.spinnerFormYear);
        btnSubmit = (Button)view.findViewById(R.id.btnFormSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Package form data into a single object
                RusheeInfo pack = new RusheeInfo(etEmail.getText().toString(),etFirstName.getText().toString(),1,etLastName.getText().toString(),spinnerYear.getSelectedItem().toString(),null);

                Gson gson = new GsonBuilder().setLenient().create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getString(R.string.base_url))
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                RegistrationAPI registrationAPI = retrofit.create(RegistrationAPI.class);
                Call<RusheeInfo> call = registrationAPI.addRushee(pack);
                call.enqueue(new Callback<RusheeInfo>() {
                    @Override
                    public void onResponse(Call<RusheeInfo> call, Response<RusheeInfo> response) {
                        RusheeInfo rushee = response.body();
                        System.out.println("Submitted Rushee Information!");
                    }

                    @Override
                    public void onFailure(Call<RusheeInfo> call, Throwable throwable) {
                        System.out.println("Failure to Submit Rushee Information...");
                    }
                });
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFormFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFormFragmentInteractionListener) {
            // Take the parent activity's implementation of our IteractionListener.
            mListener = (OnFormFragmentInteractionListener) context;
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
    public interface OnFormFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFormFragmentInteraction(Uri uri);
    }
}
