package com.sparta.week04.models;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class ItemDTO {
    //title, image, link lprice

    private String title;
    private String link;
    private String image;
    private Integer lprice;

    public ItemDTO(JSONObject jsonObject){
        this.title = jsonObject.getString("title");
        this.link = jsonObject.getString("link");
        this.image = jsonObject.getString("image");
        this.lprice = jsonObject.getInt("lprice");
    }

}
