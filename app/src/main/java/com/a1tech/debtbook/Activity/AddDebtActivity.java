package com.a1tech.debtbook.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.a1tech.debtbook.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class AddDebtActivity extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 22;
    private final String[] amount = {"шт", "кг", "литр"};
    private final String[] currency = {"SUM", "$", "₽"};
    private final String TAG = "AddDebtActivity";
    private ImageView ivBack, ivDebterPhoto;
    private TextView tvDebterPhoto;
    private EditText etDebterName, etDebterPhone;
    private EditText etDebtItemName, edItemAmount, etItemPrice;
    private TextView tvDDItemAmount, tvDDPrice, actionBarText;
    private Button btnDebtorSave, btnDebtSave;
    private ListPopupWindow mListPopupWindowAmount, mListPopupWindowPrice;
    private ConstraintLayout addDebtor, addDebt;
    private Uri filePath;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private String urlImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);

        init();
        setOnClicks();
        setDropDownForAmount();
        setDropDownForPrice();
        setViewByStatus();

    }

    private void init() {
        ivBack = findViewById(R.id.iv_back);
        ivDebterPhoto = findViewById(R.id.iv_debtor_photo);
        tvDebterPhoto = findViewById(R.id.tv_debter_photo);
        etDebterName = findViewById(R.id.et_debter_name);
        etDebterPhone = findViewById(R.id.et_debter_phone);
        etDebtItemName = findViewById(R.id.et_debt_item_name);
        edItemAmount = findViewById(R.id.ed_item_amount);
        etItemPrice = findViewById(R.id.et_item_price);
        tvDDPrice = findViewById(R.id.tv_dd_price);
        tvDDItemAmount = findViewById(R.id.tv_dd_item_amount);
        btnDebtorSave = findViewById(R.id.btn_debtor_save);
        btnDebtSave = findViewById(R.id.btn_debt_save);
        addDebt = findViewById(R.id.cl_add_debt);
        addDebtor = findViewById(R.id.cl_add_debtor);
        actionBarText = findViewById(R.id.action_bar_txt);
        mListPopupWindowAmount = new ListPopupWindow(this);
        mListPopupWindowPrice = new ListPopupWindow(this);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }

    private void setViewByStatus() {
        Bundle arguments = getIntent().getExtras();
        int status = arguments.getInt("add");

        if (status == 0) {
            addDebt.setVisibility(View.GONE);
            addDebtor.setVisibility(View.VISIBLE);
            actionBarText.setText("Новый должник");
        } else if (status == 1) {
            addDebt.setVisibility(View.VISIBLE);
            addDebtor.setVisibility(View.GONE);
            actionBarText.setText("Новый долг");
        }
    }

    private void setOnClicks() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvDebterPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set intent to take photo from gallery
                selectImage();
            }
        });
        btnDebtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnDebtorSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setDropDownForAmount() {
        mListPopupWindowAmount.setAnchorView(tvDDItemAmount);
        mListPopupWindowAmount.setAdapter(new ArrayAdapter(getBaseContext(), R.layout.list_popup_window, amount));

        mListPopupWindowAmount.setModal(true);
        mListPopupWindowAmount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvDDItemAmount.setText(amount[position]);
                mListPopupWindowAmount.dismiss();
            }
        });

        tvDDItemAmount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListPopupWindowAmount.show();
            }
        });
    }

    private void setDropDownForPrice() {
        mListPopupWindowPrice.setAnchorView(tvDDPrice);
        mListPopupWindowPrice.setAdapter(new ArrayAdapter(getBaseContext(), R.layout.list_popup_window, currency));

        mListPopupWindowPrice.setModal(true);
        mListPopupWindowPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvDDPrice.setText(currency[position]);
                mListPopupWindowPrice.dismiss();
            }
        });

        tvDDPrice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListPopupWindowPrice.show();
            }
        });
    }

    // Select Image method
    private void selectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    // UploadImage method
    private void uploadImage() {
        if (filePath != null) {
            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref = storageRef.child("avatars/" + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Image uploaded successfully
                            // Dismiss dialog
                            progressDialog.dismiss();
                            Toast.makeText(AddDebtActivity.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();

                            taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    urlImage = uri.toString();

                                    Picasso.get()
                                            .load(urlImage)
                                            .resize(ivDebterPhoto.getMeasuredWidth(), ivDebterPhoto.getMeasuredHeight())
                                            .centerCrop()
                                            .into(ivDebterPhoto);

                                    Log.e("URL image-> ", uri.toString());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(AddDebtActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "error-> " + e.getMessage());
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        // Progress Listener for loading
                        // percentage on the dialog box
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the Uri of data
            filePath = data.getData();

            uploadImage();
//            try {
//                // Setting image on image view using Bitmap
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                ivDebterPhoto.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

}