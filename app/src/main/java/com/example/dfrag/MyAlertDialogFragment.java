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

    ArrayList<String> filepath = new ArrayList<String>();

    ArrayList<String> selectedItems = new ArrayList<String>();

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
        final List<String> listItems = new ArrayList<String>();
        File mfile=new File(rootDir);
        File[] list=mfile.listFiles();
        String tempupper;
        for(int i=0;i<mfile.listFiles().length;i++)
        {
            tempstr=list[i].getAbsolutePath();
            tempupper=tempstr.toUpperCase();
            if(tempupper.endsWith(".BCH") )
                {
                filepath.add(list[i].getAbsolutePath());
                listItems.add(list[i].getName());
                }
        }

        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);


        selectedItems = new ArrayList();  // Where we track the selected items

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle("Delete Selected Batches")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(items, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    selectedItems.add(filepath.get(which));
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
                        {   int syze=selectedItems.size();
                            for(int i=0;i<syze;i++) {
                                File file = new File(selectedItems.get(i));
                                boolean deleted = file.delete();
                            }
                            Toast.makeText(getActivity(),String.format("Deleted %d Batche",syze),Toast.LENGTH_SHORT).show();
                        }

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

