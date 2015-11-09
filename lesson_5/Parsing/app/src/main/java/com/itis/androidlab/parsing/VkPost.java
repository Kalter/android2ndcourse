package com.itis.androidlab.parsing;

import com.itis.androidlab.parsing.models.VkPostType;

public class VkPost {
    /**
     * идентификатор записи
     */
    private long mId;

    /**
     * идентификатор владельца стены, на которой размещена запись
     */
    private int mOwnerId;

    /**
     * идентификатор владельца стены, на которой размещена запись
     */
    private int mFromId;

    /**
     * время публикации записи в формате unixtime
     */
    private long mDate;

    /**
     * текст записи
     */
    private String mText;

    /**
     * количество комментариев
     */
    private int mCommentsCount;

    /**
     * число пользователей, которым понравилась запись
     */
    private int mLikesCount;

    /**
     *  число пользователей, скопировавших запись
     */
    private int mRepostsCount;

    /**
     * тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
     */
    private VkPostType mPostType;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public int getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(int ownerId) {
        mOwnerId = ownerId;
    }

    public int getFromId() {
        return mFromId;
    }

    public void setFromId(int fromId) {
        mFromId = fromId;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getCommentsCount() {
        return mCommentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        mCommentsCount = commentsCount;
    }

    public int getLikesCount() {
        return mLikesCount;
    }

    public void setLikesCount(int likesCount) {
        mLikesCount = likesCount;
    }

    public int getRepostsCount() {
        return mRepostsCount;
    }

    public void setRepostsCount(int repostsCount) {
        mRepostsCount = repostsCount;
    }

    public VkPostType getPostType() {
        return mPostType;
    }

    public void setPostType(VkPostType postType) {
        mPostType = postType;
    }

    @Override
    public String toString() {
        return "VkPost{" +
                "mId=" + mId +
                ", mOwnerId=" + mOwnerId +
                ", mFromId=" + mFromId +
                ", mDate=" + mDate +
                ", mText=" + mText +
                ", mCommentsCount=" + mCommentsCount +
                ", mLikesCount=" + mLikesCount +
                ", mRepostsCount=" + mRepostsCount +
                ", mPostType=" + mPostType +
                '}';
    }
}
