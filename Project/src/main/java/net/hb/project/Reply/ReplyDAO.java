package net.hb.project.Reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class ReplyDAO {

 @Autowired
 private SqlSessionTemplate sqlSession;
 
 	public List<ReplyDTO> r_list(int b_idx) {
 		
 		List<ReplyDTO> list=sqlSession.selectList("reply.select", b_idx);
 		
 		return list;
 	}
 	
 	public void r_delete(int b_idx) {
 		
 		sqlSession.delete("reply.delete", b_idx);
 	}

	public ReplyDTO r_detail(int r_idx) {
		ReplyDTO rto=sqlSession.selectOne("reply.detail", r_idx);
		return rto;
	}
	
	public void r_update(ReplyDTO rto) {
		
		sqlSession.update("reply.update", rto);
	}
	
	public void r_insert(ReplyDTO rto) {
		
		sqlSession.insert("reply.insert", rto);
	}
	
}
