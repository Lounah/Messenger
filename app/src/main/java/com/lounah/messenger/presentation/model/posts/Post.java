package com.lounah.messenger.presentation.model.posts;

public class Post {

    private PostType type;
    private String textContent;
    private String imageURL;
    private String author;
    private String date;
    private int id;
    private int likesCount;
    private boolean isLiked;
    private boolean isBookmarked;

    public Post(int id, PostType type, String textContent, String imageURL, String author, String date, int likesCount, boolean isLiked, boolean isBookmarked) {
        this.id = id;
        this.type = type;
        this.textContent = textContent;
        this.imageURL = imageURL;
        this.author = author;
        this.date = date;
        this.likesCount = likesCount;
        this.isLiked = isLiked;
        this.isBookmarked = isBookmarked;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }


    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}
