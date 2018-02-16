package cn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.entity.User;
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao{
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public void saveUser(User user) {
		 hibernateTemplate.save(user);
	}

	public void deleteUser(User user) {
		 hibernateTemplate.delete(user);
	}

	public void updateUser(User user) {
		hibernateTemplate.update(user);
	}

	public User findUserByName(String name) {
		String hql="from User where name=:name";
		List list=hibernateTemplate.findByNamedParam(
				hql, "name", name);
		if(list.isEmpty()){
			return null;
		}
		return (User) list.get(0);
	}

	public User findUserById(String id) {
		String hql="from User where id=?";
		List list=
			hibernateTemplate.find(hql,id);
		if(list.size()==0){
			return null;
		}else{
			return (User) list.get(0);
		}
	}

	public List<User> findAllUsers() {
		String hql="from User";
		List<User> list=
				hibernateTemplate.find(hql);
		return list;
	}

	

	

}
