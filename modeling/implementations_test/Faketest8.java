package implementations_test;
public class Faketest8  { 
private int a;
private String goo;
private String too;
private test3 test3attr;
private test8 test8attr;
public Faketest8(test3 test3attr){this.test3attr = test3attr;
this.test8attr = new test8();
}
private String func1(int a,String goo){}
private int func2(float b,String goo,boolean ko){}
private int geta(){ 
 return this.a;}
private void seta(int a){
 this.a = a;
 }
private String getgoo(){ 
 return this.goo;}
private void setgoo(String goo){
 this.goo = goo;
 }
private String gettoo(){ 
 return this.too;}
private void settoo(String too){
 this.too = too;
 }
 } 
