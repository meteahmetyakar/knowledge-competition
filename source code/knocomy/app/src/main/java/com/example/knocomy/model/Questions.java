package com.example.knocomy.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("questiondatabase")
    @Expose
    private List<Questiondatabase> questiondatabase = null;

    public List<Questiondatabase> getQuestiondatabase() {
        return questiondatabase;
    }//Burada Questiondatabase sınıfından bir liste döndürecek fonksiyon oluşturuyoruz, yani dönen questiondatabase listesi Questiondatabase sınıfından oluyor, bunu da zaten 11.satırda biz oluşturuyoruz.
    //içini de Question_Page sayfasında doldurmuştuk zaten
    //Questiondatabase sınıfında da get fonksiyonları var onlarla da o elemanlara erişebiliyorsunuz
    //Örneğin questionLists.getQuestiondatabase().get(3).getAnswerTrue() dediğinizde questionLists nesnesindeki 3 idli, answertrue keyli değeri çağırmış oluyorsunuz
}
