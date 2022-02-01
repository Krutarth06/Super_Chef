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

public class MultipleSelectIngredientMeat extends DialogFragment {

    public interface onMultiChoiceListenerMeat {
        void onPositiveButtonClickedMeat(String[] list, ArrayList<String> SelectedItemListMeat);

        void onNegativeButtonClickedMeat();
    }

    MultipleSelectIngredientMeat.onMultiChoiceListenerMeat mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (MultipleSelectIngredientMeat.onMultiChoiceListenerMeat) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString() + " onMultiChoiceListener must Implemented");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        ArrayList<String> SelectedItemListMeat = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] listMeat = getActivity().getResources().getStringArray(R.array.Meat);
        builder.setTitle("\uD83C\uDF56 Meat & Poultry")
                .setMultiChoiceItems(listMeat, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            SelectedItemListMeat.add(listMeat[i]);
                        } else {
                            SelectedItemListMeat.remove(listMeat[i]);
                        }
                    }
                })
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClickedMeat(listMeat, SelectedItemListMeat);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClickedMeat();
                    }
                });
        return builder.create();
    }
}
