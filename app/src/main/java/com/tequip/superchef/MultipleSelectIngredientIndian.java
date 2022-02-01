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

public class MultipleSelectIngredientIndian extends DialogFragment {

    public interface onMultiChoiceListenerIndian{
        void onPositiveButtonClickedIndian(String[] list,ArrayList<String> SelectedItemListIndian );
        void onNegativeButtonClickedIndian();
    }

    onMultiChoiceListenerIndian mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (onMultiChoiceListenerIndian) context;
        } catch (Exception e){
            throw new ClassCastException(getActivity().toString()+" onMultiChoiceListener must Implemented");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        ArrayList<String> SelectedItemListIndian = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] listIndian = getActivity().getResources().getStringArray(R.array.Indian_Ingredients);
        builder.setTitle("\uD83C\uDDEE\uD83C\uDDF3 Indian Ingredients")
                .setMultiChoiceItems(listIndian, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            SelectedItemListIndian.add(listIndian[i]);
                        } else {
                            SelectedItemListIndian.remove(listIndian[i]);
                        }
                    }
                })
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClickedIndian(listIndian,SelectedItemListIndian);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClickedIndian();
                    }
                });
        return builder.create();
    }
}
