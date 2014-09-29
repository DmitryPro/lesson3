package com.example.task3;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.app.Activity;
import android.app.Fragment;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Activity1  extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //return super.onCreateView(inflater, container, savedInstanceState);

        View viewHierarchy =
                inflater.inflate(R.layout.activity_main1,
                        container, false);
        return viewHierarchy;
    }


}
