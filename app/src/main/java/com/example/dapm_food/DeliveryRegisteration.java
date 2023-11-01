package com.example.dapm_food;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

public class DeliveryRegisteration extends AppCompatActivity {

    String[] TP_HCM = {"Q1", "Q2", "Q3","Q4","Q5","Q6","Q7","Q8","Q9","Q10","Q11","Q12","Q.TânBình","Q.BìnhTân","Q.PhúNhuận","Q.BìnhThạnh"};
    String[] TP_HàNội = {"Q.HoànKiếm", "Q.BaĐình", "Q.ĐốngĐa"};
    String[] TiềnGiang = {"H.ChợGạo", "H.TânPhúĐông", "H.GòCông"};


    String[] Q1 = {"P. Bến Nghé", "P.Bến Thành", "P.Cô Giang", "P.Cầu Kho", "P.Cầu Ông Lãnh", "P.Nguyễn Cư Trinh", "P.Nguyễn Thái Bình", "P.Phạm Ngũ Lão",
            "P.Tân Định", "P.Đa Kao"};


    String[] Q2 = {"P.An Khánh", "P.An Lợi Đông", " P.An Phú", "P.Bình An", "P.Bình Khánh","P.Bình Trưng Đông","P.Bình Trưng Tây","P.Cát Lái","P.Thạnh Mỹ Lợi","P.Thảo Điền","P.Thủ Thiêm"};
    String[] Q3 = {"P.1", "P.2", "P.3", "P.4", "P.5", "P.9", "P.10", "P.11", "P.12", "P.13", "P.14"};

    TextInputLayout Fname, Lname, Email, Pass, cfpass, mobileno, houseno, area, postcode;
    Spinner statespin, Cityspin, Suburban;
    Button signup, Emaill, Phone;
    CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname;
    String lname;
    String emailid;
    String password;
    String confirmpassword;
    String mobile;
    String house;
    String Area;
    String Postcode;
    String role = "DeliveryPerson";
    String statee;
    String cityy;
    String suburban;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_registeration);

        Fname = (TextInputLayout) findViewById(R.id.Firstname);
        Lname = (TextInputLayout) findViewById(R.id.Lastname);
        Email = (TextInputLayout) findViewById(R.id.Email);
        Pass = (TextInputLayout) findViewById(R.id.Pwd);
        cfpass = (TextInputLayout) findViewById(R.id.Cpass);
        mobileno = (TextInputLayout) findViewById(R.id.Mobileno);
        houseno = (TextInputLayout) findViewById(R.id.houseNo);
        area = (TextInputLayout) findViewById(R.id.Area);
        postcode = (TextInputLayout) findViewById(R.id.Postcode);
        statespin = (Spinner) findViewById(R.id.Statee);
        Cityspin = (Spinner) findViewById(R.id.Citys);
        Suburban = (Spinner) findViewById(R.id.Suburban);
        signup = (Button) findViewById(R.id.Signup);
        Emaill = (Button) findViewById(R.id.emaill);
        Phone = (Button) findViewById(R.id.phone);
        Cpp = (CountryCodePicker) findViewById(R.id.CountryCode);
        Cpp.setDefaultCountryUsingNameCode("VN");
        Cpp.resetToDefaultCountry();

        statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                statee = value.toString().trim();
                if (statee.equals("TP_HCM")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : TP_HCM) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DeliveryRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("TP_HàNội")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : TP_HàNội) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DeliveryRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("TiềnGiang")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : TiềnGiang) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DeliveryRegisteration.this, android.R.layout.simple_spinner_item, list);

                    Cityspin.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Cityspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                cityy = value.toString().trim();
                if (cityy.equals("Q1")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Q1) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DeliveryRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }

                if (cityy.equals("Q2")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Q2) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DeliveryRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }

                if (cityy.equals("Q3")) {
                    ArrayList<String> listt = new ArrayList<>();
                    for (String text : Q3) {
                        listt.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DeliveryRegisteration.this, android.R.layout.simple_spinner_item, listt);
                    Suburban.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Suburban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                suburban = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        databaseReference = firebaseDatabase.getInstance().getReference("DeliveryPerson");
        FAuth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = Fname.getEditText().getText().toString().trim();
                lname = Lname.getEditText().getText().toString().trim();
                emailid = Email.getEditText().getText().toString().trim();
                mobile = mobileno.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();
                confirmpassword = cfpass.getEditText().getText().toString().trim();
                Area = area.getEditText().getText().toString().trim();
                house = houseno.getEditText().getText().toString().trim();
                Postcode = postcode.getEditText().getText().toString().trim();


                if (isValid()) {

                    final ProgressDialog mDialog = new ProgressDialog(DeliveryRegisteration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Đang đăng kí, vui lòng đợi !");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(emailid, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("Role", role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        HashMap<String, String> hashMappp = new HashMap<>();
                                        hashMappp.put("Area", Area);
                                        hashMappp.put("City", cityy);
                                        hashMappp.put("ConfirmPassword", confirmpassword);
                                        hashMappp.put("EmailID", emailid);
                                        hashMappp.put("Fname", fname);
                                        hashMappp.put("House", house);
                                        hashMappp.put("Lname", lname);
                                        hashMappp.put("Mobile", mobile);
                                        hashMappp.put("Password", password);
                                        hashMappp.put("Postcode", Postcode);
                                        hashMappp.put("State", statee);
                                        hashMappp.put("Suburban", suburban);
                                        firebaseDatabase.getInstance().getReference("DeliveryPerson")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMappp).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        mDialog.dismiss();

                                                        FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(DeliveryRegisteration.this);
                                                                    builder.setMessage("Đăng kí thành công, hãy xác nhận email của bạn");
                                                                    builder.setCancelable(false);
                                                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                            dialog.dismiss();

                                                                            String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobile;
                                                                            Intent b = new Intent(DeliveryRegisteration.this, ChefVerifyPhone.class);
                                                                            b.putExtra("phonenumber", phonenumber);
                                                                            startActivity(b);

                                                                        }
                                                                    });
                                                                    AlertDialog alert = builder.create();
                                                                    alert.show();

                                                                } else {
                                                                    mDialog.dismiss();
                                                                    ReusableCodeForAll.ShowAlert(DeliveryRegisteration.this, "Error", task.getException().getMessage());

                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                    }
                                });


                            } else {
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(DeliveryRegisteration.this, "Lỗi", task.getException().getMessage());
                            }

                        }
                    });

                }

            }

        });

        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DeliveryRegisteration.this, ChefLoginEmail.class);
                startActivity(i);
                finish();
            }
        });

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e = new Intent(DeliveryRegisteration.this, Chefloginphone.class);
                startActivity(e);
                finish();
            }
        });


    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        mobileno.setErrorEnabled(false);
        mobileno.setError("");
        cfpass.setErrorEnabled(false);
        cfpass.setError("");
        area.setErrorEnabled(false);
        area.setError("");
        houseno.setErrorEnabled(false);
        houseno.setError("");
        postcode.setErrorEnabled(false);
        postcode.setError("");

        boolean isValidname = false, isValidemail = false, isvalidpassword = false, isvalidconfirmpassword = false, isvalid = false, isvalidmobileno = false, isvalidlname = false, isvalidhousestreetno = false, isvalidarea = false, isvalidpostcode = false;
        if (TextUtils.isEmpty(fname)) {
            Fname.setErrorEnabled(true);
            Fname.setError("Họ và tên lót của bạn không được để trống");
        } else {
            isValidname = true;
        }
        if (TextUtils.isEmpty(lname)) {
            Lname.setErrorEnabled(true);
            Lname.setError("Tên của bạn không được để trống");
        } else {
            isvalidlname = true;
        }
        if (TextUtils.isEmpty(emailid)) {
            Email.setErrorEnabled(true);
            Email.setError("Email không được để trống");
        } else {
            if (emailid.matches(emailpattern)) {
                isValidemail = true;
            } else {
                Email.setErrorEnabled(true);
                Email.setError("Hãy nhập địa chỉ chính xác !!!");
            }

        }
        if (TextUtils.isEmpty(password)) {
            Pass.setErrorEnabled(true);
            Pass.setError("Mật khẩu không được để trống ");
        } else {
            if (password.length() < 6) {
                Pass.setErrorEnabled(true);
                Pass.setError("Mật khẩu quá yếu");
            } else {
                isvalidpassword = true;
            }
        }
        if (TextUtils.isEmpty(confirmpassword)) {
            cfpass.setErrorEnabled(true);
            cfpass.setError("Xác nhận mật khẩu không được để trống");
        } else {
            if (!password.equals(confirmpassword)) {
                Pass.setErrorEnabled(true);
                Pass.setError("Mật khẩu không khớp");
            } else {
                isvalidconfirmpassword = true;
            }
        }
        if (TextUtils.isEmpty(mobile)) {
            mobileno.setErrorEnabled(true);
            mobileno.setError("Số điện thoại không được để trống");
        } else {
            if (mobile.length() < 10) {
                mobileno.setErrorEnabled(true);
                mobileno.setError("Số điện thoại không tồn tại");
            } else {
                isvalidmobileno = true;
            }
        }
        if (TextUtils.isEmpty(house)) {
            houseno.setErrorEnabled(true);
            houseno.setError("Trường này không được để trống");
        } else {
            isvalidhousestreetno = true;
        }
        if (TextUtils.isEmpty(Area)) {
            area.setErrorEnabled(true);
            area.setError("Trường này không được để trống");
        } else {
            isvalidarea = true;
        }
        if (TextUtils.isEmpty(Postcode)) {
            postcode.setErrorEnabled(true);
            postcode.setError("Trường này không được để trống");
        } else {
            isvalidpostcode = true;
        }

        isvalid = (isValidname && isvalidpostcode && isvalidlname && isValidemail && isvalidconfirmpassword && isvalidpassword && isvalidmobileno && isvalidarea && isvalidhousestreetno) ? true : false;
        return isvalid;
    }


}



