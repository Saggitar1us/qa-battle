package com.qabattle.client.support;

/**
 * @author Aleksei Stepanov
 */


public enum ArticlesInfo {

    TEST_ADVERT("/data/test_advert/test_advert_file_data.txt"),
    ADIDAS("/data/adidas/adidas_file_data.txt"),
    YOUTUBE("/data/youtube/youtube_file_data.txt"),
    INSTAGRAM("/data/instagram/instagram_file_data.txt"),
    JON_SNOW("/data/john_snow/john_snow_file_data.txt"),
    ARTUR_FLECK("/data/artur_fleck/artur_fleck_file_data.txt"),
    TIM_COOK("/data/artur_fleck/tim_cook_file_data.txt"),
    BUGS_BUNNY("/data/bugs_bunny/bugs_bunny_file_data.txt"),
    SASHA_GREY("/data/sasha_gray/sasha_gray_file_data.txt"),
    YOU("/data/you/you_file_data.txt"),
    LEONEL_MESSI("/data/leonel_messi/leonel_messi_file_data.txt"),
    TONY_START("/data/tony_stark/tony_stark_file_data.txt"),
    ELON_MUSK("/data/elon_musk/elon_musk_file_data.txt"),
    DARTH_VADER("/data/darth_vader/darth_vader_file_data.txt");

    private final String url;

    ArticlesInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
