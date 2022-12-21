package com.example.wagba.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity(tableName = "user_table")
    public class user_table {

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "use_id")
        private String use_id;
        @NonNull
        @ColumnInfo(name = "use_name")
        private String use_name;

        @NonNull
        @ColumnInfo(name = "email")
        private String use_email;

        public user_table(@NonNull String use_id , @NonNull String use_name, @NonNull String use_email) {
            this.use_id = use_id;
            this.use_name = use_name;
            this.use_email = use_email;


        }

        @NonNull
        public String getUse_id() {
            return use_id;
        }

        @NonNull
        public String getUse_name() {
            return use_name;
        }

        @NonNull
        public String getUse_email() {
            return use_email;
        }
    }

