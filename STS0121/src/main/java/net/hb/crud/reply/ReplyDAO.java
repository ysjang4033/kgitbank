package net.hb.crud.reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAO {
	
	@Autowired
	SqlSessionTemplate temp;
	
	public void dbInsert(ReplyDTO dto) {
	  System.out.println();
	  System.out.println("댓글dao 넘어온idx="  + dto.getHobby_idx());
		System.out.println("댓글dao 넘어온저자="  + dto.getRwriter() );
		System.out.println("댓글dao 넘어온메모="  + dto.getRmemo());
		try {
			temp.insert("board_reply.add", dto);
		}catch(Exception ex) { System.out.println("댓글저장에러: "+ex);} 
	} //end
	
	public List<ReplyDTO> dbSelect(int idx) {
		List<ReplyDTO> list=null;
		 try {
		 list = temp.selectList("board_reply.selectAll", idx);
		 }catch(Exception ex) { System.out.println("hobby댓글 dbSelect"+ ex); }
		return list;
	}

	
	public void dbDelete(int idx) {
		temp.delete("board_reply.del", idx);
	} //end
	
	public ReplyDTO dbDetail(int idx) {
		ReplyDTO dto = temp.selectOne("board_reply.selectOne", idx);
		return dto;
	}
	
	public void dbUpdate(ReplyDTO dto) {
		temp.update("board_reply.edit", dto);
	}
	
}//ReplyDAO class END
