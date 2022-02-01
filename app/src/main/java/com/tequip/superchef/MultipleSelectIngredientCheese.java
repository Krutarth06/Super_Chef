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

public class MultipleSelectIngredientCheese extends DialogFragment {

    public interface onMultiChoiceListenerCheese {
        void onPositiveButtonClickedCheese(String[] list, ArrayList<String> SelectedItemListCheese);

        void onNegativeButtonClickedCheese();
    }

    MultipleSelectIngredientCheese.onMultiChoiceListenerCheese mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (MultipleSelectIngredientCheese.onMultiChoiceListenerCheese) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString() + " onMultiChoiceListener must Implemented");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        ArrayList<String> SelectedItemListCheese = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] listCheese = getActivity().getResources().getStringArray(R.array.Cheese);
        builder.setTitle("\uD83E\uDDC0 Cheese")
                .setMultiChoiceItems(listCheese, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            SelectedItemListCheese.add(listCheese[i]);
                        } else {
                            SelectedItemListCheese.remove(listCheese[i]);
                        }
                    }
                })
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClickedCheese(listCheese, SelectedItemListCheese);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClickedCheese();
                    }
                });
        return builder.create();
    }
}
