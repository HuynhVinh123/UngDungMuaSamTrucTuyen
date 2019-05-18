package com.example.huynhvinh.applazada_java.view.DangNhap.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynhvinh.applazada_java.CustomView.FButton;
import com.example.huynhvinh.applazada_java.Presenter.DangNhap_DangKy.PresenterLogicDangKy;
import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.model.DangNhap_DangKy.DangNhapModel;
import com.example.huynhvinh.applazada_java.model.ObjectClass.NhanVien;
import com.example.huynhvinh.applazada_java.view.QuenMatKhau.QuenMatKhauActivity;
import com.example.huynhvinh.applazada_java.view.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static android.app.Activity.RESULT_OK;

public class FragmentDangNhap extends Fragment implements FirebaseAuth.AuthStateListener,View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    Button btnDangNhapGoogle,btnDangNhapFacebook;
    GoogleApiClient signInApi;
    FirebaseAuth firebaseAuth;
    FButton btnDangNhap;
    DangNhapModel dangNhapModel;
    EditText edTenDN,edMatKhau;
    PresenterLogicDangKy presenterLogicDangKy;
    TextView txtQuenMatKhauDN;

    boolean KiemTraDN  =false;

    private CallbackManager callbackManager;

    public static int CODE_DANG_NHAP_GOOGLE = 123;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        View view = inflater.inflate(R.layout.layout_fragment_dangnhap,container,false);

        txtQuenMatKhauDN = (TextView) view.findViewById(R.id.txtQuenMatKhauDangNhap);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhauDN);
        edTenDN = (EditText) view.findViewById(R.id.edDiaChiEmailDN);
        btnDangNhap = (FButton) view.findViewById(R.id.btnDangNhap);
        btnDangNhapGoogle = (Button) view.findViewById(R.id.btnDangNhapGoogle);
        btnDangNhapFacebook = (Button) view.findViewById(R.id.btnDangNhapFacebook);

        presenterLogicDangKy = new PresenterLogicDangKy();

       // printKeyHash();

        // Đăng nhập bằng Facebook

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                       Intent iTrangChu = new Intent(getContext(),TrangChuActivity.class);
                       getActivity().startActivity(iTrangChu);

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException exception) {

                    }
                });
        btnDangNhapFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("email","public_profile"));
            }
        });

        // Đăng nhập bằng tài khoản đăng ký
        dangNhapModel = new DangNhapModel();

        // Đăng nhập bằng google
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        TaoClientDangNhapGoogle();
        btnDangNhapGoogle.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        txtQuenMatKhauDN.setOnClickListener(this);
        return view;
    }

//    private void printKeyHash(){
//        try {
//            PackageInfo info = getActivity().getPackageManager().getPackageInfo("com.example.huynhvinh.applazada_java", PackageManager.GET_SIGNATURES);
//            for(Signature signature: info.signatures)
//            {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KEYHASH", Base64.encodeToString(md.digest(),Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//    }

    private  void TaoClientDangNhapGoogle()
    {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)  // Khởi tạo để lấy thông tin tài khoảng google
                .requestEmail().requestIdToken(getString(R.string.default_web_client_id))  // để chứng thực google
                .requestProfile()
                .build();

        signInApi = new GoogleApiClient.Builder(getContext())   // Khởi tạo API cho google để có thể kết nối
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        KiemTraDN = false;
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnDangNhapGoogle:
                DangNhapGoogle(signInApi);
                break;
            case R.id.btnDangNhap:
                String TenDN = edTenDN.getText().toString();
                String MatKhau = edMatKhau.getText().toString();
                boolean kiemtra =  dangNhapModel.KiemTraDangNhap(getActivity(),TenDN,MatKhau);
                if(kiemtra)
                {
                    Intent iTrangchu = new Intent(getActivity(), TrangChuActivity.class);
                    getActivity().startActivity(iTrangchu);
                }else
                {
                    Toast.makeText(getActivity(), "Sai tên toàn khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtQuenMatKhauDangNhap:
                Intent iQuenMatKhau = new Intent(getActivity(), QuenMatKhauActivity.class);
                startActivity(iQuenMatKhau);
                break;
        }

    }

    // Mở form đăng nhập bằng google
    private void DangNhapGoogle(GoogleApiClient apiClient)
    {
        Intent iDNGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(iDNGoogle,CODE_DANG_NHAP_GOOGLE);
    }
    // end Mở form đăng nhập bằng google

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_DANG_NHAP_GOOGLE)
        {
            if(resultCode == RESULT_OK)
            {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data); // lấy thông tin người dùng đăng nhập được
                GoogleSignInAccount account = result.getSignInAccount(); // Lấy tài khoản google
                String tokenID = account.getIdToken();      // Lấy tokenID thông qua account
                ChungThucDangNhapFirebase(tokenID);
            }
        }
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void ChungThucDangNhapFirebase(String tokenID){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID,null); // Tạo chứng thực cho google thông qua provider
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });

    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user   = firebaseAuth.getCurrentUser();        // Lấy user vừa đăng nhập được
        if(user!=null) {
            DangNhapModel dangNhapModel = new DangNhapModel();
            dangNhapModel.CapNhatCachedDangNhap(getActivity(),user.getDisplayName(),user.getEmail(),user.getUid());
            dangNhapModel.CapNhatCacheKiemTraDangNhap(getActivity(),"0");

            boolean kiemtraNV = presenterLogicDangKy.KiemTraNVTonTai(user.getUid());

            if(!kiemtraNV)
            {
                // Thực hiện việc đăng ký thông tin nhân viên lên bảng nhân viên
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTenNV(user.getDisplayName());
                nhanVien.setTenDN(user.getEmail());
                nhanVien.setIdDangNhap(user.getUid());
                nhanVien.setMaLoaiNV(2);
                boolean kiemtra = presenterLogicDangKy.DangKyByGoogkeAndFacebook(nhanVien);
                if(kiemtra)
                {
                    Toast.makeText(getContext(),"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                }


            }
            Intent iTrangchu = new Intent(getActivity(), TrangChuActivity.class);
            getActivity().startActivity(iTrangchu);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Toast.makeText(getContext(), "Connection fail !", Toast.LENGTH_SHORT).show();

    }
/*
    // Login By Facebook
    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken)
        {
            if(currentAccessToken==null)
            {
                txtName.setText("");
                txtEmail.setText("");
                circleImageView.setImageResource(0);
                Toast.makeText(MainActivity.this,"User Logged out",Toast.LENGTH_LONG).show();
            }
            else {
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/"+id+ "/picture?type=normal";

                    Log.d("kiemtra",first_name + " - " +email);

                    txtEmail.setText(email);
                    txtName.setText(first_name +" "+last_name);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    Glide.with(MainActivity.this).load(image_url).into(circleImageView);

                    logoutButton.setVisibility(View.VISIBLE);
                    logoutButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LoginManager.getInstance().logOut();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }
*/
}