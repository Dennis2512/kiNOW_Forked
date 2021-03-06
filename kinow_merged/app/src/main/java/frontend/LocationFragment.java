package frontend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import backend.classes.Kino;
import backend.connections.Requests;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LocationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationFragment extends Fragment implements LocationAdapter.onLocationClickedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // locationList RecyclerView
    private RecyclerView locationList;
    private View locView;
    LocationAdapter locAdapter;

    //Kino Liste und ausgewähltes Kino
    ArrayList<Kino> kinos = new ArrayList<Kino>();

    private OnFragmentInteractionListener mListener;
    private OnKinoIDChangedListener onKinoIDChangedListener;

    public LocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        locView = inflater.inflate(R.layout.fragment_location, container, false);

        locationList = locView.findViewById(R.id.locationList);
        locationList.setHasFixedSize(true);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        locationList.setLayoutManager(layoutManager);

        //alle Kinos holen
        if(kinos.size()==0){
            Requests request = new Requests();
            kinos = request.getKinos();
        }//then

        if (locAdapter==null)locAdapter = new LocationAdapter(kinos, getActivity(),this);

        locationList.setAdapter(locAdapter);
        locationList.getAdapter().notifyDataSetChanged();


        return locView;
    }//onCreateView

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onKinoSelection(long id) {
        Kino kino = kinos.get((int) id);
        String msg = kino.getName() + " selected.";
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        onKinoIDChangedListener = (OnKinoIDChangedListener)getContext();
        onKinoIDChangedListener.onKinoIDChanged(kino);
    }//onKinoSelection

    public interface OnKinoIDChangedListener{
        void onKinoIDChanged(Kino kino);
    }//interface
}
