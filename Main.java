import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class MyJFramee extends JFrame implements ActionListener{
  private JPanel contentPane_text,contentPane_num;
  private JTextField text=new JTextField(30);
  private String s="";//儲存輸入的所有內容，輸入=時開始計算
  MyJFramee(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Hello JFrame");
    setBounds(200,200,450,400);
    //pack();

    
    contentPane_text= new JPanel();
    contentPane_num= new JPanel();
    contentPane_num.setLayout(new GridLayout(5,3,5,5));

    this.add(contentPane_text,BorderLayout.NORTH);
    this.add(contentPane_num);

    contentPane_text.add(text);

    String[] num={"1","2","3","4","5","6","7","8","9","+","0","-","*","=","/"};

    for(String s:num){
      JButton a=new JButton(s);
      a.addActionListener(this);
      contentPane_num.add(a);
    }
    setVisible(true);
    
  }
  public void actionPerformed(ActionEvent e){
    JButton namBtn=(JButton)e.getSource();
    s+=namBtn.getText();
    if(namBtn.getText().equals("=")){
      String str=s;//獲得現在按鈕數字
      ArrayList<Integer> num=new ArrayList<>();//儲存數字
      ArrayList<String> op=new ArrayList<>();//儲存運算元
      int index=0;//開始擷取的地方
      //擷取數字，字元
      for(int i=0;i<str.length();i++){
        String c=String.valueOf(str.charAt(i));
        if(c.equals("*") || c.equals("/")|| c.equals("+")|| c.equals("-") || c.equals("=")){
          String first=str.substring(index,i);
          index=i+1;
          num.add(Integer.parseInt(first));
          op.add(c);
        }
      }
      while(op.size()>0){
          if(op.contains("*")){
            int numindex=op.indexOf("*");//1
            int number=0;
            number=num.get(numindex)*num.get(numindex+1);//5,2
            num.remove(numindex+1);
            num.remove(numindex);
            op.remove(op.indexOf("*"));
            num.add(numindex,number);
          }else if(op.contains("/")){
            int numindex=op.indexOf("/");//1
            int number=0;
            number=num.get(numindex)/num.get(numindex+1);//5,2
            num.remove(numindex+1);
            num.remove(numindex);
            op.remove(op.indexOf("/"));
            num.add(numindex,number);
          }else if(op.contains("+")){
            int numindex=op.indexOf("+");//0
            int number=0;
            number=num.get(numindex)+num.get(numindex+1);//5,2
            num.remove(numindex+1);
            num.remove(numindex);
            op.remove(op.indexOf("+"));
            num.add(numindex,number);
          }else if(op.contains("-")){
            int numindex=op.indexOf("-");//1
            int number=0;
            number=num.get(numindex)-num.get(numindex+1);//5,2
            num.remove(numindex+1);
            num.remove(numindex);
            op.remove(op.indexOf("-"));
            num.add(numindex,number);
          }else{
            break;
          }       
      }
      s=String.valueOf(num.get(0));
    }
    text.setText(s);
      }
}
class Main{
  public static void main(String[] args) throws Exception{
    MyJFramee f1= new MyJFramee();
  }
}
