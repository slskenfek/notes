package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	public static SqlSessionFactory sqlMapper = null;

	private static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			try {
				String resource = "mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	public List<MemberVO> selectAllMemberList() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> memlist = null;
		memlist = session.selectList("mapper.member.selectAllMemberList");
		return memlist;
	}

	public MemberVO selectMemberById(String id){
      sqlMapper=getInstance();
      SqlSession session=sqlMapper.openSession();
      //-selectOne()메소드는 하나의 레코드를 조회할 때 사용합니다.
      //-selectOne()메소드의 두번째 인자는 서블릿에서 selectMemberById(매개변수)메소드 호출시..
      // 매개변수로 넘어온 id의 값을 selectOne()메소드 호출시 해당 SQL문의 조건 값으로 전달 합니다.
      MemberVO memberVO=session.selectOne("mapper.member.selectMemberById",id);
      return memberVO;		
	   }

	public List<MemberVO> selectMemberByPwd(int pwd) {
	sqlMapper = getInstance();
	SqlSession session = sqlMapper.openSession();
	List<MemberVO> membersList = null;
	//- 비밀번호가 같은 회원은 여러명 있을 수 있으므로 selectList()메소드를 호출하여 사용함.
	//- selectList()메소드의 두번째 인자는 서블릿에서 selectMemberByPwd(매개변수)메소드 호출시..
    // 매개변수로 넘어온 정수데이터 pwd의 값을 selectOne()메소드 호출시 해당 SQL문의 조건 값으로 전달 합니다.  
	membersList= session.selectList("mapper.member.selectMemberByPwd", pwd);
	return membersList;
	}

	
	//MemberDAO클래스에서 insert문을 사용하려면 SqlSession 클래스의  insert()메소드를 이용해야 합니다.
	//insert()메소드의  첫번째 인자에는 실행하고자 하는 SQL문의 id속성값을 입력하고
	//               두번째 인자에는 SQL문으로 전달할 데이터를 지정합니다.
	//SQL문으로 전달할 데이터는 <insert>태그의 parameterType속성의 데이터 타입인 MemberVO클래스와 일치 해야함.
	public int insertMember(MemberVO  memberVO){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		result = session.insert("mapper.member.insertMember",memberVO);
		session.commit();
		return result;
		
	}

	public int insertMember2(Map<String, String>  memberMap){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		result = session.insert("mapper.member.insertMember2",memberMap);
		session.commit();
		return result;
		
	}
	
	public int updateMember(MemberVO memberVO){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		result = session.update("mapper.member.updateMember",memberVO);
		session.commit();
		return result;
		
	}
	
	public void deleteMember(String id){
		sqlMapper = getInstance(); //SqlSessionFactory객체 얻기 
		
		SqlSession session = sqlMapper.openSession(); //SqlSession객체 얻기
		
		//Sqlsession객체의 delete메소드 호출하여  member.xml로 접근하여 .. DELETE구문 실행함
		session.delete("mapper.member.deleteMember",id);
		
		session.commit();	
	}
	
	public List searchMember(MemberVO  memberVO){
		
		sqlMapper = getInstance(); //SqlSessionFactory객체 얻기 
		
		SqlSession session = sqlMapper.openSession(); //SqlSession객체 얻기
		
		List list = session.selectList("mapper.member.searchMember", memberVO);
		
		return list;
		
	}
	
	public List foreachSelect(List nameList){
		
		sqlMapper = getInstance(); //SqlSessionFactory객체 얻기 
		
		SqlSession session = sqlMapper.openSession(); //SqlSession객체 얻기
		
		List list = session.selectList("mapper.member.foreachSelect", nameList);
		
		return list;
	}
	
	public int foreachInsert(List memList){
		
		sqlMapper = getInstance(); //SqlSessionFactory객체 얻기 
		
		SqlSession session = sqlMapper.openSession(); //SqlSession객체 얻기
		
		int result = session.insert("mapper.member.foreachInsert", memList);
		System.out.println(result);
		session.commit();
		
		return result;
	}
	

}//MemberDAO 클래스 닫는 부분







