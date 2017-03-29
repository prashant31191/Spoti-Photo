package co.cdmunoz.spotiphoto.topArtists;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.cdmunoz.spotiphoto.R;

/**
 * Created by Prashant on 30-03-2017.
 */
public class ActAnimationList extends AppCompatActivity {


    @BindView(R.id.btnStartDemo)
    Button btnStartDemo;

    @BindView(R.id.top_artists_list_container)
    RecyclerView topArtistsList;

    @BindView(R.id.rvBottom)
    RecyclerView horizontal_recycler_view;

    String[] nameArray = {"1", "0", "0", "0", "0", "0", "0","0", "0", "0", "0","0", "0", "0", "0", "0", "0","0", "0", "0", "0"};
 //   Integer[] drawableArray = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_artists_activity);
        ButterKnife.bind(this);

        btnStartDemo.setVisibility(View.GONE);
        topArtistsList.setVisibility(View.GONE);
        horizontal_recycler_view.setVisibility(View.VISIBLE);

        ArrayList   horizontalList = new ArrayList<BottomModel>();
        for (int i = 0; i < nameArray.length; i++) {


            horizontalList.add(new BottomModel(nameArray[i]));
        }
        CustomAdapter  horizontalAdapter=new CustomAdapter(horizontalList);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_view.setAdapter(horizontalAdapter);

    }




    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        private ArrayList<BottomModel> dataSet;

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textViewName;
            ImageView imageViewIcon;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.textViewName = (TextView) itemView.findViewById(R.id.txtView);
                //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
                this.imageViewIcon = (ImageView) itemView.findViewById(R.id.image);



            }
        }

        public CustomAdapter(ArrayList<BottomModel> data) {
            this.dataSet = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.raw_bottom, parent, false);

            //view.setOnClickListener(MainActivity.myOnClickListener);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

            TextView textViewName = holder.textViewName;
            // TextView textViewVersion = holder.textViewVersion;
        final    ImageView imageView = holder.imageViewIcon;

          /*  textViewName.setText(dataSet.get(listPosition).getName());
            //textViewVersion.setText(dataSet.get(listPosition).getVersion());
            imageView.setImageResource(dataSet.get(listPosition).getImage());

*/


            boolean isSelected = dataSet.get(listPosition).isSelected;

            textViewName.setText(""+listPosition);
            RelativeLayout.LayoutParams layoutParams100 = new RelativeLayout.LayoutParams(60, 60);

            final RelativeLayout.LayoutParams layoutParams140 = new RelativeLayout.LayoutParams(80, 80);

            if(isSelected==false)
            {
                imageView.setLayoutParams(layoutParams100);
            }
            else {
                imageView.setLayoutParams(layoutParams140);
            }



            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(dataSet.get(listPosition).isSelected == true)
                    {

                    }
                    else {



                        for (int i =0; i<dataSet.size();i++)
                        {
                            if(i==listPosition)
                            {
                                dataSet.get(listPosition).isSelected = true;
                            }
                            else {
                                dataSet.get(i).isSelected = false;
                            }

                        }


                        Random rnd = new Random();
                        int color = Color.argb(200, rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(200));
                        horizontal_recycler_view.setBackgroundColor(color);

                        notifyDataSetChanged();
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }}



    public class BottomModel{
        String name;
        int image;
        boolean isSelected = false;

        public BottomModel(String name) {
            if(name.equalsIgnoreCase("1"))
            {
                this.isSelected = true;
            }
            else
            {
                this.isSelected = false;
            }

            this.name = name;
        }
        public BottomModel(String name, int image) {
            this.name = name;
            this.image=image;
        }
        public String getName() {
            return name;
        }
        public int getImage() {
            return image;
        }}


}
