package com.example.prachistodoapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

//Responsible for display data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.Viewholder>
{
    public interface OnClickListener
    {
        void onItemClicked(int position);
    }
    public interface OnLongClickListener
    {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener)
    {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // Wrap it inside the View Holder and return it
        return new Viewholder(todoView);
    }

    // responsible for binding a data to a particular View Holder
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position)
    {
        // Grab the items at the position
        String item = items.get(position);

        // Bind the items to the specified View Holder
        holder.bind(item);
    }

    // Tell the Recycler View how many items in the list
    @Override
    public int getItemCount()
    {
        return items.size();
    }

    //Container to provide easy access to views represent each row of the list
    class Viewholder extends RecyclerView.ViewHolder
    {
        TextView tvItem;

        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update the view inside of the view holder with this data
        public void bind(String item)
        {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener()
            {
                public void onClick (View v)
                {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            
            tvItem.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    // Notify the listener which position was long clicked
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
