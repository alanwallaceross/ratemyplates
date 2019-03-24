package com.example.ratemyplate2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.health.PidHealthStats;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    // declaration
    static private ArrayList<Plate> plates;
    public static PictureUtils pictureUtils;

    private Context context;


    // constructor
    public RecyclerViewAdapter(ArrayList<Plate> plates, Context context) {
        this.plates = plates;
        this.context = context;

    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plate_list_element, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // log for debugging
        Log.d(TAG, "onBindViewHolder: called.");

        //gets the images, converts to bitmap, loads into holder

        holder.imageName.setText(plates.get(position).getName());

        holder.imageCaption.setText(plates.get(position).getCaption());
//        holder.imageName.setText(plates.get(holder.getAdapterPosition()).getName());
//        holder.imageCaption.setText(plates.get(holder.getAdapterPosition()).getName());

        final Bitmap rotatedBm = PictureUtils.getRotatedImage(BitmapFactory.decodeFile(plates.get(position).getImage().getFileName()), plates.get(position).getImage().getFileName());
        final Bitmap resizedBm = Bitmap.createScaledBitmap(rotatedBm, 400, 400, false);

        holder.image.setImageBitmap(PictureUtils.getRotatedImage(BitmapFactory.decodeFile(plates.get(position).getImage().getFileName()), plates.get(position).getImage().getFileName()));
//        holder.image.setImageBitmap(PictureUtils.getRotatedImage(BitmapFactory.decodeFile(plates.get(holder.getAdapterPosition()).getImage().getFileName()), plates.get(holder.getAdapterPosition()).getImage().getFileName()));

//         user clicks on list in the list
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on:" + plates.get(position).getName());

                Toast.makeText(context, plates.get(position).getName(), Toast.LENGTH_SHORT).show();

                //takes user to the item in the list they clicked on
                Intent intent2 = new Intent(context, PlateActivity.class);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                rotatedBm.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                byte[] byteArray = stream.toByteArray();
                intent2.putExtra("image", byteArray);
                intent2.putExtra("image_name", plates.get(position).getName());
                intent2.putExtra("image_caption", plates.get(position).getCaption());
                context.startActivity(intent2);
            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                customDialog("Confirm delete", "Are you sure you want to delete your plate?", "cancelMethod", "okMethod", position);
                return false;
            }
        });
    }



    @Override
    public int getItemCount() {

        // return the list, without this, blank screen
        return plates.size();
    }


    public void customDialog(String title, String message, final String cancelMethod, final String okMethod, final int position) {
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(context, R.style.AlertDialogCustom);
        builderSingle.setIcon(R.drawable.ic_android_black_24dp);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                R.string.Cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: Cancel Called.");
                        if (cancelMethod.equals("cancelMethod")) {
                            cancelMethod();
                        }
                    }
                }
        );

        builderSingle.setPositiveButton(
                R.string.OK,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: OK Called.");
                        if (okMethod.equals("okMethod")) {
                            okMethod(position);
                        }
                    }
                }
        );
        builderSingle.show();
    }

    public void cancelMethod(){
        Log.d(TAG, "cancelMethod: Called.");
        Toast.makeText(context, "Cancelled.", Toast.LENGTH_SHORT).show();
    }

    public void okMethod(int position){
        Log.d(TAG, "okMethod: Called.");
        plates.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(context, "Plate deleted.", Toast.LENGTH_SHORT).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView imageName;
        TextView imageCaption;
        CircleImageView image;
        RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            imageCaption = itemView.findViewById(R.id.image_caption);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }



}
