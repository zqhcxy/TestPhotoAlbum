package com.github.zqhcxy.testphotoalbum;

/**
 * Created by zqh-pc on 2016/2/17.
 */
public class Backet {

    String back_id;//所在文件夹id
    int id;
    String fileName;//文件夹名字
    String photoName;//图片名字
    String filePath;//图片地址
    int count;//文件夹中图片总数
    String floderPath;//文件夹全地址

    public String getFloderPath() {
        return floderPath;
    }

    public void setFloderPath(String floderPath) {
        this.floderPath = floderPath;
    }

    public String getBack_id() {
        return back_id;
    }

    public void setBack_id(String back_id) {
        this.back_id = back_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
