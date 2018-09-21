package jp.co.qualysite.internship.form;

/**
 * 翻訳情報を保持するViewクラス
 * @author A.Miyazato
 * @since 2018/05/25 新規作成
 */
public class TranslateInputForm {
    /** 入力文章 */
    private String inWords;
    /** 入力指定言語 */
    private String fromLang;
    /** 翻訳指定言語 */
    private String toLang;

    /**
     * inWords の値を取得.
     * @return inWords
     */
    public String getInWords() {
        return inWords;
    }

    /**
     * inWords へ値を設定.
     * @param inWords 入力文章
     */
    public void setInWords(String inWords) {
        this.inWords = inWords;
    }

    /**
     * fromLang の値を取得.
     * @return fromLang
     */
    public String getFromLang() {
        return fromLang;
    }

    /**
     * fromLang へ値を設定.
     * @param fromLang 入力指定言語
     */
    public void setFromLang(String fromLang) {
        this.fromLang = fromLang;
    }

    /**
     * toLang の値を取得.
     * @return toLang
     */
    public String getToLang() {
        return toLang;
    }

    /**
     * toLang へ値を設定.
     * @param toLang 翻訳指定言語
     */
    public void setToLang(String toLang) {
        this.toLang = toLang;
    }
}
