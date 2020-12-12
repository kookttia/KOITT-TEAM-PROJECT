package koitt.ratta.doeat.dao;

import java.util.List;

import koitt.ratta.doeat.domain.GalleryListVo;
import koitt.ratta.doeat.domain.GalleryVo;

public interface GalleryDao {

	List<GalleryListVo> viewAll();
	
	List<GalleryListVo> viewAllByUser(int u_idx);

	// 게시물 작성
	public int insertG(GalleryVo vo);
	
	// 게시물 상세보기
	public GalleryVo viewDetail(int gIdx);

	GalleryListVo viewByGIdx(int gIdx);
	
//	// 유저 이름 조회
//	public String userName(int gIdx);
}
