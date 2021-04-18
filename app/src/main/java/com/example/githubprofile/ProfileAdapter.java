package com.example.githubprofile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder>
{
    ArrayList<Profile> data;
    Bitmap img;
    ImageView imageView;
    ProfileAdapter(ArrayList<Profile>data)
    {
        this.data=data;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        final Profile myListData = data.get(position);
        holder.name.setText("Name :"+myListData.getName());
        holder.id.setText("ID :"+String.valueOf(myListData.getId()));
        holder.score.setText("Score :"+String.valueOf(myListData.getScore()));
        holder.html.setText("Github link :"+String.valueOf(myListData.getHtmlURL()));
//        holder.name.setText("Mukul");
//        holder.id.setText(1);
//        holder.score.setText();
//        URL url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
//Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//imageView.setImageBitmap(bmp);
        ImageTask imageTask=new ImageTask();
        imageTask.execute(myListData.getAvatar());
        imageView=holder.imageView;
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }
    class ImageTask extends AsyncTask<String,Void,Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings)
        {
            URL url = null;
            try
            {
                url = new URL(strings[0]);
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            Bitmap bmp = null;
            try
            {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                return bmp;
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            super.onPostExecute(bitmap);
            img=bitmap;
            imageView.setImageBitmap(bitmap);
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView name;
        public TextView id;
        public TextView score;
        public TextView html;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.imageView= (ImageView) itemView.findViewById(R.id.image);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.id = (TextView) itemView.findViewById(R.id.id);
            this.score = (TextView) itemView.findViewById(R.id.score);
            this.html=itemView.findViewById(R.id.html);
        }
    }

}
