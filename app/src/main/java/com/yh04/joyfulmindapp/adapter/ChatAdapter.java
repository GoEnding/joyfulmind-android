package com.yh04.joyfulmindapp.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yh04.joyfulmindapp.R;
import com.yh04.joyfulmindapp.model.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessage> chatMessages;
    private String currentUser;

    public ChatAdapter(List<ChatMessage> chatMessages, String currentUser) {
        this.chatMessages = chatMessages;
        this.currentUser = currentUser;
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).getNickname() != null && chatMessages.get(position).getNickname().equals(currentUser)) {
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myimagechat_row, parent, false);
            return new MyChatViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.otherimagechat_row, parent, false);
            return new OtherChatViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);
        if (holder instanceof MyChatViewHolder) {
            ((MyChatViewHolder) holder).bind(chatMessage);
        } else {
            ((OtherChatViewHolder) holder).bind(chatMessage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    static class MyChatViewHolder extends RecyclerView.ViewHolder {
        TextView txtMessage, txtTime, txtName;
        ImageView imgMessage;
        CircleImageView profileImage; // 프로필 이미지 뷰 추가

        public MyChatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txtMyMessage);
            txtTime = itemView.findViewById(R.id.txtMyTime);
            txtName = itemView.findViewById(R.id.txtMyName);
            imgMessage = itemView.findViewById(R.id.imgMessage);
            profileImage = itemView.findViewById(R.id.profileImage); // 프로필 이미지 뷰 초기화
        }

        public void bind(ChatMessage chatMessage) {
            txtName.setText(chatMessage.getNickname());
            txtTime.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(chatMessage.getTimestamp().toDate()));

            if (chatMessage.getMessage() != null) {
                txtMessage.setVisibility(View.VISIBLE);
                txtMessage.setText(chatMessage.getMessage());
                imgMessage.setVisibility(View.GONE);
            } else {
                txtMessage.setVisibility(View.GONE);
                imgMessage.setVisibility(View.GONE);
            }

//            // 프로필 이미지 로드
//            String profileImageUrl = chatMessage.getProfileImageUrl();
//            Log.d("ChatAdapter", "Loading profile image: " + profileImageUrl); // 로그 추가
//
//            if (!TextUtils.isEmpty(profileImageUrl)) {
//                Glide.with(profileImage.getContext())
//                        .load(profileImageUrl)
//                        .placeholder(R.drawable.default_profile_image)
//                        .error(R.drawable.default_profile_image)
//                        .into(profileImage);
//            } else {
//                profileImage.setImageResource(R.drawable.default_profile_image);
            }
        }


    static class OtherChatViewHolder extends RecyclerView.ViewHolder {
        TextView txtMessage, txtTime, txtName;
        ImageView imgMessage;
        CircleImageView profileImage; // 프로필 이미지 뷰 추가

        public OtherChatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txtOtherMessage);
            txtTime = itemView.findViewById(R.id.txtOtherTime);
            txtName = itemView.findViewById(R.id.txtOtherName);
            imgMessage = itemView.findViewById(R.id.imgMessageOther);
            profileImage = itemView.findViewById(R.id.profileImage); // 프로필 이미지 뷰 초기화
        }

        public void bind(ChatMessage chatMessage) {
            txtName.setText(chatMessage.getNickname());
            txtTime.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(chatMessage.getTimestamp().toDate()));

            if (chatMessage.getMessage() != null) {
                txtMessage.setVisibility(View.VISIBLE);
                txtMessage.setText(chatMessage.getMessage());
                imgMessage.setVisibility(View.GONE);
            } else {
                txtMessage.setVisibility(View.GONE);
                imgMessage.setVisibility(View.GONE);
            }

//            // 프로필 이미지 로드
//            String profileImageUrl = chatMessage.getProfileImageUrl();
//            Log.d("ChatAdapter", "Loading profile image: " + profileImageUrl); // 로그 추가
//
//            if (!TextUtils.isEmpty(profileImageUrl)) {
//                Glide.with(profileImage.getContext())
//                        .load(profileImageUrl)
//                        .placeholder(R.drawable.default_profile_image)
//                        .error(R.drawable.default_profile_image)
//                        .into(profileImage);
//            } else {
//                profileImage.setImageResource(R.drawable.default_profile_image);
//            }
        }
    }
    }

