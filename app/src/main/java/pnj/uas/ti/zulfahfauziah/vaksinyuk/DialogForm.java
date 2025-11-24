package pnj.uas.ti.zulfahfauziah.vaksinyuk;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
    String nik, nama, alamat, nohp, key, pilih;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String nik, String nama, String alamat, String nohp, String key, String pilih) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
        this.key = key;
        this.pilih = pilih;
    }

    TextView edt_nik;
    TextView edt_nama;
    TextView edt_alamat;
    TextView edt_nohp;
    Button btn_save;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.input,container,false);
        edt_nik = view.findViewById(R.id.edt_nik);
        edt_nama = view.findViewById(R.id.edt_nama);
        edt_alamat = view.findViewById(R.id.edt_alamat);
        edt_nohp = view.findViewById(R.id.edt_nohp);
        btn_save = view.findViewById(R.id.btn_save);

        edt_nik.setText(nik);
        edt_nama.setText(nama);
        edt_alamat.setText(alamat);
        edt_nohp.setText(nohp);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nik = edt_nik.getText().toString();
                String nama = edt_nama.getText().toString();
                String alamat = edt_alamat.getText().toString();
                String nohp = edt_nohp.getText().toString();
                if (TextUtils.isEmpty(nik)) {
                    input((EditText) edt_nik, "NIK");
                } else if (TextUtils.isEmpty(nama)) {
                    input((EditText) edt_nama, "Nama");
                } else if (TextUtils.isEmpty(nohp)) {
                    input((EditText) edt_nohp, "Nomor HP");
                } else if (TextUtils.isEmpty(alamat)) {
                    input((EditText) edt_alamat, "Alamat");
                } else {
                    if(pilih.equals("Tambah")){
                        database.child("Data").push().setValue(new PesertaVaksinasi(nama,nik, alamat, nohp)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(view.getContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(view.getContext(), "Data Gagal Tersimpan", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }else if(pilih.equals("Ubah")){
                        database.child("Data").child(key).setValue(new PesertaVaksinasi(nama,nik, alamat, nohp)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(view.getContext(), "Data Updated", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(view.getContext(), "Failed Update", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                }
            }
            });
        return view;

    }
    public void onStart() {

        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    }
    private void input(EditText txt, String s){
        txt.setError("tidak boleh kosong");
        txt.requestFocus();
    }
        private void input datauser(EditText txt, String s){
        txt.setError("tidak boleh kosong");
        txt.requestFocus();
}

}
    private void input transaksi usser(EditText txt, String s){
        txt.setError("tidak boleh kosong");
        txt.requestFocus();
    }