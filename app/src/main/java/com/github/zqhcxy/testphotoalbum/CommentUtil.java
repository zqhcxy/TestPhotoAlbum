package com.github.zqhcxy.testphotoalbum;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zqh-pc on 2016/2/17.
 */
public class CommentUtil {
    public static final int IMAGES = 1;
    public static final int VIDEOS = 2;
    public static final int AUDIOS = 3;

    private static HashMap<String, Backet> bucketList = new HashMap<String, Backet>();




    /**
     * 获取视频与图片的目录列表（带有第一张图片(视频)与各目录图片(视频)总数）
     * @param context
     * @return
     */
    public static ArrayList<Backet> getMediaAblum(Context context,int mediaType) {
        ArrayList<Backet> ablumList = new ArrayList<>();
        Cursor cursor = null;
        String[] projection=null;
        String selection=null;
        Uri mediaUri=null;
        switch (mediaType){
            case IMAGES:
                projection = new String[]{MediaStore.Images.Media._ID,
                        MediaStore.Images.Media.BUCKET_ID, // 直接包含该图片文件的文件夹ID，防止在不同下的文件夹重名
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME, // 直接包含该图片文件的文件夹名
                        MediaStore.Images.Media.DISPLAY_NAME, // 图片文件名
                        MediaStore.Images.Media.DATA, // 图片绝对路径
                        "count(*)"
                };
                selection="0=0) " +
                        "group by ("+MediaStore.Images.Media.BUCKET_DISPLAY_NAME;
                mediaUri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                break;
            case AUDIOS:
                break;
            case VIDEOS:
                projection = new String[]{MediaStore.Video.Media._ID,
                        MediaStore.Video.Media.BUCKET_ID, // 直接包含该视频文件的文件夹ID，防止在不同下的文件夹重名
                        MediaStore.Video.Media.BUCKET_DISPLAY_NAME, // 直接包含该视频文件的文件夹名
                        MediaStore.Video.Media.DISPLAY_NAME, // 视频文件名
                        MediaStore.Video.Media.DATA, // 视频绝对路径
                        "count(*)"
                };
                selection="0=0) " +
                        "group by ("+MediaStore.Video.Media.BUCKET_DISPLAY_NAME;
                mediaUri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                break;
        }

        try {
            if(mediaUri==null||selection==null||projection==null)
                return ablumList;
            cursor = context.getContentResolver().query(
                    mediaUri, projection, selection,
                    null, "");
            if (cursor != null && cursor.getCount() > 0&&cursor.moveToFirst()) {
                do {
                    String folderIdColumn = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.BUCKET_ID));
                    String folderColumn = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                    int fileIdColumn = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                    String fileNameColumn = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                    String pathColumn = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(pathColumn).getParentFile();
                    Backet backet=new Backet();
                    backet.count=cursor.getInt(5);
                    backet.setBack_id(folderIdColumn);
                    backet.setFileName(folderColumn);
                    backet.setPhotoName(fileNameColumn);
                    backet.setFilePath(pathColumn);
                    backet.setFloderPath(parentFile.getAbsolutePath());
                    ablumList.add(backet);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return ablumList;
    }




//    /**
//     * 快速读取视频与图片的目录列表。
//     * @param context
//     * @return
//     */
//    public static List<Backet> getImageAblum(Context context) {
//        List<Backet> ablumList = new ArrayList<>();
//
//        Cursor cursor = null;
//        try {
//            String[] projection = new String[]{MediaStore.Images.Media._ID,
//                    MediaStore.Images.Media.BUCKET_ID, // 直接包含该图片文件的文件夹ID，防止在不同下的文件夹重名
//                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME, // 直接包含该图片文件的文件夹名
//                    MediaStore.Images.Media.DISPLAY_NAME, // 图片文件名
//                    MediaStore.Images.Media.DATA, // 图片绝对路径
//                    "count(*)"
//            };
//            String selection="0=0) " +
//                    "group by ("+MediaStore.Images.Media.BUCKET_DISPLAY_NAME;
//
//            cursor = context.getContentResolver().query(
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, selection,
//                    null, "");
//            if (cursor != null && cursor.getCount() > 0&&cursor.moveToFirst()) {
//                do {
//                    String folderIdColumn = cursor.getString(cursor
//                            .getColumnIndex(MediaStore.Images.Media.BUCKET_ID));
//                    String folderColumn = cursor.getString(cursor
//                            .getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
//                    int fileIdColumn = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
//                    String fileNameColumn = cursor.getString(cursor
//                            .getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
//                    String pathColumn = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
////                    Backet backet=bucketList.get(folderIdColumn);
////                    if(backet==null) {
//                        Backet  backet = new Backet();
//                        backet.setBack_id(folderIdColumn);
//                        backet.setFileName(folderColumn);
//                        backet.setPhotoName(fileNameColumn);
//                        backet.setFilePath(pathColumn);
//                        ablumList.add(backet);
//                        bucketList.put(folderIdColumn,backet);
////                    }
//                    backet.count=cursor.getInt(5);
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//                cursor = null;
//            }
//        }
//        return ablumList;
//    }
}
