package com.lounah.messenger.presentation.view.adapters;

import android.content.Context;
import android.media.session.PlaybackState;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lounah.messenger.R;
import com.lounah.messenger.presentation.model.posts.Post;
import com.lounah.messenger.presentation.model.posts.PostType;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<Post> posts = new ArrayList<>();
    private Context context;

    private static final int TEXT_VIEW_TYPE = 1;
    private static final int PHOTO_VIEW_TYPE = 2;
    private static final int TEXT_WITH_PHOTO_VIEW_TYPE = 3;

    public PostsAdapter(@NonNull final Context context, @NonNull final List<Post> posts) {
        this.posts = posts;
        this.context = context;
    }

    public PostsAdapter(@NonNull final Context context) {
        this.context = context;
    }

    public PostsAdapter() {}

    @Override
    public int getItemViewType(int position) {
        int result;
        switch (posts.get(position).getType()) {
            case TEXT:
                result = TEXT_VIEW_TYPE;
                Log.i("ENUM TEXT", " position " + position + " text");
                break;
            case PHOTO:
                result = PHOTO_VIEW_TYPE;
                Log.i("ENUM photo", " position " + position + " photo");
                break;
            case PHOTO_WITH_TEXT:
                result = TEXT_WITH_PHOTO_VIEW_TYPE;
                Log.i("ENUM TEXT ph", " position " + position + " text ph");
                break;
            default: result = 0; break;
        }
        return result;
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case (TEXT_VIEW_TYPE) :
                View textView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_text, parent, false);
                return new PostWithTextViewHolder(textView);
            case (PHOTO_VIEW_TYPE) :
                View photoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_photo, parent, false);
                return new PostWithPhotoViewHolder(photoView);
            case (TEXT_WITH_PHOTO_VIEW_TYPE) :
                View textAndPhotoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_text_with_photo, parent, false);
                return new PostWithTextViewHolder(textAndPhotoView);
                default: return null;
        }
    }

    @Override
    public void onBindViewHolder(PostsAdapter.ViewHolder holder, int position) {
//        switch (holder.getItemViewType()) {
//            case 1:
//                PostWithTextViewHolder postWithTextViewHolder = (PostWithTextViewHolder) holder;
//                postWithTextViewHolder.bindType(posts.get(position));
//                break;
//
//            case 2:
//                PostWithPhotoViewHolder postWithPhotoViewHolder = (PostWithPhotoViewHolder) holder;
//                postWithPhotoViewHolder.bindType(posts.get(position));
//                break;
//
//            case 3:
//                PostWithTextAndPhotoViewHolder postWithTextAndPhotoViewHolder
//                        = (PostWithTextAndPhotoViewHolder) holder;
//                postWithTextAndPhotoViewHolder.bindType(posts.get(position));
//                break;
//        }
        Post post = posts.get(position);
        holder.bindType(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
//
//    public static final class ViewHolder extends RecyclerView.ViewHolder {
//
//        @Inject
//        ArticlesPresenter presenter;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//            PregnantsApplication.getArticlesComponent().inject(this);
//            itemView.setOnClickListener(view -> {
//                Article article = new Article(titleTv.getText().toString(),
//                        "content", categoryTv.getText().toString(),
//                        dateTv.getText().toString(), "-");
//                presenter.onItemClicked(article);
//            });
//        }
//
//    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindType(Post post);
    }


    public class PostWithTextViewHolder extends ViewHolder {

        @BindView(R.id.avatar_iv)
        CircleImageView avatarIv;

        @Nullable
        @BindView(R.id.content_iv)
        ImageView contentIv;

        @BindView(R.id.username_tv)
        TextView usernameTv;

        @BindView(R.id.date_tv)
        TextView dateTv;

        @BindView(R.id.content_tv)
        TextView contentTv;

        @BindView(R.id.like_btn)
        ImageButton likeBtn;

        @BindView(R.id.save_btn)
        ImageButton saveBtn;

        @BindView(R.id.count_of_likes_tv)
        TextView countOfLikesTv;

        @BindView(R.id.delete_btn)
        ImageButton deleteBtn;

        public PostWithTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindType(Post post) {
            if (contentIv != null)
                Picasso.with(context).load(post.getImageURL()).into(contentIv);
            contentTv.setText(post.getTextContent());
            usernameTv.setText(post.getAuthor());
            dateTv.setText(post.getDate());
            countOfLikesTv.setText(String.valueOf(post.getLikesCount()));
        }

    }

    public class PostWithPhotoViewHolder extends ViewHolder {

        @BindView(R.id.avatar_iv)
        CircleImageView avatarIv;

        @BindView(R.id.username_tv)
        TextView usernameTv;

        @BindView(R.id.date_tv)
        TextView dateTv;

        @BindView(R.id.content_iv)
        ImageView contentIv;

        @BindView(R.id.like_btn)
        ImageButton likeBtn;

        @BindView(R.id.save_btn)
        ImageButton saveBtn;

        @BindView(R.id.count_of_likes_tv)
        TextView countOfLikesTv;

        @BindView(R.id.delete_btn)
        ImageButton deleteBtn;

        public PostWithPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindType(Post post) {
            usernameTv.setText(post.getAuthor());
            dateTv.setText(post.getDate());
            Picasso.with(context).load(post.getImageURL()).into(contentIv);
            countOfLikesTv.setText(String.valueOf(post.getLikesCount()));
        }

    }

    public class PostWithTextAndPhotoViewHolder extends ViewHolder {

        @BindView(R.id.avatar_iv)
        CircleImageView avatarIv;

        @BindView(R.id.username_tv)
        TextView usernameTv;

        @BindView(R.id.date_tv)
        TextView dateTv;

        @BindView(R.id.content_tv)
        TextView contentTv;

        @BindView(R.id.content_iv)
        ImageView contentIv;

        @BindView(R.id.like_btn)
        ImageButton likeBtn;

        @BindView(R.id.save_btn)
        ImageButton saveBtn;

        @BindView(R.id.count_of_likes_tv)
        TextView countOfLikesTv;

        @BindView(R.id.delete_btn)
        ImageButton deleteBtn;

        public PostWithTextAndPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindType(Post post) {
            usernameTv.setText(post.getAuthor());
            contentTv.setText(post.getTextContent());
            dateTv.setText(post.getDate());
            Picasso.with(context).load(post.getImageURL()).into(contentIv);
            countOfLikesTv.setText(String.valueOf(post.getLikesCount()));
        }

    }

    public void updateDataSet(@NonNull final List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

}
