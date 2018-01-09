package com.dascom.product.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dascom.product.dao.CpResourceMapper;
import com.dascom.product.dao.CpResourceSetMapper;
import com.dascom.product.dao.CpSoftwareMapper;
import com.dascom.product.entity.CpResource;
import com.dascom.product.entity.CpResourceExample;
import com.dascom.product.entity.CpResourceSet;
import com.dascom.product.entity.CpSoftware;
import com.dascom.product.service.ResourceService;
import com.dascom.product.util.PageBeanUtil;
import com.dascom.product.util.PagedResult;
import com.github.pagehelper.PageHelper;
@Service
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private CpResourceMapper cpResourceMapper;
	@Autowired
	private CpResourceSetMapper cpResourceSetMapper;
	
	@Autowired
	private CpSoftwareMapper cpSoftwareMapper;
	

	@Override
	public PagedResult<CpResourceExample> findApp(Integer pageNumber,
			Integer pageSize) {
		if(pageNumber!=null&&pageSize!=null)
			PageHelper.startPage(pageNumber,pageSize);
		
		return PageBeanUtil.toPagedResult(cpResourceMapper.selectByTypeTitle("应用软件",null));
	}

	@Override
	public List<CpSoftware> findAppEdition(Integer rId) {
		return cpSoftwareMapper.selectByRid(rId);
	}



	@Override
	public int addresource(String type, String title,String name, MultipartFile file,String imgFilePath, String filePath, String describe) {
		
		CpResource res=new CpResource();
		res.setCoverUrl(" ");
		if(imgFilePath!=null){
			res.setCoverUrl(imgFilePath);
		}
		//数据库url不能为空
		res.setUrl(" ");
		if(file!=null){
			res.setSize(file.getSize()+"");
			res.setUrl(filePath);
		}
		res.setDescribe(describe);
		res.setTime(new Date());
		res.setTitle(title);
		res.setName(name);
		//获取该类型的id
		CpResourceSet ress=cpResourceSetMapper.selectByTitle(type);
		res.setType(ress.getId());
		
		return cpResourceMapper.insertSelective(res);
	}

	@Override
	public int delByKey(Integer rid) {
		return cpResourceMapper.deleteByPrimaryKey(rid);
	}

	@Override
	public int delByKeyList(String rid) {
		int num=0;
		String[] ridlist=rid.split(",");
		for (String id : ridlist) {
			num =num+cpResourceMapper.deleteByPrimaryKey(Integer.valueOf(id));
		}
		return num;
	}

	@Override
	public int updateresource(CpResource res) {
		return cpResourceMapper.updateByPrimaryKeySelective(res);
	}

	@Override
	public CpResource findByRid(Integer rid) {
		return cpResourceMapper.selectByPrimaryKey(rid);
	}

	@Override
	public PagedResult<CpResourceExample> findApp(String typeTitle,Integer pageNumber,
			Integer pageSize, String like) {
		if(pageNumber!=null&&pageSize!=null)
			PageHelper.startPage(pageNumber,pageSize);
		
		List<CpResourceExample> list=cpResourceMapper.selectByTypeTitle(typeTitle,like!=null?"%"+like+"%":"%%" );
		return PageBeanUtil.toPagedResult(list);
	}

	@Override
	public PagedResult<CpSoftware> findAppVersion(Integer pageNumber,
			Integer pageSize, Integer rId) {
		if(pageNumber!=null&&pageSize!=null)
			PageHelper.startPage(pageNumber,pageSize);
		return  PageBeanUtil.toPagedResult(cpSoftwareMapper.selectByRid(rId));
		
	}

	@Override
	public int addsoft(CpSoftware soft) {
		//验证版本名字是否重复
		CpSoftware s=new CpSoftware();
		s.setVersionNum(soft.getVersionNum());
		s.setSystem(soft.getSystem());
		s.setRid(soft.getRid());
		if(cpSoftwareMapper.selectByExample (s).size()>0){
			return -1;
		}
		//验证该  支持系统是否有最新版
		if(soft.getIsNew()!=null){
			if(soft.getIsNew()==1){
				s=new CpSoftware();
				s.setSystem(soft.getSystem());
				s.setIsNew(soft.getIsNew());
				s.setRid(soft.getRid());
				if(cpSoftwareMapper.selectByExample (s).size()>0){
					return -2;
				}
			}
		}
		
		//设置添加时间
		soft.setTime(new Date());
		int number =  cpSoftwareMapper.insertSelective(soft);
		if(number>0){
			//删除上传表对应数据
		}
		return number;
	}
	
	@Override
	public int editSoft(CpSoftware soft) {
		//验证版本名字是否重复
		CpSoftware s=new CpSoftware();
		s.setVersionNum(soft.getVersionNum());
		s.setSystem(soft.getSystem());
		s.setRid(soft.getRid());
		for (CpSoftware item : cpSoftwareMapper.selectByExample (s)) {
			if(item.getId()!=soft.getId()){
				return -1;
			}
		}
		//验证该  支持系统是否有最新版
		if(soft.getIsNew()!=null){
			if(soft.getIsNew()==1){
				s=new CpSoftware();
				s.setSystem(soft.getSystem());
				s.setIsNew(1);
				s.setRid(soft.getRid());
				for (CpSoftware item : cpSoftwareMapper.selectByExample (s)) {
					if(item.getId()!=soft.getId()){
						return -2;
					}
				}
			}
		}
		if(soft.getUrl()!=null){
			if(soft.getUrl().equals("")){
				return cpSoftwareMapper.updateByPrimaryKeySelective(soft);
			}
		}
		@SuppressWarnings("unused")
		CpSoftware s2=cpSoftwareMapper.selectByPrimaryKey(soft.getId());
		int number= cpSoftwareMapper.updateByPrimaryKeySelective(soft);
		if(number>0){
			//删除原来应用软件版本的本地文件
		}
		return number;
	}
	
	@Override
	public int delVersion(String id) {
		int num=0;
		String[] ridlist=id.split(",");
		for (String item : ridlist) {
			num =num+cpSoftwareMapper.deleteByPrimaryKey(Integer.valueOf(item));
		}
		return num;
	}

	@Override
	public CpSoftware findVersionBykey(Integer id) {
		return cpSoftwareMapper.selectByPrimaryKey(id);
	}

	

	@Override
	public CpSoftware findSoftwareByVersonInfo(String title, String system) {
		return cpSoftwareMapper.findSoftwareBySoftware(title,system);
	}


	@Override
	public CpSoftware findSoftwareByVersion(String name) {
		return cpSoftwareMapper.selectSoftwareByVersion(name);
	}

	@Override
	public CpSoftware findSoftwareByKey(String name) {
		
		return cpSoftwareMapper.selectByPrimaryKey(Integer.valueOf(name));
	}


	@Override
	public PagedResult<CpResourceExample> findResource(String typeName,
			Integer pageNumber, Integer pageSize) {
		if(pageNumber!=null&&pageSize!=null)
			PageHelper.startPage(pageNumber,pageSize);
		
		return PageBeanUtil.toPagedResult(cpResourceMapper.selectByTypeTitle(typeName,null));
	}

	@Override
	public void editSoftDownloadNum(CpSoftware soft) {
		if(soft.getDownloadNum()==null){
			soft.setDownloadNum(1);
		}else{
			soft.setDownloadNum(soft.getDownloadNum()+1);
		}
		cpSoftwareMapper.updateByPrimaryKeySelective(soft);
		
	}


	

}
