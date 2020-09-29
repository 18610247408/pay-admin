package com.hhm.bussplat.util.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.stream.FileImageInputStream;

import net.coobird.thumbnailator.Thumbnails;

public class FileUtil {
	
	
	/**
     * 上传文件 inputStream
     * @param filePath
     * @param inputStream
     * @return
     */
    public static boolean uploadFile(String filePath, InputStream inputStream) {
        boolean result = false;
        FileOutputStream outputStream = null;
        String[] pathArray = getPathArray(filePath);
        String directoryPath = filePath.substring(0,filePath.length()-pathArray[pathArray.length-1].length());
        File targetDirectoryFile = new File(directoryPath);
        if (targetDirectoryFile.exists()){
            if (!targetDirectoryFile.isDirectory()){
                targetDirectoryFile.mkdirs();
            }
        }else {
            targetDirectoryFile.mkdirs();
        }
        try {
        	File file = new File(filePath);
            outputStream = new FileOutputStream(file);
            int length = 0;
            byte[] bytes = new byte[1024];
            while ((length = inputStream.read(bytes)) != -1)
            {
                outputStream.write(bytes, 0, length);
            }
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return result;
    }
	
    
    /**
     * 读取图片流
     * @return
     * @throws Exception
     */
    public static byte[] getLocalPicture(String imagePath) throws Exception {
        byte[] data = null;
        FileImageInputStream input = new FileImageInputStream(new File(imagePath));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int numBytesRead = 0;
        while ((numBytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, numBytesRead);
        }
        data = output.toByteArray();
        output.close();
        input.close();
        return data;
    }
    
    
    /**
     * 拆分文件路径
     * @param path
     * @return
     */
    public static String[] getPathArray(String path){
        String[] pathArray = null;
        if(path.contains("\\")){
            pathArray = path.split("\\\\");
        }else if(path.contains("/")){
            pathArray = path.split("/");
        }
        return  pathArray;
    }
    
}
