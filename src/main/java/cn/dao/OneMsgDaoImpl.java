package cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.entity.OneMsg;
@Repository("oneMsgDao")
@Transactional  
public class OneMsgDaoImpl implements OneMsgDao{
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public void saveOneMsg(OneMsg oneMsg) {
		hibernateTemplate.save(oneMsg);
	}

	public void deleteOneMsg(OneMsg oneMsg) {
		hibernateTemplate.delete(oneMsg);
	}

	public void updateOneMsg(OneMsg oneMsg) {
		hibernateTemplate.update(oneMsg);
	}

	public OneMsg findOneMsgByMsgId(String msgId) {
		String hql="from OneMsg where msgId=?";
		List<OneMsg>list=hibernateTemplate.find(hql,msgId);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public OneMsg findOneMsgByUserIds(String userIdSend, String userIdTarget) {
		String hql=" from OneMsg where userIdSend=? and userIdTarget=?";
		List<OneMsg>list=hibernateTemplate.find(hql,userIdSend,userIdTarget);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public List<OneMsg> findAllOneMsgs() {
		String hql="from OneMsg";
		return hibernateTemplate.find(hql);
	}

}
