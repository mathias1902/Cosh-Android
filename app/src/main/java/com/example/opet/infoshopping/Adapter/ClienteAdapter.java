package com.example.opet.infoshopping.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.opet.infoshopping.Model.Cliente;
import com.example.opet.infoshopping.R;

import java.util.List;

/**
 * Created by opet on 19/06/2018.
 */

public class ClienteAdapter extends ArrayAdapter {

    private int resource;
    private List<Cliente> clientes;

    public ClienteAdapter(Context context, int resource, List<Cliente> objects) {
        super(context, resource, objects);
        this.resource = resource;
        clientes = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        View mView = currentView;

        if (mView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource, null, false);
        }


        Cliente cliente = clientes.get(position);

        TextView textID = (TextView) mView.findViewById(R.id.textID);
        TextView textNome = (TextView) mView.findViewById(R.id.textNome);
        TextView textEmail = (TextView) mView.findViewById(R.id.textEmail);
        TextView textSenha = (TextView) mView.findViewById(R.id.textSenha);
        TextView textCPF = (TextView) mView.findViewById(R.id.textCPF);

        if (textID != null) {
            textID.setText(String.valueOf(cliente.getID()));
        }
        if (textNome != null) {
            textNome.setText(cliente.getNome());
        }
        if (textEmail != null) {
            textEmail.setText(cliente.getEmail());
        }
          if (textCPF != null) {
            textCPF.setText(String.valueOf(cliente.getCPF()));
        }
        if (textSenha != null) {
            textSenha.setText(String.valueOf(cliente.getSenha()));
        }
            return mView;
        }
    }

