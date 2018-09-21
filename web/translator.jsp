<%-- 
    Document   : translator
    Created on : 2018/06/20
    Author     : A.Miyazato
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="mbr-section form1 cid-qXEPtkYeat" id="form1-3">
    <div class="container">
        <div class="row justify-content-center">
            <div class="title col-12 col-lg-8">
                <%-- サブタイトルを記載 --%>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-center">
            <div class="media-container-column col-lg-8" data-form-type="formoid">
                <form class="mbr-form" action="translate" method="post">
                    <div class="row row-center va-middle">
                        <div class="form-group col-sm-4" style="padding-top: 10px;"><%-- 入力言語 --%>
                            <div class="toggle-buttons" >
                                <label>
                                    <input type="radio" name="fromLang" value="ja" 
                                           <c:if test="${fromLang == 'ja' || fromLang == null}">checked</c:if>><span class="button">日本語</span>
                                </label>
                                <label>
                                    <input type="radio" name="fromLang" value="en" 
                                           <c:if test="${fromLang == 'en'}">checked</c:if>><span class="button">英語</span>
                                </label>
                            </div>
                        </div>
                        <div class="input-group-btn col-sm-2"><%-- 翻訳言語入替 --%>
                            <button type="button" id="changeLang" class="btn btn-sm btn-success display-7" onclick="changeLang('/changeLang')">⇔</button>
                        </div>
                        <div class="form-group form-group-sm col-sm-6"><%-- 翻訳言語 --%>
                            <div class="col-xs-8">
                                <select id="toLang" name="toLang" class="form-control">
                                    <option name="selectLang" value="ja">日本語</option>
                                    <option name="selectLang" value="en">英語</option>
                                    <option name="selectLang" value="zh-CHS">中国語(簡体字)</option>
                                    <option name="selectLang" value="vi">ベトナム語</option>
                                    <option name="selectLang" value="fr">フランス語</option>
                                    <option name="selectLang" value="de">ドイツ語</option>
                                    <option name="selectLang" value="ko">韓国語</option>
                                </select>
                            </div>
                            <input type="hidden" name="toLang" class="toLangHdn" value="${toLang}"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-sm-6 inputArea" data-for="message"><%-- 入力項目 --%>
                            <textarea type="text" class="form-control" rows="7" data-form-field="Message" maxlength="200" id="inWords" name="inWords" placeholder="入力してください">${inWords}</textarea>
                            <p><span id="txtlmt">0</span> / 200</p>
                        </div>
                        <div class="form-group col-sm-6" data-for="message"><%-- 翻訳結果 --%>
                            <textarea type="text" class="form-control" name="transWords" rows="7" data-form-field="Message" id="transWords" disabled>${transWords}</textarea>
                        </div>
                    </div>
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-primary btn-form display-4">翻　訳</button>
                        <button type="button" class="btn btn-success btn-form display-4 copyBtn" style="border-radius:100px; padding:1rem 3rem " data-clipboard-text="${transWords}">コピー</button>
                    </span>
                </form>
                <c:if test="${errorList != null && errorList.size() != 0}">
                    <div class="alert alert-danger" role="alert">
                        <c:forEach var="errMsg" items="${errorList}">
                            ${errMsg}<br/>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</section>
