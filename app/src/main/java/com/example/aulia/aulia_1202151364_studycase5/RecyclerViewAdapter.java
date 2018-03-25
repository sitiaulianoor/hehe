package com.example.aulia.aulia_1202151364_studycase5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Aulia on 25/03/2018.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TodoHolder>{


        private Context context;
        private List<ToDoModel> item;
        int id;

        //Constructor
        public RecyclerViewAdapter(Context con, List<ToDoModel> item, int id) {
            this.context = con;
            this.item = item;
            this.id = id;
        }

        @Override
        public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
           TodoHolder hold = new TodoHolder(v);
            return hold;
        }

        @Override
        public void onBindViewHolder(RecyclerViewAdapter.TodoHolder holder, int position) {
            ToDoModel model = item.get(position);
            holder.name.setText(model.getNameTodo());
            holder.description.setText(model.getDescription());
            holder.priority.setText(model.getPriority());
            holder.cView.setCardBackgroundColor(context.getResources().getColor(this.id));
        }

        //Method untuk mendapatkan addtodo dari adapter
        public ToDoModel getItem(int position) {
            return item.get(position);
        }

        @Override
        public int getItemCount() {
            return item.size();
        }

        //Method untuk menghapus item addtodo
        public void remove(int i){
            item.remove(i);
            notifyItemRemoved(i);
            notifyItemRangeChanged(i, item.size());
        }

        public class TodoHolder extends RecyclerView.ViewHolder {
            public TextView name, description, priority;
            public CardView cView;
            public TodoHolder(View itemView) {
                super(itemView);
                //Menginisialisasikan objek
                name = itemView.findViewById(R.id.todoname);
                description = itemView.findViewById(R.id.description);
                priority = itemView.findViewById(R.id.priority);
                cView = itemView.findViewById(R.id.cardview);
            }
        }
    }


