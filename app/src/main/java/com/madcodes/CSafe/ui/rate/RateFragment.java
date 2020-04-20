package com.madcodes.CSafe.ui.rate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.madcodes.CSafe.API.ApiClient;
import com.madcodes.CSafe.API.ApiInterface;
import com.madcodes.CSafe.Adapters.PrepaidBundlesAdapter;
import com.madcodes.CSafe.Models.RelationList;
import com.madcodes.CSafe.R;
import com.madcodes.CSafe.Utils.CSafePreferences;
import com.madcodes.CSafe.Utils.CommonClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Callback;

public class RateFragment extends Fragment {

    private RateViewModel rateViewModel;
    RecyclerView pre_pay_recyclerview;

    ApiInterface apiInterface;
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    PrepaidBundlesAdapter prePaypacksAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rateViewModel = ViewModelProviders.of(this).get(RateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rate, container, false);

        pre_pay_recyclerview = root.findViewById(R.id.pre_pay_recyclerview);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        setAdapter();
        return root;
    }

    private void setAdapter() {
        try {
            ArrayList<RelationList> bundlelist = new ArrayList<>();
            RelationList relationList = new RelationList();
            RelationList relationList2 = new RelationList();
            RelationList relationList3 = new RelationList();


            JSONObject obj = null;

            obj = new JSONObject(CSafePreferences.getUserRelations());


            if (obj != null) {

                relationList.setRelation_mobile(obj.getString("mobile1"));
                relationList.setRelation_name(obj.getString("relation1"));
                relationList.setRelationId("1");
                relationList.setMy_mobile(CSafePreferences.getMsisdn());
                bundlelist.add(relationList);

                relationList2.setRelation_mobile(obj.getString("mobile2"));
                relationList2.setRelation_name(obj.getString("relation2"));
                relationList2.setRelationId("2");
                relationList2.setMy_mobile(CSafePreferences.getMsisdn());
                bundlelist.add(relationList2);

                relationList3.setRelation_mobile(obj.getString("mobile3"));
                relationList3.setRelation_name(obj.getString("relation3"));
                relationList3.setRelationId("3");
                relationList3.setMy_mobile(CSafePreferences.getMsisdn());
                bundlelist.add(relationList3);
            }


            // bundlelist.add(relationList);
      /*  bundlelist.get(0).setMy_mobile(CSafePreferences.getMsisdn());
        bundlelist.get(0).setRelationId("1");
        bundlelist.get(0).setRelation_name(CommonClass.user_relations.getRelation1());
        bundlelist.get(0).setRelation_mobile(CommonClass.user_relations.getMobile1());

        bundlelist.get(1).setMy_mobile(CSafePreferences.getMsisdn());
        bundlelist.get(1).setRelationId("2");
        bundlelist.get(1).setRelation_name(CommonClass.user_relations.getRelation2());
        bundlelist.get(1).setRelation_mobile(CommonClass.user_relations.getMobile2());

        bundlelist.get(2).setMy_mobile(CSafePreferences.getMsisdn());
        bundlelist.get(2).setRelationId("3");
        bundlelist.get(2).setRelation_name(CommonClass.user_relations.getRelation2());
        bundlelist.get(2).setRelation_mobile(CommonClass.user_relations.getMobile3());*/


            prePaypacksAdapter = new PrepaidBundlesAdapter(getActivity(), bundlelist);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            pre_pay_recyclerview.setLayoutManager(mLayoutManager);
            pre_pay_recyclerview.setItemAnimator(new DefaultItemAnimator());
            pre_pay_recyclerview.setAdapter(prePaypacksAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
