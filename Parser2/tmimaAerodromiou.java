import java.util.ArrayList;
public class tmimaAerodromiou {
private int ArithmosErevnitwn;
private long[][] misthoiYpallilwn = new long[10][12];
public tmimaAerodromiou(){
}
public int getArithmosErevnitwn() {
return ArithmosErevnitwn;
}
public void setArithmosErevnitwn(int parameter ) {
ArithmosErevnitwn = parameter; 
}
public long[][] getmisthoiYpallilwn() {
return misthoiYpallilwn;
}
public void setmisthoiYpallilwn(long parameter[][] ) {
for (int n=0; n<misthoiYpallilwn.length; n++){
for (int n1=0; n1<misthoiYpallilwn[0].length; n1++){
misthoiYpallilwn[n][n1] = parameter[n][n1]; 
}
}
}
}
