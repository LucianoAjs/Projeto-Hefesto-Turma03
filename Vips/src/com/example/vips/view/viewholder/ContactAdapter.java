package com.example.vips.view.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.PhonebookManager;
import com.example.vips.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Arrays; 

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ProductViewHolder> {

    private PhonebookManager phonebook;
    private Context mCtx;
    private List<String> contactList;


    public ContactAdapter(Context mCtx, List<String> contactList, PhonebookManager phonebook) {
        this.mCtx = mCtx;
        this.contactList = contactList;
        this.phonebook = phonebook;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(com.example.vips.R.layout.custom_contact_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        final String contact = contactList.get(position);

        //binding the data with the viewholder views
        holder.textViewNumber.setText(contact);

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateContact(contact);
            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Você tem certeza?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContactAdapter.this.phonebook.deleteNumber(contact);
                        reloadContactsFromDatabase(); //Reload List
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    void realoadGetOneFromDatabase(String contact) {
        String contactNumber[] = new String[1];
        contactNumber[0] = this.phonebook.getNumber(contact);
        if(contactNumber[0].equals("")) {
            Toast.makeText(mCtx, "Número não encontrado.", Toast.LENGTH_SHORT).show();
        } else {
            contactList = Arrays.asList(contactNumber[0]);
            notifyDataSetChanged();
        }
    }

    void reloadContactsFromDatabase() {
        contactList = Arrays.asList(this.phonebook.getAllNumbers());
        notifyDataSetChanged();
    }
    private void updateContact(final String contact) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_contact, null);
        builder.setView(view);

        final EditText editTextNumber = view.findViewById(R.id.editTextNumber);

        editTextNumber.setText(contact.toString());

        final AlertDialog dialog = builder.create();
        dialog.show();

        // CREATE METHOD FOR EDIT THE FORM
        view.findViewById(R.id.buttonUpdateEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editTextNumber.getText().toString().trim();

                if (number.isEmpty()) {
                    editTextNumber.setError("O número não pode ficar em branco");
                    editTextNumber.requestFocus();
                    return;
                }

                ContactAdapter.this.phonebook.updateNumber(contact, number);

                Toast.makeText(mCtx, "Número atualizado!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                notifyDataSetChanged();
                reloadContactsFromDatabase();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNumber;
        ImageView editbtn, deletebtn;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewNumber = itemView.findViewById(R.id.textViewNumber);

            deletebtn = itemView.findViewById(R.id.buttonDeleteContact);
            editbtn = itemView.findViewById(R.id.buttonEditstudent);
        }
    }
}
