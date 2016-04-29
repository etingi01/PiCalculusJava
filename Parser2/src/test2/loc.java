
package test2;
import java.util.ArrayList;
public class loc {
private String nametmimatos;
private int[][] misthoi = new int[5][12];
public loc(){
}
public String getnametmimatos() {
return nametmimatos;
}
public void setnametmimatos(String parameter ) {
nametmimatos = parameter; 
}
public int[][] getmisthoi() {
return misthoi;
}
public void setmisthoi(int parameter[][] ) {
for (int n=0; n<misthoi.length; n++){
for (int n1=0; n1<misthoi[0].length; n1++){
misthoi[n][n1] = parameter[n][n1]; 
}
}
}
}
