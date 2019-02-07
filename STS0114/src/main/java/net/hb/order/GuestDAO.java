package net.hb.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component	//객체화시키겠다
@Repository //저장고 역활을 하겠다
public class GuestDAO {
	
	@Autowired
	SqlMapClientTemplate temp; // guest.xml문서의 id를 가져온다
	
	public void dbInsert(GuestDTO dto) { //guest_insert(dto)
		System.out.println();
		System.out.println("DAO 넘어온사번="+dto.getSabun());
		System.out.println("DAO 넘어온사번="+dto.getName());
		System.out.println("DAO 넘어온사번="+dto.getTitle());
		System.out.println("DAO 넘어온사번="+dto.getPay());
		temp.insert("guest.add", dto); //guest네임스페이스  add <insert id=add>의 의미 ... 여기서 guest.xml의 <insert id=add> insert~ 실행
		//ibatis데이터매칭 #값#  Mybatis는 #{}   el태그 ${}
		System.out.println("guest 테이블 데이터 저장성공");
	}//end
	
	
	public List<GuestDTO> dbSelect2(int start, int end) {	//guest_select() 시작행, end행
		@SuppressWarnings("unchecked")
		//DTO객체화후 setXXX(값)
		GuestDTO dto=new GuestDTO();
		dto.setStart(start);
		dto.setEnd(end);
		List<GuestDTO> list=temp.queryForList("guest.selectAll", dto);
		return list;
	}//end
	
	public List<GuestDTO> dbSelect(int start, int end, String skey, String sval) {	//guest_select() 처음꺼 재활용 시작행,end행,검색필드,키워드
		@SuppressWarnings("unchecked")
		//DTO객체화후 setXXX(값)
		GuestDTO dto=new GuestDTO();
		dto.setStart(start);
		dto.setEnd(end);
		dto.setSkey(skey);
		dto.setSval(sval);
		List<GuestDTO> list=temp.queryForList("guest.selectAll", dto);
		return list;
	}
	
	public int dbCount() {//조회결과도 객체로 받아서 형변환
		int cnt=(Integer)temp.queryForObject("guest.countAll");
		return cnt;
	}//end
	
	public int dbCountSearch(String skey, String sval) {
		GuestDTO dto=new GuestDTO();
		dto.setSkey(skey);
		dto.setSval(sval);
		int cnt=(Integer)temp.queryForObject("guest.countSearch", dto);
		return cnt;
	}
	
	public GuestDTO dbDetail(int data) {
		GuestDTO dto=(GuestDTO)temp.queryForObject("guest.detail", data);
		return dto;
	}//end
	
	public void dbDelete(int data) {
		temp.delete("guest.del", data);
	}
	
	public void dbEdit(GuestDTO dto) {
		temp.update("guest.edit", dto);
	}
	
}//DAO class END