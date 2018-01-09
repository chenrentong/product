package com.dascom.product.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dascom.product.dao.CpUserMapper;
import com.dascom.product.entity.CpUser;
import com.dascom.product.service.UserService;
import com.dascom.product.util.PageBeanUtil;
import com.dascom.product.util.PagedResult;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private CpUserMapper cpUserMapper;
	
	public CpUser findCpUserByUsername(String username){
		
		return cpUserMapper.selectCpUserByUsername(username);
	}

	

	@Override
	public PagedResult<CpUser> findByNamePage(String like, Integer pageNumber,
			Integer pageSize) {
		if(pageNumber!=null&&pageSize!=null)
			PageHelper.startPage(pageNumber,pageSize);
		if(like!=null||"".equals(like))
			like="%"+like+"%";
		
		return PageBeanUtil.toPagedResult(cpUserMapper.selectByName(like));
	}

	@Override
	public int addUser(CpUser user) {
		//验证用户名是否重复 .
		List<CpUser> userlist=cpUserMapper.selectByName(user.getUsername());
		if(userlist!=null&&userlist.size()>0){
			return -1;
		}
		user.setRegisterTime(new Date());
		user.setUpdateTime(new Date());
		return cpUserMapper.insertSelective(user);
	}

	@Override
	public Integer delUser(String id) {
		int num=0;
		String[] idlist=id.split(",");
		for (String id2 : idlist) {
			int i=cpUserMapper.deleteByPrimaryKey(Integer.parseInt(id2));
			num=i+num;
		}
		return num;
	}

	@Override
	public CpUser findUserByKey(Integer id) {
		return cpUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateUser(CpUser user) {
		//验证用户名是否重复 .
		List<CpUser> userlist=cpUserMapper.selectByName(user.getUsername());
		if(userlist!=null&&userlist.size()>1){
			return -1;
		}
		if(userlist.size()>0&&userlist.get(0).getId()!=user.getId()){
			return -1;
		}
		user.setUpdateTime(new Date());
		return cpUserMapper.updateByPrimaryKeySelective(user);
	}

}
