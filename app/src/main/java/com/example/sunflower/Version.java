package com.example.sunflower;

public class Version {

    public static void main(String[] args) {
        System.out.println(getAttachmentIdFromContentUri(""));
        System.out.println(getAttachmentIdFromContentUri("/"));
        System.out.println(getAttachmentIdFromContentUri("//"));
        System.out.println(getAttachmentIdFromContentUri("///"));
        System.out.println(getAttachmentIdFromContentUri("////"));
        System.out.println(getAttachmentIdFromContentUri("//adas//"));
        System.out.println(getAttachmentIdFromContentUri("//adas/"));
        System.out.println(getAttachmentIdFromContentUri("//-1/"));
        System.out.println(getAttachmentIdFromContentUri("//-saa/"));
    }

    //从AttachmentProvider生成的Uri中获取AttachmentId
    //样例模板：content://cn.cj.pe.attachmentprovider/46112f51-20fc-36ff-9c86-bcf5c13a8b8dimap/3/RAW
    //返回3
    public static long getAttachmentIdFromContentUri(String uri){
        long attachmentId = -1;
        int second = uri.lastIndexOf("/");
        if(second != -1){
            String temp = uri.substring(0, second);
            int first = temp.lastIndexOf("/");
            if (first != -1){
                String id = temp.substring(first + 1, temp.length());
                if (!id.equals("")) {
                    try {
                        attachmentId = Long.parseLong(id);
                    }catch (Exception e){
//                        e.printStackTrace();
                    }
                }
            }
        }
        return attachmentId;
    }
}
