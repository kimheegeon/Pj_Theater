package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import db.TheaterDB;

public class PrintModel {
	Connection con;
	ArrayList<ArrayList<String>> infoList;
	String moviedate,screenId;
	
	public PrintModel() throws Exception {
		infoList = new ArrayList<ArrayList<String>>();
		con = TheaterDB.getConnection();
	}
	
	
	
	public int insertTel(String movietitle, String starttime, String endtime, ArrayList<String> selectedSeat,
			int person, int selectRoomnum, String date, String tel, int numOfDay) throws Exception {

		con.setAutoCommit(false);
//		===============================================================================================
		System.out.println("시작");
		String sql2 = "INSERT INTO point ( tel ) VALUES ( ? )";
		System.out.println("point -> tel 실패");
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setString(1, tel);
		int result2 = ps2.executeUpdate();

		if (result2 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("포인트 인설트");
// =================================================================================================
		String sql3 = "INSERT INTO payment ( paynum, sumof) VALUES ( sq_pay_paynum.nextval, ?)";
		System.out.println("payment -> 실패");
		PreparedStatement ps3 = con.prepareStatement(sql3);
		ps3.setInt(1, person*10000);
//		ps3.setString(2, optionof);
		
		int result3 = ps3.executeUpdate();

		if (result3 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("페이먼트 인설트");
//==================================================================================================
		
//		sql 작업중 
		String sql5 = "SELECT screenno, moviedate, numofday FROM screen";
		PreparedStatement ps5 = con.prepareStatement(sql5);
		ResultSet rs5 = ps5.executeQuery();

//		while(rs5.next()){
//			System.out.println(rs5.getInt("screenno")+rs5.getString("moviedate")+rs5.getInt("numofday")+"asdf");
//		
//		}
		
//		if(!rs5.next()){
//			String sql6 = "INSERT INTO   "
//					+ "screen(screenid, screenno, seats, selected, moviedate, numofday )   "
//					+ "VALUES (sq_screen_screenid.nextval, ?, ?, ?, ?, ? ) ";
//			PreparedStatement ps6 = con.prepareStatement(sql6);
//			ps6.setInt(1, selectRoomnum);
//			ps6.setInt(2, 80);
////			ps6.setString(3, selectedSeat.toString());
//			ps6.setString(3, "ddasdf");
//			ps6.setString(4, date);
//			ps6.setInt(5, numOfDay);
//			
//			System.out.println("INSERT 잘됨");
//			
//			int result6 = ps6.executeUpdate();
//			if (result6 != 1) {
//				con.rollback();
//				return -1;
//			}
			
//		}else{
			String sql7 = "UPDATE screen SET selected = ? WHERE SCREENID = ?";
			PreparedStatement ps7 = con.prepareStatement(sql7);
			System.out.println(selectedSeat.toString());
			StringTokenizer str = new StringTokenizer(selectedSeat.toString(), ",");
			int count = str.countTokens();
			ps7.setString(1, Integer.toString(count));
//			ps7.setString(1, selectedSeat.toString());
			moviedate = String.valueOf(date.charAt(5))+date.charAt(6)+date.charAt(8)+date.charAt(9);
			screenId = selectRoomnum+"#"+moviedate+"#"+numOfDay;
			ps7.setString(2, screenId);
			System.out.println(selectedSeat.toString() + "\n" +selectRoomnum+"#"+moviedate+"#"+numOfDay );
			int result7 = ps7.executeUpdate();
			
			if (result7 != 1) {
				con.rollback();
				return -1;
			}
			System.out.println("스크린 insert"+result7);
//		}
		
//	============================================	
//		while(rs5.next()){
//			ArrayList<String> temp = new ArrayList<String>();
//			temp.add(rs5.getString("screenno"));
//			temp.add(rs5.getString("moviedate"));
//			temp.add(rs5.getString("numofday"));
//			
//			infoList.add(temp);
//		}
//		if(infoList.get(0).get(0))
//	===============================================
//		String sql5 = "UPDATE screen SET SEATS = ? , SELECTEDNUM = ? , SELECTED = ?  WHERE SCREENNO = ? ";
//		System.out.println("screen -> 실패" + selectRoomnum);
//		PreparedStatement ps5 = con.prepareStatement(sql5);
//
//		ps5.setInt(1, 12314);
//		ps5.setInt(2, 5421);
//		ps5.setString(3, "asdfas");
//		ps5.setInt(4, selectRoomnum);
//		int result5 = ps5.executeUpdate();
//		System.out.println(result5);
//
//		if (result5 != 1) {
//			con.rollback();
//			return -1;
//		}
//		System.out.println("스크린 업데이트");
		
//		==============================================================================
		
		String seats="";
		for (int i = 0; i < selectedSeat.size(); i++) {
			 seats = seats+"&"+selectedSeat.get(i);
		}
		System.out.println(seats);
		
		
		String sql1 = "INSERT INTO booking ( bookno, screenid, runtime, moviedate, people, seat, tel, paynum, movie_no)     "
				+ "VALUES ( SQ_BOOKING_BOOKNO.nextval,?, ?, ?, ?, ?, ?, sq_pay_paynum.currval,(SELECT movie_no FROM movie WHERE TITLE = ?) )";
		System.out.println("booking -> 실패");
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1, screenId);
		ps1.setString(2, starttime);
		ps1.setString(3, date);
		ps1.setInt(4, person);
		ps1.setString(5, seats);
		ps1.setString(6, tel);
		ps1.setString(7, movietitle);
		int result1 = ps1.executeUpdate();

		if (result1 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("부킹 인설트");
//	=====================================================================================
				
		con.commit();
		con.setAutoCommit(true);
		ps1.close();
		ps2.close();
		ps3.close();
		// ps4.close();
		ps5.close();
		return 0;
	}

}
