package com.example.deepaks.krishiseva.util;

import android.support.annotation.NonNull;

import com.example.deepaks.krishiseva.bean.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUserUtils {

    /**
     * @return teh list of all users
     * @author deepaks
     */
    public static void getAllUserList(final SignUpListener signUpListener) {
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference("user");
        final List<User> userList = new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    userList.add(user);
                }
                signUpListener.getAllUsers(userList);
                db.removeEventListener(this);
                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                signUpListener.getAllUsers(userList);
            }
        });
    }

    /**
     * @param user the user to insert on firebase db
     * @author deepaks
     * @description method to insert the user in firebase db
     */
    public static void insertUser(User user) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("user");
        String userId = db.push().getKey();
        db.child(userId).setValue(user);
    }
}
