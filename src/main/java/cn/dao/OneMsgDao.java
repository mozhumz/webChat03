package cn.dao;

import java.util.List;

import cn.entity.OneMsg;

public interface OneMsgDao {
	void saveOneMsg(OneMsg oneMsg);
	void deleteOneMsg(OneMsg oneMsg);
	void updateOneMsg(OneMsg oneMsg);
	OneMsg findOneMsgByMsgId(String msgId);
	OneMsg findOneMsgByUserIds(String userIdSend,String userIdTarget);
	List<OneMsg> findAllOneMsgs();
}
