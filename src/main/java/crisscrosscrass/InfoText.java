package crisscrosscrass;

public enum InfoText {
    TextSearch("HP: text search + Suggestions","Enter keywords that will be used to test our search functionality and the suggestions.\n" +
            " If you enter more than one keyword, use pipe as separator\n"),
    GridPageURL("Grid Page URL without Windows","This field will be used to test a gridpage without Windows\n"),
    GridPageURLWithWindows("Grid Page URL with Windows","This field will be used to test a gridpage with Windows\n"),
    BrandShopKeyword("Brand Keyword","This field will be used to test brand search boxes. \n" +
            "Pick a brand which has series so that Series Filter Box can also be tested. \n" ),
    FilterText("Filer Buttons","This field will be used to test a gridpage with Windows\n"),
    GridPageWitthFillIns("Grid Page URL with FillIns","This field will be used to test a gridpage with Fill-ins\n"),
    exclamationMarkGridPageURLwithoutWindows("Info","- Grid page has to have a style box with more than 4 tags\n (or it has to be a tag with at least 4 children)\n" +
            "- It cannot be a one-gender tag\n" +
            "- Grid page has to contain items from chosen brands and shops\n"),
    LucenePageSearch("Keyword trigger Lucene Page","This keyword will be used to trigger a Lucene page\n " +
            "and perform all the Lucene tests. \n"),
    ShopSearchBox("Shop Search Box","This field will be used to test search on Partnershop page and Grid Page.\n "),
            //"If you enter more than one Shop, use pipe as separator.\n"),
    MerchandiseSearch("Merchandise Search Box","This field will be used to test search on Merchandise page.\n " +
            "If you enter more than one Merchandise, use pipe as separator.\n");
    private String HeaderMessage;
    private String MainMessage;
    InfoText(String headerMessage, String mainMessage) {
        this.HeaderMessage = headerMessage;
        this.MainMessage = mainMessage;
    }
    public String getHeaderMessage() {
        return HeaderMessage;
    }
    public String getMainMessage() {
        return MainMessage;
    }
}
