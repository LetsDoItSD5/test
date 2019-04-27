package com.sda5.walletdroid.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sda5.walletdroid.R;
import com.sda5.walletdroid.models.Account;

import java.util.ArrayList;
import java.util.List;


public class AccountAdapterGroupDetail extends ArrayAdapter<Account> {
    private Context mContext;
    private final List<Account> accounts;
    private final boolean showCheckboxes;
    private final List<String> selectedAccountIDList = new ArrayList<>();


    public AccountAdapterGroupDetail(Context context, ArrayList<Account> accounts, boolean showCheckboxes) {
        super(context, 0, accounts);
        mContext = context;
        this.accounts = accounts;
        this.showCheckboxes = showCheckboxes;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_account_item_group_detail, parent, false);


        Account account = accounts.get(position);

        TextView debt = listItem.findViewById(R.id.textview_account_debt_gd);
        debt.setText("-50 SEK");

        final CheckBox checkBoxAccount = listItem.findViewById(R.id.checkbox_account_item_gd);
        checkBoxAccount.setTag(account.getId());

        TextView textViewAccount = listItem.findViewById(R.id.textview_account_item_gd);
        if (account.isInternalAccount()) {
            textViewAccount.setText(account.getOwnerName());
        } else {
            textViewAccount.setText("Name: " + account.getOwnerName() + "\n" + "Email:" + account.getEmail());
            textViewAccount.setTypeface(null, Typeface.ITALIC);
        }

        textViewAccount.setTag(account.getId());


        if (showCheckboxes) {
            checkBoxAccount.setVisibility(View.VISIBLE);
        } else {
            checkBoxAccount.setVisibility(View.GONE);
        }

        View finalListItem = listItem;
        checkBoxAccount.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String selectedAccountID = checkBoxAccount.getTag().toString();
            if (isChecked) {
                selectedAccountIDList.add(selectedAccountID);
                finalListItem.setBackgroundColor(Color.parseColor("#FFFFE0"));
            } else {
                selectedAccountIDList.remove(selectedAccountID);
                finalListItem.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });

        return listItem;
    }

    public List<String> getSelectedAccountIDList() {
        return selectedAccountIDList;
    }


    public void addSelectedAccountId(String accountId) {
        this.selectedAccountIDList.add(accountId);
    }
}
