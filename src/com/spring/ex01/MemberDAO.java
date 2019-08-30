package com.spring.ex01;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	//SqlSessionFactory객체를 저장할 변수
	public static SqlSessionFactory sqlMapper = null;
	
	private static SqlSessionFactory getInstance(){
		
		if(sqlMapper == null){
			try {
				//MemberDAO의 각 메소드 호출시 src/mybatis/SqlMapConfig.xml에서
				//설정정보를 읽은 후 데이터베이스와 연동 준비를 합니다.
				Reader reader = 
						Resources.getResourceAsReader("mybatis/SqlMapConfig.xml");
				
				//마이바티스를 이용하는 SqlSessionFactory객체를 가져옵니다.
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				
				//자원해제
				reader.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper; //SqlSessionFactory객체 반환
		
	}//getInstance()메소드 끝
	
	
	public List<MemberVO> selectAllMemberList(){
		//마이바티스프레임워크에 속해 있는 SqlSessionFactory객체 얻기
		sqlMapper = getInstance();
		
		//실제 member.xml의 SQL문을 호출하는데 사용되는 SqlSession객체를 가져옵니다.
		SqlSession session = sqlMapper.openSession();
		
		//조회한 회원정보(MemberVO)들을 저장하기 위한 용도의  List변수선언
		List<MemberVO> memlist = null;
		
		//여러개의 코드를 조회하므로 selectList()메소드를 호출하면서
		//인자로 mapper.member.selectAllMemberList를 전달해
		//member.xml에서 해당 네임스페이스와 id속성에 해당하는 SQL문을 실행합니다.
		//여기서 selectList()메소드는 member.xml파일 내부에서 DB로 부터 검색한 결과를
		//MemberVO객체 각각 담고, 다시 List에 담아 리턴 받는다.
		memlist = session.selectList("mapper.member.selectAllMemberList");
		
		return memlist; //List리턴
		
	}
	
	
	
	
}//MmeberDAO클래스 끝









