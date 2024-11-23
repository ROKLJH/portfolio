package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	DBConnect(){
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3399/test",
					"root",
					"1234"
					);
			
			if(conn != null) {
				System.out.println("Congratulations!! Connect OK");
			}
		}
		catch(Exception e) { e.printStackTrace(); }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("DB Connect Program");
		DBConnect dbconn = new DBConnect();
		
		// for Test
		HospitalVO vo = new HospitalVO();
		vo.setVO("테스트병원", "하늘", 12534);
		System.out.println(vo);
		
		dbconn.select1("서울").forEach(System.out::println);
		System.out.println("--------------------------------------");
		dbconn.select2("서울").forEach(System.out::println);
	}
	
	// Statement
	public ArrayList<HospitalVO> select1(String region){
		
		ArrayList<HospitalVO> arr = new ArrayList<>();
		
		String sql = "";
		sql += "select * from hptl_mast_bak ";
		sql += "where sido_cd_nm = '" + region + "' ";
		sql += "limit 10";
		
		System.out.println(sql);
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				HospitalVO vo = new HospitalVO();
				vo.setVO(rs.getString("hptl_nm"),
						 rs.getString("sido_cd_nm"),
						 rs.getInt("doc_num"));
				arr.add(vo);
			}
			
			rs = null;
		}
		catch(Exception e) { e.printStackTrace(); }
		
		return arr;
	}

	// PreparedStament
	public ArrayList<HospitalVO> select2(String region){
		
		ArrayList<HospitalVO> arr = new ArrayList<>();
		
		// PreparedStatement는 변수 부분을 ?로 작성
		String sql = "";
		sql += "select * from hptl_mast_bak ";
		sql += "where sido_cd_nm = ? ";
		sql += "limit 10";
		
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, region);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HospitalVO vo = new HospitalVO();
				vo.setVO(rs.getString("hptl_nm"),
						 rs.getString("sido_cd_nm"),
						 rs.getInt("doc_num"));
				arr.add(vo);
			}
			
			rs = null;
		}
		catch(Exception e) { e.printStackTrace(); }
		
		return arr;
	}
}
