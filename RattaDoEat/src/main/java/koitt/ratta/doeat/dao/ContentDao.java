package koitt.ratta.doeat.dao;

import java.util.List;

import koitt.ratta.doeat.domain.ContentVO;

public interface ContentDao {

	public List<ContentVO> getAll();
	public void write(ContentVO vo);
	public ContentVO read(int rIdx);
	public void hitUpdate(int rIdx);
	public void update(ContentVO vo);
	public void delete(int rIdx);
	public void likeNumUpdate(int rIdx);
	public void scrapNumUpdate(int rIdx);
	public List<ContentVO> getAllByUser(int uIdx);
	public ContentVO viewByRIdx(int rIdx);
	
}
