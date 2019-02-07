package net.hb.crud;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class BoardDAO {
	
 //org.mybatis.spring.SqlSessionTemplate
 @Autowired
 SqlSessionTemplate temp;
	
 public void dbInsert(BoardDTO dto) {
//	System.out.println("\ndao�Ѿ���̸�=" +dto.getName() );
//	System.out.println("dao�Ѿ������=" +dto.getTitle());
//	System.out.println("dao�Ѿ�³���=" +dto.getContent());
//	System.out.println("dao�Ѿ�¼���=" +dto.getGender());
//	System.out.println("dao�Ѿ�����=" +dto.getHobby());
//	System.out.println("dao�Ѿ������=" +dto.getUpload_f());
//	System.out.println("dao����������=" +dto.getImg_file_name());
	temp.insert("board.add", dto);
//	System.out.println("hobby���̺� ���������强��");
 }//end
 
 public BoardDTO dbRcount() {
	 BoardDTO dto=temp.selectOne("board.rcnt");
	 return dto;
 }
 
 public int dbCount() {
	 int cnt= temp.selectOne("board.countAll");
	 return cnt;
 }
 
 public int dbCountSearch(String skey, String sval) {
	 
	 BoardDTO dto=new BoardDTO();
	 dto.setSkey(skey);
	 dto.setSval(sval);
	 int cnt= (Integer)temp.selectOne("board.countSearch", dto);
	 System.out.println("들어옴?"+cnt+skey+sval);
	 return cnt;
 }
  
 public List<BoardDTO> dbList(int start, int end, String skey, String sval) {
	 BoardDTO dto=new BoardDTO();
	 dto.setStart(start);
	 dto.setEnd(end);
	 dto.setSkey(skey);
	 dto.setSval(sval);
	 System.out.println(start+""+end+""+skey+sval);
	 List<BoardDTO> list=temp.selectList("board.select", dto);
	 return list;
 }
 
 public BoardDTO dbDetail(int data) {
	 BoardDTO dto=temp.selectOne("board.detail", data);
	 return dto;
 }
 
 public void dbDelete(int data) {
	 temp.delete("board.del", data);
 }
 
 public void dbEdit(BoardDTO dto) {
	 temp.update("board.edit", dto);
 }
 
 
 
}//BoardDA end
