package com.example.dfrag;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;




public class MyAlertDialogFragment extends DialogFragment
{
ArrayList selectedItems;


    ArrayList<String> filenem = new ArrayList<String>();



    public MyAlertDialogFragment() {

        // Empty constructor required for DialogFragment

    }



    public static MyAlertDialogFragment newInstance(String title) {

        MyAlertDialogFragment frag = new MyAlertDialogFragment();

        Bundle args = new Bundle();

        args.putString("title", title);

        frag.setArguments(args);

        return frag;

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
   ////  ensure that the follwing line is added in AndroidManifest.xml
   //// <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

        String tempstr;
        String rootDir= Environment.getExternalStorageDirectory().getPath();
        List<String> listItems = new ArrayList<String>();
        File mfile=new File(rootDir);
        File[] list=mfile.listFiles();
        String tempupper;
        for(int i=0;i<mfile.listFiles().length;i++)
        {
            tempstr=list[i].getAbsolutePath();
            tempupper=tempstr.toUpperCase();
            if(tempupper.endsWith(".BCH") )
                listItems.add(list[i].getAbsolutePath());
        }

        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);


        selectedItems = new ArrayList();  // Where we track the selected items

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle("Pick Toppings")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(items, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    selectedItems.add(which);
                                } else if (selectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    selectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        {
                            Toast.makeText(getActivity(),selectedItems.get(0).toString(),Toast.LENGTH_SHORT).show();
                            File file = new File(selectedItems.get(0).toString());
                            boolean deleted = file.delete();
                        }
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                  // ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                   //...


                    }
                });

        return builder.create();
    }


}

