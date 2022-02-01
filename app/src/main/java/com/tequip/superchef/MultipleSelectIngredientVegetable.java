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

public class MultipleSelectIngredientVegetable extends DialogFragment {

    public interface onMultiChoiceListenerVegetable{
        void onPositiveButtonClickedVegetable(String[] list,ArrayList<String> SelectedItemListVegetable );
        void onNegativeButtonClickedVegetable();
    }

    MultipleSelectIngredientVegetable.onMultiChoiceListenerVegetable mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (MultipleSelectIngredientVegetable.onMultiChoiceListenerVegetable) context;
        } catch (Exception e){
            throw new ClassCastException(getActivity().toString()+" onMultiChoiceListener must Implemented");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        ArrayList<String> SelectedItemListVegetable = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] listVegetable = getActivity().getResources().getStringArray(R.array.Vegetables);
        builder.setTitle("\uD83E\uDD55 Vegetables")
                .setMultiChoiceItems(listVegetable, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            SelectedItemListVegetable.add(listVegetable[i]);
                        } else {
                            SelectedItemListVegetable.remove(listVegetable[i]);
                        }
                    }
                })
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClickedVegetable(listVegetable,SelectedItemListVegetable);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClickedVegetable();
                    }
                });
        return builder.create();
    }
}