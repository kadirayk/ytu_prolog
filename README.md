expert systems prolog workspace

<b> Odev2: </b>

Nasıl Kullanılır: </br>
Sorgu için odev.pl açıldıktan sonra, ‘basla.’ Yazılarak program çalıştırılır. Cümlenin ögeleri kullanıcıdan tek tek alınmaktadır. </br>
 Örnek Sorgu: </br>
?- basla. </br>
bos birakmak icin buyuk bir Harf girin </br>
Ozne girin </br>
|: 'curiosity rover' </br>
Nesne girin </br>
|: araç </br>
Zaman girin </br>
|: 'şu anda' </br>
Mekan girin </br>
|: X.  </br>
Eylem girin </br>
|: Y. </br>
</br>

<b> Örnek çalışma: </b> </br>
Cumle(‘curiosity rover’, araç, ‘şu anda’, _, _ ): </br> </br>
curiosity rover araç (parkda araç bulunur) </br>
curiosity rover bulmak ( curiosity rover araç ise, araç bulmak yaparsa curiosity rover bulmak yapar ) </br>
curiosity rover bulunur ( curiosity rover araç ise, araç bulmak yaparsa, bulmak yapmak bulunur yapmaya sebep oluyorsa curiosity rover bulunur yapar ) </br>
curiosity rover kendisini tanır ( curiosity rover araç ise, araç bulmak yaparsa, bulmak yapmak kendisini tanır yapmaya sebep oluyorsa curiosity rover kendisini tanır yapar ) </br>
curiosity rover ortaya çıkarılır ( curiosity rover araç ise, araç bulmak yaparsa, bulmak yapmak ortaya çıkarılır yapmaya sebep oluyorsa curiosity rover ortaya çıkarılır yapar ) </br>
curiosity rover sevinilir ( curiosity rover araç ise, araç bulmak yaparsa, bulmak yapmak sevinilir yapmaya sebep oluyorsa curiosity rover sevinilir yapar ) </br>
