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

public class MultipleSelectIngredientDesserts extends DialogFragment {

    public interface onMultiChoiceListenerDesserts {
        void onPositiveButtonClickedDesserts(String[] list, ArrayList<String> SelectedItemListDesserts);

        void onNegativeButtonClickedDesserts();
    }

    MultipleSelectIngredientDesserts.onMultiChoiceListenerDesserts mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (MultipleSelectIngredientDesserts.onMultiChoiceListenerDesserts) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString() + " onMultiChoiceListener must Implemented");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        ArrayList<String> SelectedItemListDesserts = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] listDesserts = getActivity().getResources().getStringArray(R.array.Desserts);
        builder.setTitle("\uD83C\uDF70 Desserts & Sweet snacks")
                .setMultiChoiceItems(listDesserts, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            SelectedItemListDesserts.add(listDesserts[i]);
                        } else {
                            SelectedItemListDesserts.remove(listDesserts[i]);
                        }
                    }
                })
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClickedDesserts(listDesserts, SelectedItemListDesserts);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClickedDesserts();
                    }
                });
        return builder.create();
    }
}
