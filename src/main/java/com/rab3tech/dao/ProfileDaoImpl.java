package com.rab3tech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

@Repository //@Service, @Component , @Controller
public class ProfileDaoImpl implements ProfileDao {
	
	@Autowired
	@Qualifier("pdataSource")
	private DataSource datasource;
	
	JdbcTemplate jdbcTemplate=null;
	
	
	@PostConstruct 
	public void magicMethod() {
		
		jdbcTemplate=new JdbcTemplate(datasource);	
	}
	
	@Override 
	public String findPasswordByUsernameOrEmail (String pusernameEmail)
	{
		String pass = "";
		String sql = "select password from user_login_tbl where username = ? or email = ?";
		Object[] data = {pusernameEmail, pusernameEmail};
		try {
		pass = jdbcTemplate.queryForObject(sql, data,String.class );
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return pass;
	}
	
	
	@Override
	public String updateSignup(ProfileDTO profileDTO) {
		
		String sql = "update user_login_tbl set name=?,email=?,qualification=?,mobile=?,photo=?,gender=? where username=?";
		
		Object data[]={ profileDTO.getName(),profileDTO.getEmail(),profileDTO.getQualification(),profileDTO.getMobile(),
				profileDTO.getPhoto(),profileDTO.getGender(),profileDTO.getUsername()};
		jdbcTemplate.update(sql,data);
		return "success";
	}

	@Override
	public String createSignup(ProfileDTO profileDTO) {
		String sql = "insert into  user_login_tbl(username,password,name,email,qualification,mobile,photo,gender,createdate) values(?,?,?,?,?,?,?,?,?)";
		Object data[]={profileDTO.getUsername(),profileDTO.getPassword(),profileDTO.getName(),profileDTO.getEmail(),
				profileDTO.getQualification(),profileDTO.getMobile(),profileDTO.getPhoto(),profileDTO.getGender(),new Timestamp(new Date().getTime())};
		jdbcTemplate.update(sql,data);
		return "success";
	}
	
	@Override
	public String icreateSignup(ProfileDTO profileDTO) {
		byte[] image = null;
		try {
			 image = profileDTO.getFile().getBytes();
		} catch (IOException e) {
			 	
			e.printStackTrace();
		}
		LobHandler lobHandler=new DefaultLobHandler();
	    SqlLobValue sqlLobValue=new SqlLobValue(image,lobHandler);
		
		String sql = "insert into iuser_login_tbl(username,password,name,email,qualification,mobile,photo,gender,createdate) values(?,?,?,?,?,?,?,?,?)";
		Object data[]={profileDTO.getUsername(),profileDTO.getPassword(),profileDTO.getName(),profileDTO.getEmail(),
				profileDTO.getQualification(),profileDTO.getMobile(),sqlLobValue,profileDTO.getGender(),new Timestamp(new Date().getTime())};
		int[] datatype = new int[] {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.BLOB,Types.VARCHAR,Types.TIMESTAMP };
		jdbcTemplate.update(sql,data, datatype);
		return "success";
	}
	

	@Override
	public List<ProfileDTO> sortProfiles(String sort) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl order by email "
				+ sort;
		List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,new BeanPropertyRowMapper(ProfileDTO.class));
	
		return profileDTOs;
	}
	
	@Override
	public List<String> findAllQualification() {
		List<String> qualifications = new ArrayList<>();
		String sql = "select distinct qualification from user_login_tbl ";
		try(Connection conn = datasource.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);ResultSet rs = pstmt.executeQuery();){
			
			while (rs.next()) { 
				String q = rs.getString(1);
				qualifications.add(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qualifications;
	}
	
	
	
	@Override
	public List<ProfileDTO>  filterProfiles(String filterText) {
		
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl  where qualification = ?";
		Object[] data={filterText};
		List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,data,new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> searchProfiles(String search) {
		
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl  where name like '%"
				+ search + "%'  or  qualification like '%" + search + "%'";
		List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> findAll() {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl";
		List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs;
	}
	
	@Override
	public List<ProfileDTO> findAllWithPhoto() {
		String sql = "select username,password,name,email,qualification,mobile, gender,createdate from iuser_login_tbl";
		List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs;
	}	
	
	
	
	@Override
	public ProfileDTO findByEmail(String pemail) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where email=?";
		Object[] data={pemail};
		ProfileDTO profileDTO=jdbcTemplate.queryForObject(sql, data,new BeanPropertyRowMapper<>(ProfileDTO.class));
		return profileDTO;
		
	}

	@Override
	public ProfileDTO findByUsername(String pusername) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where username=?";
		Object[] data={pusername};
		ProfileDTO profileDTO=jdbcTemplate.queryForObject(sql, data,new BeanPropertyRowMapper<>(ProfileDTO.class));
		return profileDTO;
	}
	
	@Override
	public byte[] findPhotoByUsername(String pusername) {
		String sql = "select photo from iuser_login_tbl where username='"+pusername+"'";
		byte[] photo=jdbcTemplate.queryForObject(sql,byte[].class);
		return photo;
	}

	@Override
	public void deleteByUsername(String pusername) {
		String sql = "delete from user_login_tbl where username=?";
		Object[] data={pusername};
		jdbcTemplate.update(sql,data);
	}

	@Override
	public ProfileDTO authUser(String pusername, String ppassword) {
		ProfileDTO profileDTO = null;
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where username=? and password=?";
		ResultSet rs=null;
		try(Connection conn = datasource.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, pusername);
			pstmt.setString(2, ppassword);
			 rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				String username = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String qualification = rs.getString(5);
				String mobile = rs.getString(6);
				String photo = rs.getString(7);
				String gender = rs.getString(8);
				profileDTO = new ProfileDTO(username, password, name, email, mobile, gender, photo, qualification);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return profileDTO;

	}

	@Override
	public void show(){
		try {
		Connection conn=datasource.getConnection();
		if( conn!=null)
		System.out.println("Connection is established!!!!!!!!!!!!!!!"+ conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
