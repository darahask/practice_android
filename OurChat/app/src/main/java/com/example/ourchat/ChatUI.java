package com.example.ourchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Objects;

public class ChatUI extends AppCompatActivity {

    private static final int RC_PHOTO_PICKER =12345 ;
    private static final int DEFAULT_MSG_LENGTH_LIMIT =1000 ;
    Button button;
    ImageButton imageButton;
    EditText editText;
    ListView listView;
    String chatConnection,chatConnection2;
    DatabaseReference reference;
    StorageReference storageReference;
    MessageAdapter messageAdapter;
    String mUsername;
    ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ui);

        chatConnection = getIntent().getStringExtra("TransactionId");
        chatConnection2 = getIntent().getStringExtra("TransactionId2");
        button = findViewById(R.id.button);
        imageButton = findViewById(R.id.img_button);
        editText = findViewById(R.id.edit_text);
        listView = findViewById(R.id.list_view2);
        ArrayList<Message> arrayList = new ArrayList<>();
        arrayList.add(new Message("Welcome To Our Chat","OurChat",null));
        messageAdapter = new MessageAdapter(this,0,arrayList);
        listView.setAdapter(messageAdapter);

        //STORAGE_DATABASE_LOADING
        mUsername = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference().child("chat_photos");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(chatConnection)){
                    reference = reference.child(chatConnection);
                    reference.addChildEventListener(childEventListener);
                }
                else if(dataSnapshot.hasChild(chatConnection2)){
                    reference = reference.child(chatConnection2);
                    reference.addChildEventListener(childEventListener);
                }else{
                    reference = reference.child(chatConnection);
                    reference.addChildEventListener(childEventListener);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //DATABASE_LOADING_OVER
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                messageAdapter.add(message);
                scrollMyListViewToBottom();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Complete action using"),RC_PHOTO_PICKER);

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click
                Message message = new Message(editText.getText().toString(),mUsername,null);
                reference.push().setValue(message);
                editText.setText("");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == RC_PHOTO_PICKER){
            assert data != null;
            Uri imageUri = data.getData();
            StorageReference photoReference = storageReference.child(imageUri.getLastPathSegment());
            photoReference.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Message message = new Message(null,mUsername,uri.toString());
                            reference.push().setValue(message);
                        }
                    });
                }
            });
        }
    }

    private void scrollMyListViewToBottom(){
        listView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                listView.setSelection(messageAdapter.getCount() - 1);
            }
        });
    }

}
