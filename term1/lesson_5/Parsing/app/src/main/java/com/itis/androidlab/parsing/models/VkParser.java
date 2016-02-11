package com.itis.androidlab.parsing.models;

import com.itis.androidlab.parsing.VkPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VkParser {

    public static List<VkPost> parsePosts(JSONObject object) throws JSONException {
        List<VkPost> posts = new ArrayList<>();
        JSONArray postJsonArray = object.getJSONArray("items");
        for (int i = 0; i < postJsonArray.length(); i++) {
            posts.add(parseVkPost(postJsonArray.getJSONObject(i)));
        }
        return posts;
    }

    private static VkPost parseVkPost(JSONObject jsonObject) throws JSONException {
        VkPost post = new VkPost();
        post.setId(jsonObject.getLong("id"));
        post.setOwnerId(jsonObject.getInt("owner_id"));
        post.setFromId(jsonObject.getInt("from_id"));
        post.setDate(jsonObject.getLong("date"));
        post.setText(jsonObject.getString("text"));

        JSONObject commentsObject = jsonObject.getJSONObject("comments");
        post.setCommentsCount(commentsObject.getInt("count"));

        JSONObject likesObject = jsonObject.getJSONObject("likes");
        post.setLikesCount(likesObject.getInt("count"));

        post.setRepostsCount(jsonObject.getJSONObject("reposts").getInt("count"));

        post.setPostType(VkPostType.getEnum(jsonObject.getString("post_type")));
        return post;
    }
}
