package com.example.brainics;

import android.provider.BaseColumns;

public class schedulerContract {

    private schedulerContract() {}

    public static final class schedulerEntry implements BaseColumns {
        public static final String TABLE_NAME = "schedulerList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";

    }
}
