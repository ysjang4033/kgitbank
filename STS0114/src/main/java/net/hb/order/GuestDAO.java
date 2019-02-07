package net.hb.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component	//��üȭ��Ű�ڴ�
@Repository //����� ��Ȱ�� �ϰڴ�
public class GuestDAO {
	
	@Autowired
	SqlMapClientTemplate temp; // guest.xml������ id�� �����´�
	
	public void dbInsert(GuestDTO dto) { //guest_insert(dto)
		System.out.println();
		System.out.println("DAO �Ѿ�»��="+dto.getSabun());
		System.out.println("DAO �Ѿ�»��="+dto.getName());
		System.out.println("DAO �Ѿ�»��="+dto.getTitle());
		System.out.println("DAO �Ѿ�»��="+dto.getPay());
		temp.insert("guest.add", dto); //guest���ӽ����̽�  add <insert id=add>�� �ǹ� ... ���⼭ guest.xml�� <insert id=add> insert~ ����
		//ibatis�����͸�Ī #��#  Mybatis�� #{}   el�±� ${}
		System.out.println("guest ���̺� ������ ���强��");
	}//end
	
	
	public List<GuestDTO> dbSelect2(int start, int end) {	//guest_select() ������, end��
		@SuppressWarnings("unchecked")
		//DTO��üȭ�� setXXX(��)
		GuestDTO dto=new GuestDTO();
		dto.setStart(start);
		dto.setEnd(end);
		List<GuestDTO> list=temp.queryForList("guest.selectAll", dto);
		return list;
	}//end
	
	public List<GuestDTO> dbSelect(int start, int end, String skey, String sval) {	//guest_select() ó���� ��Ȱ�� ������,end��,�˻��ʵ�,Ű����
		@SuppressWarnings("unchecked")
		//DTO��üȭ�� setXXX(��)
		GuestDTO dto=new GuestDTO();
		dto.setStart(start);
		dto.setEnd(end);
		dto.setSkey(skey);
		dto.setSval(sval);
		List<GuestDTO> list=temp.queryForList("guest.selectAll", dto);
		return list;
	}
	
	public int dbCount() {//��ȸ����� ��ü�� �޾Ƽ� ����ȯ
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