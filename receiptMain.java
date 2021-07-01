package receipt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuSQL {

   public static void main(String[] args) {
      int count = 0;
      int InputNO;
      String sql;
      Scanner scan = new Scanner(System.in);
      ArrayList<receiptArrayList> OrderList = new ArrayList<receiptArrayList>();

      while (true) {
         receiptArrayList item = new receiptArrayList();

         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/goodslist", "root", "123456");

            System.out.print("상품 번호를 입력하세요 : ");
            InputNO = scan.nextInt();

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select * from goods WHERE no=" + InputNO);

            

            if (rset.next()) {
               System.out.printf("%d %s %d원 %s\n", rset.getInt(1), rset.getString(2), rset.getInt(3),
                     rset.getString(4));
               item.name = rset.getString(2);
               item.price = rset.getInt(3);
            } else {
               System.out.println("해당하는 상품이 없습니다.");
            }
            rset.close();
            stmt.close();
            conn.close();

         } catch (Exception e) {
            e.printStackTrace();
         }
         System.out.println("구매 갯수 : ");
         count = scan.nextInt();
         item.total = item.price * count;

         OrderList.add(item);

         int type;
         System.out.println("1. 추가구매  2.구매 종료");
         type = scan.nextInt();

         if (type == 2) {
            break;
         }

      }
      scan.close();
      for (int index = 0; index < OrderList.size(); index++) {
         System.out.printf("%s %5s \n", "[매장명]", "카페리즈(LEEZ)");
         System.out.printf("%s %5s \n", "[사업자]", "178-02-01306");
         System.out.printf("%s %6s \n", "[주 소]", "서울 강남구 봉은사로61길 31 (삼성");
         System.out.println("동, 이화빌리지) 1층 101호(삼성동)");
         System.out.printf("%s %3s %7s %5s \n", "[대표자]", "김유경", "[TEL]", "010-5918-4947");
         System.out.printf("%s %5s \n", "[매출일]", "2021-03-09 17:15:24  01-");
         System.out.println("====================================");
         System.out.printf("%10s %10s %3s %6s \n", "상 품 명", "단 가", "수량", "금 액");
         System.out.println("------------------------------------");
         System.out.printf("%s %10d %3d %8d \n", OrderList.get(index).name, OrderList.get(index).price, count,
               OrderList.get(index).price * count);
         System.out.println("------------------------------------");
         System.out.printf("%s %26s \n", "합 계 금 액", OrderList.get(index).price * count);
         System.out.println("------------------------------------");
         System.out.printf("%18s %13s \n", "부가세 과세물품가액", "12,728");
         System.out.printf("%21s %12s  \n", "부     가     세", " 1,272");
         System.out.println("------------------------------------");
         System.out.printf("%s %26d \n", "받 을 금 액", OrderList.get(index).price * count);
         System.out.printf("%s %26d \n", "받 은 금 액", OrderList.get(index).price * count);
         System.out.println("------------------------------------");
         System.out.printf("%27s \n", "*** 신용승인정보(고객용)[1] ***");
         System.out.println("------------------------------------");
         System.out.printf("주문하신 %s, %d잔. 금액은 %d 원 입니다.\n\n\n", OrderList.get(index).name, count,
               OrderList.get(index).price * count);
      }
      
      String sql = "update goodslist set no =?, name = ?, price = ?, text = ?";
      PreparedStatement pstmt = null;
      
      try {
         pstmt = connection.prepareStatement(sql);
         pstmt.setInt(1, item.no);
         pstmt.setString(2, item.name);
         pstmt.setInt(3, item.price);
         pstmt.setString(4, item.text);
         pstmt.executeUpdate();
      } catch(Exception e) {
         e.printStackTrace();
      }finally {
         try {
            if(pstmt != null && !pstmt.isClosed()) {
               pstmt.close();
               
            } catch (SQLException e ) {
               e.printStackTrace();
            }
            
         }
      }

   }
   
   

}
