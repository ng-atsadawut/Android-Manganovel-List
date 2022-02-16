package com.example.manganovellist.uitils;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.manganovellist.R;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    List<String> book_number;
    List<String> flag;
    List<String> price;

    Context context;


    public CustomAdapter(Context context, List<String> book_number,List<String> flag,List<String> price) {
        this.context = context;
        this.book_number = book_number;
        this.flag = flag;
        this.price = price;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // set the data in items
        if(flag.get(position).equals("Y")){
            holder.name.setText("Y");
            holder.name.setBackgroundColor(Color.rgb(186,218,0));
        }else{
        Log.d("CustomAdapter X person ", String.valueOf(book_number));
            holder.name.setText(book_number.get(position));
            holder.name.setBackgroundColor(Color.rgb(208,208,225));
        }

//        holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open another activity on item click
                if(holder.name.getText() == "Y"){
                    holder.name.setText(book_number.get(position));
                    flag.set(position,"N");
                    holder.name.setBackgroundColor(Color.rgb(208,208,225));
                }else{
                    holder.name.setText("Y");
                    flag.set(position,"Y");
                    holder.name.setBackgroundColor(Color.rgb(186,218,0));
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return book_number.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
//        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            name = (Button) itemView.findViewById(R.id.name);
//            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }

}
