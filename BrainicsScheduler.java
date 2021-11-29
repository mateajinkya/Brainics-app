package com.example.brainics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class BrainicsScheduler extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount = 0;
    private SQLiteDatabase mDatabase;
    private schedulerAdapter mAdapter;

    Button button;
    Button buttonIncrease;
    Button buttonDecrease;
    // Button add.
    Button button2;
    Button buttonForDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brainics_scheduler);

        schedulerDBHelper dbHelper = new schedulerDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Might need constraint layout manager.
        mAdapter = new schedulerAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        mEditTextName = findViewById(R.id.edittext_name);
        mTextViewAmount = findViewById(R.id.textview_amount);

        button2 = findViewById(R.id.button_add); // Possible cause of nullptrreference.
        button = findViewById(R.id.homebutton);
        buttonIncrease = findViewById(R.id.button_increase);
        buttonDecrease = findViewById(R.id.button_decrease);
        buttonForDate = findViewById(R.id.datebutton);
        
        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase(); // Call later.
            }
        });

        buttonDecrease.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                decrease();
            }
        });
        
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View h) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        buttonForDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.dateview);
        textView.setText(currentDateString);

    }

    private void decrease() {
        if (mAmount > 0) {
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }

    private void increase() {
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));
    }

    private void addItem() {
        // Include "|| mAmount = 0 in your test case.
        if (mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0) {
            return;
        }
        String name = mEditTextName.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(schedulerContract.schedulerEntry.COLUMN_NAME, name);
        cv.put(schedulerContract.schedulerEntry.COLUMN_AMOUNT, mAmount);

        mDatabase.insert(schedulerContract.schedulerEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());
        mEditTextName.getText().clear();

    }

    private void removeItem(long id) {
        mDatabase.delete(schedulerContract.schedulerEntry.TABLE_NAME, schedulerContract.schedulerEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                schedulerContract.schedulerEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                schedulerContract.schedulerEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}