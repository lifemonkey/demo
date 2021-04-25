package com.example.demo.helpers;

public class Utils {

    /**
     * Get file extension from file name
     *
     * @param fileName file name
     * @return file extension
     */
    public static String getFileExtension(String fileName) {
        String fileExtension = null;

        String[] fileFrags = fileName.split("\\.");
        if (fileFrags.length > 0) {
            fileExtension = fileFrags[fileFrags.length - 1];
        }

        return fileExtension;
    }
}
