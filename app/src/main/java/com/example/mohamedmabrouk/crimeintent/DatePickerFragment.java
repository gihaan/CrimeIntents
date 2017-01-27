package com.example.mohamedmabrouk.crimeintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Mohamed Mabrouk on 30/03/2016.
 */
public class DatePickerFragment extends DialogFragment {
    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "com.example.mohamedmabrouk.crimeintent.date";
    private DatePicker mDatePicker;


    //******** for return DatePicker Fragment *******//
    public static DatePickerFragment NewInstence(Date date){
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment=new DatePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    //***  for sending result from date  picker ***//
    public void SendResult(int requestCode, Date date){
if (getTargetFragment()==null){
    return ;
}
        Intent intent=new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), requestCode, intent);

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date=(Date)getArguments().getSerializable(ARG_DATE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        View v= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);
        mDatePicker=(DatePicker)v.findViewById(R.id.dailog_date_date_picker);
        mDatePicker.init(year, month, day, null);
        return new AlertDialog.Builder(getActivity()).
                setView(v).
                setTitle("Date of crime:").
                setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                             int year=mDatePicker.getYear();
                             int month=mDatePicker.getMonth();
                             int day=mDatePicker.getDayOfMonth();
                             Date date=new GregorianCalendar(year,month,day).getTime();
                                SendResult(Activity.RESULT_OK, date);

                            }
                        }


                ).create();
    }
}
