package com.yashwanth.routinecheck.recycler;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.TooltipCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yashwanth.routinecheck.DB.AppDatabase;
import com.yashwanth.routinecheck.DB.DAO.TaskDao;
import com.yashwanth.routinecheck.DB.Entity.Task;
import com.yashwanth.routinecheck.R;
import com.yashwanth.routinecheck.UpdateTask;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private List<Task> tasks;
    private final LayoutInflater inflater;
    private OnTaskItemClick onTaskItemClick;

    public TaskAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        tasks = new ArrayList<>();
//        this.onTaskItemClick = (OnTaskItemClick) context;
    }


    public interface OnTaskItemClick{
        void onTaskClick(int pos);
    }
    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged(); //going to bind new data to Views.
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_view,parent,false);
        TaskViewHolder viewHolder = new TaskViewHolder(taskItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(tasks.get(position));
        Task task = tasks.get(position);
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView title,description;
        Button deleteTask,updateTask;
        TaskDao taskDao;
        Task taskdel;


        public TaskViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tsk_title);
            description = itemView.findViewById(R.id.description_text);
            deleteTask = itemView.findViewById(R.id.delete_task);
            updateTask = itemView.findViewById(R.id.update_task_button);

            final AppDatabase database = AppDatabase.getDatabaseInstance(itemView.getContext());
            taskDao = database.taskDao();

            deleteTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            taskDao.delete(taskdel);
                        }
                    });
                }
            });

            updateTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTask updateTaskDialog = new UpdateTask();
                    updateTaskDialog.show(updateTaskDialog,"update");
                }
            });

        }

        public void bind(Task task) {
            this.taskdel = task;
        }


    }
}
