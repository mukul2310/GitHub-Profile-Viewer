package com.example.githubprofile;

public class Profile
{
    String name,htmlURL,avatar;
    int id;
    double score;

    public double getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public Profile(String name, int id, String htmlURL, String avatar, double score)
    {
        this.name = name;
        this.htmlURL = htmlURL;
        this.avatar = avatar;
        this.id = id;
        this.score = score;
    }



    public String getHtmlURL()
    {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL)
    {
        this.htmlURL = htmlURL;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

}
