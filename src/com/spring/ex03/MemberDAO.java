package com.spring.ex03;

import java.io.Reader;
import java.util.List;

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



}
