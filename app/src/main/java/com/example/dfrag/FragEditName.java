package com.example.dfrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

public class FragEditName extends DialogFragment
{



    private EditText mEditText;



    public FragEditName()
    {

        // Empty constructor is required for DialogFragment

        // Make sure not to add arguments to the constructor

        // Use `newInstance` instead as shown below

    }



    public static FragEditName newInstance(String title) {

        FragEditName frag = new FragEditName();

        Bundle args = new Bundle();

        args.putString("title", title);

        frag.setArguments(args);

        return frag;

    }



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_edit_name, container);

    }



    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Get field from view

        mEditText = (EditText) view.findViewById(R.id.txt_your_name);

        // Fetch arguments from bundle and set title

        String title = getArguments().getString("title", "Enter Name");

        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field

        mEditText.requestFocus();

        getDialog().getWindow().setSoftInputMode(

                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }

}

