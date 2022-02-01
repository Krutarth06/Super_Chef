package com.tequip.superchef;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class MultipleSelectIngredientBeverages extends DialogFragment {

    public interface onMultiChoiceListenerBeverages{
        void onPositiveButtonClickedBeverages(String[] list, ArrayList<String> SelectedItemListBeverages );
        void onNegativeButtonClickedBeverages();
    }

    MultipleSelectIngredientBeverages.onMultiChoiceListenerBeverages mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (MultipleSelectIngredientBeverages.onMultiChoiceListenerBeverages) context;
        } catch (Exception e){
            throw new ClassCastException(getActivity().toString()+" onMultiChoiceListener must Implemented");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        ArrayList<String> SelectedItemListBeverages = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] listBeverages = getActivity().getResources().getStringArray(R.array.Beverages);
        builder.setTitle("\uD83C\uDF77 Beverages")
                .setMultiChoiceItems(listBeverages, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            SelectedItemListBeverages.add(listBeverages[i]);
                        } else {
                            SelectedItemListBeverages.remove(listBeverages[i]);
                        }
                    }
                })
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClickedBeverages(listBeverages,SelectedItemListBeverages);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClickedBeverages();
                    }
                });
        return builder.create();
    }
}
