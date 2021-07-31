package wallet.currency.searchview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import wallet.currency.searchview.util.Util;

public class UsersitemsAdapter extends RecyclerView.Adapter<UsersitemsAdapter.FollowerViewHolder> implements Filterable {

    private static final String TAG = UsersitemsAdapter.class.getSimpleName();

    private Context context;
    private List<UsersItemsModal> usersItemsModals;
    private List<UsersItemsModal> filteredList;

    public UsersitemsAdapter(Context context, List<UsersItemsModal> usersItemsModals) {
        this.context = context;
        this.usersItemsModals = usersItemsModals;
        this.filteredList = usersItemsModals;
    }

    public static class FollowerViewHolder extends RecyclerView.ViewHolder{
        public TextView itemname, itemprice,itemdiscount,itemTotal;
        public ImageView item_pic;
        String item_id;
        CardView cardView;

        public FollowerViewHolder(final View view) {
            super(view);
            itemname = view.findViewById(R.id.itemname);
            itemprice= view.findViewById(R.id.item_price);
            itemTotal= view.findViewById(R.id.itemTotal);
            itemdiscount = view.findViewById(R.id.item_discount);
            item_pic = view.findViewById(R.id.useritem_pic);
            cardView = view.findViewById(R.id.cardView);

        }
    }

    @Override
    public FollowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_items, parent, false);
        return new FollowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FollowerViewHolder holder, int position) {
        if (filteredList.size() > 0) {
            UsersItemsModal album = filteredList.get(position);
            if (album != null) {
                holder.itemname.setText(album.getItems_name());
                holder.itemprice.setText(album.getItems_price());
                holder.itemTotal.setText(album.getItems_total());
                holder.itemdiscount.setText(album.getItems_discount());
                holder.item_id = album.getItems_id();
                Picasso.Builder builder = new Picasso.Builder(context);
                Picasso picasso = builder.build();
                picasso.load(album.getItems_pic()).placeholder(R.drawable.ic_launcher_background).fit().centerInside().into(holder.item_pic);
                holder.itemprice.setPaintFlags(holder.itemprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                holder.cardView.setOnClickListener(v -> onCardClick(holder.item_id));
            }
        }
    }

    private void onCardClick(String item_id) {
      //  SharedHelper.putKey(context, "item_id", item_id);
        }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = usersItemsModals;
                } else {
                    filteredList = Util.searchFollowersFilter(usersItemsModals, charString);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<UsersItemsModal>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return filteredList == null ? 0 : filteredList.size();
    }

}
