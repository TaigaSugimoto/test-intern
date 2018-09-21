package jp.co.qualysite.internship.translate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.qualysite.internship.form.TranslateInputForm;







/**
  * 翻訳機能の処理クラス.
 * @author A.Miyazato
 * @since 2018/05/24 新規作成
 */
@WebServlet(name = "TranslateServlet", urlPatterns = {"/translate"})
public class TranslateServlet extends HttpServlet {
    /** Logger */
    private static final Logger LOG = Logger.getLogger(TranslateServlet.class.getName());
    /** 入力文字数の最小値 */
    public static final int MINLENGTH = 0;
    /** 入力文字数の最大値 */
    public static final int MAXLENGTH = 200;
    /** エラー情報List */
    private List<String> errorList;
    /** サブスクリプションキー */
    private static String subscriptionKey = "435dec3164ef4b999f521a0040d89e86";
    /** 翻訳サービスURL */
    private static String host = "https://api.cognitive.microsofttranslator.com";
    /**翻訳サービスのapiのバージョン*/
    private static String path = "/translate?api-version=3.0";
    /**入力された文章をリストに追加する。<br>*/
    public class RequestBody {
        /**入力された文章*/
        private String text;
        /**
         * 引数で渡されたtextをこのリストのtextに追加する
         @param text 引数で渡されたこれから翻訳する文章
         */
        public RequestBody(String text) {
            this.text = text;
        }
    }
    /**
     * 翻訳処理.<br>
     * 入力された情報を指定言語に翻訳する。<br>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1.画面情報の取得し、TranslateInputFormへ格納する
        TranslateInputForm form = this.createForm(request);

        // 2.入力チェックを実施
        // TODO 入力チェックの処理を記載
        errorList = new ArrayList<String>(); //Listの初期化
        inputCheck(form);

        // 3.翻訳処理実行
        // TODO 翻訳処理を記載
        String toLangParam = "&to=" + form.getToLang();
        String text = form.getInWords();
        String translated = null;
        try {
            translated = translate(toLangParam, text);
        } catch (Exception e) {
            errorList.add("翻訳に失敗しました");
        }

        // 4.画面への返却

        request.setAttribute("errorList", errorList); //エラー表示
        request.setAttribute("toLang", form.getToLang());
        request.setAttribute("fromLang", form.getFromLang());
            // TODO 入力項目の値を設定
        request.setAttribute("transWords", prettify(translated));

        // 5.自画面へ遷移
        // TODO index.jspへ遷移する処理を記載
        request.getRequestDispatcher("/index.jsp")
        .forward(request, response);

    }

        /**
     * 入力情報をFormへ格納する.<br>
     *
     * @param request リクエスト情報の取得
     * @return TranslateInputForm
     */
    private TranslateInputForm createForm(HttpServletRequest request) {
        TranslateInputForm form = new TranslateInputForm();

        form.setFromLang(request.getParameter("fromLang"));
        form.setToLang(request.getParameter("toLang"));
        //TODO 入力項目をformへ格納する処理を記載
        form.setInWords(request.getParameter("inWords"));
        return form;
    }



    /**
     * 入力チェック処理.<br>
     * 入力された文章のチェックを行う.<br>
     *
     * @param form 翻訳情報
     */
    private void inputCheck(TranslateInputForm form) {
        String setLanguage = form.getFromLang(); //翻訳先言語
        String inLanguage = form.getToLang(); //翻訳元言語
        int inLength = form.getInWords().length(); //入力文字数
        //必須・文字数チェック
        if (inLength == MINLENGTH) {
            errorList.add("翻訳する文章を入力してください。");
        } else if (inLength > MAXLENGTH) {
            errorList.add("文字数が上限を超えています。");
        }

        //同一言語翻訳チェック
        if (setLanguage.equals(inLanguage)) {
            errorList.add("同一言語への翻訳は行いません。");
        }
    }
    /**
     * 翻訳処理.<br>
     * 入力された文章の翻訳を行う.<br>
     *
     * @param params 翻訳先言語
     * @param text 翻訳する文章
     * @return post
     * @throws java.lang.Exception 例外処理
     */
    public  String translate(String params, String text) throws Exception {
        URL url = new URL(host + path + params); //翻訳する為のURL
        List<RequestBody> objList = new ArrayList<RequestBody>();
        objList.add(new RequestBody(text));
        String content = new Gson().toJson(objList); //JavaからJSON形式へ
        String result = post(url, content);
        //RequestBody reqResult = Gson.fromJson(result,RequestBody.class);
        return result;
    }
    /**
     * 通信処理.<br>
     * Microsoft Translatorと通信し、<br>
     * 翻訳を行う.<br>
     * @param url Microsoft TranslatorのURL
     *@return String
     */
    public String post(URL url, String content) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection(); //https接続
            connection.setRequestMethod("POST"); //URL要求のメソッドをPOST(サーバに値を渡す)に設定
            connection.setRequestProperty("Content-Type", "application/json"); //json形式で要求
            connection.setRequestProperty("Content-Length", content.length() + ""); //json形式の長さ
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
            connection.setRequestProperty("X-ClientTraceId", java.util.UUID.randomUUID().toString()); //要求を一意に識別する
            connection.setDoOutput(true); //URL接続を出力用として使う
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            byte[] encodedContent = content.getBytes("UTF-8"); //入力文字のUTF-8でのバイト数
            wr.write(encodedContent, 0, encodedContent.length);
            wr.flush();
            wr.close();
            StringBuilder response = new StringBuilder(); //翻訳結果の文字列を扱う
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8")); //入力ストリームから文字列を読み取る
            String line;
            while ((line = in.readLine()) != null) { //ストリームから1行分読み取る
                response.append(line);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            errorList.add("通信に失敗しました。\nシステム管理者へ問い合わせください。");
            return ""; //翻訳結果のボックスに何も表示しない
        }
    }
    public static String prettify(String json_text){
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }
    
    
    /**
     * errorList の取得メソッド.
     * @return errorList
     */
    public List<String> getErrorList() {
        return errorList;
    }

}
