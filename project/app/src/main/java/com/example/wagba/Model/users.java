package com.example.wagba.Model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity(tableName = "users")
    public class users  {

        public users(@NonNull String user_id, @NonNull String user_name, @NonNull String user_email, @NonNull String phone_no) {
            this.user_id = user_id;
            this.user_name = user_name;
            this.user_email = user_email;
            this.phone_no = phone_no;
        }

        public users() {
        }

        public users(@NonNull String user_name, @NonNull String user_email, @NonNull String phone_no) {
            this.user_name = user_name;
            this.user_email = user_email;
            this.phone_no = phone_no;
        }

        @NonNull
        @PrimaryKey
        @ColumnInfo(name = "user_id")
        private String user_id;
        @NonNull
        @ColumnInfo(name = "use_name")
        private String user_name;

        @NonNull
        @ColumnInfo(name = "email")
        private String user_email;

        @NonNull
        @ColumnInfo(name = "phone_no")
        private String phone_no;

        @NonNull
        public String getPhone_no() {
            return phone_no;
        }

        public void setPhone_no(@NonNull String phone_no) {
            this.phone_no = phone_no;
        }

        @NonNull
        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(@NonNull String user_id) {
            this.user_id = user_id;
        }

        @NonNull
        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(@NonNull String user_name) {
            this.user_name = user_name;
        }

        @NonNull
        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(@NonNull String user_email) {
            this.user_email = user_email;
        }

        @Override
        public String toString() {
            return "users{" +
                    "user_id='" + user_id + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", user_email='" + user_email + '\'' +
                    ", phone_no='" + phone_no + '\'' +
                    '}';
        }
    }

