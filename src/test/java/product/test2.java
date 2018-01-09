package product;




import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dascom.product.entity.CpSoftware;
import com.dascom.product.entity.Solves;
import com.dascom.product.entity.WorkOrder;
import com.dascom.product.serviceImpl.ResourceServiceImpl;
import com.dascom.product.serviceImpl.SolvesServiceImpl;
import com.dascom.product.serviceImpl.WorkOrderServiceImpl;
import com.dascom.product.util.JsonTransform;
import com.dascom.product.util.PagedResult;



public class test2 {
	ApplicationContext context=new ClassPathXmlApplicationContext("config/spring-*.xml");
	ResourceServiceImpl res=(ResourceServiceImpl) context.getBean("resourceServiceImpl");
	
	@Test
	public void sdf() throws Exception{
		WorkOrderServiceImpl workOrderServiceImpl=(WorkOrderServiceImpl) context.getBean("workOrderServiceImpl");
		PagedResult<WorkOrder> olist=workOrderServiceImpl.findWorkOrderPage(null, null, 0, "%%");
		for (WorkOrder item : olist.getDataList()) {
			System.out.println(item.getId());
		}
		
	}
	
	@Test
	public void sd(){
		try {
			CpSoftware soft= res.findSoftwareByVersonInfo("得实卡片设计器1", "Windows");
			if(soft!=null){
				if(!soft.getVersionNum().equals("1.2.4")){
					JsonTransform.loginJsonTransform("101", "找到最新版软件.", soft);
				}
			}
			JsonTransform.loginJsonTransform("102", "已经是最新版了.", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
