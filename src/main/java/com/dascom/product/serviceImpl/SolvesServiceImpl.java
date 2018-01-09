package com.dascom.product.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dascom.product.dao.SolvesMapper;
import com.dascom.product.entity.Solves;
import com.dascom.product.service.SolvesService;
import com.dascom.product.util.PageBeanUtil;
import com.dascom.product.util.PagedResult;
import com.github.pagehelper.PageHelper;

@Service
public class SolvesServiceImpl implements SolvesService {
	
	@Autowired
	private SolvesMapper solvesMapper;
	

	@Override
	public PagedResult<Solves> findsolveList(Integer pageNumber,
			Integer pageSize, String title) {
		if(pageNumber!=null&&pageSize!=null)
			PageHelper.startPage(pageNumber,pageSize);
		
		return PageBeanUtil.toPagedResult(solvesMapper.selectBytitle(title==null?null:"%"+title+"%"));
	}

	@Override
	public int addSolves(Solves solves) throws Exception {
		try {
			solves.setTime(new Date());
			return solvesMapper.insertSelective(solves);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("出现了意外的错误.");
		}
	}

	@Override
	public int delSolves(String id) {
		int num=0;
		String[] ridlist=id.split(","); 
		for (String string : ridlist) {
			num=num+solvesMapper.deleteByPrimaryKey(Integer.valueOf(string));
		}
		return num;
	}

	@Override
	public Solves findSolvesByKey(Integer id) {
		return solvesMapper.selectByPrimaryKey(id);
	}

	@Override
	public int editSolves(Solves solves) {
		return solvesMapper.updateByPrimaryKeySelective(solves);
	}

	@Override
	public List<Solves> seleteBySolves(Solves solves) {
		return solvesMapper.selectByExample(solves);
	}
		
	
	
}
