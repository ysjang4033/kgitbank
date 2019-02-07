package net.hb.project.Board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class BoardDAO {

 @Autowired
 private SqlSessionTemplate sqlSession;
 
 public void b_insert(BoardDTO bto) {
	 
	 System.out.println("id="  + bto.getB_id());
	 System.out.println("title=" + bto.getB_title());
	 System.out.println("content=" + bto.getB_content());
	 System.out.println("img="  + bto.getB_img_file_name());

	 sqlSession.insert("board.insert", bto);
	 
 }
 
 public List<BoardDTO> b_list(int start, int end, String keyfield, String keyword) {
	 
	 BoardDTO bto=new BoardDTO();
	 bto.setKeyfield(keyfield);
	 bto.setKeyword(keyword);
	 bto.setStart(start);
	 bto.setEnd(end);
	 
	 List<BoardDTO> list=sqlSession.selectList("board.select", bto);
	 
	 return list;
 }
 
 public int b_count() {
	 
	 int cnt=sqlSession.selectOne("board.count");
	 
	 return cnt;
 }
 
 public int b_Searchcount(String keyfield, String keyword) {
	 
	 BoardDTO bto=new BoardDTO();
	 bto.setKeyfield(keyfield);
	 bto.setKeyword(keyword);
	 
	 int cnt=sqlSession.selectOne("board.searchcount", bto);
	 
	 return cnt;
 }
 
 public BoardDTO b_detail(int data) {

	 BoardDTO bto=sqlSession.selectOne("board.detail", data);
	 
	 return bto;
 }
 
 public void b_delete(int data) {
	 
	 sqlSession.delete("board.delete", data);
 }
 
 public void b_edit(BoardDTO bto) {
	 
	 System.out.println(bto.getB_idx());
	 
	 sqlSession.update("board.edit", bto);
	 
 }
 
 
}
