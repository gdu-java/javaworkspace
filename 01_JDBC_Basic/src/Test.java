import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Test {
	
	public static void main(String[] args) {
		
		/*
		 * JDBC(JAVA DATABASE CONNECTIVITY)
		 *  ; 자바에서 데이터베이스에 접속할 수 있도록 하는 자바API이다.
		 *  
		 *  * JDBC용 객체
		 *  - DriverManager : Connection 객체를 생성하기 위한 객체
		 *  - Connection : DB에 접속된 객체(DB의 연결정보를 담고 있는 객체)
		 *  - [Prepared]Statement : 연결된 DB에 sql문을 전달해서 실행하고 그 결과를 받아내는 객체
		 *  - ResultSet : SELECT문 실행 후 조회된 결과를 담기위한 객체
		 *                INSERT,UPDATE,DELETE문(DML)은 실행 후 반환값이 int값이 됨. 즉 영향을 받은 행수
		 *                
		 *  * JDBC 연결 과정(순서 중요)              
		 *  
		 *  1) JDBC DRIVER 등록 : 해당 DBMS(오라클)가 제공하는 클래스 등록
		 *  2) Connection 생성 : 연결하고자하는 DB정보를 작성해서  해당 DB와 연결 생성
		 *  3) Statement 생성 : Connection객체를 이용해서 생성.(sql문을 실행하고 결과를 받음)
		 *  4) sql문을 DB에 전달하면서 실행 : Statement객체를 이용해서 sql문 실행
		 *  5) 결과 받기
		 *     > SELECT문 실행 => ResultSet객체를 이용해서 받음.(조회된 데이터들이 담겨있음.) => 6-1)
		 *     > DML문(INSERT,UPDATE,DELETE) => int (처리된 행 수)  => 6-2)
		 *  6-1) ResultSet에 담겨있는 데이터들을 하나씩 추출해서 변수나, 자바객체(ArrayList)에 옮겨 담는다.
		 *  6-2) 트랜잭션 처리(성공적으로 수행됐으면 COMMIT, 실패했으면 ROLLBACK)
		 *  
		 *  7) 다 사용된 JDBC용 객체 자원 반납(close) => 생성한 역순으로 반납처리
		 *  
		 * 
		 */
		
		 //조회된 값을 담을 변수 선언 및 초기화
		 int	tno = 0;
		 String tname = null;
		 Date	tdate = null;
		
		 // JDBC용 객체 생성 및 초기화
		 Connection conn = null;
		 Statement stmt  = null;
		 ResultSet rset  = null;
		 
		 // DB에 전달할 SQL문 작성. 마지막 세미콜론(;)은 포함되면 안됨.
		 String sql = "SELECT TNO, TNAME, TDATE FROM TEST";
		 
		 try {
			
			//1) JDBC드라이버 등록
			// ojdbc6.jar 파일이 등록안됐거나, 오타가 있을 경우 => ClassNotFoundException 예외 발생 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("ORACLE JDBC드라이버가 로드됐습니다.");
			
			//2)Connection 객체 생성(url(DB위치), username, password)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			//3) Statement 객체 생성(sql문 실행에 필요한 객체)
			stmt = conn.createStatement();
			
			// 4),5) sql문을 전달하면서 실행 후 결과(조회된 데이터)를 ResultSet객체에 받기 
			rset = stmt.executeQuery(sql);
			
			//6) ResultSet에 담겨있는 데이터들을 하나씩 추출
			//   ResultSet의 next()메서드는 조회된 다음 레코드가 있는지 확인해서 있으면 true, 없으면 false반환
			while(rset.next()) {
				
				//rset에 담겨있는 칼럼을 변수에 담기
				tno = rset.getInt("tno");
				tname = rset.getString("tname");
				tdate = rset.getDate("tdate");
				
				//변수 출력
				System.out.println("tno : "+tno+", tname : "+tname+", tdate : "+tdate);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// rset -> stmt -> conn 순으로 자원 반납
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
	}
}
