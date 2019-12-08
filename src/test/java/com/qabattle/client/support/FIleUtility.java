package com.qabattle.client.support;

import com.qabattle.util.allure.AllureLogger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.download;

/**
 * @author Aleksei Stepanov
 */

public class FIleUtility {

    public static File getFile(String name) {
        File file = null;
        try {
            switch (name) {
                case "Test Advertiser":
                    return file = download(ArticlesInfo.TEST_ADVERT.getUrl());
                case "Adidas":
                    return file = download(ArticlesInfo.ADIDAS.getUrl());
                case "Youtube":
                    return file = download(ArticlesInfo.YOUTUBE.getUrl());
                case "Instagram":
                    return file = download(ArticlesInfo.INSTAGRAM.getUrl());
                case "Jon Snow":
                    return file = download(ArticlesInfo.JON_SNOW.getUrl());
                case "Artur Fleck":
                    return file = download(ArticlesInfo.ARTUR_FLECK.getUrl());
                case "Tim Cook":
                    return file = download(ArticlesInfo.TIM_COOK.getUrl());
                case "Bugs Bunny":
                    return file = download(ArticlesInfo.BUGS_BUNNY.getUrl());
                case "Sasha Grey":
                    return file = download(ArticlesInfo.SASHA_GREY.getUrl());
                case "You":
                    return file = download(ArticlesInfo.YOU.getUrl());
                case "Leonel Messi":
                    return file = download(ArticlesInfo.LEONEL_MESSI.getUrl());
                case "Tony Stark":
                    return file = download(ArticlesInfo.TONY_START.getUrl());
                case "Elon Musk":
                    return file = download(ArticlesInfo.ELON_MUSK.getUrl());
                case "Darth Vader":
                    return file = download(ArticlesInfo.DARTH_VADER.getUrl());
            }
        } catch (IOException e) {
            throw new RuntimeException("ERROR download file " + e.getMessage());
        }
        if (file == null) {
            throw new RuntimeException("File name not found");
        }
        return file;
    }

    public static String getStringFromFile(File file) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //delete last '\n'
        contentBuilder.deleteCharAt(contentBuilder.length() - 1);
        return contentBuilder.toString();
    }

    public static boolean isEqualFiles(String first, String second) {
        if (first.equals(second)) {
            return true;
        } else {
            AllureLogger.saveTextLog("text_area_txt", first);
            AllureLogger.saveTextLog("download_file_txt", second);
            return false;
        }
    }
}
