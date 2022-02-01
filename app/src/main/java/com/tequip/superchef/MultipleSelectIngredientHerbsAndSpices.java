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

public class MultipleSelectIngredientHerbsAndSpices extends DialogFragment {

    public interface onMultiChoiceListenerHerbsAndSpices{
        void onPositiveButtonClickedHerbsAndSpices(String[] list, ArrayList<String> SelectedItemListHerbsAndSpices );
        void onNegativeButtonClickedHerbsAndSpices();
    }

    MultipleSelectIngredientHerbsAndSpices.onMultiChoiceListenerHerbsAndSpices mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (MultipleSelectIngredientHerbsAndSpices.onMultiChoiceListenerHerbsAndSpices) context;
        } catch (Exception e){
            throw new ClassCastException(getActivity().toString()+" onMultiChoiceListener must Implemented");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        ArrayList<String> SelectedItemListHerbsAndSpices = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] listHerbsAndSpices = getActivity().getResources().getStringArray(R.array.Herbs_and_spices);
        builder.setTitle("\uD83C\uDF3F Herbs & Spices")
                .setMultiChoiceItems(listHerbsAndSpices, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            SelectedItemListHerbsAndSpices.add(listHerbsAndSpices[i]);
                        } else {
                            SelectedItemListHerbsAndSpices.remove(listHerbsAndSpices[i]);
                        }
                    }
                })
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClickedHerbsAndSpices(listHerbsAndSpices,SelectedItemListHerbsAndSpices);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClickedHerbsAndSpices();
                    }
                });
        return builder.create();
    }
}